<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>인사기록 카드</title>
    <script>
        // 사원 검색 팝업을 여는 함수
        function openSearchPopup() {
            var dept = document.querySelector('select[name="dept"]').value;
            var position = document.querySelector('select[name="position"]').value;
            var employmentType = document.querySelector('select[name="employmentType"]').value;
            var keyword = document.querySelector('input[name="keyword"]').value;

            var url = 'employeeSearch.do?dept=' + encodeURIComponent(dept) +
                      '&position=' + encodeURIComponent(position) +
                      '&keyword=' + encodeURIComponent(keyword);

            window.open(url, '사원 검색', 'width=600,height=400');
        }
    </script>
</head>
<body>
      <h1>人事記録カード</h1>

    <h2>社員検索</h2>
    <form id="searchForm" onsubmit="openSearchPopup(); return false;">
        <label for="dept">部門:</label>
        <select name="dept">
            <option value="">すべて</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
        
        <label for="position">職位:</label>
        <select name="position">
            <option value="">すべて</option>
            <option value="Manager">マネージャー</option>
            <option value="Developer">デベロッパー</option>
            <option value="Analyst">アナリスト</option>
        </select>
        
        <label for="keyword">検索語:</label>
        <input type="text" name="keyword" placeholder="社員番号、名前、職位検索">
        
        <button type="submit">検索</button>
    </form>

    <hr>

    <div id="recordCard">
        <!-- 選択した社員情報がここに表示されます。 -->
    </div>

    <button onclick="window.print()">印刷</button>
    <button onclick="downloadExcel()">Excelダウンロード</button>

    <script>
        function downloadExcel() {
            // 엑셀 다운로드 로직
        }
    </script>
</body>
</html>
