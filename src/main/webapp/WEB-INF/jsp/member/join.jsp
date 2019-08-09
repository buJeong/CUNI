<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../part/head.jspf" %>
<script>
function subJoinForm(form) {
	form.loginId.value = form.loginId.value.trim();

	if ( form.loginId.value.length == 0 ) {
		alert('아이디를 입력하세요.');
		form.loginId.focus();
		return false;
	}

	form.loginPw.value = form.loginPw.value.trim();

	if ( form.loginPw.value.length == 0 ) {
		alert('비밀번호를 입력하세요.');
		form.loginPw.focus();
		return false;
	}

	form.checkLoginPw.value = form.checkLoginPw.value.trim();

	if ( form.checkLoginPw.value != form.loginPw.value ) {
		alert('비밀번호가 일치하지 않습니다.');
		form.loginPw.value = '';
		form.checkLoginPw.value = '';
		form.loginPw.focus();
		return false;
	}

	form.name.value = form.name.value.trim();

	if ( form.name.value.length == 0 ) {
		alert('이름을 입력하세요.');
		form.name.focus();
		return false;
	}

	form.email.value = form.email.value.trim();

	if ( form.email.value.length == 0 ) {
		alert('이메일을 입력하세요.');
		form.email.focus();
		return false;
	}

	form.submit();
}
</script>
<div class="con table-common">
	<form action="./doJoin" method="POST" onsubmit="subJoinForm(this); return false;">
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="loginId" autocomplete="off"
						autofocus="autofocus"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="loginPw"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="checkLoginPw"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" autocomplete="off"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="email" autocomplete="off"></td>
				</tr>
				<tr>
					<th>가입</th>
					<td><input type="submit" value="가입하기" class="btn-a">
					<input type="reset" value="취소" onclick="location.replace('/member/login')" class="btn-a"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="../part/foot.jspf" %>