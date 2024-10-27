package model;

import java.util.Date;

public class EmployeeCard {
//	 private double deductionAmount;
//	    private Date deductionDate;
//		public double getDeductionAmount() {
//			return deductionAmount;
//		}
//		public void setDeductionAmount(double deductionAmount) {
//			this.deductionAmount = deductionAmount;
//		}
//		public Date getDeductionDate() {
//			return deductionDate;
//		}
//		public void setDeductionDate(Date deductionDate) {
//			this.deductionDate = deductionDate;
//		}
//	    
	private int empId; // 사원 ID 社員ID
	private String userId;
    private String dept; // 부서 部門
    private String position; // 직위 役職
    private Date hireDate; // 입사일 入社日
    private String financialCompany; // 금융기관 金融機関
    private String accountNumber; // 계좌번호 口座番号
    
   //인사기록 카드에 작성할 내용 추가 人事記録カードに作成する内容の追加
    private Date retirementDate; // 퇴사일 退社日
    private String employmentStatus; // 상태 (재직/퇴직) 状態(在職/退職)
    
    public EmployeeCard(int empId, String userId, String dept, String position, Date hireDate, Date retirementDate, String employmentStatus) {
		// TODO Auto-generated constructor stub
    	this.empId = empId;
    	this.userId = userId;
    	this.dept = dept;
    	this.position = position;
    	this.hireDate = hireDate;
    	this.retirementDate = retirementDate;
    	this.employmentStatus = employmentStatus;
    	
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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
