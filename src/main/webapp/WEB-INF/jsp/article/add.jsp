<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="게시물리스트" />
<%@ include file="../part/head.jspf" %>
<script>
function subAddForm(form) {
	form.title.value = form.title.value.trim();

	if ( form.title.value.length == 0 ) {
		alert('제목을 입력하세요.');
		form.title.focus();
		return false;
	}
	
	form.body.value = form.body.value.trim();

	if ( form.body.value.length == 0 ) {
		alert('내용을 입력하세요.');
		form.body.focus();
		return false;
	}	
	form.submit();
}
</script>
<h1 class="con">게시물 작성</h1>
<div class="con table-common">
	<form action="./doAdd" method="POST" onsubmit="subAddForm(this); return false;">
		<table>
			<tbody>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" autocomplete="off"
						autofocus="autofocus"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="20px" cols="120px" name="body" autocomplete="off"></textarea></td>
				</tr>
				<tr>
					<th>저장</th>
					<td><input type="submit" value="저장" class="btn-a">
					<input type="reset" value="취소" onclick="location.replace('./list')" class="btn-a"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="../part/foot.jspf" %>