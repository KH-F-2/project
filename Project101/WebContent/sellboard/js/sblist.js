var page = 1;
var centerLat = location.search.split('&')[0].split('=')[1] * 1.0;
var centerLng = location.search.split('&')[1].split('=')[1] * 1.0;
var category;
var word;
var item;

$(window).load(function () {
	
	$(window).scroll(infiniteScroll); 
	
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
		searchFunction();
	});
	
	$('#search_category').change(function () {
		searchFunction();
	});
	
	
});

// 입력 창에 검색어 입력 시 & 카테고리 변경 시 실행되는 함수
function searchFunction() {
	page = 1;		// 초기 페이지 설정
	word = $('input[name=search_input').val();		// 검색어 (없을 시 전체)
	item = $('#search_sel').val();							// 검색조건 (미선택 시 제목)
	category = $('#search_category').val();			// 카테고리 (미선택 시 전체)
	
	$.ajax({
		type: 'post',
		data: {
			'page' : page,
			// 지도 중심에서 가까운 위치 순으로 보여주기 위해 좌표 값 전달
			'centerLat' : centerLat,
			'centerLng' : centerLng,
			'category' : category,
			'word' : word,
			'item' : item,
			'state' : 'ajax',
		},
		url: './sbmain.sb',
		beforeSend : function () {
			// 기존의 마커와 인포 윈도우를 비워준다.
			infoContentArr = [];
			removeMarkers();
		},
		success: function (json) {
			// json을 리턴받아 마커를 생성 하고 인포윈도우를 띄워주고 리스트를 만들어주는 함수 들을 실행 한다.
			if (json.length != 0) {
				
				getBoardListUsingCurrentPosition(json);
				
				for (var i = 0; i < json.length; i++) {
					var title = json[i].title;
					
					var infoContent = '<div id="iw-container"><div class="iw-title"><a href="sbview.sb?num=' 
					+ json[i].num + '&board_name=' + json[i].board_name + '">' + title + '</a>'
					+ '</div><div class="iw-content"><div class="iw-subTitle">' + json[i].price + '원</div>'
					+ '<img src="' + json[i].image_url + '" alt="./image/koala.jpg" height="115" width="83">'
					+ '<p>' + json[i].content + '</p></div><div class="iw-bottom-gradient"></div></div>';
					
					infoContentArr.push(infoContent);
					
					addMarkerWithTimeout(json[i], i, title);
				}
			}
		},
		error: function () {
			console.log('error');
		}
	});
}

var prevJsonLen;	// 기존 마커에 이어 붙이기 위한 인덱스 용 변수

// 무한 스크롤 페이징 처리 함수
function infiniteScroll() {
	var docHeight = $(document).height();
	var winHeight = $(window).height();

	if ($(window).scrollTop() == (docHeight - winHeight)) {	// 바닥에 닿았을 때
		++page;	// 다음 페이지

		$.ajax({
			type: 'post',
			data: {
				'page' : page,
				'centerLat' : centerLat,
				'centerLng' : centerLng,
				'category' : category,
				'word' : word,
				'item' : item,
				'state' : 'ajax',
			},
			url: './sbmain.sb',
			beforeSend : function() {
				prevJsonLen = markers.length;	// 기존 마커에 이어 붙이기 위해 인덱스 설정
			},
			success: function (json) {
				// 다음 페이지 값이 있을 경우
				if (json.length != 0) {
					// json으로 리스트를 만들어 기존 리스트에 이어붙여주는 ajax 실행 함수
					getInfiniteBoardList(json);
					
					for (var i = 0; i < json.length; i++) {
						var title = json[i].title;
						
						var infoContent = '<div id="iw-container"><div class="iw-title"><a href="sbview.sb?num=' 
						+ json[i].num + '&board_name=' + json[i].board_name + '">' + title + '</a>'
						+ '</div><div class="iw-content"><div class="iw-subTitle">' + json[i].price + '원</div>'
						+ '<img src="' + json[i].image_url + '" alt="./image/koala.jpg" height="115" width="83">'
						+ '<p>' + json[i].content + '</p></div><div class="iw-bottom-gradient"></div></div>';
						
						infoContentArr.push(infoContent);
						
						addMarkerWithTimeout(json[i], i + prevJsonLen, title);		// 기존 마커에 이어 인덱스 전달
					}
				} else {
					// 다음 페이지 값이 없을 경우 다시 페이지를 원 위치로
					page--;
				}
			},
			error: function () {
				console.log('error');
			}
		});
	}
}


