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
			$('#go').click(function(){				//다음페이지
				location.href = "./PurchaseDetailAction.buy?num=${buydata.num }&page="+${page+1};	
				//다음페이지 이동
			});
			$('#back').click(function(){				//이전페이지
				location.href = "./PurchaseDetailAction.buy?num=${buydata.num }&page="+${page-1};	
				//이전페이지 이동
			});
			
			
			$('.rmod').click(function () {			// 댓글 수정 버튼
				
				location.href="./CommentModifyAction.cmt?cnum="+$(this).val();
			});
			
		
			
			$('#insert_reply').click(function () {			// 댓글등록버튼
				if($("#comment_content").val() == "") {
					alert("글을 쓰시오");
					return false;
				}else{
			
					$.ajax({
						type : "post",
						dataType:"json",
						data : {
								comment_content:$("#comment_content").val(),
								num:$("PB_NO").val()
								},
						url : "./CommentWriteAction.cmt?num=${buydata.num }",
						success : function(data){ 
							if (data == 1) {
								alert("댓글 등록했다."+data);
								var out = "";
								out += '<tr>';
								out += '<td>작성자id</td>'
								out += '<td colspan="2">'
								out += $("#comment_content").val();
								out += '</td>'
								out += '<td><button class="rrep">답글</button>'
								out += '<td><button class="rmod">수정</button>'
								out += '<td><button class="rdel">삭제</button>'
								out += '</tr>'
								
								$('#reply').append(out);
								
							} else {
								alert("실패"+data);
							}
							
						},
						error : function(data){
							alert("에러"+data);
						}
					});
				
				}
			
			/* 	$('#rmod').click(function () {	
					
					
				});
			 */
			
				
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

	</table>	
	<br><hr><br>
	
	
	<table>
			<tr>
				<td>로그인 된 id값</td>
				<td colspan="5"><input type='text' name="comment_content" id="comment_content"><button id=insert_reply>댓글등록</button></td>
			</tr>
		<c:if test = "${requestScope.cmtlist != null}" >
			<c:forEach var ="comment" items="${requestScope.cmtlist}"> <!-- 댓글목록 출력반복문 -->
				<tr>
					<td>작성자id</td>
					
						<td colspan="2">
							<div>
								${comment.CMT_CONTENT}<br>
								<font size="2">${comment.CMT_DATE}</font>
							</div>
						</td>
					
					<td>${comment.CMT_NO}</td>
					<td><button class="rrep">답글</button>
					<td><button class="rmod" value="${comment.CMT_NO }">수정</button>
					<td><button class="rdel">삭제</button>
				</tr>	
				
						
			</c:forEach>
			
		</c:if>	
			<tbody id="reply"></tbody> <!-- 새로 입력된 댓글 ajax로 출현 -->
	
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
						<a href="./PurchaseDetailAction.buy?num=${buydata.num }&page=${a }">${a }</a>		
					</c:if>
					
			</c:forEach>
										
					<c:if test="${page >= endpage }">
						&nbsp; 다음
					</c:if>
					
					<c:if test="${page < endpage }">
						<a id="go">다음</a>
					</c:if>
					
				
				</td>	
			</tr>
	</table>


</body>
</html>