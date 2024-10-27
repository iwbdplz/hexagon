package salary.model;

public class SalaryItems {
	int salary_item_id;
	String item_name;
	int amount;
	
	public SalaryItems(int salary_item_id, 
			String item_name, int amount) {
		this.salary_item_id = salary_item_id;
		this.item_name = item_name;
		this.amount = amount;
	}

	public int getSalary_item_id() {
		return salary_item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public int getAmount() {
		return amount;
	}
	

}
