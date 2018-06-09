<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- 이미지 업로드 API -->
        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>
        
        <!-- 지도 API -->
        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap2&libraries=places'></script>

        <script src="js/boardwrite.js"></script>
        <link href="css/boardwrite.css" rel="stylesheet" type="text/css">
        <script>
			$(function(){
				var cate = '${boardBean.SB_CATEGORY}';
				$('.cate_option').each(function(){
					if ($(this).text() == cate) {
						$(this).attr('selected', 'selected');
					}
				});
				
			});
			
			function initMap2() {
				var sb_lat = ${boardBean.SB_LAT};
				var sb_lng = ${boardBean.SB_LNG};
				var mak = {
					lat : sb_lat,
					lng : sb_lng
				};

				map = new google.maps.Map(document.getElementById('map'), {
					zoom : 14,
					center : mak
				});
				(function (){
					marker = new google.maps.Marker({
						position : mak,
						map : map,
						draggable: true,
					});
					addDragEvent(marker);
				})();

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

			function addDragEvent(marker) {
				marker.addListener('dragend', function (event) {
					map.setCenter(event.latLng);
					$('#markerLat').val(event.latLng.lat());
					$('#markerLng').val(event.latLng.lng());
				});
			}
		</script>
	
		<div class="header"><h1>게시판 수정</h1></div>
		<form action="" method="post" id="write_submit">
			<input type="hidden" name="SB_NO" value="${boardBean.SB_NO}">
			<ul class="write_ul">
			
				<li class="write_li">
					<div id="locationField">
						<input type="text" placeholder="검색할 장소를 입력하세요." id="autocomplete">
					</div>
					<div id="map"></div>
					<input type="hidden" name="markerLat" id="markerLat" value="${boardBean.SB_LAT}">
					<input type="hidden" name="markerLng" id="markerLng" value="${boardBean.SB_LNG}">
				</li>
				
				<li class="write_li">
					<div class="title">
						<img src="image/document.png" alt="document" class="write_img">
						<input name="TITLE" type="text" size="50" maxlength="100" value="${boardBean.SB_TITLE}" placeholder="제목을 입력하세요">
					</div>
				</li>
				
				<li class="write_li">
					<div id="tagSection">
						<img src="image/hashtag.png" alt="hashtag" class="write_img">
						<input type="text" id="inputTag" placeholder="hashtags">
						<input type="hidden" id="hashTag" name="HASHTAG">
					</div>
					
				</li>
				
				<li class="write_li">
					<div class="category">
						<select name="CATEGORY">
							<option class="cate_option" value="0" selected>카테고리</option>
						    <option class="cate_option" value="1">의류/잡화</option>
						    <option class="cate_option" value="2">뷰티</option>
						    <option class="cate_option" value="3">식품/생활/유아동</option>
						    <option class="cate_option" value="4">가구</option>
						    <option class="cate_option" value="5">가전/디지털</option>
						    <option class="cate_option" value="6">도서/쿠폰</option>
						</select>
					</div>
					<div class="price">
						<input name = "PRICE" type="text" size="50" maxlength="50" value="${boardBean.SB_PRICE}" placeholder="가격">
					</div>
				</li>
				
				<li class="write_li">
					<div class="content">
						<textarea name="CONTENT" cols="65" rows="15">${boardBean.SB_CONTENT}</textarea>
					</div>
				</li>
				
				<li class="write_li">
					<div class="image">
						<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
						<div id="showImage">
							<c:forEach var="img" items="${imageBeanList}">
								<img src="${img.IMAGE_URL}"/>
							</c:forEach>
						</div>
						<input type="hidden" id="img_hidden" name="img_hidden" value="${imageBean.IMAGE_URL}">
					</div>
				</li>
				
				<li class="btn_li">
					<div>
						<button type="submit" id="submit_btn" class="write_btn">등록</button>
						<button type="reset" id="cancle_btn" class="write_btn" onclick='history.go(-1)'>취소</button>
					</div>
				</li>
				
			</ul>
			
		</form>
