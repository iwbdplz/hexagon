package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import attendance.dao.AttendanceDao;
import attendance.model.LeaveAttendanceSettings;
import jdbc.connection.ConnectionProvider;

public class LeaveAttendanceListService {

	
	//正義したquery・メソッドを使うため、AttendanceDaoクラス刑の変数を生成する。
	//정의한 쿼리메소드를 사용하기 위해 AttendanceDao클래스형의 변수를 생성한다. 
	private final AttendanceDao attendanceDao = new AttendanceDao();

	// 各ページに表示する記録（コラム）の数
	// 각 페이지에 표시할 기록(칼럼)의 수 
	private int size = 10;

	public LeaveAttendanceListPage getLeaveAttendanceListPage(int pageNum){
		int firstRow = 0; //ページの一番上に表示する列の数字, 페이지의 가장 위에 표시할 열의 번호 
		int endRow = 0; //ページの一番下に表示する列の数字, 페이지의 가장 아래에 표시할 열의 번호 
		List<LeaveAttendanceSettings> content = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			//休暇記録の数を数えるメソッドを呼出
			//휴가기록의 수를 세는 메소드를 호출 
			int total = attendanceDao.selectCountAttendanceSettings(conn);
			if (total > 0){
				firstRow = (pageNum-1) * size + 1;
				endRow = firstRow + size - 1;
				content = attendanceDao.selectLeave(conn, firstRow, endRow);
			}
			return new LeaveAttendanceListPage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
