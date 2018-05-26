<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
        <script type="text/javascript">
			function deleteConfirm(){
			   	ans=confirm("삭제하시겠습니까?");
			   	if(ans){
			   		location.href="<c:url value='/BoardDelete.sell?num=${sellboard.SB_NO}'/>";
			  	return;
			   	}
				alert("삭제가 취소됨!!");
			}
        </script>
        
        <link href="/test/sellboard/css/board_view.css" rel="stylesheet" type="text/css">
	</head>
		<body>
			<table class="main_table">
				<tr>
					<td width="75%">
						<div class="title">${sellboard.SB_TITLE}</div>
					</td>
					<td width="25%">
						<div class="date">
							글번호 ${sellboard.SB_NO}<br>
							등록 ${sellboard.SB_DATE}<br>
							수정 ${sellboard.SB_MDATE }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><div class="titlebar"></div></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="writer">
							<div class=writer_left>
								${sellboard.SB_WRITER}
							</div>
							<div class=writer_right>
								<button class="info">Info</button>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="price">${sellboard.SB_PRICE } 원</div>
						<div class="pdate">
							구입일 ${sellboard.SB_PDATE }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${imagelist.size()>0}">
							<div class="slideshow-container">
								<c:forEach var="image" items="${imagelist}" varStatus="i">
									<div class="mySlides fade">
										<div class="numbertext">${i.index+1} / ${imagelist.size()}</div>
										<img src="${image.IMAGE_URL}" style="width:100%">
									</div>
								</c:forEach>
								<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
								<a class="next" onclick="plusSlides(1)">&#10095;</a>
								
								<div style="text-align:center">
									<c:forEach var="image" items="${imagelist}" varStatus="i">
										<span class="dot" onclick="currentSlide(${i.index+1})"></span> 
									</c:forEach>
								</div>
							</div>
							<script>
								function plusSlides(n) {
						     		showSlides(slideIndex += n);
						     	}
	
						     	// Thumbnail image controls
						     	function currentSlide(n) {
						     		showSlides(slideIndex = n);
						     	}
						     	var slideIndex = 1;
						     	showSlides(slideIndex);
						     	function showSlides(n) {
						 			var i;
						 			var slides = document.getElementsByClassName("mySlides");
						 			var dots = document.getElementsByClassName("dot");
						 			if (n > slides.length) {slideIndex = 1} 
						 			if (n < 1) {slideIndex = slides.length}
						 			for (i = 0; i < slides.length; i++) {
						 			    slides[i].style.display = "none"; 
						 			}
						 			for (i = 0; i < dots.length; i++) {
						 			    dots[i].className = dots[i].className.replace(" active", "");
						 			}
						 			slides[slideIndex-1].style.display = "block";
						 			dots[slideIndex-1].className += " active";
						     	}
							</script>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="content">
							${sellboard.SB_CONTENT }
						</div>
					</td>
				</tr>
			</table>
			<table class="second_table">
				<tr>
					<td>
					    <c:if test="${id=='admin'||id==sellboard.SB_WRITER}">
					    	<a href="<c:url value='/BoardModifyView.sell?num=${sellboard.SB_NO}'/>">
								<img src="/test/sellboard/image/update.png">
							</a> &nbsp;
							<a href="javascript:deleteConfirm()">
								<img src="/test/sellboard/image/delete.png">
							</a> &nbsp;
						</c:if>
						<a href="<c:url value='/BoardList.sell'/>">
							<img src="/test/sellboard/image/list.png">
						</a>
					</td>
				</tr>
			</table>
	</body>
</html>