package attendance.model;

import java.util.Date;

public class NewLeaveAttendance {

	private String empId; //　社員ID, 사원ID 
	private String leaveDays; //　休暇残日数, 휴가잔여일수 
	private Date startDate; //　休暇開始日, 휴가시작일 
	private Date endDate; //　休暇終了日, 휴가종료일 
	
	
	//変数生成者
	//매개변수를 가지는 생성자 
	public NewLeaveAttendance(String empId, String leaveDays, Date startDate, Date endDate) {
		
		this.empId = empId; 
		this.leaveDays = leaveDays; 
		this.startDate = startDate; 
		this.endDate = endDate; 
		}
	
	//社員IDのGETTER
	public String getEmpId() {
		return empId;
	}
	
	//休暇残日数のGETTER
	public String getLeaveDays() {
		return leaveDays;
	}
	
	//休暇開始日のGETTER
	public Date getStartDate() {
		return startDate;
	}
	
	//休暇終了日のGETTER
	public Date getEndDate() {
		return endDate;
	}
	
	
	
	
}
