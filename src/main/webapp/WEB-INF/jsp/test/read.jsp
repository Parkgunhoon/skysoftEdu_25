<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function button_event() {
	var seq = $('#seq').val();
	if (confirm("정말 삭제하시겠습니까??") == true) {
		location.href = "/test/delete.do?seq="+seq;
	} else {
		return;
	}
}

function fileDownload(){
	input.action = "/test/download.do";
	input.submit();
}

function fileDelete(){
	input.action = "/test/fileDelete.do";
	input.submit();

}
function update(){
	var title = $('#title').val();
	var contents = $('#contents').val();

	if($.trim(title)=="" || $.trim(title)==null){
		alert("제목을 입력하세요");
		return;
	}
	if($.trim(contents)=="" || $.trim(contents)==null){
		alert("내용을 입력하세요.");
		return;
	}

	input.submit();

}

</script>


<form id="input" name="input" action="/test/update.do" method="post" enctype="multipart/form-data">
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
	<th scope="col"><input id="title" name="title" size="50" maxlength="300" value="<c:out value="${contentRead.title}"/>"></th>
</tr>
</thead>
<tbody>
   		<tr>
			<td>내용</td>
			<td><textarea id ="contents" name="contents"><c:out value="${contentRead.contents}"/></textarea></td>
		</tr>
		<tr>
		<c:choose>
		<c:when test="${requestScope.contentRead.realFileNm!=null}">
			<td>첨부파일</td>
			<td><a href="javascript:fileDownload()" style="text-decoration: none;">${requestScope.contentRead.realFileNm}</a>
				<input type="button" value="선택한파일 삭제" onClick="fileDelete()">
			</td>
		</c:when>
		<c:otherwise>
			<td>첨부파일</td>
			<td><input type="file" id="file" name="file"></td>
		</c:otherwise>
		</c:choose>
		</tr>
			<tr>
			<td>등록일</td>
			<td><fmt:formatDate value="${contentRead.regDtm}" pattern="yyyy/MM/dd"/></td>
		</tr>
</tbody>
</table>
<input type="hidden" id="realFileNm" name="realFileNm" value="${requestScope.contentRead.realFileNm}">
<input type="hidden" id="seq" name="seq" value="<c:out value="${contentRead.seq}"/>">
</form>

<div class="btn">
	<input type="button" value="수정" onclick="javascript:update()">
	<input type="button" value="삭제" onclick="button_event();">
	<input type="button" value="목록" onclick="javascript:document.location.href='/test/list.do?cPageNo='+<c:out value="${vo.cPageNo}"/>">
</div>
