$(document).ready(function() {
	
	$('#sel').change(function() {
		var a = $(this).val();

		if (a == '') {
			$('#domain').val('');
			$('#domain').focus();
		} else {
			$('#domain').val($(this).val());
		}
	});

	$('#postcode').click(function() {
		window.open("post.html", "post", 'width=300, height=250')

	});

	$('form').submit(function() {
		if ($('#id').val() == '') {
			alert("ID를 입력하세요");
			$('#id').focus();
			return false;
		}

		if ($('#pass').val() == '') {
			alert("pass를 입력하세요");
			$('#pass').focus();
			return false;
		}


		if ($('#email').val() == '') {
			alert("EMAIL을 입력하세요");
			$('#email').focus();
			return false;
		}
		if ($('#domain').val() == '') {
			alert("domain을 입력하세요");
			$('#domain').focus();
			return false;
		}

		if ($('#post').val() == '') {
			alert("우편번호 앞자리를 입력하세요");
			$('#post').focus();
			return false;
		}



		if ($('#address').val() == '') {
			alert("주소를 입력하세요");
			$('#address').focus();
			return false;
		}

		if ($('#school').val() == '') {
			alert("학교를 입력하세요");
			$('#school').focus();
			return false;
		}

		if ($('#major').val() == '') {
			alert("학번을 입력하세요");
			$('#major').focus();
			return false;
		}

		if ($('#college').val() == '') {
			alert("학과를 입력하세요");
			$('#college').focus();
			return false;
		}

		if ($('#pass').val() == '') {
			alert("비밀번호를 입력하세요");
			$('#pass').focus();
			return false;
		}
		if ($('#pass').val() != $('#passcheck').val()) {
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}


	});

	$('select').prettyDropdown({
		height: 25
	});
	
	$(document).on('click', '#check1', function () {
		if ($(this).val() == '') {
			$('.check').each(function () {
				$(this).html('&#xe800;');
				$(this).val('checked');
			});
		} else {
			$('.check').each(function () {
				$(this).html('&#xe801;');
				$(this).val('');
			});
		}
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
			removeMarkers();
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

//마커 제거 함수
function removeMarkers() {
	console.log('삭제');
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}