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
 * @Class Name : BndtCeckManageEdit.jsp
 * @Description : 당직체크 수정
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
<title>당직체크 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직체크 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bndtCeckManageVO" method="post" action=""> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bndtCeckSe" />
<form:hidden path="bndtCeckCd" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="당직체크 수정">
<caption>당직체크 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="bndtCeckSeNm">당직체크구분</label>
    	</th>          
    	<td width="80%">
    		<c:out value='${bndtCeckManageVO.bndtCeckSeNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
      		<span class="required_icon"></span>
    		<label for="bndtCeckCd">당직체크코드</label>
    	</th>
    	<td>
    		<c:out value='${bndtCeckManageVO.bndtCeckCd}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="useAt">당직체크코드명</label>
    	</th>          
    	<td>
      		<form:input  path="bndtCeckCdNm" size="80" maxlength="100" title="당직체크코드명"/>
      		<form:errors path="bndtCeckCdNm" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
      		<form:select path="useAt" title="사용여부 ">
	      		<form:option value="Y" label="사용"/>
	      		<form:option value="N" label="미사용"/>
      		</form:select>
    	</td>    
  	</tr>     
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bndtCeckManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
* 목록 으로 가기
******************************************************** */
function fn_aram_list() {
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtCeckManage.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("bndtCeckManageVO");

    if(!validateBndtCeckManageVO(varForm)){           
        return;
    }
    
	if(confirm("<spring:message code='common.update.msg'/>")){
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/updateBndtCeckManage.do";
	    varForm.submit();
    }
}

</script>

</body>
</html>