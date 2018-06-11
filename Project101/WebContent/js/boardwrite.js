$(document).ready(function(){
	UPLOADCARE_LOCALE = "ko";
	UPLOADCARE_TABS = "file url";
	UPLOADCARE_PUBLIC_KEY = "c45d0fc9bcc9538a677e";
	UPLOADCARE_LOCALE_TRANSLATIONS = {
		buttons: {
		    cancel: 'Cancel',
		    remove: 'Remove',
		    choose: {
		    	images: {
		        one: '파일 첨부',
		        other: '파일 첨부'
		        }
			}
		}
	};
	var widget=uploadcare.MultipleWidget('[role=uploadcare-uploader]');
	widget.onUploadComplete(function(info){
		var url=[];
		console.log(info.cdnUrl);
		$('#showImage').empty();
		$('#img_hidden').attr('value', '');
		var length=info.cdnUrl.charAt(info.cdnUrl.length-2);
		for(var i=0;i<length;i++){
			url[i]=info.cdnUrl+"nth/"+i+"/";
			$('#showImage').append('<img src="'+url[i]+'-/resize/x100/"/>');
			url[i]+="-/resize/500x/ ";
			var val=$('#img_hidden').attr('value');
			$('#img_hidden').attr('value', val+url[i]);
		}
	});
	
	
	$('#write_submit').submit(function () {
		board_type = $('input[type="radio"]:checked').val();
		if(board_type == 1){
			$('#write_submit').attr('action', "./pbwriteAction.pb");
		} else {
			$('#write_submit').attr('action', "./sbwriteaction.sb");
		}

		if($('#markerLat').val()=='' || $('#markerLng').val()==''){
            alert('위치를 지정해주세요!');
            return false;
        }
		if($('input[name=TITLE]').val()==''){
        	alert('제목을 입력하세요');
        	$('input[name=TITLE]').focus();
        	return false;
        }
		if($('select').val()==0){
			alert('카테고리를 선택해 주세요');
			return false;
		}
		
        if($('input[name=PRICE]').val()==''){
            alert("가격을 입력하세요");
            $('input[name=SB_PRICE]').focus();
            return false;
        }
        if(isNaN($('input[name=PRICE]').val())){
            alert('가격은 숫자로 입력하세요');
            $('input[name=SB_PRICE]').focus();
            $('input[name=SB_PRICE]').val('');
            return false;
        }
        if($('textarea[name=CONTENT]').val()==''){
            alert("내용을 입력하세요");
            return false;
        }
        if($('#img_hidden').val()==''){
            alert('이미지를 등록해주세요!');
            return false;
        }
        
    });
	
	
	// 해시태그 입력 공간 너비 초기 조정
	$('#inputTag').css('width', $('#tagSection').width());
	
	// 입력 공간에 입력시 함수 실행
	$('#inputTag').keyup(function () {
		// 마지막 입력 값 index
		var len = $(this).val().length - 1;
		
		// 마지막 입력 값이 공백 일 때 태그를 생성 해 준다
		if ($(this).val().charAt(len) == ' ') {
			
			// 태그 최대 설정 갯수 제한
			if ($('.tag').length == 5) {
				alert('태그는 5개 까지만 설정 할 수 있습니다.');
				$(this).val('');
				
				return false;
			}
			
			// 첫 입력 값이 공백 일 때 리턴 false
			if ($(this).val() == ' ') {
				$(this).val('');
				
				return false;
			}
			
			// 중복 된 태그 생성 제한
			if ($('.tag').length) {
				for (var i = 0; i < $('.tag').length; i++) {
					if ($('.tag span').eq(i).html().split('#')[1] == $(this).val().trim()) {
						$(this).val('');
						
						return false;
					}
				}
			}
			
			// 태그 생성 보여줌 tag 클래스로
			$('#tagSection').append('<div class="tag"><span>#' + $(this).val().trim() + '</span><a href="#" class="close"> X </a></div>');
			$(this).val('');	// 입력 공간 비우기
			
			// 입력 공간을 오른쪽으로 밀고 폭을 줄여 준다
			$(this).css('left',  $('.tag').last().offset().left + $('.tag').last().width() + 15);
			$(this).css('width', $('#tagSection').width() - ($(this).offset().left - $('#tagSection').offset().left));
			
			// 생성 된 태그를 input type hidden에 value로 준다.
			if ($('#hashTag').val() == '') {	// 첫 태그는 공백 없이
				
				$('#hashTag').val($('.tag span').last().html());
			} else {	// 두 번째 부터 공백으로 구분
				
				$('#hashTag').val($('#hashTag').val() + ' ' + $('.tag span').last().html());
			}
		}
	});
	
	// X 버튼 클릭시 삭제 함수 실행
	$(document).on('click', '.close', function () {
		// input type hidden에 등록 된 value와 x버튼 클릭 한 text 변수
		var text = $(this).prev().html().trim();
		var hashArr = $('#hashTag').val().split(' ');
		
		// 둘을 비교해서 잘라낸다 splice(첫 인덱스, 잘라낼 인덱스 갯수, 대치할 값(선택)(없으면 걍 잘라냄))
		for (var i = hashArr.length - 1; i >= 0 ; i--) {
			if (hashArr[i] == text) {
				hashArr.splice(i, 1);
			}
		}

		// text 변수 비우고 input type hidden에 들어갈 값 다시 만들어 줌
		text = '';
		for (var i = 0; i < hashArr.length; i++) {
			text += hashArr[i] + ' ';
		}
		$('#hashTag').val(text.trim());
		
		// X 누른 div를 삭제한다
		$(this).parent().remove();

		// 입력 공간 좌표, 폭 재 설정 위한 과정
		var tagLen = 0;
		if ($('.tag').last().length) {
			tagLen = $('.tag').last().offset().left + $('.tag').last().width() + 15;
		} else {
			tagLen = $('#tagSection').offset().left + 1; 
		}
		
		$('#inputTag').css('left', tagLen);
		$('#inputTag').css('width', $('#tagSection').width() - ($('#inputTag').offset().left - $('#tagSection').offset().left));
		$('#inputTag').focus();
		
		return false;
	});
	
});

var map;
var markers = [];

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
			google.maps.event.removeListener(clickEvent);
			clickEvent = map.addListener('click', function(event) {
			    
				placeMarker(event.latLng);
			});
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
	markers.push(marker);
	
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