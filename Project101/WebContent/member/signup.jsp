<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/signupform.css" rel="stylesheet">

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

	
	
	<div id="sticky">
		<h1>회원가입 </h1>
		<form name="joinform" action="joinProcess.me" method="post">
			<div id="first">
				<h3>사용자 계정</h3>
				
				<div class="form-row">
					<label>ID</label>
					<div class="secondCol">
						<input type="text" name="id" id="id" placeholder="Enter Id" required> 
						<input type="button" id="idcheck" value="ID중복검사"><br>
						<span id="message"></span>
					</div>
				</div>
				
				<div class="form-row">
					<label>닉네임</label>
					<div class="secondCol">
						<input type="text" name="nickname" placeholder="Enter nickname" required>
						<input type="button" id="nicknamecheck" value="닉네임중복검사"><br>
						<span id="nickmessage"></span>
					</div>
				</div>
				
				<div class="form-row">
					<label>E-mail</label>
					<div class="secondCol">
						<input type="text" name="email" id="email" placeholder="Enter email" required>
						@<input type="text" name="domain" id="domain">
						<select name="sel" id="sel">
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
							<option value="gmail.com">gmail.com</option>
						</select>
					</div>
				</div>
				
				<div class="form-row">
					<label>비밀번호</label> 
						<div class="secondCol">
						<input type="password" name="password" placeholder="Enter password" required>
					</div>
				</div>
							
				<div class="form-row">	
					<label>비밀번호 확인</label>
					<div class="secondCol"> 
						<input type="password" name="passcheck" placeholder="Enter password" required>
					</div>
				</div>
			</div>
			
			<div id="second">
				<h3>상세정보</h3>
					 
				<div class="form-row">
					<label>통신사</label>
					<div class="secondCol">
						<input type="text" name="brand" id="brand" placeholder="통신사" size=6> 
						<select name="brandsel"	id="brandsel">
							<option value="LGU+">LGU+</option>
							<option value="SKT">SKT</option>
							<option value="KT">KT</option>
						</select>
					</div>
				</div>
				
				<div class="form-row">
					<label>연락처</label>
					<div class="secondCol">
						<input type="text" name="phone" placeholder="010-xxxx-xxxx" required><br>
					</div>
				</div>
				
				<div class="form-row">
					<label>우편번호</label>
					<div class="secondCol">
						<input type="text" name="post" class="postcodify_postcode5" placeholder="우편번호" />
						<input type="button" id="postcodify_search_button" value="우편번호 찾기"><br> 
					 </div>
				 </div>
				 
				 <div class="form-row">
					<label>주소</label>
					<div class="secondCol">
						<input type="text" name="address" class="postcodify_address" size=40  placeholder="주소" /><br>
					 </div>
				 </div>
				 
				 <div class="form-row">
					<label>상세주소</label>
					<div class="secondCol"> 
						<input type="text" name="detailaddress" size=40  placeholder="상세주소"><br>
					</div>
				</div>
			</div>
			
			<div id="map"></div>
			
			<div class="form-row">
				<div id="clearfix">
					<button type="submit" class="submitbtn">회원가입</button>
					<button type="reset" class="cancelbtn">다시 작성</button>
				</div>
			</div>
			
		</form>
	</div>
</body>
</html>