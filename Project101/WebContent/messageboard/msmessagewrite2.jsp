<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<style>
	table{
		width:60%;		
		border:1px solid #cdd2d2;
		margin:0 auto;
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
	input{
		margin-right: 10px;
	}
	.th_id{
		text-align:left
	}
</style>
<body>
	<h1 style="text-align:center">쪽지 보내기</h1>
	<form action="msmessageaddaction2.ms" method="post">
		<table border=1>
			<thead>
				<tr>
					<th>회원아이디</th>
					<th  class="th_id"><input type="text" value="${id }" name="MS_SEND" size="20px" disabled ></th>
				</tr>				
			</thead>
			
			<tbody>
				<tr>
					<td>받는사람</td>
					<td><input type="text" value="" placeholder="받는사람입력" name="MS_TO" size="50px" ></td>
				</tr>				
				<tr>
					<td>제목</td>
					<td><input type="text" value="" placeholder="제목입력" name="MS_TITLE" size="50px"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text" value="" placeholder="내용입력" name="MS_CONTENT" size="50px"></td>
				</tr>
			</tbody>
			
			<tfoot>
				<tr>
					<td colspan=2 style="text-align: right;">
					<input type="submit" value="쪽지보내기">
					<input type="button" value="취소" onClick="window.history.go(-1)">
					</td>
				</tr>
			</tfoot>			
		</table>	
	</form>
</body>