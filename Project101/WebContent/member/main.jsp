<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>Nice2MeetU</title>
	
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

<body>

	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle"><i>&#xe801;</i>  우리지금만나</h1>
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
					<a href="./signin.mem">Sign in</a><br>
				</c:when>
	
				<c:otherwise>

					<a href="signout.mem">Sign Out</a>
					<a href="mypage.mem?id=${id }">${id }</a>
				</c:otherwise>
			</c:choose>
			
			<span>Nice2MeetU</span><br>
			<h3>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nulla hic perferendis iste sunt alias iusto, 
			maiores recusandae minima vitae a suscipit consequatur vel praesentium est obcaecati ullam aspernatur. Beatae, qui.</h3>
		</div>
		
		<div id="map"></div>
		
		<div id="container">
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam nam iste velit aut dolores praesentium corporis quasi, laboriosam porro ab.</span>
			</div>
		</div>
		
		
		
	</div>
</body>
</html>