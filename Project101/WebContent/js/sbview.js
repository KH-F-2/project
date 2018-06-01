$(document).ready(function(){
	$("#comment_btn").click(function(){
		var content=$('#comment_content').val();
		
		$('#comment_content').val('');
		$('#counter').html('0/300');
		
		var id=$('#SB_WRITER').val();
		var SB_NO=$('#SB_NO').val();
		var data={"id" : id, "content" : content, "SB_NO" : SB_NO};
		$.ajax({
			type : "POST",
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data : data,
			url : "./sbcommentaddaction.sb?",
			success: function(data){
				console.log(data);
				$('.comment_view').empty().append(data);
				$('#comment_count').text($('#comment_count').text()*1+1);
			},
			error: function() {
				alert("error");
			}
		}); // ajax
	}); // click()
	
	$('#comment_content').click(function(){
		if(id == null || id == ''){
			alert('로그인 후 작성하실 수 있습니다.');
			location.href='./signin.me';
		}
	});
	$(document).on('click', '#comment_reply_content', function(){
		if(id == null || id == ''){
			alert('로그인 후 작성하실 수 있습니다.');
			location.href='./signin.me';
		}
	});
	
	// 댓글 삭제버튼 CSS
	$('.comment_li').each(function(){
		var cmt_writer=$(this).attr('id');
		
		if(id != cmt_writer){
			$('a[id="comment_delete"]').eq($(this).index()/2).css('display', 'none');
		}
	});
	
	
});

// ajax로 불러온 댓글 삭제버튼 CSS 적용
$(document).ajaxSuccess(function(){
	$('.comment_li').each(function(){
		var cmt_writer=$(this).attr('id');
		
		if(id != cmt_writer){
			$('a[id="comment_delete"]').eq($(this).index()/2).css('display', 'none');
		}
	});
});	


function commentDelete(CMT_NO){
	ans=confirm("삭제하시겠습니까?");
	var SB_NO=$('#SB_NO').val();
   	if(ans){
   		$.ajax({
			type : "POST",
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data : {"BOARD_NO" : SB_NO, "CMT_NO" : CMT_NO, "URL" : "sbview.sb?ajax=", 
				"CMT_BOARD_NAME" : "SELL_BOARD"},
			url : "./cmtdelete.cmt?",
			success: function(data){
				$('.comment_view').empty().append(data);
				$('.comment_count').text($('.comment_count').text()*1-1);
			},
			error: function() {
				alert("error");
			}
		});
   		return;
   	}
}


function replyView(CMT_NO){
	$.ajax({
		type : "POST",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		url : "./sellboard/sbcommentreply.jsp",
		success: function(data){
			console.log(data);
			console.log(CMT_NO);
			$('.reply').empty();
			$('.reply').css("display", "none");
			$('.r'+CMT_NO).append(data);
			$('.r'+CMT_NO).css("display", "list-item");
		},
		error: function() {
			alert("error");
		}
	});
}

function commentReply(CMT_NO){
	var content=$('#comment_reply_content').val();
	var writer=$('#SB_WRITER').val();
	var SB_NO=$('#SB_NO').val();
	var data={"writer" : writer, "content" : content, "SB_NO" : SB_NO, "CMT_BOARD_NAME" : "SELL_BOARD", 
				"CMT_NO" : CMT_NO};
	
	
	alert('댓댓글 입력!\n'+content);
	/*$.ajax({
		type : "POST",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data : {"BOARD_NO" : SB_NO, "CMT_NO" : CMT_NO, "URL" : "sbview.sb?ajax=", 
			"CMT_BOARD_NAME" : "SELL_BOARD"},
		url : "./cmtreply.cmt?",
		success: function(data){
			$('.comment_view').empty().append(data);
			$('.comment_count').text($('.comment_count').text()*1-1);
		},
		error: function() {
			alert("error");
		}
	});*/
	/*$.ajax({
		type : "POST",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data : {"BOARD_NO" : SB_NO, "CMT_NO" : CMT_NO, "URL" : "sbview.sb?ajax=", 
			"CMT_BOARD_NAME" : "SELL_BOARD"},
		url : "./cmtreply.cmt?",
		success: function(data){
			$('.comment_view').empty().append(data);
			$('.comment_count').text($('.comment_count').text()*1-1);
		},
		error: function() {
			alert("error");
		}
	});*/
}

// 댓글입력창 글자수 카운팅
function chkword(obj, maxByte) {
   var strValue = obj.value;
   var strLen = strValue.length;
   var totalByte = 0;
   var len = 0;
   var oneChar = "";
   var str2 = "";

   for (var i = 0; i < strLen; i++) {
       oneChar = strValue.charAt(i);
       if (escape(oneChar).length > 4) {
           totalByte += 3;
       } else {
           totalByte++;
       }
       $('#counter').html(totalByte + '/300');
       // 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
       if (totalByte <= maxByte) {
           len = i + 1;
       }
   }

   // 넘어가는 글자는 자른다.
   if (totalByte > maxByte) {
       alert(maxByte + "자를 초과 입력 할 수 없습니다.");
       str2 = strValue.substr(0, len);
       obj.value = str2;
       chkword(obj, 4000);
   }
}