package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeeRecord;
import service.EmployeeRecService;

public class EmployeeRecordController implements CommandHandler {
	private EmployeeRecService employeeRecService = new EmployeeRecService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 String action = req.getParameter("action"); // 요청된 액션  要請されたアクション
	        int empId = Integer.parseInt(req.getParameter("empId")); // 사원 ID 社員ID

	        if ("view".equals(action)) {
	            EmployeeRecord record = employeeRecService.getEmployeeRecord(empId); // 인사 정보 조회 人事情報の照会
	            req.setAttribute("record", record);
	            return "/view/EditEmployeeRecord.jsp"; // 조회 화면 照会画面
	        } else if ("edit".equals(action)) {
	            EmployeeRecord record = employeeRecService.getEmployeeRecord(empId); // 인사 정보 수정
	            req.setAttribute("record", record);
	            return "/view/EditEmployeeRecord.jsp"; // 수정 화면 修正画面
	        } else if ("update".equals(action)) {
	            // 수정 처리 修正処理を行います
	            EmployeeRecord record = new EmployeeRecord();
	            record.setEmpId(empId);
	            record.setUserId(Integer.parseInt(req.getParameter("user_id")));
	            record.setDepartment(req.getParameter("department"));
	            record.setPosition(req.getParameter("position"));
	            record.setHireDate(java.sql.Date.valueOf(req.getParameter("hireDate")));
	            record.setFinancialCompany(req.getParameter("financialCompany"));
	            record.setAccountNumber(req.getParameter("accountNumber"));

	            employeeRecService.updateEmployeeRecord(record); // 업데이트 アップデートします
//	            return "redirect:/employee/list.do"; // 수정 후 목록으로 리다이렉트 修正後のリストへのリダイレクトです?/
	            res.sendRedirect(req.getContextPath() + "/employee/list.do"); 
	            return null;  
	      
	        } else if ("delete".equals(action)) {
	            employeeRecService.deleteEmployeeRecord(empId); // 삭제 처리 削除処理します
	            res.sendRedirect(req.getContextPath() + "/employee/list.do"); // 削除後のリストにリダイレクトします
	        }

	        return null; // 기본값 基本値
	}
	
}
