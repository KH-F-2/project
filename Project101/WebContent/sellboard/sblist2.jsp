<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>�Ǹ� �Խ���</title>
        <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        
         <!-- ���� API -->
        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
        
        <script type="text/javascript">
        	$(function(){
		        var id = '<%=session.getAttribute("id")%>';
        	});
        </script>
        <script src="js/sblist.js"></script>
		<link href="css/sblist.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="list_title">
			<h1>�Խñ� ����Ʈ</h1>
		</div>
		
		<div id="map">����~</div>
		
		<div class="write">
			<button type="button" class="write_btn" onclick="location.href='./sbwriteview.sb'">�۾���</button>
		</div>
		<section class="content_section">
			<c:forEach var="list" items="${arr}">
				<div class="card">
					<a class="card_a" href="./sbview.sb?num=${list.SB_NO }">
						<div class="card_img">
							<img class="content_img" src="${list.IMAGE_URL}">
						</div>
						<div class="card_content">
							<span class="content_span_title">${list.SB_TITLE}</span><br>
							<span class="content_span_price">${list.SB_PRICE}��</span>
							<br>${list.SB_DATE}
						</div>
						<div class="card_bottom">
							<span class="bottom_span">
								��� 0 �� ��ȸ�� ${list.SB_READCOUNT}
							</span>
						</div>
					</a>
				</div>
			</c:forEach>
		</section>
		
	</body>
</html>