package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import model.EmployeeCard;

//사원 정보를 데이터베이스에서 처리하는 클래스 社員情報をデータベースで処理するクラス
public class EmployeeCardDAO {
	  // 사원 검색 메서드 社員検索メソッド
	public List<EmployeeCard> searchEmployees(String dept, String position,String employeeType, String keyword) throws Exception{
		List<EmployeeCard> employees =new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT e.emp_id, u.user_name, e.dept, e.position, e.hire_date,(SELECT r.retirement_date FROM retirement r WHERE r.emp_id = e.emp_id) AS retirement_date, \n"
				+ "CASE WHEN (SELECT r.retirement_date FROM retirement r WHERE r.emp_id = e.emp_id) IS NULL THEN '재직' ELSE '퇴직' END AS employment_status \n"
				+ "FROM employee e JOIN user_info u ON e.user_id = u.user_id WHERE 1=1;");
		if (dept != null &&  !dept.isEmpty()) {
			sql.append("AND e.dept");
		}
		if (position != null && !position.isEmpty()) {
			sql.append("AND e.position = ? ");
			
		}
		if (employeeType !=null && !employeeType.isEmpty()) {
			if (employeeType.equals("정규")) {
			sql.append("AND (SELECT r.retirement_date FROM retirement r WHERE r.emp_id = e.emp_id) IS NULL");
			} else if (employeeType.equals("일용")) {
				sql.append("AND (SELECT r.retirement_date FROM retirement r WHERE r.emp_id = e.emp_id) IS NOT NULL");
			}
		}
		if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (e.emp_id = ? OR u.user_name LIKE ? OR e.position LIKE ?)");
        }
		try (Connection conn = ConnectionProvider.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(sql.toString());	){
			int idx =1;
			
			if (dept != null && !dept.isEmpty()) {
				pstmt.setString(idx++, dept);
			}
			if (position != null && !position.isEmpty()) {
				pstmt.setString(idx++, keyword);
			}
			 if (employeeType != null && !employeeType.isEmpty()) {
	                // 이미 조건을 추가했으므로 아무 것도 설정하지 않음
	            }
			 if (keyword != null && keyword.isEmpty()) {
				  pstmt.setString(idx++, keyword); // 사원번호
				  pstmt.setString(idx++, "%" + keyword + "%"); // 이름 검색
	              pstmt.setString(idx++, "%" + keyword + "%"); // 직위 검색
			}
			 ResultSet rs = pstmt.executeQuery();
			 while (rs.next()) {
				employees.add(new EmployeeCard(
						rs.getInt("emp_id"),
						rs.getString("user_name"),
						rs.getString("dept"),
						rs.getString("position"),
						rs.getDate("hire_date"),
						rs.getDate("retirement_date"),
						 rs.getString("employment_status") // 상태 (재직/퇴직)
						));
				
			}
			 
		} 
		return employees;
		
	}
}
