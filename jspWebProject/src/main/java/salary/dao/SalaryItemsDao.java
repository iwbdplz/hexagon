package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class SalaryItemsDao {
	public int delete(Connection conn, int emp_id) throws SQLException{
		try (PreparedStatement pstmt = conn.prepareStatement(
				"delete from salary_items"
				+ " WHERE salary_item_id=? ")){
			pstmt.setInt(1, emp_id);
			return pstmt.executeUpdate();
		}
	}
	
	public void update(Connection conn, int emp_id, int salaryItem) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update salary_items "
					+ " set amount = ? where salary_item_id = ? ");
			pstmt.setInt(1, salaryItem);
			pstmt.setInt(2, emp_id);
			pstmt.executeQuery();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, int emp_id) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into salary_items "
					+ " values(?,'나머지수당',0)");
			pstmt.setInt(1,emp_id);
			pstmt.executeQuery();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectAmount(Connection conn, int emp_id)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int amount = 0;
		try {
			pstmt = conn.prepareStatement("select amount from salary_items where salary_item_id = ?");
			pstmt.setInt(1,emp_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 amount = rs.getInt("amount");
			}
			return amount;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
