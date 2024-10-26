package userinfo.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.service.UpdateUserRequest;
import userinfo.dao.UserInfoDao;
import userinfo.model.UserInfo;

//ユーザー修正のサービスロジックを提供するクラス。
public class UserInfoUpdateService {

	private final UserInfoDao userInfoDao = new UserInfoDao();
	
	//　「EmployeeUpdateService.updateEmployee」メソッドから呼び出されるメソッドで、「Connection」もこのメソッドを呼び出すメソッドで管理するのでここでは管理しない。
	public void updateUserInfo(Connection conn, UpdateUserRequest updateUserRequest) throws SQLException {
		userInfoDao.updateUser(
				conn,
				new UserInfo(
						Long.valueOf(updateUserRequest.getUserId()),
						updateUserRequest.getUsername(),
						updateUserRequest.getEmail(),
						updateUserRequest.getPhoneNum()
						)
				);
		
	}
	
}
