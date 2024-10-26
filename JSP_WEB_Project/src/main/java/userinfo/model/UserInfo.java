package userinfo.model;

import java.util.Date;

public class UserInfo {

	private Long userId;
	private String username;
	private String email;
	private String phoneNum;
	private Date createdAt;
	
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public UserInfo(String username, String email, String phoneNum, Date createdAt) {
		super();
		this.username = username;
		this.email = email;
		this.phoneNum = phoneNum;
		this.createdAt = createdAt;
	}
	public UserInfo(Long userId, String username, String email, String phoneNum, Date createdAt) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phoneNum = phoneNum;
		this.createdAt = createdAt;
	}
	
	public UserInfo(Long userId, String username, String email, String phoneNum) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phoneNum = phoneNum;
	}
}
