package employee.service;

import java.util.Date;

import employee.model.Employee;
import userinfo.model.UserInfo;

// 社員管理ページで社員リストに表示する各一人の情報DTO
public class EmployeeWithUserInfoAndRetiredDate{
	private final Employee employee;
	private final UserInfo userInfo;
	private final Date retirementDate;
	
	public EmployeeWithUserInfoAndRetiredDate(Employee employee, UserInfo userInfo, Date retirementDate) {
		this.employee = employee;
		this.userInfo = userInfo;
		this.retirementDate = retirementDate;
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
	
}