<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="comment_reply_textarea">
	<i class="material-icons" style="font-size:36px;margin-left:4%;position: relative;bottom: 10px;">navigate_next</i>
	<textarea rows="3" cols="75" style="resize: none;" id="comment_reply_content" placeholder="내용은 300자까지 입력가능합니다."></textarea>
	<button id="comment_reply_btn" onclick='commentReply(${comment.CMT_NO});'>작성</button>
</div>