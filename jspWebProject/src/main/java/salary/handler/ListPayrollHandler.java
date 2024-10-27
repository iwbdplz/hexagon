package salary.handler;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import salary.dto.Payroll;
import salary.service.ListPayrollService;

public class ListPayrollHandler implements CommandHandler {
	
	ListPayrollService payroll = new ListPayrollService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String year = req.getParameter("year");

		if(year == null) {year = String.valueOf(LocalDate.now().getYear());}
		
		List<Payroll> payrollList = payroll.getList(year);
		System.out.println(payrollList.size());
		req.setAttribute("payrollList",payrollList);
		return "/WEB-INF/view/listPayroll.jsp";
	}
}
