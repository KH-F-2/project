<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
		a{text-decoration: none}
		</style>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/test/js/sellboard.js"></script>
	</head>
			<table>
				<tr class="center">
					<th colspan="2">신고 게시판 - view 페이지</th>
				</tr>
				<tr>
					<td><div>글쓴이</div></td>
					<td>
						<div>${reportboard.RB_WRITER}</div>
					</td>
				</tr>
				<tr>
					<td><div>제목</div></td>
					<td><div>${reportboard.RB_TITLE}</div>	</td>
				</tr>
				<tr>
					<td><div>내용</div></td>
					<td><div>${reportboard.RB_CONTENT }</div></td>
				</tr>
				<tr>
					<td><div>구매가격</div></td>
					<td><div>${reportboard.RB_PRICE }</div></td>
				</tr>
				<tr class="center">
					<td colspan="2">
					   <%--<c:if test="${id=='admin'||id==reportboard. B_WRITER}">--%>
					    	<a href="<c:url value='/BoardModifyView.report?RB_NO=${reportboard.RB_NO}'/>">
								<input type="button" value="수정">
							</a>&nbsp;
							<a href="<c:url value='/BoardDelete.report?RB_NO=${reportboard.RB_NO}'/>">
								<input type="button" value="삭제">
							</a>&nbsp;
						<%--</c:if>--%>
						<a href="<c:url value='/BoardList.report'/>">
							<input type="button" value="목록">
						</a>&nbsp;
				</tr>
			</table>
	</body>
</html>