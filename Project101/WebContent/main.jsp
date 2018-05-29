<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Nice2MeetU</title>
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<!-- Semantic UI Library -->
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="semantic/semantic.min.js"></script>
	
	<!-- Google Map API -->
	<script src='https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js'></script>
	<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
	
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
		<span>Nice2MeetU</span><br>
		<h3>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Nulla hic perferendis iste sunt alias iusto, maiores recusandae
			minima vitae a suscipit consequatur vel praesentium est obcaecati
			ullam aspernatur. Beatae, qui.</h3>
		</div>
	
		<div id="locationField">
			<input id="autocomplete" placeholder="검색할 장소를 입력하세요." type="text" />
	    </div>
	
		<div id="map"></div>
	
		<div id="content_box">
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

	</div>
	
</body>

</html>