<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- Icon link -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!-- 지도 API -->
        <script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
		
        <script type="text/javascript">
			var id='${sessionScope.id}';
			var sb_lat = ${boardBean.SB_LAT};
			var sb_lng = ${boardBean.SB_LNG};
				function deleteConfirm(){
				   	ans=confirm("삭제하시겠습니까?");
				   	if(ans){
				   		location.href="<c:url value='/sbdelete.sb?num=${boardBean.SB_NO}'/>";
				  	return;
				   	}
					alert("삭제가 취소됨!!");
				}
        </script>
        
        <script src="js/sbview.js"></script>
        <link href="css/sbview.css" rel="stylesheet" type="text/css">
	</head>
		<body>
			<ul class="sbview_ul">
			
				<li class="sbview_li 1">
					<div class="title">
						${boardBean.SB_TITLE}
					</div>
					<div class="date">
						글번호 ${boardBean.SB_NO}<input type="hidden" id="SB_NO" value="${boardBean.SB_NO}"><br>
						등록 ${boardBean.SB_DATE}
					</div>
				</li>
				
				<li class="sbview_li 2">
					<div class="category">
						[${boardBean.SB_CATEGORY}]
					</div>
					<div class="hashtag">
						${boardBean.SB_HASHTAG}
					</div>
					
					<div class="writer">
						<%-- <a href="./sellerpage_main.me?writer=${boardBean.SB_WRITER}">${boardBean.SB_WRITER}</a> --%>
						<a href="#" class="a_writer" id="${boardBean.SB_NO}">${boardBean.SB_WRITER}</a>
						<input type="hidden" id="SB_WRITER" value="${boardBean.SB_WRITER}">
					</div>
					
					<div id="div_writer${boardBean.SB_NO}" class="div_writer">
						<ul>
							<li>
								<a href="./sellerpage_main.me?writer=${boardBean.SB_WRITER}"><span>정보보기</span></a>
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
					
					<div class="price">${boardBean.SB_PRICE } 원</div>
					
					
				</li>
				
				<li class="sbview_li 3">
					<div id="map"></div>
				</li>
			
				<li class="sbview_li_last 4">
					<div class="image">
						<c:if test="${imageBeanList.size()>0}">
							<div class="slideshow-container">
								<c:forEach var="image" items="${imageBeanList}" varStatus="i">
									<div class="mySlides fade">
										<div class="numbertext">${i.index+1} / ${imageBeanList.size()}</div>
										<img src="${image.IMAGE_URL}" class="img_slide">
									</div>
								</c:forEach>
								<a class="prev" onclick="plusSlides(-1)" style="left:-45px;">&#10094;</a>
								<a class="next" onclick="plusSlides(1)">&#10095;</a>
								
								<div style="text-align:center" class="dot_div">
									<c:forEach var="image" items="${imageBeanList}" varStatus="i">
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
					</div>
					
					<div class="content">
						${boardBean.SB_CONTENT }
					</div>
				</li>
				<li class="btn_li">
					<button type="button" id="trade">구매 신청</button>
				</li>
				
			</ul>
			
			
			
			
			
			<div class="menu_bar">
				<ul>
					<li>
						<c:if test="${id=='admin'||id==boardBean.SB_WRITER}">
					    	<a href="<c:url value='/sbmodifyview.sb?num=${boardBean.SB_NO}'/>">
								<img src="image/update.png">
							</a> &nbsp;
							<a href="javascript:deleteConfirm()">
								<img src="image/delete.png">
							</a> &nbsp;
						</c:if>
						<a href="<c:url value='/sbmain.sb'/>">
							<img src="image/list.png">
						</a>
					</li>
				</ul>
			</div>
			
			<div class="comment_div">
				<c:set var="b_p" value="${boardPageBean }"/>
				<ul>
					<li>
						<div class="comment_textarea">
							<b>댓글 (<span id="comment_count">${b_p.listcount}</span>)</b><br>
							<textarea rows="10" cols="70" style="resize: none;" id="comment_content" onkeyup="chkword(this,300)" placeholder="내용은 300자까지 입력가능합니다."></textarea>
							<span id="counter">0/300</span>
							<button id="comment_btn">작성</button>
						</div>
					</li>
				</ul>
			</div>
			
			<div class="commentlist">
				<ul class="comment_view">
					<c:if test="${b_p.listcount>0}">
						<c:forEach var="comment" items="${b_p.commentBeanList}" varStatus="i">
							<li id="${comment.CMT_WRITER}" class="comment_li" 
								style="padding-left:${comment.CMT_RE_LEV*3}%">
								<div class="comment_top">
									<span class="comment_writer">${comment.CMT_WRITER}</span>
									<span class="comment_date">${comment.CMT_DATE}</span>
								</div>
								<div class="comment_bottom">
									<div class="comment_text">${comment.CMT_CONTENT}</div>
									<div class="comment_btn2" id="${comment.CMT_NO}">
										<a href="#" id="comment_reply" 
											onclick='replyView(${comment.CMT_NO}); return false;' 
											class="btn btn-sm animated-button victoria-one">답글</a>
										<a href="#" id="comment_delete" 
											onclick='commentDelete(${comment.CMT_NO}); return false;'  
											class="btn btn-sm animated-button victoria-one">삭제</a>
									</div>
								</div>
							</li>
							<li class="reply r${comment.CMT_NO}"></li>
						</c:forEach>
					
						<li>
							<div class="comment_paging">
								<c:if test="${b_p.page<=1 }">
									이전&nbsp;
								</c:if>
								<c:if test="${b_p.page>1 }">
									<a href="./sbview.sb?page=${b_p.page-1}&num=${boardBean.SB_NO}">이전</a>&nbsp;
								
								</c:if>
								<c:forEach var="a" begin="${b_p.startpage }" end="${b_p.endpage }">
									<c:if test="${a==b_p.page }">
										${a }
									</c:if>
									<c:if test="${a!=b_p.page }">
										<a href="./sbview.sb?page=${a }&num=${boardBean.SB_NO}">${a }</a>
									</c:if>
								
								</c:forEach>
								<c:if test="${b_p.page>=b_p.maxpage }">
									&nbsp;다음
								</c:if>
								<c:if test="${b_p.page<b_p.maxpage }">
									&nbsp;<a href="./sbview.sb?page=${b_p.page+1 }&num=${boardBean.SB_NO}">다음</a>
								</c:if>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
			
	</body>
</html>