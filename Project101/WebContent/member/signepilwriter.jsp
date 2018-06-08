<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(document).ready(function() {
	$("#li_epil").click(function() {
		$.ajax({
			type : "POST",
			url : "signepilcontent.me",
			data : {"state" : "ajax"},
			success : function(data) {
					$(".li_content").html(data);									
			}			
		})
		return false;
		
	});
});
</script>
<style>

#user-filter ul {
    list-style-type: none;
    border-bottom: 1px solid #e9ecef;
    padding-bottom: 10px;
}
#user-filter ul li{
    display: inline-block;
}    
</style>
    
<section id="user-filter">
	<ul>
		<li>회원아이디 : ${id}</li>
	</ul>

    <ul>
      <li><a class="active" id="li_epil"  href="">
      	거래 후기</a></li>
    </ul>
    
    <ul>
    	<li class="li_content"></li>    
    </ul>
</section>	