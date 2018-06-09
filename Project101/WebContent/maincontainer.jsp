<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${!empty jsonArr }">
		<h3 id="container-header">내 주변에 등록된 상품이 ${jsonArr.size() }개 있습니다.</h3>
		<c:forEach var="item" items="${jsonArr }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.NUM }">
					<img src="${item.IMAGE_URL }">
					<p>${item.TITLE }</p>
				</a>
				<span>${item.PRICE }원</span><br>
				<span>${item.CONTENT }</span>
			</div>
		</c:forEach>
	</c:when>
	
	<c:otherwise>
		<h3>현재 위치에 등록된 상품이 없습니다.</h3>
		<a href="./sbwriteview.sb">
			<h4>지금 등록하러 가기</h4>
		</a>
	</c:otherwise>
</c:choose>