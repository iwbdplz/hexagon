package salary.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import jdbc.JdbcUtil;
import salary.dao.DeductionsDao;
import salary.dao.SalaryItemsDao;
import salary.dao.SalaryManagementDao;
import salary.dto.DeductionItems;
import salary.dto.PaymentItems;
import salary.dto.UpdateViewData;


public class UpdateSalaryService {
	
	private SalaryManagementDao salaryMDao = new SalaryManagementDao();
	private DeductionsDao deductionsDao = new DeductionsDao();
	private SalaryItemsDao salaryItemSDao = new SalaryItemsDao();
	
	
	public void updateSalary(int emp_id,String salary_date, int totalSalary,int deductionAmount, int salaryItems){
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			salaryMDao.update(conn,emp_id, salary_date, totalSalary);
			deductionsDao.update(conn, emp_id, salary_date, deductionAmount);
			salaryItemSDao.update(conn, emp_id, salaryItems);
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public UpdateViewData loadSalaryData(int emp_id, String salary_date) throws SQLException{
		UpdateViewData viewData = null;
		try(Connection conn = ConnectionProvider.getConnection()){
			int total_Salary = salaryMDao.loadTotalSalary(conn, emp_id, salary_date);
			int salary_item_amount = salaryItemSDao.selectAmount(conn, emp_id);
			int deduction_amount = deductionsDao.selectByAmount(conn, emp_id,salary_date);
			
			PaymentItems payItem = new PaymentItems(total_Salary, salary_item_amount);
			DeductionItems dedItem = new DeductionItems(total_Salary, salary_item_amount, deduction_amount);
			viewData = new UpdateViewData(emp_id,salary_date, payItem, dedItem);
			return viewData;
		}
	}

}
