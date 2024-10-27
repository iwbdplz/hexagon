<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 업데이트 테스트</title>
<script>
	// 급여 합계 계산
	function calculateTotalSalary() {
		let salaryFields = ["basicSalary", "foodexp", "childcareAllow", "jobAllow", "vehicleCost", "serviceAllow", "onDutyAllow", "bonus", "holidayAllow"];
		let totalSalary = 0;
		salaryFields.forEach(function(field) {
			let value = parseFloat(document.getElementsByName(field)[0].value) || 0;
			totalSalary += value;
		});
		document.getElementsByName("totalSalary")[0].value = totalSalary;
	}

	// 공제 합계 계산
	function calculateTotalDeduction() {
		let deductionFields = ["nationalPension", "healthIns", "longTerm", "employment", "incomeTax", "localIncomeTax", "funeralExp"];
		let totalDeduction = 0;
		deductionFields.forEach(function(field) {
			let value = parseFloat(document.getElementsByName(field)[0].value) || 0;
			totalDeduction += value;
		});
		document.getElementsByName("deductionAmount")[0].value = totalDeduction;
	}

	// 두 함수 모두 실행하는 함수
	function calculateTotals() {
		calculateTotalSalary();
		calculateTotalDeduction();
	}
</script>
</head>
<body>
	<!-- 검색 폼 -->
	<form action="updateSalary.do" method="get">
		<input type="text" name="emp_id" placeholder="사원 ID 입력">
		<input type="text" name="salary_date" placeholder="날짜 입력"> <!-- 날짜 입력 필드 추가 -->
		<button type="submit">검색</button>
	</form>

	<!-- 기존 데이터를 사용하기 위한 변수 설정 -->
	<c:set var="paymentItems" value="${payAndDed.paymentItems}" />
	<c:set var="deductionItems" value="${payAndDed.deductionItems}" />

	<!-- 급여 및 공제 항목 입력 폼 -->
	<h2>급여 및 공제 항목 입력</h2>
	<form action="updateSalary.do" method="post">
		<table border="1">
			<input type="text" name="empId" value="${payAndDed.emp_id}"/>
			<input type="text" name="salaryDate" value="${payAndDed.salary_date}"/>
			<tr>
				<td>기본급</td>
				<td><input type="text" name="basicSalary" value="${paymentItems.basicSalary}" oninput="calculateTotals()"> 원</td>
				<td>국민연금</td>
				<td><input type="text" name="nationalPension" value="${deductionItems.nationalPension}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>식비</td>
				<td><input type="text" name="foodexp" value="${paymentItems.foodexp}" oninput="calculateTotals()"> 원</td>
				<td>건강보험</td> 
				<td><input type="text" name="healthIns" value="${deductionItems.healthIns}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>보육수당</td>
				<td><input type="text" name="childcareAllow" value="${paymentItems.childcareAllow}" oninput="calculateTotals()"> 원</td>
				<td>장기요양비</td>
				<td><input type="text" name="longTerm" value="${deductionItems.longTerm}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>직책수당</td>
				<td><input type="text" name="jobAllow" value="${paymentItems.jobAllow}" oninput="calculateTotals()"> 원</td>
				<td>고용보험</td>
				<td><input type="text" name="employment" value="${deductionItems.employment}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>차량유지비</td>
				<td><input type="text" name="vehicleCost" value="${paymentItems.vehicleCost}" oninput="calculateTotals()"> 원</td>
				<td>소득세</td>
				<td><input type="text" name="incomeTax" value="${deductionItems.incomeTax}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>근속수당</td>
				<td><input type="text" name="serviceAllow" value="${paymentItems.serviceAllow}" oninput="calculateTotals()"> 원</td>
				<td>지방소득세</td>
				<td><input type="text" name="localIncomeTax" value="${deductionItems.localIncomeTax}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>당직수당</td>
				<td><input type="text" name="onDutyAllow" value="${paymentItems.onDutyAllow}" oninput="calculateTotals()"> 원</td>
				<td>상조회비</td>
				<td><input type="text" name="funeralExp" value="${deductionItems.funeralExp}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>상여금</td>
				<td><input type="text" name="bonus" value="${paymentItems.bonus}" oninput="calculateTotals()"> 원</td>
				<td>휴일수당</td>
				<td><input type="text" name="holidayAllow" value="${paymentItems.holidayAllow}" oninput="calculateTotals()"> 원</td>
			</tr>
			<tr>
				<td>총급여액</td>
				<td><input type="text" name="totalSalary" value="${paymentItems.totalSalary}" readonly> 원</td>
				<td>총공제액</td>
				<td><input type="text" name="deductionAmount" value="${deductionItems.deduction_amount}" readonly> 원</td>
			</tr>
		</table>
		<button type="submit">업데이트</button>
	</form>
</body>
</html>
