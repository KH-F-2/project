<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<link href="./css/mswrite.css" rel="stylesheet">
<body>
	<div id="ccc">
		<div id="abc">
			<img src="image/send.png" class="img">	
			<h1>쪽지 보내기</h1>
		</div>
	</div>
	<form action="msmessageaddaction2.ms" method="post">
		<table border=1>
			<thead>
				<tr>
					<th>회원아이디</th>
					<th class="th_id"><input type="text" value="${id }" name="MS_SEND" size="20px" disabled style="width:100%"></th>
				</tr>				
			</thead>
			
			<tbody>
				<tr>
					<td>받는사람</td>
					<td><input type="text" value="" placeholder="받는사람입력" name="MS_TO" size="50px" style="width:100%"></td>
				</tr>				
				<tr>
					<td>제목</td>
					<td><input type="text" value="" placeholder="제목입력" name="MS_TITLE" size="50px" style="width:100%"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea placeholder="내용입력" name="MS_CONTENT" class="MS_CONTENT" rows="7" cols="52" style="resize:none;width:100%"></textarea></td>
				</tr>
			</tbody>
			
			<tfoot>
				<tr>
					<td colspan=2 style="text-align: right;">
					<input type="submit" value="쪽지보내기" class="send">
					<input type="button" value="취소" onClick="window.history.go(-1)" class="cancel">
					</td>
				</tr>
			</tfoot>			
		</table>	
	</form>
</body>