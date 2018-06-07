<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
<html>
<head>
	<title>뷰 페이지</title>
	
	<script>
		$(document).ready(function () { 
			
			$('#modifyBtn').click(function () {			// 수정버튼
				location.href="./pbmodify.pb?PB_NO=${boardBean.PB_NO }";
			});
			
			$('#deleteBtn').click(function () {			// 삭제버튼
				location.href="./pbdeleteAction.pb?CMT_SUBJECT_NO=${boardBean.PB_NO }";
			});
			$('#next').click(function(){				//다음페이지
				location.href = "./pbview.pb?CMT_SUBJECT_NO=${boardBean.PB_NO }&page=" + ${page + 1};
				//다음페이지 이동
			});
			$('#back').click(function(){				//이전페이지
				location.href = "./pbview.pb?CMT_SUBJECT_NO=${boardBean.PB_NO }&page=" + ${page - 1};	
				//이전페이지 이동
			});
			
			$('.cmtDeleteBtn').click(function () {			// 댓글삭제 버튼
				location.href="./CommentDeleteAction.cmt?url=./pbview.pb?CMT_SUBJECT_NO=&CMT_SUBJECT_NO=${boardBean.PB_NO }&CMT_BOARD_NAME=purchaseboard&CMT_NO=" + $(this).val();
			});
			
			$('#cmtInsertBtn').one("click" , function(){			// 댓글등록버튼
				if($("#comment_content").val() == "") {
					alert("글을 쓰시오");
					return false;
				}else{		
					$.ajax({
						type : "post",
						dataType : "json",
						data : {
								CMT_CONTENT : $("#comment_content").val(),
								CMT_SUBJECT_NO : $("PB_NO").val()
								},
								url : "./cmtwriteAction.cmt?CMT_SUBJECT_NO=${boardBean.PB_NO }&CMT_BOARD_NAME=purchaseboard",
						success : function(data){
							if (data == 1) {
								if(${endPage}==0){
									location.href='./pbview.pb?CMT_SUBJECT_NO=${boardBean.PB_NO }&page=1';  //등록시 마지막 페이지가 0일경우 1로 고정
								} else {	
									location.href='./pbview.pb?CMT_SUBJECT_NO=${boardBean.PB_NO }&page=${endPage}';  //등록시 마지막 페이지로
								}
								
							} else {
								alert("실패" + data);
							}
							
						},
						error : function(data){
							alert("에러다" + data);
						}
					});
				}
				
			});

			$('.recmtInsertBtn').click( function(){  //다른 답글 버튼 클릭시 이전에 펼쳐진 댓글 입력창 사라지게 하는거
				$('.answer').empty();
			});
			
			$('.recmtInsertBtn').click( function(){  //답글 클릭시 답글 입력창 띄워주는 거
			
				var a = $(this).val();
				
				var reout = "";
				reout += '<li id="rerepage">';
				reout += '<div>';
				reout += '<span><%=session.getAttribute("id")%></span>';
				reout += '<span><input type="text" id="rere_Comment"></span>';
				reout += '<span> <button id="rereBtn'+a+'"';
				reout += ' class="rereBtn" value='+a+'>등록</button> </apsn>';
				reout += '</div>';
				reout += '</li>';
				
				$('#answer'+ a).empty();
				$('#answer'+ a).append(reout);
					
			});
			
			$(document).on('click', '.rereBtn', function () {			// 대댓글 등록 버튼
				
				if($("#rere_Comment").val() == "") {
					alert("글을 쓰시오");
					return false;
				}else{		
					$.ajax({
						type : "post",
						dataType : "json",
						data : {
								CMT_CONTENT : $("#rere_Comment").val(),
								CMT_SUBJECT_NO : $("PB_NO").val()
								},
						url : "./CommentReplyAction.cmt?CMT_BOARD_NAME=purchaseboard&CMT_SUBJECT_NO=${boardBean.PB_NO }&CMT_NO="+$(this).val(),
						success : function(data){
							if (data == 1) {
								location.href='./pbview.pb?CMT_SUBJECT_NO=${boardBean.PB_NO }&page=${page}'; //등록시 원문글 페이지로
							} else {
								alert("실패" + data);
							}
							
						},
						error : function(data){
							alert("대댓글에러다" + data);
						}
					});
				}
			});
			
			
			$('#listBtn').click(function () {			// 목록버튼
				location.href="./pbmain.pb";
			});
		
		});
		 
	</script>
	
	<style>
		table{
			width: 600px;
			text-align: center;
			margin: 0 auto;
		}
		td{
			border:1px solid black;
		}
		li{
			border:1px solid black;
			width: 600px;
			height: 30px;
		}
		.reBtn{
			float:right;
		}
		ul{
   			list-style:none;
  		}
  		#bottompage{		
  			text-align:center;
  			margin:0 auto;
  		}
  		#viewtable{
  			margin:0 auto;
  		}
  		#cmtInsertBtn{
  			float:right;
  		}
  		#replytable{ /* 리플 등록 줄 */
  	
  			margin:0 auto;
  			padding : 0;
  		}
  		#insertreplypage{ /* 리플 등록 줄 */
  			margin: 0 auto;
  			
  		}
  		#replypage{	/* 리플 보여주기 줄*/
  			margin: 0 auto;
  		}
  		#rerepage{ 	/* 답글 입력 줄*/
  			margin: 0 auto;
  		}
  		

		/* 이미지 CSS */
