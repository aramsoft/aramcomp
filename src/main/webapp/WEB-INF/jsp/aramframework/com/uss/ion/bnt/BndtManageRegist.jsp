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
 * @Class Name : BndtManageRegist.jsp
 * @Description : 당직 등록
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
<title>당직 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직  등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<form:form commandName="bndtManageVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="당직 등록">
<caption>당직 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="bndtId">당직자명</label>
    	</th>          
    	<td width="80%">
      		<input name="bndtIdName" id="bndtIdName" type="text" title="당직자명" readonly/>
      		<form:hidden path="bndtId"/>
			<a href="/uss/ion/ism/listSanctnerPopup.do" target="_blank"  title="새 창으로 이동"  onClick="fn_aram_get_charger('당직 대상자', 'bndtId', '', 'bndtIdName', 'orgnztNm'); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="당직" title="당직">
			</a>
       		<form:errors path="bndtId" cssClass="error"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="orgnztNm">소속</label>
    	</th>
    	<td>
        	<input name="orgnztNm" id="orgnztNm" type="text" title="소속" class="readOnlyClass" readonly>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="bndtDe">당직일자</label>
    	</th>          
    	<td>
      		<form:hidden path="bndtDe" />
	    	<c:if test="${!empty bndtManageVO.bndtDe}">
 				<c:set var="bndtDeVal" value="${fn:substring(bndtManageVO.bndtDe, 0,4)}-${fn:substring(bndtManageVO.bndtDe, 4,6)}-${fn:substring(bndtManageVO.bndtDe, 6,8)}"/>
      		</c:if>
      		<input name="bndtDeView" id="bndtDeView" type="text" size="10" title="당직일자" value="${bndtDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].bndtDe, document.forms[0].bndtDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="bndtDe" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr> 
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="remark">비고</label>
    	</th>
    	<td>
      		<form:textarea path="remark" rows="4" cols="70" cssClass="txArea" title="비고" />
      		<form:errors path="remark" cssClass="error"/>
    	</td>    
  	</tr>     
</table>

<!-- 검색조건 유지 -->
<form:hidden path="year" />
<form:hidden path="month" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bndtManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("bndtManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtManage.do";
	varForm.submit();   
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("bndtManageVO");
 
    if(!validateBndtManageVO(varForm)){           
        return;
    }

	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/insertBndtManage.do";
        varForm.submit();
    }
}

var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
* ******************************************************** */
function fn_aram_get_charger(strTitle, uniqId, emplNo, emplyrNm, orgnztNm){
	gArguments["title"]    = strTitle;
	if( uniqId != "" )   gArguments["uniqId"]   = document.getElementById(uniqId);
	if( emplNo != "" )   gArguments["emplNo"]   = document.getElementById(emplNo);
	if( emplyrNm != "" ) gArguments["emplyrNm"] = document.getElementById(emplyrNm);
	if( orgnztNm != "" ) gArguments["orgnztNm"] = document.getElementById(orgnztNm);

	var url = "/uss/ion/ism/listSanctnerPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

</body>
</html>
