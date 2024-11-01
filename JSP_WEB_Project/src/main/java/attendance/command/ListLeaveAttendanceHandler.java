package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.service.LeaveAttendanceListPage;
import attendance.service.LeaveAttendanceListService;
import mvc.command.CommandHandler;

public class ListLeaveAttendanceHandler implements CommandHandler {

	private LeaveAttendanceListService leaveAttendanceListService = new LeaveAttendanceListService();

	//　休暇照会・記録ページを表示するためのメソッド
	// 휴가조회/기록 페이지를 표시하기 위한 메소드 
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		LeaveAttendanceListPage leaveAttendanceListPage = leaveAttendanceListService.getLeaveAttendanceListPage(pageNo);
		req.setAttribute("leaveAttendanceListPage", leaveAttendanceListPage);
		return "/WEB-INF/view/listLeaveAttendance.jsp";
	}

}
