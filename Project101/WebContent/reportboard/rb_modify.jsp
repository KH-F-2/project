<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="./css/boardwrite.css" rel="stylesheet" type="text/css">
<script src="./reportboard/rbwrite.js"></script>

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
	


<form action="./rbmodifyaction.rb" method="post" id="write_submit">
	<input type="hidden" value="${boardBean.RB_NO }" name="RB_NO">
	<ul class="write_ul">
		
		<li class="write_li">
			<div class="rb_1">
				제목
			</div>
			<div class="rb_2">
				<input name="RB_TITLE" type="text" size="50" maxlength="100" value="${boardBean.RB_TITLE}">
			</div>
		</li>
		
		<li class="write_li">
			<div class="rb_3">
				신고 대상
			</div>
			<div class="rb_4">
				<input name="rb_rp_id" type="text" value="${boardBean.RB_RP_ID }" readonly>
			</div>
		</li>
		
		<li class="write_li">
			<div class="rb_5">
				신고 글 번호
			</div>
			<div class="rb_6">
				<input name="rb_rp_no" type="text" value="${boardBean.RB_RP_NO }" readonly>
				<input name="rb_rp_board_name" type="hidden" value="${boardBean.RB_RP_BOARD_NAME }">
			</div>
		</li>
		
		<li class="write_li">
			<div class="content">
				<textarea name="RB_CONTENT" id="board_content" cols="65" rows="15">${boardBean.RB_CONTENT}</textarea>
			</div>
		</li>
		
	
		
		<li class="btn_li">
			<div>
				<button type="submit" id="submit_btn" class="write_btn">등록</button>
				<button type="reset" id="cancle_btn" class="write_btn" onclick='history.go(-1)'>취소</button>
			</div>
		</li>
		
	</ul>
	
</form>	