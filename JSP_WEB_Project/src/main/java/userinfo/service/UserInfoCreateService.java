package userinfo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import employee.service.CreateUserRequest;
import userinfo.dao.UserInfoDao;
import userinfo.model.UserInfo;

//ユーザー登録のサービスロジックを提供するクラス。
public class UserInfoCreateService {
	
	private final UserInfoDao userInfoDao = new UserInfoDao();
	
	//　「EmployeeCreateService.createEmployee」メソッドから呼び出されるメソッドで、「Connection」もこのメソッドを呼び出すメソッドで管理するのでここでは管理しない。
	public Long createUserInfo(Connection conn, CreateUserRequest createUserInfoRequest) throws SQLException {
		return userInfoDao.createUser(
				conn,
				new UserInfo(
						createUserInfoRequest.getUsername(),
						createUserInfoRequest.getEmail(),
						createUserInfoRequest.getPhoneNum(),
						new Date()
						)
				);
	}
}