/* Slideshow container */
.slideshow-container {
   max-width: 500px;
   width: 500px;
   max-height: 500px;
   height: 500px;
   position: relative;
   margin: auto;
   background-color: black;
}
/* Hide the images by default */
.mySlides {
   width: 500px;
   height: 100%;
    display: none;
    overflow: hidden;
    position: relative;
}
.img_slide{
   position: absolute;
   width: 100%;
   top: 0;
   bottom: 0;
   margin: auto;
}
.zoomContainer{
   left: 115.5px; top: 223px; height: 500px; overflow: hidden;
}
/* Next & previous buttons */
.prev, .next {
   cursor: pointer;
   position: absolute;
   top: 50%;
   width: auto;
   margin-top: -22px;
   padding: 16px;
   color: white;
   font-weight: bold;
   font-size: 18px;
   transition: 0.6s ease;
   background-color: rgba(26,171,138,0.6);
}
.dot_div{
   position: absolute;
   top: 96%;
   left: 0;
   right: 0;
   margin: auto;
}
/* Position the "next button" to the right */
.prev {
  left: -45px;
  border-radius: 5px 0 0 5px;
}
.next {
  right: -45px;
  border-radius: 0 5px 5px 0;
}
/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}
/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}
/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}
/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}
.active, .dot:hover {
  background-color: #4f4aa8;
}
/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}
@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}
@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}
   	
	</style>
</head>

