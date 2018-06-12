<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/test/js/sellboard.js"></script>
        <style>
        	 nav{height: 40px; background: #e5ffff} 
			a{text-decoration: none; }
			.right{margin-top: 10px; margin-right: 30px; float: right; color: #fb8c00; display: inline-block; font-weight: bold;}
			.right>a{margin-left: 15px;} 	
			table{margin:0 auto; width: 50%; text-align: center; margin-top: 20px; font-family: koverwatch; }
			tr:first-child{background-color:#A566FF; height: 50px; color: white; letter-spacing: 3px; font-size: 16pt;}
			tr{height: 30px;}
			tr>td:first-child{width:150px; margin-left: 10px; background-color: rgba(205,185,202,0.4);}
			tr>td:nth-child(2n){background-color: rgba(185,245,202,0.4); text-align: left;}
			textarea{resize: none; }
		
        </style>
	</head>
			<table>
				<tr>
					<td>글번호 : ${ebBoardBean.SB_NO }</td>
					<td><div>${ebBoardBean.SB_TITLE}</div></td>
					<td>${ebBoardBean.SB_DATE }</td>
				</tr>
				<tr>					
					<td colspan=3>
						<div>${ebBoardBean.SB_WRITER}</div>
					</td>
				</tr>
				<tr>
					<td><div>내용</div></td>
					<td colspan=2>
						<input type="button" value="후기" id="epil_btn">
						<div>
						구매가격 : ${ebBoardBean.SB_PRICE}<br>
						평점 : ${ebBoardBean.SB_GRADE }<br><br> 
						
						내용 : 
						
						${ebBoardBean.SB_CONTENT }</div>
					</td>
				</tr>
				<tr>
					<td><div>조회수</div></td>
					<td colspan=2><div>${ebBoardBean.SB_READCOUNT}</div></td>
				</tr>
				
				<tr>
					<td><div>첨부파일</div></td>
					<c:if test="${!empty ebBoardBean.SB_FILE}">
					<td colspan=2>
						<img src="image/down.png" width="10px">
						<a href="/ebfiledown.eb?filename=${ebBoardBean.SB_FILE }">
							${ebBoardBean.SB_FILE }</a>
					</c:if>
				</tr>
				
				<tr>
					<td colspan=3>
					    	<a href="<c:url value='/ebmodifyview.eb?num=${ebBoardBean.SB_NO}'/>">
								<input type="button" value="수정">
							</a> &nbsp;
							<a href="<c:url value='/ebdelete.eb?num=${ebBoardBean.SB_NO}'/>">
								<input type="button" value="삭제">
							</a> &nbsp;
							<a href="<c:url value='/ebmain.eb'/>">
									<input type="button" value="목록">
							</a>
				</tr>
			</table>
	</body>
</html>