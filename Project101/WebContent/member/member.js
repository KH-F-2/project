$(document).ready(function() {
	
	$('#sel').change(function() {
		var a = $(this).val();

		if (a == '') {
			$('#domain').val('');
			$('#domain').focus();
		} else {
			$('#domain').val($(this).val());
		}
	});

	$('#postcode').click(function() {
		window.open("post.html", "post", 'width=300, height=250')

	});

	$('form').submit(function() {
		if ($('#id').val() == '') {
			alert("ID를 입력하세요");
			$('#id').focus();
			return false;
		}

		if ($('#pass').val() == '') {
			alert("pass를 입력하세요");
			$('#pass').focus();
			return false;
		}


		if ($('#email').val() == '') {
			alert("EMAIL을 입력하세요");
			$('#email').focus();
			return false;
		}
		if ($('#domain').val() == '') {
			alert("domain을 입력하세요");
			$('#domain').focus();
			return false;
		}

		if ($('#post').val() == '') {
			alert("우편번호 앞자리를 입력하세요");
			$('#post').focus();
			return false;
		}



		if ($('#address').val() == '') {
			alert("주소를 입력하세요");
			$('#address').focus();
			return false;
		}

		if ($('#school').val() == '') {
			alert("학교를 입력하세요");
			$('#school').focus();
			return false;
		}

		if ($('#major').val() == '') {
			alert("학번을 입력하세요");
			$('#major').focus();
			return false;
		}

		if ($('#college').val() == '') {
			alert("학과를 입력하세요");
			$('#college').focus();
			return false;
		}

		if ($('#pass').val() == '') {
			alert("비밀번호를 입력하세요");
			$('#pass').focus();
			return false;
		}
		if ($('#pass').val() != $('#passcheck').val()) {
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}


	});

});