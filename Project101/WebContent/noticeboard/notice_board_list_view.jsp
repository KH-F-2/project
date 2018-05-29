<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%> 
  
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>고객센터</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
	</head>
	<body>
		<%@ include file="/noticeboard/notice_board_list.jsp" %>
		
			
					<c:forEach var = "notice" items = "${list}">
						<details>
						<summary>
						<b>Q.</b>	
						<b>${notice.notice_Q}</b> 
						</summary>	
						<p>	
						<b>A.</b>	
						<b>${notice.notice_A}</b> 
						</p>
						</details>
					</c:forEach>
			
		
	</body>
</html>