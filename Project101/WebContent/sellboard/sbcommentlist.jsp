<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="b_p" value="${boardPageBean }"/>
<c:forEach var="comment" items="${b_p.commentBeanList}" varStatus="i">
	<li id="${comment.CMT_WRITER}" class="comment_li">
		<div class="comment_top">
			<span class="comment_writer">${comment.CMT_WRITER}</span>
			<span class="comment_date">${comment.CMT_DATE}</span>
		</div>
		<div class="comment_bottom">
			<div class="comment_text">${comment.CMT_CONTENT}</div>
			<div class="comment_btn2" id="${comment.CMT_NO}">
				<a href="#" id="comment_reply" 
					onclick='replyView(${comment.CMT_NO}); return false;' 
					class="btn btn-sm animated-button victoria-one">답글</a>
				<a href="#" id="comment_delete" 
					onclick='commentDelete(${comment.CMT_NO}); return false;'  
					class="btn btn-sm animated-button victoria-one">삭제</a>
			</div>
		</div>
	</li>
	<li class="reply r${comment.CMT_NO}"></li>
</c:forEach>

<li>
	<div class="comment_paging">
		<c:if test="${b_p.page<=1 }">
			이전&nbsp;
		</c:if>
		<c:if test="${b_p.page>1 }">
			<a href="./sbview.sb?page=${b_p.page-1}&num=${boardBean.SB_NO}">이전</a>&nbsp;
		
		</c:if>
		<c:forEach var="a" begin="${b_p.startpage }" end="${b_p.endpage }">
			<c:if test="${a==b_p.page }">
				${a }
			</c:if>
			<c:if test="${a!=b_p.page }">
				<a href="./sbview.sb?page=${a }&num=${boardBean.SB_NO}">${a }</a>
			</c:if>
		
		</c:forEach>
		<c:if test="${b_p.page>=b_p.maxpage }">
			&nbsp;다음
		</c:if>
		<c:if test="${b_p.page<b_p.maxpage }">
			&nbsp;<a href="./sbview.sb?page=${b_p.page+1 }&num=${boardBean.SB_NO}">다음</a>
		</c:if>
	</div>
</li>