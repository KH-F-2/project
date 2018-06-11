
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
	
	marker = new google.maps.Marker({
		position : seoulCityhall,
		map : map
	});
}