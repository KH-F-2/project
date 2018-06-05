<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="member/joinform.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<script src="member/member.js"></script>
<script src="member/emailscript.js"></script>

<script>
	$(document).ready(function() {

		$('#sel').change(function() {
			$('#domain').val($('#sel').val());
		});
		$('#brandsel').change(function() {
			$('#brand').val($('#brandsel').val());
		});

		var id, id2;
		
		$("#idcheck").click(function() {
			id = $("input[name=id]").val();

			if (id == '') {
				alert('id를 입력해주세요')
				return false;
			}

			$.ajax({
				type : "GET",
				data : {
					"id" : id
				},
				url : "idcheck.mem",
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

		$('form').submit(function() {
			id2 = $("input[name=id]").val();

			if (id != id2) {
				alert('id중복검사를 해주세요');
				return false;
			}
			var emailconfirm_value;
			emailconfirm_value =$("input[name=emailconfirm_value]").val();
			if(emailconfirm_value != 1){
				alert('email 인증을 해주세요.');
				return false;
			}
		});
		
		$(function() { $("#postcodify_search_button").postcodifyPopUp(); });
		
		var email;
		
		$("#emailcheck").click(function(){
			email = $("input[name=email]").val() + "@" +$("input[name=domain]").val(); 	
			
			if(email == ''){
				alert("이메일을 입력하세요.");
				return false;
			}
			
		});
	})	;
	
	function emailcheck(email, domain){
	    // 유효성 검사
		if(!joinform.email.value || !joinform.domain.value){ 
			alert(emailerror);
			joinform.email.focus();
			return;
		}else{
			if(joinform.email.value){
				if(joinform.domain.value==0){
					// 직접입력
					if(joinform.email.value.indexOf("@")==-1){
						alert(emailerror);
						joinform.domain.focus();
						return false;
					}
				}else{
					// 선택입력
					if(joinform.email.value.indexOf("@")!=-1){
						alert(emailerror);
						joinform.email.focus();
						return false;
					}
				}
			}
		}
	    // 인증을 위해 새창으로 이동
		var url="emailCheck.mem?email="+email+"&domain="+domain;
		open(url,"emailwindow", "statusbar=no, scrollbar=no, menubar=no,  width=400, height=200" );
	}
		
</script>

</head>
<body>
	<h1>회원가입 </h1>
	<div class ="sticky">
	<form name="joinform" action="joinProcess.mem" method="post">
		<div class="first">
		<h1>기본정보</h1>
		<hr>
		<label>아이디</label> 
		<input type="text" name="id" placeholder="Enter id" required> 
		<input type="button" id="idcheck" value="ID중복검사"> <br>
		<span id="message"></span><br>
		<br>
		
		<label>비밀번호</label> 
		<input type="password" name="pass" placeholder="Enter password" required>
		<br><br> 
		
		<label>비밀번호 확인</label> 
		<input type="password" name="passcheck" placeholder="Enter password" required>
		<br>
		</div>
		
		<div class="second">
		<h1>상세정보</h1>
		<hr>
		<label>이름</label> <input type="text" name="name" placeholder="Enter name" required>
		<br><br><br>
		
		<label>E-mail</label> <input type="text" name="email" id="email" placeholder="Enter email" required>@ 
		<input type="text" name="domain" id="domain"> 
		
		<select name="sel" id="sel">
			<option value="">직접입력</option>
			<option value="naver.com">naver.com</option>
			<option value="daum.net">daum.net</option>
			<option value="nate.com">nate.com</option>
			<option value="gmail.com">gmail.com</option>
		</select> 
		<br>
		
		
		<input type="button" name="emailconfirm_btn" value="인증하기" 
         			onclick="emailcheck(joinform.email.value,joinform.domain.value)">
		<input type="hidden" name="emailconfirm_value">
		
		
		<br><br>	<br> 
		<label>통신사</label> <input type="text" name="brand" id="brand" placeholder="통신사" size=2> 
		<select name="brandsel"	id="brandsel">
			<option value="LGU+">LGU+</option>
			<option value="SKT">SKT</option>
			<option value="KT">KT</option>
		</select>
		<br>
		
		 <label>연락처</label> <input type="text" name="tel" placeholder="010-xxxx-xxxx" required>
		 <br><br> <br>
		
		<label>우편번호</label> <input type="text" name="post" class="postcodify_postcode5" placeholder="우편번호" />
		<button id="postcodify_search_button">검색</button> 
		 <br> 
		 
		 <label>주소</label>
		<input type="text" name="address" class="postcodify_address" size=40  placeholder="주소" />
		 <br>
		 
		<label>상세주소</label> 
		<input type="text" name="subaddress" size=40  placeholder="상세주소">
		<br><br><br>
		
		<label>학교</label>
		<input type="text" name="school" placeholder="Enter school" required>
		<br>
		<label>학과명</label>
		 <input type="text" name="major" required>
		<br>
		<label>학번</label>
	    <input type="text" name="college">
	    <br>	<br><br>
	    </div>
		
		<div class="clearfix">
			<button type="submit" class="submitbtn">회원가입</button>
			<button type="reset" class="cancelbtn">다시 작성</button>
		</div>
		
	</form>
	</div>
</body>
</html>