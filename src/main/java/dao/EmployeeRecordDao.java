package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import model.EmployeeRecord;

public class EmployeeRecordDao {
	  // 사원 인사 정보를 조회하는 메소드
	// サンプル社員情報を取得するメソッド
	public EmployeeRecord getEmployeeRecord(int empId) {
	    EmployeeRecord record = null;
	    String sql = "SELECT * FROM employee WHERE emp_id = ?"; 
	
	    try (Connection conn = ConnectionProvider.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, empId); // empId 설정
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            record = new EmployeeRecord();
	            record.setEmpId(rs.getInt("emp_id"));
                record.setUserId(rs.getInt("user_id"));
                record.setDepartment(rs.getString("dept"));
                record.setPosition(rs.getString("position"));
                record.setHireDate(rs.getDate("hire_date"));
                record.setFinancialCompany(rs.getString("financial_company"));
                record.setAccountNumber(rs.getString("account_number"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    System.out.println(record);
	    return record; // 사원 인사 정보 반환 社員人事情報の返却です
	}
	
	// 사원 인사 정보를 업데이트하는 메소드
	// サンプル社員情報を更新するメソッド
	public void updateEmployeeRecord(EmployeeRecord record) {
	    String sql = "UPDATE employee SET user_id=?, dept=?, position=?, hire_date=?, financial_company=?, account_number=? WHERE emp_id=?"; // SQL 쿼리
	
	    try (Connection conn = ConnectionProvider.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, record.getUserId());
	        pstmt.setString(2, record.getDepartment());
	        pstmt.setString(3, record.getPosition());
	        pstmt.setDate(4, new java.sql.Date(record.getHireDate().getTime()));
	        pstmt.setString(5, record.getFinancialCompany());
	        pstmt.setString(6, record.getAccountNumber());
	        pstmt.setInt(7, record.getEmpId());
	        pstmt.executeUpdate(); 
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	}
	
	// 사원 인사 정보를 삭제하는 메소드
	// サンプル社員情報を削除するメソッド
	public void deleteEmployeeRecord(int empId) {
	    String sql = "DELETE FROM employee WHERE emp_id=?"; 
	
	    try (Connection conn = ConnectionProvider.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, empId); // empId 설정 empId設定
	        pstmt.executeUpdate(); 
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	}
}