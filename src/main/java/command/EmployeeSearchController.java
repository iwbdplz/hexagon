package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeCardDAO;
import model.EmployeeCard;

public class EmployeeSearchController implements CommandHandler{
	private EmployeeCardDAO employeeCardDAO = new EmployeeCardDAO();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		   String dept = req.getParameter("dept");
	        String position = req.getParameter("position");
	        String employmentType = req.getParameter("employmentType");
	        String keyword = req.getParameter("keyword");
	        
	        List<EmployeeCard> employees = employeeCardDAO.searchEmployees(dept, position, employmentType, keyword);

	        req.setAttribute("employees", employees);
	        return "/view/employeeSearchPopup.jsp";
	}
	
	
}
