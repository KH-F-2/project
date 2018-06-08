<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="./css/joinform.css" rel="stylesheet">
<link href="./css/prettydropdowns.css" rel="stylesheet">

<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<script src="./js/member.js"></script>
<script src="./js/jquery.prettydropdowns.js"></script>

<script>
	$(document).ready(function() {

		$('#sel').change(function() {
			$('#domain').val($('#sel').val());
		});
		$('#brandsel').change(function() {
			$('#brand').val($('#brandsel').val());
		});

		var id, id2,nickname;
		
		$("#idcheck").click(function() {
			id = $("input[name=id]").val();

			if (id == '') {
				alert('아이디를 입력해주세요')
				return false;
			}

			$.ajax({
				type : "GET",
				data : {
					"id" : id
				},
				url : "idcheck.me",
				success : function(result) {
					data_check = result;
					if (result == 1) {
						$("#message").html('사용 가능한 아이디입니다.').css('color', 'blue');
					}
					if (result == -1) {
						$("#message").html('중복된 아이디입니다.').css('color', 'red');
						$("input[name=id]").val('');
						$("input[name=id]").focus();
					}
				}
			}); //ajax end
		}) //click end
		$("#nicknamecheck").click(function() {
			nickname = $("input[name=nickname]").val();

			if (nickname == '') {
				alert('닉네임을 입력해주세요')
				return false;
			}

			$.ajax({
				type : "GET",
				data : {
					"nickname" : nickname
				},
				url : "nicknamecheck.me",
				success : function(result) {
					data_check = result;
					if (result == 1) {
						$("#nickmessage").html('사용 가능한 닉네임입니다.').css('color', 'blue');
					}
					if (result == -1) {
						$("#nickmessage").html('중복된 닉네임입니다.').css('color', 'red');
						$("input[name=nickname]").val('');
						$("input[name=nickname]").focus();
					}
				}
			}); //ajax end
		}) //click end


		$('form').submit(function() {
			id2 = $("input[name=id]").val();
	
			if (id != id2) {
				alert('id중복검사를 해주세요');
				return false;
			}
			
			if (!joinform.email.value || !joinform.domain.value) {
				alert(emailerror);
				joinform.email.focus();
				return;
			} else {
				if (joinform.email.value) {
					if (joinform.domain.value == 0) {
						// 직접입력
						if (joinform.email.value.indexOf("@") == -1) {
							alert(emailerror);
							joinform.domain.focus();
							return false;
						}
					} else {
						// 선택입력
						if (joinform.email.value.indexOf("@") != -1) {
							alert(emailerror);
							joinform.email.focus();
							return false;
						}
					}
				}
			}
		});
	
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});

	});

</script>

<div id="sticky">
	<form name="joinform" action="joinProcess.me" method="post">
		<div id="first">
			<div id="form-title">
				<span>
					<h3>NICE2MEETU.COM에 등록하십시오</h3>
					<b>Nice2MeetU.COM</b>회원가입을 원하시면 아래 양식을 작성해 주십시오.<br>
					보다 편리한 쇼핑을 위해 제공해주신 정보를 저장합니다.
				</span>
			</div>
			
			<div class="form-row subTitle">
				<h3>사용자 계정</h3>
			</div>
			
			<div class="form-row">
				<label>ID</label>
				<div class="secondCol halfLen">
					<input type="text" name="id" id="id" required> 
					<input type="button" id="idcheck" value="ID중복검사"><br>
					<span id="message"></span>
				</div>
			</div>
			
			<div class="form-row">
				<label>닉네임</label>
				<div class="secondCol halfLen">
					<input type="text" name="nickname" required>
					<input type="button" id="nicknamecheck" value="닉네임중복검사"><br>
					<span id="nickmessage"></span>
				</div>
			</div>
			
			<div class="form-row">
				<label>E-mail</label>
				<div class="secondCol triLen">
					<input type="text" name="email" id="email" required>
					@<input type="text" name="domain" id="domain">
					<select name="sel" id="sel">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</div>
			</div>
			
			<div class="form-row">
				<label>비밀번호</label> 
					<div class="secondCol fullLen">
					<input type="password" name="password" required>
				</div>
			</div>
						
			<div class="form-row">	
				<label>비밀번호 확인</label>
				<div class="secondCol fullLen"> 
					<input type="password" name="passcheck" required>
				</div>
			</div>
		</div>
		
		<div id="second">
			<div class="form-row subTitle">
				<h3>연락처 정보</h3>
			</div>
			
			<div class="form-row">
				<label>연락처</label>
				<div class="secondCol telLen">
					<select name="brandsel"	id="brandsel">
						<option value="LGU+">LGU+</option>
						<option value="SKT">SKT</option>
						<option value="KT">KT</option>
					</select>
					<input type="text" name="phone" required><br>
				</div>
			</div>
			
			<div class="form-row">
				<label>우편번호</label>
				<div class="secondCol halfLen">
					<input type="text" name="post" class="postcodify_postcode5" />
					<input type="button" id="postcodify_search_button" value="우편번호 찾기"><br> 
				 </div>
			 </div>
			 
			 <div class="form-row">
				<label>주소</label>
				<div class="secondCol fullLen">
					<input type="text" name="address" class="postcodify_address" size=40  /><br>
				 </div>
			 </div>
			 
			 <div class="form-row">
				<label>상세주소</label>
				<div class="secondCol fullLen"> 
					<input type="text" name="detailaddress" size=40><br>
				</div>
			</div>
		</div>
		
		<div class="form-row subTitle">
			<h3>기본 위치 설정</h3>
		</div>
		<div class="form-row">
			<div id="locationField">
				<input id="autocomplete" placeholder="검색할 장소를 입력하세요." type="text" />
			</div>
			<div id="map"></div>
			<input type="hidden" id="markerLat" name="markerLat">
			<input type="hidden" id="markerLng" name="markerLng">
		</div>
		
		<div class="form-row last-row">
			<span class="check" id="check1">&#xe801;</span>
			<h3>모든 항목에 체크하기</h3><br>
			<span class="check" id="check2">&#xe801;</span>
			<h3>* 개인정보의 수집 및 이용에 대한 동의 자세히 보기</h3><br>
			<span class="check" id="check3">&#xe801;</span>
			<h3>* 개인정보의 국외 이전에 대한 동의 자세히 보기</h3><br>
			<span class="check" id="check4">&#xe801;</span>
			<h3>뉴스레터 구독을 위한 개인정보의 수집 및 이용에 대한 동의 자세히 보기</h3>
		</div>
		
		<div class="form-row">
			<div id="clearfix">
				<button type="submit" class="submitbtn">회원가입</button>
				<button type="reset" class="cancelbtn">다시 작성</button>
			</div>
		</div>
		
	</form>
</div>
