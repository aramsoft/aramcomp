<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : DeptAuthor.java
  * @Description : 부서 권한 관리
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>부서 권한 관리</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle;" >
	</a>
</div>

<form:form modelAttribute="deptAuthorVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="authorCodes"/>
<input type="hidden" name="regYns"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insertList(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
 		부서  : 
 		<form:input path="deptCode" size="22" title="부서코드" onkeypress="press();" readonly="true" />
        <form:input path="deptNm" size="17" title="부서명" onkeypress="press();" readonly="true" />
  		<span class="button"><a href="#" onclick="javascript:fn_aram_get_dept(); return false;">부서조회 팝업</a></span>
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />

<table class="table-list" id="tblData" summary="부서 권한 관리 테이블입니다.사용자 ID,사용자 명,권한,등록 여부 정보를 담고 있습니다.">
<thead>
  	<tr>
		<th scope="col" width="7%">No.</th>
    	<th scope="col" width="5%">
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
    	</th>
    	<th scope="col" width="10%">사용자 ID</th>
    	<th scope="col"            >사용자 명</th>
    	<th scope="col" width="30%">권한</th>
    	<th scope="col" width="10%">등록 여부</th>
  	</tr>
</thead>
<tbody>
 	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(deptAuthorVO.pageIndex-1) * deptAuthorVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${deptAuthorVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.uniqId}" />
		</td>

	    <td class="lt_text3"><c:out value="${result.userId}"/></td>
	    <td class="lt_text3"><c:out value="${result.userNm}"/></td>
	    <td class="lt_text3">
	    	<select id="author${result.uniqId}" title="등록여부">
	        	<c:forEach var="author" items="${authorList}" varStatus="status">
	            <option value="<c:out value="${author.authorCode}"/>" <c:if test="${author.authorCode == result.authorCode}">selected</c:if>><c:out value="${author.authorNm}"/></option>
	        	</c:forEach>
	        </select>
	    </td>
	    <td class="lt_text3">
	    	<c:out value="${result.regYn}"/>
	    	<input type="hidden" id="regYn${result.uniqId}" value="<c:out value="${result.regYn}"/>">
	    </td>
	</tr>
	</c:forEach>
</tbody>
</table>

</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" defer="defer">

$(function() {
	$("#checkAll").on("click", function(){
		if( $(this).is(":checked") ){
			$("#tblData input[name=uniqIds]").prop("checked", true);
		}else{
			$("#tblData input[name=uniqIds]").prop("checked", false); 
		}
	});
});

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("deptAuthorVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDeptAuthor.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("deptAuthorVO");
	if(varForm.deptCode.value == '') {
		alert("부서를 선택하세요");
		return;
	}

	varForm.pageIndex.value = 1;
	varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDeptAuthor.do";
	varForm.submit();
}

function fn_aram_insertList() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}

	var authorCodes = "";
	var regYns = "";
	$("#tblData input[name=uniqIds]:checked").each(function() {	
		authorCodes += ((authorCodes == "")?"":";") + $("#author"+this.value).val(); 
		regYns += ((regYns == "")?"":";") + $("#regYn"+this.value).val(); 
	});

    var varForm = document.getElementById("deptAuthorVO");
    if(confirm("등록하시겠습니까?")) {
    	varForm.authorCodes.value = authorCodes;
    	varForm.regYns.value = regYns;
    	varForm.action = "${pageContext.request.contextPath}/sec/dpt/insertListDeptAuthor.do";
    	varForm.submit();
    }
}

function fn_aram_deleteList() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
    
    var varForm = document.getElementById("deptAuthorVO");
    if(confirm("삭제하시겠습니까?")) {
    	varForm.action = "${pageContext.request.contextPath}/sec/dpt/deleteListDeptAuthor.do";
    	varForm.submit();
    }
}

var gArguments = new Array();

/* ********************************************************
 * 부서  팝업창열기
 ******************************************************** */
function fn_aram_get_dept() {
 	var varForm = document.getElementById("deptAuthorVO");
	gArguments["deptCode"] = varForm.deptCode;
	gArguments["deptNm"] = varForm.deptNm;

	var url = "/sec/dpt/listDeptPopup.do";

	window.open(url, "p_deptInqire", "width=500px,height=485px,top=100px,left=100px,location=no");
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sec:" + encodeURIComponent("부서권한관리");	
	window.open(url, "도움말");
}

</script>
