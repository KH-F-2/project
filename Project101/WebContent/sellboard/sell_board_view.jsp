<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/test/js/sellboard.js"></script>
	</head>
			<table>
				<tr class="center">
					<th colspan="2">판매 게시판 - view 페이지</th>
				</tr>
				<tr>
					<td><div>글쓴이</div></td>
					<td>
						<div>${sellboard.SB_WRITER}</div>
					</td>
				</tr>
				<tr>
					<td><div>제목</div></td>
					<td><div>${sellboard.SB_TITLE}</div>	</td>
				</tr>
				<tr>
					<td><div>내용</div></td>
					<td><div>${sellboard.SB_CONTENT }</div></td>
				</tr>
				
				<tr class="center">
					<td colspan="2">
					    <c:if test="${id=='admin'||id==sellboard.SB_WRITER}">
					    	<a href="<c:url value='/ModifyView.sell?num=${sellboard.SB_NO}'/>">
								<img src="/test/image/update.png">
							</a>&nbsp;
							<a href="<c:url value='/BoardDelete.sell?num=${sellboard.SB_NO}'/>">
								<img src="/test/image/delete.png">
							</a>&nbsp;
						</c:if>
						<a href="<c:url value='/BoardList.sell'/>">
							<img src="/test/image/list.png">
						</a>&nbsp;
				</tr>
			</table>
	</body>
</html>