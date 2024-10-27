package salary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import salary.dto.EmpSalary;
import salary.dto.Payroll;

public class PayrollDao {
	
	public List<Payroll> select(Connection conn,String year)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT "
					+ " TO_CHAR(sm.salary_date, 'MM') AS month,"
					+ " COUNT(*) AS salary_count,"
					+ " SUM(sm.total_salary) AS totalsal,"
					+ " SUM(ded.deduction_amount) AS ded_amount"
					+ " FROM salary_management sm"
					+ " JOIN deductions ded ON sm.emp_id = ded.emp_id"
					+ " WHERE TO_CHAR(sm.salary_date, 'YYYY') = ? "
					+ " GROUP BY TO_CHAR(sm.salary_date, 'MM')"
					+ " ORDER BY TO_CHAR(sm.salary_date, 'MM')");
			
			pstmt.setString(1, year);
			rs = pstmt.executeQuery();
			List<Payroll> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertEmpSal(rs, year));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Payroll convertEmpSal(ResultSet rs, String year) throws SQLException{
		return new Payroll(
				year,
				rs.getString("month"),
				rs.getInt("salary_count"),
				rs.getInt("totalSal"),
				rs.getInt("ded_amount"));
	}
}
