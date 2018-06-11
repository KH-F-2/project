<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Semantic UI Library -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="semantic/semantic.min.css">
<script src="semantic/semantic.min.js"></script>
	
<link rel="stylesheet" type="text/css" href="css/template.css">
<script>
	$(document).on("click", "#item1", (function() {
		var writer = $('#writer').val();

		$.ajax({
			type : "POST",
			url : "sellerpage_main_ajax.me?writer=" + writer,
			data : {
				"writer" : writer
			},
			success : function(data) {

				$("#abc").empty().append(data);
			}
		})

		return false;
	}))

	$(document).on("click", "#item2", (function() {
		var writer = $('#writer').val();
		$.ajax({
			type : "POST",
			url : "sellerpage_main2_ajax.me?writer=" + writer,
			data : {
				"writer" : writer
			},
			success : function(data) {

				$("#abc").empty().append(data);
			}
		})

	}))

	$(document).ready(function() {
		$("#item3").click(function() {
			var writer = $('#writer').val();
			$.ajax({
				type : "POST",
				url : "signepilcontent.me?writer=" + writer,
				data : {
					"state" : "ajax"
				},
				success : function(data) {
					$("#abc").empty().html(data);
				}
			})
			return false;

		});
	});
</script>
<style>
#content_box {
	display: inline-block
}
</style>


<div class="ui left vertical menu sidebar">
	<h1 id="navTitle">
		<i>&#xe801;</i> 우리지금만나
	</h1>
	<h2>Category</h2>
	<a class="item" href="pbmain.pb">구매게시판</a> <a class="item"
		href="sbmain.sb">판매게시판</a> <a class="item" href="ebmain.eb">후기게시판</a>
	<a class="item" href="rbmain.rb">신고게시판</a>
</div>

<div id="header">
	<jsp:include page='header.jsp' />
</div>

<div id="main_top">
	<span>"${writer}"회원님의 등록된 판매 게시글</span><br> <input type="hidden"
		id="writer" value="${writer }">
</div>

<div id="list_category">
	<a class="item" id="item1" href="#">앨범형</a> 
	<a class="item" id="item2"href="#">게시판형</a> 
	<a class="item" id="item3" href="#">후기보기</a>
	<%-- signepilwriter.me?writer=${writer } --%>
</div>

<div id="abc">

	<c:if test="${listcount != 0 }">
		<c:forEach var="boardBean" items="${getimage }">
			<div id="content_box">
				<div class="content">
					<img src="${boardBean.IMAGE_URL}">
					<p>
						&nbsp;<a href="./sbview.sb?num=${boardBean.SB_NO }">
							${boardBean.SB_TITLE}</a>
					</p>
					<span>${boardBean.SB_CONTENT}</span>
				</div>
			</div>
		</c:forEach>
	</c:if>

</div>
