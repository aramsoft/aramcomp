<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : TroblReqstList.jsp
 * @Description : 장애신청 목록
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
	<h2>장애신청 목록</h2>
</div>

<form:form commandName="troblReqstVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type=hidden name="troblId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
 		장애명 : 
    	<form:input path="strTroblNm" size="20" maxlength="25" title="검색어 입력" onkeypress="press();"/> 
 		장애종류 : 
  		<form:select path="strTroblKnd">
	   		<form:option value='00' label="전체" />
            <form:options items="${COM065_troblKnd}" itemValue="code" itemLabel="codeNm"/>
   		</form:select>	   
 		처리상태 : 
  		<form:select path="strProcessSttus">
	   		<form:option value='00' label="전체" />
            <form:options items="${COM068_processSttus}" itemValue="code" itemLabel="codeNm"/>
   		</form:select>	   
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="장애신청에 대한 목록을 제공한다.">
<caption>장애신청 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">장애ID</th>
	    <th scope="col"            >장애명</th>
	    <th scope="col" width="18%">장애종류</th>
	    <th scope="col" width="20%">장애발생시간</th>
	    <th scope="col" width="10%">등록자</th>
	    <th scope="col" width="10%">처리상태</th>
  	</tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr>
        <td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
    </tr>
    </c:if>
    
 	<c:set var="searchVO" value="${troblReqstVO.searchVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('${result.troblId}'); return false;">
  	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">${result.troblId}</td>
	    <td class="lt_text3"><c:out value="${result.troblNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.troblKndNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.troblOccrrncTime}"/></td>
	    <td class="lt_text3"><c:out value="${result.troblRqesterNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.processSttusNm}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage"  />
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
    var varForm = document.getElementById("troblReqstVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/listTroblReqst.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("troblReqstVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/listTroblReqst.do";
    varForm.submit();
}

function fn_aram_detail(troblId) {
    var varForm = document.getElementById("troblReqstVO");
    varForm.troblId.value = troblId;
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/detailTroblReqst.do";
    varForm.submit();   
}

function fn_aram_regist() {
    var varForm = document.getElementById("troblReqstVO");
    varForm.troblId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/registTroblReqst.do";
    varForm.submit(); 
}

</script>
