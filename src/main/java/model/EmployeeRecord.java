package model;

import java.util.Date;

public class EmployeeRecord {
	private int empId; // 사원 ID 社員ID
	private int userId;
    private String department; // 부서 部門
    private String position; // 직위 役職
    private Date hireDate; // 입사일 入社日
    private String financialCompany; // 금융기관 金融機関
    private String accountNumber; // 계좌번호 口座番号
    
   //인사기록 카드에 작성할 내용 추가 人事記録カードに作成する内容の追加
    private Date retirementDate; // 퇴사일 退社日
    private String employmentStatus; // 상태 (재직/퇴직) 状態(在職/退職)
    
    

    
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getFinancialCompany() {
		return financialCompany;
	}
	public void setFinancialCompany(String financialCompany) {
		this.financialCompany = financialCompany;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
    
}
