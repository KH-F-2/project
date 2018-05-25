<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="css/fontello-embedded.css">
	<link rel="stylesheet" type="text/css" href="css/fontello.css">
	<link rel="stylesheet" type="text/css" href="css/fontello-codes.css">
	<link rel="stylesheet" type="text/css" href="css/animation.css">
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<!-- Semantic UI Library -->
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
	<script src="semantic/semantic.min.js"></script>
	
	<!-- Google Map API -->
	<script src='https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js'></script>
	<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap'></script>
	
	<script src="js/main.js"></script>
</head>

<body>

	<div id="hiddenHeader">
		<button id="menuBtn1">&#xe800;</button>
		<span>Nice2MeetU</span> <a href="./signin.me">Sign in</a><br>
	</div>

	<div id="header">
		<button id="menuBtn2">&#xe800;</button>
		<c:set var="id" value="${id }" />
		<c:choose>
			<c:when test="${empty id }">
				<a href="./signin.me">Sign in</a>
				<br>
			</c:when>

			<c:otherwise>

				<a href="signout.me">Sign Out</a>
				<a href="mypage.me?id=${id }">${id }</a>
			</c:otherwise>
		</c:choose>

		<span>Nice2MeetU</span><br>
		<h3>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Nulla hic perferendis iste sunt alias iusto, maiores recusandae
			minima vitae a suscipit consequatur vel praesentium est obcaecati
			ullam aspernatur. Beatae, qui.</h3>
	</div>

	<div id="map"></div>

	<div id="container">
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
</body>
</html>