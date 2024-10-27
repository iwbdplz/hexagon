package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import model.Employee;

public class EmployeeDAO {
	  public List<Employee> getAllEmployees(int currentPage, int pageSize) {
	        List<Employee> employeesList = new ArrayList<>();
	        int start = (currentPage - 1) * pageSize; // 시작 인덱스 계산 スタートインデックス計算
	        String sql = "SELECT * FROM (SELECT e.*, ROWNUM rnum FROM employee e WHERE ROWNUM <= ?) WHERE rnum > ?";

	        try (Connection conn = ConnectionProvider.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, currentPage * pageSize); // 페이지 수에 맞게 설정 ページ数に合わせて設定します
	            pstmt.setInt(2, start);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                Employee employee = new Employee();
	                employee.setEmpId(rs.getInt("emp_id"));
	                employee.setUserId(rs.getInt("user_id"));
	                employee.setDept(rs.getString("dept"));
	                employee.setPosition(rs.getString("position"));
	                employee.setHireDate(rs.getDate("hire_date"));
	                employee.setFinancialCompany(rs.getString("financial_company"));
	                employee.setAccountNumber(rs.getString("account_number"));
	                employeesList.add(employee);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	       System.out.println(employeesList);
	        return employeesList;
	    }

	    public int getTotalEmployees() {
	        int total = 0;
	        String sql = "SELECT COUNT(*) FROM employee"; // 전체 사원 수 쿼리 全社員数クエリ
	        try (Connection conn = ConnectionProvider.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                total = rs.getInt(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println(total);
	        return total;
	    }
}
