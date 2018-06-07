<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="member/joinform.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<script src="./js/member.js"></script>


<script>
	$(document).ready(function() {

		$('#sel').change(function() {
			$('#domain').val($('#sel').val());
		});
		$('#brandsel').change(function() {
			$('#brand').val($('#brandsel').val());
		});

		var id, id2,nickname;
		
		$("#idcheck").click(function() {
			id = $("input[name=id]").val();

			if (id == '') {
				alert('아이디를 입력해주세요')
				return false;
			}

			$.ajax({
				type : "GET",
				data : {
					"id" : id
				},
				url : "idcheck.me",
				success : function(result) {
					data_check = result;
					if (result == 1) {
						$("#message").html('사용 가능한 아이디입니다.').css('color', 'blue');
					}
					if (result == -1) {
						$("#message").html('중복된 아이디입니다.').css('color', 'red');
						$("input[name=id]").val('');
						$("input[name=id]").focus();
					}
				}
			}); //ajax end
		}) //click end
		$("#nicknamecheck").click(function() {
			nickname = $("input[name=nickname]").val();

			if (nickname == '') {
				alert('닉네임을 입력해주세요')
				return false;
			}

			$.ajax({
				type : "GET",
				data : {
					"nickname" : nickname
				},
				url : "nicknamecheck.me",
				success : function(result) {
					data_check = result;
					if (result == 1) {
						$("#nickmessage").html('사용 가능한 닉네임입니다.').css('color', 'blue');
					}
					if (result == -1) {
						$("#nickmessage").html('중복된 닉네임입니다.').css('color', 'red');
						$("input[name=nickname]").val('');
						$("input[name=nickname]").focus();
					}
				}
			}); //ajax end
		}) //click end


		$('form').submit(function() {
			id2 = $("input[name=id]").val();
	
			if (id != id2) {
				alert('id중복검사를 해주세요');
				return false;
			}
			
			if (!joinform.email.value || !joinform.domain.value) {
				alert(emailerror);
				joinform.email.focus();
				return;
			} else {
				if (joinform.email.value) {
					if (joinform.domain.value == 0) {
						// 직접입력
						if (joinform.email.value.indexOf("@") == -1) {
							alert(emailerror);
							joinform.domain.focus();
							return false;
						}
					} else {
						// 선택입력
						if (joinform.email.value.indexOf("@") != -1) {
							alert(emailerror);
							joinform.email.focus();
							return false;
						}
					}
				}
			}
		});
	
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});

	});

</script>

</head>
<body>
	<h1>회원가입 </h1>
	<div class ="sticky">
	<form name="joinform" action="joinProcess.me" method="post">
		<div class="first">
		<h1>기본정보</h1>
		<hr>
		<label>ID</label> <input type="text" name="id" id="id" placeholder="Enter Id" required> 
		<input type="button" id="idcheck" value="ID중복검사">
		<br>
		<span id="message"></span><br>
		<label>E-mail</label>
		<input type="text" name="email" id="email" placeholder="Enter email" required>
		@<input type="text" name="domain" id="domain">
		<select name="sel" id="sel">
			<option value="">직접입력</option>
			<option value="naver.com">naver.com</option>
			<option value="daum.net">daum.net</option>
			<option value="nate.com">nate.com</option>
			<option value="gmail.com">gmail.com</option>
		</select> 
		<br>
		<br>
		
		<label>비밀번호</label> 
		<input type="password" name="password" placeholder="Enter password" required>
		<br><br> 
		
		<label>비밀번호 확인</label> 
		<input type="password" name="passcheck" placeholder="Enter password" required>
		<br>
		</div>
		
		<div class="second">
		<h1>상세정보</h1>
		<hr>
		<label>닉네임</label> <input type="text" name="nickname" placeholder="Enter nickname" required>
		<input type="button" id="nicknamecheck" value="닉네임중복검사">
		<br>
		<span id="nickmessage"></span><br>
		<br><br>
			 
		<label>통신사</label> <input type="text" name="brand" id="brand" placeholder="통신사" size=2> 
		<select name="brandsel"	id="brandsel">
			<option value="LGU+">LGU+</option>
			<option value="SKT">SKT</option>
			<option value="KT">KT</option>
		</select>
		<br>
		<label>연락처</label> <input type="text" name="phone" placeholder="010-xxxx-xxxx" required>
		 <br><br> <br>
		
		<label>우편번호</label> <input type="text" name="post" class="postcodify_postcode5" placeholder="우편번호" />
		<button type="button" id="postcodify_search_button">검색</button> 
		 <br> 
		 
		 <label>주소</label>
		<input type="text" name="address" class="postcodify_address" size=40  placeholder="주소" />
		 <br>
		 
		<label>상세주소</label> 
		<input type="text" name="detailaddress" size=40  placeholder="상세주소">
		<br><br><br>
		</div>
		<div id="map">
								
		</div>
		
		
		<div class="clearfix">
			<button type="submit" class="submitbtn">회원가입</button>
			<button type="reset" class="cancelbtn">다시 작성</button>
		</div>
		
	</form>
	</div>
</body>
</html>