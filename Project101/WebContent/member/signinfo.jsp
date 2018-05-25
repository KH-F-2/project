<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
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

</style>
<body>
	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle"><i>&#xe801;</i>  �츮���ݸ���</h1>
		<h2>Category</h2>
		<a class="item" id="sellBoard">���ŰԽ���</a>
		<a class="item">�ǸŰԽ���</a>
		<a class="item">�ı�Խ���</a>
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
					<a href="./signin.mem">Sign in</a><br>
				</c:when>
	
				<c:otherwise>

					<a href="signout.mem">Sign Out</a>
					<a href="signmypage.mem?id=${id }">${id }</a>
				</c:otherwise>
			</c:choose>
			</div>
	</div>
	<c:set var ="m" value="${signinfo}"/>
   <table border=1>
      <tr>
         <td class="first_td">���̵�</td>
         <td>${m.id}</td>
      </tr>
      <tr>
         <td>��й�ȣ</td>
         <td>${m.password}</td>
      </tr>
      <tr>
         <td>�̸�</td>
         <td>${m.name}</td>
      </tr>
      <tr>
         <td>����</td>
         <td>${m.age}</td>
      </tr>
      <tr>
         <td>����</td>
         <td>${m.gender}</td>
      </tr>
      <tr>
         <td>�̸���</td>
         <td>${m.email}</td>
      </tr>
      <tr>
      	<td>�ּ�</td>
      	<td>${m.address }</td>
      </tr>
      <tr>
      	<td>��ȭ��ȣ</td>
      	<td>${m.phonenum }</td>
      </tr>      
      <tr>
	      <td>�б���</td>
	      <td>${m.school }</td>
      </tr>
      <tr>
	      <td>�а���</td>
	      <td>${m.department }</td>
      </tr>
	  <tr>
	      <td>�й�</td>
	      <td>${m.studentid }</td>
      </tr>
      <tr>
         <td colspan=2>
         <a href="main.mem">����Ʈ�� ���ư���</a></td>
      </tr>
   
   </table>
</body>