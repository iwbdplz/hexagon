<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트 화면</title>
</head>
<body>
	<table border="1">
		<form action="listPayroll.do" method="get">
			<td><select name="year">
					<option value="">년도 선택</option>
					<c:forEach var="year" begin="2005" end="2024">
						<option value="${year}"
							<c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
					</c:forEach>
			</select></td>
			<td>
				<button type="submit">조회</button>
			</td>
		</form>
		<tr>
			<td>귀속연월</td>
			<td>정산기간</td>
			<td>지급일</td>
			<td>인원</td>
			<td>지급총액</td>
			<td>공제총액</td>
			<td>실지급액</td>
			<td></td>
		</tr>
		<c:forEach var="payroll" items="${payrollList}">
			<tr>
				<td>${payroll.yearMonth}</td>
				<td>${payroll.settleDate}</td>
				<td>${payroll.payDate}</td>
				<td>${payroll.salary_count}</td>
				<td>${payroll.total_salary}</td>
				<td>${payroll.ded_amount}</td>
				<td>${payroll.actual_payment}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
</body>
</html>