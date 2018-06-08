<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/main.css">

<!-- Google Map API -->
<script src='https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js'></script>
<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>

<script src="js/main.js"></script>

<script>
	$(document).ready(function () {
		$('#writeBtn').click(function () {
			var id = '<%=session.getAttribute("id") %>';
			
			if (id == 'null') {
				alert('로그인 후 이용할 수 있습니다.');
				
				return false;
			} else {
				location.href = './sbwriteview.sb';
			}
		});
	});
</script>

<div id="main_head">
	<span>Nice2MeetU</span><br>
	<h3>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
		Nulla hic perferendis iste sunt alias iusto, maiores recusandae minima
		vitae a suscipit consequatur vel praesentium est obcaecati ullam
		aspernatur. Beatae, qui.</h3>
</div>

<div id="locationField">
	<input id="autocomplete" placeholder="검색할 장소를 입력하세요." type="text" />
</div>
<div id="chkBtnDiv">
	<input type="button" id="checkCurrentPosition" value="현재위치에서 조회">
</div>

<div id="map"></div>

<<<<<<< HEAD
<div id="container"></div>

<div id="main_bottom">
	<input type="button" id="seeMoreBtn" value="더보기">
	<input type="button" id="writeBtn" value="글 쓰러 가기">
</div>
=======
<div id="chkBtnDiv">
	<input type="button" id="checkCurrentPosition" value="현재위치에서 조회">
</div>

<div id="container">
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
	<div class="content">
		<img src="./image/koala.jpg">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
		<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
			Magnam nam iste velit aut dolores praesentium corporis quasi,
			laboriosam porro ab.</span>
	</div>
</div>
>>>>>>> jusung
