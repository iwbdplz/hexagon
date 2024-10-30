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
// 유저 등록 핸들러
public class EmployeeCreateHandler implements CommandHandler{

	private static final String FORM_VIEW="/WEB-INF/view/EmployeeCreateForm.jsp";
	private EmployeeCreateService employeeCreateService = new EmployeeCreateService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//　リクエストのメソードに従って呼び出すメソードを区分する。
		// 리퀘스 메서드에 따라서 호출할 메서드를 구분함.
		// GETー＞ユーザー登録ページ
		// GET->유저 등록 페이지
		//　POSTー＞ユーザー登録
		// POST->유저 등록
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
	// 리퀘스트의 메서드가 post인 경우의 메서드.
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException, ServletException, IOException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		CreateUserRequest createUserReq = createUserRequest(req);
		
		//　全てのパラメータが入力されたのかを検証する。
		// 모든 파라미터가 입력 되었는 지 검증한다.
		createUserReq.validate(errors);
		
		//　入力されなかったパラメータがあろ時はエラーを利用者に表紙する。
		// 입력되지 않은 파라미터가 있는 경우 에러를 사용자에게 표시한다.
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		//　ユーザー登録
		// 유저 등록
		Long userId = employeeCreateService.createEmployee(createUserReq);
		
		res.sendRedirect("/JSP_WEB_Project/user/update.do?userId=" + userId);
		return null;
	}

	// GETメソードの場合すぐ画面を表示する。
	// GET메서드인 경우 바로 화면을 표시한다.
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
