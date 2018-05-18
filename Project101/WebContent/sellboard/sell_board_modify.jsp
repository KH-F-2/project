<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
	<body>
		<form action="/BoardModifyAction.sell" method="post" enctype="multipart/form-data">
			<input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM}">
			<table>
				<tr class="center">
					<th colspan="2">구매게시판-수정</th>
				</tr>
				<tr>
					<td>
						<div>작성자</div>
					</td>
					<td><input name="SB_WRITER" readOnly type="text" size="10" maxlength="30" value="${id}"></td>
					<td>조회수</td>
				</tr> 
				<tr>
					<td>
						<div>제목</div>
					</td>
					<td><input name="SB_TITLE" type="text" size="50" maxlength="100" value="${selldata.SB_TITLE}"></td>
				</tr>
				<tr>
					<td>카테고리</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<input name = "SB_PRICE" type="text" size="50" maxlength="100" value="${selldata.SB_PRICE}">
					</td>
				</tr>
				<tr>
					<td>구매날짜</td>
					<td>
						<input name = "SB_REDIDATE" type="text" size="50" maxlength="100" value="${selldata.SB_REDIDATE}">
					</td>
				</tr>
				<tr>
					<td>
						<div>내용</div>
					</td>
					<td><textarea name="SB_CONTENT" cols="67" rows="15" >${selldata.SB_CONTENT}</textarea></td>
				</tr>
				
				<!-- 파일 첨부되어 있으면 -->
				<c:if test = "${!empty selldata.SB_PICTURE}">
				<tr>
					<td>
						<div>첨부파일</div>
					</td>
					<td>
						&nbsp;&nbsp;${selldata.SB_PICTURE}
					</td>
				</tr>
				</c:if>
				
				<c:if test = "${empty selldata.SB_PICTURE}">
				<tr>
					<td>
						<div>파일 첨부</div>
					</td>
					<td>
						<input type="file" id="upfile" name="SB_PICTURE" value="attach.png">
					</td>
					<td>
						<div>태그달기</div>
					</td>
				</tr>
				</c:if>
				
				<tr class="center">
					<td colspan="2" class="h30 lime">
						<input type=submit value="수정">
						<input type=reset value="취소" onclick = "history.go(-1)">
				</tr>
			</table>
		</form>
	</body>
</html>