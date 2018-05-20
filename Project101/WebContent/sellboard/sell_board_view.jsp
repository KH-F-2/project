<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			table {
				width : 80%;
				border : 1px black solid;
				height : 500px;
				text-align: center;
			}
			table>tr:first-child>td {
				background-color: #88c399;
			}
			#div_1{
				width : 30%;
				height : 350px;
			}
			#div_2{
				width : 70%;
				height : 350px;
			}
			#content_img{
				width : 95%;
				height : 80%;
				display: inline-block;
			}
			#sell_icon{
				float: left;
			}
		</style>
	</head>
	<body>
		<form id = "sell_view" action = "" method = "post">
			<table border="1">
				<tr>
					<td width = "10%">글번호</td>
					<td width = "15%">[카테고리]</td>
					<td>제목</td>
					<td width = "15%">날짜</td>
				</tr>
				<tr height = "350px">
					<td colspan="4">
						<div id = "div_1">
							<img id = "content_img" src = "/test/image/File1.jpg">
							<button id = "buy_btn">구매신청</button>
						</div>
						<div id = "div_2">
							<img id = "sell_icon" src = "/test/image/sellicon.PNG"><br>
							가격 : 백마누언
							~~~~~~ 내용 ~~~~~
							~~~~~~ 내용 ~~~~~
							~~~~~~ 내용 ~~~~~
							~~~~~~ 내용 ~~~~~
							~~~~~~ 내용 ~~~~~
						</div>
					</td>
				</tr>
				<tr>
					<td>
						댓글
					</td>
					<td colspan="3">
						~~댓글~~
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<button>수정</button>
						<button>삭제</button>
						<button>목록</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>