<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="css/sblist.css" rel="stylesheet" type="text/css">

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
		
		function initMap() {
    		var lat = ${centerLat};
    		var lng = ${centerLng};
    		var mak = {
    				lat : lat,
    				lng : lng
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
    		})();
    		
    	}
		
		
		$('#search_btn').click(function() {
			var word = $('input[name=search_input').val();
			var item = $('#search_sel').val();
			location.href = './sbmain.sb?word=' + word + '&item=' + item;
		});
	});
</script>


<!-- <div id="mapControler">
	<div id="emptyDiv">
		<div id="chkBtnDiv">
			<input type="button" id="checkCurrentPosition" value="현재위치에서 조회">
		</div>
		<div id="locationField">
			<input id="autocomplete" placeholder="검색할 장소를 입력하세요." type="text" />
		</div>
	</div>
</div>
 -->
<div id="map"></div>

<div class="write">
	<button type="button" class="write_btn" onclick="location.href='./sbwriteview.sb'">글쓰기</button>
</div>

<div class="search">
	<select id="search_sel">
		<option value="title" selected="selected">제목</option>
		<option value="content">내용</option>
		<option value="title_content">제목+내용</option>
	</select> <input type="text" name="search_input" placeholder="Search..">
	<button id="search_btn">검색</button>
</div>
<section class="content_section">
	<c:forEach var="list" items="${boardPageBean.boardBeanList}">
		<div class="card">
			<a class="card_a" href="./sbview.sb?num=${list.NUM}&board_name=${list.BOARD_NAME}">
				<div class="card_img">
					<img class="content_img" src="${list.IMAGE_URL}">
				</div>
				<div class="card_content">
					<div class="content_title">
						<span class="content_span_title">${list.TITLE}</span>
						<br>
						<span class="content_span_price">${list.PRICE}원</span>
					</div>
					<div class="content_board">
						<c:if test="${list.BOARD_NAME eq 'SELL_BOARD'}">
							<span class="content_span_sell">판매</span>
						</c:if>
						<c:if test="${list.BOARD_NAME eq 'PURCHASE_BOARD'}">
							<span class="content_span_purchase">구매</span>
						</c:if>
						</span>
					</div>
				</div>
				<div class="card_bottom">
					<span class="bottom_span">
						${list.DDATE} · 조회수 ${list.READCOUNT}
					</span>
				</div>
			</a>
		</div>
	</c:forEach>
</section>
<%-- 
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
</div> --%>

<div id="moveTop">&#xf139;</div>
