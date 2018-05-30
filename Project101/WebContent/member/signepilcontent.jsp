<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
	<style>
		body{
			text-align: center
		}
		table{
			width:500px;height:400px;margin:0 auto;text-align:center
		}
	</style>
	</head>
	
	<body>
	<h1>후기목록</h1>
		<table border=1>
			<tr>
				<td>내용 : </td>
				<td>${epil}</td>
			</tr>		
			<tr>
				<td>별점 : </td>
				<td>${star}</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
				<c:if test="${!empty member.epfile}">
						<img src="image/down.png" width="10px">
						<a href="/signfiledown.mem?filename=${member.epfile }">
							${member.epfile }</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2"><button onclick="location.href='main.mem'">메인으로</button></td>
			</tr>
		</table>
			
	</body>
</html>