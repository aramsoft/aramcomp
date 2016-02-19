<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : RssManageList.jsp
  * @Description : RSS서비스관리 목록
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
	<h2>RSS서비스관리 목록</h2>
</div>

<form:form commandName="rssManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="rssId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
		<label for="searchCondition"> </label>
     	<form:select path="searchVO.searchCondition" title="조회조건 선택" class="select" >
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="TRGET_SVC_NM" label="대상서비스명" />			   
	   		<form:option value="TRGET_SVC_TABLE" label="대상테이블명" />			   
	   		<form:option value="HDER_TITLE" label="헤더TITLE" />			   
	   		<form:option value="HDER_LINK" label="헤더LINK" />			   
	   		<form:option value="HDER_DESCRIPTION" label="헤더DESCRIPTION" />			   
	   		<form:option value="HDER_TAG" label="헤더TAG" />			   
	   		<form:option value="HDER_ETC" label="헤더ETC" />			   
	   		<form:option value="BDT_LINK" label="본문LINK" />			   
	   		<form:option value="BDT_DESCRIPTION" label="본문DESCRIPTION" />			   
	   		<form:option value="BDT_TAG" label="본문TAG" />			   
	   		<form:option value="BDT_ETC" label="본문ETC" />			   
   		</form:select>
   		
		<label for="searchKeyword"> </label>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />

<!-- 목록  -->
<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
  	<tr> 
	    <th scope="col" width="7%" >No.</th>
	  	<th scope="col" width="5%" >
	  		<input type="checkbox" name="checkAll" id="checkAll" title="전체선택" value="1" onClick="fn_aram_checkAll();">
	  	</th> 
	    <th scope="col"            >대상서비스명</th>
	    <th scope="col" width="20%">대상테이블명</th>
	    <th scope="col" width="10%">작성자</th>
	    <th scope="col" width="15%">등록일자</th>                 
  	</tr>
</thead> 
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(rssManageVO.searchVO.pageIndex-1) * rssManageVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.rssId}'); return false;">
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${rssManageVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<input type="checkbox" name="checkList" title="선택" value="${result.rssId}">
		</td>

		<td class="lt_text3L"><c:out value="${result.trgetSvcNm}"/></td>
		<td class="lt_text3L"><c:out value="${result.trgetSvcTable}"/></td>
		<td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>  
</table>

</form:form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("rssManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/listRssManage.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("rssManageVO");
	
	if( varForm.searchCondition.selectedIndex == 0){
		alert("검색조건을 선택 해주세요!");
		varForm.searchCondition.focus();
		return;
	}
	
	if( varForm.searchKeyword.value == ""){
		alert("검색어를 입력 해주세요!");
		varForm.searchKeyword.focus();
		return;
	}  
	
    varForm["searchVO.pageIndex"].value = "1";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/listRssManage.do";
	varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(rssId){
    var varForm = document.getElementById("rssManageVO");
    varForm.rssId.value = rssId; 
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/detailRssManage.do";
    varForm.submit();
}

/* ********************************************************
 * 등록화면 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("rssManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/registRssManage.do";
    varForm.submit();
}

/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_aram_deleteList(){
    var varForm = document.getElementById("rssManageVO");

	if(fn_aram_delCnt() == 0){
		alert("삭제할 목록을 선택해주세요!   "); 
		document.getElementById('checkAll').focus();return;
	}
	
	if(confirm("선택된 정보를 삭제 하시겠습니까?")){
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/deleteListRssManage.do";
	    varForm.submit();
	}
}

/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_aram_checkAll(){
    var varForm = document.getElementById("rssManageVO");

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		varForm.checkList.checked = checkAllValue;
	} else {
		for(var i=0; i < FLength; i++) {
			document.getElementsByName("checkList")[i].checked = checkAllValue;	
		}
	}
		
}

/* ********************************************************
* 체크 박스 선태 건수 
******************************************************** */
var g_nDelCount  = 0;
function fn_aram_delCnt(){
    var varForm = document.getElementById("rssManageVO");

	g_nDelCount = 0;
	var FLength = document.getElementsByName("checkList").length;
	
	//undefined
	if( FLength == 1){
		if(varForm.checkList.checked == true){g_nDelCount++;}
	} else {
		for(var i=0; i < FLength; i++) {
			if(document.getElementsByName("checkList")[i].checked == true){g_nDelCount++;}
		}
	}
	return g_nDelCount;
}

</script>
