package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.service.AttendanceListPage;
import attendance.service.AttendanceListService;
import mvc.command.CommandHandler;

public class ListAttendanceHandler implements CommandHandler {

	private AttendanceListService attendanceListService = new AttendanceListService();

	//　勤怠照会・記録ページを表示するためのメソッド
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		AttendanceListPage attendanceListPage = attendanceListService.getAttendanceListPage(pageNo);
		req.setAttribute("attendanceListPage", attendanceListPage);
		return "/WEB-INF/view/listAttendance.jsp";

	}

}
