function add__reply() {
	var body = $('.add-reply-form textarea[name="body"]').val();
	
	if ( body == null || body.length == 0 ) {
		alert('댓글내용을 입력해주세요.');
		$('.add-reply-form textarea[name="body"]').focus();
		return;
	}
	var articleId = $('.add-reply-form input[name="articleId"]').val();
	
	var boardId = $('.add-reply-form input[name="boardId"]').val();
	
	$.post('./addReply',
		{
			body: body,
			articleId: articleId,
			boardId: boardId
		},
		function(data) {
			
		}
		,'json'
	);
	$('add-reply-form textarea[name="body"]').val('');
	$('add-reply-form textarea[name="body"]').focus();
}
var reply__lastReceivedId = 0;

function load__replies() {
	$.get('./getReplies',
		{
			from : reply__lastReceivedId + 1,
			articleId : articleId
		},
		function(data) {
			for ( var i = 0; i < data.replies.length; i++ ){
				var reply = data.replies[i];
				reply__lastReceivedId = reply.id;
				draw__reply__list(reply);
			}
			
			setTimeout(load__replies, 1000);
		},
		
		'json'
	);
}

function draw__reply__list(reply) {
	var id = reply.id;
	
	var regDate = reply.regDate;
	
	var body = reply.body;
	
	var 회원번호 = reply.memberId;
	
	var 내용 = `
				<div class="reply-body">
					${body}
				</div>
				<form method="POST" onsubmit="doModifyReply(this); return false;" class="edit-mode-visible">
					<div>
						<textarea name="body" cols="30" rows="3"></textarea>
					</div>
					<div>
						<input type="hidden" name="id" value="${reply.id}">
						<input type="submit" value="수정" class="btn-a" style="margin-left:25px; transform:scale(0.8);"/>
						<input type="reset" value="취소" class="btn-a" style="margin-left:20px; transform:scale(0.8);" onclick="changeMode(this);">
					</div>
				</form>
	`;
	
	var 비고 = `
		<div class="editable-item">
			<a href="javascript:;" onclick="changeMode(this);" class="read-mode-visible btn-a">수정</a>
			<input type="button" class="delete-reply-btn btn-a" value="삭제" onclick="doDeleteReply(this)" style="margin-top:5px;">
		</div>
	`
		
	var editableClass = '';
	
	if ( 회원번호 == loginedMemberId ) {
		editableClass = 'editable';
	}
	
	var html = `
		<tr data-id="${id}" data-member-id="${회원번호}" class="${editableClass}">
			<td>${id}</td>
			<td>${regDate}</td>
			<td>${내용}</td>
			<td>${비고}</td>
		</tr>
	`;
	
	$('.replies-list > tbody').prepend(html);
}

function doDeleteReply(el) {
	
	if ( confirm('삭제하시겠습니까?') == false ) {
		return false;
	}
	
	var $tr = $(el).closest('tr');
	
	var id = parseInt($tr.attr('data-id'));
	
	$.post('./doDeleteReply',
		{
			id: id
		},
		function(data) {
			$tr.remove();
		},
		'json'
	);
}

function doModifyReply(form) {
	var body = form.body.value;
	
	body = body.trim();
	
	if ( body == null || body.length == 0 ) {
		alert('수정할 댓글내용을 입력해주세요.');
		form.body.focus();
		return false;
	}
	var $tr = $(form).closest('tr');
	
	var id = parseInt($tr.attr('data-id'));
	
	$.post('./doModifyReply',
			{
				id: id,
				body: body
			},
			function(data) {
				$tr.find('.reply-body').text(body);
			},
		'json'
	);
	
	changeMode(form);
	$tr.find('.reply-body').text('수정중...');
}
$(function() {
	$('.add-reply-form .add-reply-btn').click(function() {
		add__reply();
	})
	load__replies();

});

function changeMode(el) {
	var $el = $(el);

	var $tr = $el.closest('tr');

	if ($tr.hasClass('edit-mode')) {
		$tr.removeClass('edit-mode');
	} else {
		$tr.addClass('edit-mode');
	}
}