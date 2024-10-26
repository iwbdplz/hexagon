package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.EmployeeListPage;
import employee.service.EmployeeListService;
import mvc.command.CommandHandler;

//　ユーザーリスト照会ハンドラー
public class EmployeeListHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/EmployeeListForm.jsp";
	private EmployeeListService employeeListService = new EmployeeListService();
	
	//　ユーザーリスト照会はどんなリクエストメソードでも照会だけする。
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		EmployeeListPage employeeListPage = null;
		
		// 検索語有無によって呼び出すメソードが違う。
		String keyword = req.getParameter("keyword");
		if (keyword != null && !keyword.isEmpty()){
			employeeListPage = employeeListService.getEmployeeListPageWithKeyword(pageNo, keyword);
			req.setAttribute("keyword", keyword);
		}
		else{
			employeeListPage = employeeListService.getEmployeeListPage(pageNo);
		}
		req.setAttribute("employeeList", employeeListPage);
		return FORM_VIEW;
	}
	
}
