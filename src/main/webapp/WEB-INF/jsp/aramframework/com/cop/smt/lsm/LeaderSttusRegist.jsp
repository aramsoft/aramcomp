<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : LeaderSttusRegist.jsp
 * @Description : 간부상태 등록
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
<title>간부상태 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body onload="fn_aram_init_LeaderSttus()">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>간부상태 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="leaderSttusVO"  action="" method="post" >
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="leaderId"/>

<table class="table-register" summary="이 표는 간부상태 정보를  등록하기 위한 표이며, 간부, 상태 정보로 구성되어 있습니다 .">
<caption>간부상태 등록</caption>
<tbody>
	<tr> 
		<th scope="row" width="20%">
	    	<span class="norequired_icon"></span>
			<label for="orgnztNm">부서명</label>
		</th>
		<td width="80%">
			<input type="text" id="orgnztNm" name="orgnztNm" size="15" maxlength="10" title="부서명" class="readOnlyClass" readOnly>
		</td>
	</tr>
	<tr> 
		<th scope="row" width="20%">
	    	<span class="required_icon"></span>
			<label for="leaderNm">간부명</label>
		</th>
		<td width="80%">
			<form:input path="leaderNm" size="15" readonly="true" maxlength="10" title="간부명"/>
			<a href="#" target="_blank"  title="새 창으로 이동"  onClick="fn_aram_schdulCharger('간부', 'leaderId', '', 'leaderNm', 'orgnztNm');return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="간부선택 검색" title="간부선택 검색">
			</a>
			<form:errors path="leaderNm" cssClass="error"/>
		</td>
	</tr>
	<tr> 
	    <th scope="row" width="20%">
	    	<span class="required_icon"></span>
	    	<label for="leaderSttus">간부상태</label>
	    </th>
	    <td width="80%">
			<form:select path="leaderSttus" title="간부상태">
	            <form:options items="${COM061_leaderSttus}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
			<form:errors path="leaderSttus" cssClass="error"/>
	    </td>
	</tr>
</tbody>
</table>
	
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="leaderSttusVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

window.onload = function() {
	
	if("${leaderIdDuplicated}" == "true"){
		alert("등록하려는 간부상태 정보가 이미 등록되어 있습니다.");
	}

	document.getElementsByName('leaderSttus')[0].checked = true;
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("leaderStatusVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSttus.do";
    varForm.submit();	
}	

function fn_aram_insert() {
    var varForm = document.getElementById("leaderStatusVO");

    if (!validateLeaderSttusVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/insertLeaderSttus.do";
		varForm.submit();					
	}
}

var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_aram_schdulCharger(strTitle, uniqId, emplNo, emplyrNm, orgnztNm){
	gArguments["title"]    = strTitle;
	if( uniqId != "" )   gArguments["uniqId"]   = document.getElementById(uniqId);
	if( emplNo != "" )   gArguments["emplNo"]   = document.getElementById(emplNo);
	if( emplyrNm != "" ) gArguments["emplyrNm"] = document.getElementById(emplyrNm);
	if( orgnztNm != "" ) gArguments["orgnztNm"] = document.getElementById(orgnztNm);

	var url = "/cop/smt/lsm/listEmplyrPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

</body>
</html>
