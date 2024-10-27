package salary.dto;

public class DeductionItems {
	private int totalSalary;
	private int nationalPension;
	private int healthIns;
	private int longTerm;
	private int employment;
	
	private int incomeTax = 0;
	private int localIncomeTax = 0;
	private int funeralExp = 0;
	
	private int deduction_amount;
	
	
	public DeductionItems(int totalSalary, int salary_item_amount, int deduction_amount) {
		this.deduction_amount = deduction_amount;
		this.totalSalary = totalSalary;
		int basicSalary = totalSalary-200000;
		if(salary_item_amount > 0 ) {basicSalary -= salary_item_amount;}
		nationalPension = (int)(basicSalary * 0.045);
		healthIns = (int)(basicSalary * 0.3545);
		longTerm = (int)(healthIns * 0.1295);
		employment = (int)(this.totalSalary * 0.009);		
	}


	public int getTotalSalary() {
		return totalSalary;
	}


	public int getNationalPension() {
		return nationalPension;
	}


	public int getHealthIns() {
		return healthIns;
	}


	public int getLongTerm() {
		return longTerm;
	}


	public int getEmployment() {
		return employment;
	}


	public int getIncomeTax() {
		return incomeTax;
	}


	public int getLocalIncomeTax() {
		return localIncomeTax;
	}


	public int getFuneralExp() {
		return funeralExp;
	}


	public int getDeduction_amount() {
		return deduction_amount;
	}	
	

}
