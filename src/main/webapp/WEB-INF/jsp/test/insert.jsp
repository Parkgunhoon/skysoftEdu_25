<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="input" action="/test/insertProc.do" method="post">
<input type="hidden" name="cPageNo" value="<c:out value="${vo.cPageNo}"/>">
<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="search_list">
<caption>테스트과제 목록</caption>
<colgroup>
	<col width="15%" />
	<col width="*" />
	<col width="20%" />
</colgroup>
<thead>
<tr>
	<th scope="col">제목</th>
	<th scope="col"><input id="title" name="title" size="50" maxlength="300"></th>
</tr>
</thead>
<tbody>
   		<tr>
			<td>내용</td>
			<td><textarea id ="contents" name="contents"></textarea></td>
		</tr>
</tbody>
</table>

<!-- 페이징 처리 -->
<div class="paging">
</div>
 <!-- 페이징 처리 -->

<div class="btn">
	<input type="button" value="저장" onclick="submit();return false;">
	<input type="button" value="목록" onclick="javascript:document.location.href='/test/list.do?cPageNo='+<c:out value="${vo.cPageNo}"/>">
</div>
</form>