package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
//import mvc.command.CommandHandler;
import service.EmployeeService;
import util.PagingUtil;

public class EmployeeController implements CommandHandler{
	private EmployeeService employeeService = new EmployeeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	     int currentPage = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1"); // 기본값 1
	        int pageSize = 10; // 페이지당 아이템 수 ページ当たりのアイテム数

	        PagingUtil<Employee> pagingUtil = employeeService.getAllEmployees(currentPage, pageSize);
	        req.setAttribute("pagingUtil", pagingUtil);
	        return "/view/employee.jsp"; 
	}

}
