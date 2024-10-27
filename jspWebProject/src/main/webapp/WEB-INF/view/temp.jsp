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
        // �������� �ش� ����� �����͸� ������
        fetch(`getEmployeeDetails.do?emp_id=${empId}`)
            .then(response => response.json())
            .then(data => {
                // �޾ƿ� �����͸� salaryForm�� �ʵ忡 ����
                document.getElementById("basicSalary").value = data.basicSalary || 0;
                document.getElementById("bonus").value = data.bonus || 0;
                document.getElementById("pension").value = data.pension || 0;
                document.getElementById("healthInsurance").value = data.healthInsurance || 0;
                document.getElementById("totalSalary").value = data.totalSalary || 0;
                document.getElementById("totalDeduction").value = data.totalDeduction || 0;
                document.getElementById("actualPayment").value = data.actualPayment || 0;

                // �����ȣ�� salary_date�� ���� ����
                document.getElementById("empId").value = empId;
                document.getElementById("salaryDate").value = data.salaryDate || "";
                
                // ��� �Լ� ȣ�� (�ʿ��)
                calculate();
            })
            .catch(error => {
                console.error('Error loading employee details:', error);
                alert('��� ������ �ҷ����� �� �����߽��ϴ�.');
            });
    }

    function calculate() {
        // �ʿ� �� �ǽð����� �Ѿװ� ������ ����ϴ� ����
        const basicSalary = parseFloat(document.getElementById("basicSalary").value) || 0;
        const bonus = parseFloat(document.getElementById("bonus").value) || 0;
        const pension = parseFloat(document.getElementById("pension").value) || 0;
        const healthInsurance = parseFloat(document.getElementById("healthInsurance").value) || 0;

        // ���� �Ѿ� ���
        const totalSalary = basicSalary + bonus;
        document.getElementById("totalSalary").value = totalSalary;

        // ���� �Ѿ� ���
        const totalDeduction = pension + healthInsurance;
        document.getElementById("totalDeduction").value = totalDeduction;

        // �����޾� ���
        const actualPayment = totalSalary - totalDeduction;
        document.getElementById("actualPayment").value = actualPayment;
    }
</script>

</body>
</html>