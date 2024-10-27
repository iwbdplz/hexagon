package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import salary.model.EmpAccountInfo;

public class EmpAccountInfoDao {
	
	public EmpAccountInfo selectEmpAccountInfo(Connection conn, int emp_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select emp_id, financial_company, account_number"
					+ " from employee where emp_id = ?");
			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();
			EmpAccountInfo accountInfo = null;
			if(rs.next()) {
				accountInfo = new EmpAccountInfo(
						rs.getInt("emp_id"),
						rs.getString("financial_company"),
						rs.getString("account_number")
						);
			}
			return accountInfo;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	

}
