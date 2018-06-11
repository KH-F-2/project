<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/Project101/sellboard/css/sblist.css" rel="stylesheet" type="text/css">

<!-- Google Map API -->
<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>

<script src="./sellboard/js/sblist.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#a_write').click(function() {
			var id = <%=session.getAttribute("id")%>;
			if (id == null) {
				alert('로그인 후 이용하실 수 있습니다.');
				return false;
			}
		});
	});
</script>


<div id="mapControler">
	<div id="emptyDiv">
		<div id="chkBtnDiv">
			<input type="button" id="checkCurrentPosition" value="현재위치에서 조회">
		</div>
		<div id="locationField">
			<input id="autocomplete" placeholder="검색할 장소를 입력하세요." type="text" />
		</div>
	</div>
</div>

<div id="map"></div>


<div class="search">
	<select id="search_category">
		<option selected="selected">전체카테고리</option>
		<option value="1">의류/패션잡화</option>
		<option value="2">취미/레저</option>
		<option value="3">식품/생활/유아동</option>
		<option value="4">가구/생활잡화</option>
		<option value="5">가전/디지털</option>
		<option value="6">도서/티켓/쿠폰</option>
		<option value="7">기타</option>
	</select>
	
	<select id="search_sel">
		<option value="title" selected="selected">제목</option>
		<option value="content">내용</option>
		<option value="hashtag">태그</option>
		<option value="title_content">제목+내용</option>
	</select>
	
	<input type="text" name="search_input" id="search_input" placeholder="Search..">
	
	<button id="search_btn">검색</button>
</div>

<div id="container">
		<c:forEach var="item" items="${boardBeanlist }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.num }&board_name=${item.board_name }">
					<img src="${item.image_url }">
					<p>${item.title }</p>
				</a>
				<p>${item.price }원</p>
				<p>${item.content }</p>
			</div>
		</c:forEach>
</div>

<div id="moveTop">&#xf139;</div>

