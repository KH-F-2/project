<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>글쓰기 게시판</title>
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
		<form action="./BoardAddAction.epil" method="post" id="write_submit" enctype="multipart/form-data">
			<table>
				<tr>
					<th colspan="2">후기게시판</th>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						<input type="text" name=SB_WRITER size="20" >
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input name="SB_TITLE" type="text" size="50" maxlength="100"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<input name = "SB_PRICE" type="text" size="50" maxlength="100">
					</td>
				</tr>
				<tr>
					<td>평점</td>
					<td>
						<input type="text" name="SB_GRADE" size="20">
					</td>
				</tr>
				<tr>
					<td>
						<div>내용</div>
					</td>
					<td>
						<textarea name="SB_CONTENT" cols="65" rows="15"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<div>파일첨부</div>
					</td>
					<td>
						<input type="file" id="upfile" name="SB_FILE">					
					</td>					
				</tr>
				<tr class="center">
					<td colspan="2">
						<input type=submit value="등록">
						<input type=reset value="취소" onclick = "history.go(-1)">
					</td>
				</tr>
			</table>
		</form>	
	</body>
</html>