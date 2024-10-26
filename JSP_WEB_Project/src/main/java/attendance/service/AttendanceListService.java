package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attendance.dao.AttendanceDao;
import attendance.model.AttendanceRecord;
import jdbc.connection.ConnectionProvider;

public class AttendanceListService {

	
	//正義したquery・メソッドを使うため、AttendanceDaoクラス刑の変数を生成する。
	private final AttendanceDao attendanceDao = new AttendanceDao();
	
	// 各ページに表示する記録（コラム）の数
	private int size = 10;

	public AttendanceListPage getAttendanceListPage(int pageNum){
		int firstRow = 0; //ページの一番上に表示する列の数字
		int endRow = 0; //ページの一番下に表示する列の数字
		List<AttendanceRecord> content = null;
		
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			//勤怠記録の数を数えるメソッドを呼出
			int total = attendanceDao.selectCountAttendanceRecord(conn);
			if (total > 0){
				firstRow = (pageNum-1) * size + 1;
				endRow = firstRow + size - 1;
				content = attendanceDao.select(conn, firstRow, endRow);
			}
			return new AttendanceListPage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
