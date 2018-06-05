<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script>
	$(document).ready(function(){
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});
		
		$(".cancelbtn").click(function(){
			location.href="main.me"
		});
	});
</script>
	<c:set var="m"	value="${memberinfo }"/>
<form name="joinform" action="Updatemember.me" method="post">
	<b>ID</b>
	<input type="text" name="id" value="${m.id }" readonly><br>
	<b>Email</b>
	<input type="text" name="email" value="${m.email}" readonly><br> 
<b>비밀번호</b>
<input type="password" name="pass" value="${m.password}" ><br>
<b>닉네임</b>
<input type="text" name ="nickname" value="${m.nickname }"><br>

<b>연락처</b>
<input type="text" name="phone" value="${m.phone }"><br>
<b>우편번호</b>
<input type="text" name="post" class="postcodify_postcode5" value="${m.post }">
<input type="button" id="postcodify_search_button" value="검색"><br>
<b>주소</b>
<input type="text" name="address"  size=40 class="postcodify_address" value="${m.address }"><br>
<b>상세주소</b>
<input type="text" name="detailaddress" size=40 value="${m.detailaddress}"><br>

<div class="clearfix">
<button type="submit" class="submitbtn">정보수정 </button>
<button type="reset" class="cancelbtn">다시작성</button>
</div>
</form>

