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
								location.href = "./main.me";
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
	
	<style>
		#signInDiv {
			width: 300px;
			height: 500px;
			display: block;
			margin: 0 auto;
		}
		
		#alertId, #alertPassword {
			display: none;
		}
		
	</style>
</head>
<body>
	<div id="signInDiv">
		<h1>Sign In</h1>
		<form name="joinForm" method="post" id="signInForm">
			
			<div class="ff">
				<div id="alertId">We cannot find an account with that ID.</div>
				<b>ID</b>
				<input type="text" name="id" id="id" placeholder="Enter ID" required>
				
				<input type="checkbox" name="rememberID" id="rememberID">Remember ID<br>

				<div id="alertPassword">Your Password is incorrect!</div>				
				<b>PASSWORD</b>
				<input type="password" name="password" id="password" placeholder="Enter Password" required><br>
			</div>
			
			<div class="clearfix">
				<input type="submit" value="Sign In">
			</div>
		</form>
		
		<hr>
		<input type="button" value="아이디/비밀번호찾기" onclick="location.href='find.me'">
		<input type="button" value="Create Your Account" onclick="location.href='signup.me'">
	</div>
</body>
</html>