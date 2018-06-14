<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<style>
		.cp_qa *, .cp_qa *:after, .cp_qa *:before {
			font-family: 'FontAwesome', sans-serif;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
		}
		.cp_qa dt {
			position: relative;
			margin: 0 0 1.5em 0;
			
		}
		.cp_qa dd {
			position: relative;
			margin: 0 0 2em 0;
			padding: 0 0 1.5em 2em;
			border-bottom: 1px dotted #0097a7;
		}
		
	</style>

<c:forEach var="notice" items="${list}">
	<div class="cp_qa">
			<dl>
				<dt>&nbsp;<img src = "./image/Q.png" width ="20" width = "20">&nbsp;&nbsp;${notice.notice_Q}</dt>
				
				<dd><img src = "./image/A.svg" width ="20" width = "20">&nbsp;&nbsp;${notice.notice_A}</dd>
			</dl>
	</div>
</c:forEach>