package attendance.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import attendance.dao.AttendanceDao;
import attendance.model.NewAttendance;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

//新規勤怠記録を追加　サービス
public class NewAttendanceService {

	private AttendanceDao attendanceDao = new AttendanceDao();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	//ページからパラメーターを取得し、insertAttedanceSettingメソッドを通じて、保存する。
	public void write(NewAttendanceRequest newAttendanceReq) throws ParseException {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			//　トランザクション。自動的にコミットされないように設定。
			conn.setAutoCommit(false);

			attendanceDao.insertAttendanceSetting(conn, new NewAttendance(newAttendanceReq.getEmpId(),
					formatter.parse(newAttendanceReq.getAttendDate()), newAttendanceReq.getStatus()

			));

			conn.commit();
		} catch (SQLException e) {
			JDBCUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(conn);
		}

	}
}
