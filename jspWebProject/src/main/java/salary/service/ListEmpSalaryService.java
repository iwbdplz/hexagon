package salary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProvider;
import salary.dao.CountPayDao;
import salary.dao.EmpSalaryDao;
import salary.dto.CountPay;
import salary.dto.EmpSalary;
import salary.dto.EmpSalaryPage;

public class ListEmpSalaryService {
	private int size = 10;
	private EmpSalaryDao empSalaryDao = new EmpSalaryDao() ;
	private CountPayDao countPayDao = new CountPayDao();

	public EmpSalaryPage getListPage(int pageNum, String year, String month) {
		int firstRow = 0;
		int endRow = 0;
		List<EmpSalary> ListEmpSal = null;
		CountPay countPay = null;
		String year_month;

		if(Integer.parseInt(month) < 10) {year_month = year+"/0"+month;
		}else {year_month = year+"/"+month;}
		
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = empSalaryDao.selectCount(conn, year_month);
			if(total >0){
				firstRow = (pageNum -1) * size +1;
				endRow = firstRow + size -1;
				countPay  = countPayDao.payTotalResult(conn,year_month);
				ListEmpSal = empSalaryDao.select(conn, year_month ,firstRow, endRow);
			}
			return new EmpSalaryPage(total,pageNum,size, ListEmpSal,countPay);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
