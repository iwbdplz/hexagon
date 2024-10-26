package employee.service;

import java.util.Map;

// 社員情報修正リクエストDTO
public class UpdateUserRequest {
	
	// user info
	private String userId;
	private String username;
	private String email;
	private String phoneNum;
	
	// employee
	private String dept;
	private String position;
	private String hiredAt;
	private String financialCo;
	private String accountNum;
	
	public String getUserId() {
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

	public String getHiredAt() {
		return hiredAt;
	}

	public String getFinancialCo() {
		return financialCo;
	}

	public String getAccountNum() {
		return accountNum;
	}

	//　リクエストパラメーターに全ての情報があるか検査する。
	public void validate(Map<String, Boolean> errors) {
		// userinfo
		checkEmpty(errors, userId, "userId");
		checkEmpty(errors, username, "username");
		checkEmpty(errors, email, "email");
		checkEmpty(errors, phoneNum, "phoneNum");
		
		// employee
		checkEmpty(errors, dept, "dept");
		checkEmpty(errors, position, "position");
		checkEmpty(errors, hiredAt, "hiredAt");
		checkEmpty(errors, financialCo, "financialCo");
		checkEmpty(errors, accountNum, "accountNum");
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}

	public UpdateUserRequest(String userId, String username, String email, String phoneNum, String dept, String position,
			String hiredAt, String financialCo, String accountNum) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phoneNum = phoneNum;
		this.dept = dept;
		this.position = position;
		this.hiredAt = hiredAt;
		this.financialCo = financialCo;
		this.accountNum = accountNum;
	}

}
