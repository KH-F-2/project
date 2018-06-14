<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="./css/mypage.css">
<script src="./js/mypage.js"></script>

<div id="mysec1">
	<div id="sec1container">
		<div id="sec1col1">&#xe81a;</div>
		<div id="sec1col2">
			<span id="id">아이디 : ${member.id }</span><br>
			<hr><br>
			<span id="nickname">닉네임 : ${member.nickname }</span><br>
			<hr><br>
			<span id="email">이메일 : ${member.email }</span>
		</div>
		<div id="sec1col3">
			<input type="button" id="modifyinfo" value="&#xe80a; 정보수정">
			<input type="button" id="message" value="&#xe81b; 메세지함">
			<input type="button" id="servicecenter" value="&#xe812; 고객센터">
			<input type="button" id="withdrawal" value="&#xe83c; 회원탈퇴">
		</div>
	</div>
</div>

<div id="mysec2">
	<div id="sec2container">
		<p style="text-align:left; margin-left:100px">내가 찜 한 글</p>
		
		<c:choose>
			<c:when test="${!empty interestArr }">
				<c:forEach var="item" items="${interestArr }">
					<div class="content">
						<input type="hidden" class="content_num" value="${item.content_num }">
						<input type="hidden" class="board_name" value="${item.board_name }">
						
						<a href="sbview.sb?num=${item.content_num }&board_name=${item.board_name }">
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
						<input type="button" class="interestDelete" value="찜 취소">
						<input type="button" class="interestEpil" value="후기작성" onclick="interestEpil(${item.num}, '${item.board_name}')">
					</div>
				</c:forEach>
			</c:when>
			
			<c:otherwise>
				<h2>아직 찜 한 글이 없습니다.</h2>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div id="mysec3">
	<div id="sec3container">
	<p style="text-align:left; margin-left:100px">내가 작성 한 글</p>
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
	</div>

</div>