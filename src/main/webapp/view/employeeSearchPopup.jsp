<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>사원 검색 결과</title>
    <script>
        // 사원 정보를 선택하는 함수
        function selectEmployee(empId, userName, hireDate, retirementDate, employmentStatus) {
            var recordCard = window.opener.document.getElementById("recordCard");
            recordCard.innerHTML = "사원 ID: " + empId + "<br>성명: " + userName + "<br>입사일: " + hireDate + "<br>퇴사일: " + (retirementDate != null ? retirementDate : '현재 재직중') + "<br>상태: " + employmentStatus;
            window.close();
        }
    </script>
</head>
<body>
  <h1>社員検索結果</h1> <!-- 사원 검색 결과 / 社員検索結果 -->
<table border="1">
    <thead>
        <tr>
            <th>社員ID</th> <!-- 사원 ID / 社員ID -->
            <th>氏名</th> <!-- 성명 / 性名 -->
            <th>部署</th> <!-- 부서 / 部署 -->
            <th>職位</th> <!-- 직위 / 職位 -->
            <th>入社日</th> <!-- 입사일 / 入社日 -->
            <th>退社日</th> <!-- 퇴사일 / 退社日 -->
            <th>状態</th> <!-- 상태 / 状態 -->
            <th>選択</th> <!-- 선택 / 選択 -->
        </tr>
    </thead>
    <tbody>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.empId}</td> <!-- 사원 ID 출력 / 社員IDを表示 -->
                <td>${employee.userName}</td> <!-- 성명 출력 / 性名を表示 -->
                <td>${employee.dept}</td> <!-- 부서 출력 / 部署を表示 -->
                <td>${employee.position}</td> <!-- 직위 출력 / 職位を表示 -->
                <td>${employee.hireDate}</td> <!-- 입사일 출력 / 入社日を表示 -->
                <td>${employee.retirementDate != null ? employee.retirementDate : '現在在職中'}</td> <!-- 퇴사일 출력 / 退社日を表示 -->
                <td>${employee.employmentStatus}</td> <!-- 상태 출력 / 状態を表示 -->
                <td>
                    <button onclick="selectEmployee('${employee.empId}', '${employee.userName}', '${employee.hireDate}', '${employee.retirementDate}', '${employee.employmentStatus}')">選択</button> <!-- 선택 버튼 / 選択ボタン -->
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
