<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
window.onload = function(){
	var result = $('#result').val();
	if(result!=""){
		if(result=="true"){
			alert("첫번째CELL(제목), 두번째CELL(내용)을 입력하세요.");
			return;
		}else {
			window.opener.location.href="/test/list.do";
			window.close();
			return;
		}

	}
};

function excelSave(){
	tableForm.submit();
}

function windowClose(){
	window.close();
}

</script>

<form id="tableForm" name="tableForm" action="/test/excelUpload.do" method="post" enctype="multipart/form-data">
<input type="hidden" id="result" name="result" value="${requestScope.result}">
<table border="1" class="search_list">
<tr>
	<td>엑셀업로드</td>
	<td><input type="file" id="file" name="file"></td>
</tr>
</table>
</form>
<input type="button" value="저장" onClick="excelSave()">
<input type="button" value="닫기" onClick="windowClose()">