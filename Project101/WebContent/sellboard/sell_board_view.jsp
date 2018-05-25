<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <script type="text/javascript">
        function deleteConfirm(){
        	ans=confirm("삭제하시겠습니까?");
        	if(ans){
        		location.href="<c:url value='/BoardDelete.sell?num=${sellboard.SB_NO}'/>";
		     	return;
        	}
        	alert("삭제가 취소됨!!");
        }
        </script>
        <style>
        	a{text-decoration: none;}
        	table{
        		margin: 0 auto;
        		width: 80%;
        	}
        	div{
        		height: 100%;
        		margin: 0px 20px 0px 20px;
        	}
        	.main_table{
        		height: 600px;
        		border: 0.5px solid #2aa1ff;
        		border-radius: 5px;
        		background-color: #e8e8e880;
        	}
        	.title{
        		font-weight: bold;
        		font-size: 18pt;
        		text-align: left;
        	}
        	.date{
        		text-align: right;
        		color: gray;
        		font-size: 10pt;
        	}
        	.price{
        		color: #008effad;
        	}
        	tr:last-child{
        		height: 60%;
			}
			.second_table tr{
				text-align: right;
			}
        </style>
	</head>
			<table class="main_table">
				<tr>
					<td width="75%"><div class="title">${sellboard.SB_TITLE}</div></td>
					<td width="25%">
						<div class="date">
							글번호 ${sellboard.SB_NO}<br>
							등록 ${sellboard.SB_DATE}<br>
							수정 ${sellboard.SB_MDATE }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="writer">${sellboard.SB_WRITER}</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pdate">
							구입일 ${sellboard.SB_PDATE }
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="price">${sellboard.SB_PRICE } 원</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><div class="content">${sellboard.SB_CONTENT }</div></td>
				</tr>
			</table>
			<table class="second_table">
				<tr>
					<td>
					    <c:if test="${id=='admin'||id==sellboard.SB_WRITER}">
					    	<a href="<c:url value='/BoardModifyView.sell?num=${sellboard.SB_NO}'/>">
								<img src="/Project101/sellboard/image/update.png">
							</a> &nbsp;
							<a href="javascript:deleteConfirm()">
								<img src="/Project101/sellboard/image/delete.png">
							</a> &nbsp;
						</c:if>
						<a href="<c:url value='/BoardList.sell'/>">
							<img src="/Project101/sellboard/image/list.png">
						</a>
					</td>
				</tr>
			</table>
	</body>
</html>