<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BdMstrRegistByTrget.jsp
  * @Description : 커뮤니티/동호회의 게시판 등록
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
	<h2>게시판 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="boardMasterVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="trgetId" value="${curTrgetId}" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	게시판명
	    </th>
	    <td width="80%" colspan="3">
	      	<form:input path="bbsNm" size="60" cssStyle="width:100%" />
	      	<form:errors path="bbsNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	게시판 소개
	    </th>
	    <td colspan="3">
	      	<form:textarea path="bbsIntrcn" cols="75" rows="4" cssStyle="width:100%" />
	      	<form:errors path="bbsIntrcn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	게시판 유형
	    </th>
	    <td width="30%">
	        <form:select path="bbsTyCode">
    	  		<form:option value='' label="--선택하세요--" />
	      		<form:options items="${COM004_bbsType}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
	  	    <form:errors path="bbsTyCode" cssClass="error"/>
	    </td>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	게시판 속성
	    </th>
	    <td width="30%">
	        <form:select path="bbsAttrbCode">
    	  		<form:option value='' label="--선택하세요--" />
	      		<form:options items="${COM009_bbsAttrb}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
	  	    <form:errors path="bbsAttrbCode" cssClass="error"/>
	    </td>
	</tr> 
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	답장가능여부
	    </th>
	    <td>
	     	<label for="replyPosblAtY"><spring:message code="button.possible" /></label> : <form:radiobutton path="replyPosblAt"  value="Y" id="replyPosblAtY" />&nbsp;
	     	<label for="replyPosblAtN"><spring:message code="button.impossible" /></label> : <form:radiobutton path="replyPosblAt"  value="N" id="replyPosblAtN" />
	     	<form:errors path="replyPosblAt" cssClass="error"/> 
	    </td>
	    <th>
	    	<span class="required_icon"></span>
	    	파일첨부가능여부
	    </th>
	    <td>
	     	<label for="fileAtchPosblAtY"><spring:message code="button.possible" /></label> : <form:radiobutton path="fileAtchPosblAt"  value="Y" id="fileAtchPosblAtY" />&nbsp;
	     	<label for="fileAtchPosblAtN"><spring:message code="button.impossible" /></label> : <form:radiobutton path="fileAtchPosblAt"  value="N" id="fileAtchPosblAtN" />
	     	<form:errors path="fileAtchPosblAt" cssClass="error"/>
 	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	첨부가능파일 숫자
	    </th>
	    <td colspan="3">
	     	<form:select path="posblAtchFileNumber">
	  		   <form:option value="0" label="---선택하세요--" />
	  		   <form:option value='1' label="1개" />
	  		   <form:option value='2' label="2개" />
	  		   <form:option value='3' label="3개" />
	  	   </form:select>
	  	   <form:errors path="posblAtchFileNumber" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	템플릿 정보
	    </th>
	    <td colspan="3">
	     	<form:input path="tmplatNm"  size="20" readonly="true" />
	     	<form:hidden path="tmplatId"  />
	     	&nbsp;
	     	<a href="#" onclick="javascript:fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search" />
	     	</a>
			<form:errors path="tmplatId" cssClass="error"/>
	    </td>
	</tr>
	<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
	<c:if test="${useComment == 'true' || useSatisfaction == 'true'}">
  	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			추가 선택사항
		</th>
		<td colspan="3">
		  	<form:select path="option">
		  	   <!-- form:option value=''--><!--선택하세요--><!-- /option-->
		  	   	<form:option value=""  label="미선택" />
		  	   	<c:if test="${useComment == 'true' }">
		  	  	<form:option value='comment'>댓글</form:option>
		  		</c:if>
		  		<c:if test="${useSatisfaction == 'true' }">
		  		<form:option value='stsfdg'>만족도조사</form:option>
		  		</c:if>
		  	</form:select>
		</td>
	</tr>
	</c:if>
	<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${boardMasterVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${boardMasterVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${boardMasterVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${boardMasterVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("boardMasterVO");
    varForm.action = "${pageContext.request.contextPath}/cop/com/listBdMstrByTrget.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("boardMasterVO");

    if (!validateBoardMasterVO(varForm)){
		return;
	}

	//----------------------------
	// 2009.06.26 : 2단계 기능 추가
	//----------------------------
	if (varForm.bbsTyCode.options[varForm.bbsTyCode.selectedIndex].value == 'BBST04' 
		&&	varForm.option.options[varForm.option.selectedIndex].value != '') {
		alert('방명록의 경우는 추가 선택사항을 지원하지 않습니다.');
		varForm.option.focus();
		return;
	}
	////--------------------------

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/com/insertBdMstrByTrget.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 템플릿 팝업창열기
******************************************************** */
function fn_aram_get_tmplat(){
    var varForm = document.getElementById("boardMasterVO");
	gArguments["tmplatId"] = varForm.tmplatId;
	gArguments["tmplatNm"] = varForm.tmplatNm;
	
	var url = "/cop/tpl/listTemplatePopup.do?typeFlag=BBS";

	window.open(url, "p_tmplatInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

