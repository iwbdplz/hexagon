package attendance.service;

import java.util.List;

import attendance.model.AttendanceRecord;
import attendance.model.LeaveAttendanceSettings;



public class LeaveAttendanceListPage {

	
	private int total; //　全記録数, 전체 기록 수 
	private int currentPage; //　現在ページナンバー　, 현재페이지 번호 
	private List<LeaveAttendanceSettings> content; //　休暇記録, 휴가기록 
	private int totalPages; //　全ページ数, 전체 페이지 수 
	private int startPage; //　最初ページ, 첫 페이지 
	private int endPage; //　最後ページ, 마지막 페이지 

	
	
	//休暇照会・記録ページをページングする。
	//휴가조회/기록 페이지를 페이징한다. 
	public LeaveAttendanceListPage(int total, int currentPage, int size, List<LeaveAttendanceSettings> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		//休暇記録がない場合、ページ数は０になる。
		//휴가기록이 없는 경우, 페이지수는 0이 된다. 
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
			
		//全休暇記録をサイズで割り、余りがある場合、ページを１足す。	
		//전체 휴가기록을 사이즈로 나누어 나머지가 있는 경우, 페이지를 1 더한다. 
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
	
	//休暇記録がない場合、TRUEを返却する。
	//휴가기록이 없는 경우, TRUE를 반환한다. 
	public boolean hasNoLeaveAttendance() {
		return total == 0;
	}
	
	//休暇記録がある場合、TRUEを返却する。
	//휴가기록이 있는 경우, TRUE를 반환한다. 
	public boolean hasLeaveAttendance() {
		return total > 0;
	}


	public int getTotal() {
		return total;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public List<LeaveAttendanceSettings> getContent() {
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

