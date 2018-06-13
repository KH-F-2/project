<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${!empty jsonArr }">
		<c:forEach var="item" items="${jsonArr }">
			<div class="content">
				<a href="sbview.sb?num=${item.num }&board_name=${item.board_name }">
					<img src="${item.image_url }">
					<p>${item.title }</p>
				</a>
				<span>&#xf159; ${item.price }원</span>
				<span>${item.content }</span>
				<div id="contentInfoSection">
					<div class="contentInfo">&#xe80b; 0</div>
					<div class="contentInfo">&#xe816; 0</div>
					<div class="contentInfo">&#xe80d; ${item.readcount }</div>
				</div>
			</div>
		</c:forEach>
	</c:when>	
</c:choose>