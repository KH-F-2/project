<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>판매게시판</title>
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<!-- Semantic UI Library -->
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="semantic/semantic.min.js"></script>
	
	
	<script src="js/main.js"></script>
	
</head>

<body>

	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle"><i>&#xe801;</i>  우리지금만나</h1>
		<h2>Category</h2>
		<a class="item" href="pbmain.pb">구매게시판</a>
		<a class="item" href="sbmain.sb">판매게시판</a>
		<a class="item" href="ebmain.eb">후기게시판</a>
		<a class="item" href="rbmain.rb">신고게시판</a>
	</div>
	
	<div class="pusher">
	
		<div id="header">
			<jsp:include page='header.jsp' />
		</div>
		
		<div id="main_top">
		<span>회원님의 등록된 판매 게시글</span><br>
		</div>
		
		<div id = "list_category">
			<a class = "item" href = "sellerpage_main.me?writer=${writer }">앨범형</a>
			<a class = "item" href = "sellerpage_main2.me?writer=${writer }">게시판형</a>
			
		</div>
	
		<div id="content_box">
			<div class="content">
				<img src="./image/koala.jpg">
				<p></p>
				<span></span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p></p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
			<div class="content">
				<img src="./image/koala.jpg">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
				<span>Lorem ipsum dolor sit amet, consectetur adipisicing
					elit. Magnam nam iste velit aut dolores praesentium corporis quasi,
					laboriosam porro ab.</span>
			</div>
		</div>

	</div>
	
</body>

</html>