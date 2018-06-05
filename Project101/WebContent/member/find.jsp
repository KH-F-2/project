<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(window).load(function(){
		$("#btnfindUid").click(btnfindUid);
		$("#btnfindPW").click(btnfindPW);
	});
	
	function btnfindUid(event){
		
		var nickname =$("#nickname");
		
		if(nickname.val() == "" ){
			alert("닉네임을 입력하세요");
			return;
		}
		
		var phone = $("#phone");
		if(phone.val() == ""){
			alert("연락처를 입력하세요");
			return;
		}
		
		/* $.post("findidResult.me",{
			email : email.val(),
			name : name.val()
		}, function(data){
			alert(eval(data).result);
		}); */
		
		$.ajax({
			type: "post",
			data: 'nickname=' + nickname.val() + '&phone=' + phone.val(),
			url: 'findIDResult.me',
			success: function(result) {
				 alert(result); 
			},
			error: function() {
				console.log('error');
			}
		});
	}
	
	function btnfindPW(event){
		var email =$("#email");
		if(email.val() == ""){
			alert("이메일을 입력하세요");
			return;
		}
		
		var nickname = $("#pwnickname");
		if(nickname.val() ==""){
			alert("닉네임을 입력하세요");
			return;
		}
		
		var phone = $("#pwphone");
		if(phone.val() == ""){
			alert("연락처를 입력하세요");
			return;
		}
		
		$.ajax({
			type: "post",
			data: 'email=' + email.val() +'&nickname=' + nickname.val() + '&phone=' + phone.val(),
			url: 'findPWResult.me',
			success: function(result) {
			 	alert(result); 
			
			
			
			},
			error: function() {
				console.log('error');
			}
		});
	}
</script>
</head>
<body>
	<a href="main.me">홈으로</a> 
	<a href = "signin.me"> 로그인 하러가기</a>
	
	<div class="leftt">
			<form name="form1" action="findIDResult.me" method="post">
		<fieldset>
			<legend class="hidden">ID/비밀번호 찾기</legend>
			<ul id ="findid">
				<li><label>닉네임</label><input type="text" id="nickname" name="nickname"/></li>
				<li><label>연락처</label><input type = "text" id ="phone" name="phone"/></li>
				<li>
				<input type= "button" name = "btnfindUid" id="btnfindUid" value="아이디찾기/"/>
				</li>
			</ul>
		</fieldset>
		
		</form>
	</div>
	
	<div>
		<form name="form2" action="findPWResult.me" method="post">
			<fieldset>
				<ul id="findPW">
					<li>비밀번호 찾기</li>
					<li><label>Email</label><input type="text" name="email" id="email"></li>
					<li><label>닉네임</label><input type="text" name="nickname"	 id="pwnickname"></li>
					<li><label>연락처</label><input type="text" name= "phone"	id="pwphone"></li>
					<li>
						<input type="button" id="btnfindPW" name="btnfindPW" value="비밀번호 찾기"/>
					</li>
				</ul>
			</fieldset>
		</form>
	</div>
</body>
</html>