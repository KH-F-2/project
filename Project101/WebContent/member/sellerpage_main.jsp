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
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>		
		<c:forEach var = "seller" items = "${list}">
			<tr>
				<td>
				${seller.SB_NO}
				</td>
				<td>
				${seller.SB_TITLE}
				</td>
				<td>
				${seller.SB_WRITER}
				</td>
				<td>
				${seller.SB_DATE}
				</td>
				<td>
				${seller.SB_READCOUNT}
				</td>
			</tr>	 
		</c:forEach>
		</table>
	</body>
</html>