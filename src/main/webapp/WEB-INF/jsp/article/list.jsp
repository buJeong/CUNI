<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="게시물리스트" />
<%@ include file="../part/head.jspf" %>
<h1 class="con">게시물 리스트</h1>

<div class="con">
	<form action="./list" method="GET" id="list-view-option">

		<select name="searchType">
			<option value="title">제목</option>
			<option value="body">내용</option>
		</select>

		<c:if test="${param.searchType != null && param.searchType != ''}">
			<script>
				$('select[name="searchType"]').val('${param.searchType}');
			</script>
		</c:if>
		<select name="sortType" id="sort-type-select">
			<option value="">정렬기준</option>
			<option value="desc">내림차순</option>
			<option value="asc">오름차순</option>
		</select>
		<script>
			$('#sort-type-select').change(function() {
				var sortType = $(this).val();
				$('#list-view-option').submit();
			});
		</script>
		<input type="text" name="searchKeyword" placeholder="검색어"
			value="${param.searchKeyword}"> <input type="submit"
			value="검색" />
	</form>
	
	
	
</div>


<div class="list-1 table-common con">
	<table>
		<colgroup>
			<col width="80">
			<col width="180">
			<col>
			<col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>제목</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${pagedListRs.list}">
				<tr>
					<td><c:out value="${article.id}" /></td>
					<td><c:out value="${article.regDate}" /></td>
					<td><a href="detail?id=${article.id}"><c:out
								value="${article.title}" /></a></td>
					<td>${article.extra.repliesCount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="con page-nav text-align-center line-height-0-ch-only">
	<ul class="row inline-block">
		<c:forEach var="currentPage" begin="1" end="${pagedListRs.lastPage}">
			<!-- URL 초기값 -->
			<c:url var="url" value="">
				<c:forEach items="${param}" var="entry">
					<c:if test="${entry.key != 'page'}">
						<c:param name="${entry.key}" value="${entry.value}" />
					</c:if>
				</c:forEach>
				<c:param name="page" value="${currentPage}" />
			</c:url>
			<c:set var="aElclass"
				value="${currentPage == pagedListRs.page ? 'red bold' : ''}" />
			<li class="cell"><a href="${url}" class="block ${aElclass}" style="margin:0 10px;">${currentPage}</a></li>
		</c:forEach>
	</ul>
</div>

<%@ include file="../part/foot.jspf" %>