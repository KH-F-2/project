<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>판매 게시판</title>
        <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        
         <!-- 지도 API -->
        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
        
        <script type="text/javascript">
	        $(document).ready(function(){
	        	$('.write_btn').click(function(){
	        		var id=<%=session.getAttribute("id")%>
	        		if(id==null){
	        			alert('로그인 후 이용하실 수 있습니다.');
	        			location.href='./signin.me';
	        			return false;
	        		}
	        	});
	        	
	        	
	        });
	        
	        
	        function initMap() {
	        	var seoulCityhall = {
	        		lat : 37.566697,
	        		lng : 126.978457
	        	};
	        	map = new google.maps.Map(document.getElementById('map'), {
	        		zoom : 14,
	        		center : seoulCityhall
	        	});
	        	
	        	clickEvent = map.addListener('click', function(event) {
	        	    
	        		placeMarker(event.latLng);
	        	});

	        	// 검색 자동 완성 기능 구현
	        	autocomplete = new google.maps.places.Autocomplete(document.getElementById('autocomplete'));

	        	autocomplete.addListener('place_changed', function() {
	        		var place = autocomplete.getPlace();
	        		if (place.geometry) {
	        			map.panTo(place.geometry.location);
	        			map.setZoom(17);
	        		} else {
	        			document.getElementById('autocomplete').placeholder = 'Enter a city';
	        		}
	        	});
	        	
	        }
	        function placeMarker(location) {
	        	marker = new google.maps.Marker({
	        		position : location,
	        		map : map,
	        		draggable: true,
	        	});
	        	
	        	map.setCenter(location);
	        	$('#markerLat').val(location.lat());
	        	$('#markerLng').val(location.lng());
	        	
	        	addDragEvent(marker);
	        	google.maps.event.removeListener(clickEvent);
	        }

	        function addDragEvent(marker) {
	        	marker.addListener('dragend', function (event) {
	        		map.setCenter(event.latLng);
	        		$('#markerLat').val(event.latLng.lat());
	        		$('#markerLng').val(event.latLng.lng());
	        	});
	        }
        </script>
		<link href="css/sblist.css" rel="stylesheet" type="text/css">
		<style>
			#map{
				width: 80%; 
				height: 400px; 
				margin: 0 auto; 
				margin-top: 20px;
			}
			.write{
				width: 80%;
				margin: 0 auto;
				margin-top: 20px;
				text-align: right;
				height: 35px;
			}
			.content_section{
				width: 80%;
				margin: 0 auto;
			}
			.card{
				position: relative;
				width: 200px;
				height: 340px;
				margin: 20px 15px 0px 15px;
				display: inline-block;
				border: 1px solid #cdd2d2;
				border-radius: 10px;
				transition: box-shadow .3s;
				overflow: hidden;
			}
			.card:hover{
				box-shadow: 0 0 13px rgba(33,33,33,0.2); 
				bottom: 2px;
			}
			.card *{
				width: 100%;
			}
			.card_img{
				height: 200px;
				overflow: hidden;
			}
			.content_img{
				min-width: 100%;
				min-height: 100%;
			}
			.card_content{
				height: 110px;
				padding: 10px;
				border-bottom: 1px solid #cdd2d2;
			}
			.content_span_title{
				font-weight: bold;
				font-size: 15pt;
			}
			content_span_price{
				color: skyblue;
			}
			.card_bottom{
				height: 30px;
				text-align: center;
			}
			.bottom_span{
				font-size: 8pt;
				color: silver;
			}
			.list_title{
				margin: 0 auto;
				width: 20%;
				padding: 10px;
				border-top: 3px solid #526bbe; 
				border-bottom: 3px solid #526bbe;
				text-align: center;
			}
			.card_a{
				text-decoration: none;
				color: black !important;
			}
			.card_a:hover{
				text-shadow: 0 0 3px rgba(0,84,255,0.6);
			}
			
			.write_btn {
				background-color: #c47135;
				border: none;
				color: #ffffff;
				cursor: pointer;
				display: inline-block;
				line-height: 1em;
				margin: 0 auto;
				font-size: 9pt;
				outline: none;
				padding: 8px 15px;
				letter-spacing: 2px;
				position: relative;
				text-transform: uppercase;
				font-weight: 700;
			}
			.write_btn:before, .write_btn:after {
				border-color: transparent;
				-webkit-transition: all 0.25s;
				transition: all 0.25s;
				border-style: solid;
				border-width: 0;
				content: "";
				height: 24px;
				position: absolute;
				width: 24px;
			}
			.write_btn:before {
				border-color: #c47135;
				border-right-width: 2px;
				border-top-width: 2px;
				right: -5px;
				top: -5px;
			}
			.write_btn:after {
				border-bottom-width: 2px;
				border-color: #c47135;
				border-left-width: 2px;
				bottom: -5px;
				left: -5px;
			}
			.write_btn:hover, .write_btn.hover {
				background-color: #c47135;
			}
			.write_btn:hover:before, .write_btn.hover:before,
			.write_btn:hover:after, .write_btn.hover:after {
				height: 100%;
				width: 100%;
			}
		</style>
	</head>
	<body>
		<div class="list_title">
			<h1>게시글 리스트</h1>
		</div>
		
		<div id="map">지도~</div>
		
		<div class="write">
			<button type="button" class="write_btn" onclick="location.href='./sbwriteview.sb'">글쓰기</button>
		</div>
		<section class="content_section">
			<c:forEach var="list" items="${arr}">
				<div class="card">
					<a class="card_a" href="./sbview.sb?num=${list.SB_NO }">
						<div class="card_img">
							<img class="content_img" src="${list.IMAGE_URL}">
						</div>
						<div class="card_content">
							<span class="content_span_title">${list.SB_TITLE}</span><br>
							<span class="content_span_price">${list.SB_PRICE}원</span>
							<br>${list.SB_DATE}
						</div>
						<div class="card_bottom">
							<span class="bottom_span">
								댓글 0 · 조회수 ${list.SB_READCOUNT}
							</span>
						</div>
					</a>
				</div>
			</c:forEach>
		</section>
		
	</body>
</html>