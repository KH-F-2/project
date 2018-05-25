<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<html>
<head>
<title>구매 작성 페이지</title>
<style>
#title {
	float: left;
}

#writeNo {
	float: left;
}

#writeDate {
	float: right;
}
header{
	border: 1px solid black;
}
nav{
	border: 1px solid black;
}
aside {
	float: left;
	height: 100%;
	border: 1px solid black;
}
section{
height: 100%;
}
</style>
</head>
<body>
	<header>
	헤더
	</header>
	
	<nav>
		<!-- 맨 윗줄 -->
		<span id='title'>구매게시판&nbsp;</span>
		<span id='writeNo'>글 번호</span>
		<span id='writeDate'>Sysdate</span>
	</nav>
	<br>
	<aside>
		<select>
			<option>카테고리</option>
			<option>의류</option>
			<option>가구</option>
		</select>
	</aside>
	<section>
	<form action="./PurchaseAddAction.buy" method="post" name="PurchaseForm"  enctype="Multipart/form-data">
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name='id' id='id' readOnly type="text" value="로그인 된 ID"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name='title' id='title' placeholder="제목을 입력해주세요"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td></td>
			</tr>
			<tr>
				<td colspan='2'><textarea name='content' id='content'></textarea></td>	
			</tr>
			<tr>
				<td>위치</td>
				<td>클릭이미지</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="upfile" placeholder="파일명"></td>
			</tr>
			<tr>
				<td>태그달기</td>
				<td><input type="text" placeholder="태그"></td>
			</tr>	
			<tr>
				<td colspan='2'>
					<input type="submit" name='submit' id='submit' value="등록">
					<input type="button" name='cancel' id='cancel' value="취소">
				</td>
			</tr>		
		</table>
	</form>
</section>
</body>
</html>