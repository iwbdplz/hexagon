package userinfo.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import userinfo.dao.UserInfoDao;

//ユーザー削除のサービスロジックを提供するクラス。
public class UserInfoDeleteService {
	
	private UserInfoDao userInfoDao = new UserInfoDao();
	
	public void deleteUserInfo(String[] userIds) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userInfoDao.deleteUser(conn, userIds);
			
			//　削除を全部成功した後にコミットする。
			conn.commit();
		} catch (SQLException e) {
			// 途中にエラーが発生した時はロールバックする。
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
