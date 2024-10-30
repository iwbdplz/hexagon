package userinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil;
import userinfo.model.UserInfo;

//データベースに接近するメソードを管理するクラス。
//데이터베이스에 접근하는 메서드를 관리하는 클래스
public class UserInfoDao {
	
	// ユーザー登録。
	// 유저 등록
	//　ユーザー氏名、Eメール、携帯番号、生成日が必要。
	// 유저의 성명, 이메일, 전화번호, 생성일이 필요
	private final static String USERINFO_INSERT_QUERY = "insert into user_info values(seq.nextval,?,?,?,?)";
	// ユーザー登録の後、データベースから生成されたユーザーの情報からもらうデータを指定。
	// 유저 등록 후, 데이터베이스로부터 생성된 유저의 정보에서 받을 데이터를 지정
	private final static String[] RETURNID = { "USER_ID" };
	//　ユーザー修正
	// 유저 수정
	//　ユーザー氏名、Eメール、携帯番号、ユーザーIDが必要。
	// 유저의 성명, 이메일, 전화번호, 유저 ID가 필요
	private final static String USERINFO_UPDATE_QUERY = "update user_info u set u.user_name=?, u.email=?, u.phone_number=? where u.user_id=?";
	//　ユーザー削除
	// 유저 삭제
	//　ユーザーIDのリストが必要。
	// 유저 ID의 리스트가 필요
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
