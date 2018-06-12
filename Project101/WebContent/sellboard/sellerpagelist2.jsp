<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.sellboard_table{
		width:100%;
		text-align: center;
	}
	.thead11{
		font-size:22px
	}
	body{
		font-size:18px;
	}
	.last_tr {
		 border:1px solid #cdd2d2;
	}
	.tbody {
		 border:1px solid #cdd2d2;
	}
</style>
	<body>
	
	<c:set var="b_p" value="${boardpage }"/>
		<table class="sellboard_table">
			<c:if test="${b_p.listcount>=1}">
			<thead class="thead11">
				<tr>
					<th colspan="3">판매 게시판 - list</th>
					<th colspan="2">글 개수 : ${b_p.listcount }</th>
				</tr>
			</thead>	
				<tr>
					<th width="8%">번호</th>
					<th width="50%">제목</th>
					<th width="14%">작성자</th>
					<th width="17%">날짜</th>
					<th width="11%">조회수</th>
				</tr>
			
				
				<c:set var="num" value="${b_p.listcount-(b_p.page-1)*b_p.limit }"/>
				<tbody class="tbody">
					 <c:forEach var="board" items="${list}">
						<tr>
							<td width="8%">
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td width="50%" align="left">
								&nbsp;<a href="./sbview.sb?num=${board.SB_NO }">
									${board.SB_TITLE }
								</a>
							</td>
							<td width="14%">
							
							<a href = "./sellerpage_main2.me?writer=${board.SB_WRITER }">
								${board.SB_WRITER }
							</a>
							
							
							</td>
							<td width="19%">${board.SB_DATE }</td>
							<td width="11%">${board.SB_READCOUNT }</td>
						</tr>
					</c:forEach> 
				
				
				<tr class="last_tr">
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
