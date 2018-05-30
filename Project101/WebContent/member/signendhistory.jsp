<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta charset="utf-8">
<title>Canvas</title>

<style type="text/css">
body {
	line-height: 2em;
	font-family: "맑은 고딕";
}

ul, li {
	list-style: none;
	text-align: center;
	padding: 0;
	margin: 0;
}

#wrap {
	margin: 0 auto; /*가운데 정렬*/
	border: 1px solid black;
	margin: 0 auto;
	width: 800px;
	margin-top: 150px
}

#mainWrapper>ul>li:first-child {
	text-align: center;
	height: 40px;
	vertical-align: middle;
	line-height: 30px;
}

#ulTable {
	margin-top: 10px;
}

#ulTable>li:first-child>ul>li {
	background-color: #c9c9c9;
	font-weight: bold;
	text-align: center;
}

#ulTable>li>ul {
	clear: both;
	padding: 0px auto;
	position: relative;
	min-width: 40px;
}

#ulTable>li>ul>li {
	float: left;
	font-size: 10pt;
	border-bottom: 1px solid silver;
	vertical-align: baseline;
}

#ulTable>li>ul>li:first-child {
	width: 10%;
} /*No 열 크기*/
#ulTable>li>ul>li:first-child+li {
	width: 40%;
} /*제목 열 크기*/
#ulTable>li>ul>li:first-child+li+li {
	width: 18%;
} /*작성일 열 크기*/
#ulTable>li>ul>li:first-child+li+li+li {
	width: 13%;
} /*작성자 열 크기*/
#ulTable>li>ul>li:first-child+li+li+li+li {
	width: 10%;
} /*조회수 열 크기*/
#divPaging {
	clear: both;
	margin: 0 auto;
	width: 220px;
	height: 50px;
}

#divPaging>div {
	width: 30px;
	margin: 0 auto;
	text-align: center;
}

#liSearchOption {
	clear: both;
}

#liSearchOption>div {
	margin: 0 auto;
	margin-top: 30px;
	width: auto;
	height: 100px;
}

.left {
	text-align: left;
}

span {
	text-align: center;
	font-weight: bold;
	font-size: 30px
}

#write_button {
	float: right;
	width: 100px;
	height: 35px;
	font-size: 20px;
	margin-right: 10px
}

span {
	float: left
}

.ui-datepicker-title {
	font-size: 15px
}

#ui-datepicker-div {
	width: 300px;
	height: 380px
}

.ui-widget {
	font-size: 15px
}

a {
	text-decoration: none;
	color: black
}
</style>

</head>
<body>

	<div id="wrap">
		<ul>
			<span>거래종료내역</span>
			<a href="javascript:history.back()">뒤로가기</a>
			<!-- 게시판 목록  -->
			<li>
				<ul id="ulTable">
					<li>
						<ul>
							<li>글번호</li>
							<li>카테고리</li>
							<li>제 목</li>
							<li>거래금액</li>
							<li>등록일</li>
							<li>작성자</li>
						</ul>
					</li>
					
					<!-- 글번호 -->
					 <c:forEach var="eh" items="${requestScope.list}">
						<li>
							<ul>
								<li><c:out value="${num }" /> <c:set var="num"
										value="${num-1 }" />
										${board.SB_NO}</li>
				
								<li><a href="./BoardDetail.epil?num=${board.SB_NO }">
										${board.SB_TITLE } </a></li>

								<li>${board.SB_WRITER }</li>

								<li>${board.SB_DATE }</li>

								<li>${board.SB_READCOUNT }</li>
							</ul>
						</li>
					</c:forEach>

				</ul>
			</li>


			<!-- 게시판 페이징 영역 -->
			<li>
				<div id="divPaging">
					<c:if test="${startPage != 1}">
						<a href='BoardList.epil?page=${startPage-1}'>[ 이전 ]</a>
					</c:if>

					<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
						<c:if test="${pageNum == spage}">
                ${pageNum}&nbsp;
            </c:if>
						<c:if test="${pageNum != spage}">
							<a href='BoardList.epil?page=${pageNum}'>${pageNum}&nbsp;</a>
						</c:if>
					</c:forEach>

					<c:if test="${endPage != maxPage }">
						<a href='BoardList.epil?page=${endPage+1 }'>[다음]</a>
					</c:if>
				</div>
			</li>
						
		</ul>
	</div>
</body>
</html>