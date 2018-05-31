<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     	

<html>
<head>
<title>수정 페이지</title>
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
		<span id='title'>수정게시판&nbsp;</span>
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
	<form action="./pbmodifyAction.pb" method="post" name="Modifyform">
	<input type = "hidden" name="PB_NO" value="${boardBean.num }">
		<table>
			<tr>
				<td>작성자</td>
				<td>로그인 된 ID값</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name='PB_TITLE' id='subject' value="${boardBean.title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td></td>
			</tr>
			<tr>
				<td colspan='2'><textarea name='PB_CONTENT' id='content'>${boardBean.content}</textarea></td>	
			</tr>
			<tr>
				<td>위치</td>
				<td>클릭이미지</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="text" value="${boardBean.file }"></td>
			</tr>
			<tr>
				<td>태그달기</td>
				<td><input type="text" placeholder="태그"></td>
			</tr>	
			<tr>
				<td colspan='2'>
					<input type="submit" name='submit' id='submit' value="수정">
					<input type="button" name='cancel' id='cancel' value="취소" onClick="history.go(-1)">
				</td>
			</tr>		
		</table>
	</form>
</section>
</body>
</html>