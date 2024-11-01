package attendance.service;

import java.util.Map;


//新規休暇記録のデーターを入れるためのクラス
//신규휴가기록 데이터를 넣기 위한 클래스 
public class NewLeaveAttendanceRequest {

	
	private String empId; //社員ID, 사원ID
	private String leaveDays; //休暇残日数, 휴가잔여일수 
	private String startDate; // 休暇開始日, 휴가시작일 
	private String endDate; // 休暇終了日, 휴가종료일 
	
	
	public NewLeaveAttendanceRequest(String empId, String leaveDays, String startDate, String endDate) {
		
		this.empId = empId;
		this.leaveDays = leaveDays;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public String getEmpId() {
		return empId;
	}
	
	public String getLeaveDays() {
		return leaveDays;
	}


	public String getStartDate() {
		return startDate;
	}


	public String getEndDate() {
		return endDate;
	}
	
	//入力した値にエラーがあるのかを確認するメソッド
	//입력한 값에 에러가 있는지를 확인하는 메소드 
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, empId, "empId");
		checkEmpty(errors, leaveDays, "leaveDays");
		checkEmpty(errors, startDate, "startDate");
		checkEmpty(errors, endDate, "endDate");
		
	}
	
	//入力欄は空白なのかを確認するためのメソッド
	//입력란이 공백인지를 확인하는 메소드 
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}

	
	
	
}
