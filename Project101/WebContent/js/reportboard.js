$(document).ready(function(){
	$('#list_detail').click(function(){
		location.href='BoardDetail.report';
	});
	$('#list_write').click(function(){
		location.href='BoardWrite.report';
	});
	$('#view_modify').click(function(){
		location.href='BoardModifyView.report';
	});
	$('#view_delete').click(function(){
		location.href='BoardDelete.report';
	});
	$('#view_list').click(function(){
		location.href='BoardList.report';
	});
});