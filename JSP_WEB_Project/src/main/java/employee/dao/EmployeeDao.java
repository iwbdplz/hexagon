package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import employee.model.Employee;
import employee.service.EmployeeWithUserInfoAndRetiredDate;
import employee.service.EmployeeWithUserInfoAndTotalSalaryAndRetireDate;
import jdbc.JdbcUtil;
import userinfo.model.UserInfo;

//　データベースに接近するメソードを管理するクラス。
// 데이터베이스에 접근하는 메서드를 관리하는 클래스
public class EmployeeDao {
	//　ユーザー登録。
	// 유저 등록
	//　ユーザーID、所属、職位、入社日、使用銀行、口座番号が必要。
	// 유저ID, 소속, 직위, 입사일, 사용은행, 계좌번호가 필요하다.
	private static final String INSERT_EMPLOYEE = "insert into employee values(?,?,?,?,?,?,?)";
	//　ユーザーページングの為にユーザーの数を数える。
	// 유저 페이징을 위해 수를 센다.
	private static final String SELECT_COUNT = "select count(*) from employee";
	// ページングに従ってユーザーとそのユーザーの引退日を取得する。
	// 페이징에 따라서 유저와 그 유저의 퇴직일을 취득한다.
	// ユーザーを何番から何番まで持って来るか数字が必要。
	// 유저를 몇번부터 몇번까지 가져올 지 숫자가 필요하다.
	private static final String SELECT_LIST = "select b.*, c.*, d.retirement_date from (select rownum as rnum, a.* from (select * from employee order by user_id desc) a where rownum <= ?) b, user_info c, retirement d where rnum >= ? and b.user_id = c.user_id and b.emp_id = d.emp_id(+) order by b.user_id desc";
	// ページングとキーワードに従ってキーワードを名前に含めているユーザーとそのユーザーの引退日を取得する。
	// 페이징과 키워드에 따라서 키워드를 이름에 포함하고 있는 유저와 그 유저의 퇴직일을 취득한다.
	//　ユーザーを何番から何番まで持って来るか数字と名前検索に使うキーワードが必要。
	// 유저를 몇번부터 몇번까지 가져올 지 숫자가 필요하다.
	private static final String SELECT_LIST_BY_KEYWORD = "select u.*, e.*, r.retirement_date from (select rownum as rnum, a.* from (select * from user_info where user_name LIKE ? order by user_id desc) a where rownum <= ?) u, employee e, retirement r where rnum >= ? and u.user_id = e.user_id and e.emp_id = r.emp_id(+) order by u.user_id desc";
	// ユーザー一人照会。
	// 유저 개인 조회
	// 照会するユーザーのIDが必要。
	// 조회할 유저의 ID가 필요하다.
	private static final String SELECT_EMPLOYEE_INFO = "select e.*, u.*, s.total_salary, r.retirement_date from employee e, user_info u, salary_management s, retirement r where e.user_id=? and e.user_id=u.user_id and e.emp_id=s.emp_id(+) and e.emp_id=r.emp_id(+)";
	//　ユーザー情報修正。
	// 유저 정보 수정
	// 所属、職位、入社日、　情報を修正するユーザーのIDと
	// 소속, 직위, 입사일, 정보를 수정할 유저의 ID가 필요하다.
	private static final String UPDATE_EMPLOYEE = "update employee e set e.dept = ?, e.position=?, e.hire_date=?, financial_company=?, account_number=? where e.user_id=?";
	
	public void createEmployee(Connection conn, Employee employee) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_EMPLOYEE)){
			pstmt.setLong(1, employee.getUserId());
			pstmt.setLong(2, employee.getUserId());
			pstmt.setString(3, employee.getDept());
			pstmt.setString(4, employee.getPosition());
			pstmt.setTimestamp(5, new Timestamp(employee.getHiredAt().getTime()));
			pstmt.setString(6, employee.getFinancialCo());
			pstmt.setString(7, employee.getAccountNum());
			pstmt.executeUpdate();
		}
	}
	
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_COUNT);
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<EmployeeWithUserInfoAndRetiredDate> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LIST);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			List<EmployeeWithUserInfoAndRetiredDate> result = new ArrayList<>();
			while(rs.next()){
				result.add(convertEmployeeWithUserInfo(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<EmployeeWithUserInfoAndRetiredDate> selectByKeyword(Connection conn, int firstRow, int endRow,
			String keyword) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LIST_BY_KEYWORD);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, firstRow);
			rs = pstmt.executeQuery();
			List<EmployeeWithUserInfoAndRetiredDate> result = new ArrayList<>();
			while(rs.next()){
				result.add(convertEmployeeWithUserInfo(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public EmployeeWithUserInfoAndTotalSalaryAndRetireDate findById(Connection conn, Long userId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_EMPLOYEE_INFO);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			EmployeeWithUserInfoAndTotalSalaryAndRetireDate result = null;
			if(rs.next()) {
				result = convertEmployeeWithUserInfoAndTotalSalaryAndRetireDate(rs);
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void updateEmployee(Connection conn, Employee employee) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_EMPLOYEE)){
			pstmt.setString(1, employee.getDept());
			pstmt.setString(2, employee.getPosition());
			pstmt.setTimestamp(3, new Timestamp(employee.getHiredAt().getTime()));
			pstmt.setString(4, employee.getFinancialCo());
			pstmt.setString(5, employee.getAccountNum());
			pstmt.setLong(6, employee.getUserId());
			pstmt.executeUpdate();
		}
	}
	
	private EmployeeWithUserInfoAndTotalSalaryAndRetireDate convertEmployeeWithUserInfoAndTotalSalaryAndRetireDate(ResultSet rs) throws SQLException{
		return new EmployeeWithUserInfoAndTotalSalaryAndRetireDate(
					rs.getLong("user_id"),
					rs.getString("user_name"),
					rs.getString("email"),
					rs.getString("phone_number"),
					rs.getString("dept"),
					rs.getString("position"),
					rs.getDate("hire_date"),
					rs.getString("financial_company"),
					rs.getString("account_number"),
					rs.getLong("total_salary"),
					rs.getString("retirement_date")
				);
	}

	private EmployeeWithUserInfoAndRetiredDate convertEmployeeWithUserInfo(ResultSet rs) throws SQLException {
		return new EmployeeWithUserInfoAndRetiredDate(
				new Employee(
						rs.getLong("emp_id"),
						rs.getLong("user_id"),
						rs.getString("dept"),
						rs.getString("position"),
						rs.getDate("hire_date"),
						rs.getString("financial_company"),
						rs.getString("account_number")),
				new UserInfo(
						rs.getLong("user_id"),
						rs.getString("user_name"),
						rs.getString("email"),
						rs.getString("phone_number"),
						rs.getDate("created_at")),
				rs.getDate("retirement_date") == null ? null : rs.getDate("retirement_date")
				);
	}


}
