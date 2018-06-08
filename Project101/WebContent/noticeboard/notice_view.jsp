<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach var="notice" items="${noticeCategory}">
	<details>
		<summary>
			<b>Q.</b> <b>${notice.notice_Q}</b>
		</summary>
		<p>
			<b>A.</b> <b>${notice.notice_A}</b>
		</p>
	</details>
</c:forEach>
