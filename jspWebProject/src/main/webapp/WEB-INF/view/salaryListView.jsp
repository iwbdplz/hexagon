<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>급여 및 공제 항목 입력</title>
    <style>
        /* 레이아웃 구성을 위한 기본 스타일 */
        .container {
            display: flex;
        }
        .left, .right {
            width: 50%;
            padding: 10px;
            border: 1px solid #ddd;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, td, th {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- 왼쪽 영역: 첫 번째 코드 -->
        <div class="left">
            <!-- 검색 폼 -->
            <form action="updateSalary.do" method="get">
                <input type="text" name="emp_id" placeholder="사원 ID 입력">
                <button type="submit">검색</button>
            </form>

            <!-- 급여 및 공제 항목 입력 폼 -->
            <h2>급여 및 공제 항목 입력</h2>
            <form action="updateSalary.do" method="post">
                <table>
                    <tr>
                        <td>기본급</td>
                        <td><input type="text" name="basicSalary" value="${paymentItems.basicSalary}"> 원</td>
                        <td>국민연금</td>
                        <td><input type="text" name="nationaPension" value="${deductionItems.nationaPension}"> 원</td>
                    </tr>
                    <!-- 추가된 급여 및 공제 항목들 -->
                    <tr>
                        <td>식비</td>
                        <td><input type="text" name="foodexp" value="${paymentItems.foodexp}"> 원</td>
                        <td>건강보험</td>
                        <td><input type="text" name="healthIns" value="${deductionItems.healthIns}"> 원</td>
                    </tr>
                    <tr>
                        <td>총급여액</td>
                        <td>${paymentItems.totalSalary} 원</td>
                        <td>총공제액</td>
                        <td>${deductionItems.deduction_amount} 원</td>
                    </tr>
                </table>
                <button type="submit">업데이트</button>
            </form>

            <!-- 사원 목록 테이블 -->
            <table>
                <tr>
                    <td>사원 번호</td>
                    <td>성명</td>
                    <td>부서</td>
                    <td>날짜</td>
                </tr>
                <c:forEach var="empSalary" items="${empSalaryPage.content}">
                    <tr>
                        <td><a href="#" onclick="loadEmployeeDetails('${empSalary.emp_id}'); return false;">${empSalary.emp_id}</a></td>
                        <td>${empSalary.user_name}</td>
                        <td>${empSalary.emp_dept}</td>
                        <td>${empSalary.salary_date}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- 오른쪽 영역: 두 번째 코드 -->
        <div class="right">
            <form id="salaryForm" action="updateSalary.do" method="post">
                <table>
                    <input type="hidden" name="emp_id" id="empId" value="">
                    <input type="hidden" name="salary_date" id="salaryDate" value="">    
                    <tr>
                        <td>기본급</td>
                        <td><input type="text" name="basicSalary" id="basicSalary" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>상여금</td>
                        <td><input type="text" name="bonus" id="bonus" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>지급 총액</td>
                        <td><input type="text" name="totalSalary" id="totalSalary" value="0" readonly></td>
                    </tr>
                    <tr>
                        <td>국민연금</td>
                        <td><input type="text" name="pension" id="pension" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>건강보험</td>
                        <td><input type="text" name="healthInsurance" id="healthInsurance" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>공제 총액</td>
                        <td><input type="text" name="totalDeduction" id="totalDeduction" value="0" readonly></td>
                    </tr>
                    <tr>
                        <td>실지급액</td>
                        <td><input type="text" name="actualPayment" id="actualPayment" value="0" readonly></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <button type="submit">저장</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <script>
        function confirmDelete() {
            return confirm('삭제하시겠습니까?');
        }
        
        function calculate() {
            let basicSalary = parseFloat(document.getElementById("basicSalary").value) || 0;
            let bonus = parseFloat(document.getElementById("bonus").value) || 0;
            document.getElementById("totalSalary").value = basicSalary + bonus;

            let pension = parseFloat(document.getElementById("pension").value) || 0;
            let healthInsurance = parseFloat(document.getElementById("healthInsurance").value) || 0;
            document.getElementById("totalDeduction").value = pension + healthInsurance;

            let totalSalary = basicSalary + bonus;
            let totalDeduction = pension + healthInsurance;
            document.getElementById("actualPayment").value = totalSalary - totalDeduction;
        }

        function loadEmployeeDetails(empId) {
            fetch(`getEmployeeDetails.do?emp_id=${empId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("empId").value = empId;
                    document.getElementById("salaryDate").value = data.salaryDate || "";
                    document.getElementById("basicSalary").value = data.basicSalary || 0;
                    document.getElementById("bonus").value = data.bonus || 0;
                    document.getElementById("pension").value = data.pension || 0;
                    document.getElementById("healthInsurance").value = data.healthInsurance || 0;
                    calculate();
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
</body>
</html>
