package salary.dto;

import java.util.Date;

public class EmpSalary {
	
	int emp_id;
	String user_name;
	String emp_dept;
	Date salary_date;
	int total_salary;
	int ded_amount;
	int actual_payment;
	
	public EmpSalary(int emp_id, String user_name,String emp_dept, Date salary_date, int total_salary, int ded_amount) {
		this.emp_id = emp_id;
		this.user_name = user_name;
		this.emp_dept = emp_dept;
		this.salary_date = salary_date;
		this.total_salary = total_salary;
		this.ded_amount = ded_amount;
		this.actual_payment = total_salary - ded_amount;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getEmp_dept() {
		return emp_dept;
	}

	public Date getSalary_date() {
		return salary_date;
	}

	public int getTotal_salary() {
		return total_salary;
	}

	public int getDed_amount() {
		return ded_amount;
	}

	public int getActual_payment() {
		return actual_payment;
	}

	
	
	

}
