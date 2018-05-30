<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>판매 게시판</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/test/js/reportboard.js"></script>
		<link href="/test/css/board_list.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table>
			<c:if test="${listcount>=1}">
				<tr>
					<th colspan="3">신고 게시판 - list</th>
					<th colspan="2">글 개수 : ${listcount }</th>
				</tr>
				<tr>
					<th width="8%"><div>번호</div></th>
					<th width="50%"><div>제목</div></th>
					<th width="14%"><div>작성자</div></th>
					<th width = 20%><div>날짜</div>
					<th width = 20%><div>조회수</div>
				</tr>
				
				
				<c:set var="num" value="${listcount-(page-1)*limit }"/>
				<c:forEach var="board" items="${boardlist }">
					<tr>
						<td>
							<c:out value="${num }"/>
							<c:set var="num" value="${num-1 }"/>
						</td>
						<td style="text-align: left;">
							<div>
								<a href="./BoardDetail.report?RB_NO=${board.RB_NO }">
									${board.RB_TITLE }
								</a>
							</div>
						</td>
						<td>
							<div>
								${board.RB_WRITER }
							</div>
						</td>
						<td>
							<div>
								${board.RB_DATE }
							</div>
						</td>
						<td>
							<div>
								${board.RB_READCOUNT }
							</div>
						</td>
					</tr>
				</c:forEach>
				
				<tr class="h30 lime center btn">
					<td colspan="5">
						<c:if test="${page<=1 }">
							이전&nbsp;
						</c:if>
						<c:if test="${page>1 }">
							<a href="./BoardList.report?page=${page-1 }">이전</a>&nbsp;
						</c:if>
						
						<c:forEach var="a" begin="${startpage }" end="${endpage }">
							<c:if test="${a==page }">
								${a }
							</c:if>
							<c:if test="${a!=page }">
								<a href="./BoardList.report?page=${a }">${a }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${page>=maxpage }">
							&nbsp;다음
						</c:if>
						<c:if test="${page<maxpage }">
							&nbsp;<a href="./BoardList.report?page=${page+1 }">다음</a>
						</c:if>
						
					</td>
				</tr>
				
			</c:if>
			
			
			<c:if test="${listcount==0}">
				<tr>
					<td colspan="4">신고 게시판</td>
					<td style="text-align:right;"><font style="margin-right:15px;" size=2>등록된 글이 없습니다.</font></td>
				</tr>
			</c:if>
				<tr>
					<td colspan="5" style="text-align:right; font-size: 14pt;"><a style="margin-right:10px;" href="./BoardWrite.report">[글쓰기]</a></td>
				</tr>
		</table>
	</body>
</html>