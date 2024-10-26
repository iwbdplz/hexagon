<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報修正</title>
</head>
<body>

<form action="update.do" method="post">
	<input type="hidden" type="text" name="userId" value="${data.userId}">
	<table border="2">
	
		<tr>
			<td>
				氏名:<input type="text" name="username" value="${data.username}">
				<c:if test="${errors.username}"><br>氏名を入力してください。</c:if>
			</td>
			<td>
				Eメール:<input type="text" name="email" value="${data.email}">
				<c:if test="${errors.email}"><br>Eメールを入力してください。</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				携帯番号:<input type="text" name="phoneNum" value="${data.phoneNum}">
				<c:if test="${errors.phoneNum}"><br>携帯番号を入力してください。</c:if>
			</td>
			<td>
				部署:<input type="text" name="dept" value="${data.dept}">
				<c:if test="${errors.dept}"><br>部署を入力してください。</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				職位:<input type="text" name="position" value="${data.position}">
				<c:if test="${errors.position}"><br>職位を入力してください。</c:if>
			</td>
			<td>
				入社日:<input type="date" name="hiredAt" value="${data.hiredAt}">
				<c:if test="${errors.hiredAt}"><br>入社日を入力してください。</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				使用銀行:<input type="text" name="financialCo" value="${data.financialComapany}">
				<c:if test="${errors.financialComapany}"><br>使用銀行を入力してください。</c:if>
			</td>
			<td>
				口座番号:<input type="text" name="accountNum" value="${data.accountNum}">
				<c:if test="${errors.accountNum}"><br>口座番号を入力してください。</c:if>
			</td>
		</tr>
		
		<tr>
			<td>
				総給料:<input type="text" name="totalSalary" readonly value="${data.totalSalary}">
			</td>
			<td>
				退社日:<input type="date" name="retirementDate" readonly value="${data.retirementDate}">
			</td>
		</tr>
	</table>
	<br>
	<div>
		<input type="submit" value="修正"> 
		<button type="button" onClick="location.href='/JSP_WEB_Project/user/list.do'">取消</button>
	</div>
	
	
</form>
</body>
</html>