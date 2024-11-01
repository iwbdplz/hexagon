<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>従業員検索結果</title> <!-- 사원 검색 결과 제목 / 従業員検索結果のタイトル -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style type="text/css">
        /* 여기에 필요한 스타일 추가 / 必要なスタイルをここに追加 */
        body {
            font-family: Arial, sans-serif;
            margin: 0; /* Remove default margin */
            padding: 20px; /* Add padding */
        }
        .searchPopupWrapper {
            height: 400px; /* Height of the table wrapper */
            overflow-y: auto; /* Enable vertical scrolling */
            width: 100%; /* Make wrapper full width */
            position: relative; /* Required for the fixed header */
        }
        table {
            width: 100%; /* Table takes full width */
            border-collapse: collapse; /* Remove space between borders */
        }
        th, td {
            padding: 12px 15px; /* Add padding for cells */
            text-align: left; /* Align text to the left */
            border: 1px solid #ccc; /* Add border */
        }
        th {
            background-color: #f2f2f2; /* Header background color */
            position: sticky; /* Make header sticky */
            top: 0; /* Stick to the top */
            z-index: 10; /* Ensure it stays above the body */
        }
        td:nth-child(5), td:nth-child(6) {
            width: 150px; /* Set width for the '入社日' and '退職日' columns */
        }
        tr:nth-child(even) {
            background-color: #f9f9f9; /* Zebra stripes for rows */
        }
    </style>
    <script>
        // 사원 정보를 선택하는 함수 / 従業員情報を選択する関数
        function selectEmployee(empId, userName, email, phone, employmentType, dept, position, retirementDate, retirementReason, retirementPayment, employmentStatus, hireDate) {
            closing(); // 팝업 닫기 / ポップアップを閉じる
            var openerDoc = window.opener.document; // 부모 창의 문서 객체를 참조 / 親ウィンドウのドキュメントオブジェクトを参照
            
            // 각 태그의 innerText를 개별적으로 설정 / 各タグのinnerTextを個別に設定
            openerDoc.getElementById("employeeName").innerText = userName;
            openerDoc.getElementById("employeeEmail").innerText = email;
            openerDoc.getElementById("employeePhone").innerText = phone;
            openerDoc.getElementById("employmentType").innerText = employmentType;
            openerDoc.getElementById("employeeDept").innerText = dept;
            openerDoc.getElementById("employeePosition").innerText = position;
            openerDoc.getElementById("retirementDate").innerText = retirementDate || '현재 재직중'; // 재직중일 경우의 처리 / 現在在職中の場合の処理
            openerDoc.getElementById("retirementReason").innerText = retirementReason || 'N/A';
            openerDoc.getElementById("retirementPayment").innerText = retirementPayment || 'N/A';
            openerDoc.getElementById("employmentStatus").innerText = employmentStatus || 'N/A';
            openerDoc.getElementById("hireDate").innerText = hireDate || 'N/A';

            console.log("Closing popup..."); // 팝업 닫는 로그 출력 / ポップアップを閉じるログを出力
            window.close(); // 팝업 닫기 / ポップアップを閉じる
        }

        // 행 클릭 시 선택하는 함수 / 行をクリックしたときに選択する関数
        function onRowClick(empId, userName, email, phone, employmentType, dept, position, retirementDate, retirementReason, retirementPayment, employmentStatus, hireDate) {
            console.log("Row clicked with employee:", empId); // 클릭한 행의 사원 ID 로그 출력 / クリックした行の従業員IDを出力
            selectEmployee(empId, userName, email, phone, employmentType, dept, position, retirementDate, retirementReason, retirementPayment, employmentStatus, hireDate);
        }

        function closing() {
            console.log("Closing the window..."); // 창 닫는 로그 출력 / ウィンドウを閉じるログを出力
            window.close(); // 팝업 닫기 / ポップアップを閉じる
        }
    </script>
</head>
<body>
    <h1>従業員検索結果</h1> <!-- 직원 검색 결과 제목 / 従業員検索結果のタイトル -->
    <div class="searchPopupWrapper">
        <table>
            <thead>
                <tr>
                    <th>従業員ID</th> <!-- 사원 ID 헤더 / 従業員IDヘッダー -->
                    <th>ユーザー名</th> <!-- 사용자 이름 헤더 / ユーザー名ヘッダー -->
                    <th>部門</th> <!-- 부서 헤더 / 部門ヘッダー -->
                    <th>職位</th> <!-- 직위 헤더 / 職位ヘッダー -->
                    <th>入社日</th> <!-- 입사일 헤더 / 入社日ヘッダー -->
                    <th>退職日</th> <!-- 퇴직일자 헤더 / 退職日ヘッダー -->
                    <th>状態</th> <!-- 상태 헤더 / 状態ヘッダー -->
                    <!-- <th>選択</th> 선택 헤더 / 選択ヘッダー -->
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty employees}"> <!-- 직원 리스트가 비어 있는지 확인 / 従業員リストが空かどうか確認 -->
                    <tr>
                        <td colspan="8" style="text-align:center;">検索結果がありません。</td> <!-- 검색 결과 없음 메시지 / 検索結果がありませんメッセージ -->
                    </tr>
                </c:if>
                <c:forEach var="employee" items="${employees}"> <!-- 직원 리스트를 반복 / 従業員リストを繰り返す -->
                    <tr onclick="onRowClick(
                        '${employee.empId}', 
                        '${employee.userName}', 
                        '${employee.email}', 
                        '${employee.phoneNumber}', 
                        '${employee.employmentType}', 
                        '${employee.dept}', 
                        '${employee.position}', 
                        '${employee.retirementDate}', 
                        '${employee.retirementReason}', 
                        '${employee.retirementPayment}', 
                        '${employee.employmentStatus}', 
                        '${employee.hireDate}'
                    )">
                        <td>${employee.empId}</td>
                        <td>${employee.userName}</td>
                        <td>${employee.dept}</td>
                        <td>${employee.position}</td>
                        <td>${employee.hireDate}</td>
                        <td>${employee.retirementDate}</td>
                        <td>${employee.employmentStatus}</td>
       <%--                  <td>
                            <button onclick="onRowClick(
                                '${employee.empId}', 
                                '${employee.userName}', 
                                '${employee.email}', 
                                '${employee.phoneNumber}', 
                                '${employee.employmentType}', 
                                '${employee.dept}', 
                                '${employee.position}', 
                                '${employee.retirementDate}', 
                                '${employee.retirementReason}', 
                                '${employee.retirementPayment}', 
                                '${employee.employmentStatus}', 
                                '${employee.hireDate}'
                            )">選択</button> <!-- 선택 버튼 / 選択ボタン -->
                        </td> --%>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
   
    <div onclick="closing()" style="cursor: pointer;"> <i class="fa-solid fa-square-xmark"></i> ポップアップを閉じる</div> <!-- 팝업 닫기 버튼 / ポップアップを閉じるボタン -->
</body>
</html>
