<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/test/js/sellboard.js"></script>
        <style>
        	nav{height: 40px; background: #e5ffff} 
			a{text-decoration: none; }
			.right{margin-top: 10px; margin-right: 30px; float: right; color: #fb8c00; display: inline-block; font-weight: bold;}
			.right>a{margin-left: 15px;} 	
			table{margin:0 auto; width: 90%; text-align: center; margin-top: 20px; font-family: koverwatch; }
			tr:first-child{background-color:#A566FF; height: 50px; color: white; letter-spacing: 3px; font-size: 16pt;}
			tr{height: 30px;}
			tr>td:first-child{width:150px; margin-left: 10px; background-color: rgba(205,185,202,0.4);}
			tr>td:nth-child(2n){background-color: rgba(185,245,202,0.4); text-align: left;}
			textarea{resize: none; }
        </style>
	</head>
	<body>	
		<form action="./BoardModifyAction.sell" method="post">
			<table>
				<tr>
					<th colspan="2">판매게시판 - 수정</th>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						${sellboard.SB_WRITER}
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input name="SB_TITLE"  type="text" size="50" maxlength="100" value="${sellboard.SB_TITLE}"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<input name = "SB_PRICE" type="text" size="50" maxlength="100" value="${sellboard.SB_PRICE}">
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