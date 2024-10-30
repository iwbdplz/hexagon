package userinfo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import employee.service.CreateUserRequest;
import userinfo.dao.UserInfoDao;
import userinfo.model.UserInfo;

//ユーザー登録のサービスロジックを提供するクラス。
// 유저 등록 서비스 로직을 제공하는 클래스
public class UserInfoCreateService {
	
	private final UserInfoDao userInfoDao = new UserInfoDao();
	
	//　「EmployeeCreateService.createEmployee」メソッドから呼び出されるメソッドで、「Connection」もこのメソッドを呼び出すメソッドで管理するのでここでは管理しない。
	// [EmployeeCreateService.createEmployee] 메서드로부터 호출되는 메서드로, [Connection]도 이 메서드를 호출하는 메서드에서 관리하기 때문에 여기선 관리하지 않는다
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
