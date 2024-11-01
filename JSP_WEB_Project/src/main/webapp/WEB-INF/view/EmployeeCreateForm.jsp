<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員登録</title>
</head>
<body>

	<!-- 社員登録フォーム -->
	<!-- 사원등록 form -->
	<form action="create.do" method="post">
		<table border="2" align="center">
			<tr>
				<td>氏名:<input type="text" name="username"> <c:if
						test="${errors.username}">
						<br>氏名を入力してください。</c:if>
				</td>
				<td>Eメール:<input type="text" name="email"> <c:if
						test="${errors.email}">
						<br>Eメールを入力してください。</c:if>
				</td>
			</tr>

			<tr>
				<td>携帯番号:<input type="text" name="phoneNum"> <c:if
						test="${errors.phoneNum}">
						<br>携帯番号を入力してください。</c:if>
				</td>
				<td>部署:<input type="text" name="dept"> <c:if
						test="${errors.dept}">
						<br>部署を入力してください。</c:if>
				</td>
			</tr>

			<tr>
				<td>職位:<input type="text" name="position"> <c:if
						test="${errors.position}">
						<br>職位を入力してください。</c:if>
				</td>
				<td>入社日:<input type="date" name="hiredAt"> <c:if
						test="${errors.hiredAt}">
						<br>入社日を入力してください。</c:if>
				</td>
			</tr>

			<tr>
				<td>使用銀行:<input type="text" name="financialCo"> <c:if
						test="${errors.financialCo}">
						<br>使用銀行を入力してください。</c:if>
				</td>
				<td>口座番号:<input type="text" name="accountNum"> <c:if
						test="${errors.accountNum}">
						<br>口座番号を入力してください。</c:if>
				</td>
			</tr>
		</table>
		<br>
		<div align="center">
			<input style="width: 80px; height: 30px; font-size: 18px;"
				type="submit" value="登録">
			<button style="width: 80px; height: 30px; font-size: 18px;"
				type="button"
				onClick="location.href='/JSP_WEB_Project/user/list.do'">取消</button>
		</div>
	</form>

</body>
</html>