//맵 필드 변수 설정
var map;
var markers = [];
var infoContentArr = [];
var infoWindowArr = [];

//맵 초기화 함수
function initMap() {
	// 전달 받은 위도 경도 좌표 
	var receivedCenter = {
		lat : centerLat,
		lng : centerLng
	};

	// 전달 받은 좌표를 기준으로 맵을 생성 & 초기 줌 값 설정
	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : receivedCenter
	});

	// 현재 위치에서 조회하는 함수 실행
	checkCurrentPosition();

	// 검색 자동 완성 기능 구현
	autocomplete = new google.maps.places.Autocomplete(document.getElementById('autocomplete'));

	// 검색 자동 완성 기능을 이용해 장소가 변경 됐을 때 이벤트 발생
	autocomplete.addListener('place_changed', function() {
		var place = autocomplete.getPlace();
		if (place.geometry) {
			// 맵의 중심을 옮기고 줌 설정 
			map.panTo(place.geometry.location);
			map.setZoom(17);
			// 현재 위치에서 조회하는 함수 실행
			checkCurrentPosition();
		} else {
			document.getElementById('autocomplete').placeholder = 'Enter a city';
		}
	});
}

//현재 위치에서 조회하는 함수
function checkCurrentPosition() {
	// 맵의 현재 중앙 좌표값을 읽어 변수 설정
	centerLat = map.center.lat();
	centerLng = map.center.lng();

	// 기존 인포윈도우 배열을 비워준다.
	infoContentArr = [];
	// ajax로 DB에서 json을 리턴받아 marker를 생성 해주는 함수 실행
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
			beforeSend : function() {
				// 조회 전 기존 마커를 제거 하기 위한 함수 실행
				removeMarkers();
			},
			// json 리턴 받아 마커 생성
			success : function(json) {

				// 리턴 받은 json 으로 리스트를 보여주는 ajax를 실행하는 함수 실행
				getBoardListUsingCurrentPosition(json);

				// json 으로 마커의 인포 윈도우를 띄워 주기 위한 배열 생성
				for (var i = 0; i < json.length; i++) {
					var title = json[i].title;

					var infoContent = '<div id="iw-container"><div class="iw-title"><a href="sbview.sb?num=' + json[i].num 
												+ '&board_name=' + json[i].board_name + '">' + title + '</a>'
												+ '</div><div class="iw-content"><div class="iw-subTitle">' + json[i].price + '원</div>'
												+ '<img src="' + json[i].image_url + '" alt="./image/koala.jpg" height="115" width="83">'
												+ '<p>' + json[i].content + '</p></div><div class="iw-bottom-gradient"></div></div>';
					// 배열에 담는다
					infoContentArr.push(infoContent);

					// 생성 된 인포 윈도우 배열과 json으로 마커를 생성 해 주는 함수에 전달
					addMarkerWithTimeout(json[i], i, title);
				}
			},
			error : function(request, status, error){
				// 에러 발생 시 에러문 출력
			    alert("code:" + request.status + "\n" + "error:" + error + "message:" + request.responseText + "\n");
		   }
		});
	}
}

//마커 제거 함수
function removeMarkers() {
	for (var i = 0; i < markers.length; i++) {
		// 마커를 지도에서 지워준다
		markers[i].setMap(null);
	}
	// 마커가 담긴 배열을 비워준다.
	markers = [];
}

