<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        #recordCard {
            border: 1px solid #ccc;
            padding: 15px;
            margin-top: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
        label {
            font-weight: bold;
        }
        /* 인사관리 카드 인쇄 人事管理カードの印刷 */
        @media print {
            body {
                margin: 0; /* 인쇄 시 여백 제거 / 印刷時に余白を削除 */
                padding: 0; /* 인쇄 시 패딩 제거 / 印刷時にパディングを削除 */
            }
            #recordCard {
                padding: 20px; /* 카드 패딩 조정 / カードのパディング調整 */
                border: 1px solid #ccc; /* 테두리 추가 / 枠線を追加 */
                width: 100%; /* 카드의 너비를 페이지에 맞춤 / カードの幅をページに合わせる */
                box-shadow: none; /* 그림자 제거 / シャドウを削除 */
            }
            h1,.initial, form, hr, button {
                display: none; /* 인쇄 시 제목, 폼, 버튼 숨기기 / 印刷時にタイトル、フォーム、ボタンを非表示にする */
            }
        }
    </style>
    <title>人事記録カード</title> <!-- 인사 기록 카드 제목 / 人事記録カードのタイトル -->
    <script>
        // 직원 검색 팝업을 여는 함수
        function openSearchPopup() {
            var dept = document.querySelector('select[name="dept"]').value;
            var position = document.querySelector('select[name="position"]').value;
            var keyword = document.querySelector('input[name="keyword"]').value;

            var url = 'employeeSearch.do?dept=' + encodeURIComponent(dept) +
                      '&position=' + encodeURIComponent(position) +
                      '&keyword=' + encodeURIComponent(keyword);

            // URL 앞에 /JSPboard를 추가
            window.open('/JSPboard/' + url, '従業員検索', 'width=1200,height=550'); // 팝업을 열기
        }

        // Excel 다운로드 함수
        function downloadExcel() {
            // 카드의 데이터 가져오기
            const recordCard = document.getElementById('recordCard');
            const rows = [];
            const headerRow = ['항목', '내용']; // 헤더 추가
            rows.push(headerRow);
            // 각 항목의 데이터를 CSV 형식으로 변환
            const dataElements = recordCard.querySelectorAll('p');
            dataElements.forEach((p) => {
                const itemName = p.childNodes[0].textContent.trim(); // 항목 이름
                const itemValue = p.querySelector('span').textContent.trim(); // 항목 값
                rows.push([itemName, itemValue]);
            });

            // CSV 데이터 생성
            let csvContent = "data:text/csv;charset=utf-8," 
                + rows.map(e => e.join(",")).join("\n");

            // Blob 생성 및 다운로드 링크 생성
            const encodedUri = encodeURI(csvContent);
            const link = document.createElement("a");
            link.setAttribute("href", encodedUri);
            link.setAttribute("download", "employee_record.csv");
            document.body.appendChild(link); // Firefox 호환성 문제 해결

            link.click(); // 다운로드 링크 클릭하여 파일 다운로드
            document.body.removeChild(link); // 링크 제거
        }
    </script>
</head>
<body>
    <h1>人事記録カード</h1> <!-- 인사 기록 카드 / 人事記録カード -->

    <h2 class="initial">従業員検索</h2> <!-- 직원 검색 / 従業員検索 -->
    <form id="searchForm" onsubmit="openSearchPopup(); return false;">
        <label for="dept">部門:</label> <!-- 부서: / 部門: -->
        <select name="dept">
            <option value="">すべて</option> <!-- 모두 / すべて -->
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
        
        <label for="position">職位:</label> <!-- 직위: / 職位: -->
        <select name="position">
            <option value="">すべて</option> <!-- 모두 / すべて -->
            <option value="Manager">マネージャー</option> <!-- 매니저 / マネージャー -->
            <option value="Developer">デベロッパー</option> <!-- 개발자 / デベロッパー -->
            <option value="Analyst">アナリスト</option> <!-- 애널리스트 / アナリスト -->
        </select>
        
        <label for="keyword">検索語:</label> <!-- 검색어: / 検索語: -->
        <input type="text" name="keyword" placeholder="社員番号、名前、職位検索"> <!-- 직원 번호, 이름, 직위 검색 -->
        
        <button type="submit">検索</button> <!-- 검색 / 検索 -->
    </form>

    <hr>

    <div id="recordCard">
        <h2>人事記録カード</h2> <!-- 인사 기록 카드 / 人事記録カード -->
        <p>ユーザー名: <span id="employeeName"></span></p> <!-- 사용자 이름: / ユーザー名: -->
        <p>メールアドレス: <span id="employeeEmail"></span></p> <!-- 이메일 주소: / メールアドレス: -->
        <p>電話番号: <span id="employeePhone"></span></p> <!-- 전화 번호: / 電話番号: -->
        <p>従業員区分: <span id="employmentType"></span></p> <!-- 직원 구분: / 従業員区分: -->
        <p>部門: <span id="employeeDept"></span></p> <!-- 부서: / 部門: -->
        <p>職位: <span id="employeePosition"></span></p> <!-- 직위: / 職位: -->
        <p>退職日: <span id="retirementDate"></span></p> <!-- 퇴직일: / 退職日: -->
        <p>退職理由: <span id="retirementReason"></span></p> <!-- 퇴직 이유: / 退職理由: -->
        <p>退職金: <span id="retirementPayment"></span></p> <!-- 퇴직금: / 退職金: -->
        <p>記号番号: <span id="symbolNumber"></span></p> <!-- 기호 번호: / 記号番号: -->
        <p>取得日: <span id="acquisitionDate"></span></p> <!-- 취득일: / 取得日: -->
        <p>喪失日: <span id="lossDate"></span></p> <!-- 상실일: / 喪失日: -->
    </div>

    <button onclick="window.print()">印刷</button> <!-- 인쇄 / 印刷 -->
    <button onclick="downloadExcel()">Excelダウンロード</button> <!-- Excel 다운로드 / Excelダウンロード -->
</body>
</html>
