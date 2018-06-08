<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/find.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(window).load(function(){
		$("#btnfindUid").click(btnfindUid);
		$("#btnfindPW").click(btnfindPW);
	});
	
	function btnfindUid(event){
		
		var email = $("#email");
		if(email.val() == ""){
			alert("Email를 입력하세요");
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
			data:  'email=' + email.val(),
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
		var id =$("#id");
		if(id.val() == ""){
			alert("이메일을 입력하세요");
			return;
		}
		
	
		
		var email = $("#pwemail");
		if(email.val() == ""){
			alert("Email를 입력하세요");
			return;
		}
		
		$.ajax({
			type: "post",
			data: 'id=' + id.val() + '&email=' + email.val(),
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
	
	<div id ="findform">
	<div id="findform_id">
			<form name="form1" action="findIDResult.me" method="post">
		
			<b id="hidden_id">ID 찾기</b><br>
			
			<h6>아이디를 잊으신경우  Email인증된 아이디를 입력해주세요.</h6>
			<ul id ="findid">
				
				<li><label>Email</label><input type = "text" id ="email" name="email"/></li>
				<li>
				<input type= "button" name = "btnfindUid" id="btnfindUid" value="아이디찾기"/>
				</li>
			</ul>
		
		
		</form>
	</div>
	
	<div id="findform_pw">
		<form name="form2" action="findPWResult.me" method="post">
			
				<b id="hidden_pw">비밀번호 찾기</b>
				
				<h6>비밀번호를 잊으신경우  아이디, Email을 입력해주세요.</h6>
				<ul id="findPW">
					<li><label>아이디</label><input type="text" name= "id"	id="id"></li>
					
					<li><label>Email</label><input type="text" name="email" id="pwemail"></li>
					<li>
						<input type="button" id="btnfindPW" name="btnfindPW" value="비밀번호 찾기"/>
					</li>
				</ul>
			
		</form>
	</div>
	</div>
</body>
</html>