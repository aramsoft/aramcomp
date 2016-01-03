<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : TrsmrcvMntrngList.jsp
 * @Description : 송수신모니터링 목록
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
	<h2>송수신모니터링 목록</h2>
</div>

<form:form commandName="trsmrcvMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="cntcId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="CNTC_NM" label="연계명" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
	   		<form:option value="MNTRNG_STTUS" label="상태" />			   
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

<table class="table-list" summary="송수신모니터링 목록 테이블">
<thead>
    <tr>
        <th scope="col" width="10%">연계ID</th>
        <th scope="col" width="15%">연계명</th>
        <th scope="col" width="27%">테스트클래스명</th>
        <th scope="col" width="10%">관리자명</th>
        <th scope="col" width="20%">관리자이메일주소</th>
        <th scope="col" width="18%">모니터링시각</th>   
    </tr>
    <tr>
        <th scope="col">제공기관</th>
        <th scope="col">제공시스템</th>
        <th scope="col">제공서비스</th>
        <th scope="col">요청기관</th>
        <th scope="col">요청시스템</th>
        <th scope="col">상태</th>   
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
    <tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.cntcId}"/>'); return false;">
    	<td class="lt_text3">${resultInfo.cntcId}</td>
        <td class="lt_text6"><c:out value="${result.cntcNm}"/></td>
        <td class="lt_text6">${result.testClassNm}</td>
        <td class="lt_text6">${result.mngrNm}</td>
        <td class="lt_text6">${result.mngrEmailAddr}</td>
        <td class="lt_text3">${result.creatDt}</td>
    </tr>   
    <tr>
        <td class="lt_text6">${result.provdInsttNm}</td>
        <td class="lt_text6">${result.provdSysNm}</td>
        <td class="lt_text6">${result.provdSvcNm}</td>
        <td class="lt_text6">${result.requstInsttNm}</td>
        <td class="lt_text6">${result.requstSysNm}</td>
        <td class="lt_text3">${result.mntrngSttusNm}</td>
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

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("trsmrcvMntrngVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrng.do";
    varForm.submit();  
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("trsmrcvMntrngVO");
    if (varForm.searchKeyword.value != "") {
        if (document.frm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            return;
        }
    }
    varForm.pageIndex.value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrng.do";
    varForm.submit();  
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(cntcId) {
    var varForm = document.getElementById("trsmrcvMntrngVO");
    varForm.cntcId.value = cntcId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/detailTrsmrcvMntrng.do";
    varForm.submit();          
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("trsmrcvMntrngVO");
    varForm.cntcId.value = '';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/registTrsmrcvMntrng.do";
    varForm.submit();  
}   

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){
	location.href = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrngLog.do";
}

</script>
