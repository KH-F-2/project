<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="./js/reportboard.js"></script>

<style type="text/css">
	table{
		width:50%;
		border:1px solid #cdd2d2;
		margin:0 auto;
		border-collapse: collapse;
		box-shadow: 0 0 13px rgba(33,33,33,0.2)
	}
	tbody>tr:first-child{
		font-size:22px;
		background:#D5D5D5;
		text-align: center;
	}
	tbody{
		font-size:18px;
		border: 1px solid #cdd2d2;
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
	a:hover {
		background-color:#FFF2E6		
	}
	tr.h30.lime.center.btn{
		text-align: center;
	}
	h1{
		text-align: center;
		font-size: 25pt;
		margin: 10px 0px;
		text-shadow: 0 0 10px rgba(55,33,33,0.5)
	}
</style>

<h1>신고 게시판</h1>

<table>
	<c:if test="${listcount>=1}">
		<tr>
			<th width="10%"><div>번호</div></th>
			<th width="20%"><div>신고 대상</div></th>
			<th width="50%"><div>제목</div></th>
			<th width=20%><div>날짜</div>
		</tr>

		<c:set var="num" value="${listcount-(page-1)*limit }" />
		<c:forEach var="board" items="${reportList}">
			<tr>
				<td>
					<c:out value="${num }" />
					<c:set var="num" value="${num-1 }" />
				</td>
				<td>
					<div>${board.RB_RP_ID }</div>
				</td>
				<td style="text-align: left;">
					<div>
						<a href="./rbview.rb?RB_NO=${board.RB_NO }"> ${board.RB_TITLE }
					</a>
					</div>
				</td>
				<td>
					<div>${board.RB_DATE }</div>
				</td>
			</tr>
		</c:forEach>

		<tr class="h30 lime center btn">
			<td colspan="4">
				<c:if test="${page<=1 }">
					이전&nbsp;
				</c:if>
				<c:if test="${page>1 }">
					<a href="./rbmain.rb?page=${page-1 }">이전</a>&nbsp;
				</c:if>
				<c:forEach var="a" begin="${startpage }" end="${endpage }">
					<c:if test="${a == page }">
						${a }
					</c:if>
					<c:if test="${a != page }">
						<a href="./rbmain.rb?page=${a }">${a }</a>
					</c:if>
				</c:forEach>
				<c:if test="${page>=maxpage }">
					&nbsp;다음
				</c:if>
				<c:if test="${page<maxpage }">
					&nbsp;<a href="./rbmain.rbt?page=${page+1 }">다음</a>
				</c:if>
			</td>
		</tr>
	</c:if>

	<c:if test="${listcount==0}">
		<tr>
			<td colspan="4">신고 게시판</td>
			<td style="text-align: right;">
				<font style="margin-right: 15px;" size=2>등록된 글이 없습니다.</font>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="4" style="text-align: right; font-size: 14pt;">
			<img src="image/pencil.png" width="30px" height="30px">
			<a style="margin-right: 10px;" href="./rbwrite.rb">[글쓰기]</a>
		</td>
	</tr>
</table>
