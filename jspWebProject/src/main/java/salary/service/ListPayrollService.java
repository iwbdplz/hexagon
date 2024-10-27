package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProvider;
import salary.dao.PayrollDao;
import salary.dto.Payroll;

public class ListPayrollService {
	
	PayrollDao payrollDao = new PayrollDao();
	
	public List<Payroll> getList(String year){
		List<Payroll> payrollList = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			payrollList = payrollDao.select(conn, year);
			return payrollList;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
