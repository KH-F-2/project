<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>우리지금만나 글쓰기</title>
		

        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- 이미지 업로드 API -->
        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>
        
        <!-- 지도 API -->
        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
        

        <script src="js/boardwrite.js"></script>
        <link href="css/boardwrite.css" rel="stylesheet" type="text/css">

		<script type="text/javascript">
			
		</script>
	</head>
	<body>	
		<div class="header"><h1>거래 등록페이지</h1></div>
		<form action="" method="post" id="write_submit">
			<ul class="write_ul">
				
				<li class="write_li">
					<div id="locationField">
						<input type="text" placeholder="검색할 장소를 입력하세요." id="autocomplete">
					</div>
					<div id="map"></div>
					<input type="hidden" name="markerLat" id="markerLat">
					<input type="hidden" name="markerLng" id="markerLng">
				</li>
			
				<li class="write_li">
					<div class="board_type">
						<input type="radio" id="purchase" name="iCheck" value="1" checked>
							<label class="radio_label" id="radio1" for="purchase">구매</label>
						<input type="radio" id="sell" name="iCheck" value="2">
							<label class="radio_label" id="radio2" for="sell">판매</label>
					</div>
					<div class="title">
						<img src="image/document.png" alt="document" class="write_img">
						<input name="TITLE" type="text" size="50" maxlength="100" placeholder="제목을 입력하세요">
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
						<img src="image/category.png" alt="category" class="write_img">
						<select name="CATEGORY">
							<option value="0" selected>카테고리</option>
						    <option value="1">의류/패션잡화</option>
						    <option value="2">취미/레저</option>
						    <option value="3">식품/생활/유아동</option>
						    <option value="4">가구/생활잡화</option>
						    <option value="5">가전/디지털</option>
						    <option value="6">도서/티켓/쿠폰</option>
						    <option value="7">기타</option>
						</select>
					</div>
					<div class="price">
						<img src="image/money.png" alt="money" class="write_img">
						<input name = "PRICE" type="text" size="50" maxlength="50" placeholder="가격을 입력하세요">
					</div>
				</li>
				
				<li class="write_li">
					<div class="content">
						<textarea name="CONTENT" cols="65" rows="15"></textarea>
					</div>
				</li>
				
				<li class="write_li">
					<div class="image">
						<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
						<div id="showImage"></div>
						<input type="hidden" id="img_hidden" name="img_hidden" value="">
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
	</body>
</html>