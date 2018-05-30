<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/form.css" rel="stylesheet" type="text/css">
<style>
form{border:2px solid black}
</style>

<!-- 게시판 리스트 -->

<table >
	<tr>
		<td colspan="2">거래중물품 - view페이지</td>
	</tr>
	
	<tr>
		<td>글쓴이</td>
	<td>
		<div>${tr.TR_NAME }</div>
	</td>
	</tr> 
	<tr>
		<td>카테고리</td>
		<td>${tr.TR_CATEGORY }</td>
	</tr>
	<tr>
		<td>제목</td>
	<td>
		<div>${tr.TR_SUBJECT }</div>
	</td>
	</tr> 
	<tr>
		<td>가격</td>
		<td>${tr.TR_PRICE }</td>
	</tr>
	<tr>
		<td>날짜</td>
		<td>${tr.TR_DATE }</td>
	</tr>
	<tr>
		<td>내용</td>
	<td>
		<div>${tr.TR_CONTENT }</div>
	</td>
	</tr> 
	<tr>
		<td colspan="2" class="center">		
		<a href="./signtradelist.mem">목록</a>
	</td>
</tr> 
</table>