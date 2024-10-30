package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//社員一人の情報照会のサービスロジックを提供するクラス。
// 사원 개인의 정보를 조회하는 서비스 로직을 제공하는 클래스
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