// 시간 차 를 두고 마커 생성 & 이벤트 추가 ( json의 좌표값, 순서, 지도에 표시해 줄 마커의 제목 )
function addMarkerWithTimeout(position, i, title) {
	window.setTimeout(function() {
		// 판매 게시글 일 경우 파란색 마커를 생성
		if (position.board_name == 'SELL_BOARD') {
			// 마커를 생성하고 마커 배열에 담는다.
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
					url : 'https://raw.githubusercontent.com/Concept211/Google-Maps-Markers/master/images/marker_blueS.png',
				}
			}));
			// 구매 게시글 일 경우 빨간색 마커를 생성
		} else {
			// 마커를 생성하고 마커 배열에 담는다.
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
					url : 'https://raw.githubusercontent.com/Concept211/Google-Maps-Markers/master/images/marker_redP.png',
				}
			}));
		}

		// 마커를 클릭 했을 때 정보 창을 띄워 주기 위한 이벤트를 설정
		markers[i].addListener('click', function() {
			var infoWindow = new google.maps.InfoWindow({
				content : infoContentArr[i],		// 인포 윈도우 배열에 담아 두었던 값
				maxWidth: 350
			});
			// 인포윈도우 배열에 담는다.
			infoWindowArr.push(infoWindow);
			// 마커를 클릭 했을 때 인포 윈도우를 오픈 해 준다.
			infoWindow.open(markers[i].get('map'), markers[i]);
			// 인포 윈도우 바깥의 지도 영역을 클릭 하면 인포윈도우를 닫아준다.
			google.maps.event.addListener(map, 'click', function() {
				infoWindow.close();
			});
			
			// 인포윈도우를 커스텀 하기 위해 기존에 구글에서 제공하는 인포윈도우를 제거해 준다.
			google.maps.event.addListener(infoWindow, 'domready', function() {
				var iwOuter = $('.gm-style-iw');		// 기존 구글에서 제공하는 인포 윈도우
				var iwBackground = iwOuter.prev();
				
				// 기존 인포윈도우를 가린다.
			    iwBackground.children(':nth-child(2)').css({'display' : 'none'});
			    iwBackground.children(':nth-child(3)').css({'z-index' : '1000'});
			    iwBackground.children(':nth-child(4)').css({'display' : 'none'});

			    // 인포윈도우 닫기 버튼을 달아준다.
			    var iwCloseBtn = iwOuter.next();
			    iwCloseBtn.css({right: '38px', top: '3px', border: '7px solid black', 'border-radius': '13px', 'box-shadow': '0 0 5px gray'});
			    iwCloseBtn.mouseout(function(){
			      $(this).css({opacity: '1'});
			    });
			  });
		});
	}, i * 100);
}

// 리턴 받은 json 으로 리스트를 보여주는 ajax를 실행하는 함수
function getBoardListUsingCurrentPosition(json) {
	$.ajax({
		type: "post",
		data: {
			// json을 java에서 읽을 수 있도록 문자열로 변환해 준다.
			"json" : JSON.stringify(json)
		},
		url: "./getboardlist.map",
		cache: false,
		headers: {
			"cache-control": "no-cache",
			"pragma": "no-cache"
		},
		success: function (data) {
			// 리턴 받은 결과를 리스트로 보여주도록 붙여준다.
			$("#container").empty().prepend(data);
		},
		error: function () {
			alert("에러");
		}
	});
}

function getInfiniteBoardList(json) {
	$.ajax({
		type: "post",
		data: {
			"json" : JSON.stringify(json),
			'state' : 'infinite'
		},
		url: "./getboardlist.map",
		cache: false,
		headers: {
			"cache-control": "no-cache",
			"pragma": "no-cache"
		},
		success: function (data) {
			$("#container").append(data);
		},
		error: function () {
			alert("에러");
		}
	});
}