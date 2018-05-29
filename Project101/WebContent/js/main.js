$(document).ready(function() {

	// 반응형 사이즈 조절. 반씩
	$('#main_head').css('height', $(window).height() * 0.5 - 70);
	$('#map').css('height', $(window).height() * 0.5);
	$('#locationField').css('left', $(window).width() * 0.38);

});

// 맵 필드 변수 설정
var map;
var markers = [];

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

//	// DB에서 json으로 넘겨받아 마커 생성
//	var markers = jsonObj.map(function(spot, i) {
//		return new google.maps.Marker({
//			position : new google.maps.LatLng(spot.lat, spot.lng),
//			animation : google.maps.Animation.DROP,
//			label : {
//				color : 'black',
//				fontWeight : 'bold',
//				text : spot.title
//			},
//			icon : {
//				labelOrigin : new google.maps.Point(11, 50),
//				url : 'image/ryan.png',
//				size : new google.maps.Size(24, 40),
//				origin : new google.maps.Point(0, 0),
//				anchor : new google.maps.Point(11, 40),
//			}
//		})
//	});
//
//	// 마커 클러스터 생성
//	var markerCluster = new MarkerClusterer(
//			map,
//			markers,
//			{
//				imagePath : 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
//			});
//
//	// 각 마커에 클릭이벤트 할당
//	for (let i = 0; i < markers.length; i++) {
//		markers[i].addListener('click', function() {
//			map.setZoom(16);
//			var content = '<a href="http://www.naver.com">'
//					+ markers[i].label.text + '</a>' + '<br>가격 : ' + '10,000원'
//					+ '<br>설명 : ' + '블라블라';
//
//			var infoWindow = new google.maps.InfoWindow({
//				content : content
//			});
//
//			infoWindow.open(markers[i].get('map'), markers[i]);
//		});
//	}

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

	// idle : 가동 되지 않는
		map.addListener('idle', function() {
		startLat = map.getBounds().getSouthWest().lat();
		startLng = map.getBounds().getSouthWest().lng();
		endLat = map.getBounds().getNorthEast().lat();
		endLng = map.getBounds().getNorthEast().lng();

		viewMarkers();
	});
	
	
}

function checkCurrentPosition() {
	startLat = map.getBounds().getSouthWest().lat();
	startLng = map.getBounds().getSouthWest().lng();
	endLat = map.getBounds().getNorthEast().lat();
	endLng = map.getBounds().getNorthEast().lng();

	viewMarkers();
}

// 초기 로드 시 & 지도 중심이 움직이면 ajax로 DB에서 json 받아 marker 생성
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
				var kkk = 0; //마커 갯수 확인 용
				var infoContentArr = [];
				var markerList = json;
				var gap = /\+/g;
				console.log(json);
				console.log(markerList);
				// DB에서 json으로 넘겨받아 마커 생성
				markers = markerList.map(function(spot, i) {
					kkk++; //마커 갯수 확인 용
					var title = spot.sb_TITLE;
					/* var content = decodeURIComponent(spot.SB_CONTENT.replace(gap, " ") ); */
					
					var infoContent = '<a href="http://www.naver.com">'
						+ title + '</a>' + '<br>가격 : '
						+ spot.SB_PRICE + '<br>설명 : ' + '블라블라';
					infoContentArr.push(infoContent);
						
					return new google.maps.Marker({
						position: new google.maps.LatLng(spot.lat, spot.lng),
						animation: google.maps.Animation.DROP,
						label: {
							color: 'black',
							fontWeight: 'bold',
							text: title,
						},
						icon: {
							labelOrigin : new google.maps.Point(11, 50),
							url : './image/ryan.png',
							size : new google.maps.Size(24, 40),
							origin : new google.maps.Point(0, 0),
							anchor : new google.maps.Point(11, 40),
						}
					});
				});
				
				// 마커 클러스터 생성
				var markerCluster = new MarkerClusterer(map, markers, {
					imagePath : 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
				});
				
				// 각 마커에 클릭이벤트 할당
				for (let i = 0; i < markers.length; i++) {
					markers[i].addListener('click', function() {
						/* map.setZoom(16); */

						var infoWindow = new google.maps.InfoWindow({
							content : infoContentArr[i]
						});

						infoWindow.open(markers[i].get('map'), markers[i]);
					});
				}
				
				console.log("현재 화면에 " + kkk + "개의 마커가 로드됨");
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

