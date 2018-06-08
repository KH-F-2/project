<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
	</head>
	<body>
		<div class="right">
			<c:choose>
				<c:when test="${id!=null }">
					<c:choose>
					<c:when test="${id=='admin' }">
						관리자 ${id }님 환영합니다.
					</c:when>
					<c:otherwise>
						일반회원 ${id }님 환영합니다.
					</c:otherwise>
					</c:choose>
					<!-- <a href='updateForm.jsp'>정보수정</a> -->
					<a href='member_update.net'>정보수정</a>
					<a href="logout.net">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href="login.net">로그인</a>
				</c:otherwise>
			</c:choose>
			 <%-- <%if(id!=null){
    				if(id.equals("admin")){ 
						%> 관리자 <%=id %>님 환영합니다.<%
						}else{
							 %> 일반회원 <%=id %>님 환영합니다.
						<%
						}
    				out.println("<a href='updateForm.jsp'>정보수정</a>");
    			%>
    			<a href="logout.net">로그아웃</a>
			<%}else{ %>
				<a href="login.net">로그인</a>
			<%} %> --%>
			<a href="join.net">회원가입</a>
		</div>
	</body>
</html>