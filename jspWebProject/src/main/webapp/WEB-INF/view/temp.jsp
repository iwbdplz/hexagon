<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
    function loadEmployeeDetails(empId) {
        // 서버에서 해당 사원의 데이터를 가져옴
        fetch(`getEmployeeDetails.do?emp_id=${empId}`)
            .then(response => response.json())
            .then(data => {
                // 받아온 데이터를 salaryForm의 필드에 설정
                document.getElementById("basicSalary").value = data.basicSalary || 0;
                document.getElementById("bonus").value = data.bonus || 0;
                document.getElementById("pension").value = data.pension || 0;
                document.getElementById("healthInsurance").value = data.healthInsurance || 0;
                document.getElementById("totalSalary").value = data.totalSalary || 0;
                document.getElementById("totalDeduction").value = data.totalDeduction || 0;
                document.getElementById("actualPayment").value = data.actualPayment || 0;

                // 사원번호와 salary_date도 폼에 설정
                document.getElementById("empId").value = empId;
                document.getElementById("salaryDate").value = data.salaryDate || "";
                
                // 계산 함수 호출 (필요시)
                calculate();
            })
            .catch(error => {
                console.error('Error loading employee details:', error);
                alert('사원 정보를 불러오는 데 실패했습니다.');
            });
    }

    function calculate() {
        // 필요 시 실시간으로 총액과 공제액 계산하는 로직
        const basicSalary = parseFloat(document.getElementById("basicSalary").value) || 0;
        const bonus = parseFloat(document.getElementById("bonus").value) || 0;
        const pension = parseFloat(document.getElementById("pension").value) || 0;
        const healthInsurance = parseFloat(document.getElementById("healthInsurance").value) || 0;

        // 지급 총액 계산
        const totalSalary = basicSalary + bonus;
        document.getElementById("totalSalary").value = totalSalary;

        // 공제 총액 계산
        const totalDeduction = pension + healthInsurance;
        document.getElementById("totalDeduction").value = totalDeduction;

        // 실지급액 계산
        const actualPayment = totalSalary - totalDeduction;
        document.getElementById("actualPayment").value = actualPayment;
    }
</script>

</body>
</html>