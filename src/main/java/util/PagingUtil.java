package util;

import java.util.List;

public class PagingUtil<T> {
	 private int currentPage;       // 현재 페이지 現在のページ
	    private int totalItems;        // 전체 아이템 수 全体アイテム数
	    private int pageSize;          // 페이지당 아이템 수 ページ当たりのアイテム数
	    private int totalPages;        // 전체 페이지 수  全ページ数 
	    private List<T> items;         // 현재 페이지의 아이템 리스트 現在ページのアイテムリスト

	    public PagingUtil(int currentPage, int totalItems, int pageSize, List<T> items) {
	        this.currentPage = currentPage;
	        this.totalItems = totalItems;
	        this.pageSize = pageSize;
	        this.items = items;
	        this.totalPages = (int) Math.ceil((double) totalItems / pageSize); // 전체 페이지 계산
	    }

		public int getCurrentPage() {
			return currentPage;
		}

		public int getTotalItems() {
			return totalItems;
		}

		public int getPageSize() {
			return pageSize;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public List<T> getItems() {
			return items;
		}
	    
		   public String createPageLink(int page) {
		        return "?page=" + page; // 쿼리 파라미터로 페이지 번호를 추가
		    }


}
