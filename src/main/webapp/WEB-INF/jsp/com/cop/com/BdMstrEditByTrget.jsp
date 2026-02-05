<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BdMstrEditByTrget.jsp
  * @Description : 커뮤니티/동호회 사용 게시판 수정
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>게시판 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="boardMasterVO" action="" method="post">

<form:hidden path="bbsId" />
<form:hidden path="bbsTyCode" />
<form:hidden path="bbsAttrbCode" />
<form:hidden path="replyPosblAt" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	게시판ID
	    </th>
	    <td width="80%"  colspan="3">
	      	<c:out value="${boardMasterVO.bbsId}"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	게시판명
	    </th>
	    <td colspan="3">
	      	<form:input path="bbsNm" type="text" size="60" maxlength="60" style="width:100%" title="게시판명입력" />
	      	<form:errors path="bbsNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	게시판 소개
	    </th>
	    <td colspan="3">
	      	<form:textarea path="bbsIntrcn" class="textarea"  cols="75" rows="4" style="width:100%" title="게시판소개" />
	      	<form:errors path="bbsIntrcn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	게시판 유형
	    </th>
	    <td width="30%">
	    	<c:out value="${boardMasterVO.bbsTyCodeNm}"/>
	    </td>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	게시판 속성
	    </th>
	    <td width="30%">
	    	<c:out value="${boardMasterVO.bbsAttrbCodeNm}"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	답장가능여부
	    </th>
	    <td>
	    	<c:choose>
	    	<c:when test="${boardMasterVO.replyPosblAt == 'Y'}">
	    		<spring:message code="button.possible" />
	    	</c:when>
	    	<c:otherwise>
	    		<spring:message code="button.impossible" />
	    	</c:otherwise>
	    	</c:choose>
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
	    <td>
	     	<form:select title="첨부가능파일 숫자선택" path="posblAtchFileNumber" class="select">
	  		   	<form:option value="0" label="--선택하세요--" />
	  		   	<form:option value='1' label="1개" />
	  		   	<form:option value='2' label="2개" />
	  		   	<form:option value='3' label="3개" />
	  	    </form:select>
	  	   	<form:errors path="posblAtchFileNumber" cssClass="error"/>
	    </td>
	    <th>
	    	<span class="required_icon"></span>
	    	첨부가능파일사이즈
	    </th>
	    <td>
	     	<form:select path="posblAtchFileSize" class="select" title="첨부가능파일사이즈선택">
	  		   	<form:option value="" label="--선택하세요--" />
	  		   	<form:option value='5000000' label="5M Byte" />
	  		   	<form:option value='10000000' label="10M Byte" />
	  	   </form:select>
	  	   <form:errors path="posblAtchFileSize" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	템플릿 정보
	    </th>
	    <td colspan="3">
	     	<form:input path="tmplatNm" title="템플릿정보입력" size="20" maxlength="20" readonly="true" />
	     	<form:hidden path="tmplatId" />
	     	&nbsp;
	     	<a href="#" onclick="javascript:fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search">
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
		    <select name="option" class="select" title="추가선택사항" <c:if test="${boardMasterVO.option != 'na'}">disabled="disabled"</c:if>>
				<option value='na' <c:if test="${boardMasterVO.option == 'na'}">selected="selected"</c:if>>---선택하세요--</option>
				<option value='' <c:if test="${boardMasterVO.option == ''}">selected="selected"</c:if>>미선택</option>
				<c:if test="${useComment == 'true' }">
				<option value='comment' <c:if test="${boardMasterVO.option == 'comment'}">selected="selected"</c:if>>댓글</option>
				</c:if>
				<c:if test="${useSatisfaction == 'true' }">
				<option value='stsfdg' <c:if test="${boardMasterVO.option == 'stsfdg'}">selected="selected"</c:if>>만족도조사</option>
				</c:if>
		    </select>
		  	   ※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함)
		</td>
	</tr>
	</c:if>
	<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
	<c:if test="${not empty boardUseInfVO.provdUrl}">
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	제공 URL
	    </th>
	    <td colspan="3">
	    	<a href="<c:out value="${boardUseInfVO.provdUrl}" />" target="_new">
	    	   	<c:out value="${boardUseInfVO.provdUrl}" />
			</a>
	    </td>
	</tr>
	</c:if>
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
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("boardMasterVO");
    varForm.action = "${pageContext.request.contextPath}/cop/com/listBdMstrByTrget.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("boardMasterVO");

    if (!validateBoardMasterVO(varForm)){
		return;
	}

	//----------------------------
	// 2009.06.26 : 2단계 기능 추가
	//----------------------------
	if ('<c:out value="${result.bbsTyCode}"/>' == 'BBST04' 
		&& (varForm.option.options[varForm.option.selectedIndex].value == 'comment' 
			|| varForm.option.options[varForm.option.selectedIndex].value == 'stsfdg')) {
		alert('방명록의 경우는 추가 선택사항을 지원하지 않습니다.');
		varForm.option.focus();
		return;
	}
	////--------------------------

	if (varForm.posblAtchFileSize.value == "") {
		alert('첨부파일 사이즈를 선택해주세요.');
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/com/updateBdMstrByTrget.do";
		varForm.submit();
	}
}

function fn_aram_delete(){
    var varForm = document.getElementById("boardMasterVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/com/deleteBdMstrByTrget.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 템플릿 팝업창열기
******************************************************** */
function fn_aram_get_tmplat() {
    var varForm = document.getElementById("boardMasterVO");
	gArguments["tmplatId"] = varForm.tmplatId;
	gArguments["tmplatNm"] = varForm.tmplatNm;
	
	var url = "/cop/tpl/listTemplatePopup.do?typeFlag=BBS";

	window.open(url, "p_tmplatInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>


