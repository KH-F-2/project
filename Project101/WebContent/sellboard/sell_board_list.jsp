<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>�Ǹ� �Խ���</title>
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
		�Ǹ� �Խ��� ����Ʈ<br>
		<button id="detail">�ۺ���</button>
		<br>
		<button id="write">�۾���</button>
	</body>
</html>