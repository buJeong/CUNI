<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../part/head.jspf" %>
<script>
function subModifyForm(form) {
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
<div class="con table-common">
	<form action="./doModify" method="POST" onsubmit="subModifyForm(this); return false;">
		<table>
			<tbody>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" autocomplete="off"
						autofocus="autofocus" placeholder="${article.title}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><input type="hidden" name="id" value="${article.id}">
					<textarea name="body" autocomplete="off" placeholder="${article.body}"></textarea></td>
				</tr>
				<tr>
					<th>수정</th>
					<td><input type="submit" value="수정" class="btn-a">
					<input type="reset" value="취소" onclick="location.replace('/article/detail?id=${article.id}')" class="btn-a"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="../part/foot.jspf" %>