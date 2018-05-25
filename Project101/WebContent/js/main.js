
$(document).ready(function() {
	$('head').append("<script src='https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js'></script>");
	$('head').append("<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap'></script>");
	
	$('#header').css('height', $(window).height() * 0.5);
	$('#map').css('height', $(window).height() * 0.5);

	$('#menuBtn1, #menuBtn2').click(function() {
		$('.ui.sidebar').sidebar('toggle');
	})

	$(window).scroll(function() {
		var targetID = "#hiddenHeader";

		if ($(window).scrollTop() > $(window).height() / 2) {
			$(targetID).css({
				top : 0,
				position : 'fixed',
				display : 'block'
			});
		} else {
			$(targetID).css({
				top : '-' + $(targetID).height() + 'px',
				display : 'none',
				position : 'relative'
			});
		}
	}).trigger('scroll');

	
	
});

function initMap() {
	var seoulCityhall = {
			lat : 37.566697,
			lng : 126.978457
	};
	
	var map = new google.maps.Map(document
			.getElementById('map'), {
		zoom : 14,
		center : seoulCityhall
	});
	
	// Create an array of alphabetical characters used to
	// label the markers.
	var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	
	// Add some markers to the map.
	// Note: The code uses the JavaScript
	// Array.prototype.map() method to
	// create an array of markers based on a given
	// "locations" array.
	// The map() method here has nothing to do with the
	// Google Maps API.
	var markers = locations.map(function(location, i) {
		return new google.maps.Marker({
			position : location,
			label : labels[i % labels.length]
		});
	});
	
	// Add a marker clusterer to manage the markers.
	var markerCluster = new MarkerClusterer(
			map,
			markers,
			{
				imagePath : 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
			});
}
var locations = [ {
	lat : 37.569563,
	lng : 126.982180
}, {
	lat : 37.569313,
	lng : 126.976218
}, {
	lat : 37.568301,
	lng : 126.969727
}, {
	lat : 37.565802,
	lng : 126.967535
}, {
	lat : 37.555216,
	lng : 126.975418
}, {
	lat : 37.553465,
	lng : 126.976307
}, {
	lat : 37.534339,
	lng : 127.007979
}, {
	lat : 37.533622,
	lng : 127.006550
}, {
	lat : 37.528812,
	lng : 127.029051
}, {
	lat : 37.531569,
	lng : 127.030918
}, {
	lat : 37.522228,
	lng : 127.041072
}, {
	lat : 37.521645,
	lng : 127.040648
}, {
	lat : 37.514857,
	lng : 127.031953
}, {
	lat : -37.773700,
	lng : 145.145187
}, {
	lat : -37.774785,
	lng : 145.137978
}, {
	lat : -37.819616,
	lng : 144.968119
}, {
	lat : -38.330766,
	lng : 144.695692
}, {
	lat : -39.927193,
	lng : 175.053218
}, {
	lat : -41.330162,
	lng : 174.865694
}, {
	lat : -42.734358,
	lng : 147.439506
}, {
	lat : -42.734358,
	lng : 147.501315
}, {
	lat : -42.735258,
	lng : 147.438000
}, {
	lat : -43.999792,
	lng : 170.463352
} ]