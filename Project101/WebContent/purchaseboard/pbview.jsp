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
			
			$('.cmtDeleteBtn').click(function () {			// 댓글 삭제 버튼
				location.href="./CommentDeleteAction.cmt?URL=./pbview.pb?num=&CMT_BOARD_NAME=purchaseboard&num=${boardBean.num }&cmtNum=" + $(this).val();
			});
			
			$('#cmtInsertBtn').one("click" , function(){			// 댓글등록버튼
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
								url : "./cmtwriteAction.cmt?num=${boardBean.num }&CMT_BOARD_NAME=purchaseboard",
						success : function(data){
							if (data == 1) {
								location.href='./pbview.pb?num=${boardBean.num }&page=${endPage}';  //등록시 마지막 페이지로
						
								
							} else {
								alert("실패" + data);
							}
							
						},
						error : function(data){
							alert("에러다" + data);
						}
					});
				}
				
			});

			$('.recmtInsertBtn').click( function(){  //다른 답글 버튼 클릭시 이전에 펼쳐진 댓글 입력창 사라지게 하는거
				$('.answer').empty();
			});
			
			$('.recmtInsertBtn').click( function(){  //답글 클릭시 답글 입력창 띄워주는 거
			
				var a = $(this).val();
				
				var reout = "";
				reout += '<li>';
				reout += '<div>';
				reout += '<span>답답글 쓰는 id</span>'
				reout += '<span><input type="text" id="rere_Comment"></span>'
				reout += '<span> <button id="rereBtn'+a+'"';
				reout += ' class="rereBtn" value='+a+'>등록</button> </apsn>'
				reout += '</div>';
				reout += '</li>';
				
				$('#answer'+ a).empty();
				$('#answer'+ a).append(reout);
					
			});
			
			$(document).on('click', '.rereBtn', function () {			// 대댓글 등록 버튼
				
				if($("#rere_Comment").val() == "") {
					alert("글을 쓰시오");
					return false;
				}else{		
					$.ajax({
						type : "post",
						dataType : "json",
						data : {
								comment_content : $("#rere_Comment").val(),
								num : $("PB_NO").val()
								},
						url : "./CommentReplyAction.cmt?CMT_BOARD_NAME=purchaseboard&num=${boardBean.num }&cmtnum="+$(this).val(),
						success : function(data){
							if (data == 1) {
								location.href='./pbview.pb?num=${boardBean.num }&page=${page}'; //등록시 원문글 페이지로
							} else {
								alert("실패" + data);
							}
							
						},
						error : function(data){
							alert("대댓글에러다" + data);
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
		li{
			border:1px solid black;
			width: 600px;
			height: 30px;
		}
		.reBtn{
			float:right;
		}
		ul{
   			list-style:none;
  		}
  		#bottompage{		
  			text-align:center;
  			margin:auto;
  		}
  		#viewtable{
  			margin:auto;
  		}
  		#replytable{
  			algin:center;
  			margin:auto;
  		}
  		

   	
	</style>
</head>

<body>

	<input type="hidden" name="PB_NO" value="${boardBean.num }">
	<table id="viewtable">
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

	<ul id="replytable">
		<li>
			<span>로그인 된 id값</span>
			<span><input type='text' name="comment_content" id="comment_content">
			<button id=cmtInsertBtn>댓글등록</button></span>
		</li>
		<c:if test="${requestScope.cmtList != null}">
			<c:forEach var="comment" items="${requestScope.cmtList}">
				<!-- 댓글목록 출력반복문 -->
		
				<li>
				<div>
					<span id="cmt_writer">작성자id</span>

					
						<span>
							<c:if test="${comment.CMT_RE_LEV !=0 }">
								<c:forEach var = "a" begin="1" end="${comment.CMT_RE_LEV }" step="1">
									R  <!-- 댓글 깊이 만큼 r 찍기 -->
								</c:forEach>
							</c:if>
							
							${comment.CMT_CONTENT} <font size="2">${comment.CMT_DATE}</font>
							
							
						</span>
				
													
						<span class="reBtn"><button id="recmtInsertBtn" class="recmtInsertBtn" value="${comment.CMT_NO }">답글</button></span>
						<span class="reBtn"><button class="cmtDeleteBtn" value="${comment.CMT_NO }">삭제</button></span>
					</div>
				</li>
				<span class="answer" id ="answer${comment.CMT_NO}"></span> <!-- 대댓글 칸 -->
			</c:forEach>
		</c:if>
		
		<div id="reply"></div>
		
		<!-- 새로 입력된 댓글 ajax로 출현 -->

		<li>
			<div id=bottompage>
				<c:if test="${page <= 1 }">
					이전&nbsp;
				</c:if>
				<c:if test="${page>1 }">
					<a id="back">이전</a>
				</c:if>
				
				<c:forEach var="a" begin="${startPage }" end="${endPage }">
					<c:if test="${a == page }">
						${a }
					</c:if>

					<c:if test="${a != page }">
						<a href="./pbview.pb?num=${boardBean.num }&page=${a }">${a }</a>
					</c:if>
				</c:forEach>
				
				<c:if test="${page >= endPage }">
					&nbsp; 다음
				</c:if>
				<c:if test="${page < endPage }">
					<a id="next">다음</a>
				</c:if>
			</div>
		</li>
	</ul>

</body>
</html>