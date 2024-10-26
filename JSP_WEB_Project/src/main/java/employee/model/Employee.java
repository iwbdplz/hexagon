package employee.model;

import java.util.Date;

public class Employee {
	
	private Long employeeId;  // 社員番号
	private Long userId;  // userinfo_fk
	private String dept;  //　所属
	private String position;  //　職位
	private Date hiredAt;  //　入社日
	private String financialCo; // 使用銀行
	private String accountNum;  // 口座番号
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public Long getUserId() {
		return userId;
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
	public String getFinancialCo() {
		return financialCo;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public Employee(Long userId, String dept, String position, Date hiredAt, String financialCo, String accountNum) {
		super();
		this.userId = userId;
		this.dept = dept;
		this.position = position;
		this.hiredAt = hiredAt;
		this.financialCo = financialCo;
		this.accountNum = accountNum;
	}
	public Employee(Long employeeId, Long userId, String dept, String position, Date hiredAt, String financialCo,
			String accountNum) {
		super();
		this.employeeId = employeeId;
		this.userId = userId;
		this.dept = dept;
		this.position = position;
		this.hiredAt = hiredAt;
		this.financialCo = financialCo;
		this.accountNum = accountNum;
	}
	
	
}
