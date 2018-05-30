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

</head>

<body>

	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle">&#xe801;  우리지금만나</h1>
		<h2>Category</h2>
		<a class="item" href="./pbmain.pb">구매게시판</a>
		<a class="item" href="./sbmain.sb">판매게시판</a>
		<a class="item" href="./ebmain.eb">후기게시판</a>
		<a class="item" href="./rbmain.rb">신고게시판</a>
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