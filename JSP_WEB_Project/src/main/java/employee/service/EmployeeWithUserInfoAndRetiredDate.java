package employee.service;

import java.util.Date;

import employee.model.Employee;
import userinfo.model.UserInfo;

// 社員管理ページで社員リストに表示する各一人の情報DTO
// 사원관리 페이지에서 사원 리스트에 표시할 각 한명의 정보 DTO
public class EmployeeWithUserInfoAndRetiredDate{
	private final Employee employee;
	private final UserInfo userInfo;
	private final Date retirementDate;
	private final String status;
	
	public EmployeeWithUserInfoAndRetiredDate(Employee employee, UserInfo userInfo, Date retirementDate) {
		this.employee = employee;
		this.userInfo = userInfo;
		this.retirementDate = retirementDate;
		this.status = retirementDate == null ? "在職" : "退職";
	}

	public Employee getEmployee() {
		return employee;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}
	
	public String getStatus() {
		return status;
	}
	
}