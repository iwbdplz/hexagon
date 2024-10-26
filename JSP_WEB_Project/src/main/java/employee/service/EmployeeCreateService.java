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
public class EmployeeCreateService {

	private final UserInfoCreateService userInfoCreateService = new UserInfoCreateService();
	private final EmployeeDao employeeDao = new EmployeeDao();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	// 社員登録の時はユーザーを登録し、その後に社員テーブルに登録する。
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
			conn.commit();
			return userId;
		} catch (SQLException | ParseException e) {
			// 途中にエラーが発生した時はロールバックする。
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
