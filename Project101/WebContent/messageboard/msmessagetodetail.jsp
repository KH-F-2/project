<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>쪽지 - 디테일</h1>
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
				<td colspan=2><input type="button" value="답장하기"
					onClick="location.href='msmessagewrite2.ms'"> <input
					type="button" value="취소" onClick="window.history.go(-1)"></td>
			</tr>
		</tfoot>
	</table>
</div>
