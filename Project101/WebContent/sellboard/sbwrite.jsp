<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		

<<<<<<< HEAD
<!-- Google Map API -->
<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>

<!-- 달력 API -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
=======
		<!-- 달력 API -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- 이미지 업로드 API -->
        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>
>>>>>>> jusung

        <script src="js/sbwrite.js"></script>
        <link href="css/sbwrite.css" rel="stylesheet" type="text/css">

<<<<<<< HEAD
<script src="/Project101/sellboard/js/sbwrite.js"></script>
<link href="/Project101/sellboard/css/sbwrite.css" rel="stylesheet" type="text/css">

<form action="./sbwriteaction.sb" method="post" id="write_submit">
	<table class="sbwrite_table">
		<tr>
			<th colspan="2">판매게시판</th>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${id}</td>
		</tr>
		<tr>
			<td>구매 날짜</td>
			<td>
				<input type="text" id="datepicker" name="SB_PDATE" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input name="SB_TITLE" type="text" size="50" maxlength="100">
			</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>
				<input name="SB_PRICE" type="text" size="50" maxlength="100">
			</td>
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td>
				<textarea name="SB_CONTENT" cols="65" rows="15"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				<div>태그</div>
			</td>
			<td>
				<div id="tagSection">
					<input type="text" id="inputTag">
					<input type="hidden" id="hashTag" name="hashTag">
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>
					<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
				</div>
			</td>
			<td>
				<div id="showImage"></div>
				<input type="hidden" id="img_hidden" name="img_hidden" value="">
			</td>
		</tr>
		<tr>
			<td>거래 위치</td>
			<td>
				<div id="locationField">
					<input type="text" placeholder="검색할 장소를 입력하세요." id="autocomplete">
				</div>
				<div id="map"></div>
				<input type="hidden" name="markerLat" id="markerLat">
				<input type="hidden" name="markerLng" id="markerLng">
			</td>
		</tr>
		<tr class="center">
			<td colspan="2"><input type=submit value="등록">
			<input type=reset value="취소" onclick="history.go(-1)"></td>
		</tr>
	</table>
</form>
=======
		<script type="text/javascript">
			/* $(function(){
				$('#hashtag').on('input', function(){
					console.log('해시태그 입력');
					var content = $(this).val();
					var splitedArray = content.split(' ');
					var linkedContent = '';
						
					for(var word in splitedArray)
					{
						console.log(splitedArray);
						console.log(word);
						console.log(splitedArray[word]);
						
					  word = splitedArray[word];
					   if(word.indexOf('#') == 0)
					   {
					      word = '<span>' + word + '</span>';
					   }
					   linkedContent += word;
					}
				content = $(this).val(linkedContent);
				});
			}); */
		</script>
	</head>
	<body>	
		<div class="header"><h1>판매게시판 글쓰기</h1></div>
		<form action="./sbwriteaction.sb" method="post" id="write_submit">
			<ul class="sbwrite_ul">
				<li class="write_li">
					<div class="title">
						<input name="SB_TITLE" type="text" size="50" maxlength="100" placeholder="제목을 입력하세요">
					</div>
				</li>
				<li class="write_li">
					<div class="category">
						<select name="SB_CATEGORY">
							<option value="0" selected>카테고리</option>
						    <option value="1">의류/잡화</option>
						    <option value="2">뷰티</option>
						    <option value="3">식품/생활/유아동</option>
						    <option value="4">가구</option>
						    <option value="5">가전/디지털</option>
						    <option value="6">도서/쿠폰</option>
						</select>
					</div>
					<div class="hashtag">
						<input type="text" id="hashtag" name="SB_HASHTAG" size="50" placeholder="hashtags">
					</div>
				</li>
				<li class="write_li">
					<div class="purchase_date">
						<input type="text" name="SB_PURCHASE_DATE" id="datepicker" readonly="readonly" placeholder="구매일">
					</div>
					<div class="price">
						<input name = "SB_PRICE" type="text" size="50" maxlength="50" placeholder="가격을 입력하세요">
					</div>
				</li>
				<li class="write_li">
					<div class="content">
						<textarea name="SB_CONTENT" cols="65" rows="15"></textarea>
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
>>>>>>> jusung
