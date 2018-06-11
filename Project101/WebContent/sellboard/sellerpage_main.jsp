<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>판매게시판</title>
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<!-- Semantic UI Library -->
	<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="semantic/semantic.min.js"></script>
	<link href="css/seller_main.css" rel="stylesheet" type="text/css">
	
	<script>
		$(document).on("click","#item1",(function(){
			var writer = $('#writer').val();
			
			$.ajax({
				type:"POST",
				url : "sellerpage_main_ajax.me?writer="+writer,
				data : {"writer" : writer},
				success : function(data) {
					
					
					$("#abc").empty().append(data);
				}
			})
			
			return false;
		}))
		
		$(document).on("click","#item2",(function(){
			var writer = $('#writer').val();
			$.ajax({
				type:"POST",
				url : "sellerpage_main2_ajax.me?writer="+writer,
				data : {"writer" : writer},
				success : function(data) {
					
					
					$("#abc").empty().append(data);
				}
			})			
		}))		
$(document).ready(function() {
	$("#item3").click(function() {
		var writer = $('#writer').val();
		$.ajax({
			type : "POST",
			url : "signepilcontent.me?writer="+writer,
			data : {"state" : "ajax"},
			success : function(data) {
				$("#abc").empty().html(data);									
			}			
		})
		return false;
		
	});
});
	
	</script>
</head>

<body>

	<div class="ui left vertical menu sidebar">
		<h1 id="navTitle"><i>&#xe801;</i>  우리지금만나</h1>
		<h2>Category</h2>
		<a class="item" href="pbmain.pb">구매게시판</a>
		<a class="item" href="sbmain.sb">판매게시판</a>
		<a class="item" href="ebmain.eb">후기게시판</a>
		<a class="item" href="rbmain.rb">신고게시판</a>
	</div>
	
		<div id="header">
			<jsp:include page='header.jsp' />
		</div>
		
		<div class="main_top">
		<span>"${writer}"회원님의 등록된 판매 게시글</span><br>
		<input type="hidden" id="writer" value="${writer }">
		</div>
	
	<div class="sell_map">
		<h2>지도넣을위치 class="sell_map"</h2>	
    </div>
		<div class="tab st02">
			<a class = "button " id="item1" href = "#">앨범형</a>
			<a class = "button " id="item2" href = "#">게시판형</a>
			<a class = "button " id="item3" href = "#">후기보기</a>
		</div>
	<ul id="abc" class="seller_ul">	
	 <c:if test="${listcount != 0 }">
			<c:forEach var="boardBean" items="${getimage }">
				<li id="content_box" class="seller_li 1">
					<div class="content" class="seller_li 2">
						<img src="${boardBean.IMAGE_URL}">
						<p>&nbsp;<a href="./sbview.sb?num=${boardBean.SB_NO }">
								${boardBean.SB_TITLE}</a></p>
						<span>${boardBean.SB_CONTENT}</span>
					</div>
				</li>	
			</c:forEach>
	  </c:if>
	
	</ul>
</body>

</html>