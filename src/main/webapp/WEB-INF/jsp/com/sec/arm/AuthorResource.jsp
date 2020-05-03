<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AuthorResource.java
 * @Description : 접근자원 권한관리
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
 *
 */
%>
<DIV id="main">

<div class="content_title">
	<h2>접근자원 권한관리</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle;" alt="도움말아이콘이미지">
	</a>
</div>

<form:form modelAttribute="authorResourceVO" action="${pageContext.request.contextPath}/sec/arm/listAuthorResource.do" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="regYns"/>

<div id="search_area">
	<div class="search_right">
		<span class="keyword_area">
	 		권한코드 : 
	   		<form:input path="authorCode" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.create" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list_author(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>	
</div>

<table class="table-list" id="tblData" summary="접근자원을 관리하는 테이블입니다.접근자원ID,접근자원명,접근자원타입,접근자원Sort,접근자원설명,등록일자,등록여부의 내용을 담고 있습니다.">
<thead>
  	<tr>
	    <th scope="col" width="3%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
	    </th>
	    <th scope="col" width="10%">접근자원 ID</th>
	    <th scope="col" width="20%">접근자원 명</th>
	    <th scope="col" width="10%">접근자원 타입</th>
	    <th scope="col" width="10%">접근자원 Sort</th>
	    <th scope="col" >접근자원 설명</th>
	    <th scope="col" width="12%">등록일자</th>
	    <th scope="col" width="10%" >등록여부</th>
  	</tr>
</thead>
<tbody>
 	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
	    <td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.resourceCode}" />
	    </td>
	    <td class="lt_text"><c:out value="${result.resourceCode}"/></td>
	    <td class="lt_text"><c:out value="${result.resourceNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.resourceTy}"/></td>
	    <td class="lt_text3"><c:out value="${result.resourceSort}"/></td>
	    <td class="lt_text3"><c:out value="${result.resourceDc}"/></td>
	    <td class="lt_text3"><c:out value="${result.creatDt}"/></td>
	    <td class="lt_text3">
        	<select id="regYn${result.resourceCode}" title="등록여부">
            	<option value="Y" <c:if test="${result.regYn == 'Y'}">selected</c:if>>등록</option>
            	<option value="N" <c:if test="${result.regYn == 'N'}">selected</c:if>>미등록</option>
        	</select>
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />

<form:hidden path="saveSearchKeyword" />
<form:hidden path="savePageIndex" />
<form:hidden path="saveRecordPerPage" />
</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript"  defer="defer">

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
    var varForm = document.getElementById("authorResourceVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthorResource.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("authorResourceVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthorResource.do";
    varForm.submit();
}

function fn_aram_insert() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}

	var regYns = "";
	$("#tblData input[name=uniqIds]:checked").each(function() {	
		regYns += ((regYns == "")?"":";") + $("#regYn"+this.value).val(); 
	});
	
    var varForm = document.getElementById("authorResourceVO");
    if(confirm("등록하시겠습니까?")) {
    	varForm.regYns.value = regYns;
    	varForm.action = "${pageContext.request.contextPath}/sec/arm/insertAuthorResource.do";
    	varForm.submit();
	} 
}

function fn_aram_list_author(){
    var varForm = document.getElementById("authorResourceVO");

    varForm.searchKeyword.value = varForm.saveSearchKeyword.value;
    varForm.pageIndex.value = varForm.savePageIndex.value;
    varForm.recordPerPage.value = varForm.saveRecordPerPage.value;
    
    varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthor.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sec:%EA%B6%8C%ED%95%9C%EB%B3%84_%EB%A1%A4%EA%B4%80%EB%A6%AC";	
	window.open(url, "도움말");
}

</script>
