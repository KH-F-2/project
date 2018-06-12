<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>Nice2MeetU</title>
	
	<!-- Semantic UI Library -->
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<script src="semantic/semantic.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/template.css">
	
	<script>
		$(document).ready(function () {
			$('#navTitle').click(function () {
				location.href = 'main.map';
			});
			
			$('#c1').click(function(){
				location.href = './pbsearchAction.pb?category=1&centerLat='+ centerLat + '&centerLng=' + centerLng;
			});
			$('#c2').click(function(){
				location.href = './pbsearchAction.pb?category=2&centerLat='+ centerLat + '&centerLng=' + centerLng;
			});
			$('#c3').click(function(){
				location.href = './pbsearchAction.pb?category=3&centerLat='+ centerLat + '&centerLng=' + centerLng;
			});
			$('#c4').click(function(){
				location.href = './pbsearchAction.pb?category=4&centerLat='+ centerLat + '&centerLng=' + centerLng;
			});
			$('#c5').click(function(){
				location.href = './pbsearchAction.pb?category=5&centerLat='+ centerLat + '&centerLng=' + centerLng;
			});
			$('#c6').click(function(){
				location.href = './pbsearchAction.pb?category=6&centerLat='+ centerLat + '&centerLng=' + centerLng;
			});
			
		});
		
	
		
		
		
		
	</script>

</head>

<body>

	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle">&#xe81d;  우리지금만나</h1>
		<h2>&#xf114;  Category</h2>
		
		<a class="item" id="c1">의류/패션잡화</a>
		<a class="item" id="c2">취미/레저</a>
		<a class="item" id="c3">식품/생활/유아동</a>
		<a class="item" id="c4">가구/생활잡화</a>
		<a class="item" id="c5">가전/디지털</a>
		<a class="item" id="c6">도서/티켓/쿠폰</a>
		<a class="item" id="c7">기타</a>
	</div>

	
	
	<div class="pusher">
		<div id="header_section">
			<c:choose>
				<c:when test="${empty param.page }">
					<jsp:include page='./mainheader.jsp' />
				</c:when>
				<c:otherwise>
					<jsp:include page='./header.jsp' />
				</c:otherwise>
			</c:choose>
		</div>
		
		<div id="container_section">
			<c:set var="pageFile" value="${empty param.page ? './main.jsp' : param.page }"/>
			<jsp:include page='${pageFile}' />
		</div>

		<div id="footer_section"></div>
		
	</div>
</body>
</html>