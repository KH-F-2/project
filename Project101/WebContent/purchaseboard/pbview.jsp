<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
<html>
<head>
	<title>뷰 페이지</title>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script>
		$(document).ready(function () { 
			
			$('#modifyBtn').click(function () {			// 수정버튼
				location.href="./pbmodify.pb?num=${boardBean.num }";
			});
			
			$('#deleteBtn').click(function () {			// 삭제버튼
				location.href="./pbdeleteAction.pb?num=${boardBean.num }";
			});
			$('#next').click(function(){				//다음페이지
				location.href = "./pbview.pb?num=${boardBean.num }&page=" + ${page + 1};	
				//다음페이지 이동
			});
			$('#back').click(function(){				//이전페이지
				location.href = "./pbview.pb?num=${boardBean.num }&page=" + ${page - 1};	
				//이전페이지 이동
			});
			
			
			$('#cmtModifyBtn').click(function () {			// 댓글 수정 버튼
				location.href="./cmtmodifyAction.cmt?cmtNum=" + $(this).val();
			});
			
			$('#cmtInsertBtn').click(function () {			// 댓글등록버튼
				if($("#comment_content").val() == "") {
					alert("글을 쓰시오");
					return false;
				}else{
					$.ajax({
						type : "post",
						dataType : "json",
						data : {
								comment_content : $("#comment_content").val(),
								num : $("PB_NO").val()
								},
						url : "./cmtwriteAction.cmt?num=${boardBean.num }",
						success : function(data){
							if (data == 1) {
								alert("댓글 등록했다." + data);
								var out = "";
								out += '<tr>';
								out += '<td>작성자id</td>'
								out += '<td colspan="2">'
								out += $("#comment_content").val();
								out += '</td>'
								out += '<td><button id="cmtInsertBtn">답글</button>'
								out += '<td><button id="cmtModifyBtn">수정</button>'
								out += '<td><button id="cmtDeleteBtn">삭제</button>'
								out += '</tr>'
								
								$('#reply').append(out);
								
							} else {
								alert("실패" + data);
							}
							
						},
						error : function(data){
							alert("에러" + data);
						}
					});
				}
			
			});
			
			$('#listBtn').click(function () {			// 목록버튼
				location.href="./pbmain.pb";
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

	<input type="hidden" name="PB_NO" value="${boardBean.num }">
	<table>
		<tr>
			<th colspan="3">뷰페이지</th>
		</tr>
		<tr>
			<td>
				<div>작성자</div>
			</td>
			<td colspan="2">
				<div>${boardBean.writer }</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>제목</div>
			</td>
			<td colspan="2">
				<div>${boardBean.title}</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>내용</div>
			</td>
			<td colspan="2">
				<div>${boardBean.content }</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>첨부파일</div>
			</td>
			<td colspan="2"><img src=""> ${boardBean.file }</td>
		</tr>
		<tr>
			<td colspan="3" class="center">
				<c:if test="${boardBean.id == id || id == 'admin' }">
					<button id="modifyBtn">수정</button> &nbsp; &nbsp;
					<button id="deleteBtn">삭제</button> &nbsp; &nbsp;
				</c:if>
				<button id="listBtn">목록</button> &nbsp; &nbsp;
			</td>
		</tr>

	</table>
	
	<br><hr><br>
		<!-- --------------------------------------지옥의 댓글 시작------------------------------------------- -->

	<table>
		<tr>
			<td>로그인 된 id값</td>
			<td colspan="5"><input type='text' name="comment_content" id="comment_content">
			<button id=cmtInsertBtn>댓글등록</button></td>
		</tr>
		<c:if test="${requestScope.cmtList != null}">
			<c:forEach var="comment" items="${requestScope.cmtList}">
				<!-- 댓글목록 출력반복문 -->
				<tr>
					<td>작성자id</td>

					<td colspan="2">
						<div>
							${comment.CMT_CONTENT}<br> <font size="2">${comment.CMT_DATE}</font>
						</div>
					</td>

					<td>${comment.CMT_NO}</td>
					<td><button class="cmtInsertBtn">답글</button>
					<td><button class="cmtModifyBtn" value="${comment.CMT_NO }">수정</button>
					<td><button class="cmtDeleteBtn">삭제</button>
				</tr>
			</c:forEach>
		</c:if>
		
		<tbody id="reply"></tbody>
		<!-- 새로 입력된 댓글 ajax로 출현 -->

		<tr class="h30 lime center btn">
			<td colspan="6">
				<c:if test="${page <= 1 }">
					이전&nbsp;
				</c:if>
				<c:if test="${page>1 }">
					<a id="back">이전</a>
				</c:if>
				
				<c:forEach var="a" begin="${startpage }" end="${endpage }">
					<c:if test="${a == page }">
						${a }
					</c:if>

					<c:if test="${a != page }">
						<a href="./PurchaseDetailAction.pb?num=${boardBean.num }&page=${a }">${a }</a>
					</c:if>
				</c:forEach>
				
				<c:if test="${page >= endPage }">
					&nbsp; 다음
				</c:if>
				<c:if test="${page < endPage }">
					<a id="next">다음</a>
				</c:if>
			</td>
		</tr>
	</table>

</body>
</html>