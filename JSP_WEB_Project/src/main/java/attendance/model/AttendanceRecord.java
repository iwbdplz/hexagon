package attendance.model;

import java.sql.Date;

public class AttendanceRecord {

	
	private String empId; //　社員ID
	private String username; //　使用者氏名
	private String dept; //　部署
	private String position; //　役職
	private Date attendDate; //　勤怠記録日付
	private String status; //　勤怠状態
	
	
	//変数生成者
	public AttendanceRecord(String empId, String username, String dept, String position, Date attendDate, String status) {
		this.empId = empId; 
		this.username = username; 
		this.dept = dept; 
		this.position = position; 
		this.attendDate = attendDate;
		this.status = status;
	
	} 

	//社員IDのGETTER
	public String getEmpId() {
		return empId;
	} 

	//使用者名前のGETTER
	public String getUsername() {
		return username;
	} 

	//部署のGETTER
	public String getDept() {
		return dept;
	} 

	 //役職のGETTER
	public String getPosition() {
		return position;
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
