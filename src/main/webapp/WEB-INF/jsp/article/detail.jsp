<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="게시물상세정보" />
<%@ include file="../part/head.jspf"%>
<script>
var articleId = parseInt('${param.id}');

var loginedMemberId = ${loginedMemberId};
</script>
<h1 class="con">게시물 상세</h1>

<div class="article-detail table-common con">
	<table>
		<colgroup>
			<col width="80">
		</colgroup>
		<tbody>
			<tr>
				<th>ID</th>
				<td><c:out value="${article.id}" /></td>
			</tr>
			<tr>
				<th>날짜</th>
				<td><c:out value="${article.regDate}" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><c:out value="${article.title}" escapeXml="true" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${article.bodyForPrint}</td>
			</tr>
		</tbody>
	</table>
</div>
<h2 class="con">댓글 작성</h2>
<div class="con">
	<form class="add-reply-form" method="POST"
		onsubmit="subAddReplyForm(this); return false;">
		<textarea name="body" cols="100" rows="5"></textarea>
		<input type="hidden" name="articleId" value="${article.id}"> <input
			type="hidden" name="boardId" value="${article.boardId}">
		<div>
			<input type="submit" value="작성" class="btn-a add-reply-btn"> <input
				type="reset" value="취소" class="btn-a">
		</div>
	</form>
</div>

<h2 class="con">댓글 리스트</h2>
<div class="con replies-table-common">
	<table class="replies-list">
		<thead>
			<tr>
				<th>번호</th>
				<th>등록날짜</th>
				<th>내용</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
</div>

<%@ include file="../part/foot.jspf"%>