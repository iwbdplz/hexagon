package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import salary.dto.CountPay;

public class CountPayDao {
	
	public CountPay payTotalResult(Connection conn, String year_month) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CountPay countPay = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select count(*) as totalcount, "
					+ " NVL(sum(sm.total_salary), 0) as totalsal, "
					+ " NVL(sum(deductions.deduction_amount), 0) as totalda"
					+ " from salary_management sm"
					+ " JOIN deductions ON deductions.emp_id = sm.emp_id"
					+ " where TRUNC(salary_date, 'MM') = TO_DATE(?, 'YYYY/MM')");
			pstmt.setString(1, year_month);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				countPay = new CountPay(rs.getInt("totalcount"), 
				                        rs.getInt("totalsal"), 
				                        rs.getInt("totalda"));
			}
			return countPay;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	


}
