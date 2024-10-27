package salary.model;

import java.util.Date;

public class Deduction {
	
	int deduction_id;
	int emp_id;
	int ded_amount;
	Date ded_date;
	
	public Deduction(int deduction_id, int emp_id, int ded_amount, Date ded_date) {
		this.deduction_id = deduction_id;
		this.emp_id = emp_id;
		this.ded_amount = ded_amount;
		this.ded_date = ded_date;
	}

	public int getDeduction_id() {
		return deduction_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public int getDed_amount() {
		return ded_amount;
	}

	public Date getDed_date() {
		return ded_date;
	}
	
}
