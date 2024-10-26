package employee.command;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.CreateUserRequest;
import employee.service.EmployeeCreateService;
import mvc.command.CommandHandler;

// ユーザー登録ハンドラー
public class EmployeeCreateHandler implements CommandHandler{

	private static final String FORM_VIEW="/WEB-INF/view/EmployeeCreateForm.jsp";
	private EmployeeCreateService employeeCreateService = new EmployeeCreateService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//　リクエストのメソードに従って呼び出すメソードを区分する。
		// GETー＞ユーザー登録ページ
		//　POSTー＞ユーザー登録
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	//　リクエストのメソードがpostの場合のメトード。
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException, ServletException, IOException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		CreateUserRequest createUserReq = createUserRequest(req);
		
		//　全てのパラメータが入力されたのかを検証する。
		createUserReq.validate(errors);
		
		//　入力されなかったパラメータがあろ時はエラーを利用者に表紙する。
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		//　ユーザー登録
		Long userId = employeeCreateService.createEmployee(createUserReq);
		
		res.sendRedirect("/JSP_WEB_Project/user/update.do?userId=" + userId);
		return null;
	}

	// GETメソードの場合すぐ画面を表示する。
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private CreateUserRequest createUserRequest(HttpServletRequest req) throws ParseException {
		return new CreateUserRequest(
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
