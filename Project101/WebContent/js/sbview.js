$(document).ready(function(){
	$("#comment_btn").click(function(){
	      var content=$('#comment_content').val();
	      
	      $('#comment_content').val('');
	      $('#counter').html('0/300');
	      
	      var id=$('#WRITER').val();
	      var NO=$('#NO').val();
	      var data={"id" : id, "content" : content, "NO" : NO, "board_name" : board_name};
	      $.ajax({
	         type : "POST",
	         contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	         data : data,
	         url : "./sbcommentaddaction.sb",
	         success: function(data){
	            console.log(data.length);
	            $('.comment_view').empty().append(data);
	            if(data.length > 100) {
	               $('#comment_count').text($('#comment_count').text()*1+1);
	            }
	         },
	         error: function() {
	            alert("error");
	         }
	      }); // ajax
	}); // click()
	
	$(".div_writer").hide();
    $(".a_writer").click(function () {
		var id = $(this).attr('id');
		$("#div_writer"+id).toggle(); 
		$("#div_writer"+id).css({
			'top': '7em',
			'left': '8em'
		});
    });
	
	$('#comment_content').click(function(){
		if(id == null || id == ''){
			alert('로그인 후 작성하실 수 있습니다.');
			location.href='./signin.me';
		}
	});
	$('#comment_reply_content').click(function(){
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
	
	// 구매신청 버튼
	$('#trade').click(function(){
		var data = $('#NO').val();
		var name = $('#WRITER').val();
		$.ajax({
			type : "POST",
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data : {"NO" : data},
			url : "./sbtradeaction.sb",
			success: function(data){
				alert(data);
				console.log(data.length);
				// 판매완료되었습니다 가 뜰시 후기작성으로 이동
				if(data.length < 13){
					location.href = "./signepil.me?writer="+name;
				}
			},
			error: function() {
				alert("error");
			}
		}); // ajax
	});

	tagArr = $('#tagVal').val().split(' ');
	
	for (var i = 0; i < tagArr.length; i++) {
		$('.hashtag').append('<a href="./sbmain.sb?word=' + tagArr[i].split('#')[1] + '&item=hashtag">' + tagArr[i] + '</a> ');
	}

	
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
   var NO=$('#NO').val();
      if(ans){
         $.ajax({
         type : "POST",
         contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
         data : {"CMT_SUBJECT_NO" : NO, "CMT_NO" : CMT_NO, "url" : "sbview.sb?ajax=", 
            "CMT_BOARD_NAME" : board_name},
         url : "./cmtdelete.cmt",
         success: function(data){
            console.log(data.length)
            $('.comment_view').empty().append(data);
            if(data.length > 5){
               $('#comment_count').text($('#comment_count').text()*1-1);
            }
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
      data : {"CMT_NO" : CMT_NO},
      url : "./sellboard/sbcommentreply.jsp",
      success: function(data){
         console.log(data);
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
   var NO=$('#NO').val();
   var data={"CMT_CONTENT" : content, "CMT_SUBJECT_NO" : NO, "CMT_BOARD_NAME" : board_name, 
            "CMT_NO" : CMT_NO, "url" : "sbview.sb?ajax="};
   
   $.ajax({
      type : "POST",
      contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
      data : data,
      url : "./cmtreply.cmt",
      success: function(data){
         $('.comment_view').empty().append(data);
         if(data.length > 100){
            $('#comment_count').text($('#comment_count').text()*1+1);
         }
      },
      error: function() {
         alert("error");
      }
   });
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

function initMap() {
	var centerLocation = {
		lat : lat,
		lng : lng
	};
	console.log(centerLocation)

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 17,
		center : centerLocation
	});
	
	marker = new google.maps.Marker({
		position : centerLocation,
		map : map
	});
}
