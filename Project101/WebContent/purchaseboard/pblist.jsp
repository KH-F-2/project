<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>
	$(document).ready(function() {
		$('#searchBtn').click(function() { //검색버튼
			location.href = "./pbmain.pb?keyword=" + $('input[name="keyword"]').val()
					+ "&searchOption=" + $('select[name=searchOption]').val() + "&page=1";
			//엑션 실행 - 검색 값, 검색옵션 값, 1page고정값 가져옴
		});

		$('#back').click(function() { //이전페이지
			if ($('input[name="keyword"]').val() == "") {
				location.href = "./pbmain.pb?page="+ ${page - 1};
			} else {
				location.href = "./pbmain.pb?keyword=${keyword}&searchOption=${searchOption}&page=" + ${page - 1};
			}
			//이전페이지 검색값과 검색옵션값 같이 이동
		});

		$('#next').click(function() { //다음페이지
			if ($('input[name="keyword"]').val() == "") {
				location.href = "./pbmain.pb?page="+ ${page + 1};
			} else {
				location.href = "./pbmain.pb?keyword=${keyword}&searchOption=${searchOption}&page=" + ${page + 1};
			}
			//다음페이지 검색값과 검색옵션값 같이 이동
		});

		$('#writeBtn').click(function() { //글쓰기버튼
			location.href = "./pbwrite.pb";
		});
		$('#backBtn').click(function() { //검색 결과가 없을 경우 뒤로 가기 버튼
			location.href = "./pbmain.pb";
		});

	});
</script>

<style>
table {
	text-align: center;
	margin: 0 auto;
}
</style>

<!-- 게시판 리스트 -->
<table width="600px">
	<!-- 임시 사이즈부여 -->
	<c:choose>
		<c:when test="${listCount == 0 }">
			<tr>
				<td colspan="4">구매 게시판</td>
				<td style="text-align: right"><font size=2>등록된 글이 없습니다.</font>
				</td>
			</tr>
		<c:if test="${sessionScope.id != null }">
			<tr>
				<td colspan="5" style="text-align: right">
					<button id="writeBtn">글쓰기</button>
				</td>
			</tr>	
		</c:if>	
		<c:if test="${sessionScope.id == null }">
				<tr>
					<td colspan="5" align="center">
						<b>로그인 후 글 쓰기가 가능합니다.</b>
					</td>
				</tr>
		</c:if>
		
		</c:when>
		
		
		<c:when test="${searchResultCount == 0 }">
			<tr>
				<td colspan="4">구매 게시판</td>
				<td style="text-align: right"><font size=2>검색결과가 없습니다.</font>
				</td>
			</tr>
			
			<tr>
				<td colspan="5" style="text-align: right">
					<c:if test="${sessionScope.id != null }">
						<button id="writeBtn">글쓰기</button>
						<button id="backBtn">목록으로 돌아가기</button>
					</c:if>	
					<c:if test="${sessionScope.id == null }">
						<tr>
							<td colspan="5" align="center">
								<b>로그인 후 글 쓰기가 가능합니다.</b>
								<button id="backBtn">목록으로 돌아가기</button>
							</td>
						</tr>
					</c:if>	
				</td>
			</tr>
		</c:when>
	</c:choose>

	<c:if test="${searchResultCount > 0 }">
		<tr>
			<th colspan="3">구매 게시판</th>
			<th colspan="2"><font size=2>글 개수 : ${searchResultCount }</font></th>
		</tr>
		<tr>
			<th width="10%"><div>번호</div></th>
			<th width="30%"><div>제목</div></th>
			<th width="20%"><div>작성자</div></th>
			<th width="30%"><div>날짜</div></th>
			<th width="10%"><div>조회수</div></th>
		</tr>

		<c:set var="num" value="${listCount - (page - 1) * 10 }" />

		<c:forEach var="b" items="${boardBeanList}">
			<tr>
				<td>
					<c:out value="${b.PB_NO }" />
					<c:set var="num" value="${b.PB_NO - 1 }" /> <%--num=num-1 --%>
				</td>
				<td>
					<div>
						<a href="./pbview.pb?PB_NO=${b.PB_NO }">
						<c:out value="${b.PB_TITLE }" /></a>
					</div>
				</td>
				<td><c:out value="${b.PB_WRITER }" /></td>
				<td><c:out value="${b.PB_DATE }" /></td>
				<td><c:out value="${b.PB_READCOUNT }" /></td>
			</tr>
		</c:forEach>

			<c:if test="${sessionScope.id != null }">
				<tr>
					<td colspan="5" align="right">
						<button id="writeBtn">글쓰기</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${sessionScope.id == null }">
				<tr>
					<td colspan="5" align="center">
						<b>로그인 후 글 쓰기가 가능합니다.</b>
					</td>
				</tr>
			</c:if>

		<tr>
			<td colspan="5">
				<div>
					<select name="searchOption">
						<!-- 첫 선택옵션 값 고정 -->
						<option value="0"
							<c:if test= "${searchOption eq '0' }">selected</c:if>>작성자</option>
						<option value="1"
							<c:if test= "${searchOption eq '1' }">selected</c:if>>제목</option>
						<option value="2"
							<c:if test= "${searchOption eq '2' }">selected</c:if>>내용</option>
					</select>
					
					<input type="text" name="keyword" value="${keyword}">
					&nbsp;
					<button id="searchBtn">검색</button>
				</div>
			</td>
		</tr>


		<tr class="h30 lime center btn">
			<td colspan="5">
				<c:if test="${page <= 1 }">
					이전&nbsp;
				</c:if>
				<c:if test="${page>1 }">
					<a id="back">이전</a>
				</c:if>
				
				<c:forEach var="a" begin="${startPage }" end="${searchResultPage }">
					<c:if test="${a == page }">
						${a }
					</c:if>

					<c:if test="${a != page }">
						<c:choose>
							<c:when test="${empty keyword}">
								<a href="./pbmain.pb?page=${a }">${a }</a>
							</c:when>
							<c:otherwise>
								<a href="./pbmain.pb?page=${a }&keyword=${keyword}&searchOption=${searchOption}">${a }</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				
				<c:if test="${page >= searchResultPage }">
					&nbsp; 다음
				</c:if>
				<c:if test="${page < searchResultPage }">
					<a id="next">다음</a>
				</c:if>
			</td>
		</tr>

	</c:if>

</table>