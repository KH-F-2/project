<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>�Ǹ� �Խ���</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/test/js/sellboard.js"></script>
		<link href="/test/css/board_list.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table>
			<c:if test="${listcount>=1}">
				<tr>
					<th colspan="3">�Ǹ� �Խ��� - list</th>
					<th colspan="2">�� ���� : ${listcount }</th>
				</tr>
				<tr>
					<th width="8%"><div>��ȣ</div></th>
					<th width="50%"><div>����</div></th>
					<th width="14%"><div>�ۼ���</div></th>
					<th width="17%"><div>��¥</div></th>
					<th width="11%"><div>��ȸ��</div></th>
				</tr>
				
				
				<c:set var="num" value="${listcount-(page-1)*limit }"/>
				<c:forEach var="board" items="${boardlist }">
					<tr>
						<td>
							<c:out value="${num }"/>
							<c:set var="num" value="${num-1 }"/>
						</td>
						<td style="text-align: left;">
							<div>
								<a href="./BoardDetail.sell?num=${board.SB_NO }">
									${board.SB_TITLE }
								</a>
							</div>
						</td>
						<td><div>${board.SB_WRITER }</div></td>
						<td><div>${board.SB_DATE }</div></td>
						<td><div>${board.SB_READCOUNT }</div></td>
					</tr>
				</c:forEach>
				
				<tr class="h30 lime center btn">
					<td colspan="5">
						<c:if test="${page<=1 }">
							����&nbsp;
						</c:if>
						<c:if test="${page>1 }">
							<a href="./BoardList.sell?page=${page-1 }">����</a>&nbsp;
						</c:if>
						
						<c:forEach var="a" begin="${startpage }" end="${endpage }">
							<c:if test="${a==page }">
								${a }
							</c:if>
							<c:if test="${a!=page }">
								<a href="./BoardList.sell?page=${a }">${a }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${page>=maxpage }">
							&nbsp;����
						</c:if>
						<c:if test="${page<maxpage }">
							&nbsp;<a href="./BoardList.sell?page=${page+1 }">����</a>
						</c:if>
						
					</td>
				</tr>
				
			</c:if>
			
			
			<c:if test="${listcount==0}">
				<tr>
					<td colspan="4">�Ǹ� �Խ���</td>
					<td style="text-align:right;"><font style="margin-right:15px;" size=2>��ϵ� ���� �����ϴ�.</font></td>
				</tr>
			</c:if>
				<tr>
					<td colspan="5" style="text-align:right; font-size: 14pt;"><a style="margin-right:10px;" href="./BoardWrite.sell">[�۾���]</a></td>
				</tr>
		</table>
	</body>
</html>