<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>판매 게시판</title>
        <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        
        <script type="text/javascript">
	        $(document).ready(function(){
	        	$('#a_write').click(function(){
	        		var id=<%=session.getAttribute("id")%>
	        		if(id==null){
	        			alert('로그인 후 이용하실 수 있습니다.');
	        			location.href='./signin.me';
	        			return false;
	        		}
	        	});
	        	/* $('#search_btn').click(function(){
	        		var word=$('input[name=search_input').val();
	        		var item=$('#search_sel').val();
	        		location.href='./sbmain.sb?word='+word+'&item='+item;
	        	}); */
	        });
        </script>
		<link href="css/sblist.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<c:set var="b_p" value="${boardPageBean }"/>
		<table class="sellboard_table">
			<c:if test="${b_p.listcount>=1}">
			<thead>
				<tr>
					<th colspan="3">판매 게시판 - list</th>
					<th colspan="2">글 개수 : ${b_p.listcount }</th>
				</tr>
				<tr>
					<th width="8%">번호</th>
					<th width="50%">제목</th>
					<th width="14%">작성자</th>
					<th width="17%">날짜</th>
					<th width="11%">조회수</th>
				</tr>
			</thead>	
				
				<c:set var="num" value="${b_p.listcount-(b_p.page-1)*b_p.limit }"/>
				<tbody>
					<c:forEach var="boardBean" items="${b_p.boardBeanList }">
						<tr>
							<td width="8%">
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td width="50%" align="left">
								&nbsp;<a href="./sbview.sb?num=${boardBean.SB_NO }">
									${boardBean.SB_TITLE }
								</a>
							</td>
							<td width="14%">${boardBean.SB_WRITER }</td>
							<td width="17%">${boardBean.SB_DATE }</td>
							<td width="11%">${boardBean.SB_READCOUNT }</td>
						</tr>
					</c:forEach>
				
				
				<tr>
					<td colspan="5">
						<c:if test="${b_p.page<=1 }">
							이전&nbsp;
						</c:if>
						<c:if test="${b_p.page>1 }">
							<a href="./sbmain.sb?page=${b_p.page-1 }">이전</a>&nbsp;
						</c:if>
						
						<c:forEach var="a" begin="${b_p.startpage }" end="${b_p.endpage }">
							<c:if test="${a==b_p.page }">
								${a }
							</c:if>
							<c:if test="${a!=b_p.page }">
								<a href="./sbmain.sb?page=${a }&word=${b_p.searchWord}&item=${b_p.searchItem}">${a }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${b_p.page>=b_p.maxpage }">
							&nbsp;다음
						</c:if>
						<c:if test="${b_p.page<b_p.maxpage }">
							&nbsp;<a href="./sbmain.sb?page=${b_p.page+1}&word=${b_p.searchWord}&item=${b_p.searchItem}">다음</a>
						</c:if>
						
					</td>
				</tr>
				</tbody>
				
			</c:if>
			
			
			<c:if test="${b_p.listcount==0}">
			<thead>
				<tr>
					<td colspan="5">판매 게시판</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5">
						<font style="margin-right:15px;" size=2>등록된 글이 없습니다.</font>
					</td>
				</tr>
			</tbody>
			</c:if>
			<tfoot>
				<tr>
					<td colspan="5" style="text-align:right; font-size: 14pt;">
						<a style="margin-right:10px;" href="./sbwriteview.sb" id="a_write">글쓰기</a>
					</td>
				</tr>
			</tfoot>
		</table>
		<!-- <div class="search">
			<select id="search_sel">
			    <option value="title" selected="selected">제목</option>
			    <option value="content">내용</option>
			    <option value="title_content">제목+내용</option>
			</select>
			<input type="text" name="search_input" placeholder="Search..">
			<button id="search_btn">검색</button>
		</div> -->
	</body>
</html>