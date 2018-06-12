<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="2">
<link rel="stylesheet" href="../css/bootstrap.css">
<title>JSP 게시판 웹사이트</title>

<style>
nav > li >span {
    color: #777;
}

.navbar-nav > li > span {
    padding-top: 10px;
    padding-bottom: 10px;
    line-height: 20px;
}
.navbar-nav {
	width : 100%
}
.navbar-nav > li {
	width : 20%;
	text-align : center
}
.nav > li > span {
    position: relative;
    display: block;
    padding: 10px 15px;
}
span {
    color: #337ab7;
    text-decoration: none;
}
span {
    background-color: transparent;
}


span:-webkit-any-link {
    color: -webkit-link;
    cursor: pointer;
    text-decoration: underline;
}
</style>
<script>
			function search(e) {
		
				var notice_category = e;
			
				$.ajax({
					type : "POST",
					url : "noticeCategory.notice?notice_category=" + notice_category,
					data : {
						"notice_category" : notice_category
					},
					success : function(data) {
		
						$("#abc").empty().append(data);
					}
				})
				
			}
		</script>
</head>
<body>

  	<nav class="navbar navbar-default">
  		
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div> 
		
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><"WebContent/css/seller_main.css"span onclick="search(1)">이용제재</span></li>
				<li><span onclick="search(2)">계정/인증</span></li>
				<li><span onclick="search(3)">구매/판매</span></li>
				<li><span onclick="search(4)">거래품목</span></li>
				<li><span onclick="search(5)">거래매너</span></li>
			</ul>
			
		</div>
	</nav>
	
	
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="../js/bootstrap.js"></script>
		<div id="abc"></div>
</body>
</html>
