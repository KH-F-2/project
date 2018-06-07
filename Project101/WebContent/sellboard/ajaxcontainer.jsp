<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${!empty boardPageBean.boardList }">
		<c:forEach var="item" items="${boardPageBean.boardList }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.num }">
					<img src="./image/koala.jpg">
					<p>${item.title }</p>
				</a>
				<span>${item.price }원</span>
				<span>${item.content }</span>
			</div>
		</c:forEach>
	</c:when>	
</c:choose>