<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>	
		<form action="/BoardWrite.sell" method="post">
			<table>
				<tr>
					<th>판매게시판</th>
					<td>글번호</td>
					<td></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						<input name="SB_WRITER" readOnly type="text" size="10" maxlength="30" value="${id}">
					</td>
					<td>조회수</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input name="SB_TITLE"  type="text" size="50" maxlength="100" value=""></td>
				</tr>
				<tr>
					<td>카테고리</td>
				</tr>
				
				<tr>
					<td>가격</td>
					<td>
						<input name = "SB_PRICE" type="text" size="50" maxlength="100" value="10,000원">
					</td>
				</tr>
				<tr>
					<td>구매날짜</td>
					<td>
						<input name = "SB_REDIDATE" type="text" size="50" maxlength="100" value="2018-01-01">
					</td>
				</tr>
									
				<tr>
					<td>
						<div>내용</div>
					</td>
					<td>
						<textarea name="SB_CONTENT" id="board_content" cols="67" rows="15"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<div>첨부파일</div>
					</td>
					<td>
						<input type="file" id="upfile" name="BOARD_FILE" value="attach.png">
					</td>
				</tr>
				<tr>
					<td>
						<div>태그달기</div>
					</td>
					<td>
						<input type="text" id="tag" name="BOARD_tag" placeholder="#">
					</td>
				</tr>
				<tr class="center">
					<td colspan="2" class="h30 lime"><input type=submit value="등록">
						<input type=reset value="취소" onclick = "history.go(-1)">
				</tr>
			</table>
		</form>	
	</body>
</html>