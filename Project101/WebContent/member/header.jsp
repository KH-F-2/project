<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<button id="menuBtn1">&#xe800;</button>
<span>Nice2MeetU</span>
<c:set var="id" value="${id }" />
<c:choose>
	<c:when test="${empty id }">
		<a href="./signin.me">Sign in</a>
		<br>
	</c:when>

	<c:otherwise>

		<a href="signout.me">Sign Out</a>
		<a href="mypage.me?id=${id }">${id }</a>
	</c:otherwise>
</c:choose>
