$(document).ready(function(){
	$('.write_btn').click(function(){
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