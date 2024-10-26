package userinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil;
import userinfo.model.UserInfo;

//データベースに接近するメソードを管理するクラス。
public class UserInfoDao {
	
	// ユーザー登録。
	//　ユーザー氏名、Eメール、携帯番号、生成日が必要。
	private final static String USERINFO_INSERT_QUERY = "insert into user_info values(seq.nextval,?,?,?,?)";
	// ユーザー登録の後、データベースから生成されたユーザーの情報からもらうデータを指定。
	private final static String[] RETURNID = { "USER_ID" };
	//　ユーザー修正
	//　ユーザー氏名、Eメール、携帯番号、ユーザーIDが必要。
	private final static String USERINFO_UPDATE_QUERY = "update user_info u set u.user_name=?, u.email=?, u.phone_number=? where u.user_id=?";
	//　ユーザー削除
	//　ユーザーIDのリストが必要。
	private final static String USERINFO_DELETE_QUERY = "delete from user_info where user_id in ";
	
	public Long createUser(Connection conn, UserInfo userinfo) throws SQLException {
		ResultSet rs = null;
		try(PreparedStatement pstmt = conn.prepareStatement(USERINFO_INSERT_QUERY, RETURNID)){
			pstmt.setString(1, userinfo.getUsername());
			pstmt.setString(2, userinfo.getEmail());
			pstmt.setString(3, userinfo.getPhoneNum());
			pstmt.setTimestamp(4, new Timestamp(userinfo.getCreatedAt().getTime()));
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
		        return rs.getLong(1);
		    }
			else {
				throw new SQLException("failed to return sequence value");
			}
		} finally {
			JdbcUtil.close(rs);
		}
	}
	
	public void updateUser(Connection conn, UserInfo userinfo) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement(USERINFO_UPDATE_QUERY)){
			pstmt.setString(1, userinfo.getUsername());
			pstmt.setString(2, userinfo.getEmail());
			pstmt.setString(3, userinfo.getPhoneNum());
			pstmt.setLong(4, userinfo.getUserId());
			
			pstmt.executeUpdate();
		}
	}
	
	public void deleteUser(Connection conn, String[] userIds) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(USERINFO_DELETE_QUERY + "('" + String.join("', '", userIds) + "')");
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
