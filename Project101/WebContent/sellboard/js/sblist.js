$(window).load(function () {
	var docHeight = $(document).height();
	var winHeight = $(window).height();
	console.log(docHeight);
	console.log(winHeight);
	console.log(docHeight - winHeight);
	var page = 1;
	
	var infiniteScroll = $(window).scroll(function () {
		console.log($(window).scrollTop());
		if ($(window).scrollTop() == (docHeight - winHeight)) {
			++page;
			console.log('바닥');

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
	
//	function onElementHeightChange(elm, callback){
//		var lastHeight = elm.clientHeight, newHeight;
//		(function run(){
//			newHeight = elm.clientHeight;
//			if( lastHeight != newHeight )
//				callback();
//			lastHeight = newHeight;
//
//	        if( elm.onElementHeightChangeTimer )
//	          clearTimeout(elm.onElementHeightChangeTimer);
//
//			elm.onElementHeightChangeTimer = setTimeout(run, 200);
//		})();
//	}
//
//
//	onElementHeightChange(document.body, infiniteScroll);
	
	$('#checkCurrentPosition').click(function () {
		checkCurrentPosition();
	});
	
	$('#moveTop').css({
		'cursor' : 'pointer',
		'position' : 'fixed',
		'left' : $(window).width() - 70,
		'top' : $(window).height() - 70,
	}).click(function () {
		$('body, html').animate({
			scrollTop : 0
		}, 500);
	});
	

	$('#search_input').keyup(function() {
		page = 1;
		var word = $('input[name=search_input').val();
		var item = $('#search_sel').val();
		
		$.ajax({
			type: 'post',
			data: {
				'page' : page,
				'centerLat' : location.search.split('&')[0].split('=')[1],
				'centerLng' : location.search.split('&')[1].split('=')[1],
				'word' : word,
				'item' : item,
				'state' : 'ajax',
			},
			url: './sbmain.sb',
			headers: {
				"cache-control": "no-cache",
				"pragma": "no-cache"
			},
			success: function (json) {
				console.log('검색 ajax 실행');
				$('#container').empty().append(json);
			},
			error: function () {
				console.log('error');
			}
		});
	});
	
	
});


//맵 필드 변수 설정
var map;
var markers = [];
var infoContentArr = [];
var infoWindowArr = [];

var startLat = null;
var startLng = null;
var endLat = null;
var endLng = null;

//맵 초기화 함수
function initMap() {
	var receivedCenter = {
		lat : location.search.split('&')[0].split('=')[1] * 1.0,
		lng : location.search.split('&')[1].split('=')[1] * 1.0
	};

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : receivedCenter
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

	checkCurrentPosition();


}

//현재 위치에서 조회
function checkCurrentPosition() {
	centerLat = map.center.lat();
	centerLng = map.center.lng();
	console.log(centerLat);
	console.log(centerLng);

	viewMarkers();
}

//ajax로 DB에서 json 받아 marker 생성
function viewMarkers() {

	if (centerLat) {
		$.ajax({
			type : "POST",
			data : 'centerLat=' + centerLat + '&centerLng=' + centerLng,
			dataType : 'json',
			url : "./getmarkers.map",
			 // 조회 전 기존 마커 제거
			beforeSend : function() {
				console.log('before');

				removeMarkers();
			},
			// json 받아 마커 생성 & 마커 클러스터 생성 & 마커 이벤트 추가
			success : function(json) {
				console.log('success : ' + json);

				getBoardListUsingCurrentPosition(json);

				for (var i = 0; i < json.length; i++) {
					var title = json[i].title;
					
					var infoContent = '<div id="iw-container"><div class="iw-title"><a href="sbview.sb?num=' + json[i].num + '">' + title + '</a>'
												+ '</div><div class="iw-content"><div class="iw-subTitle">' + json[i].price + '원</div>'
												+ '<img src="' + json[i].image_url + '" alt="./image/koala.jpg" height="115" width="83">'
												+ '<p>' + json[i].content + '</p></div><div class="iw-bottom-gradient"></div></div>';
					
					infoContentArr.push(infoContent);

					addMarkerWithTimeout(json[i], i, title);
				}

				console.log("현재 화면에 " + json.length + "개의 마커가 로드됨");
			},
			error : function(request, status, error){
			    alert("code:" + request.status + "\n" + "error:" + error + "message:" + request.responseText + "\n");
		   }
		});
	}
}

//마커 제거 함수
function removeMarkers() {
	console.log('삭제');
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}

// 시간 차 를 두고 마커 생성 & 이벤트 추가
function addMarkerWithTimeout(position, i, title) {
	window.setTimeout(function() {
		if (position.board_name == 'SELL_BOARD') {
			console.log('sell');
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
					labelOrigin : new google.maps.Point(11, 45),
					url : 'https://raw.githubusercontent.com/Concept211/Google-Maps-Markers/master/images/marker_blueP.png',
				}
			}));
		} else {
			console.log('purchase');
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
					labelOrigin : new google.maps.Point(11, 45),
					url : 'https://raw.githubusercontent.com/Concept211/Google-Maps-Markers/master/images/marker_redS.png',
				}
			}));
		}

		markers[i].addListener('click', function() {
			var infoWindow = new google.maps.InfoWindow({
				content : infoContentArr[i],
				maxWidth: 350
			});
			
			infoWindowArr.push(infoWindow);

			infoWindow.open(markers[i].get('map'), markers[i]);
			
			google.maps.event.addListener(map, 'click', function() {
				infoWindow.close();
			});
			
			google.maps.event.addListener(infoWindow, 'domready', function() {
			    var iwOuter = $('.gm-style-iw');
			    var iwBackground = iwOuter.prev();

			    iwBackground.children(':nth-child(2)').css({'display' : 'none'});
			    iwBackground.children(':nth-child(3)').css({'z-index' : '1000'});
			    iwBackground.children(':nth-child(4)').css({'display' : 'none'});

			    var iwCloseBtn = iwOuter.next();

			    iwCloseBtn.css({right: '38px', top: '3px', border: '7px solid black', 'border-radius': '13px', 'box-shadow': '0 0 5px gray'});

			    iwCloseBtn.mouseout(function(){
			      $(this).css({opacity: '1'});
			    });
			  });
			
		});

	}, i * 100);
}

function getBoardListUsingCurrentPosition(json) {
	$.ajax({
		type: "post",
		data: {
			"json" : JSON.stringify(json)
		},
		url: "./getboardlist.map",
		cache: false,
		headers: {
			"cache-control": "no-cache",
			"pragma": "no-cache"
		},
		success: function (data) {
			console.log('here1');
			$("#container").empty().prepend(data);
		},
		error: function () {
			alert("에러");
		}
	});
}