<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="input" name="input" action="/test/naverApi.do" method="post" >
<input type="hidden" id="val" name="val" value="1">
<input type="hidden" id="searchQuery" name="searchQuery" value="${srchVO.searchQuery}">
<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="search_list">
<caption>테스트과제 목록</caption>

<colgroup>
	<col width="15%" />
	<col width="*" />
	<col width="20%" />
</colgroup>
<c:forEach var="item" items="${naverList}" varStatus="x">
<thead>
<tr>
	<th scope="col">제목</th>
	<th scope="col"><div style="width:600px; height:20px">${item.nTitle}</div></th>
</tr>
</thead>
<tbody>
   		<tr>
			<td>내용</td>
			<td><div style="width:600px; height:100px">${item.description}</div></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><div style="width:500px; height:120px"><img src="${item.image}"><a href="${item.link}">네이버링크</a></div></td>
		</tr>
</tbody>
</c:forEach>
</table>
</form>

<div class="btn">
	<input type="button" value="목록" onclick="javascript:document.location.href='/test/list.do'">
</div>

</body>
</html>