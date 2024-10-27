package salary.dto;

public class PaymentItems {

	private int totalSalary;	// 총급여(=Salary_Mangement의 Total_Salary)
	private int basicSalary;				// 기본급
	private int foodexp = 200000;			// 식비
	private int childcareAllow  = 0;	// Allowance준말
	private int jobAllow = 0;				// 직책수당
	private int vehicleCost = 0;			// 차량유지비
	private int serviceAllow = 0;			// 근속수당
	private int onDutyAllow = 0;			// 당직수당
	private int bonus = 0;					// 상여금
	private int holidayAllow = 0;			// 휴일 수당;
	
	public PaymentItems(int totalSalary, int salary_item_amount) {
		this.totalSalary = totalSalary;
	
		if(salary_item_amount > 0) {
			this.basicSalary = totalSalary - 200000 - salary_item_amount;
			childcareAllow  = salary_item_amount / 2 ;
			int temp = childcareAllow;
			bonus = temp;
		}else {
			this.basicSalary = totalSalary - 200000;
		}
		
		
	}


	public int getTotalSalary() {
		return totalSalary;
	}


	public int getBasicSalary() {
		return basicSalary;
	}


	public int getFoodexp() {
		return foodexp;
	}


	public int getChildcareAllow() {
		return childcareAllow;
	}


	public int getJobAllow() {
		return jobAllow;
	}


	public int getVehicleCost() {
		return vehicleCost;
	}


	public int getServiceAllow() {
		return serviceAllow;
	}


	public int getOnDutyAllow() {
		return onDutyAllow;
	}


	public int getBonus() {
		return bonus;
	}


	public int getHolidayAllow() {
		return holidayAllow;
	}
	
	
	
	
	
	
	
}
