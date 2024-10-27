package salary.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import jdbc.JdbcUtil;
import salary.dao.DeductionsDao;
import salary.dao.EmpAccountInfoDao;
import salary.dao.SalaryItemsDao;
import salary.dao.SalaryManagementDao;
import salary.model.EmpAccountInfo;

public class InsertSalaryService {

	private EmpAccountInfoDao accountInfoDao = new EmpAccountInfoDao();
	private SalaryManagementDao salaryMDao = new SalaryManagementDao();
	private DeductionsDao deductionsDao = new DeductionsDao();
	private SalaryItemsDao salaryItemsDao = new SalaryItemsDao();
	private EmpAccountInfo accountInfo = null;
	
	public void insertSalary(int emp_id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			accountInfo = accountInfoDao.selectEmpAccountInfo(conn, emp_id);
			salaryMDao.insert(conn, emp_id, accountInfo);
			deductionsDao.insert(conn, emp_id);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}

	}
}
