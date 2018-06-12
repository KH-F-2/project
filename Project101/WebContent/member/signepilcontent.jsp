<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>


</script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
	<style>
		
		table{
			width:500px;height:400px;
		}		
		.epil_ul{
			font-size:20px;
		}
		.epil_ul .epil_li div{
			display: inline-block;
			height: 100%;
		}
		.epil_ul .epil_li{
			width: 100%;
			height: 50px;
			list-style:none;			
		}
		.epil_li{
			padding: 17px 37px 20px 0;
			border: 1px solid #cdd2d2;
		}
	</style>
	</head>
	
	<body>
	<h1 style="text-align:center;">후기목록</h1>
		<h2 style="text-align: center;">"${writer }"님의 후기목록</h2>
		<ul class="epil_ul">
		
			<c:forEach var="ep" items="${list}">
				<li class="epil_li">
					<div>작성자 : ${ep.EP_WRITER}</div>
					<div>내용 : ${ep.EP_CONTENT}</div>
					<div>작성일 : ${ep.EP_DATE}</div>
					<div>별점 : ${ep.EP_STAR}</div>
				</li>
			</c:forEach>
			
		</ul>
	</body>
</html>