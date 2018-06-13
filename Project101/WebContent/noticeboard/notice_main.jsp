<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	$(document).ready(function () {
		$('#report').click(function () {
			location.href = 'rbmain.rb';
		});
		
	});

	function search(e) {

		var notice_category = e;

		$.ajax({
			type : "POST",
			url : 'noticecategory.nt',
			data : {
				"notice_category" : notice_category
			},
			success : function(data) {

				$("#abc").empty().append(data);
			}
		})
	}
</script>

<div id="reportSection">
	<button id="report">신고하기</button>
</div>

<div id="list_category">
	<button value="1" onclick="search(this.value)">이용제재</button>
	<button value="2" onclick="search(this.value)">계정/인증</button>
	<button value="3" onclick="search(this.value)">구매/판매</button>
	<button value="4" onclick="search(this.value)">거래품목</button>
	<button value="5" onclick="search(this.value)">거래매너</button>
</div>

<div id="abc"></div>
