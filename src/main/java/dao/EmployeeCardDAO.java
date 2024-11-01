package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import model.EmployeeCard;

// 사원 정보를 데이터베이스에서 처리하는 클래스
// 社員情報をデータベースで処理するクラス
public class EmployeeCardDAO {
    // 사원 검색 메서드
    // 社員検索メソッド
    public List<EmployeeCard> searchEmployees(String dept, String position, String employeeType, String keyword) throws Exception {
        List<EmployeeCard> employees = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT e.emp_id, u.user_name, u.email, u.phone_number, "
            + "CASE WHEN (SELECT COUNT(dw.work_id) FROM daily_work_record dw WHERE dw.emp_id = e.emp_id) > 0 "
            + "THEN '일용직' ELSE '정규직' END AS employment_type, "
            + "e.dept, e.position, "
            + "(SELECT r.retirement_date FROM retirement r WHERE r.emp_id = e.emp_id) AS retirement_date, "
            + "(SELECT r.retirement_reason FROM retirement r WHERE r.emp_id = e.emp_id) AS retirement_reason, "
            + "(SELECT rs.retirement_salary FROM retirement_salary rs WHERE rs.emp_id = e.emp_id) AS retirement_payment, "
            + "e.hire_date, "
            + "CASE WHEN EXISTS (SELECT 1 FROM retirement rt WHERE rt.emp_id = e.emp_id) "
            + "THEN '퇴직' ELSE '재직' END AS employment_status "
            + "FROM employee e "
            + "JOIN user_info u ON e.user_id = u.user_id "
            + "LEFT JOIN deductions d ON e.emp_id = d.emp_id "
            + "WHERE 1=1"
        );

        // 조건 추가
        // 条件追加
        if (dept != null && !dept.isEmpty()) {
            sql.append(" AND e.dept = ?"); // 부서 조건
            // 部門条件
        }
        if (position != null && !position.isEmpty()) {
            sql.append(" AND e.position = ?"); // 직위 조건
            // 職位条件
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (u.user_name LIKE ? OR e.position LIKE ?)"); // 키워드 조건
            // キーワード条件
        }

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int idx = 1; // 파라미터 인덱스 초기화

            if (dept != null && !dept.isEmpty()) {
                pstmt.setString(idx++, dept); // 부서 조건 설정
                // 部門条件を設定
            }
            if (position != null && !position.isEmpty()) {
                pstmt.setString(idx++, position); // 직위 조건 설정
                // 職位条件を設定
            }
            if (keyword != null && !keyword.isEmpty()) {
                pstmt.setString(idx++, "%" + keyword + "%"); // 이름에서 검색
                // 名前から検索
                pstmt.setString(idx++, "%" + keyword + "%"); // 직위에서 검색
                // 職位から検索
            }

            // 쿼리 실행
            // クエリ実行
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(new EmployeeCard(
                    rs.getInt("emp_id"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("employment_type"),
                    rs.getString("dept"),
                    rs.getString("position"),
                    rs.getDate("retirement_date"),
                    rs.getString("retirement_reason"),
                    rs.getDouble("retirement_payment"),
                    rs.getString("employment_status"),
                    rs.getDate("hire_date")
                ));
            }
        }
        return employees; // 검색된 사원 목록 반환
        // 検索された社員リストを返す
    }
}
