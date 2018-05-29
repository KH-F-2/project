$(document).ready(function () {
	
	// 사이드 네비바 토글 이벤트
	$('#menuBtn').click(function() {
		$('.ui.sidebar').sidebar('setting', 'transition', 'overlay').sidebar('toggle');
	})
	
	// 스크롤 1/10 내리면 히든헤더 보여주기
	$(window).scroll(function() {

		if ($(window).scrollTop() > $(window).height() / 100) {
			$('#header').css({
				top : 0,
				position: 'fixed',
				'box-shadow': '0 1px 10px gray',
			});
			$('#header_title').css('visibility', '');
		} else {
			$('#header').css({
				'box-shadow': '',
			});
			$('#header_title').css('visibility', 'hidden');
		}
	}).trigger('scroll');
	
});