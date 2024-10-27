<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>인사 정보 조회</title> <!-- 페이지 제목 / ページのタイトル -->
</head>
<body>
	<h2>人事情報</h2> <!-- 제목 / タイトル -->
<table>
    <tr>
        <th>社員ID</th> <!-- 사원 ID / 社員ID -->
        <td>${record.empId}</td> <!-- 사원 ID 출력 / 社員IDを表示 -->
    </tr>
    <tr>
        <th>名前</th> <!-- 이름 / 名前 -->
        <td>${record.name}</td> <!-- 이름 출력 / 名前を表示 -->
    </tr>
    <tr>
        <th>部署</th> <!-- 부서 / 部署 -->
        <td>${record.department}</td> <!-- 부서 출력 / 部署を表示 -->
    </tr>
    <tr>
        <th>職位</th> <!-- 직위 / 職位 -->
        <td>${record.position}</td> <!-- 직위 출력 / 職位を表示 -->
    </tr>
    <tr>
        <th>入社日</th> <!-- 입사일 / 入社日 -->
        <td>${record.hireDate}</td> <!-- 입사일 출력 / 入社日を表示 -->
    </tr>
    <tr>
        <th>金融機関</th> <!-- 금융기관 / 金融機関 -->
        <td>${record.financialCompany}</td> <!-- 금융기관 출력 / 金融機関を表示 -->
    </tr>
    <tr>
        <th>口座番号</th> <!-- 계좌번호 / 口座番号 -->
        <td>${record.accountNumber}</td> <!-- 계좌번호 출력 / 口座番号を表示 -->
    </tr>
</table>

<a href="${pageContext.request.contextPath}/employee/record.do?empId=${record.empId}&action=edit">修正</a> <!-- 수정 링크 / 修正リンク -->

<form action="employee/record.do" method="post"> <!-- 삭제 처리할 액션 지정 / 削除処理のアクションを指定 -->
    <input type="hidden" name="empId" value="${record.empId}" /> <!-- 사원 ID를 숨김 필드로 / 社員IDを隠しフィールドとして -->
    <input type="hidden" name="action" value="delete" /> <!-- 삭제 액션 지정 / 削除アクションを指定 -->
    <button type="submit">削除</button> <!-- 삭제 버튼 / 削除ボタン -->
</form>

<a href="employee/list.do">一覧に戻る</a> <!-- 목록으로 돌아가기 링크 / 一覧に戻るリンク -->
</body>
</html>