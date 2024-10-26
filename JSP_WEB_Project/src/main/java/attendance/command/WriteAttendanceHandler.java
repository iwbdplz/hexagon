package attendance.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.service.NewAttendanceRequest;
import attendance.service.NewAttendanceService;
import mvc.command.CommandHandler;

public class WriteAttendanceHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/listAttendance.jsp";
	private NewAttendanceService newAttendanceService = new NewAttendanceService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			// GET要請の場合、processFormメソッドを呼び出し、返却する。
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			// POST要請の場合、processSubmitメソッドを呼び出し、返却する。
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	
	// POST要請を処理するメソッド
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		//　createNewAttendanceメソッドを使い、新規勤怠記録の情報をnewAttendacneReqに保存する。
		NewAttendanceRequest newAttendanceReq = createNewAttendance(req);
		//　エラーがあるかを確認する。
		newAttendanceReq.validate(errors);

		//　エラーがある場合、勤怠照会ページに戻る。
		if (!errors.isEmpty()) {
			return "/attendanceRecord/list.do";
		}

		//　エラーがない場合、writeメソッドを使い、データーベースーにデーターを追加する。その後、休暇照会ページに戻る。
		newAttendanceService.write(newAttendanceReq);
		return "/attendanceRecord/list.do";

	}

	
	//　GET要請を処理するメソッド
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

	
	//　パラメーターを取得し、新規勤怠データーを保存する。
	private NewAttendanceRequest createNewAttendance(HttpServletRequest req) throws ParseException {
		return new NewAttendanceRequest(req.getParameter("empId"), req.getParameter("attendDate"),
				req.getParameter("status"));
	}

}
