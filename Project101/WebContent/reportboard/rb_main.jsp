<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="./js/reportboard.js"></script>

<style type="text/css">
     body{
        line-height:2em;        
        font-family:"맑은 고딕";
}
    ul, li{ 
        list-style:none;
        text-align:center;
        padding:0;
        margin:0;
}

    #mainWrapper{
        width: 800px;
        margin: 0 auto; /*가운데 정렬*/
    }

    #mainWrapper > ul > li:first-child {
        text-align: center;
        font-size:14pt;
        height:40px;
        vertical-align:middle;
        line-height:30px;
}

    #ulTable {margin-top:10px;}
    

    #ulTable > li:first-child > ul > li {
        background-color:#c9c9c9;
        font-weight:bold;
        text-align:center;
}

    #ulTable > li > ul {
        clear:both;
        padding:0px auto;
        position:relative;
        min-width:40px;
}
    #ulTable > li > ul > li { 
        float:left;
        font-size:10pt;
        border-bottom:1px solid silver;
        vertical-align:baseline;
}    

    #ulTable > li > ul > li:first-child               {width:10%;} /*No 열 크기*/
    #ulTable > li > ul > li:first-child +li           {width:45%;} /*제목 열 크기*/
    #ulTable > li > ul > li:first-child +li+li        {width:20%;} /*작성일 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li     {width:15%;} /*작성자 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li+li{width:10%;} /*조회수 열 크기*/

    #divPaging {
          clear:both; 
        margin:0 auto; 
        width:220px; 
        height:50px;
}

    #divPaging > div {
        float:left;
        width: 30px;
        margin:0 auto;
        text-align:center;
}

    #liSearchOption {clear:both;}
    #liSearchOption > div {
        margin:0 auto; 
        margin-top: 30px; 
        width:auto; 
        height:100px; 

}

    .left {
        text-align : left;
} 
	th{
		 border: 1px solid silver;
		 background-color: silver;
		 color: white;
		 
	}
	td{
		 border: 1px solid silver;
		 text-align: center;
	}
	.gray{
		background-color: gray;
	}
	table{
		border-radius:10px;
	}

</style>

<table>
	<c:if test="${listcount>=1}">
		<tr>
			<th colspan="3">신고 게시판 - list</th>
			<th colspan="1">글 개수 : ${listcount }</th>
		</tr>
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
			<a style="margin-right: 10px;" href="./rbwrite.rb">[글쓰기]</a>
		</td>
	</tr>
</table>
