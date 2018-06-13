<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="./css/modifyinfo.css" rel="stylesheet">

<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>

<script>
	$(document).ready(function(){
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});
		
		$(".cancelbtn").click(function(){
			location.href="main.map"
		});
		
		$('#checkRetry').click(function () {
			var email = $('#email').val();
			
			
		});
		
	});
	

	var map;
	var markers = [];

	var startLat = null;
	var startLng = null;
	var endLat = null;
	var endLng = null;

	//맵 초기화 함수
	function initMap() {
		 registedPosition = {
			lat : $('#markerLat').val() * 1.0,
			lng : $('#markerLng').val() * 1.0
		};
		
		map = new google.maps.Map(document.getElementById('map'), {
			zoom : 14,
			center : registedPosition
		});

		clickEvent = map.addListener('click', function(event) {
		    
			placeMarker(event.latLng);
		});
		
		placeMarker(registedPosition);

		// 검색 자동 완성 기능 구현
		autocomplete = new google.maps.places.Autocomplete(document.getElementById('autocomplete'));

		autocomplete.addListener('place_changed', function() {
			var place = autocomplete.getPlace();
			if (place.geometry) {
				removeMarkers();
				google.maps.event.removeListener(clickEvent);
				clickEvent = map.addListener('click', function(event) {
				    
					placeMarker(event.latLng);
				});
				map.panTo(place.geometry.location);
				map.setZoom(15);
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
		markers.push(marker);
		
		map.setCenter(location);
		$('#markerLat').val(location.lat);
		$('#markerLng').val(location.lng);
		
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

	//마커 제거 함수
	function removeMarkers() {
		console.log('삭제');
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}
</script>


<c:set var="m"	value="${memberinfo }"/>

<div id = "memberinfo">	
	<h1>회원 정보수정</h1>
		
	<form name="joinform" action="Updatemember.me" method="post">
		<b>ID</b>
		<input type="text" name="id" value="${m.id }" readonly><br>
		
		<b>비밀번호</b>
		<input type="password" name="password" value="${m.password}" ><br>
		
		<b>닉네임</b>
		<input type="text" name ="nickname" value="${m.nickname }" readonly><br>
		
		<b>Email</b>
		<input type="text" id="email" name="email" value="${m.email}" readonly><br> 
		
		<b>EmailCheck</b>
		<c:choose>
			<c:when test="${m.emailcheck == 'checked'}">
				<input type="text" name="emailcheck" value="인증완료" readonly><br> 
			</c:when>
			<c:otherwise>
				<input type="text" name="emailcheck" value="미인증" readonly><br>
				<input type="button" name="checkRetry" id="checkRetry" value="인증메일 다시받기">
			</c:otherwise>
		</c:choose>
		
		<b>연락처</b>
		<input type="text" name="phone" value="${m.phone }"><br>
		
		<b>우편번호</b>
		<input type="text" name="post" class="postcodify_postcode5" value="${m.post }">
		<input type="button" id="postcodify_search_button" value="검색"><br>
		
		<b>주소</b>
		<input type="text" name="address"  size=40 class="postcodify_address" value="${m.address }"><br>
		
		<b>상세주소</b>
		<input type="text" name="detailaddress" size=40 value="${m.detailaddress}"><br><br>
		
		<input id="autocomplete" placeholder="검색할 장소를 입력하세요." type="text" />
		<div id="map"></div>
		<input type="hidden" id="markerLat" name="markerLat" value="${m.latitude }"><br>
		<input type="hidden" id="markerLng" name="markerLng" value="${m.longitude }"><br>
		
		<div class="clearfix">
			<button type="submit" class="submitbtn">정보수정 </button>
			<button type="reset" class="cancelbtn">다시작성</button>
		</div>
	
	</form>
</div>	

