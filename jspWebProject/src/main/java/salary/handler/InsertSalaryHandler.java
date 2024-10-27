package salary.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import salary.model.EmpAccountInfo;
import salary.service.InsertSalaryService;

public class InsertSalaryHandler implements CommandHandler {

	InsertSalaryService insertSalaryService = new InsertSalaryService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String emp_id = req.getParameter("emp_id");
		int empId = Integer.parseInt(emp_id);
	
		insertSalaryService.insertSalary(empId);
		return "/WEB-INF/view/listSalary.jsp";
	}

}
