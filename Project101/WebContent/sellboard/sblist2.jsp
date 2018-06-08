<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>�Ǹ� �Խ���</title>
        <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        
        <script type="text/javascript">
	        $(document).ready(function(){
	        	$('#a_write').click(function(){
	        		var id=<%=session.getAttribute("id")%>
	        		if(id==null){
	        			alert('�α��� �� �̿��Ͻ� �� �ֽ��ϴ�.');
	        			location.href='./signin.me';
	        			return false;
	        		}
	        	});
	        	/* $('#search_btn').click(function(){
	        		var word=$('input[name=search_input').val();
	        		var item=$('#search_sel').val();
	        		location.href='./sbmain.sb?word='+word+'&item='+item;
	        	}); */
	        });
        </script>
		<link href="css/sblist.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<c:set var="b_p" value="${boardPageBean }"/>
		<table class="sellboard_table">
			<c:if test="${b_p.listcount>=1}">
			<thead>
				<tr>
					<th colspan="3">�Ǹ� �Խ��� - list</th>
					<th colspan="2">�� ���� : ${b_p.listcount }</th>
				</tr>
				<tr>
					<th width="8%">��ȣ</th>
					<th width="50%">����</th>
					<th width="14%">�ۼ���</th>
					<th width="17%">��¥</th>
					<th width="11%">��ȸ��</th>
				</tr>
			</thead>	
				
				<c:set var="num" value="${b_p.listcount-(b_p.page-1)*b_p.limit }"/>
				<tbody>
					<c:forEach var="boardBean" items="${b_p.boardBeanList }">
						<tr>
							<td width="8%">
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td width="50%" align="left">
								&nbsp;<a href="./sbview.sb?num=${boardBean.SB_NO }">
									${boardBean.SB_TITLE }
								</a>
							</td>
							<td width="14%">${boardBean.SB_WRITER }</td>
							<td width="17%">${boardBean.SB_DATE }</td>
							<td width="11%">${boardBean.SB_READCOUNT }</td>
						</tr>
					</c:forEach>
				
				
				<tr>
					<td colspan="5">
						<c:if test="${b_p.page<=1 }">
							����&nbsp;
						</c:if>
						<c:if test="${b_p.page>1 }">
							<a href="./sbmain.sb?page=${b_p.page-1 }">����</a>&nbsp;
						</c:if>
						
						<c:forEach var="a" begin="${b_p.startpage }" end="${b_p.endpage }">
							<c:if test="${a==b_p.page }">
								${a }
							</c:if>
							<c:if test="${a!=b_p.page }">
								<a href="./sbmain.sb?page=${a }&word=${b_p.searchWord}&item=${b_p.searchItem}">${a }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${b_p.page>=b_p.maxpage }">
							&nbsp;����
						</c:if>
						<c:if test="${b_p.page<b_p.maxpage }">
							&nbsp;<a href="./sbmain.sb?page=${b_p.page+1}&word=${b_p.searchWord}&item=${b_p.searchItem}">����</a>
						</c:if>
						
					</td>
				</tr>
				</tbody>
				
			</c:if>
			
			
			<c:if test="${b_p.listcount==0}">
			<thead>
				<tr>
					<td colspan="5">�Ǹ� �Խ���</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5">
						<font style="margin-right:15px;" size=2>��ϵ� ���� �����ϴ�.</font>
					</td>
				</tr>
			</tbody>
			</c:if>
			<tfoot>
				<tr>
					<td colspan="5" style="text-align:right; font-size: 14pt;">
						<a style="margin-right:10px;" href="./sbwriteview.sb" id="a_write">�۾���</a>
					</td>
				</tr>
			</tfoot>
		</table>
		<!-- <div class="search">
			<select id="search_sel">
			    <option value="title" selected="selected">����</option>
			    <option value="content">����</option>
			    <option value="title_content">����+����</option>
			</select>
			<input type="text" name="search_input" placeholder="Search..">
			<button id="search_btn">�˻�</button>
		</div> -->
	</body>
</html>