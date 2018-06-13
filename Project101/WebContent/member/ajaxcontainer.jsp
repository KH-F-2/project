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
		
		<c:otherwise>
			<h2>아직 작성한 글이 없습니다.</h2>
			<button type="button" id="write_btn" onclick="location.href='sbwriteview.sb'">글쓰기</button>
		</c:otherwise>
	</c:choose>

	<div id="pagingArea">
		<input type="hidden" id="currPage" value="${page }">
		<c:if test="${page <= 1 }">이전&nbsp;</c:if>
		<c:if test="${page > 1 }">
			<a>이전</a>
		</c:if>

		<c:forEach var="pageNum" begin="${startPage }" end="${endPage }">
			<c:if test="${pageNum == page }">
				${pageNum }
			</c:if>
			<c:if test="${pageNum != page }">
				<a>${pageNum }</a>
			</c:if>
		</c:forEach>

		<c:if test="${page >= maxPage }">&nbsp;다음</c:if>
		<c:if test="${page < maxPage }">
			<a>다음</a>
		</c:if>
	</div>