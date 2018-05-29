<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>

		<!-- 달력 API -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- 이미지 업로드 API -->
        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>

        <script src="/project101/sellboard/js/sellboard_write.js"></script>
        <link href="/project101/sellboard/css/board_write.css" rel="stylesheet" type="text/css">
	</head>
	<body>	
		<form action="./BoardModifyAction.sell" method="post" id="write_submit">
		<input type="hidden" name="SB_NO" value="${sellboard.SB_NO}">
			<table>
				<tr>
					<th colspan="2">판매게시판</th>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						${id}
					</td>
				</tr>
				<tr>

					<td>구매날짜</td>
					<td>
						<input name = "SB_BDATE" type="text" size="50" maxlength="100" value="${sellboard.SB_BDATE}">

					</td>
				</tr>
				<tr>
					<td>
						<div>내용</div>
					</td>
					<td>

						<textarea name="SB_CONTENT" id="board_content" cols="65" rows="15">${sellboard.SB_CONTENT}</textarea>
					</td>
				</tr>
				<tr class="center">
					<td colspan="2"><input type=submit value="등록">
						<input type=reset value="취소" onclick = "history.go(-1)">
				</tr>
			</table>
		</form>

	</body>
</html>