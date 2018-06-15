<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="./css/boardwrite.css" rel="stylesheet" type="text/css">
<%-- <style>
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
</form> --%>
<style>
	.write_li {
		box-sizing: border-box;
	}
	.rb_1,.rb_2, .rb_3, .rb_4, .rb_5, .rb_6{
		display: inline-block;
	}
	.rb_1, .rb_3, .rb_5{
		width: 15%;
		border-right: 1px soild #eeeeee;
		font-weight: bold;
	}
	.rb_2, .rb_4, .rb_6{
		width: 79%;
	}
</style>
	


<form action="./rbwriteaction.rb" method="post" id="write_submit">
	<input type="hidden" value="${boardBean.RB_NO }" name="RB_NO">
	<ul class="write_ul">
		
		<li class="write_li">
			<div class="rb_1">
				제목
			</div>
			<div class="rb_2">
				<input name="rb_title" type="text" size="50" maxlength="100">
			</div>
		</li>
		
		<li class="write_li">
			<div class="rb_3">
				신고 대상
			</div>
			<div class="rb_4">
				<input name="rb_rp_id" type="text" value="${writer }">
			</div>
		</li>
		
		<li class="write_li">
			<div class="rb_5">
				신고 글 번호
			</div>
			<div class="rb_6">
				<input name="rb_rp_no" type="text" value="${board_no }">
				<input name="rb_rp_board_name" type="hidden" value="${board_name }">
			</div>
		</li>
		
		<li class="write_li">
			<div class="content">
				<textarea name="rb_content" id="board_content" cols="65" rows="15"></textarea>
			</div>
		</li>
		
	
		
		<li class="btn_li">
			<div>
				<input type=submit id="submit_btn" class="write_btn" value="등록">
				<input type=reset id="cancle_btn" class="write_btn" value="취소" onclick="history.go(-1)">
			</div>
		</li>
		
	</ul>
	
</form>	