<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<!-- 지도 API -->
<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
<style>
	#map{
	   width: 80%; 
	   height: 400px; 
	   margin: 0 auto; 
	   margin-top: 20px;
	}
	.write{
	   width: 80%;
	   margin: 0 auto;
	   margin-top: 20px;
	   text-align: right;
	   height: 35px;
	}
	.content_section{
	   width: 80%;
	   margin: 0 auto;
	}
	.card{
	   position: relative;
	   width: 200px;
	   height: 300px;
	   margin: 20px 15px 0px 15px;
	   display: inline-block;
	   border: 1px solid #cdd2d2;
	   border-radius: 10px;
	   transition: box-shadow .3s;
	   overflow: hidden;
	}
	.card:hover{
	   box-shadow: 0 0 13px rgba(33,33,33,0.2); 
	   bottom: 2px;
	}
	.card *{
	   width: 100%;
	}
	.card_img{
	   height: 200px;
	   overflow: hidden;
	}
	.content_img{
	   min-width: 100%;
	   min-height: 100%;
	}
	.card_content{
	   height: 70px;
	   padding: 10px 5px 5px 10px;
	   border-bottom: 1px solid #cdd2d2;
	}
	.content_title{
	   display: inline-block;
	   width: 80%;
	   height: 60px;
	}
	.content_board{
	   display: inline-block;
	   width: 19%;
	   position: absolute;
	   height: 60px;
	   text-align: right;
	}
	.content_span_title{
	   font-weight: bold;
	   font-size: 15pt;
	}
	
	.content_span_board{
	   margin-right: 3px;
	   color: #4183C4;
	}
	.content_span_price{
	   position: absolute;
	   bottom: 30px;
	   font-weight: bold;
	}
	.card_bottom{
	   height: 30px;
	   text-align: center;
	}
	.bottom_span{
	   font-size: 0.82em;
	   line-height: 2.3;
	   color: #101010;
	}
	.list_title{
	   margin: 0 auto;
	   width: 20%;
	   padding: 10px;
	   border-top: 3px solid #526bbe; 
	   border-bottom: 3px solid #526bbe;
	   text-align: center;
	}
	.card_a{
	   text-decoration: none;
	   color: black !important;
	}
	.card_a:hover{
	   text-shadow: 0 0 3px rgba(0,84,255,0.6);
	}
	
	.write_btn {
	   background-color: #c47135;
	   border: none;
	   color: #ffffff;
	   cursor: pointer;
	   display: inline-block;
	   line-height: 1em;
	   margin: 0 auto;
	   font-size: 9pt;
	   outline: none;
	   padding: 8px 15px;
	   letter-spacing: 2px;
	   position: relative;
	   text-transform: uppercase;
	   font-weight: 700;
	}
	.write_btn:before, .write_btn:after {
	   border-color: transparent;
	   -webkit-transition: all 0.25s;
	   transition: all 0.25s;
	   border-style: solid;
	   border-width: 0;
	   content: "";
	   height: 24px;
	   position: absolute;
	   width: 24px;
	}
	.write_btn:before {
	   border-color: #c47135;
	   border-right-width: 2px;
	   border-top-width: 2px;
	   right: -5px;
	   top: -5px;
	}
	.write_btn:after {
	   border-bottom-width: 2px;
	   border-color: #c47135;
	   border-left-width: 2px;
	   bottom: -5px;
	   left: -5px;
	}
	.write_btn:hover, .write_btn.hover {
	   background-color: #c47135;
	}
	.write_btn:hover:before, .write_btn.hover:before,
	.write_btn:hover:after, .write_btn.hover:after {
	   height: 100%;
	   width: 100%;
	}
</style>
<title>검색 결과 페이지</title>
</head>
<body>
	<div class="list_title">
         <h1>게시글 리스트</h1>
      </div>
      
      <div id="map">지도~</div>
      
      <div class="write">
         <button type="button" class="write_btn" onclick="">글쓰기</button>
      </div>
      <section class="content_section">
         <c:forEach var="list" items="${requestScope.categoryList}">
            <div class="card">
            <!-- 사진 링크 이동 if문 -->
            	<c:if test="${list.board_name eq 'SELL_BOARD'}">
               		<a class="card_a" href="./sbview.sb?num=${list.num }">
               	</c:if>
               	<!-- 판매글 이동 주소값 설정 -->
               	<c:if test="${list.board_name eq 'PURCHASE_BOARD'}">
               		<a class="card_a" href="./pbview.pb?CMT_SUBJECT_NO=${list.num }">
               	</c:if>
               	
                  <div class="card_img">
                     <img class="content_img" src="${list.image_url}">
                  </div>
                  <div class="card_content">
                     <div class="content_title">
                        <span class="content_span_title">${list.title}</span>
                        <br>
                        <span class="content_span_price">${list.price}원</span>
                     </div>
                     <div class="content_board">
                        <span class="content_span_board">
                           <c:if test="${list.board_name eq 'SELL_BOARD'}">판매</c:if>
                           <c:if test="${list.board_name eq 'PURCHASE_BOARD'}">구매</c:if>
                        </span>
                     </div>
                  </div>
                  <div class="card_bottom">
                     <span class="bottom_span">
                        ${list.date} · 조회수 ${list.readcount}
                     </span>
                  </div>
               </a>
            </div>
         </c:forEach>
      </section>
</body>
</html>