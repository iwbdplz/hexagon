package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import salary.dto.EmpSalary;

public class EmpSalaryDao {
	
	public List<EmpSalary> select(Connection conn,String year_month, int firstRow, int endRow)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT *"
					+ " FROM ("
					+ " SELECT emp.emp_id, userinfo.user_name, emp.dept, sama.salary_date, sama.total_salary, ded.deduction_amount,"
					+ " ROW_NUMBER() OVER (ORDER BY sama.salary_date DESC) AS row_num"
					+ " FROM employee emp"
					+ " JOIN salary_management sama ON emp.emp_id = sama.emp_id"
					+ " JOIN deductions ded ON ded.emp_id = emp.emp_id AND TRUNC(ded.deduction_date, 'MM') = TRUNC(sama.salary_date, 'MM')"
					+ " JOIN user_info userinfo ON userinfo.user_id = emp.user_id"
					+ " WHERE TRUNC(sama.salary_date, 'MM') = TO_DATE(?, 'YYYY/MM'))"
					+ " WHERE row_num BETWEEN ? AND ?");
			/*
			 * pstmt = conn.prepareStatement( "SELECT *" + " FROM (" +
			 * " SELECT emp.emp_id, userinfo.user_name, emp.dept, sama.salary_date, sama.total_salary, ded.deduction_amount,"
			 * + " ROW_NUMBER() OVER (ORDER BY sama.salary_date DESC) AS row_num" +
			 * " FROM employee emp" +
			 * " JOIN salary_management sama ON emp.emp_id = sama.emp_id" +
			 * " JOIN deductions ded ON ded.emp_id = emp.emp_id" +
			 * " JOIN user_info userinfo ON userinfo.user_id = emp.user_id" +
			 * " WHERE TRUNC(salary_date, 'MM') = TO_DATE(?, 'YYYY/MM'))" +
			 * "	WHERE row_num BETWEEN ? AND ?");
			 */			pstmt.setString(1, year_month);
			pstmt.setInt(2, firstRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			List<EmpSalary> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertEmpSal(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private EmpSalary convertEmpSal(ResultSet rs) throws SQLException{
		return new EmpSalary(
				rs.getInt("emp_id"),
				rs.getString("user_name"),
				rs.getString("dept"),
				rs.getDate("salary_date"),
				rs.getInt("total_salary"),
				rs.getInt("deduction_amount"));
	}
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public int selectCount(Connection conn, String year_month) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from salary_management"
					+ " WHERE TRUNC(salary_date, 'MM') = TO_DATE(?, 'YYYY/MM')");
			pstmt.setString(1, year_month);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
				}
			}catch(SQLException e){	
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				}
		return 0;
	}
	
	
}
