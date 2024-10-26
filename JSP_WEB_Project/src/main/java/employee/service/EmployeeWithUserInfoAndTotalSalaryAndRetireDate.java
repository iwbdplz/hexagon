package employee.service;

import java.util.Date;

// 社員照会ページで表示する社員一人の情報DTO
public class EmployeeWithUserInfoAndTotalSalaryAndRetireDate {

	// user_info
	private Long userId;
	private String username;
	private String email;
	private String phoneNum;
	// employee
	private String dept;
	private String position;
	private Date hiredAt;
	private String financialComapany;
	private String accountNum;
	// salary_management
	private Long totalSalary;
	// retirement
	private String retirementDate;
	
	public EmployeeWithUserInfoAndTotalSalaryAndRetireDate(Long userId, String username, String email, String phoneNum,
			String dept, String position, Date hiredAt, String financialComapany, String accountNum, Long totalSalary,
			String retirementDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phoneNum = phoneNum;
		this.dept = dept;
		this.position = position;
		this.hiredAt = hiredAt;
		this.financialComapany = financialComapany;
		this.accountNum = accountNum;
		this.totalSalary = totalSalary;
		this.retirementDate = retirementDate;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getDept() {
		return dept;
	}

	public String getPosition() {
		return position;
	}

	public Date getHiredAt() {
		return hiredAt;
	}

	public String getFinancialComapany() {
		return financialComapany;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public Long getTotalSalary() {
		return totalSalary;
	}

	public String getRetirementDate() {
		return retirementDate;
	}
	
	
}
