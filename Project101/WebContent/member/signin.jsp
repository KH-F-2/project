<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String cookie = request.getHeader("Cookie");	
	String remember = "";

	if (cookie != null) {
		
		Cookie[] cookies = request.getCookies();
		
		for (Cookie coo : cookies) {
			
			if (coo.getName().equals("rememberID")) {
				
				remember = coo.getValue();
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<link href="./css/signin.css" rel="stylesheet">

	<script>
		$(document).ready(function() {
			$('#id').focus();
			var id = '<%=remember %>';
			
			if (id != "") {
				$('#rememberID').attr('checked', true);
				$('#id').val(id);
			}
			
			$('#rememberID').on('change', function() {
				
				if (id != "") {
					$('#id').val('');
					
					var noww = new Date();
					noww.setDate(noww.getDate() - 1);
					
					document.cookie = "rememberID=; path=/member/; expires=" + noww.toGMTString() + ";";
				}
			});
			
			$('#signInForm').submit(function () {
				$('#alertId').css('display', 'none');
				$('#alertPassword').css('display', 'none');
				
				if ($('#id').val() != "") {
					
					$.ajax({
						type: 'post',
						data: $(this).serialize(),
						url: 'signinprocess.me',
						success: function (result) {
							
							if (result == -1) {
								$('#alertId').css({
									'display': 'block',
									'color': 'red'
								});
								$('#id').focus();
							} else if (result == 0) {
								$('#alertPassword').css({
									'display': 'block',
									'color': 'red'
								});
								$('#password').focus();
							} else {
								location.href = "./main.map";
							}
						}
					});
					
				} else {
					
					alert("아이디를 입력하세요.");
				}
				
				return false;
			});
			
		});
	</script>
	
	
</head>
<body>
	<div id="signInDiv">
		<h1>로그인/회원가입</h1>
		<div class="form_div1">
			<form name="joinForm" method="post" id="signInForm">
				
				<div class="ff">
					<div id="alertId">We cannot find an account with that ID.</div>
					<h3>로그인을 위해 아이디와 비밀번호를 입력해 주십시오.</h3>
					<b>아이디</b>
					<input type="text" name="id" id="id" placeholder="Enter ID" required>
					
	
					<div id="alertPassword">Your Password is incorrect!</div>				
					<b>비밀번호</b>
					<input type="password" name="password" id="password" placeholder="Enter Password" required><br>
					<input type="checkbox" name="rememberID" id="rememberID"><label for="rememberID">Remember ID</label><br>
				</div>
				
				<div class="clearfix">
					<input type="submit" value="Sign In">
					<input type="button" value="아이디/비밀번호찾기" onclick="location.href='find.me'">
				</div>
			</form>
		</div>
		
		<div class="form_div2">
			<div id="signup">
			<h1>Nice2MeetU</h1><h3>회원으로 가입하시면 빠르고 편리하게 이용하실 수 있습니다.</h3>
			<br>
			<input type="button" value="회원가입" onclick="location.href='signup.me'">
			</div>
		</div>
		
	</div>
</body>
</html>