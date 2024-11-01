<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>社員現状・管理</title>
</head>
<style>
<!--
フォームの内容を真ん中に整列 --><!--form의 내용을 정중앙으로 정렬 -->th, td {
	text-align: center;
	vertical-align: middle;
}
</style>

<!-- 総社員数、引退者、在職者の数を表示する -->
<!-- 총사원수, 퇴직자, 재직자의 수를 표시한다. -->
<body>
	<table border="３" align="center">
		<tr>
			<td style="width: 100px;">在職者</td>
			<td style="width: 100px;">退職者</td>
			<td style="width: 100px;">全体</td>
		</tr>
		<tr>
			<td>${employeeList.inCompanyCount}</td>
			<td>${employeeList.retiredCount}</td>
			<td>${employeeList.total}</td>
		</tr>
	</table>
	<br>

<!-- 社員削除の時に全体選択機能を提供する為のスクリプトコード -->
<!-- 사원 삭제 시 전체선택 기능을 제공하기 위한 스크립트 코드 -->
	<script type="text/javascript">
	function selectAll(selectAll)  {
		  const checkboxes 
		     = document.querySelectorAll('input[type="checkbox"]');
		  
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = selectAll.checked
		  })
		}
	</script>
	
	<!-- 社員リスト -->
	<!-- 사원 리스트 -->
	<div align="center">
		<form action="list.do" method="get">
			<!-- 社員検索 -->
			<!-- 사원 검색 -->
			<span> <select
				style="width: 100px; height: 40px; font-size: 20px;" id="searchBy"
				name="searchBy">
					<option value="${searchBy}">${searchByValue}</option>
					<option value="user_name">氏名</option>
					<option value="emp_id">社員番号</option>
					<option value="dept">所属</option>
					<option value="position">職位</option>
			</select> <input style="width: 200px; height: 20px; font-size: 15px;"
				type="text" name="keyword" id="keyword" value="${keyword}"
				placeholder="社員検索" />
				<button style="width: 80px; height: 20px; font-size: 15px;"
					class="button" type="submit">検索</button>
			</span>
		</form>
	</div>
	<br>
	<div align="center">
		<form action="delete.do" method="post">
			<table border="1">
				<tr>
					<th><input type="checkbox" onclick='selectAll(this)'></th>
					<td>入社日</td>
					<td>社員番号</td>
					<td>氏名</td>
					<td>部署</td>
					<td>職位</td>
					<td>携帯電話</td>
					<td>Eメール</td>
					<td>退社日</td>
					<td>状態</td>
				</tr>
				<c:if test="${employeeList.hasNoData()}">
					<tr>
						<td colspan="4">유저가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="data" items="${employeeList.content}">
					<tr
						onClick="location.href='/JSP_WEB_Project/user/update.do?userId=${data.userInfo.userId}'">
						<td onClick="event.stopPropagation()"><input type="checkbox"
							name="userId" id="userId" value="${data.userInfo.userId}"></td>
						<td>${data.employee.hiredAt}</td>
						<td>${data.employee.employeeId}</td>
						<td>${data.userInfo.username}</td>
						<td>${data.employee.dept}</td>
						<td>${data.employee.position}</td>
						<td>${data.userInfo.phoneNum}</td>
						<td>${data.userInfo.email}</td>
						<td>${data.retirementDate}</td>
						<td>${data.status}</td>
					</tr>
				</c:forEach>
				<!-- 社員リストのページング -->
				<!-- 사원리스트의 페이징 -->
				<c:if test="${employeeList.hasData()}">
					<tr>
						<td colspan="10"><c:if test="${employeeList.startPage > 5}">
								<a
									href="list.do?pageNo=${employeeList.startPage - 5}&keyword=${keyword}&searchBy=${searchBy}">[前のページ]</a>
							</c:if> <c:forEach var="pNo" begin="${employeeList.startPage}"
								end="${employeeList.endPage}">
								<a
									href="list.do?pageNo=${pNo}&keyword=${keyword}&searchBy=${searchBy}">[${pNo}]</a>
							</c:forEach> <c:if test="${employeeList.endPage < employeeList.totalPages}">
								<a
									href="list.do?pageNo=${employeeList.startPage + 5}&keyword=${keyword}&searchBy=${searchBy}">[次のページ]</a>
							</c:if></td>
					</tr>
				</c:if>
			</table>
			<div align="center">
				<!-- 社員登録 -->
				<!-- 사원등록 -->
				<button type="button"
					style="width: 80px; height: 30px; font-size: 18px;"
					onClick="location.href='/JSP_WEB_Project/user/create.do'">社員登録</button>
				<!-- 사원삭제 -->
				<input style="width: 80px; height: 30px; font-size: 18px;"
					type="submit" value="社員削除">
				<c:if test="${errors.noSelectedError}">
					<br>削除対象を選択してください。</c:if>
			</div>
		</form>
	</div>
	<br>

</body>
</html>

