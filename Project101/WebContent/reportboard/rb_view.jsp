<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script type="text/javascript">
			var id='${sessionScope.id}';
			
				function deleteConfirm(){
				   	ans=confirm("삭제하시겠습니까?");
				   	if(ans){
				   		location.href="<c:url value='/rbdelete.rb?num=${boardbean.RB_NO}'/>";
				  	return;
				   	}
					alert("삭제가 취소됨!!");
				}
        </script>
        
<style>
			.rbview_ul{
			   margin: 0 auto;
			   width: 70%;
			   font-family: "fontello";
			   border: solid 1px #cdd2d2;
			   border-radius: 10px;
			   padding: 0;
			   min-height: 800px;
			   position: relative;
			}
			.rbview_li{
			   list-style: none;
			   width: 100%;
			   padding: 10px 30px;
			   border-bottom: 1px solid #cdd2d2;
			   box-sizing: border-box;
			}
			.rbview_li_last{
			   list-style: none;
			   width: 100%;
			   padding: 10px 30px;
			}
			
			.rbview_li div{
			   -webkit-box-sizing: border-box;
			           -moz-box-sizing: border-box;
			   box-sizing: border-box;
			}
			
			.title{
			   width: 75%;
			   font-weight: bold;
			   font-size: 21pt;
			   text-align: left;
			   line-height: 1.5;
			   display: inline-block;
			   float: left;
			}
			.date{
			   width: 25%;
			   text-align: right;
			   color: gray;
			   font-size: 10pt;
			   display: inline-block;
			   clear: left;
			}
			
			
			.writer{
			   margin-top: 10px;
			   font-weight: bold;
			   font-size: 16pt;
			}
			.a_writer{
				color: black;
			}
			.price{
			   display: inline-block;
			   color: #007ee6;
			   font-weight: bold;
			}
			.btn_li{
			    position: absolute;
			    bottom: 10px;
			    width: 100%;
			    text-align: center;
			    margin-bottom: 10px;
			}
			.content{
				margin-top: 2em;
			}
			.menu_bar{
			   text-align: right;
			   width: 70%;
			   margin: 0 auto;
			}
			.menu_bar>ul{
				list-style: none;
			}	
			.menu_bar>ul>li>a{
				text-decoration: none;
			}		
		</style>


<%-- <table>
	<tr class="center">
		<th colspan="2">신고 게시판 - view 페이지</th>
	</tr>
	<tr>
		<td>신고 대상 </td>
		<td>${boardbean.RB_RP_ID }
		</td>
	</tr>
	<tr>
		<td>신고 글 번호 </td>
		<td>${boardbean.RB_RP_NO }
		</td>
	</tr>
	<tr>
		<td><div>제목</div></td>
		<td><div>${boardbean.RB_TITLE}</div></td>
	</tr>
	<tr>
		<td><div>내용</div></td>
		<td><div>${boardbean.RB_CONTENT }</div></td>
	</tr>
	<tr class="center">
		<td colspan="2">
			<c:if test="${id=='admin'||id==reportboard. B_WRITER}"> <a
			href="<c:url value='/rbmodifyview.rb?RB_NO=${boardbean.RB_NO}'/>">
				<input type="button" value="수정">
		</a>&nbsp; <a
			href="<c:url value='/rbdelete.rb?RB_NO=${boardbean.RB_NO}'/>"> <input
				type="button" value="삭제">
		</a>&nbsp; </c:if> <a href="<c:url value='/rbmain.rb'/>"> <input
				type="button" value="목록">
		</a>&nbsp;
	</tr>
</table> --%>



<ul class="rbview_ul">

<li class="rbview_li 1">
	<div class="title">
		${boardbean.RB_TITLE}
	</div>
	<div class="date">
		글번호${boardbean.RB_NO}<input type="hidden" id="rb_no" value="${boardbean.RB_NO}"><br>
		등록 ${boardbean.RB_DATE}
	</div>
</li>

<li class="rbview_li 2">
	
	
	<div class="writer">
		<input type="hidden" id="rb_rp_id" value="${boardbean.RB_RP_ID }">
		신고 대상 : ${boardbean.RB_RP_ID } <br>
		<input type="hidden" id="rb_rp_no" value="${boardbean.RB_RP_NO }">
		신고 글 번호 : ${boardbean.RB_RP_NO }
	</div>
	
	
	
</li>


<li class="rbview_li_last 4">
	<div class="content">
		${boardbean.RB_CONTENT }
		</div>
	</li>
	
</ul>

<div class="menu_bar">
	<ul>
		<li>
			<c:if test="${id=='admin'||id==boardbean.RB_WRITER}">
	    	<a href="<c:url value='/rbmodifyview.rb?RB_NO=${boardbean.RB_NO}'/>">
				<img src="image/update.png">
			</a> &nbsp;
			<a href="javascript:deleteConfirm()">
				<img src="image/delete.png">
			</a> &nbsp;
		<a href="<c:url value='/rbmain.rb'/>"><img src="image/list.png"></a>
		</c:if>
		</li>
	</ul>
</div>