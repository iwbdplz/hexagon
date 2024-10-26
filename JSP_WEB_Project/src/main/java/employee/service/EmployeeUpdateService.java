package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import employee.dao.EmployeeDao;
import employee.model.Employee;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import userinfo.service.UserInfoUpdateService;

//社員情報修正のサービスロジックを提供するクラス。
public class EmployeeUpdateService {

	private final UserInfoUpdateService userInfoUpdateService = new UserInfoUpdateService();
	private final EmployeeDao employeeDao = new EmployeeDao();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	// 社員情報修正は使用者管理テーブルと社員テーブルの二つのテーブルを全部修正する。
	public void updateEmployee(UpdateUserRequest updateUserRequest) throws NumberFormatException, ParseException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userInfoUpdateService.updateUserInfo(conn, updateUserRequest);
			
			employeeDao.updateEmployee(
					conn,
					new Employee(
							Long.valueOf(updateUserRequest.getUserId()),
							updateUserRequest.getDept(),
							updateUserRequest.getPosition(),
							formatter.parse(updateUserRequest.getHiredAt()),
							updateUserRequest.getFinancialCo(),
							updateUserRequest.getAccountNum()
							)
					);
			// ユーザー修正と社員修正を全部完了した時コミットする。
			conn.commit();
		} catch (SQLException e) {
			// 途中にエラーが発生した時はロールバックする。
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
