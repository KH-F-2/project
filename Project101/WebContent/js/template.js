$(document).ready(function() {
	$('#navTitle').click(function() {
		location.href = 'main.map';
	});

	$('.item').click(function () {
		var value = $(this).val();
		
		if (value != 'report') {
			location.href = 'category.map?page=1&centerLat=' + centerLat + '&centerLng=' + centerLng + '&category=' + value;
		}
		
	});

});

//function searchFunction() {
//	page = 1;
//	word = $('input[name=search_input').val();
//	item = $('#search_sel').val();
//	category = $('#search_category').val();
//	
//	$.ajax({
//		type: 'post',
//		data: {
//			'page' : page,
//			'centerLat' : centerLat,
//			'centerLng' : centerLng,
//			'category' : category,
//			'word' : word,
//			'item' : item,
//			'state' : 'ajax',
//		},
//		url: './sbmain.sb',
//		headers: {
//			"cache-control": "no-cache",
//			"pragma": "no-cache"
//		},
//		beforeSend : function () {
//			infoContentArr = [];
//			removeMarkers();
//		},
//		success: function (json) {
//			
//			if (json.length != 0) {
//				getBoardListUsingCurrentPosition(json);
//				
//				for (var i = 0; i < json.length; i++) {
//					var title = json[i].title;
//					
//					var infoContent = '<div id="iw-container"><div class="iw-title"><a href="sbview.sb?num=' + json[i].num + '&board_name=' + json[i].board_name + '">' + title + '</a>'
//					+ '</div><div class="iw-content"><div class="iw-subTitle">' + json[i].price + '원</div>'
//					+ '<img src="' + json[i].image_url + '" alt="./image/koala.jpg" height="115" width="83">'
//					+ '<p>' + json[i].content + '</p></div><div class="iw-bottom-gradient"></div></div>';
//					
//					infoContentArr.push(infoContent);
//					
//					addMarkerWithTimeout(json[i], i, title);
//				}
//			}
//		},
//		error: function () {
//			console.log('error');
//		}
//	});
//}
//
//function getBoardListUsingCurrentPosition(json) {
//	console.log('여기서 받아줘요 : ' + json);
//	$.ajax({
//		type: "post",
//		data: {
//			"json" : JSON.stringify(json)
//		},
//		url: "./getboardlist.map",
//		cache: false,
//		headers: {
//			"cache-control": "no-cache",
//			"pragma": "no-cache"
//		},
//		success: function (data) {
//			console.log('here1');
//			$("#container").empty().prepend(data);
//		},
//		error: function () {
//			alert("에러");
//		}
//	});
//}
//
//
////시간 차 를 두고 마커 생성 & 이벤트 추가
//function addMarkerWithTimeout(position, i, title) {
//	window.setTimeout(function() {
//		if (position.board_name == 'SELL_BOARD') {
//			console.log('sell');
//			markers.push(new google.maps.Marker({
//				position : new google.maps.LatLng(position.lat, position.lng),
//				animation : google.maps.Animation.DROP,
//				map: map,
//				label : {
//					color : 'black',
//					fontWeight : 'bold',
//					text : title,
//				},
//				icon : {
//					labelOrigin : new google.maps.Point(11, 45),
//					url : 'https://raw.githubusercontent.com/Concept211/Google-Maps-Markers/master/images/marker_blueS.png',
//				}
//			}));
//		} else {
//			console.log('purchase');
//			markers.push(new google.maps.Marker({
//				position : new google.maps.LatLng(position.lat, position.lng),
//				animation : google.maps.Animation.DROP,
//				map: map,
//				label : {
//					color : 'black',
//					fontWeight : 'bold',
//					text : title,
//				},
//				icon : {
//					labelOrigin : new google.maps.Point(11, 45),
//					url : 'https://raw.githubusercontent.com/Concept211/Google-Maps-Markers/master/images/marker_redP.png',
//				}
//			}));
//		}
//
//		markers[i].addListener('click', function() {
//			var infoWindow = new google.maps.InfoWindow({
//				content : infoContentArr[i],
//				maxWidth: 350
//			});
//			
//			infoWindowArr.push(infoWindow);
//
//			infoWindow.open(markers[i].get('map'), markers[i]);
//			
//			google.maps.event.addListener(map, 'click', function() {
//				infoWindow.close();
//			});
//			
//			google.maps.event.addListener(infoWindow, 'domready', function() {
//			    var iwOuter = $('.gm-style-iw');
//			    var iwBackground = iwOuter.prev();
//
//			    iwBackground.children(':nth-child(2)').css({'display' : 'none'});
//			    iwBackground.children(':nth-child(3)').css({'z-index' : '1000'});
//			    iwBackground.children(':nth-child(4)').css({'display' : 'none'});
//
//			    var iwCloseBtn = iwOuter.next();
//
//			    iwCloseBtn.css({right: '38px', top: '3px', border: '7px solid black', 'border-radius': '13px', 'box-shadow': '0 0 5px gray'});
//
//			    iwCloseBtn.mouseout(function(){
//			      $(this).css({opacity: '1'});
//			    });
//			  });
//			
//		});
//
//	}, i * 100);
//}