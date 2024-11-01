package attendance.command;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.service.NewLeaveAttendanceRequest;
import attendance.service.NewLeaveAttendanceService;
import mvc.command.CommandHandler;

public class WriteLeaveAttendanceHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/listLeaveAttendance.jsp";
	private NewLeaveAttendanceService newLeaveAttendanceService = new NewLeaveAttendanceService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			// GET要請の場合、processFormメソッドを呼び出し、返却する。
			// GET요청일 경우, processFrom메소드를 호출하여 반환한다. 
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			// POST要請の場合、processSubmitメソッドを呼び出し、返却する。
			// POST요청일 경우, processSubmit메소드를 호출하여 반환한다. 
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// POST要請を処理するメソッド
	// POST요청을 처리하는 메소드 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		//　createNewLeaveAttendanceメソッドを使い、新規休暇記録の情報をnewLeaveAttendacneReqに保存する。
		// createNewLeaveAttendance메소드를 사용하여, 신규휴가기록 정보를 newLeaveAttendanceReq에 저장한다. 
		NewLeaveAttendanceRequest newLeaveAttendanceReq = createNewLeaveAttendance(req);
		//　エラーがあるかを確認する。
		// 에러가 있는지를 확인한다. 
		newLeaveAttendanceReq.validate(errors);

		//　エラーがある場合、休暇照会ページに戻る。
		// 에러가 있는 경우, 휴가조회 페이지로 돌아간다. 
		if (!errors.isEmpty()) {
			return "/leaveAttendanceSettings/leavelist.do";
		}

		//　エラーがない場合、writeメソッドを使い、データーベースーにデーターを追加する。その後、休暇照会ページに戻る。
		// 에러가 없는 경우, write메소드를 사용하여, 데이터베이스에 데이터를 추가한다. 그 후, 휴가조회 페이지에 돌아간다. 
		newLeaveAttendanceService.write(newLeaveAttendanceReq);
		return "/leaveAttendanceSettings/leavelist.do";

	}

	//　GET要請を処理するメソッド
	// GET요청을 처리하는 메소드 
	private String processForm(HttpServletRequest req, HttpServletResponse res) {

		return FORM_VIEW;
	}

	
	//　パラメーターを取得し、新規休暇データーを保存する。
	// 파라미터를 얻어 신규휴가 데이터를 저장한다. 
	private NewLeaveAttendanceRequest createNewLeaveAttendance(HttpServletRequest req) throws ParseException {

		return new NewLeaveAttendanceRequest(req.getParameter("empId"), req.getParameter("leaveDays"),
				req.getParameter("startDate"), req.getParameter("endDate")

		);

	}

}
