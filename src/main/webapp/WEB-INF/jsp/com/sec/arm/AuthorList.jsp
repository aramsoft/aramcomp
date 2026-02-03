<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : AuthorList.java
  * @Description : 권한 목록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>권한 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="authorVO" action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuNm" value="${curMenuNm}" />

<input type="hidden" name="authorCode"/>

<input type="hidden" name="saveSearchKeyword"/>
<input type="hidden" name="saveRecordPerPage"/>
<input type="hidden" name="savePageIndex"/>

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${authorVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		권한 명 : 
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
			<c:if test="${authorResourceReload=='true'}">
	   		<span class="button"><a href="#" onclick="javascript:fn_aram_clearCache(); return false;">캐쉬청소</a></span>
			</c:if>
		</span>
	</div>	
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />

<table class="table-list" id="tblData" summary="권한관리에  관한 테이블입니다.권한ID,권한 명,설명, 등록일자, 접근자원정보의 내용을 담고 있습니다.">
<thead>
  	<tr>
		<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="5%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
    	</th>
    	<th scope="col" width="15%">권한 ID</th>
    	<th scope="col" width="25%">권한 명</th>
    	<th scope="col"            >설명</th>
    	<th scope="col" width="15%">등록일자</th>
    	<th scope="col" width="10%">권한설정</th>
  	</tr>
</thead>
<tbody>
  	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(authorVO.pageIndex-1) * authorVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${authorVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>
		
    	<td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.authorCode}" />
    	</td>
    	
    	<td class="lt_text">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.authorCode}"/>'); return false;">
    			<c:out value="${result.authorCode}"/>
    		</a>
			</span>
    	</td>
    	<td class="lt_text"><c:out value="${result.authorNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.authorDc}"/></td>
    	<td class="lt_text3"><c:out value="${result.authorCreatDe}"/></td>
    	<td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_get_authorResource('<c:out value="${result.authorCode}"/>'); return false;">
    			<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="롤 정보">
    		</a>
			</span>
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
    var varForm = document.getElementById("authorVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthor.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("authorVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthor.do";
    varForm.submit();
}

function fn_aram_detail(author) {
    var varForm = document.getElementById("authorVO");
    varForm.authorCode.value = author;
    varForm.action = "${pageContext.request.contextPath}/sec/arm/editAuthor.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("authorVO");
    varForm.authorCode.value = "";
    varForm.action = "${pageContext.request.contextPath}/sec/arm/registAuthor.do";
    varForm.submit();
}

function fn_aram_clearCache() {
    var varForm = document.getElementById("authorVO");
    varForm.action = "${pageContext.request.contextPath}/sec/arm/clearCacheAuthor.do";
    varForm.submit();
}

function fn_aram_get_authorResource(author) {
    var varForm = document.getElementById("authorVO");

    varForm.saveSearchKeyword.value = varForm.searchKeyword.value;
    varForm.saveRecordPerPage.value = varForm.recordPerPage.value;
    varForm.savePageIndex.value = varForm.pageIndex.value;
    
    varForm.authorCode.value = author;
    varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthorResource.do";
    varForm.submit();
}

function fn_aram_deleteList() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}

	var varForm = document.getElementById("authorVO");
    if(confirm("삭제하시겠습니까?")) {
      	varForm.action = "${pageContext.request.contextPath}/sec/arm/deleteListAuthor.do";
       	varForm.submit();
    }
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sec:" + encodeURIComponent("권한관리");	
	window.open(url, "도움말");
}

</script>
