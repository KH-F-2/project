<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<body>
<h1>${id }이 보낸 쪽지함</h1>
	<table border=1>
			<thead>
			<tr>
				<th colspan="3">보낸 쪽지함 </th>
			</tr>
				<tr>
					<th width="22%">받는사람</th>
					<th width="50%">제목</th>
					<th width="28%">날짜</th>
				</tr>
			</thead>
			
			<c:set var="num" value="${listcount-(page-1)*10 }"/>
			<c:forEach var="b" items="${msBoardBean }">			
			<tbody>
					<tr>
						<td width="22%">
						<div>						
							${b.MS_TO}						
						</div>
						</td>
						<td width="50%"><a href="msmessagesenddetail.ms?num=${b.MS_NO}">${b.MS_TITLE }</a></td>
						<td width="28%">${b.MS_DATE }</td>
					</tr>
			</c:forEach>
			
	<tr class="h30 lime center btn">
		<td colspan="3">
			<c:if test="${page <= 1 }">
				이전&nbsp;
			</c:if>
			<c:if test="${page > 1 }">
				<a href="./msmessagesendlist.ms?page=${page-1 }">이전</a>&nbsp;
			</c:if>
			
			<c:forEach var="a" begin="${startpage }" end="${endpage }">
				<c:if test="${a == page }">
					${a }
				</c:if>
				<c:if test="${a != page }">
					<a href="./msmessagesendlist.ms?page=${a }">${a}</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
			&nbsp;다음
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="./msmessagesendlist.ms?page=${page+1 }">&nbsp;다음</a>
			</c:if>
	</tr>
	</tbody>
	<%-- <c:if test="${listcount == 0 }">
		<tr>
			<td colspan="3">보낸 쪽지함</td>
			<td style="text-align: right">
				<font size=2>보낸 쪽지가 없습니다.</font>
			</td>
		</tr>
	</c:if>	 --%>
			<tfoot>
				<tr>
					<td colspan="3" style="text-align: right; font-size: 14pt;"><a
						style="margin-right: 10px;" href="msmessagewrite2.ms" id="a_write">쪽지 쓰기
						</a>
					<a href="mypage.me">마이페이지로 이동</a>
					</td>
				</tr>
			</tfoot>
</table>
</body>