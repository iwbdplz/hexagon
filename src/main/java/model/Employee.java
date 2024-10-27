package model;

import java.util.Date;

public class Employee {
    private int empId;           // 사원 ID (社員ID)
    private int userId;          // 사용자 ID (ユーザーID)
    private String dept;         // 부서명 (部署名)
    private String position;     // 직위 (職位)
    private Date hireDate;       // 입사일 (入社日)
    private String financialCompany; // 금융기관 (金融機関)
    private String accountNumber; // 계좌번호 (口座番号)
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
    
    
    

}
