<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 全社員情報の照会</title> <!-- 페이지 제목 / ページのタイトル -->
<script>
    // 행 클릭 시 수정 페이지로 이동하는 함수 行クリック時に修正ページに移動する関数
    function goToEdit(empId) {
        window.location.href = "${pageContext.request.contextPath}/employee/record.do?empId=" + empId + "&action=view"; // 수정 페이지로 이동
    }
</script>
</head>
<body>
    <h2>全社員情報の照会</h2> <!-- 제목 / タイトル -->
    
    <c:if test="${empty pagingUtil.items}"> <!-- 사원 목록이 비어있을 경우 -->
        <p> 出力がありません。</p> <!-- 경고 메시지 / 警告メッセージ -->
    </c:if>

    <table border="1"> <!-- 테이블 시작 / テーブルの開始 -->
        <thead>
			<tr > 
                <th> 社員ID</th> <!-- 사원 ID / 社員ID -->
                <th>ユーザーID</th> <!-- 사용자 ID / ユーザーID -->
                <th> 部署名</th> <!-- 부서명 / 部署名 -->
                <th> 職位</th> <!-- 직위 / 職位 -->
                <th> 入社日</th> <!-- 입사일 / 入社日 -->
                <th>金融機関</th> <!-- 금융기관 / 金融機関 -->
                <th>口座番号</th> <!-- 계좌번호 / 口座番号 -->
           
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${pagingUtil.items}"> <!-- 사원 목록을 반복 / 社員リストを反復 -->
                <tr onclick="goToEdit(${employee.empId})" style="cursor:pointer;" >
                    <td>${employee.empId}</td> <!-- 사원 ID 출력 / 社員IDを表示 -->
                    <td>${employee.userId}</td> <!-- 사용자 ID 출력 / ユーザーIDを表示 -->
                    <td>${employee.dept}</td> <!-- 부서명 출력 / 部署名を表示 -->
                    <td>${employee.position}</td> <!-- 직위 출력 / 職位を表示 -->
                    <td>${employee.hireDate}</td> <!-- 입사일 출력 / 入社日を表示 -->
                    <td>${employee.financialCompany}</td> <!-- 금융기관 출력 / 金融機関を表示 -->
                    <td>${employee.accountNumber}</td> <!-- 계좌번호 출력 / 口座番号を表示 -->
                
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 페이징 네비게이션 / ページングナビゲーション -->
    <div>
        <c:if test="${pagingUtil.currentPage > 1}"> <!-- 현재 페이지가 1보다 클 경우 -->
            <a href="${pagingUtil.createPageLink(pagingUtil.currentPage - 1)}"> 前へ</a> <!-- 이전 페이지 링크 / 前のページリンク -->
        </c:if>

        <c:forEach var="i" begin="1" end="${pagingUtil.totalPages}"> <!-- 페이지 수 만큼 반복 -->
            <c:if test="${i == pagingUtil.currentPage}"> <!-- 현재 페이지일 경우 -->
                <strong>${i}</strong> <!-- 현재 페이지 강조 / 現在のページを強調 -->
            </c:if>
            <c:if test="${i != pagingUtil.currentPage}"> <!-- 현재 페이지가 아닐 경우 -->
                <a href="${pagingUtil.createPageLink(i)}">${i}</a> <!-- 페이지 링크 / ページリンク -->
            </c:if>
        </c:forEach>

        <c:if test="${pagingUtil.currentPage < pagingUtil.totalPages}"> <!-- 현재 페이지가 마지막 페이지보다 작을 경우 -->
            <a href="${pagingUtil.createPageLink(pagingUtil.currentPage + 1)}">次へ</a> <!-- 다음 페이지 링크 / 次のページリンク -->
        </c:if>
    </div>
</body>