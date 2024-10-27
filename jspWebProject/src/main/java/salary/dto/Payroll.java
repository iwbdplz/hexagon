package salary.dto;

import java.util.Date;

public class Payroll {
	String year;
	String month;
	
	String yearMonth;
	String settleDate;
	String payDate;
	
	int salary_count;
	int total_salary;
	int ded_amount;
	int actual_payment;
	
	public Payroll(String year, String month, int salary_count, int total_salary, int ded_amount) {
		this.year = year;
		this.month = month;
		this.yearMonth = year+"-"+month;	
		this.settleDate = year+"-"+month+"-01~"+year+"-"+month+"-31";
		int monthNum = Integer.parseInt(month) % 12 + 1;
		if(monthNum > 9) {this.payDate = year+"-"+(monthNum%12+1)+"-05";}
		else{this.payDate = year+"-0"+(monthNum%12+1)+"-05";}
		
		this.salary_count = salary_count;
		this.total_salary = total_salary;
		this.ded_amount = ded_amount;
		this.actual_payment = total_salary - ded_amount;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public int getSalary_count() {
		return salary_count;
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
