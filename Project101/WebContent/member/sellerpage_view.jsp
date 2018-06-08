<%@ page language="java" contentType="text/html; charset=UTF-8"
  	pageEncoding="UTF-8"%>
  	<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>판매자 페이지</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
	</head>
	<body>
		<table>
		<c:forEach var = "seller" items = "${list}">
			<tr>
				<td>글번호 : ${boardBean.SB_NO}</td>
			</tr>		
			<tr>
				<td>제목 : ${boardBean.SB_TITLE}</td>			
			</tr>	
			<tr>
				<td>작성자 : ${boardBean.SB_WRITER}</td>		
			</tr>	
			<tr>
				<td>날짜 : ${boardBean.SB_DATE}</td>		
			</tr>	
			<tr>
				<td>조회수 : ${boardBean.SB_READCOUNT}</td>		
			</tr>
			<tr>	
			<td>내용 : ${boardBean.SB_CONTENT}</td>
			</tr>	 	 	 	 
		</c:forEach>
		</table>
	</body>
</html>