package userinfo.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import userinfo.dao.UserInfoDao;

//ユーザー削除のサービスロジックを提供するクラス。
// 유저 삭제 서비스 로직을 제공하는 클래스
public class UserInfoDeleteService {
	
	private UserInfoDao userInfoDao = new UserInfoDao();
	
	public void deleteUserInfo(String[] userIds) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userInfoDao.deleteUser(conn, userIds);
			
			//　削除を全部成功した後にコミットする。
			// 삭제를 전부 성공한 후에 커밋한다.
			conn.commit();
		} catch (SQLException e) {
			// 途中にエラーが発生した時はロールバックする。
			// 도중에 에러가 발생했을 때는 롤백한다.
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
