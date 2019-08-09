<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../part/head.jspf" %>
<script>
function subModifyForm(form) {
	form.beforePw.value = form.beforePw.value.trim();

	if ( form.beforePw.value.length == 0 ) {
		alert('변경 전 비밀번호를 입력하세요.');
		form.beforePw.focus();
		return false;
	}

	form.afterPw.value = form.afterPw.value.trim();

	if ( form.afterPw.value.length == 0 ) {
		alert('변경할 비밀번호를 입력하세요.');
		form.afterPw.focus();
		return false;
	}
	
	form.checkAfterPw.value = form.checkAfterPw.value.trim();

	if ( form.checkAfterPw.value.length == 0 ) {
		alert('변경할 비밀번호 확인란을 입력하세요.');
		form.checkAfterPw.focus();
		return false;
	}

	if ( form.afterPw.value != form.checkAfterPw.value ) {
		alert('비밀번호가 일치하지 않습니다.');
		form.afterPw.value = '';
		form.checkAfterPw.value = '';
		form.afterPw.focus();
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
					<th>아이디</th>
					<td><c:out value="${member.getLoginId()}"></c:out></td>
				</tr>
				<tr>
					<th>기존 비밀번호</th>
					<td><input type="password" name="beforePw"></td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td><input type="password" name="afterPw"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="checkAfterPw"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><c:out value="${member.getName()}"></c:out></td>
				</tr>
				<tr>
					<th>e-mail</th>
					<td><c:out value="${member.getEmail()}"></c:out></td>
				</tr>
				<tr>
					<th>수정하기</th>
					<td><input type="submit" value="수정하기" class="btn-a">
					<input type="reset" value="취소" onclick="history.back();" class="btn-a"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="../part/foot.jspf" %>