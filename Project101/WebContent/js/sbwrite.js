$(document).ready(function(){
	$.datepicker.regional['kr'] = {
			dateFormat: 'yy-mm-dd',
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames: ['일','월','화','수','목','금','토'],
		    dayNamesShort: ['일','월','화','수','목','금','토'],
		    dayNamesMin: ['일','월','화','수','목','금','토'],
		    showMonthAfterYear: true,
		    changeMonth: true,
		    changeYear: true,
		};

	$.datepicker.setDefaults($.datepicker.regional['kr']);
	$("#datepicker").datepicker();

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
	
	
	$('#write_submit').submit(function () {
		if($('input[name=SB_TITLE]').val()==''){
        	alert('제목을 입력하세요');
        	$('input[name=SB_TITLE]').focus();
        	return false;
        }
		if($('select').val()==0){
			alert('카테고리를 선택해 주세요');
			return false;
		}
		if($('#datepicker').val() == ''){
			alert('구매일을 선택해 주세요');
			return false;
		}
        if($('input[name=SB_PRICE]').val()==''){
            alert("가격을 입력하세요");
            $('input[name=SB_PRICE]').focus();
            return false;
        }
        if(isNaN($('input[name=SB_PRICE]').val())){
            alert('가격은 숫자로 입력하세요');
            $('input[name=SB_PRICE]').focus();
            $('input[name=SB_PRICE]').val('');
            return false;
        }
        if($('textarea[name=SB_CONTENT]').val()==''){
            alert("내용을 입력하세요");
            return false;
        }
        
    });
	
});