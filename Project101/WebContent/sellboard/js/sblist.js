$(document).ready(function () {
	var docHeight = $(document).height();
	var winHeight = $(window).height();
	
	var page = 1;
	
	$(window).scroll(function () {
		console.log($(window).scrollTop())
		if ($(window).scrollTop() == docHeight - (winHeight)) {
			++page;

			$.ajax({
				type: 'post',
				data: {
					'page' : page,
					'centerLat' : location.search.split('&')[0].split('=')[1],
					'centerLng' : location.search.split('&')[1].split('=')[1],
					'state' : 'ajax',
				},
				url: './sbmain.sb',
				headers: {
					"cache-control": "no-cache",
					"pragma": "no-cache"
				},
				success: function (json) {
					console.log('ajax 실행');
					$('#container').append(json);
				},
				error: function () {
					console.log('error');
				}
			});
		}
	});
	
});

function getBoardListUsingCurrentPosition(json, page) {
	$.ajax({
		type: "post",
		data: {
			"json" : JSON.stringify(json),
			'page': page,
		},
		url: "./getboardlist.map",
		cache: false,
		headers: {
			"cache-control": "no-cache",
			"pragma": "no-cache"
		},
		success: function (data) {
			$("#container").empty().prepend(data);
		},
		error: function () {
			alert("에러");
		}
	});
}
