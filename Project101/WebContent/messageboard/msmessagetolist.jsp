<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<link href="./css/mslist.css" rel="stylesheet">
<body>
<h1 style="text-align:center">
	<img src="image/message.png" width="50px" height="50px">
		"${id }"님이 받은 쪽지함</h1>
	<table>
			<thead>
				<tr>
					<th width="22%">보낸사람</th>
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
							${b.MS_SEND}				
						</div>
						</td>
						<td width="50%"><a href="msmessagetodetail.ms?num=${b.MS_NO}">${b.MS_TITLE }</a></td>
						<td width="28%">${b.MS_DATE }</td>
					</tr>
			</c:forEach>
			
	<tr class="tr_move">
		<td colspan="3">
			<c:if test="${page <= 1 }">
				이전&nbsp;
			</c:if>
			<c:if test="${page > 1 }">
				<a href="./msmessagetolist.ms?page=${page-1 }">이전</a>&nbsp;
			</c:if>
			
			<c:forEach var="a" begin="${startpage }" end="${endpage }">
				<c:if test="${a == page }">
					${a }
				</c:if>
				<c:if test="${a != page }">
					<a href="./msmessagetolist.ms?page=${a }">${a}</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage }">
			&nbsp;다음
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="./msmessagetolist.ms?page=${page+1 }">&nbsp;다음</a>
			</c:if>
	</tr>
	</tbody>
			<tfoot>
				<tr>
					<td colspan="3" style="text-align: right; font-size: 14pt;"><a
						style="margin-right: 10px;" href="msmessagewrite2.ms" id="a_write">
						<img src="image/pencil.png" width="30px" height="30px">
						쪽지 쓰기</a>
						<a href="mypage.me">마이페이지</a>
					</td>
				</tr>
			</tfoot>
</table>
</body>