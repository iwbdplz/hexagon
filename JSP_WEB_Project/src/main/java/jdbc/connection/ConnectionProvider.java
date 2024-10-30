package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	//　コネクションプールからjdbc繋がりをゲットするメソッド。
	// 커넥션 풀로부터 jdbc연결을 get하는 메서드
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:jspwebproject");
	}
}
