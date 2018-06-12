<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     	

<!DOCTYPE html>
<html>
	<head>
		<title>수정 페이지</title>
			 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
			
			<!-- 이미지 업로드 API -->
	        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>
	        
	        <!-- 지도 API -->
	        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
	
	        <script src="js/pbmodify.js"></script>
	        <link href="css/boardwrite.css" rel="stylesheet" type="text/css">
	
			<script type="text/javascript">
				
			</script>

</head>
<body>
<input type = "hidden" name="PB_NO" id="PB_NO" value="${boardBean.PB_NO }">
	<div class="header"><h1>판매게시판 글쓰기</h1></div>
	
		<form action="" method="post" id="modify_submit">

		<ul class="write_ul">
				<li class="write_li">
					<div id="locationField">
						<input type="text" placeholder="검색할 장소를 입력하세요." id="autocomplete">
					</div>
					<div id="map"></div>
					<input type="hidden" name="markerLat" id="markerLat" value="${boardBean.PB_LAT }">
					<input type="hidden" name="markerLng" id="markerLng" value="${boardBean.PB_LNG }">
				</li>
				<li class="write_li">
					<div class="board_type">
						<input type="radio" id="purchase" name="board_radio" value="1" checked>
					       <label for="radio1">구매</label>
					    <input type="radio" id="sell" name="board_radio" value="2">
					       <label for="radio2">판매</label>
					</div>
					<div class="title">
						<img src="image/document.png" alt="document">
						<input name="TITLE" type="text" size="50" maxlength="100" value="${boardBean.PB_TITLE }">
					</div>
				</li>
				
				<li class="write_li">
					<div id="tagSection">
						<img src="image/hashtag.png" alt="hashtag">
						<input type="text" id="inputTag" value="${boardBean.PB_HASHTAG }">
						<input type="hidden" id="hashTag" name="HASHTAG">
					</div>
				</li>
				
				<li class="write_li">
					<div class="category">
						<select name="CATEGORY">
							<option value="0" <c:if test= "${boardBean.PB_CATEGORY eq '0' }">selected</c:if>>카테고리</option>
						    <option value="1" <c:if test= "${boardBean.PB_CATEGORY eq '1' }">selected</c:if>>의류/잡화</option>
						    <option value="2" <c:if test= "${boardBean.PB_CATEGORY eq '2' }">selected</c:if>>뷰티</option>
						    <option value="3" <c:if test= "${boardBean.PB_CATEGORY eq '3' }">selected</c:if>>식품/생활/유아동</option>
						    <option value="4" <c:if test= "${boardBean.PB_CATEGORY eq '4' }">selected</c:if>>가구</option>
						    <option value="5" <c:if test= "${boardBean.PB_CATEGORY eq '5' }">selected</c:if>>가전/디지털</option>
						    <option value="6" <c:if test= "${boardBean.PB_CATEGORY eq '6' }">selected</c:if>>도서/쿠폰</option>
						</select>
					</div>
					<div class="price">
						<img src="image/money.png" alt="money">
						<input name = "PRICE" type="text" size="50" maxlength="50" value="${boardBean.PB_PRICE }">
					</div>
				</li>
				
				<li class="write_li">
					<div class="content">
						<textarea name="CONTENT" cols="65" rows="15">${boardBean.PB_CONTENT }</textarea>
					</div>
				</li>
				
				<li class="write_li">
					<div class="image">
						<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
						<div id="showImage">
							 <c:forEach var="image" items="${imageBeanList}">
								 <img src="${image.IMAGE_URL}" class="img_slide">
							 </c:forEach>
						
						</div>
						<input type="hidden" id="img_hidden" name="img_hidden" value="${imageBean.IMAGE_URL }">
					</div>
				</li>
				
				<li class="btn_li">
					<div>
						<button type="submit" id="modify_btn" class="write_btn">수정</button>
						<button type="reset" id="cancle_btn" class="write_btn" onclick='history.go(-1)'>취소</button>
					</div>
				</li>
				
			</ul>
			
		</form>	
	</body>
</html>				
		