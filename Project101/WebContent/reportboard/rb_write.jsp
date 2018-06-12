<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>신고게시판 글쓰기</title>
		

        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
        <link href="css/boardwrite.css" rel="stylesheet" type="text/css">
		<script src="js/rbwrite.js"></script>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>	
		<div class="header"><h1>게시판 글쓰기</h1></div>
		<form action="./rbwriteaction.rb" method="post" id="write_submit">
			<input type="hidden" value="${sessionScope.id }" name="RB_WRITER">
			<ul class="write_ul">
				
				<li class="write_li">
					<div class="title">
						<img src="image/document.png" alt="document" class="write_img">
						<input name="RB_TITLE" type="text" size="50" maxlength="100" placeholder="제목을 입력하세요">
					</div>
				</li>
				
				<li class="write_li">
					
					<div class="price">
						<img src="image/money.png" alt="money" class="write_img">
						<input name = "RB_PRICE" type="text" size="50" maxlength="50" placeholder="가격을 입력하세요">
					</div>
				</li>
				
				<li class="write_li">
					<div class="content">
						<textarea name="RB_CONTENT" cols="65" rows="15"></textarea>
					</div>
				</li>
				
			
				
				<li class="btn_li">
					<div>
						<button type="submit" id="submit_btn" class="write_btn">등록</button>
						<button type="reset" id="cancle_btn" class="write_btn" onclick='history.go(-1)'>취소</button>
					</div>
				</li>
				
			</ul>
			
		</form>	
	</body>
</html>