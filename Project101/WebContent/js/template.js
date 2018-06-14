$(document).ready(function() {
	$('#navTitle').click(function() {
		location.href = 'main.map';
	});

	$('.item').click(function () {
		var value = $(this).val();
		
		if (value != 'report') {
			$('.pusher .container_section').load('./sellboard/sblist2.jsp');
			
			
//			location.href = 'sbmain.sb?page=1&centerLat=' + centerLat 
//			+ '&centerLng=' + centerLng + '&category=' + value + '&word=&item=&state=ajax';
		}
		
	});

});