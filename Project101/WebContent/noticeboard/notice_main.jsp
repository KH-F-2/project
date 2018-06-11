<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>고객센터</title>
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		
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

		<div id="list_category">
			<button value="1" onclick="search(this.value)">이용제재</button>
			<button value="2" onclick="search(this.value)">계정/인증</button>
			<button value="3" onclick="search(this.value)">구매/판매</button>
			<button value="4" onclick="search(this.value)">거래품목</button>
			<button value="5" onclick="search(this.value)">거래매너</button>
		</div>

<div id="abc"></div>
</body>
</html>