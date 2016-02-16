<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrRespondDetail.jsp
  * @Description : 응답자정보 상세조회
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>응답자정보 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrRespondManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrRespondId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="이 표는 응답자정보를 제공하며, 설문관리정보, 성별, 직업, 생년월일, 응답자명, 전화번호 정보로 구성되어 있습니다 .">
  	<tr>
    	<th width="20%">
    		설문관리정보
    	</th>
    	<td width="80%">
			<c:out value="${fn:replace(qustnrRespondManageVO.qestnrSj , crlf , '<br/>')}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		성별
    	</th>
    	<td>
			<c:forEach items="${COM014_sexdstn}" var="comCodeList" varStatus="status">
			<c:if test="${comCodeList.code eq qustnrRespondManageVO.sexdstnCode}">
			<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		직업
    	</th>
    	<td>
			<c:forEach items="${COM034_occpType}" var="comCodeList" varStatus="status">
			<c:if test="${comCodeList.code eq qustnrRespondManageVO.occpTyCode}">
			<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		생년월일
    	</th>
    	<td>
     		${fn:substring(qustnrRespondManageVO.brth, 0, 4)}-${fn:substring(qustnrRespondManageVO.brth, 4, 6)}-${fn:substring(qustnrRespondManageVO.brth, 6, 8)}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		응답자명
    	</th>
    	<td>
			<c:out value="${fn:replace(qustnrRespondManageVO.respondNm , crlf , '<br/>')}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		전화번호
    	</th>
    	<td>
			${qustnrRespondManageVO.areaNo}-${qustnrRespondManageVO.middleTelno}-${qustnrRespondManageVO.endTelno}
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrRespondManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qrm/listQustnrRespond.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("qustnrRespondManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qrm/editQustnrRespond.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("qustnrRespondManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qrm/deleteQustnrRespond.do";
		varForm.submit();
	}
}

</script>
