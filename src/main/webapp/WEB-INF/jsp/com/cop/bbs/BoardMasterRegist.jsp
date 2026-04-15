<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardMasterRegist.jsp
  * @Description : 게시판 등록
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
	<h2>게시판 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	    	<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
	       	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="boardMasterVO" method="post" action="">

<table class="table-register" summary="게시판명,게시판소개,게시판 유형,게시판 속성,답장가능여부,파일첨부가능여부, ..  입니다">
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="bbsNm"><spring:message code="cop.bbsNm" /></label>
	    </th>
	    <td colspan="3">
	    	<form:input title="게시판명입력" path="bbsNm" size="60" cssStyle="width:100%" />
	    	<form:errors path="bbsNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="bbsIntrcn"><spring:message code="cop.bbsIntrcn" /></label>
	    </th>
	    <td colspan="3">
	       	<form:textarea title="게시판소개입력" path="bbsIntrcn" cols="75" rows="4" cssStyle="width:100%" />
	       	<form:errors path="bbsIntrcn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.bbsTyCode" />
	    </th>
	    <td width="30%">
	        <form:select path="bbsTyCode" title="게시판유형선택">
    	  		<form:option value='' label="--선택하세요--" />
	      		<form:options items="${COM004_bbsType}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
	  	    <form:errors path="bbsTyCode" cssClass="error"/>
	    </td>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.bbsAttrbCode" />
	    </th>
	    <td width="30%">
	        <form:select path="bbsAttrbCode" title="게시판속성선택">
    	  		<form:option value='' label="--선택하세요--" />
	      		<form:options items="${COM009_bbsAttrb}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
	  	    <form:errors path="bbsAttrbCode" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.replyPosblAt" />
	    </th>
	    <td>
	     	<label for="replyPosblAtY"><spring:message code="button.possible" /></label> : <form:radiobutton path="replyPosblAt"  value="Y" id="replyPosblAtY" />&nbsp;
	     	<label for="replyPosblAtN"><spring:message code="button.impossible" /></label> : <form:radiobutton path="replyPosblAt"  value="N" id="replyPosblAtN" />
	     	<form:errors path="replyPosblAt" cssClass="error"/>
	    </td>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.fileAtchPosblAt" />
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
	    	<spring:message code="cop.posblAtchFileNumber" />
	    </th>
	    <td  colspan="3">
	     	<form:select path="posblAtchFileNumber" title="첨부가능파일 숫자선택"	>
	  		   <form:option value="0"  label="없음" />
	  		   <form:option value='1'>1개</form:option>
	  		   <form:option value='2'>2개</form:option>
	  		   <form:option value='3'>3개</form:option>
	  	   </form:select>
	  	   <form:errors path="posblAtchFileNumber" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.tmplatId" />
	    </th>
	    <td colspan="3">
	     	<form:input path="tmplatNm" size="20" readonly="true" title="템플릿정보입력"/>
	     	<form:hidden path="tmplatId"  />
	     	&nbsp;
	     	<a href="#" onclick="javascript:fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif"	width="15" height="15" align="middle" alt="새창" />
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
		    <form:select path="option" title="추가선택사항선택">
		  		<form:option value=""  label="미선택" />
		  		<c:if test="${useComment == 'true'}">
		  		<form:option value='comment'>댓글</form:option>
		  		</c:if>
		  		<c:if test="${useSatisfaction == 'true'}">
		  		<form:option value='stsfdg'>만족도조사</form:option>
		  		</c:if>
		  	</form:select>
		</td>
	</tr>
	</c:if>
	<!-- 2009.06.26 : 2단계 기능 추가 방법 변경 -->
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
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardMasterVO");
	varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMaster.do";
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
		&& varForm.option.options[varForm.option.selectedIndex].value != '') {
		alert('방명록의 경우는 추가 선택사항을 지원하지 않습니다.');
		varForm.option.focus();
		return;
	}
	////--------------------------
	// 2011.11.11 : 첨부파일가능 선택 시 파일숫자를 선택하도록 함, 첨부파일가능 미선택시 파일 숫자를 없음으로 변경
	var list = document.getElementsByName('fileAtchPosblAt');
	var fileAtchPosblAt_value = null;
	for (var i=0; i < list.length; i++) {
		if(list[i].checked == true) {
			fileAtchPosblAt_value = list[i].value;
		}
	}
	
	if (fileAtchPosblAt_value == "Y" && varForm.posblAtchFileNumber.value == 0 ) {
		alert("첨부가능파일 숫자를 1개이상 선택하세요.");
		return;
	}
	
	if (fileAtchPosblAt_value == "N" && varForm.posblAtchFileNumber.value != 0 ) {
		varForm.posblAtchFileNumber.value = 0;
	}
		////--------------------------
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertBoardMaster.do";
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

