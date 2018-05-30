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
		/* table{margin:0 auto; width: 80%; text-align: center; margin-top: 20px;
			 font-family: koverwatch; border:1px solid black }
		tr:first-child{width:70%; margin-left:10px; background:yellow}
		tr:nth-child(2){text-align: left} */
		
		
        </style>
	</head>
			<table>
				<!-- <tr class="center">
					<th colspan="2">후기 게시판 - view 페이지</th>
				</tr> -->
				<tr>
					<td>글번호 : ${epilboard.SB_NO }</td>
					<td><div>${epilboard.SB_TITLE}</div></td>
					<td>${epilboard.SB_DATE }</td>
				</tr>
				<tr>					
					<td colspan=3>
						<div>${epilboard.SB_WRITER}</div>
					</td>
				</tr>
				<tr>
					<td><div>내용</div></td>
					<td colspan=2>
						<input type="button" value="후기" id="epil_btn">
						<div>
						구매가격 : ${epilboard.SB_PRICE}<br>
						평점 : ${epilboard.SB_GRADE }<br><br> 
						
						내용 : 
						
						${epilboard.SB_CONTENT }</div>
					</td>
				</tr>
				<tr>
					<td><div>조회수</div></td>
					<td colspan=2><div>${epilboard.SB_READCOUNT}</div></td>
				</tr>
				
				<tr>
					<td><div>첨부파일</div></td>
					<c:if test="${!empty epilboard.SB_FILE}">
					<td colspan=2>
						<img src="image/down.png" width="10px">
						<a href="/BoardFileDown.epil?filename=${epilboard.SB_FILE }">
							${epilboard.SB_FILE }</a>
					</c:if>
				</tr>
				
				<tr>
					<td colspan=3>
					    <%-- <c:if test="${id=='admin'||id==boardlist.SB_WRITER}"> --%>
					    	<a href="<c:url value='/BoardModifyView.epil?num=${epilboard.SB_NO}'/>">
								<input type="button" value="수정">
							</a> &nbsp;
							<a href="<c:url value='/BoardDelete.epil?num=${epilboard.SB_NO}'/>">
								<input type="button" value="삭제">
							</a> &nbsp;
						<%-- </c:if> --%>
							<a href="<c:url value='/BoardList.epil'/>">
									<input type="button" value="목록">
							</a>
				</tr>
			</table>
	</body>
</html>