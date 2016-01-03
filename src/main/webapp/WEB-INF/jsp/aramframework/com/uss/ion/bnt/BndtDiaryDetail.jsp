<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : BndtDiaryDetail.jsp
 * @Description : 당직일지 상세조회
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
<title>당직일지 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직일지 상세</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bndtDiaryVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bndtId" />
<form:hidden path="bndtDe" />

<!-- ------------------------------------------------------------------ 등록  폼 영역  -->
<table class="table-list" summary="당직일지 상세">
<caption>당직일지 상세</caption>
<thead>
	<tr>  
		<th width="40%" scope="col">당직체크목록</th>
		<th width="30%" scope="col">양호</th>
		<th width="30%" scope="col">불량</th>
	</tr>
</thead>     
<tbody>
	<c:forEach items="${bndtDiaryList}" var="result" varStatus="status">
	<input name="bndtCeckCd" type="hidden" value="<c:out value='${result.bndtCeckCd}'/>"/>
	<input name="bndtCeckSe" type="hidden" value="<c:out value='${result.bndtCeckSe}'/>"/>
	<tr>
		<td class="lt_text3"><c:out value="${result.bndtCeckCdNm}"/></td>
		<c:if test="${result.bndtCeckSe == '99'}">
	    <td class="lt_text3"  colspan=4>
	        <input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="text" size="70" value="<c:out value='${result.chckSttus}'/>" maxlength="2000" style="width:90%;" title="<c:out value="${result.bndtCeckCdNm}"/>"  readOnly>
	    </td>
		</c:if>
		<c:if test="${result.bndtCeckSe != '99'}">
		<c:if test="${result.chckSttus == '1'}">
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="1" title="양호"   disabled checked></td>
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="2" title="불량"   disabled></td>
		</c:if>
		<c:if test="${result.chckSttus == '2'}">
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="1" title="양호"   disabled></td>
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="2" title="불량"   disabled checked></td>
		</c:if>
		</c:if>
	</tr>   
	</c:forEach>
</tbody>  
</table>

<!-- 검색조건 유지 -->
<form:hidden path="year" />
<form:hidden path="month" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bndtDiary" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("bndtDiaryVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtManage.do";
	varForm.submit();   
}

/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit() {
	var varForm = document.getElementById("bndtDiaryVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/editBndtDiary.do";
	varForm.submit();   
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete() {
	var varForm = document.getElementById("bndtDiaryVO");
	
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/deleteBndtDiary.do";
        varForm.submit();
    }
}

</script>

</body>
</html>