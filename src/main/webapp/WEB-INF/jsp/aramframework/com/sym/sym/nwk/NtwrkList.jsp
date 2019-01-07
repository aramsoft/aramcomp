<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NtwrkList.jsp
 * @Description : 네트워크 목록
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
	<h2>네트워크 목록</h2>
</div>

<form:form commandName="ntwrkVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type=hidden name="ntwrkId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
 		<label for="strManageIem">관리항목 : </label>
  		<form:select path="strManageIem">
	   		<form:option value='00' label="전체" />
            <form:options items="${COM067_manageIem}" itemValue="code" itemLabel="codeNm"/>
   		</form:select>	   
 		<label for="strUserNm">사용자 명 : </label>
    	<form:input path="strUserNm" size="15" maxlength="25" title="검색어 입력" onkeypress="press();"/> 
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="네트워크에 대한 목록을 제공한다.">
<caption>네트워크 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">네트워크ID</th>
	    <th scope="col"            >IP</th>
	    <th scope="col" width="12%">관리항목</th>
	    <th scope="col" width="15%">사용자</th>
	    <th scope="col" width="10%">사용여부</th>
	    <th scope="col" width="15%">등록일자</th>
  	</tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr>
        <td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
    </tr>
    </c:if>
    
 	<c:set var="searchVO" value="${ntwrkVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('${result.ntwrkId}'); return false;">
  	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">${result.ntwrkId}</td>
	    <td class="lt_text3"><c:out value="${result.ntwrkIp}"/></td>
	    <td class="lt_text3"><c:out value="${result.manageIem}"/></td>
	    <td class="lt_text3"><c:out value="${result.userNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.useAt}"/></td>
	    <td class="lt_text3">
	    	<c:out value="${fn:substring(result.regstYmd, 0, 4)}-${fn:substring(result.regstYmd, 4, 6)}-${fn:substring(result.regstYmd, 6, 8)}"/>
	    </td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("ntwrkVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/listNtwrk.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("ntwrkVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/listNtwrk.do";
    varForm.submit();
}

function fn_aram_detail(ntwrkId) {
    var varForm = document.getElementById("ntwrkVO");
    varForm.ntwrkId.value = ntwrkId;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/detailNtwrk.do";
    varForm.submit();   
}

function fn_aram_regist() {
    var varForm = document.getElementById("ntwrkVO");
    varForm.ntwrkId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/registNtwrk.do";
    varForm.submit(); 
}

</script>
