<%@ page language="java" contentType="text/html; charset=UTF-8"
  	pageEncoding="UTF-8"%>
  	<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>판매자 페이지</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
	</head>
	<body>
	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle"><i>&#xe801;</i>  우리지금만나</h1>
		<h2>Category</h2>
		<a class="item" href="pbmain.pb">구매게시판</a>
		<a class="item" href="sbmain.sb">판매게시판</a>
		<a class="item" href="ebmain.eb">후기게시판</a>
		<a class="item" href="rbmain.rb">신고게시판</a>
	</div>
	
	<div class="pusher">
	
		<div id="header">
			<jsp:include page='header.jsp' />
		</div>
		
		<div id="main_top">
		<span>회원님의 등록된 판매 게시글</span><br>
		</div>
		
		<div id = "list_category">
			<a class = "item" href = "sellerpage_main.me?writer=${writer }">앨범형</a>
			<a class = "item" href = "sellerpage_main2.me?writer=${writer }">게시판형</a>
			
		</div>
	
		<c:set var="b_p" value="${boardpage }"/>
		<table class="sellboard_table">
			<c:if test="${b_p.listcount>=1}">
			<thead>
				<tr>
					<th colspan="3">판매 게시판 - list</th>
					<th colspan="2">글 개수 : ${b_p.listcount }</th>
				</tr>
				<tr>
					<th width="8%">번호</th>
					<th width="50%">제목</th>
					<th width="14%">작성자</th>
					<th width="17%">날짜</th>
					<th width="11%">조회수</th>
				</tr>
			</thead>	
				
				<c:set var="num" value="${b_p.listcount-(b_p.page-1)*b_p.limit }"/>
				<tbody>
					<c:forEach var="board" items="${list}">
						<tr>
							<td width="8%">
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td width="50%" align="left">
								&nbsp;<a href="./BoardDetail.sell?num=${board.SB_NO }">
									${board.SB_TITLE }
								</a>
							</td>
							<td width="14%">
							
							<a href = "./sellerpage_main2.me?writer=${board.SB_WRITER }">
								${board.SB_WRITER }
							</a>
							
							
							</td>
							<td width="17%">${board.SB_DATE }</td>
							<td width="11%">${board.SB_READCOUNT }</td>
						</tr>
					</c:forEach>
				
				
				<tr>
					<td colspan="5">
						<c:if test="${b_p.page<=1 }">
							이전&nbsp;
						</c:if>
						<c:if test="${b_p.page>1 }">
							<a href="./BoardList.sell?page=${b_p.page-1 }">이전</a>&nbsp;
						</c:if>
						
						<c:forEach var="a" begin="${b_p.startpage }" end="${b_p.endpage }">
							<c:if test="${a==b_p.page }">
								${a }
							</c:if>
							<c:if test="${a!=b_p.page }">
								<a href="./BoardList.sell?page=${a }&word=${b_p.searchWord}&item=${b_p.searchItem}">${a }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${b_p.page>=b_p.maxpage }">
							&nbsp;다음
						</c:if>
						<c:if test="${b_p.page<b_p.maxpage }">
							&nbsp;<a href="./BoardList.sell?page=${b_p.page+1}&word=${b_p.searchWord}&item=${b_p.searchItem}">다음</a>
						</c:if>
						
					</td>
				</tr>
				</tbody>
				
			</c:if>
			</table>
	</body>
</html>