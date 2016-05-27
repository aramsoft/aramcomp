<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : DeviceIdentList.java
  * @Description : 모바일 기기 식별 목록
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
	<h2>모바일 기기 식별 목록</h2>			
</div>

<form:form commandName="deviceIdentVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="sn" type="hidden" value="0">

<div id="search_area">
	<div class="button_area">
    			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
    			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="BROWSER_CODE" label="브라우저" />			   
	   		<form:option value="OS_CODE" label="운영체제 " />			   
	   		<form:option value="RECENT_CODE" label="등록상태" />			   
   		</form:select>
		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="모바일 기기 식별 정보 목록을 제공한다.">
<thead>
	<tr>      
	    <th scope="col" width="15%">순번</th>        
	    <th scope="col" width="30%">브라우저</th>        
	    <th scope="col" width="30%">운영체제</th>               
	    <th scope="col" width="25%">등록상태</th> 
	</tr>
</thead>

<tbody>      
	<c:if test="${fn:length(resultList) == 0}">
  	<tr> 
    	<td class="lt_text3" colspan="10">
        	<spring:message code="common.nodata.msg" />
    	</td>
  	</tr>                                            
	</c:if>
    
    <c:set var="searchVO" value="${multimediaVO.searchVO}"/>
	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.sn}"/>'); return false;">
  
   		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
        <td class="lt_text3"><c:out value="${reverseIndex}"/></td>                 

        <td class="lt_text3"><c:out value="${result.browserNm}"/></td>
        <td class="lt_text3"><c:out value="${result.osNm}"/></td>
        <td class="lt_text3"><c:out value="${result.recentNm}"/></td> 
  	</tr>   
	</c:forEach>
</tbody>  
</table>

<div align="center">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>
			
</div>	
	
<script type="text/javaScript" >
	
/*********************************************************
 * 초기화
 ******************************************************** */
window.onload = function(){

    // 첫 입력란에 포커스..
    var varForm = document.getElementById("deviceIdentVO");
    varForm.searchKeyword.focus();
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("deviceIdentVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/listDeviceIdent.do";
    varForm.submit();
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("deviceIdentVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/listDeviceIdent.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(sn) {       
    var varForm = document.getElementById("deviceIdentVO");
    varForm.sn.value = sn;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/detailDeviceIdent.do";
    varForm.submit(); 
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("deviceIdentVO");
    varForm.sn.value = 0;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/registDeviceIdent.do";
    varForm.submit(); 
}

</script>
