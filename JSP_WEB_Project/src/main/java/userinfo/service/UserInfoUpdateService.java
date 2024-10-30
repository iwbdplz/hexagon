package userinfo.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.service.UpdateUserRequest;
import userinfo.dao.UserInfoDao;
import userinfo.model.UserInfo;

//ユーザー修正のサービスロジックを提供するクラス。
// 유저 수정 서비스 로직을 제공하는 클래스
public class UserInfoUpdateService {

	private final UserInfoDao userInfoDao = new UserInfoDao();
	
	//　「EmployeeUpdateService.updateEmployee」メソッドから呼び出されるメソッドで、「Connection」もこのメソッドを呼び出すメソッドで管理するのでここでは管理しない。
	// [EmployeeUpdateService.updateEmployee] 메서드로부터 호출되는 메서드로, [Connection]도 이 메서드를 호출하는 메서드에서 관리하기 때문에 여기선 관리하지 않는다.
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
