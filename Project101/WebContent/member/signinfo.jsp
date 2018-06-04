<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" type="text/css" href="css/fontello-embedded.css">
	<link rel="stylesheet" type="text/css" href="css/fontello.css">
	<link rel="stylesheet" type="text/css" href="css/fontello-codes.css">
	<link rel="stylesheet" type="text/css" href="css/animation.css">
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<!-- Semantic UI Library -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
	<script src="semantic/semantic.min.js"></script>
	<script src="js/main.js"></script>
</head>
<style>
td:first-child{background:#EAEAEA}
table{margin:0 auto; text-align: left; width:50%; height:400px}
td:last-child {
	background:white; text-align: center	
}
h2{
	text-align: center
}

</style>
<body>
	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle"><i>&#xe801;</i>우리지금만나</h1>
		<h2>Category</h2>
		<a class="item" id="sellBoard">구매게시판</a>
		<a class="item">판매게시판</a>
		<a class="item">후기게시판</a>
	</div>
	<div class="pusher">
		
		<div id="hiddenHeader">
			<button id="menuBtn1">&#xe800;</button>
			<span>Nice2MeetU</span>
			<a href="./signin.mem">Sign in</a><br>
		</div>
	<div id="header">
			<button id="menuBtn2">&#xe800;</button>
			<c:set var="id" value="${id }"/>
			<c:choose>
				<c:when test="${empty id }">
					<a href="./signin.me">Sign in</a><br>
				</c:when>
	
				<c:otherwise>

					<a href="signout.me">Sign Out</a>
					<a href="signmypage.mem?id=${id }">${id }</a>
				</c:otherwise>
			</c:choose>
			</div>
	</div>
	
	<h2>마이페이지</h2>
	<hr width="52%" color="#86E57F">
	<c:set var ="m" value="${signinfo}"/>
   <table border=1>
      <tr>
         <td class="first_td">아이디</td>
         <td>${m.id}</td>
      </tr>
      <tr>
         <td>비밀번호</td>
         <td>${m.password}</td>
      </tr>
      <tr>
         <td>이름</td>
         <td>${m.name}</td>
      </tr>
      <tr>
         <td>나이</td>
         <td>${m.age}</td>
      </tr>
      <tr>
         <td>성별</td>
         <td>${m.gender}</td>
      </tr>
      <tr>
         <td>이메일</td>
         <td>${m.email}</td>
      </tr>
      <tr>
      	<td>주소</td>
      	<td>${m.address }</td>
      </tr>
      <tr>
      	<td>전화번호</td>
      	<td>${m.phonenum }</td>
      </tr>      
      <tr>
	      <td>학교명</td>
	      <td>${m.school }</td>
      </tr>
      <tr>
	      <td>학과명</td>
	      <td>${m.department }</td>
      </tr>
	  <tr>
	      <td>학번</td>
	      <td>${m.studentid }</td>
      </tr>
      <tr>
         <td>
         <a href="main.me">리스트로 돌아가기</a></td>
         <td>
         <a href="#" onclick="location.href='signepil.me?name=${m.name}'">후기작성하기</a></td>
      </tr>
      <tr>
      	<td>
      	<a class="item" href="signendhistory.mem">거래종료내역</a>
      	</td>
      	<td>
      	<a class="trade" href="signtradelist.mem">거래중물품</a>
      	</td>
      </tr>
   
   </table>
</body>