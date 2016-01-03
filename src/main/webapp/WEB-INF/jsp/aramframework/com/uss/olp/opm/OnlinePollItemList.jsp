<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollItemList.jsp
  * @Description : 온라인POLL항목 목록
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
<c:set var="resultListCont" value="0"/>
<c:forEach items="${resultList}" var="result" varStatus="status">
	<c:set var="resultListCont" value="${resultListCont+1}"/>
</c:forEach>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>온라인POLL항목 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<BODY>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%;">

<div class="content_title">
	<h2>온라인POLL항목 목록</h2>
</div>

<form:form commandName="onlinePollItemVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pollId" />
<input name="pollIemId" type="hidden" value="">
<input name="registFormCmd" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
     	<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
	    <form:input path="pollIemNm" size="100" value="" maxlength="255" title="POLL 세부항목 입력" />
	</div>
</div>

</form:form>

<table class="table-list" >
<thead>
	<tr>
		<th scope="col"            >항목명</th>
		<th scope="col" width="10%">수정</th>
		<th scope="col" width="10%">삭제</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="3"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
		<td class="lt_text3">
			<c:out value="${result.pollIemNm}"/>
		</td>
		<td class="lt_text3">
			<span class="button">
			<a href="#" onClick="JavaScript:fn_aram_edit('${result.pollIemNm}','${result.pollIemId}'); return false;">
				수정
			</a>
			</span>
		</td>
		<td class="lt_text3">
			<span class="button">
			<a href="#" onClick="JavaScript:fn_aram_delete('${result.pollIemId}'); return false;">
				삭제
			</a>
			</span>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

//전역변수 설정
var ifr= parent.document.all? parent.document.all.PollIemView : parent.document.getElementById("PollIemView") ;

window.onload = function() {
	resizeFrame(1);
};

//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
function resizeFrame(re) {
	if(ifr){
		var innerHeight = document.body.scrollHeight + (document.body.offsetHeight - document.body.clientHeight);
		//if(ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
		//{ifr.style.height = innerHeight;}
		ifr.setAttribute("height",innerHeight);
	}
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("onlinePollItemVO");

	if(varForm.registFormCmd.value != 'updt'){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/insertOnlinePollItem.do";
		varForm.pollIemId.value = '';
	}

	if(confirm("<spring:message code="common.save.msg" />")){

		if( varForm.pollIemNm.value == ""){
			alert("온라인POLL 항목명을  입력해주세요!");
			varForm.pollIemNm.focus();
			return;
		}

		//중복된 항목명 체크
		if(!fn_aram_check_onlinePollItemList(varForm.pollIemNm.value)){
			alert("[" + varForm.pollIemNm.value + "]항목명이 중복 되었습니다!");
			varForm.pollIemNm.value = "";
			return;
		}

		varForm.submit();
	}
}

/* ********************************************************
* 삭제 처리 함수
******************************************************** */
function fn_aram_delete(pollIemId){
    var varForm = document.getElementById("onlinePollItemVO");

    varForm.pollIemId.value = pollIemId;

	if(confirm("삭제 하시겠습니까?")){
	    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/deleteOnlinePollItem.do";
		varForm.submit();
	}
}

/* ********************************************************
* 수정화면으로 전환 함수
******************************************************** */
function fn_aram_edit(pollIemNm, pollIemId){
    var varForm = document.getElementById("onlinePollItemVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/updateOnlinePollItem.do";
    varForm.pollIemNm.value = pollIemNm;
    varForm.pollIemId.value = pollIemId;
    varForm.registFormCmd.value = 'updt';

	document.getElementById('divPollIem').innerHTML = '수정';
}

/* ********************************************************
* 등록된 항목 유효성 검사
******************************************************** */
function fn_aram_check_onlinePollItemList(pollIemNm){

	var arrPollItemList = new Array(<c:forEach items="${resultList}" var="result" varStatus="status">'${result.pollIemNm}'<c:if test="${status.count != resultListCont}">,</c:if></c:forEach>);
	for(var i=0;i<arrPollItemList.length;i++){

		if(arrPollItemList[i].trim() == pollIemNm){
			return false;
		}
	}
	return true;
}

</script>

</BODY>
</HTML>
