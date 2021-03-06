<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmyMenuList.jsp
  * @Description : 커뮤니티 메뉴 목록
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
	<h2>커뮤니티 메뉴 목록</h2>
</div>

<form:form modelAttribute="communityMenuVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="menuNm" value=""/>
<input type="hidden" name="trgetId" value="${curTrgetId}">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${communityMenuVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<label for="searchKeyword">메뉴명</label>
			<form:input path="searchKeyword" size="30" title="검색조건선택" onkeypress="javascript:press(event);"  />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
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
			<span class="button"><a href="#" onclick="javascript:fn_aram_excelDown(); return false;">다운로드</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_excelUp(); return false;">업로드</a></span>
	   		<span class="button"><a href="#" onclick="javascript:fn_aram_clearCache(); return false;">캐쉬청소</a></span>
		</span>
	</div>	
</div>

<table class="table-list" id="tblData" style="table-layout:fixed" summary="메뉴관리 목록 조회화면으로 메뉴ID,메뉴한글명,프로그램파일명,메뉴설명,상위메뉴ID로 구성.">
<caption>메뉴관리 목록 조회</caption>
<thead>
  	<tr>
	    <th scope="col" width="5%" >No.</th>
	    <th scope="col" width="5%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
	    </th>
	    <th scope="col" width="20%">메뉴명</th>
	    <th scope="col" width="15%">메뉴한글명</th>
	    <th scope="col" width="10%">메뉴위치</th>
	    <th scope="col" width="15%">프로그램파일명</th>
	    <th scope="col" >바로가기URL</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(communityMenuVO.pageIndex-1) * communityMenuVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${communityMenuVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.menuNm}" />
	    </td>
	    
	    <td class="lt_text">
		    <c:if test="${result.topMenuAt == 'N'}">
		    	<img src="${pageContext.request.contextPath}/images/com/cop/tpl/bull.gif" width="21" height="11" alt="bull" />
		    </c:if>
		    <c:out value="${result.menuNm}"/>
	    </td>
	    <td class="lt_text">
	   		<span class="link">
		   	<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.menuNm}"/>'); return false;">
	    		<c:out value="${result.menuKnm}"/>
	   		</a>
	   		</span>
	    </td>
	    <td class="lt_text"><c:out value="${result.menuPos}"/></td>
	    <td class="lt_text"><c:out value="${result.progrmFileNm}"/></td>
	    <td class="lt_text"><c:out value="${result.directUrl}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

$(function() {
	$("#checkAll").on("click", function(){
		if( $(this).is(":checked") ){
			$("#tblData input[name=uniqIds]").prop("checked", true);
		}else{
			$("#tblData input[name=uniqIds]").prop("checked", false); 
		}
	});
});

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("communityMenuVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("communityMenuVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function fn_aram_detail(menuNm) {
	var varForm = document.getElementById("communityMenuVO");
	varForm.menuNm.value = menuNm;
	varForm.action = "${pageContext.request.contextPath}/cop/cmy/editMenu.do";
	varForm.submit();
}

/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function fn_aram_regist() {
    var varForm = document.getElementById("communityMenuVO");
    varForm.menuNm.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/registMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 엑셀 다운로드 처리 함수
 ******************************************************** */
function fn_aram_excelDown(){
    var varForm = document.getElementById("communityMenuVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/downMenuExcel.do";
    varForm.submit();	
}

/* ********************************************************
 * 엑셀 업로드 처리 함수
 ******************************************************** */
function fn_aram_excelUp(){
    var varForm = document.getElementById("communityMenuVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/registMenuExcel.do";
    varForm.submit();	
}

/* ********************************************************
 * 캐쉬 청소 호출 함수
 ******************************************************** */
function fn_aram_clearCache() {
    var varForm = document.getElementById("communityMenuVO");
    varForm.menuNm.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/clearCacheMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fn_aram_deleteList() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
    var varForm = document.getElementById("communityMenuVO");
    if(confirm("삭제하시겠습니까?")) {
    	varForm.action = "${pageContext.request.contextPath}/cop/cmy/deleteListMenu.do";
    	varForm.submit();
    }	
}

</script>

