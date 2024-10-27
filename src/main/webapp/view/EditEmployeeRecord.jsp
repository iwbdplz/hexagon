<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>인사 정보 수정</title> <!-- 페이지 제목 / ページのタイトル -->
</head>
<body>
	<h2>人事情報修正</h2> <!-- 제목 / タイトル -->
<form action="${pageContext.request.contextPath}/employee/record.do" method="post"> <!-- 수정 처리할 액션 지정 / 修正処理のアクションを指定 -->
    <input type="hidden" name="empId" value="${record.empId}" /> <!-- 사원 ID를 숨김 필드로 / 社員IDを隠しフィールドとして -->
    <input type="hidden" name="action" value="update" />
    
    <label>名前:</label> <!-- 이름 / 名前 -->
    <input type="text" name="user_id" value="${record.userId}" required />  <!-- 이름 입력 필드 / 名前入力フィールド -->
    <br/>

    <label>部署:</label> <!-- 부서 / 部署 -->
    <input type="text" name="department" value="${record.department}" required /> <!-- 부서 입력 필드 / 部署入力フィールド -->
    <br/>

    <label>職位:</label> <!-- 직위 / 職位 -->
    <input type="text" name="position" value="${record.position}" required /> <!-- 직위 입력 필드 / 職位入力フィールド -->
    <br/>

    <label>入社日:</label> <!-- 입사일 / 入社日 -->
    <input type="date" name="hireDate" value="${record.hireDate}" required /> <!-- 입사일 입력 필드 / 入社日入力フィールド -->
    <br/>

    <label>金融機関:</label> <!-- 금융기관 / 金融機関 -->
    <input type="text" name="financialCompany" value="${record.financialCompany}" required /> <!-- 금융기관 입력 필드 / 金融機関入力フィールド -->
    <br/>

    <label>口座番号:</label> <!-- 계좌번호 / 口座番号 -->
    <input type="text" name="accountNumber" value="${record.accountNumber}" required /> <!-- 계좌번호 입력 필드 / 口座番号入力フィールド -->
    <br/>

    <button type="submit">修正する</button> <!-- 수정 버튼 / 修正ボタン -->
</form>
<a href="${pageContext.request.contextPath}/employee/record.do?empId=${record.empId}&action=view">キャンセル</a> <!-- 취소 링크 / キャンセルリンク -->
</body>
</html>