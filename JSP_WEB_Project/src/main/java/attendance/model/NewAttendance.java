package attendance.model;

import java.util.Date;

public class NewAttendance {

	private String empId; //　社員ID
	private Date attendDate; //　勤怠記録日付
	private String status; //　勤怠状態

	// 変数生成者
	public NewAttendance(String empId, Date attendDate, String status) {
		this.empId = empId; 
		this.attendDate = attendDate; 
		this.status = status; 
		
	}
	
	//社員IDのGETTER
	public String getEmpId() {
		return empId;
	}
	
	//勤怠記録日付のGETTER
	public Date getAttendDate() {
		return attendDate;
	}
	
	//勤怠状態のGETTER
	public String getStatus() {
		return status;
	}

}
