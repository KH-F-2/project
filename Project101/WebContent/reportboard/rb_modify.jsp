<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/test/js/sellboard.js"></script>

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

<form action="./rbmodifyaction.rb" method="post">
	<input type="hidden" name="RB_NO" value="${boardBean.RB_NO}">
	<table>
		<tr>
			<th colspan="2">신고게시판 - 수정</th>
		</tr>
		<tr>
			<td>신고 대상</td>
			<td><input name="rb_rp_id" type="text" value="${boardBean.RB_RP_ID }" readonly>
			</td>
		</tr>
		<tr>
			<td>신고 글 번호</td>
			<td><input name="rb_rp_no" type="text" value="${boardBean.RB_RP_NO }" readonly>
				<input name="rb_rp_board_name" type="hidden" value="${boardBean.RB_RP_BOARD_NAME }">
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input name="RB_TITLE" type="text" size="50" maxlength="100" value="${boardBean.RB_TITLE}">
			</td>
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td>
				<textarea name="RB_CONTENT" id="board_content" cols="65" rows="15">
					${boardBean.RB_CONTENT}
				</textarea>
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
