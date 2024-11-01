package attendance.model;

import java.sql.Date;

public class LeaveAttendanceSettings {

	private String empId; //　社員ID,  사원ID
	private String username; //　使用者氏名, 사용자 이
	private String dept; //　部署, 부
	private String position; //　役職, 직
	private Date startDate; //　休暇開始日, 휴가시작일 
	private Date endDate; //　休暇終了日, 휴가종료일 
	private int leaveDays; //　休暇残日数, 휴가잔여일수 

	// 変数生成者
	// 매개변수를 가지는 생성자 
	public LeaveAttendanceSettings(String empId, String username, String dept, String position, Date startDate,
			Date endDate, int leaveDays) {

		this.empId = empId;
		this.username = username; 
		this.dept = dept; 
		this.position = position; 
		this.startDate = startDate; 
		this.endDate = endDate; 
		this.leaveDays = leaveDays;
	}

	// 社員IDのGETTER
	public String getEmpId() {
		return empId;
	}

	// 使用者名前のGETTER
	public String getUsername() {
		return username;
	}

	// 部署のGETTER
	public String getDept() {
		return dept;
	}

	// 役職のGETTER
	public String getPosition() {
		return position;
	}

	// 休暇開始日のGETTER
	public Date getStartDate() {
		return startDate;
	}

	// 休暇終了日のGETTER
	public Date getEndDate() {
		return endDate;
	}

	// 休暇残日数のGETTER
	public int getLeaveDays() {
		return leaveDays;
	}

}
