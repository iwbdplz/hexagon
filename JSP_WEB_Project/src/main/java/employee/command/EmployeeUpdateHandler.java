package employee.command;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.EmployeeInfoReadService;
import employee.service.EmployeeUpdateService;
import employee.service.EmployeeWithUserInfoAndTotalSalaryAndRetireDate;
import employee.service.UpdateUserRequest;
import mvc.command.CommandHandler;

//　ユーザー修正ハンドラー
public class EmployeeUpdateHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/EmployeeUpdateForm.jsp";
	private EmployeeUpdateService updateService = new EmployeeUpdateService();
	private EmployeeInfoReadService readService = new EmployeeInfoReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// GETー＞ユーザー修正ページ
		//　POSTー＞ユーザー修正
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	//　リクエストメソードがGETだった場合ユーザーの情報を表示する。
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		EmployeeWithUserInfoAndTotalSalaryAndRetireDate result = readService.getEmployee(Long.valueOf(req.getParameter("userId")));
		req.setAttribute("data", result);
		return FORM_VIEW;
	}
	
	//　リクエストメソードがPOSTだった場合ユーザーの情報を修正する。
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		UpdateUserRequest userRequest = toUpdateRequest(req);
		
		userRequest.validate(errors);
		
		if(!errors.isEmpty()) {
			return processForm(req, res);
		}
		updateService.updateEmployee(userRequest);
		return "/user/list.do";
	}

	private UpdateUserRequest toUpdateRequest(HttpServletRequest req) throws ParseException {
		return new UpdateUserRequest(
				req.getParameter("userId"),
				req.getParameter("username"),
				req.getParameter("email"),
				req.getParameter("phoneNum"),
				req.getParameter("dept"),
				req.getParameter("position"),
				req.getParameter("hiredAt"),
				req.getParameter("financialCo"),
				req.getParameter("accountNum")
				);
	}
}
