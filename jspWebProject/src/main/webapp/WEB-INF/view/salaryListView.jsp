<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>�޿� �� ���� �׸� �Է�</title>
    <style>
        /* ���̾ƿ� ������ ���� �⺻ ��Ÿ�� */
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
        <!-- ���� ����: ù ��° �ڵ� -->
        <div class="left">
            <!-- �˻� �� -->
            <form action="updateSalary.do" method="get">
                <input type="text" name="emp_id" placeholder="��� ID �Է�">
                <button type="submit">�˻�</button>
            </form>

            <!-- �޿� �� ���� �׸� �Է� �� -->
            <h2>�޿� �� ���� �׸� �Է�</h2>
            <form action="updateSalary.do" method="post">
                <table>
                    <tr>
                        <td>�⺻��</td>
                        <td><input type="text" name="basicSalary" value="${paymentItems.basicSalary}"> ��</td>
                        <td>���ο���</td>
                        <td><input type="text" name="nationaPension" value="${deductionItems.nationaPension}"> ��</td>
                    </tr>
                    <!-- �߰��� �޿� �� ���� �׸�� -->
                    <tr>
                        <td>�ĺ�</td>
                        <td><input type="text" name="foodexp" value="${paymentItems.foodexp}"> ��</td>
                        <td>�ǰ�����</td>
                        <td><input type="text" name="healthIns" value="${deductionItems.healthIns}"> ��</td>
                    </tr>
                    <tr>
                        <td>�ѱ޿���</td>
                        <td>${paymentItems.totalSalary} ��</td>
                        <td>�Ѱ�����</td>
                        <td>${deductionItems.deduction_amount} ��</td>
                    </tr>
                </table>
                <button type="submit">������Ʈ</button>
            </form>

            <!-- ��� ��� ���̺� -->
            <table>
                <tr>
                    <td>��� ��ȣ</td>
                    <td>����</td>
                    <td>�μ�</td>
                    <td>��¥</td>
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

        <!-- ������ ����: �� ��° �ڵ� -->
        <div class="right">
            <form id="salaryForm" action="updateSalary.do" method="post">
                <table>
                    <input type="hidden" name="emp_id" id="empId" value="">
                    <input type="hidden" name="salary_date" id="salaryDate" value="">    
                    <tr>
                        <td>�⺻��</td>
                        <td><input type="text" name="basicSalary" id="basicSalary" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>�󿩱�</td>
                        <td><input type="text" name="bonus" id="bonus" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>���� �Ѿ�</td>
                        <td><input type="text" name="totalSalary" id="totalSalary" value="0" readonly></td>
                    </tr>
                    <tr>
                        <td>���ο���</td>
                        <td><input type="text" name="pension" id="pension" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>�ǰ�����</td>
                        <td><input type="text" name="healthInsurance" id="healthInsurance" value="0" oninput="calculate();"></td>
                    </tr>
                    <tr>
                        <td>���� �Ѿ�</td>
                        <td><input type="text" name="totalDeduction" id="totalDeduction" value="0" readonly></td>
                    </tr>
                    <tr>
                        <td>�����޾�</td>
                        <td><input type="text" name="actualPayment" id="actualPayment" value="0" readonly></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <button type="submit">����</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <script>
        function confirmDelete() {
            return confirm('�����Ͻðڽ��ϱ�?');
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
