<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<link href="./css/mslist.css" rel="stylesheet">
<body>
<h1 style="text-align:center">
	<img src="image/message.png" width="50px" height="50px">
		"${id }"님이 보낸 쪽지함</h1>
	<table>
		<thead>
				<tr>
					<th width="22%">받는사람</th>
					<th width="50%">제목</th>
					<th width="28%">날짜</th>
				</tr>
			</thead>
			
			<c:set var="num" value="${listcount-(page-1)*10 }"/>
			<tbody>
			<c:forEach var="b" items="${msBoardBean }">			
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
			</tbody>
	<tfoot>
	<tr class="tr_move">
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
				<tr>
					<td colspan="3" style="text-align: right;"><a
						style="margin-right: 10px;" href="msmessagewrite2.ms" id="a_write">
							<img src="image/pencil.png" width="30px" height="30px">쪽지 쓰기</a>
					<a href="./msmessagetolist.ms">받은쪽지함</a>
					<a href="mypage.me?id=${id }">마이페이지</a>
					</td>
				</tr>
			</tfoot>
</table>
</body>