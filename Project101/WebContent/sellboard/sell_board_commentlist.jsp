<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class="comment_li" id="${comment.COMMENT_NO}">
	<div class="comment_top">
		<span class="comment_writer">${comment.COMMENT_WRITER}</span>
		<span class="comment_date">${comment.COMMENT_DATE}</span>
	</div>
	<div class="comment_bottom">
		<div class="comment_text">${comment.COMMENT_CONTENT}</div>
		<div class="comment_btn2">
			<a href="#" id="comment_reply" 
				class="btn btn-sm animated-button victoria-one">답글</a>
			<a href="#" id="comment_delete" 
				class="btn btn-sm animated-button victoria-one">삭제</a>
		</div>
	</div>
</li>