package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeCardDAO;
import model.EmployeeCard;

// 사원 검색 요청을 처리하는 컨트롤러
// 社員検索リクエストを処理するコントローラー
public class EmployeeSearchController implements CommandHandler {
    private EmployeeCardDAO employeeCardDAO = new EmployeeCardDAO(); // DAO 객체 생성
    // DAOオブジェクトを生成

    // HTTP 요청 처리 메서드
    // HTTPリクエストを処理するメソッド
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 요청 파라미터에서 부서, 직위, 고용 유형, 키워드 가져옴
        // リクエストパラメータから部門、職位、雇用タイプ、キーワードを取得
        String dept = req.getParameter("dept");
        String position = req.getParameter("position");
        String employmentType = req.getParameter("employmentType");
        String keyword = req.getParameter("keyword");

        // DAO를 통해 사원 정보 검색
        // DAOを使用して社員情報を検索
        List<EmployeeCard> employees = employeeCardDAO.searchEmployees(dept, position, employmentType, keyword);

        // 검색된 사원 정보를 요청 속성에 설정
        // 検索された社員情報をリクエスト属性に設定
        req.setAttribute("employees", employees);
        
        // 결과 JSP 페이지로 포워딩
        // 結果を表示するJSPページにフォワード
        return "/view/employeeSearchPopup.jsp";
    }
}
