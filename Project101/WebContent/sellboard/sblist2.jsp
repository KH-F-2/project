<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>판매 게시판</title>
        <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        
         <!-- 지도 API -->
        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
        
        <script type="text/javascript">
        	$(document).ready(function(){
		        $('.write_btn').click(function(){
			        var id = <%=session.getAttribute("id")%>;
		    		if(id==null){
		    			alert('로그인 후 이용하실 수 있습니다.');
		    			location.href='./signin.me';
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
        	});
        </script>
        <script src="js/sblist.js"></script>
		<link href="css/sblist.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="list_title">
			<h1>게시글 리스트</h1>
		</div>
		
		<div id="map">지도~</div>
		
		<div class="write">
			<button type="button" class="write_btn" onclick="location.href='./sbwriteview.sb'">글쓰기</button>
		</div>
		<section class="content_section">
			<c:forEach var="list" items="${arr}">
				<div class="card">
					<a class="card_a" href="./sbview.sb?num=${list.SB_NO}&board_name=${list.BOARD_NAME}">
						<div class="card_img">
							<img class="content_img" src="${list.IMAGE_URL}">
						</div>
						<div class="card_content">
							<div class="content_title">
								<span class="content_span_title">${list.SB_TITLE}</span>
								<br>
								<span class="content_span_price">${list.SB_PRICE}원</span>
							</div>
							<div class="content_board">
								<span class="content_span_board">
									<c:if test="${list.BOARD_NAME eq 'SELL_BOARD'}">판매</c:if>
									<c:if test="${list.BOARD_NAME eq 'PURCHASE_BOARD'}">구매</c:if>
								</span>
							</div>
						</div>
						<div class="card_bottom">
							<span class="bottom_span">
								${list.SB_DATE} · 조회수 ${list.SB_READCOUNT}
							</span>
						</div>
					</a>
				</div>
			</c:forEach>
		</section>
		
	</body>
</html>