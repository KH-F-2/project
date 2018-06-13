<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>쪽지 보내기</h1>
<form action="msmessageaddaction2.ms" method="post">
	<table border=1>
		<thead>
			<tr>
				<th>회원아이디</th>
				<th><input type="text" value="${id }" name="MS_SEND" disabled></th>
			</tr>
			<tr>
				<td>받는사람</td>
				<td><input type="text" value="" placeholder="받는사람입력"
					name="MS_TO"></td>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>제목</td>
				<td><input type="text" value="" placeholder="제목입력"
					name="MS_TITLE"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" value="" placeholder="내용입력"
					name="MS_CONTENT"></td>
			</tr>
		</tbody>

		<tfoot>
			<tr>
				<td colspan=2><input type="submit" value="쪽지보내기"> <input
					type="button" value="취소" onClick="window.history.go(-1)"></td>
			</tr>
		</tfoot>
	</table>
</form>
