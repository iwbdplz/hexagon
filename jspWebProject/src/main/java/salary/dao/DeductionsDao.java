package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import salary.model.EmpAccountInfo;

public class DeductionsDao {
	public int delete(Connection conn, int emp_id, String deduction_date) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"delete from deductions" 
				+ " WHERE emp_id = ? AND "
				+ " TRUNC(deduction_date) = TO_DATE(?, 'YYYY/MM/DD')")) {
			pstmt.setInt(1, emp_id);
			pstmt.setString(2, deduction_date);
			return pstmt.executeUpdate();
		}
	}

	public void update(Connection conn, int emp_id, String salary_date, int deductionAmount) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update deductions " 
					+ " set deduction_amount =?"
					+ " where emp_id = ? and"
					+ " TRUNC(deduction_date) = TO_DATE(?, 'YYYY/MM/DD')");
			pstmt.setInt(1, deductionAmount);
			pstmt.setInt(2, emp_id);
			pstmt.setString(3, salary_date);
			//pstmt.setString(3, salaryDate); 날짜를 사용한 정확한 검색 필요시
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, int emp_id) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into deductions(deduction_id, emp_id,deduction_amount, deduction_date) "
					+ " values(seq_deductions.nextval,?,0,sysdate)");
			pstmt.setInt(1,emp_id);
			pstmt.executeQuery();
		}finally {}
		JdbcUtil.close(pstmt);
	}
	
	public int selectByAmount(Connection conn, int emp_id, String salary_date) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement("select deduction_amount from deductions "
					+ "where emp_id = ? and"
					+ " TRUNC(deduction_date) = TO_DATE(?, 'YYYY/MM/DD')");
			pstmt.setInt(1, emp_id);
			pstmt.setString(2, salary_date);
			rs = pstmt.executeQuery();
			if(rs.next()) {result = rs.getInt("deduction_amount");}
			return result;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
	}
}
