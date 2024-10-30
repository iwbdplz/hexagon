package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeDao;
import jdbc.connection.ConnectionProvider;

//社員の情報リスト照会のサービスロジックを提供するクラス。
// 사원의 정보 리스트를 조회하는 서비스 로직을 제공하는 클래스
// 検索語がある時とない時を区分して提供。
// 검색어가 있을 때와 없을 때를 구분하여 제공
public class EmployeeListService {

	private final EmployeeDao employeeDao = new EmployeeDao();
	
	private int size = 10;

	// 検索語がない場合
	// 검색어가 없는 경우
	public EmployeeListPage getEmployeeListPage(int pageNum){
		int firstRow = 0;
		int endRow = 0;
		List<EmployeeWithUserInfoAndRetiredDate> content = null;
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = employeeDao.selectCount(conn);
			if (total > 0){
				firstRow = (pageNum-1) * size + 1;
				endRow = firstRow + size - 1;
				content = employeeDao.selectList(conn,firstRow,endRow);
			}
			return new EmployeeListPage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 検索語がある場合
	// 검색어가 있는 경우
	public EmployeeListPage getEmployeeListPageWithKeyword(int pageNum, String keyword, String searchBy){
		int firstRow = 0;
		int endRow = 0;
		List<EmployeeWithUserInfoAndRetiredDate> content = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = employeeDao.selectCount(conn);
			if (total > 0){
				firstRow = (pageNum-1) * size + 1;
				endRow = firstRow + size - 1;
				content = employeeDao.selectByKeyword(conn,firstRow,endRow, keyword, searchBy);
			}
			return new EmployeeListPage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
