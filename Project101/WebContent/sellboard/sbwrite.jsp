<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>

		<!-- 달력 API -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		
		<!-- 이미지 업로드 API -->
        <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>

        <script src="js/sbwrite.js"></script>
        <link href="css/sbwrite.css" rel="stylesheet" type="text/css">

		<script type="text/javascript">
			/* $(function(){
				$('#hashtag').on('input', function(){
					var content = $(this).val();
					var splitedArray = content.split(' ');
					var linkedContent = '';
					for(var word in splitedArray)
					{
					  word = splitedArray[word];
					   if(word.indexOf('#') == 0)
					   {
					      word = '<span>' + word + '</span>';
					   }
					   linkedContent += word;
					}
					content = $(this).val(linkedContent);
				});
				
			}); */
		</script>
	</head>
	<body>	
		<form action="./sbwriteaction.sb" method="post" id="write_submit">
			<table class="sbwrite_table">
				<tr>
					<th colspan="2">판매게시판</th>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						${id}
					</td>
				</tr>
				<tr>
					<td>구매 날짜</td>
					<td>
						<input type="text" id="datepicker" name="SB_PDATE" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>해시 태그</td>
					<td>
						<input type="text" id="hashtag" name="SB_HASHTAG" size="50">
					</td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td>
						<select name="SB_CATEGORY">
							<option value="0" selected>선택</option>
						    <option value="1">의류/잡화</option>
						    <option value="2">뷰티</option>
						    <option value="3">식품/생활/유아동</option>
						    <option value="4">가구</option>
						    <option value="5">가전/디지털</option>
						    <option value="6">도서/쿠폰</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input name="SB_TITLE" type="text" size="50" maxlength="100"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<input name = "SB_PRICE" type="text" size="50" maxlength="50">
					</td>
				</tr>
				<tr>
					<td>
						<div>내용</div>
					</td>
					<td>
						<textarea name="SB_CONTENT" cols="65" rows="15"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
						</div>
					</td>
					<td>
						<div id="showImage"></div>
						<input type="hidden" id="img_hidden" name="img_hidden" value="">
					</td>
				</tr>
				<tr class="center">
					<td colspan="2">
						<input type=submit value="등록">
						<input type=reset value="취소" onclick = "history.go(-1)">
					</td>
				</tr>
			</table>
		</form>	
	</body>
</html>