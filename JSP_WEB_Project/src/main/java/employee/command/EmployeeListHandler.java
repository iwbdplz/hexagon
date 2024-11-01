package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.EmployeeListPage;
import employee.service.EmployeeListService;
import mvc.command.CommandHandler;

//　ユーザーリスト照会ハンドラー
// 유저 리스트 조회 핸들러
public class EmployeeListHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/EmployeeListForm.jsp";
	private EmployeeListService employeeListService = new EmployeeListService();
	
	//　ユーザーリスト照会はどんなリクエストメソードでも照会だけする。
	// 유저 리스트 조회는 어떤 리퀘스트 메서드든 조회만 한다.
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		EmployeeListPage employeeListPage = null;
		
		// 検索語有無によって呼び出すメソードが違う。
		// 검색어 유무에 따라 호출하는 메서드가 다르다.
		String keyword = req.getParameter("keyword");
		String searchBy = req.getParameter("searchBy");
		if (keyword != null && !keyword.isEmpty() && searchBy != null && !searchBy.isEmpty()){
			employeeListPage = employeeListService.getEmployeeListPageWithKeyword(pageNo, keyword, searchBy);
			req.setAttribute("keyword", keyword);
			req.setAttribute("searchBy", searchBy);
			String searchByValue = null;
			switch (searchBy) {
				case "user_name":
					searchByValue = "氏名";
					break;
				case "position":
					searchByValue = "職位";
					break;
				case "emp_id":
					searchByValue = "社員番号";
					break;
				case "dept":
					searchByValue = "所属";
					break;
				default:
					break;
			}
			req.setAttribute("searchByValue", searchByValue);
		}
		else{
			employeeListPage = employeeListService.getEmployeeListPage(pageNo);
		}
		req.setAttribute("employeeList", employeeListPage);
		return FORM_VIEW;
	}
	
}
