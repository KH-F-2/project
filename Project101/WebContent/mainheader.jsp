<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/fontello-embedded.css">
<link rel="stylesheet" type="text/css" href="css/header.css">

<script src="js/header.js"></script>

<c:set var="id" value="${id }" />

<div id="header">
	<div id="sec1">
		<button id="menuBtn">&#xf008;</button>
	</div>
	
	<div id="sec2">
		<span id="header_title">Nice2MeetU</span>
	</div>
	
	<div id="sec3">
		<c:choose>
			<c:when test="${empty id }">
				<a href="./signin.me">Sign in</a>
			</c:when>
	
			<c:otherwise>
				<c:if test="${!empty receivedMessage }">
					<a href="msmessagetolist.ms?page=1">&#xe81b;</a>
				</c:if>
				<a href="mypage.me?id=${id }">${id }</a>
				<a href="signout.me">Sign Out</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>