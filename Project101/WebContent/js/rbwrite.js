$(document).ready(function(){
	
	
	$('#write_submit').submit(function () {
		

		if($('input[name=RB_TITLE]').val()==''){
        	alert('제목을 입력하세요');
        	$('input[name=RB_TITLE]').focus();
        	return false;
        }
	
		
        if($('input[name=RB_PRICE]').val()==''){
            alert("가격을 입력하세요");
            $('input[name=RB_PRICE]').focus();
            return false;
        }
        if(isNaN($('input[name=RB_PRICE]').val())){
            alert('가격은 숫자로 입력하세요');
            $('input[name=RB_PRICE]').focus();
            $('input[name=RB_PRICE]').val('');
            return false;
        }
        if($('textarea[name=RB_CONTENT]').val()==''){
            alert("내용을 입력하세요");
            return false;
        }
        
        
    });
	
	
	
	
	
});
