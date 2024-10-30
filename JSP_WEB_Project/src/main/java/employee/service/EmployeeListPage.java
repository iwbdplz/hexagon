package employee.service;

import java.util.List;

public class EmployeeListPage {

	private int total;  // データの総数 데이터의 총 갯수
	private int currentPage;  //　現在表示するページ 현재 표시중인 페이지
	private List<EmployeeWithUserInfoAndRetiredDate> content;  // 表示するデータ 표시할 데이터
	private int totalPages; // 総ページ 총 페이지
	private int startPage;  // 表示するページの初ページ 표시할 페이지
	private int endPage;  // 表示するページの最後ページ 표시할 페이지의 마지막 페이지
	
	public EmployeeListPage(int total, int currentPage, int size, List<EmployeeWithUserInfoAndRetiredDate> content) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		//　情報の総数に従ってページの数を計算してページングを提供する。
		// 정보의 총 개수에 따라 페이지의 수를 계산하여 페이징을 제공한다.
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if (endPage > totalPages) endPage = totalPages;
		}
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<EmployeeWithUserInfoAndRetiredDate> getContent() {
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
	
	public boolean hasNoData() {
		return total == 0;
	}

	public boolean hasData() {
		return total > 0;
	}
	
}
