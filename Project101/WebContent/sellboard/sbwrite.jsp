<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>

		<!-- 달력 API -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- 이미지 업로드 API -->
        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>

        <script src="js/sbwrite.js"></script>
        <link href="css/sbwrite.css" rel="stylesheet" type="text/css">

		<style>
			.sbwrite_ul{
	margin: 0 auto;
	width: 70%;
	font-family: "fontello";
	border: solid 1px #cdd2d2;
	border-radius: 10px;
	padding: 0;
}
.write_li{
	list-style: none;
	width: 100%;
	padding: 10px 30px;
	border-bottom: 1px solid #cdd2d2;
}

.sbwrite_ul li div{
	-webkit-box-sizing: border-box;
  			-moz-box-sizing: border-box;
	box-sizing: border-box;
}
input[type=text]{
	display: block;
    width: 100%;
    background: none;
    border: none;
    padding: 5px 10px;
    font-size: 14px;
    line-height: 16px;
    box-sizing: border-box;
    outline: none;
}
.header{
	margin: 0 auto;
	text-align: center;
	margin-bottom: 30px;
}
.hashtag, .category, .purchase_date, .price{
	display: inline-block;
	width: 49%;
}
select{
	height: 20px;
}
textarea{
	width: 100%;
	resize: none;
}
.btn_li{
	list-style: none;
	width: 100%;
	padding: 10px 30px;
	text-align: center;
	font-size: 17pt;
}
.write_btn{
	background:#1AAB8A;
	color:#fff;
	border:none;
	position:relative;
	height:30px;
	width: 80px;
	font-size:10pt;
	padding:0 2em;
	cursor:pointer;
	transition:800ms ease all;
	outline:none;
}
#cancle_btn{
	background: #D24D57;
}
.write_btn:hover{
  background:#fff;
  color:#1AAB8A;
}
#cancle_btn:hover{
	background: #fff;
	color: #D24D57;
}
.write_btn:before, .write_btn:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all;
}
#cancle_btn:before, #cancle_btn:after{
	background: #D24D57;
}
.write_btn:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.write_btn:hover:before, .write_btn:hover:after{
  width:100%;
  transition:800ms ease all;
}

.sbwrite_ul{
	margin: 0 auto;
	width: 85%;
	font-family: "fontello";
}
.sbwrite_ul li{
	list-style: none;
}
		</style>
		<script type="text/javascript">
			/* $(function(){
				$('#hashtag').on('input', function(){
					console.log('해시태그 입력');
					var content = $(this).val();
					var splitedArray = content.split(' ');
					var linkedContent = '';
						
					for(var word in splitedArray)
					{
						console.log(splitedArray);
						console.log(word);
						console.log(splitedArray[word]);
						
					  word = splitedArray[word];
					   if(word.indexOf('#') == 0)
					   {
					      word = '<span>' + word + '</span>';
					   }
					   linkedContent += word;
					}
				content = $(this).val(linkedContent);
				});
			}); */
		</script>
	</head>
	<body>	
		<div class="header"><h1>판매게시판 글쓰기</h1></div>
		<form action="./sbwriteaction.sb" method="post" id="write_submit">
			<ul class="sbwrite_ul">
				<li class="write_li">
					<div class="title">
						<input name="SB_TITLE" type="text" size="50" maxlength="100" placeholder="제목을 입력하세요">
					</div>
				</li>
				<li class="write_li">
					<div class="category">
						<select name="SB_CATEGORY">
							<option value="0" selected>카테고리</option>
						    <option value="1">의류/잡화</option>
						    <option value="2">뷰티</option>
						    <option value="3">식품/생활/유아동</option>
						    <option value="4">가구</option>
						    <option value="5">가전/디지털</option>
						    <option value="6">도서/쿠폰</option>
						</select>
					</div>
					<div class="hashtag">
						<input type="text" id="hashtag" name="SB_HASHTAG" size="50" placeholder="hashtags">
					</div>
				</li>
				<li class="write_li">
					<div class="purchase_date">
						<input type="text" name="SB_PURCHASE_DATE" id="datepicker" readonly="readonly" placeholder="구매일">
					</div>
					<div class="price">
						<input name = "SB_PRICE" type="text" size="50" maxlength="50" placeholder="가격을 입력하세요">
					</div>
				</li>
				<li class="write_li">
					<div class="content">
						<textarea name="SB_CONTENT" cols="65" rows="15"></textarea>
					</div>
				</li>
				<li class="write_li">
					<div class="image">
						<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
						<div id="showImage"></div>
						<input type="hidden" id="img_hidden" name="img_hidden" value="">
					</div>
				</li>
				<li class="btn_li">
					<div>
						<button id="submit_btn" class="write_btn">등록</button>
						<button id="cancle_btn" class="write_btn" onclick='history.go(-1)'>취소</button>
					</div>
				</li>
				
			</ul>
			
		</form>	
	</body>
</html>