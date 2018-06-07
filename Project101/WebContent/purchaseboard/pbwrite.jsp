<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 


<html>
<head>
<title>구매 작성 페이지</title>
<style>
table{
	margin: 0 auto;
}
#title {
	float: left;
}

#writeNo {
	float: left;
}

#writeDate {
	float: right;
}

</style>
 <script charset="utf-8" src="//ucarecdn.com/libs/widget/3.3.0/uploadcare.full.min.js"></script>
<script>

	$(document).ready(function(){
		$('#cancelBtn').click(function(){
			history.back();
		});
		$("#submit").click(function(){
			location.href="./pbwriteAction.pb?PB_TITLE="+$('input[name="title"]').val()
							+"&PB_WRITER="+$('input[name="id"]').val()
							+"&PB_CONTENT="+$('textarea[name="content"]').val()
							//+"&PB_LAT="+$()+"&PB_LNG="+$() 아직 설정값 몰라
							+"&PB_PRICE="+$('input[name="price"]').val()
							+"&PB_CATEGORY="+$('select[name=category]').val()
							+"&PB_HASHTAG="+$('input[name="hashtag"]').val()	
							+"&img_hidden="+$('#img_hidden').val()
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
		<!-- 맨 윗줄 -->
		<span id='title'>구매게시판&nbsp;</span>
	<br>

	<form method="post" name="purchaseForm">
	
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name='id' id='id' readOnly type="text" value="<%=session.getAttribute("id")%>"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name='title' id='title' placeholder="제목을 입력해주세요" required></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan='2'><textarea name='content' id='content' required></textarea></td>	
			</tr>
			<tr>
				<td>위치</td>
				<td>이거를 어떻게 넣겠지?</td>
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
				<td>구매가격</td>
				<td><input type="text" name="price" placeholder="가격이 수정이 안됩니다." required></td>
			</tr>
			<tr>
				<td>헤시태그</td>
				<td><input type="text" name="hashtag" placeholder="태그"></td>
			</tr>
			<tr>
				<td>
				카테고리
				</td>
				<td>
					<select name="category">
					<option value="0">의류/패션잡화</option>
					<option value="1">가구/생활잡화</option>
					<option value="2">전자기기/게임</option>
					<option value="3">문화/도서/티켓</option>
					<option value="4">차량용품/오토바이</option>
					<option value="5">취미/레저/스포츠</option>
					<option value="6">기타</option>
					
			</select>
				</td>
			</tr>	
			<tr>
				<td colspan='2'>
					<input type="button" name='submit' id='submit' value="등록">
					<input type="button" name='cancelBtn' id='cancelBtn' value="취소">
				</td>
			</tr>		
		</table>
	</form>

</body>
</html>