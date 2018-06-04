<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- <script src="review_popup.js"></script> -->
<script>
//별 찍기
	$(document).ready(function() {
		$('.score-star a').bind(('click'), function() {

			var $this = $(this);
			var $parent = $this.closest('.score');

			if ($this.closest('.score').hasClass('readonly')) {
				return;
			}

			var $target = $parent.find('a');
			var idx = $this.index();

			// score star
			$target.removeClass('selected');
			for (var i = 0; i <= idx; i++) {
				$target.eq(i).addClass('selected');
			}
		});
	});
</script>

<script>
	$(
	function() {
		$(".button-register").click(function(){
			var star = $(".selected").text().split("점").length - 1;
			var content = $('.textarea-review').val();
			var name = $('input[name="EP_NAME"]').val();
			location.href = 'signaddaction.mem?&star=' + star + '&content='
					+ content + '&name=' + name;
			
		});
		
	})
</script>
<style>
.ui-dialog .ui-dialog-content>div {
	display: inline-block;
	position: relative;
	margin: 0 auto;
	text-align: left;
}

.layer-review.review-write {
	width: 510px;
}

.layer-review {
	margin: 0;
	padding: 20px 30px 30px;
	border: 5px solid #fd2424;
	background: #fff;
	font-family: '돋음', Dotum;
}

body {
	font-size: 12px;
	font-weight: normal
}

div {
	margin: 0
}

.layer-review .title {
	position: relative;
	padding: 10px 0 15px;
	border-bottom: 1px solid #666;
	z-index: 101;
	zoom: 1;
}

.layer-review .title .button-close {
	position: absolute;
	top: 0;
	right: 0;
	width: 50px;
	height: 21px;
	background-position: -129px -81px;
}

.layer-review .content {
	margin-top: 30px;
}

.layer-review .score {
	display: block;
	zoom: 1;
	/* padding-top: 16px; */
}

.layer-review .score:after {
	display: table;
	clear: both;
	content: '';
}

.layer-review .score span {
	display: inline-block;
	float: left;
	/* color: #666; */
	font-size: 12px;
	line-height: 21px;
	font-weight: bold;
}

.layer-review .score-star {
	margin-left: 20px;
}

.ui-dialog .ui-dialog-content>div {
	text-align: left
}

.layer-review .score .score-text {
	margin-left: 10px;
	color: #888;
	font-size: 11px;
	font-weight: normal;
}

.layer-review .textarea-wrap {
	position: relative;
	margin-top: 20px;
}

.layer-review.review-write textarea {
	width: 468px;
	height: 208px;
}

.layer-review .textarea-wrap .byte {
	position: absolute;
	right: 0;
	bottom: -30px;
	color: #bbb;
	font-size: 12px;
	line-height: 1.5;
}

.layer-review .photo-wrap {
	position: relative;
	margin-top: 12px;
	height: 60px;
	zoom: 1;
}

.layer-review .photo-wrap .photo {
	position: absolute;
	top: 0;
	left: 0;
	display: inline-block;
	float: left;
	width: 60px;
	height: 60px;
	background:
		url(//image.wemakeprice.com/images/resources/wmp/popup/review/pop-review-v2.png?v=17011102)
		-90px -21px no-repeat;
}

.layer-review .photo-wrap .photo img {
	width: 100%;
	display: none;
}

.layer-review img {
	border: none;
	text-align: center;
	vertical-align: top;
}

.layer-review .photo-wrap .delete {
	position: absolute;
	top: 0;
	right: 0;
	width: 19px;
	height: 19px;
	background-position: -110px -81px;
}

.layer-review .photo-wrap form {
	line-height: 1.5;
}

.layer-review .photo-attach {
	margin: 0;
	padding: 0 50px 0 70px;
	color: #666;
	font-size: 12px;
}

.layer-review .photo-attach .button-attach {
	/* overflow: hidden; */
	display: block;
	position: relative;
	margin-top: 8px;
	width: 80px;
	height: 21px;
	cursor: pointer;
}

.layer-review .photo-attach input[type=file] {
	display: inline-block;
	position: absolute;
	top: 0;
	left: 0;
	width: 75px;
	height: 21px;
	cursor: pointer;
	/* opacity: 0; */
	filter: alpha(opacity = 0);
	-ms-filter: "alpha(opacity=0)";
	cursor: pointer;
}

.layer-review .photo-attach .button-attach span {
	display: block;
	width: 60px;
	height: 21px;
	background-position: -90px 0;
	cursor: pointer;
	/* text-indent: -9999px; */
	cursor: pointer;
}

.layer-review .button-wrap {
	margin-top: 30px;
	text-align: center;
	zoom: 1;
}

.layer-review .button-wrap .button-register {
	background-position: 0 -36px;
}

.layer-review .button-wrap .button-cancel {
	background-position: 0 -108px;
}

.layer-review .score-star a {
	display: block;
	float: left;
	margin-top: -2px;
	margin-right: 5px;
	width: 22px;
	height: 21px;
	background:
		url(//image.wemakeprice.com/images/resources/wmp/popup/review/ico-star-v2.png?v=17011102)
		0 -22px;
	font-size: 0;
	line-height: 0;
	text-indent: -99999px;
}

.layer-review .score-star a.selected {
	background-position: 0 0;
}

span.signname {
	color: red
}

input#upfile.photo {
	width: 200px;
	height: 24px;
	background: white
}
</style>
<body>
	<div class="layer-review review-write" style="">
		<!--  수정일 경우 'review-edit'클래스 추가 -->
		<div class="title">
			<h1>구매후기 작성</h1>
			<input type="text" name="EP_NAME" value="${name }">
			<!-- 수정일 경우 '구매후기 수정'으로 변경 -->
		</div>

		<div class="content">
			<div class="box-gray">
				<div class="score">
					<!-- 읽기 전용일 경우 'readonly' class추가 -->

					<span>${id}님 상품은 만족스러우셨나요?</span> <span class="score-star"> <a class="">1점</a> <a
						class="">2점</a> <a class="">3점</a> <a class="">4점</a> <a class="">5점</a>
					</span>
				</div>
			</div>
		</div>

		<!-- 구매후기 작성 -->
		<div class="textarea-wrap">
			<span id="preNoteDefault" class="textarea" > 구매후기는
				10자 이상 500자 이하로 입력해주세요.</span>
			<textarea id="textarea-review" class="textarea-review"
				name="review_reason_text"></textarea>
			<span class="byte">(0/500자)</span>
		</div>

		<div class="button-wrap">
			<span> <!-- 구매 작성 일 경우 -->
				<button class="button-register">등록</button>

				<button class="button-cancel" onclick="window.history.go(-1)">취소</button>
			</span>
		</div>
	</div>
</body>