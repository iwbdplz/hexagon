package salary.model;

public class EmpAccountInfo {
	int emp_id;
	String financial_Company;
	String account_Number;
	
	
	public EmpAccountInfo(int emp_id, String financial_Company, String account_Number) {
		super();
		this.emp_id = emp_id;
		this.financial_Company = financial_Company;
		this.account_Number = account_Number;
	}


	public int getEmp_id() {
		return emp_id;
	}


	public String getFinancial_Company() {
		return financial_Company;
	}


	public String getAccount_Number() {
		return account_Number;
	}
	
	
	

}
