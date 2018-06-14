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
	
	<script src="js/template.js"></script>

</head>

<body>

	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle">&#xe81d;  우리지금만나</h1>
		<h2>&#xf114;  Category</h2>
		<button class="item" id="category0" value="0"> 의류/패션잡화</button>
		<button class="item" id="category1" value="1"> 가구/생황잡화</button>
		<button class="item" id="category2" value="2"> 전자기기/게임</button>
		<button class="item" id="category3" value="3"> 도서/티켓/쿠폰</button>
		<button class="item" id="category4" value="4"> 오토바이/차량용품</button>
		<button class="item" id="category5" value="5"> 취미/레저</button>
		<button class="item" id="category6" value="6"> 기타</button>
		<button class="item" id="reportBtn" value="report"> 신고하기</button>
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