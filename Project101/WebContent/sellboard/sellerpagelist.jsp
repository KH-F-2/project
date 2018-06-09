<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<body>

			<c:forEach var="boardBean" items="${getimage }">
				<div id="content_box">
					<div class="content">
						<img src="${boardBean.IMAGE_URL}">
						<p>&nbsp;<a href="./sbview.sb?num=${boardBean.SB_NO }">
								${boardBean.SB_TITLE}</a></p>
						<span>${boardBean.SB_CONTENT}</span>
					</div>
				</div>	
			</c:forEach>
	
	</body>
