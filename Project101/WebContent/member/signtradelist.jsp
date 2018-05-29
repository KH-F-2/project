<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
div[id=header] {
	height: 130px;
	height: 80px;
	text-align: center;
	background-color: #5CD1E5;
	margin: 0 auto;
}

form {
	border: 2px solid black
}
</style>

<%
	String id = request.getParameter("id");
%>
<div id="header">
	<c:choose>
		<c:when test="${name eq 'admin'}">
		<span>관리자 ${id }님 환영합니다.</span>
		<!-- 게시판 리스트 -->
		<table border=1>
			<!-- 레코드가 있으면 -->
			<!-- core 라이브러리 사용 -->
			<c:if test="${listcount > 0 }">
				<tr>
					<th colspan="6">거래중물품</th>
					<%-- <th colspan="3">
			<font size=2>글 개수 : ${listcount }</font>
		</th> --%>
				</tr>

				<tr>
					<th width="16.66%"><div>번호</div></th>
					<th width="16.66%"><div>카테고리</div></th>
					<th width="16.66%"><div>제목</div></th>
					<th width="16.66%"><div>가격</div></th>
					<th width="16.66%"><div>날짜</div></th>
					<th width="16.66%"><div>작성자</div></th>
				</tr>
				<c:set var="num" value="${listcount-(page-1)*10 }" />
				<c:forEach var="b" items="${tr }">
					<tr>
						<td><c:out value="${num }" />
							<%--num 출력 --%> <c:set var="num" value="${num-1}" /> <%--num = num - 1--%>
						</td>
						<td>
							<div>${b.TR_CATEGORY }</div>
						</td>

						<td>
							<div>
								<!-- 이 과정을 통해서 detail에 TR_NUM값을 넘겨준거다 -->
								<a href="./signtradedetail.mem?num=${b.TR_NUM }">${b.TR_SUBJECT }</a>
							</div>
						</td>

						<td>
							<div>${b.TR_PRICE }</div>
						</td>
						<td>
							<div>${b.TR_DATE }</div>
						</td>
						<td>
							<div>${b.TR_NAME }</div>
						</td>
					</tr>
				</c:forEach>
				<tr class="h30 lime center btn">
					<td colspan="6"><c:if test="${page <= 1 }">
				이전&nbsp;
			</c:if> <c:if test="${page > 1 }">
							<a href="./signtradelist.mem?page=${page-1 }">이전</a>&nbsp;
			</c:if> <c:forEach var="a" begin="${startpage }" end="${endpage }">
							<c:if test="${a == page }">
					${a }
				</c:if>
							<c:if test="${a != page }">
								<a href="./signtradelist.mem?page=${a }">${a}</a>
							</c:if>
						</c:forEach> <c:if test="${page >= maxpage }">
			&nbsp;다음
			</c:if> <c:if test="${page < maxpage }">
							<a href="./signtradelist.mem?page=${page+1 }">&nbsp;다음</a>
						</c:if>
				</tr>
			</c:if>

			<!-- 레코드가 없으면. -->
			<c:if test="${listcount == 0 }">
				<tr>
					<td colspan="5">MVC 게시판</td>
					<td style="text-align: right"><font size=2>등록된 글이 없습니다.</font>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="6" style="text-align: right"><a
					href="./signmypage.mem">[이전으로]</a></td>
			</tr>
		</table>		
	</c:when>
	
	<c:when test="${name ne 'admin'}">
     	   <span>회원${id }님 환영합니다.</span>
     	   <!-- 게시판 리스트 -->
		<table border=1>
			<!-- 레코드가 있으면 -->
			<!-- core 라이브러리 사용 -->
			<c:if test="${listcount > 0 }">
				<tr>
					<th colspan="6">거래중물품</th>
					<%-- <th colspan="3">
			<font size=2>글 개수 : ${listcount }</font>
		</th> --%>
				</tr>

				<tr>
					<th width="16.66%"><div>번호</div></th>
					<th width="16.66%"><div>카테고리</div></th>
					<th width="16.66%"><div>제목</div></th>
					<th width="16.66%"><div>가격</div></th>
					<th width="16.66%"><div>날짜</div></th>
					<th width="16.66%"><div>작성자</div></th>
				</tr>
				<c:set var="num" value="${listcount-(page-1)*10 }" />
				<c:forEach var="b" items="${tr }">
					<tr>
						<td><c:out value="${num }" />
							<%--num 출력 --%> <c:set var="num" value="${num-1}" /> <%--num = num - 1--%>
						</td>
						<td>
							<div>${b.TR_CATEGORY }</div>
						</td>

						<td>
							<div>
								<!-- 이 과정을 통해서 detail에 TR_NUM값을 넘겨준거다 -->
								<a href="./signtradedetail.mem?num=${b.TR_NUM }">${b.TR_SUBJECT }</a>
							</div>
						</td>

						<td>
							<div>${b.TR_PRICE }</div>
						</td>
						<td>
							<div>${b.TR_DATE }</div>
						</td>
						<td>
							<div>${b.TR_NAME }</div>
						</td>
					</tr>
				</c:forEach>
				<tr class="h30 lime center btn">
					<td colspan="6"><c:if test="${page <= 1 }">
				이전&nbsp;
			</c:if> <c:if test="${page > 1 }">
							<a href="./signtradelist.mem?page=${page-1 }">이전</a>&nbsp;
			</c:if> <c:forEach var="a" begin="${startpage }" end="${endpage }">
							<c:if test="${a == page }">
					${a }
				</c:if>
							<c:if test="${a != page }">
								<a href="./signtradelist.mem?page=${a }">${a}</a>
							</c:if>
						</c:forEach> <c:if test="${page >= maxpage }">
			&nbsp;다음
			</c:if> <c:if test="${page < maxpage }">
							<a href="./signtradelist.mem?page=${page+1 }">&nbsp;다음</a>
						</c:if>
				</tr>
			</c:if>

			<!-- 레코드가 없으면. -->
			<c:if test="${listcount == 0 }">
				<tr>
					<td colspan="5">MVC 게시판</td>
					<td style="text-align: right"><font size=2>등록된 글이 없습니다.</font>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="6" style="text-align: right"><a
					href="./signmypage.mem">[이전으로]</a></td>
			</tr>
		</table>
    </c:when>	
	</c:choose>
</div>