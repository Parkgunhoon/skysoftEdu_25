<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<!-- <form action="/test/list.do"> -->
<form action="/test/search.do" name="input_form">
검색값 : <input id="title" name="search" method="post"/>
<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="search_list">
<caption>테스트과제 목록</caption>
<colgroup>
	<col width="15%" />
	<col width="*" />
	<col width="20%" />
</colgroup>
<thead>
<tr>
	<th scope="col">체크</th>
	<th scope="col">번호</th>
	<th scope="col">제목</th>
	<th scope="col">등록일</th>
</tr>
</thead>
<tbody>

<!-- 	<script>
		function check(){
    		cbox = input_form.chk;
    		if(cbox.length) {  // 여러 개일 경우
        		for(var i = 0; i<cbox.length;i++) {
            		cbox[i].checked=input_form.all.checked;
        		}

    		} else { // 한 개일 경우
        		cbox.checked=input_form.all.checked;
    		}
		}
	</script> -->



	<tr>
		<td><input type="checkbox" name="all" onclick="javascript:checkAll(document.getElementsByName('delList'), this.checked);"><br>전체</td>
		<td>번호</td>
		<td>제목</td>
		<td>등록일</td>
	</tr>
	<c:choose>
	<c:when test="${not empty contentLIst}">
	<c:forEach var="item" items="${contentLIst}" varStatus="x">
		<tr>
			<td><input type="checkbox" name="delList" value="${item.seq}"/></td>
			<td><c:out value="${item.seq }"/></td>
			<td><a href="/test/read.do?seq=${item.seq }&cPageNo=${srchVO.cPageNo}" ><c:out value="${item.title }"/></a></td>
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

<!-- 페이징 처리 -->
<div class="paging">
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




	</script>
	<body>
		<ui:pagination paginationInfo = "${paginationInfo}"
			type="text"
			jsFunction="linkPage"/>
	</body>
</div>
 <!-- 페이징 처리 -->

<div class="btn">
	<input type="button" value="검색" onclick="submit();return false;"/>
	<input type="button" value="등록" onclick="goInsert(<c:out value="${srchVO.cPageNo}"/>);return false;" />
	<input type="button" value="삭제" onclick="checkDel();">
</div>
</form>