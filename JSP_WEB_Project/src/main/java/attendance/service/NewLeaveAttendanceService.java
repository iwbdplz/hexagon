package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import attendance.dao.AttendanceDao;
import attendance.model.NewLeaveAttendance;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;

//新規休暇記録の追加 サービス
//신규휴가기록 추가 서비스 
public class NewLeaveAttendanceService {

	private AttendanceDao attendanceDao = new AttendanceDao();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	


	//ページからパラメーターを取得し、insertLeaveAttendanceメソッドを通じて、保存する。
	//페이지로부터 파라미터를 얻어 insertLeaveAttendance메소드를 통해 저장한다. 
	public void write(NewLeaveAttendanceRequest newLeaveAttendanceReq) throws ParseException {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			//　トランザクション。自動的にコミットされないように設定。
			conn.setAutoCommit(false);

			attendanceDao.insertLeaveAttendance(conn,
					new NewLeaveAttendance(newLeaveAttendanceReq.getEmpId(), newLeaveAttendanceReq.getLeaveDays(),
							formatter.parse(newLeaveAttendanceReq.getStartDate()), formatter.parse(newLeaveAttendanceReq.getEndDate())

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
