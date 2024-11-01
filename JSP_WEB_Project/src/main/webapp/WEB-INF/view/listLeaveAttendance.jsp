<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>休暇照会・記録</title>
</head>
<body>

<!-- 休暇記録リストを表示するテーブルタグ -->
<!-- 휴가기록 리스트를 표시할 테이블태그  -->
<table border="1">
	<tr>
		<td colspan="7">休暇照会・記録</td>
	</tr>
	<tr>
		<td>社員ID</td>
		<td>氏名</td>
		<td>部署</td>
		<td>職位</td>
		<td>休暇開始日</td>
		<td>休暇終了日</td>
		<td>休暇残日数</td>
	</tr>

<!-- 休暇記録がない場合、LeaveAttendanceListPageのhasNoLeaveAttendance()を呼出 -->
<!-- 휴가기록이 없는 경우, LeaveAttendanceListPage의 hasNoLeaveAttendance()를 호출  -->
<c:if test="${ leaveAttendanceListPage.hasNoLeaveAttendance() }">
	<tr>
		<td colspan="7">休暇記録が見つかりません。</td>
	</tr>
</c:if>

<c:forEach var="leaveAttendanceSettings" items="${ leaveAttendanceListPage.content }">
<tr>
	<td>${ leaveAttendanceSettings.empId }</td>
	<td>${ leaveAttendanceSettings.username }</td>
	<td>${ leaveAttendanceSettings.dept }</td>
	<td>${ leaveAttendanceSettings.position}</td>
	<td>${ leaveAttendanceSettings.startDate}</td>
	<td>${ leaveAttendanceSettings.endDate }</td>
	<td>${ leaveAttendanceSettings.leaveDays }</td>
</tr>

</c:forEach>

<!-- 休暇記録がある場合、LeaveAttendanceListPageのhasLeaveAttendance()を呼出 -->
<!-- 휴가기록이 있는 경우, LeaveAttendanceListPage의 hasLeaveAttendance()를 호출  -->
<c:if test="${ leaveAttendanceListPage.hasLeaveAttendance() }">
	<tr>
		<td colspan="7">
			<c:if test="${ leaveAttendanceListPage.startPage > 5 }">
			<a href="leavelist.do?pageNo=${ leaveAttendanceListPage.startPage -5 }">[前のページ]</a>
			</c:if>
			<c:forEach var="pNo" begin="${ leaveAttendanceListPage.startPage }" end="${leaveAttendanceListPage.endPage }">
			<a href="leavelist.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${ leaveAttendanceListPage.endPage < leaveAttendanceListPage.totalPages}">
			<a href="leavelist.do?pageNo=${ leaveAttendanceListPage.startPage + 5 }">[次のページ]</a>
		</c:if>
		</td>
		</tr>
		
</c:if>


</table>

<!-- 新規休暇記録を入力してもらうフォームタグ -->
<!-- 신규휴가기록을 입력받을 폼 태그  -->
<form action="writeleave.do" method="post">
<p>
社員ID：<br/><input type="text" name="empId">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<!-- 에러가 있는 경우, <c:if></c:if>안의 문장을 표시한다. -->
<c:if test="${errors.empId }">社員IDを入力してください</c:if>
</p>

<p>
休暇残日数：<br/><input type="text" name="leaveDays">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<!-- 에러가 있는 경우, <c:if></c:if>안의 문장을 표시한다. -->
<c:if test="${errors.leaveDays }">休暇残日数を入力してください</c:if>
</p>

<p>
休暇開始日：<br/><input type="date" name="startDate">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<!-- 에러가 있는 경우, <c:if></c:if>안의 문장을 표시한다. -->
<c:if test="${errors.startDate }">休暇開始日を入力してください</c:if>
</p>

<p>
休暇終了日：<br/><input type="date" name="endDate">
<!-- エラーがある場合、<c:if></c:if>内の文章を表示する。 -->
<!-- 에러가 있는 경우, <c:if></c:if>안의 문장을 표시한다. -->
<c:if test="${errors.endDate }">休暇終了日を入力してください</c:if>
</p>
<input type="submit" value="追加">

</form>


</body>
</html>