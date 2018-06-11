<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<style>
	table{
		width:60%;
		border:1px solid #cdd2d2;
		margin:0 auto
	}
	thead{
		font-size:22px;
		background:#D5D5D5;
	}
	tbody{
		font-size:18px;
		border: 1px solid #cdd2d2;
	}
	.tr_move {
		text-align: center;
	}
	tfoot{
		font-size:18px;
	}
	a {
		text-decoration:none
	}
	td {
		border-bottom:1px solid #cdd2d2
	}

</style>
<body>
	<h1 style="text-align:center">받은 쪽지 읽기</h1>
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