
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
	        });
        </script>
        <script>
        	$(document).ready(function() {
        	$(".div_writer").hide();
        		$(".a_writer").click(function () {
        			var id = $(this).attr('id');
        			var top = $(this).offset().top + 25;
        			var left = $(this).offset().left;
        			$("#div_writer"+id).toggle(); 
        			$("#div_writer"+id).css({
        				'top': top,
        				'left': left
        			})
        		})
        	})
        </script>
		<link href="css/sblist.css" rel="stylesheet" type="text/css">
		<style>
			.map{width: 100%; background-color: silver; height: 300px}
		/* 작성자 클릭했을때 나오는 정보보기,쪽지보내기 */		
		.div_writer {overflow:hidden;clear:both;border:1px solid #b6b6b6;background-color:#fff;text-align:left}
		.div_writer ul {overflow:hidden;width:100%;margin:0}
		.div_writer li {width:100%;height:25px;margin:0;padding:0;vertical-align:top}
		.div_writer li.line {border-top:1px solid #ebebeb}
		.div_writer li a {display:block;width:100%;height:25px;background-color:#fff;color:#4c4c4c}
		.div_writer li span {display:block;padding:7px 0 0 8px;cursor:pointer}
		.div_writer li a:hover {background-color:#f4f4f4;text-decoration:none}
		.div_writer {clear:both;border:1px solid #888;background-color:#FFF;overflow:hidden}
		.div_writer ul {width:100%;margin:0;padding:0;overflow:hidden}
		.div_writer li {width:100%;height:25px;margin:0;padding:0;vertical-align:top}
		.div_writer li a {display:block;width:100%;height:25px;color:#4c4c4c;background-color:#fff}
		.div_writer li a:hover {background-color:#f4f4f4 !important;text-decoration:none}
		</style>
	</head>
	<body>
		<%-- <c:set var="b_p" value="${boardPageBean }"/>
		<h1>판매 게시판</h1>
		<div class="map">지도~</div>
		
		<div class="write"><button type="button">글쓰기</button></div>
		<c:set var="num" value="${b_p.listcount-(b_p.page-1)*b_p.limit }"/>
		
		<c:forEach var="boardBean" items="${b_p.boardBeanList }">
			<div class="content">
				
			</div>
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
		</c:forEach> --%>
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
							<td width="14%">
							<a href="#" class="a_writer" id="${boardBean.SB_NO}">
							${boardBean.SB_WRITER }</a>
							</td>							
							
							<td width="17%">${boardBean.SB_DATE }</td>
							<td width="11%">${boardBean.SB_READCOUNT }</td>
						</tr>
		<div id="div_writer${boardBean.SB_NO}" class="div_writer" style="display: block;position: absolute; width: 109px; z-index: 1000; top: 845px; left: 559px;">
			<ul>
				<li>
					<a href="#"><span>정보보기</span></a>
				</li>					
				<hr>			
				<li>
					<a href="./msmessagewrite.ms?num=${boardBean.SB_NO}
												&writer=${boardBean.SB_WRITER }">
					<span>쪽지보내기</span>
					</a>
				</li>
			</ul>
		</div>	
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
			
	</body>
</html>
