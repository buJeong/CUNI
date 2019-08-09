$(function() {
	$('.popup .btn-close').click(function() {
		$('.popup,.popup-bg').css('display', 'none');
	});

	$('.find-id-btn').click(function() {
		$('.find-id,.popup-bg').css('display', 'block');
	});

	$('.find-pw-btn').click(function() {
		$('.find-pw,.popup-bg').css('display', 'block');
	});
});