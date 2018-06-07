<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/Project101/sellboard/css/sblist.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	$(document).ready(function() {
		$('#a_write').click(function() {
			var id = <%=session.getAttribute("id")%>;
			if (id == null) {
				alert('로그인 후 이용하실 수 있습니다.');
				return false;
			}
		});
		
		$('#search_btn').click(function() {
			var word = $('input[name=search_input').val();
			var item = $('#search_sel').val();
			location.href = './sbmain.sb?word=' + word + '&item=' + item;
		});
	});
</script>

<div id="container">
		<c:forEach var="item" items="${boardPageBean.boardList }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.num }&boardname=${item.boardName }">
					<img src="./image/koala.jpg">
					<p>${item.title }</p>
				</a>
				<p>${item.price }원</p>
				<p>${item.content }</p>
			</div>
		</c:forEach>
</div>

<div class="search">
	<select id="search_sel">
		<option value="title" selected="selected">제목</option>
		<option value="content">내용</option>
		<option value="title_content">제목+내용</option>
	</select> <input type="text" name="search_input" placeholder="Search..">
	<button id="search_btn">검색</button>
</div>
