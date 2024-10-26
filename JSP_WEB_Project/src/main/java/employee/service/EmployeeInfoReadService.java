package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//社員一人の情報照会のサービスロジックを提供するクラス。
public class EmployeeInfoReadService {

	private final EmployeeDao employeeDao = new EmployeeDao();
	
	public EmployeeWithUserInfoAndTotalSalaryAndRetireDate getEmployee(Long userId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			EmployeeWithUserInfoAndTotalSalaryAndRetireDate result = employeeDao.findById(conn, userId);
			
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
