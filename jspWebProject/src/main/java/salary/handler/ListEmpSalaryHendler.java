package salary.handler;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import salary.dto.EmpSalaryPage;
import salary.service.ListEmpSalaryService;

public class ListEmpSalaryHendler implements CommandHandler {
	
	
	private ListEmpSalaryService lesService = new ListEmpSalaryService();
	@Override
	public String process(HttpServletRequest req , HttpServletResponse resp) throws Exception{
		String pageNoVal = req.getParameter("pageNo");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		int pageNo = 1;
		if(pageNoVal != null) {pageNo = Integer.parseInt(pageNoVal);}
		if(year == null) {year = String.valueOf(LocalDate.now().getYear());}
		if(month == null) {
			if(LocalDate.now().getMonthValue()-1 == 0) {
				month = "12";
			}else {
				month = String.valueOf(LocalDate.now().getMonthValue()-1);
			}
		}
		
		EmpSalaryPage empSalaryPage = lesService.getListPage(pageNo, year, month);
		req.setAttribute("selectedYear", year);
		req.setAttribute("selectedMonth", month);
		req.setAttribute("empSalaryPage",empSalaryPage);
		return "/WEB-INF/view/listSalary.jsp";
	}
}