<body>

	<input type="hidden" name="PB_NO" value="${boardBean.PB_NO }">
	<table id="viewtable">
		<tr>
			<th colspan="3">뷰페이지</th>
		</tr>
		<tr>
			<td>
				<div>작성자</div>
			</td>
			<td colspan="2">
				<div>${boardBean.PB_WRITER }</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>제목</div>
			</td>
			<td colspan="2">
				<div>${boardBean.PB_TITLE}</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td colspan="2">
				<div>${boardBean.PB_CONTENT }</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>가격</div>
			</td>
			<td colspan="2"> ${boardBean.PB_PRICE }</td>
		</tr>
		<tr>
			<td>첨부사진</td>
			<td>
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
			</td>
		</tr>
		<tr>
			<td>
				<div>헤쉬태그</div>
			</td>
			<td colspan="2"> ${boardBean.PB_HASHTAG}</td>
		</tr>
		<tr>
			<td>
				<div>카테고리</div>
			</td>
			<td>
				<c:choose>
				<c:when test="${boardBean.PB_CATEGORY==0 }">
				의류/패션잡화
				</c:when>
				<c:when test="${boardBean.PB_CATEGORY==1 }">
				가구/생활잡화
				</c:when>
				<c:when test="${boardBean.PB_CATEGORY==2 }">
				전자기기/게임
				</c:when>
				<c:when test="${boardBean.PB_CATEGORY==3 }">
				문화/도서/티켓
				</c:when>
				<c:when test="${boardBean.PB_CATEGORY==4 }">
				차량용품/오토바이
				</c:when>
				<c:when test="${boardBean.PB_CATEGORY==5 }">
				취미/레저/스포츠
				</c:when>
				<c:when test="${boardBean.PB_CATEGORY==6 }">
				기타
				</c:when>
				
				
				</c:choose>
			</td>	
		</tr>
		<tr>
			<td colspan="3" class="center">
				<c:if test="${boardBean.PB_WRITER == sessionScope.id || sessionScope.id.equals('admin') }">
					<button id="modifyBtn">수정</button> &nbsp; &nbsp;
					<button id="deleteBtn">삭제</button> &nbsp; &nbsp;
				</c:if>
				<button id="listBtn">목록</button> &nbsp; &nbsp;
			</td>
		</tr>

	</table>
	
	<br><hr><br>
		<!-- --------------------------------------지옥의 댓글 시작------------------------------------------- -->

	<ul id="replytable">
		<li id="insertreplypage">
			<span><%=session.getAttribute("id")%></span>
			<span><input type='text' name="comment_content" id="comment_content">
			<button id=cmtInsertBtn>댓글등록</button></span>
		</li>
		<c:if test="${requestScope.cmtList != null}">
			<c:forEach var="comment" items="${requestScope.cmtList}">
				<!-- 댓글목록 출력반복문 -->
		
				<li id="replypage">
				<div>
					<span id="cmt_writer">${comment.CMT_WRITER }</span>

						<span>
							<c:if test="${comment.CMT_RE_LEV !=0 }">
								<c:forEach var = "a" begin="1" end="${comment.CMT_RE_LEV }" step="1">
									R  <!-- 댓글 깊이 만큼 r 찍기 -->
								</c:forEach>
							</c:if>
							
							${comment.CMT_CONTENT} <font size="2">${comment.CMT_DATE}</font>
							
						</span>
			
					<c:if test="${comment.CMT_WRITER eq sessionScope.id || sessionScope.id.equals('admin')}"	>						
						<span class="reBtn"><button id="recmtInsertBtn" class="recmtInsertBtn" value="${comment.CMT_NO }">답글</button></span>
						<span class="reBtn"><button class="cmtDeleteBtn" value="${comment.CMT_NO }">삭제</button></span>
					</c:if>
					</div>
				</li>
				<span class="answer" id ="answer${comment.CMT_NO}"></span> <!-- 대댓글 칸 -->
			</c:forEach>
		</c:if>
		
		<div id="reply"></div>
		
		<!-- 새로 입력된 댓글 ajax로 출현 -->

		<c:if test="${cmtList != null }">
		<li id="bottompage">
			<div>
				<c:if test="${page <= 1 }">
					이전&nbsp;
				</c:if>
			
				<c:if test="${page>1 }">
					<a id="back">이전</a>
				</c:if>
				
				<c:forEach var="a" begin="${startPage }" end="${endPage }">
					<c:if test="${a == page }">
						${a }
					</c:if>

					<c:if test="${a != page }">
						<a href="./pbview.pb?CMT_SUBJECT_NO=${boardBean.num }&page=${a }">${a }</a>
					</c:if>
				</c:forEach>
			
				<c:if test="${page >= endPage }">
					&nbsp; 다음
				</c:if>
				
				<c:if test="${page < endPage }">
					<a id="next">다음</a>
				</c:if>
			</div>
		</li>
		</c:if>
	
	</ul>

</body>
</html>