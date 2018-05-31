<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/Project101/sellboard/css/sbwrite.css" rel="stylesheet" type="text/css">

<!-- 달력 API -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<!-- 이미지 업로드 API -->
<script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>

<script src="/Project101/sellboard/js/sbwrite.js"></script>

<form action="./sbmodifyaction.sb" method="post" id="write_submit">
	<input type="hidden" name="SB_NO" value="${boardBean.SB_NO}">
	<table class="sbwrite_table">
		<tr>
			<th colspan="2">판매게시판</th>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${id}</td>
		</tr>
		<tr>
			<td>구매 날짜</td>
			<td><input type="text" id="datepicker" name="SB_PDATE"
				value="${boardBean.SB_PDATE}"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input name="SB_TITLE" type="text" size="50" maxlength="100"
				value="${boardBean.SB_TITLE}"></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input name="SB_PRICE" type="text" size="50" maxlength="100"
				value="${boardBean.SB_PRICE}"></td>
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td><textarea name="SB_CONTENT" cols="65" rows="15">${boardBean.SB_CONTENT}</textarea>
			</td>
		</tr>
		<tr>
			<td>
				<div>
					<input type="hidden" role="uploadcare-uploader" name="image"
						data-images-only="true" data-multiple="true" />
				</div>
			</td>
			<td>
				<div id="showImage">
					<c:forEach var="img" items="${imageBeanList}">
						<img src="${img.IMAGE_URL}" />
					</c:forEach>
				</div> <input type="hidden" id="img_hidden" name="img_hidden" value="">
			</td>
		</tr>
		<tr class="center">
			<td colspan="2"><input type=submit value="등록"> <input
				type=reset value="취소" onclick="history.go(-1)"></td>
		</tr>
	</table>
</form>
