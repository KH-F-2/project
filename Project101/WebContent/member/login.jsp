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
	<link rel="stylesheet" href="./member/aa.css" type="text/css">

	<script src="http://code.jquery.com/jquery-latest.js"></script>

	<script>
		$(document).ready(function() {
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
		});
	</script>
</head>
<body>
	<div>
		<a href="main.mem">뒤로 이동하기</a>
		<a href="javascript:history.back();">이전 페이지로 이동하기</a>
	</div>
	
	<h1>로그인 페이지</h1>
	<form action="loginProcess.net" name="joinForm" method="post">
		
		<div class="ff">
			<b>아이디</b>
			<input type="text" name="id" id="id" placeholder="Enter ID" required>
			
			<input type="checkbox" name="rememberID" id="rememberID">ID 기억하기<br>
			
			<b>비밀번호</b>
			<input type="password" name="password" id="password" placeholder="Enter Password" required><br>
		</div>
		
		<div class="clearfix">
			<input type="submit" value="로그인">
			<input type="button" value="회원가입" onclick="location.href='join.net'">
		</div>
	</form>
	
</body>
</html>