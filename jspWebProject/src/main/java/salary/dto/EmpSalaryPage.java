package salary.dto;

import java.util.List;


public class EmpSalaryPage {
	private int total;
	private int currentPage;
	private List<EmpSalary> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	private CountPay countPay;
	
	public EmpSalaryPage(int total, int currentPage, int size, List<EmpSalary> content, CountPay countPay) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.countPay = countPay;
		if(total == 0) {
			totalPages=0;
			startPage = 0;
			endPage = 0;
		}else {
			totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal == 0) startPage -= 5;
			
			endPage = startPage +4;
			if(endPage > totalPages) endPage = totalPages;
		}
	}
	/*
	 * if(content != null) { for(EmpSalary ep :content) { mts +=
	 * ep.getTotal_salary(); mda += ep.getDed_amount(); } mts_mda= mts_mda; }else
	 * mts_mda = 0;
	 * 
	 * countSalary = content.size(); }
	 */


	public boolean hasNoEmpSalarys() {
		return total == 0;
	}
	
	public  boolean hasEmpSalarys() {
		return total > 0;
	}
	
	public int getTotal() {
		return total;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public List<EmpSalary> getContent() {
		return content;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public int getStartPage() {
		return startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public CountPay getCountPay() {
		return countPay;
	}
	
	/*
	 * public int getMonth_Total_Salary(){ return mts; }
	 * 
	 * public int getMonth_Ded_Amount() {
	 * 
	 * return mda; }
	 * 
	 * public int getTotal_Ded() { return mts_mda; }
	 * 
	 * 
	 * public int getCountSalary() { return countSalary; }
	 */
	
	
}
