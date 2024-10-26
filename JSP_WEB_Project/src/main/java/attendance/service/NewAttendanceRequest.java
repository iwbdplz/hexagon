package attendance.service;

import java.util.Date;
import java.util.Map;

//新規勤怠記録のデーターを入れるためのクラス
public class NewAttendanceRequest {

	private String empId; //社員ID
	private String attendDate; //勤怠記録日付
	private String status; //　勤怠状態
	
	
	
	public NewAttendanceRequest(String empId, String attendDate, String status) {
		this.empId = empId;
		this.attendDate = attendDate;
		this.status = status;
	
	}


	public String getEmpId() {
		return empId;
	}

	
	public String getAttendDate() {
		return attendDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	//入力した値にエラーがあるのかを確認するメソッド
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, empId, "empId");
		checkEmpty(errors, attendDate, "attendDate");
		checkEmpty(errors, status, "status");
		
	}
	
	//入力欄は空白なのかを確認するためのメソッド
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}

	
}
