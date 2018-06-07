<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     	

<html>
<head>
<title>수정 페이지</title>
<style>
table{
	margin: 0 auto;
}
#title {
	float: left;
	margin: 0 auto;
}

#writeNo {
	float: left;
}

#writeDate {
	float: right;
}
header{
	border: 1px solid black;
}
nav{
	border: 1px solid black;
}
aside {
	float: left;
	height: 100%;
	border: 1px solid black;
}
section{
height: 100%;
}
</style>
<script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>
<script>
$(document).ready(function() {
	$('#submit').click(function() { 
		location.href = "./pbmodifyAction.pb?PB_CATEGORY="
				+$('#category option:selected').val()+"&PB_NO="+$('#PB_NO').val()+
				"&PB_TITLE="+$('#PB_TITLE').val()+"&PB_CONTENT="+$('#PB_CONTENT').val()+
				"&PB_HASHTAG="+$('#PB_HASHTAG').val()+"&img_hidden="+$('#img_hidden').val()
	});
	
	UPLOADCARE_LOCALE = "ko";
	   UPLOADCARE_TABS = "file url";
	   UPLOADCARE_PUBLIC_KEY = "c45d0fc9bcc9538a677e";
	   UPLOADCARE_LOCALE_TRANSLATIONS = {
	      buttons: {
	          cancel: 'Cancel',
	          remove: 'Remove',
	          choose: {
	             images: {
	              one: '파일 첨부',
	              other: '파일 첨부'
	              }
	         }
	      }
	   };
	   var widget=uploadcare.MultipleWidget('[role=uploadcare-uploader]');
	   widget.onUploadComplete(function(info){
	      var url=[];
	      console.log(info.cdnUrl);
	      $('#showImage').empty();
	      $('#img_hidden').attr('value', '');
	      var length=info.cdnUrl.charAt(info.cdnUrl.length-2);
	      for(var i=0;i<length;i++){
	         url[i]=info.cdnUrl+"nth/"+i+"/";
	         $('#showImage').append('<img src="'+url[i]+'-/resize/x100/"/>');
	         url[i]+="-/resize/500x/ ";
	         var val=$('#img_hidden').attr('value');
	         $('#img_hidden').attr('value', val+url[i]);
	      }
	   });
	
});

</script>
</head>
<body>

	<form method="post" name="Modifyform">
	<input type = "hidden" name="PB_NO" id="PB_NO" value="${boardBean.PB_NO }">

		<table>
			<tr>
				<td>작성자</td>
				<td>로그인 된 ID값</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name='PB_TITLE' id='PB_TITLE' value="${boardBean.PB_TITLE}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td></td>
			</tr>
			<tr>
				<td colspan='2'><textarea name='PB_CONTENT' id='PB_CONTENT'>${boardBean.PB_CONTENT}</textarea></td>	
			</tr>
			<tr>
				<td>위치</td>
				<td>클릭이미지</td>
			</tr>
			<tr>
				<td>첨부사진</td>
				<td class="image">
					
					<input type="hidden" role="uploadcare-uploader" name="image" data-images-only="true" data-multiple="true" />
				
				<div id="showImage"></div>
				  <input type="hidden" id="img_hidden" name="img_hidden" value="">
				</td>	
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name='PB_PRICE' value="${boardBean.PB_PRICE } " readonly></td>
			</tr>
			<tr>
				<td>헤시태그</td>
				<td><input type="text" name='PB_HASHTAG' id='PB_HASHTAG' value="${boardBean.PB_HASHTAG }"></td>
			</tr>
			<tr>
				<td>
				카테고리
				</td>
				<td>
					<select id="category">  <!-- 기존에 선택되었던 셀렉트값 초기 선택값으로 만들기 -->
						<option value="0"
						<c:if test= "${boardBean.PB_CATEGORY eq '0' }">selected</c:if>>의류/패션잡화</option>
						<option value="1"
						<c:if test= "${boardBean.PB_CATEGORY eq '1' }">selected</c:if>>가구/생활잡화</option>
						<option value="2"
						<c:if test= "${boardBean.PB_CATEGORY eq '2' }">selected</c:if>>전자기기/게임</option>
						<option value="3"
						<c:if test= "${boardBean.PB_CATEGORY eq '3' }">selected</c:if>>문화/도서/티켓</option>
						<option value="4"
						<c:if test= "${boardBean.PB_CATEGORY eq '4' }">selected</c:if>>차량용품/오토바이</option>
						<option value="5"
						<c:if test= "${boardBean.PB_CATEGORY eq '5' }">selected</c:if>>취미/레저/스포츠</option>
						<option value="6"
						<c:if test= "${boardBean.PB_CATEGORY eq '6' }">selected</c:if>>기타</option>
				
			</select>
				</td>
			</tr>		
			<tr>
				<td colspan='2'>
					<input type="button" name='submit' id='submit' value="수정">
					<input type="button" name='cancel' id='cancel' value="취소" onClick="history.go(-1)">
				</td>
			</tr>		
		</table>
	</form>

</body>
</html>