package salary.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import jdbc.JdbcUtil;
import salary.dao.DeductionsDao;
import salary.dao.EmpSalaryDao;
import salary.dao.SalaryItemsDao;
import salary.dao.SalaryManagementDao;
import salary.model.SalaryManagement;

public class SelectSalaryDelete {
	private SalaryManagementDao salaryMDao = new SalaryManagementDao();
	private SalaryItemsDao salaryItemsDao = new SalaryItemsDao();
	private DeductionsDao deductionsDao = new DeductionsDao();
	
	public void delete(int emp_id, String salary_date) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			SalaryManagement salaryManagement = salaryMDao.selectById(conn, emp_id, salary_date);
			if(salaryManagement == null) {
				throw new SalaryMaganementNotFoundException();
			}
			salaryItemsDao.delete(conn, emp_id);
			deductionsDao.delete(conn, emp_id, salary_date);
			salaryMDao.delete(conn, emp_id, salary_date);
			conn.commit();
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(Exception e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

}
