<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/form.css" rel="stylesheet" type="text/css">
<style>
form{border:2px solid black}
</style>

<!-- �Խ��� ����Ʈ -->

<table >
	<tr>
		<td colspan="2">�ŷ��߹�ǰ - view������</td>
	</tr>
	
	<tr>
		<td>�۾���</td>
	<td>
		<div>${tr.TR_NAME }</div>
	</td>
	</tr> 
	<tr>
		<td>ī�װ�</td>
		<td>${tr.TR_CATEGORY }</td>
	</tr>
	<tr>
		<td>����</td>
	<td>
		<div>${tr.TR_SUBJECT }</div>
	</td>
	</tr> 
	<tr>
		<td>����</td>
		<td>${tr.TR_PRICE }</td>
	</tr>
	<tr>
		<td>��¥</td>
		<td>${tr.TR_DATE }</td>
	</tr>
	<tr>
		<td>����</td>
	<td>
		<div>${tr.TR_CONTENT }</div>
	</td>
	</tr> 
	<tr>
		<td colspan="2" class="center">		
		<a href="./signtradelist.mem">���</a>
	</td>
</tr> 
</table>