<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>勤怠照会・記録</title>
</head>
<body>

<!-- 勤怠記録リストを表示するテーブルタグ -->
<table border="1">
	<tr>
		<td colspan="6">勤怠照会・記録</td>
	</tr>
	<tr>
		<td>社員ID </td>
		<td>氏名</td>
		<td>部署</td>
		<td>職位</td>
		<td>勤怠記録日付</td>
		<td>勤怠状態</td>
	</tr>
<!-- 勤怠記録がない場合、AttendanceListPageのhasNoAttendance()を呼出 -->
<c:if test="${ attendanceListPage.hasNoAttendance() }">
	<tr>
		<td colspan="６">勤怠記録が見つかりません。</td>
	</tr>
</c:if>

<c:forEach var="attendanceRecord" items="${attendanceListPage.content}">
<tr>
	<td>${attendanceRecord.empId}</td>
	<td>${attendanceRecord.username}</td>
	<td>${attendanceRecord.dept}</td>
	<td>${attendanceRecord.position}</td>
	<td>${attendanceRecord.attendDate}</td>
	<td>${attendanceRecord.status}</td>
</tr>
</c:forEach>

<!-- 勤怠記録がある場合、AttendanceListPageのhasAttendance()を呼出 -->
<c:if test="${attendanceListPage.hasAttendance() }">
	<tr>
		<td colspan="6">
			<c:if test="${attendanceListPage.startPage > 5}">
			<a href="list.do?pageNo=${attendanceListPage.startPage - 5 }">[前のページ]</a>
			</c:if>
			<c:forEach var="pNo" begin="${attendanceListPage.startPage}" end="${attendanceListPage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${attendanceListPage.endPage < attendanceListPage.totalPages}">
			<a href="list.do?pageNo=${attendanceListPage.startPage + 5 }">[次のページ]</a>
</c:if>
</td>
</tr>
</c:if>

</table>

<!-- 新規勤怠記録を入力してもらうフォームタグ -->
<form action="write.do" method="post">

<p>
社員ID：<br/><input type="text" name="empId">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<c:if test="${errors.empId}">社員IDを入力してください</c:if>
</p>
<p>
入力日付：<br/><input type="date" name="attendDate">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<c:if test="${errors.attendDate}">入力日付を入力してください</c:if>
</p>
<p>
勤怠状態：<br/><input type="text" name="status" value="出勤・欠勤・遅刻・年次の中から一つを選んで入力してください">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<c:if test="${errors.status}">勤怠状態を入力してください</c:if>
</p>
<input type="submit" value="追加">

</form>




</body>
</html>