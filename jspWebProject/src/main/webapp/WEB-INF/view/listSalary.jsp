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
		<form action="list.do" method="get">
			<td><select name="year">
					<option value="">년도 선택</option>
					<c:forEach var="year" begin="2005" end="2024">
						<option value="${year}"
							<c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
					</c:forEach>
			</select></td>
			<td><select name="month">
					<option value="">월 선택</option>
					<c:forEach var="month" begin="1" end="12">
						<option value="${month}"
							<c:if test="${month == selectedMonth}">selected</c:if>>${month}월</option>
					</c:forEach>
			</select></td>
			<td>
				<button type="submit">조회</button>
			</td>
		</form>
		<form action="insertSalary.do" method="get">
			<input type="text" name="emp_id">
			<button type="submit">생성</button>
		</form>
		<tr>
			<td>사원 번호</td>
			<td>성명</td>
			<td>부서</td>
			<td>날짜</td>
			<td>지급총액</td>
			<td>공제총액</td>
			<td>실지급액</td>
			<td></td>
		</tr>
		<c:if test="${EmpSalaryPage.hasNoEmpSalarys()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>

		<c:forEach var="empSalary" items="${empSalaryPage.content}">
			<tr>
				<!--<td>${empSalary.emp_id}</td>  -->
				 <td><a href="#" onclick="loadEmployeeDetails('${empSalary.emp_id}'); return false;">${empSalary.emp_id}</a></td>
				<td>${empSalary.user_name}</td>
				<td>${empSalary.emp_dept}</td>
				<td>${empSalary.salary_date}</td>
				<td>${empSalary.total_salary}</td>
				<td>${empSalary.ded_amount}</td>
				<td>${empSalary.actual_payment}</td>
				<td>${empSalary.salary_date}</td>
				<td>
					<form action="deleteSalary.do" method="post"
						onsubmit="return confirmDelete();">
						<input type="hidden" name="emp_id" value="${empSalary.emp_id}">
						<input type="hidden" name="salary_date"
							value="${empSalary.salary_date}">
						<button type="submit">삭제</button>
					</form>
				</td>
			</tr>
		</c:forEach>

		<c:if test="${EmpSalaryPage.hasEmpSalarys()}">
			<tr>
				<td colspan="4"><c:if test="${EmpSalaryPage.startPage > 5}">
						<a href="list.do?pageNo=${EmpSalaryPage.startPage -5}">[이전]</a>
					</c:if> <c:forEach var="pNo" begin="${EmpSalaryPage.startPage}"
						end="${EmpSalaryPage.endPage}">
						<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach> <c:if test="${EmpSalaryPage.endPage < EmpSalaryPage.totalPages}">
						<a href="list.do?pageNo=${EmpSalaryPage.startPage + 5}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>

	</table>
	<br />
	<table border="1">
		<tr>
			<td>월 합계</td>
			<td>지급 총액</td>
			<td>공제 총액</td>
			<td>실지급 총액</td>
		</tr>
		<tr>
			<td>${empSalaryPage.countPay.countSalary}</td>
			<td>${empSalaryPage.countPay.mts}</td>
			<td>${empSalaryPage.countPay.mda}</td>
			<td>${empSalaryPage.countPay.mts_mda}</td>
		</tr>
	</table>
	<form id="salaryForm" action="updateSalary.do" method="post">
		<table border="1">
			<input type="hidden" name="emp_id" id="empId" value="">
        	<input type="hidden" name="salary_date" id="salaryDate" value="">	
			<tr>
				<td>기본급</td>
				<td><input type="text" name="basicSalary" id="basicSalary"
					value="0" oninput="calculate();"></td>
			</tr>
			<tr>
				<td>상여금</td>
				<td><input type="text" name="bonus" id="bonus" value="0"
					oninput="calculate();"></td>
			</tr>
			<tr>
				<td>지급 총액</td>
				<td><input type="text" name="totalSalary" id="totalSalary"
					value="0" readonly></td>
			</tr>
			<tr>
				<td>국민연금</td>
				<td><input type="text" name="pension" id="pension" value="0"
					oninput="calculate();"></td>
			</tr>
			<tr>
				<td>건강보험</td>
				<td><input type="text" name="healthInsurance"
					id="healthInsurance" value="0" oninput="calculate();"></td>
			</tr>
			<tr>
				<td>공제 총액</td>
				<td><input type="text" name="totalDeduction"
					id="totalDeduction" value="0" readonly></td>
			</tr>
			<tr>
				<td>실지급액</td>
				<td><input type="text" name="actualPayment" id="actualPayment"
					value="0" readonly></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit">저장</button>
				</td>
			</tr>
		</table>
	</form>
	<script>
		function confirmDelete() {
			// 확인 메시지 팝업
			return confirm('삭제하시겠습니까?');
		}
		function calculate() {
            // 기본급과 상여금 합산
            let basicSalary = parseFloat(document.getElementById("basicSalary").value) || 0;
            let bonus = parseFloat(document.getElementById("bonus").value) || 0;
            document.getElementById("totalSalary").value = basicSalary + bonus;

            // 국민연금과 건강보험 합산
            let pension = parseFloat(document.getElementById("pension").value) || 0;
            let healthInsurance = parseFloat(document.getElementById("healthInsurance").value) || 0;
            document.getElementById("totalDeduction").value = pension + healthInsurance;

            // 실지급액 계산
            let totalSalary = basicSalary + bonus;
            let totalDeduction = pension + healthInsurance;
            document.getElementById("actualPayment").value = totalSalary - totalDeduction;
        }
		function loadEmployeeDetails(empId) {
            fetch(`getEmployeeDetails.do?emp_id=${empId}`)
                .then(response => response.json())
                .then(data => {
                    // 데이터가 성공적으로 로드되면 입력 필드에 표시
                    document.getElementById("basicSalary").value = data.basicSalary || 0;
                    document.getElementById("bonus").value = data.bonus || 0;
                    document.getElementById("pension").value = data.pension || 0;
                    document.getElementById("healthInsurance").value = data.healthInsurance || 0;
                    calculate(); // 계산 함수 호출
                })
                .catch(error => console.error('Error:', error));
        }
	</script>
</body>
</html>