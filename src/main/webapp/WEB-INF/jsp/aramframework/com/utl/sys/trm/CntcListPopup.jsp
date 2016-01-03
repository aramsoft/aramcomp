<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : CntcListPopup.jsp
 * @Description : 연계 목록조회
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>연계 목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="title_left" style="width:100%;margin:0 0 0 0;">
	<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align:middle; display:inline-block;" alt="제목아이콘이미지">
	&nbsp;연계 조회
</div>

<form:form commandName="searchVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="CNTC_NM" label="연계명" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="등록된 연계정보에 대한 목록을 제공합니다.">
<thead>
  	<tr>
	    <th            >연계명</th>
	    <th width="15%">제공기관</th>
	    <th width="15%">제공시스템</th>
	    <th width="15%">제공서비스</th>
	    <th width="15%">요청기관</th>
	    <th width="15%">요청시스템</th>   
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
    <c:forEach items="${resultList}" var="result" varStatus="status">
    <tr class="link" onclick="fn_aram_choose('<c:out value="${result.cntcId}"/>', 
                						'<c:out value="${result.cntcNm}"/>', 
                                        '<c:out value="${result.provdInsttNm}"/>', 
                                        '<c:out value="${result.provdSysNm}"/>', 
                                        '<c:out value="${result.provdSvcNm}"/>', 
                                        '<c:out value="${result.requstInsttNm}"/>',
                                        '<c:out value="${result.requstSysNm}"/>'); return false;">
        <td class="lt_text3">${result.cntcNm}</td>
        <td class="lt_text3">${result.provdInsttNm}</td>
        <td class="lt_text3">${result.provdSysNm}</td>
        <td class="lt_text3">${result.provdSvcNm}</td>
        <td class="lt_text3">${result.requstInsttNm}</td>
        <td class="lt_text3">${result.requstSysNm}</td>
    </tr>   
    </c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

function press(event) {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("searchVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listCntcPopup.do";
    varForm.submit();  
}

function fn_aram_search() {
    var varForm = document.getElementById("searchVO");
    if (varForm.searchKeyword.value != "") {
        if (varForm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            return;
        }
    }
    varForm.pageIndex.value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listCntcPopup.do";
    varForm.submit();  
}

// 팝업검색 결과를 호출자에게 리턴하고 화면을 닫는다.
function fn_aram_choose(cntcId, cntcNm, provdInsttNm, provdSysNm, provdSvcNm, requstInsttNm, requstSysNm) {
	if( window.opener.gArguments["cntcId"] ) 		window.opener.gArguments["cntcId"].value 	= cntcId;
	if( window.opener.gArguments["cntcNm"] ) 		window.opener.gArguments["cntcNm"].value 	= cntcNm;
	if( window.opener.gArguments["provdInsttNm"] )  window.opener.gArguments["provdInsttNm"].innerHTML  = provdInsttNm;
	if( window.opener.gArguments["provdSysNm"] )    window.opener.gArguments["provdSysNm"].innerHTML    = provdSysNm;
	if( window.opener.gArguments["provdSvcNm"] )    window.opener.gArguments["provdSvcNm"].innerHTML    = provdSvcNm;
	if( window.opener.gArguments["requstInsttNm"] ) window.opener.gArguments["requstInsttNm"].innerHTML = requstInsttNm;
	if( window.opener.gArguments["requstSysNm"] )   window.opener.gArguments["requstSysNm"].innerHTML   = requstSysNm;
	window.close();
}
    
</script>

</body>
</html>