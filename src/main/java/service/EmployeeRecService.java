package service;

import dao.EmployeeRecordDao;
import model.EmployeeRecord;

public class EmployeeRecService {
	private EmployeeRecordDao employeeRecordDAO = new EmployeeRecordDao();
	
    // 사원 인사 정보를 가져오는 메소드
    // サンプル社員情報を取得するメソッド
    public EmployeeRecord getEmployeeRecord(int empId) {
        return employeeRecordDAO.getEmployeeRecord(empId);
    }

    // 사원 인사 정보를 업데이트하는 메소드
    // サンプル社員情報を更新するメソッド
    public void updateEmployeeRecord(EmployeeRecord record) {
        employeeRecordDAO.updateEmployeeRecord(record);
    }

    // 사원 인사 정보를 삭제하는 메소드
    // サンプル社員情報を削除するメソッド
    public void deleteEmployeeRecord(int empId) {
        employeeRecordDAO.deleteEmployeeRecord(empId);
    }
}
