package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//　jdbc関連ユーティリティクラス
// jdbc관련 유틸리티 클래스
public class JdbcUtil {

	// 使ったリザルトセットの繋がりを切る。
	// 사용한 ResultSet의 연결을 끊음
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	//　使ったステートメントの繋がりを切る。
	// 사용한 Statement의 연결을 끊음
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	//　使ったコネクションの繋がりを切る。
	// 사용한 Connection의 연결을 끊음
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	//　何か問題がある時にクエリをロールバックする
	// 무엇인가 문제가 있을 때 쿼리를 롤백함
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch(SQLException e) {
			
			}
		}
	}
}
