<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>판매 게시판</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script>
			$(document).ready(function(){
				$('#detail').click(function(){
					location.href='BoardDetail.sell';
				});
				$('#write').click(function(){
					location.href='BoardWrite.sell';
				});
			});
		</script>
	</head>
	<body>
		판매 게시판 리스트<br>
		<button id="detail">글보기</button>
		<br>
		<button id="write">글쓰기</button>
	</body>
</html>