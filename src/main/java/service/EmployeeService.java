package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;
import util.PagingUtil;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	  public PagingUtil<Employee> getAllEmployees(int currentPage, int pageSize) {
	        int totalItems = employeeDAO.getTotalEmployees(); // 전체 사원 수 조회 全社員数の照会
	        List<Employee> employeeList = employeeDAO.getAllEmployees(currentPage, pageSize); // 페이지에 맞는 사원 조회 ページにあった社員照会
	        return new PagingUtil<>(currentPage, totalItems, pageSize, employeeList);
	    }
}
