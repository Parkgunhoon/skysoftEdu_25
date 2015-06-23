<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function checkAll(checkList, boolCheck) {
	checkedCount = checkList.length;

	if (typeof (checkedCount) != "undefined") {
		for ( var i = 0; i < checkedCount; i++) {
		checkList[i].checked = boolCheck;
		}
	}
}

function checkDel() {
	var chkFirList = document.getElementsByName('delList');
	var arrFir = new Array();
	var cnt = 0;
	var theForm = document.input_form;

	for ( var idx = chkFirList.length - 1; 0 <= idx; idx--) {
		if (chkFirList[idx].checked) {
			arrFir[cnt] = chkFirList[idx].value;
			cnt++;
		}
	}
	if (arrFir.length != 0) {
		if (confirm("선택하신 정말 삭제하시겠습니까??") == true) {
			//theForm.action = "/test/delete2.do?cPageNo=<c:out value="${vo.cPageNo}"/>";
			theForm.action = "/test/delete2.do";
			theForm.submit();
		}
	} else {
	alert('삭제할 **를 선택하세요.');
	return;
	}
}

/* ------------------------------------------------------ */
function linkPage(pageNo){
	location.href = "/test/list.do?cPageNo="+pageNo;
}
function goInsert(pageNo){
	location.href = "/test/insert.do?cPageNo="+pageNo;
}

function fileDownload(seq){

	$('#'+seq+'fileName').attr("name","realFileNm");
	input_form.action = "/test/download.do";
	input_form.submit();
	$('#'+seq+'fileName').attr("name",seq+"fileName");
}

function excelDownload(){
	input_form.action = "/test/excelDownload.do";
	input_form.submit();
}

function excelAllDownload(){
	location.href = "/test/excelAllDownload.do";
}

function excelupload(){

	window.open("/test/excelUploadView.do",
			    "excelUpload", "width=500, height=300, top=200, left=200, location=no, location=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,"
			   +"directories=no, directories=0, status=no"
			    );
	window.name = "parent";
}

function naverApi(){
	input_form.action = "/test/naverApi.do";
	input_form.submit();
}

</script>

<form action="/test/search.do" id="input_form" name="input_form" method="post" enctype="multipart/form-data">
<input type="hidden" id="cPageNo" name="cPageNo" value="${srchVO.cPageNo}">
검색값 : <input id="title" name="search"/>
네이버 도서검색 : <input id="searchQuery" name="searchQuery"/>
<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="owl-">
<caption>테스트과제 목록</caption>
<colgroup>
	<col width="15%" />
	<col width="15%" />
	<col width="*" />
	<col width="20%" />
	<col width="20%" />
</colgroup>
<thead>
<tr>
	<th scope="col"><input type="checkbox" name="all" onclick="javascript:checkAll(document.getElementsByName('delList'), this.checked);">전체</th>
	<th scope="col">번호</th>
	<th scope="col">제목</th>
	<th scope="col">대표파일</th>
	<th scope="col">등록일</th>
</tr>
</thead>
<tbody>
	<c:choose>
	<c:when test="${not empty contentLIst}">
	<c:forEach var="item" items="${contentLIst}" varStatus="x">
		<tr>
			<td><input type="checkbox" name="delList" value="${item.seq}"/></td>
			<td><c:out value="${item.seq}"/></td>
			<td><a href="/test/read.do?seq=${item.seq}" style="text-decoration: none;"><c:out value="${item.title}"/></a></td>
			<c:choose>
			<c:when test="${item.realFileNm!=null}">
				<td>
					<a href="javascript:fileDownload(${item.seq})" style="text-decoration: none;">파일받기</a>
					<input type="hidden" id="${item.seq}fileName" name="${item.seq}fileName" value="${item.realFileNm}">
				</td>
			</c:when>
			<c:otherwise>
				<td></td>
			</c:otherwise>
			</c:choose>
			<td><fmt:formatDate value="${item.regDtm }" pattern="yyyy/MM/dd"/></td>
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
<!-- 페이징 처리 -->
<div class="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
			type="text"
			jsFunction="linkPage"/>
</div>
<div class="paging">
	${pageUtilBean}
</div>
 <!-- 페이징 처리 -->

<div class="btn">
	<input type="button" value="검색" onclick="submit()">
	<input type="button" value="등록" onclick="goInsert(<c:out value="${srchVO.cPageNo}"/>);">
	<input type="button" value="삭제" onclick="checkDel()">
	<input type="button" value="엑셀다운로드" onclick="excelDownload()">
	<input type="button" value="전체다운로드" onclick="excelAllDownload()">
	<input type="button" value="엑셀업로드" onclick="excelupload()">
	<input type="button" value="도서검색" onclick="naverApi()">
</div>
