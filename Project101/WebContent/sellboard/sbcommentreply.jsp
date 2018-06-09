<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String cmt_no = request.getParameter("CMT_NO"); %>

<div class="comment_reply_textarea">
	<i class="material-icons">navigate_next</i>
	<textarea rows="3" cols="75" style="resize: none;" id="comment_reply_content" placeholder="내용은 300자까지 입력가능합니다."></textarea>
	<button id="comment_reply_btn" onclick='commentReply(<%=cmt_no %>)'>작성</button>
</div>