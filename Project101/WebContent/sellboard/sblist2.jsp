<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/Project101/sellboard/css/sblist.css" rel="stylesheet" type="text/css">

<!-- Google Map API -->
<script async defer src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDD7mtT6-3PmOJs9HEjXxrBwKryFLPGffU&callback=initMap&libraries=places'></script>

<script src="./sellboard/js/sblist.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#a_write').click(function() {
			var id = <%=session.getAttribute("id")%>;
			if (id == null) {
				alert('�α��� �� �̿��Ͻ� �� �ֽ��ϴ�.');
				return false;
			}
		});
	});
</script>


<div id="mapControler">
	<div id="emptyDiv">
		<div id="chkBtnDiv">
			<input type="button" id="checkCurrentPosition" value="������ġ���� ��ȸ">
		</div>
		<div id="locationField">
			<input id="autocomplete" placeholder="�˻��� ��Ҹ� �Է��ϼ���." type="text" />
		</div>
	</div>
</div>

<div id="map"></div>


<div class="search">
	<select id="search_category">
		<option selected="selected">��üī�װ�</option>
		<option value="1">�Ƿ�/�м���ȭ</option>
		<option value="2">���/����</option>
		<option value="3">��ǰ/��Ȱ/���Ƶ�</option>
		<option value="4">����/��Ȱ��ȭ</option>
		<option value="5">����/������</option>
		<option value="6">����/Ƽ��/����</option>
		<option value="7">��Ÿ</option>
	</select>
	
	<select id="search_sel">
		<option value="title" selected="selected">����</option>
		<option value="content">����</option>
		<option value="hashtag">�±�</option>
		<option value="title_content">����+����</option>
	</select>
	
	<input type="text" name="search_input" id="search_input" placeholder="Search..">
	
	<button id="search_btn">�˻�</button>
</div>

<div id="container">
		<c:forEach var="item" items="${boardBeanlist }" begin="0" end="9">
			<div class="content">
				<a href="sbview.sb?num=${item.num }&board_name=${item.board_name }">
					<img src="${item.image_url }">
					<p>${item.title }</p>
				</a>
				<p>${item.price }��</p>
				<p>${item.content }</p>
			</div>
		</c:forEach>
</div>

<div id="moveTop">&#xf139;</div>

