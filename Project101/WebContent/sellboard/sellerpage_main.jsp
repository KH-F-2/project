<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>판매게시판</title>
	
	<link rel="stylesheet" type="text/css" href="css/main.css">

	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<link href="css/seller_main.css" rel="stylesheet" type="text/css">
	
	<script>
		//판매자 페이지로서 ajax를 활용하여 게시글이 보여지는 형태는 2가지이다. 
		$(document).on("click","#item1",(function(){	//앨범형을 클릭했을 시 
			var writer = $('#writer').val();
			$.ajax({
				type:"POST",  //type은 post 지정
				url : "sellerpage_main_ajax.me?writer="+writer,	//대상 url -> 작성자(writer)를 클릭했을 때 그 작성자를 가지고 넘어간다. 
				data : {"writer" : writer},	//요청 매개변수 지정
				success : function(data) {	//success 성공 이벤트 핸들러 
					$("#abc").empty().append(data);	//success를 하게 되면 그전 데이터는 비워주고 게시글 형태에 맞게 다시 데이터를 넣는다.
				}									 
			})
			return false;
		}))
		
		$(document).on("click","#item2",(function(){	//게시판형을 클릭했을 시
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