package salary.model;

import java.util.Date;

public class SalaryManagement {
	int salary_id;
	int emp_int;
	Date salary_date;
	int total_salary;
	String fin_company;
	String account;
	
	public SalaryManagement(int salary_id, int emp_int, Date salary_date, int total_salary, String fin_company,
			String account) {
		this.salary_id = salary_id;
		this.emp_int = emp_int;
		this.salary_date = salary_date;
		this.total_salary = total_salary;
		this.fin_company = fin_company;
		this.account = account;
	}

	public int getSalary_id() {
		return salary_id;
	}

	public int getEmp_int() {
		return emp_int;
	}

	public Date getSalary_date() {
		return salary_date;
	}

	public int getTotal_salary() {
		return total_salary;
	}

	public String getFin_company() {
		return fin_company;
	}

	public String getAccount() {
		return account;
	}
	

}
