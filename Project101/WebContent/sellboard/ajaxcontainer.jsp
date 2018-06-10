<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${!empty boardPageBean.boardBeanList }">
		<c:forEach var="item" items="${boardPageBean.boardBeanList }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.NUM }">
					<img src="${item.IMAGE_URL }">
					<p>${item.TITLE }</p>
				</a>
				<span>${item.PRICE }원</span>
				<span>${item.CONTENT }</span>
			</div>
		</c:forEach>
	</c:when>	
</c:choose>