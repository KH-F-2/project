$(document).ready(function() {

	// 반응형 사이즈 조절. 반씩
	$('#main_head').css('height', $(window).height() * 0.5 - 70);
	$('#map').css('height', $(window).height() * 0.5);

	// 페이지 로드 후 1초 뒤 현재위치에서 조회 실행
	window.setTimeout(function () {
		checkCurrentPosition()
	}, 1000);
	
	$('#checkCurrentPosition').click(function () {
		checkCurrentPosition();
	});
	
});

// 맵 필드 변수 설정
var map;
var markers = [];
var infoContentArr = [];

var startLat = null;
var startLng = null;
var endLat = null;
var endLng = null;

// 맵 초기화 함수
function initMap() {
	var seoulCityhall = {
		lat : 37.566697,
		lng : 126.978457
	};
	
	// 구글 맵 객체 생성하고 센터, 줌 초기 설정
	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : seoulCityhall
	});
	
	// 현재 위치 정보를 가져와서 지도 이동시킴
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var currentPosition = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};

			map.setCenter(currentPosition);
		}, function() {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}


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

// 현재 위치에서 조회
function checkCurrentPosition() {
	startLat = map.getBounds().getSouthWest().lat();
	startLng = map.getBounds().getSouthWest().lng();
	endLat = map.getBounds().getNorthEast().lat();
	endLng = map.getBounds().getNorthEast().lng();

	viewMarkers();
}

// ajax로 DB에서 json 받아 marker 생성
function viewMarkers() {

	if (startLat) {
		$.ajax({
			type : "POST",
			data : 'startLat=' + startLat + '&startLng=' + startLng + '&endLat=' + endLat + '&endLng=' + endLng,
			dataType : 'json',
			url : "./getmarkers.map",
			 // 조회 전 기존 마커 제거
			beforeSend : function() {
				fnRemoveMarker();
			},
			// json 받아 마커 생성 & 마커 클러스터 생성 & 마커 이벤트 추가
			success : function(json) {

				for (var i = 0; i < json.length; i++) {
					var title = json[i].sb_TITLE;
					var infoContent = '<h3><a href="sbview.sb?num=' + json[i].SB_NO + '">'
						+ title + '</a></h3>' + '<br><b>가격</b> : '
						+ json[i].SB_PRICE + '<br><b>설명</b> : ' + '블라블라';
					infoContentArr.push(infoContent);
					
					addMarkerWithTimeout(json[i], i, title);
				}
				
				console.log("현재 화면에 " + json.length + "개의 마커가 로드됨");
			},
			error: function () {
				console.log('error');
			}
		});
	}
}

// 마커 제거 함수
function fnRemoveMarker() {
	console.log('삭제');
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}

// 시간 차 를 두고 마커 생성 & 이벤트 추가
function addMarkerWithTimeout(position, i, title) {
	window.setTimeout(function() {
		markers.push(new google.maps.Marker({
			position : new google.maps.LatLng(position.lat, position.lng),
			animation : google.maps.Animation.DROP,
			map: map,
			label : {
				color : 'black',
				fontWeight : 'bold',
				text : title,
			},
			icon : {
				labelOrigin : new google.maps.Point(11, 50),
				url : './image/ryan.png',
				size : new google.maps.Size(24, 40),
				origin : new google.maps.Point(0, 0),
				anchor : new google.maps.Point(11, 40),
			}
		}));
		
		markers[i].addListener('click', function() {
			var infoWindow = new google.maps.InfoWindow({
				content : infoContentArr[i],
				maxWidth: 200
			});

			infoWindow.open(markers[i].get('map'), markers[i]);
		});
		
	}, i * 200);
}

// 현재 위치정보 가져올 때 예외처리
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ? 
			'Error: The Geolocation service failed.'
			: 'Error: Your browser doesn\'t support geolocation.');
}

