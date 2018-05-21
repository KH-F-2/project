<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style>
			* {
				box-sizing: border-box;
			}
			
			/* Add a gray background color with some padding */
			body {
				font-family: Arial;
				padding: 20px;
				background: #f1f1f1;
			}
			
			/* Create two unequal columns that floats next to each other */
			/* Left column */
			.column {
				width: 100%;
			}
			
			/* Fake image */
			.fakeimg {
				background-color: #aaa;
				width: 25%;
				padding: 20px;
				display: inline-block;
			}
			#content_img{
				width : 100%;
				height : 95%;
			}
			p{
				display: inline-block;
			}
			/* Add a card effect for articles */
			.card {
				background-color: white;
				padding: 20px;
				margin-top: 20px;
			}
			
			/* Clear floats after the columns */
			.row:after {
				content: "";
				display: table;
				clear: both;
			}
			
			/* Footer */
			.footer {
				padding: 20px;
				text-align: center;
				background: #ddd;
				margin-top: 20px;
			}
			
			/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
			@media screen and (max-width: 800px) {
				.leftcolumn, .rightcolumn {
					width: 100%;
					padding: 0;
				}
			}
		</style>
	</head>
	<body>
	
	
		<div class="row">
			<div class="column">
				<div class="card">
					<table width = "100%">
						<tr>
							<td width = "10%">글번호</td>
							<td width = "15%">[카테고리]</td>
							<td>제목</td>
							<td width = "15%">날짜</td>
						</tr>
					</table>
					<div class="fakeimg" style="height: 200px;"><img id = "content_img" src = "/test/image/File1.jpg"></div>
					<img id = "sell_icon" src = "/test/image/sellicon.PNG"><br>
					<p>
						가격 : 백마누언
						~~~~~~ 내용 ~~~~~
						~~~~~~ 내용 ~~~~~
						~~~~~~ 내용 ~~~~~
						~~~~~~ 내용 ~~~~~
						~~~~~~ 내용 ~~~~~
					</p>
				</div>
			</div>
		</div>
	
	
	</body>
</html>
