package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//　jdbc関連ユーティリティクラス
public class JdbcUtil {

	// 使ったリザルトセットの繋がりを切る。
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	//　使ったステートメントの繋がりを切る。
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	//　使ったコネクションの繋がりを切る。
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	//　何か問題がある時にクエリをロールバックする
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch(SQLException e) {
			
			}
		}
	}
}
