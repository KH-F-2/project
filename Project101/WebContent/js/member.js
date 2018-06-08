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
	
	$('#brandsel').change(function() {
		$('#brand').val($('#brandsel').val());
	});

	$('#postcode').click(function() {
		window.open("post.html", "post", 'width=300, height=250')
	});
	
	$(function() {
		$("#postcodify_search_button").postcodifyPopUp();
	});
	
	$('input').keyup(function () {
		$(this).css('border-bottom', '1px solid black');
	});
	$('#sel').change(function () {
		$('#domain').css('border-bottom', '1px solid black');
	});

	$('form').submit(function() {
		if ($('#id').val() == '') {
			$('html').scrollTop($('#id').offset().top - 100);
			$('#id').focus();
			$('#id').css('border-bottom', '1px solid red');
			$('#id').attr('placeholder', 'ID를 입력하세요.');
			return false;
		}
		
		if ($('#nickname').val() == '') {
			$('html').scrollTop($('#nickname').offset().top - 100);
			$('#nickname').focus();
			$('#nickname').css('border-bottom', '1px solid red');
			$('#nickname').attr('placeholder', '닉네임을 입력하세요.');
			return false;
		}
		
		if ($('#email').val() == '') {
			$('html').scrollTop($('#email').offset().top - 100);
			$('#email').focus();
			$('#email').css('border-bottom', '1px solid red');
			$('#email').attr('placeholder', '이메일을 입력하세요.');
			return false;
		}
		
		if ($('#domain').val() == '') {
			$('html').scrollTop($('#domain').offset().top - 100);
			$('#domain').focus();
			$('#domain').css('border-bottom', '1px solid red');
			$('#domain').attr('placeholder', '도메인을 입력하세요.');
			return false;
		}

		if ($('#password').val() == '') {
			$('html').scrollTop($('#password').offset().top - 100);
			$('#password').focus();
			$('#password').css('border-bottom', '1px solid red');
			alert('비밀번호를 입력하세요.');
			return false;
		}

		if ($('#passcheck').val() == '') {
			$('html').scrollTop($('#passcheck').offset().top - 100);
			$('#passcheck').focus();
			$('#passcheck').css('border-bottom', '1px solid red');
			alert('비밀번호를 다시한번 입력하세요.');
			return false;
		} else {
			if ($('#password').val() != $('#passcheck').val()) {
				$('html').scrollTop($('#passcheck').offset().top - 100);
				$('#passcheck').val('');
				$('#passcheck').focus();
				$('#passcheck').css('border-bottom', '1px solid red');
				alert('비밀번호를 동일하게 입력하세요.');
				return false;
			}
		}
		
		if ($('#phone').val() == '') {
			$('html').scrollTop($('#phone').offset().top - 100);
			$('#phone').focus();
			$('#phone').css('border-bottom', '1px solid red');
			$('#phone').attr('placeholder', '연락처를 입력하세요.');
			return false;
		}

		if ($('#post').val() == '') {
			$('html').scrollTop($('#post').offset().top - 100);
			$('#post').focus();
			$('#post').css('border-bottom', '1px solid red');
			$('#post').attr('placeholder', '우편번호를 입력하세요.');
			return false;
		}

		if ($('#address').val() == '') {
			$('html').scrollTop($('#address').offset().top - 100);
			$('#address').focus();
			$('#address').css('border-bottom', '1px solid red');
			$('#address').attr('placeholder', '주소를 입력하세요.');
			return false;
		}

		if ($('#markerLat').val() == '') {
			$('html').scrollTop($('#locationField').offset().top - 100);
			alert('지도에서 기본 위치를 설정하세요.');
			return false;
		}

		if (idDuplicateCheckResult == false) {
			alert('아이디 중복체크를 실행하세요.');
			$('#id').focus();
			return false;
		}

		if (nickDuplicateCheckResult == false) {
			alert('닉네임 중복체크를 실행하세요.');
			$('#nickname').focus();
			return false;
		}

	});
	
	var id, id2, nickname;
	var idDuplicateCheckResult = false;
	var nickDuplicateCheckResult = false;
	
	$("#idcheck").click(function() {
		id = $("input[name=id]").val();
		
		if (id == '') {
			alert('아이디를 입력해주세요')
			return false;
		}
		
		$.ajax({
			type : "GET",
			data : {
				"id" : id
			},
			url : "idcheck.me",
			success : function(result) {
				console.log(result);
				if (result == 1) {
					$("#message").html('사용 가능한 아이디입니다.').css('color', 'blue');
					$('#id').attr('readonly');
					idDuplicateCheckResult = true;
				}
				if (result == -1) {
					$("#message").html('중복된 아이디입니다.').css('color', 'red');
					$("input[name=id]").val('');
					$("input[name=id]").focus();
				}
			}
		}); //ajax end
	}) //click end
	
	$("#nicknamecheck").click(function() {
		nickname = $("input[name=nickname]").val();
		
		if (nickname == '') {
			alert('닉네임을 입력해주세요')
			return false;
		}
		
		$.ajax({
			type : "GET",
			data : {
				"nickname" : nickname
			},
			url : "nicknamecheck.me",
			success : function(result) {
				console.log(result);
				if (result == 1) {
					$("#nickmessage").html('사용 가능한 닉네임입니다.').css('color', 'blue');
					$('#nickname').attr('readonly');
					nickDuplicateCheckResult = true;
				}
				if (result == -1) {
					$("#nickmessage").html('중복된 닉네임입니다.').css('color', 'red');
					$("input[name=nickname]").val('');
					$("input[name=nickname]").focus();
				}
			}
		}); //ajax end
	}) //click end

	$('select').prettyDropdown({
		height: 25
	});
	
	$(document).on('click', '#check1', function () {
		console.log($(this).val());
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
	
	$('.check.sub').click(function () {
		if ($(this).val() == '') {
			$(this).html('&#xe800;');
			$(this).val('checked');
		} else {
			$(this).html('&#xe801;');
			$(this).val('');
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