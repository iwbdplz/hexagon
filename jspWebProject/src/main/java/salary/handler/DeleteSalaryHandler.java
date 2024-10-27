package salary.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import salary.service.SelectSalaryDelete;

public class DeleteSalaryHandler implements CommandHandler {
	
	private SelectSalaryDelete selectSalaryDelete = new SelectSalaryDelete();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	
	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String emp_idStr = req.getParameter("emp_id");
		String salary_date = req.getParameter("salary_date");
		int emp_id = Integer.parseInt(emp_idStr);
		selectSalaryDelete.delete(emp_id,salary_date);
		
		return "/WEB-INF/view/listSalary.jsp";
		
	}
}
