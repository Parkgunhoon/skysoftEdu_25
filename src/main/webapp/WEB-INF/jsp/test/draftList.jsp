<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

</script>

<form action="/test/search.do" id="input_form" name="input_form" method="post" enctype="multipart/form-data">
<input type="hidden" id="cPageNo" name="cPageNo" value="${srchVO.cPageNo}">
<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="owl-">
<caption>테스트과제 목록</caption>

<thead>
<tr>
	<th>순번</th>
	<th >기안자</th>
	<th >기안등록일자</th>
	<th >결재유형</th>
	<th >제목</th>
	<th >결재단계</th>
	<th >상태</th>
</tr>
</thead>
<tbody>
	<c:choose>
	<c:when test="${not empty list}">
	<c:forEach var="item" items="${list}" varStatus="x">
		<tr>
			<td><c:out value="${item.seq}"/></td>
			<td><c:out value="${item.drafterNm}"/></td>
			<td><fmt:formatDate value="${item.regDtm }" pattern="yyyy/MM/dd"/></td>
			<td><c:out value="${item.draftTypeCd}"/></td>
			<td><!--  <a href="/test/read.do?seq=${item.seq}" style="text-decoration: none;">
				<c:out value="${item.title}"/></a> -->
				<c:out value="${item.drafterNm}"/>신청서
			</td>
			<td><c:out value="${item.stepCd}"/></td>
			<td><c:out value="${item.stateCd}"/></td>
		</tr>
	</c:forEach>
	</c:when>


	<c:otherwise>
		<tr><td colspan="3">검색된 결과가 없습니다</td></tr>
	</c:otherwise>
	</c:choose>

</tbody>
</table>
</form>

<div class="btn">
	<input type="button" value="검색" onclick="submit()">
	<input type="button" value="등록" onclick="goInsert(<c:out value="${srchVO.cPageNo}"/>);">
	<input type="button" value="삭제" onclick="checkDel()">
</div>
