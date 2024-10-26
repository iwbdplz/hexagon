package attendance.service;

import java.util.List;

import attendance.model.AttendanceRecord;

public class AttendanceListPage {

	private int total; //　全記録数
	private int currentPage; //　現愛ページナンバー　
	private List<AttendanceRecord> content; //　勤怠記録
	private int totalPages; //　全ページ数
	private int startPage; //　最初ページ
	private int endPage; //　最後ページ
	
	//勤怠照会・記録ページをページングする。
	public AttendanceListPage(int total, int currentPage, int size, List<AttendanceRecord> content) {
		this.total = total; //全勤怠記録の数
		this.currentPage = currentPage;
		this.content = content; //勤怠記録データーを入れるリスト。
		
		//勤怠記録がない場合、ページ数は０になる。
		if (total == 0) { 
			totalPages = 0;
			startPage = 0;
			endPage = 0; 
			
		//全勤怠記録をサイズで割り、余りがある場合、ページを１追加する。	
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
	
	//勤怠記録がない場合、TRUEを返却する。
	public boolean hasNoAttendance() {
		return total == 0;
	}
	
	//勤怠記録がある場合、TRUEを返却する。
	public boolean hasAttendance() {
		return total > 0;
	}


	public int getTotal() {
		return total;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public List<AttendanceRecord> getContent() {
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
	
	
	
}

