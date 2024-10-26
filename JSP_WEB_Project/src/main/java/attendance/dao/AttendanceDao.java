package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import attendance.model.AttendanceRecord;
import attendance.model.LeaveAttendanceSettings;
import attendance.model.NewAttendance;
import attendance.model.NewLeaveAttendance;
import jdbc.JDBCUtil;

public class AttendanceDao {

	// 勤怠記録照会queryを実行するメソッド
	public List<AttendanceRecord> select(Connection conn, int firstRow, int endRow) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select b.*, c.*, d.* from (select rownum as rnum, a.* from (select * from employee order by emp_id desc) a where rownum <= ?) b, user_info c, attendance_record d where rnum >= ? and b.user_id = c.user_id and b.emp_id=d.emp_id");
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			List<AttendanceRecord> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertAttendanceRecord(rs));
			}
			return result;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}

	}

	// 休暇記録照会queryを実行するメソッド
	public List<LeaveAttendanceSettings> selectLeave(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select b.*, c.*, d.* from (select rownum as rnum, a.* from (select * from employee order by emp_id desc) a where rownum <= ?) b, user_info c, attendance_settings d where rnum >= ? and b.user_id = c.user_id and b.emp_id=d.emp_id");
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			List<LeaveAttendanceSettings> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertLeaveAttendanceSettings(rs));
			}

			return result;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);

		}

	}

	// ResultSet形の勤怠録をAttendanceRecord形に変換する。
	private AttendanceRecord convertAttendanceRecord(ResultSet rs) throws SQLException {

		return new AttendanceRecord(rs.getString("emp_id"), rs.getString("user_name"), rs.getString("dept"),
				rs.getString("position"), rs.getDate("attend_date"), rs.getString("status")

		);

	}

	// ResultSet形の休暇記録をLeaveAttendanceSettings形に変換する。
	private LeaveAttendanceSettings convertLeaveAttendanceSettings(ResultSet rs) throws SQLException {

		return new LeaveAttendanceSettings(rs.getString("emp_id"), rs.getString("user_name"), rs.getString("dept"),
				rs.getString("position"), rs.getDate("start_date"), rs.getDate("end_date"), rs.getInt("leave_days")

		);

	}

	// 新規勤怠記録をATTENDANCE_RECORDテーブルに追加するqueryを実行するメソッド
	public void insertAttendanceSetting(Connection conn, NewAttendance newAttendance) throws SQLException {

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into attendance_record values(seq.nextval, ?, ?, ?)");
			pstmt.setString(1, newAttendance.getEmpId());
			pstmt.setTimestamp(2, new Timestamp(newAttendance.getAttendDate().getTime()));
			pstmt.setString(3, newAttendance.getStatus());

			pstmt.executeUpdate();

		} finally {
			JDBCUtil.close(pstmt);
		}
	}

	// 新規休暇記録をATTENDANCE _SETTINGSテーブルに追加するqueryを実行するメソッド
	public void insertLeaveAttendance(Connection conn, NewLeaveAttendance newLeaveAttendance) throws SQLException {

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into attendance_settings values(seq.nextval, ?, ?, ?, ?)");
			pstmt.setString(1, newLeaveAttendance.getEmpId());
			pstmt.setString(2, newLeaveAttendance.getLeaveDays());
			pstmt.setTimestamp(3, new Timestamp(newLeaveAttendance.getStartDate().getTime()));
			pstmt.setTimestamp(4, new Timestamp(newLeaveAttendance.getEndDate().getTime()));

			pstmt.executeUpdate();

		} finally {
			JDBCUtil.close(pstmt);
		}

	}

	// ATTENDANCE_SETTINGSテーブルのコラムの数を数えるメソッド
	public int selectCountAttendanceSettings(Connection conn) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from attendance_settings");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}

	}

	// ATTENDANCE_RECORDテーブルのコラムの数を数える
	public int selectCountAttendanceRecord(Connection conn) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from attendance_record");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(stmt);
		}

	}

}
