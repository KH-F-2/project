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
        	
        	
        	var slideIndex = 1;
        	showSlides(slideIndex);

        	// Next/previous controls
        	function plusSlides(n) {
        	  showSlides(slideIndex += n);
        	}

        	// Thumbnail image controls
        	function currentSlide(n) {
        	  showSlides(slideIndex = n);
        	}

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
        }
        </script>
        <style>
        	a{text-decoration: none;}
        	table{
        		margin: 0 auto;
        		width: 80%;
        	}
        	div{
        		height: 100%;
        		margin: 0px 20px 0px 20px;
        	}
        	.main_table{
        		height: 600px;
        		border: 0.5px solid #2aa1ff;
        		border-radius: 5px;
        		background-color: #e8e8e880;
        	}
        	.title{
        		font-weight: bold;
        		font-size: 18pt;
        		text-align: left;
        	}
        	.date{
        		text-align: right;
        		color: gray;
        		font-size: 10pt;
        	}
        	.price{
        		color: #008effad;
        	}
        	tr:last-child{
        		height: 60%;
			}
			.second_table tr{
				text-align: right;
			}
			
			
			* {box-sizing:border-box}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}

/* Hide the images by default */
.mySlides {
    display: none;
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
  border-radius: 0 3px 3px 0;
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
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
  background-color: #717171;
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
			<table class="main_table">
				<tr>
					<td width="75%"><div class="title">${sellboard.SB_TITLE}</div></td>
					<td width="25%">
						<div class="date">
							글번호 ${sellboard.SB_NO}<br>
							등록 ${sellboard.SB_DATE}<br>
							수정 ${sellboard.SB_MDATE }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="writer">${sellboard.SB_WRITER}</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pdate">
							구입일 ${sellboard.SB_PDATE }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="price">${sellboard.SB_PRICE } 원</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="image">
							<c:forEach var="image" items="${imagelist}" varStatus="i">
								<div class="mySlides fade">
									<div class="numbertext">${i.index+1} / ${imagelist.size()}</div>
									<img src="${image.IMAGE_URL}" style="width:100%">
									<!-- https://www.w3schools.com/howto/howto_js_slideshow.asp -->
								</div>
								<c:if test="${i.index+1==imagelist.size()}">
								<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
								<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
</div>
								</c:if>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><div class="content">${sellboard.SB_CONTENT }</div></td>
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