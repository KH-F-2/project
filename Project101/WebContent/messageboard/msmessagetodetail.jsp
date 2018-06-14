<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<link href="./css/msdetail.css" rel="stylesheet">
<body>
	<div id="ccc">
		<div id="abc">
			<img src="image/to.png" class="img">	
			<h1>받은 쪽지 읽기</h1>
		</div>
	</div>
	<div>
		<table border=1>
			<thead>
				<tr>
					<th>보낸사람</th>
					<th>${ms.MS_SEND }</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td>날짜</td>
					<td>${ms.MS_DATE }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${ms.MS_TITLE }</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>${ms.MS_CONTENT }</td>
				</tr>
			</tbody>
			
			<tfoot>
				<tr>
					<td colspan=2 style="text-align:right">
					<input type="button" value="답장하기" onClick="location.href='msmessagewrite2.ms'">
					<input type="button" value="취소" onClick="window.history.go(-1)">
					<input type="button" value="삭제하기" onClick="location.href='msmessagedeleteaction.ms?MS_SEND=${ms.MS_SEND}'">
					</td>
				</tr>
			</tfoot>			
		</table>	
	</div>
</body>