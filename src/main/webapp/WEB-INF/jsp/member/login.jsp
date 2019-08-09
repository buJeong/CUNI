<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="로그인" />
<%@ include file="../part/head.jspf" %>
<script>
function subLoginForm(form) {
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
	
	form.submit();
}
function subFindIdForm(form) {
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
function subFindPwForm(form) {
	form.name.value = form.name.value.trim();

	if ( form.name.value.length == 0 ) {
		alert('이름을 입력하세요.');
		form.name.focus();
		return false;
	}

	form.loginId.value = form.loginId.value.trim();

	if ( form.loginId.value.length == 0 ) {
		alert('로그인 아이디를 입력하세요.');
		form.loginId.focus();
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
<div class="table-common con">
	<form action="./doLogin" method="POST" onsubmit="subLoginForm(this); return false;">
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="loginId" autocomplete="off"
						autofocus="autofocus" placeholder="아이디입력"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="loginPw" placeholder="비번입력"></td>
				</tr>
				<tr>
					<th>로그인</th>
					<td><input type="submit" value="로그인" class="btn-a">
					<input type="reset" value="취소" onclick="location.replace('/home/main')" class="btn-a"></td>
				</tr>
				<tr>
					<th>아이디/비밀번호 찾기</th>
					<td><button class="btn-a find-id-btn" type="button">아이디찾기</button>
					<button class="btn-a find-pw-btn" type="button">비밀번호찾기</button></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<div class="popup-bg"></div>
<div class="popup find-id">
    <div class="head">
        <div class="btn-close"></div>
    </div>
    <div class="body popup-table-common"">
    	<form action="./findLoginId" method="POST" onsubmit="subFindIdForm(this); return false;">
    		<table>
    			<tbody>
    				<tr style="margin-left:3px">
    					<th>이름</th>
    					<td><input type="text" name="name"></td>
    				</tr>
    				<tr style="margin-left:3px">
    					<th>이메일</th>
    					<td><input type="text" name="email"></td>
    				</tr>
    				<tr style="margin-left:3px">
    					<th></th>
    					<td><input type="submit" value="찾기" class="btn-a" style="margin-right:3px"><input type="reset" value="취소" class="btn-a btn-close"></td>
    				</tr>
    			</tbody>
    		</table>
    	</form>
    </div>
</div>
<div class="popup find-pw">
    <div class="head">
        <div class="btn-close"></div>
    </div>
    <div class="body popup-table-common"">
    	<form action="./findLoginPw" method="POST" onsubmit="subFindPwForm(this); return false;">
    		<table>
    			<tbody>
    				<tr style="margin-left:3px">
    					<th>이름</th>
    					<td><input type="text" name="name"></td>
    				</tr>
    				<tr style="margin-left:3px">
    					<th>아이디</th>
    					<td><input type="text" name="loginId"></td>
    				</tr>
    				<tr style="margin-left:3px">
    					<th>이메일</th>
    					<td><input type="text" name="email"></td>
    				</tr>
    				<tr style="margin-left:3px">
    					<th></th>
    					<td><input type="submit" value="찾기" class="btn-a" style="margin-right:3px"><input type="reset" value="취소" class="btn-a btn-close"></td>
    				</tr>
    			</tbody>
    		</table>
    	</form>
    </div>
</div>
<%@ include file="../part/foot.jspf" %>