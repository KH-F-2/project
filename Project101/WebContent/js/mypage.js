$(document).ready(function () {
	
	$('.interestDelete').click(function () {
		var content_num = $(this).siblings('.content_num').val();
		var board_name = $(this).siblings('.board_name').val();

		$.ajax({
	         type : "POST",
	         contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	         data : {
	        	'content_num' : content_num,
	        	'board_name' : board_name
	         },
	         url : "./interestdelete.me",
	         success: function(result){

	        	 if (result == 1) {
	        		 alert('삭제되었습니다.');
				} else {
					alert('찜 삭제 실패했습니다.');
				}
	         },
	         error: function() {
	            alert("error");
	         }
	      }); // ajax
		
	});
	
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