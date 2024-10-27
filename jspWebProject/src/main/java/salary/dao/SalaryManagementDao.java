package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import salary.model.EmpAccountInfo;
import salary.model.SalaryManagement;

public class SalaryManagementDao {
	
	public int delete(Connection conn, int emp_id, String salary_date) throws SQLException{
		try (PreparedStatement pstmt = conn.prepareStatement(
				"delete from salary_management"
				+ " WHERE emp_id=? AND "
				+ " TRUNC(salary_date) = TO_DATE(?, 'YYYY/MM/DD')")){
			pstmt.setInt(1, emp_id);
			pstmt.setString(2, salary_date);
			return pstmt.executeUpdate();
		}
	}
	
	public SalaryManagement selectById(Connection conn, int emp_id, String salary_date) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from salary_management where emp_id = ? AND"
					+ " TRUNC(salary_date) = TO_DATE(?, 'YYYY/MM/DD')");
			pstmt.setInt(1, emp_id);
			pstmt.setString(2, salary_date);
			rs = pstmt.executeQuery();
			SalaryManagement salarymanagement= null;
			if(rs.next()) {
				salarymanagement = 
						new SalaryManagement(
								rs.getInt("salary_id"),
								rs.getInt("emp_id"),
								rs.getDate("salary_date"),
								rs.getInt("total_salary"),
								rs.getString("financial_company"),
								rs.getString("account_number"));
			}
			return salarymanagement;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public void update(Connection conn,int emp_id, String salary_date,int totalSalary) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update salary_management "
					+ " set total_salary =?"
					+ " where emp_id = ? and"
					+ " TRUNC(salary_date) = TO_DATE(?, 'YYYY/MM/DD')");
			pstmt.setInt(1, totalSalary);
			pstmt.setInt(2, emp_id);
			pstmt.setString(3, salary_date);			
			//pstmt.setString(3, salaryDate); 날짜를 사용한 정확한 검색 필요시
			//+ " and TRUNC(salary_date) = TO_DATE(?, 'YYYY/MM/DD') 쿼리추가
			pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void insert(Connection conn, int emp_id, EmpAccountInfo accountInfo) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into salary_management(salary_id, emp_id, salary_date, total_salary, financial_company, account_number) "
					+ " values(seq_salarymanagement.nextval,?,sysdate,0,?,?)");
			pstmt.setInt(1,emp_id);
			pstmt.setString(2,accountInfo.getFinancial_Company());			
			pstmt.setString(3,accountInfo.getAccount_Number());	
			pstmt.executeQuery();
		}finally {
			JdbcUtil.close(pstmt);			
		}
	}
	
	public int loadTotalSalary(Connection conn,int emp_id, String salary_date) throws SQLException{
		int totalSalary = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select total_salary from salary_management "
					+ "where emp_id = ? and"
					+ " TRUNC(salary_date) = TO_DATE(?, 'YYYY/MM/DD')");
			pstmt.setInt(1, emp_id);
			pstmt.setString(2, salary_date);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalSalary = rs.getInt("total_salary");
			}
			return totalSalary;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
