package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import employee.dao.EmployeeDao;
import employee.model.Employee;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import userinfo.service.UserInfoCreateService;

//　社員登録のサービスロジックを提供するクラス。
// 사원등록의 서비스 로직을 제공하는 클래스
public class EmployeeCreateService {

	private final UserInfoCreateService userInfoCreateService = new UserInfoCreateService();
	private final EmployeeDao employeeDao = new EmployeeDao();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	// 社員登録の時はユーザーを登録し、その後に社員テーブルに登録する。
	// 사원등록을 할 때는 유저를 등록하고, 그 후에 사원 테이블에 등록한다.
	public Long createEmployee(CreateUserRequest createUserRequest) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Long userId = userInfoCreateService.createUserInfo(conn, createUserRequest);
			
			employeeDao.createEmployee(
					conn,
					new Employee(
							userId,
							createUserRequest.getDept(),
							createUserRequest.getPosition(),
							formatter.parse(createUserRequest.getHiredAt()),
							createUserRequest.getFinancialCo(),
							createUserRequest.getAccountNum()
							)
					);
			
			// ユーザー登録と社員登録を全部完了した時コミットする。
			// 유저 등록과 사원 등록을 전부 완료했을 때 커밋 한다.
			conn.commit();
			return userId;
		} catch (SQLException | ParseException e) {
			// 途中にエラーが発生した時はロールバックする。
			// 도중에 에러가 발생했을 때는 롤백한다.
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
