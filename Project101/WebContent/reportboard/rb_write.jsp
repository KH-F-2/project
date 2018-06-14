<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="./js/reportboard.js"></script>
<style>
nav {
	height: 40px;
	background: #e5ffff
}

a {
	text-decoration: none;
}

.right {
	margin-top: 10px;
	margin-right: 30px;
	float: right;
	color: #fb8c00;
	display: inline-block;
	font-weight: bold;
}

.right>a {
	margin-left: 15px;
}

table {
	margin: 0 auto;
	width: 90%;
	text-align: center;
	margin-top: 20px;
	font-family: koverwatch;
}

tr:first-child {
	background-color: #A566FF;
	height: 50px;
	color: white;
	letter-spacing: 3px;
	font-size: 16pt;
}

tr {
	height: 30px;
}

tr>td:first-child {
	width: 150px;
	margin-left: 10px;
	background-color: rgba(205, 185, 202, 0.4);
}

tr>td:nth-child(2n) {
	background-color: rgba(185, 245, 202, 0.4);
	text-align: left;
}

textarea {
	resize: none;
}
</style>

<form action="./rbwriteaction.rb" method="post">
	<table>
		<tr>
			<th colspan="2">신고게시판</th>
		</tr>
		<tr>
			<td>신고 대상</td>
			<td>
				<input name="rb_rp_id" type="text" value="${writer }">
			</td>
		</tr>
		<tr>
			<td>신고 글 번호</td>
			<td>
				<input name="rb_rp_no" type="text" value="${board_no }">
				<input name="rb_rp_board_name" type="hidden" value="${board_name }">
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input name="rb_title" type="text" size="50" maxlength="100">
			</td>
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td>
				<textarea name="rb_content" id="board_content" cols="65" rows="15"></textarea>
			</td>
		</tr>
		<tr class="center">
			<td colspan="2">
				<input type=submit value="등록">
				<input type=reset value="취소" onclick="history.go(-1)">
			</td>
		</tr>
	</table>
</form>