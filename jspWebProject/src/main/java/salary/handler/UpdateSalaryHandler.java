package salary.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import salary.dto.UpdateViewData;
import salary.service.UpdateSalaryService;

public class UpdateSalaryHandler implements CommandHandler {

	private UpdateSalaryService updateService = new UpdateSalaryService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String empId = req.getParameter("emp_id");
		String salary_date = req.getParameter("salary_date");
		int emp_id = 3;
		if(empId !=null) {emp_id=Integer.parseInt(empId);}
		
		UpdateViewData viewData = null;
		try {
			viewData = updateService.loadSalaryData(emp_id,salary_date);
			req.setAttribute("payAndDed", viewData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/view/updateSalaryTest.jsp";
	}
	
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		int emp_id = Integer.parseInt(req.getParameter("empId"));
		String salary_date = req.getParameter("salaryDate");
		int totalSalary = Integer.parseInt(req.getParameter("totalSalary"));
		System.out.println(totalSalary);
		System.out.println("Salary Date: " + salary_date);
		int deductionAmount = Integer.parseInt(req.getParameter("deductionAmount"));
		String np = req.getParameter("childcareAllow");
		String ja = req.getParameter("jobAllow");
		String vc = req.getParameter("vehicleCost");
		String sa = req.getParameter("serviceAllow");
		String od = req.getParameter("onDutyAllow");
		String bo = req.getParameter("bonus");
		String ha = req.getParameter("holidayAllow");
		String[] resultList = new String[] {np,ja,vc,sa,od,bo,ha};
		int result = 0;
		
		for(String item : resultList) {
			if(item != null)result += Integer.parseInt(item);
		}
		
		//만약 총급여액과 총공제금액을 이용해야한다면
		// 급여와 공제만뽑아서 ----메소드 실행
		try {
			updateService.updateSalary(emp_id,salary_date,totalSalary,deductionAmount,result);
			return "/WEB-INF/view/updateSalaryTest.jsp";
		}finally {
			
		}
	}

}
