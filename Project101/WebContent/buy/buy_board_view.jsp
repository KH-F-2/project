<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<html>
<head>
	<title>뷰 페이지</title>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script>
		$(document).ready(function () {  
			$('#mBtn').click(function () {			// 수정버튼
				location.href="./PurchaseModifyView.buy?num=${buydata.num }";
			});
			
			$('#dBtn').click(function () {			// 삭제버튼
				location.href="./PurchaseDeleteAction.buy?num=${buydata.num }";
			});
			$('#insert_reply').click(function () {			// 댓글등록버튼
				var num = $(PB_NO).val();
				var contents = $(#comment_content).val();
				
			});
			
			$('#lBtn').click(function () {			// 목록버튼
				location.href="./PurchaseListAction.buy";
			});
			
			
		});
	</script>
	
	<style>
		table{
			width: 600px;
			text-align: center;
		}
		td{
			border:1px solid black;
		}
	</style>
</head>
<body>
	<input type="hidden" name="PB_NO" value="${buydata.num }">
	<table> 
		<tr>
			<th colspan="3">뷰페이지</th>
		</tr>
		<tr>
			<td>
				<div>작성자</div>
			</td>
			<td colspan="2">
				<div>${buydata.writer }</div>
			</td>			
		</tr>
		<tr>
			<td>
				<div>제목</div>
			</td>
			<td colspan="2">
				<div>${buydata.title}</div>
			</td>			
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td colspan="2">
				<div>${buydata.content }</div>
			</td>			
		</tr>
		<tr>
			<td>
				<div>첨부파일</div>
			</td>
			<td colspan="2">
			<img src="">
				${buydata.file }
				
			</td>			
		</tr>
		<tr>
			<td colspan="3" class="center">
				
			<c:if test="${buydata.id == id || id=='admin' }">
			<button id="mBtn">수정</button>
			 &nbsp; &nbsp;
			<button id="dBtn">삭제</button> &nbsp; &nbsp;
			</c:if>		
				<button id="lBtn">목록</button> &nbsp; &nbsp;	
			</td>
		</tr>
		<!-- --------------------------------------지옥의 댓글 시작------------------------------------------- -->
		<tr>
			<td>로그인 된 id값</td>
			<td colspan="2"><input type='text' name="comment_content" id="comment_content"><button id=insert_reply>댓글등록</button></td>
			
		</tr>
	
	</table>
	
	

</body>
</html>