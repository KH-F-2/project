<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	a {
		text-decoration: none
	}
</style>

<script src="/test/js/sellboard.js"></script>

<table>
	<tr class="center">
		<th colspan="2">신고 게시판 - view 페이지</th>
	</tr>
	<tr>
		<td>신고 대상</td>
		<td>${boardbean.RB_RP_ID }
		</td>
	</tr>
	<tr>
		<td>신고 글 번호</td>
		<td>${boardbean.RB_RP_NO }
		</td>
	</tr>
	<tr>
		<td><div>제목</div></td>
		<td><div>${boardbean.RB_TITLE}</div></td>
	</tr>
	<tr>
		<td><div>내용</div></td>
		<td><div>${boardbean.RB_CONTENT }</div></td>
	</tr>
	<tr class="center">
		<td colspan="2">
			<%--<c:if test="${id=='admin'||id==reportboard. B_WRITER}">--%> <a
			href="<c:url value='/rbmodifyview.rb?RB_NO=${boardbean.RB_NO}'/>">
				<input type="button" value="수정">
		</a>&nbsp; <a
			href="<c:url value='/rbdelete.rb?RB_NO=${boardbean.RB_NO}'/>"> <input
				type="button" value="삭제">
		</a>&nbsp; <%--</c:if>--%> <a href="<c:url value='/rbmain.rb'/>"> <input
				type="button" value="목록">
		</a>&nbsp;
	</tr>
</table>