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
<!-- datepicker -->
<script>
	$(function() {
		$("#Datepicker").datepicker(
				{
					dateFormat : 'yy.mm.dd',
					prevText : '이전 달',
					nextText : '다음 달',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					dateFormat : 'yy년 mm월 dd일',
					showMonthAfterYear : true,
					changeMonth : true,
					changeYear : true,
					yearSuffix : '년',
					showButtonPanel : true,
					closeText : 'Clear',
					onClose : function(dateText, inst) {
						if ($(window.event.srcElement).hasClass(
								'ui-datepicker-close')) {
							document.getElementById(this.id).value = '';
						}
					}
				});
	});

	$(function() {
		$("#Datepicker2").datepicker(
				{
					dateFormat : 'yy.mm.dd',
					prevText : '이전 달',
					nextText : '다음 달',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					dateFormat : 'yy년 mm월 dd일',
					showMonthAfterYear : true,
					changeMonth : true,
					changeYear : true,
					yearSuffix : '년',
					showButtonPanel : true,
					closeText : 'Clear',
					onClose : function(dateText, inst) {
						if ($(window.event.srcElement).hasClass(
								'ui-datepicker-close')) {
							document.getElementById(this.id).value = '';
						}
					}
				});
	});
</script>
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
	width: 45%;
} /*제목 열 크기*/
#ulTable>li>ul>li:first-child+li+li {
	width: 20%;
} /*작성일 열 크기*/
#ulTable>li>ul>li:first-child+li+li+li {
	width: 15%;
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
			<span>후기게시판</span>
			<!-- 게시판 목록  -->
			<li>
				<ul id="ulTable">
					<li>
						<ul>
							<li>글번호</li>
							<li>제 목</li>
							<li>작성자</li>
							<li>작성일</li>
							<li>조회수</li>
						</ul>
					</li>
					
					<!-- 글번호 -->
					<%-- <c:set var="num" value="${listcount-(page-1)*limit }" /> --%>
					<c:forEach var="board" items="${requestScope.list}">
						<li>
							<ul>
								<li><%-- <c:out value="${num }" /> <c:set var="num"
										value="${num-1 }" /> --%>
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
			<hr>
			<li><input type="button" id="write_button" value="글쓰기"
				onClick="location.href='BoardWrite.epil'"></li>

			<!-- 검색 폼 영역 -->
			<li id='liSearchOption'>
				<div id="searchForm">
					<form>
					<select id='pick_all'>
						<option value="pick">기간선택</option>
						<option value="all">전체기간</option>
					</select>
					
					<select name="opt">
						<option value="0">제목</option>
						<option value="1">내용</option>
						<option value="2">제목+내용</option>
						<option value="3">글쓴이</option>
					</select>
					
					<input type="text" id="txtKeyWord" name="condition" />
					<input type="submit" value='검색' /><br>
					
					<input type="text" id="Datepicker" size="13px"> ~
					<input type="text" id="Datepicker2" size="13px">
					</form>
				</div>

			</li>
		</ul>
	</div>
</body>
</html>