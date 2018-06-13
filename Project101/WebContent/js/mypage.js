$(document).ready(function () {
	
	$(document).on('click', '#pagingArea a', function () {
		var page = $('#currPage').val() * 1;
		
		if ($(this).html().trim() == '이전') {
			page = page - 1;
		} else if ($(this).html().trim() == '다음') {
			page = page + 1;
		} else {
			page = $(this).html().trim();
		}

		$.ajax({
			type: 'post',
			data: {
				'page' : page,
				'state' : 'ajax',
			},
			url: './mypage.me',
			headers: {
				"cache-control": "no-cache",
				"pragma": "no-cache"
			},
			success: function (json) {
				$('#sec3container').empty().append(json);
			}
		});
		
	});
	
	$('#modifyinfo').click(function () {
		location.href = "modifyinfo.me";
	});
	
	$('#message').click(function () {
		location.href = 'msmessagetolist.ms';
	});
	
	$('#servicecenter').click(function () {
		location.href = 'noticemain.nt';
	});
	
});