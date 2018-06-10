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
		
		$('#search_btn').click(function() {
			var word = $('input[name=search_input').val();
			var item = $('#search_sel').val();
			location.href = './sbmain.sb?word=' + word + '&item=' + item;
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
	<select id="search_sel">
		<option value="title" selected="selected">제목</option>
		<option value="content">내용</option>
		<option value="title_content">제목+내용</option>
	</select> <input type="text" name="search_input" placeholder="Search..">
	<button id="search_btn">검색</button>
</div>

<div id="container">
		<c:forEach var="item" items="${boardPageBean.boardBeanList }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.NUM }&boardname=${item.BOARD_NAME }">
					<img src="${item.IMAGE_URL }">
					<p>${item.TITLE }</p>
				</a>
				<p>${item.PRICE }원</p>
				<p>${item.CONTENT }</p>
			</div>
		</c:forEach>
</div>

<div id="moveTop"></div>