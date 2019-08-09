<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.dto.Member" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="logoText" value="시험제출" />
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../part/head.jspf" %>
<div class="con table-common">
	<table>
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${member.getLoginId()}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${member.getName()}</td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td>${member.getRegDate()}</td>
			</tr>
			<tr>
				<th>메일</th>
				<td>${member.getEmail()}</td>
			</tr>
		</tbody>
	</table>
</div>
<%@ include file="../part/foot.jspf" %>