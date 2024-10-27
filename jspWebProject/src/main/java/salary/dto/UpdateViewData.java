package salary.dto;

public class UpdateViewData {
	
	private int emp_id;
	private String salary_date;
	private PaymentItems paymentItems;
	private DeductionItems deductionItems;
	
	
	
	public UpdateViewData(int emp_id, String salary_date, PaymentItems paymentItems, DeductionItems deductionItems ) {
		this.emp_id = emp_id;
		this.salary_date = salary_date;
		this.paymentItems = paymentItems;
		this.deductionItems = deductionItems;
	}



	public String getSalary_date() {
		return salary_date;
	}



	public int getEmp_id() {
		return emp_id;
	}



	public PaymentItems getPaymentItems() {
		return paymentItems;
	}



	public DeductionItems getDeductionItems() {
		return deductionItems;
	}
	

}
