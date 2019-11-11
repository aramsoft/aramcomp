<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardMasterEdit.jsp
  * @Description : 게시판 정보 수정
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
	<h2>게시판 정보 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
     	<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
     	<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
       	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="boardMasterVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bbsId" />
<form:hidden path="bbsTyCode" />
<form:hidden path="bbsAttrbCode" />
<form:hidden path="replyPosblAt" />

<table class="table-register"  summary="게시판명,게시판 소개,게시판 유형,게시판 속성,답장가능여부, ..   입니다">
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="bbsNm">게시판명</label>
	    </th>
	    <td colspan="3">
	      	<form:input title="게시판명입력" path="bbsNm" size="50" maxlength="60" />
	     	<form:errors path="bbsNm" cssClass="error"/>
	    </td>
	</tr>
  	<tr>
	    <th>
	    	<label for="bbsIntrcn">게시판 소개</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td colspan="3">
	      	<textarea title="게시판소개입력" name="bbsIntrcn" id="bbsIntrcn" class="textarea" cols="70" rows="4"  style="width:100%">
<c:out value="${boardMasterVO.bbsIntrcn}" escapeXml="true" />
	      	</textarea>
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
	    	<label for="posblAtchFileNumber">첨부가능파일숫자</label>
	    </th>
	    <td colspan="3">
	     	<form:select title="첨부가능파일 숫자선택" path="posblAtchFileNumber" class="select">
	  		    <form:option value="0" label="없음" />
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
	    	<label for="tmplatNm">템플릿 정보</label>
	    </th>
	    <td colspan="3">
	     	<form:input path="tmplatNm" title="템플릿정보입력" size="20" maxlength="20" readonly="true" />
	     	<form:hidden path="tmplatId" />
	     	<a href="#" onclick="javascript:fn_aram_get_tmplat(); return false;">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="새창">
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
		    <select name="option" class="select" <c:if test="${boardMasterVO.option != 'na'}">disabled="disabled"</c:if> title="추가선택사항">
				<option value='na' <c:if test="${boardMasterVO.option == 'na'}">selected="selected"</c:if>>---선택하세요--</option>
				<option value='' <c:if test="${boardMasterVO.option == ''}">selected="selected"</c:if>>미선택</option>
				<c:if test="${useComment == 'true' }">
				<option value='comment' <c:if test="${boardMasterVO.option == 'comment'}">selected="selected"</c:if>>댓글</option>
				</c:if>
				<c:if test="${useSatisfaction == 'true' }">
				<option value='stsfdg' <c:if test="${boardMasterVO.option == 'stsfdg'}">selected="selected"</c:if>>만족도조사</option>
				</c:if>
		    </select>
		  	<br>※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함)
		</td>
	</tr>
	</c:if>
	<!-- 2009.06.26 : 2단계 기능 추가 방법 변경 -->
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	커뮤니티명
	    </th>
	    <td colspan="3">
	    	<c:choose>
	    	<c:when test="${not empty boardUseInfVO.cmmntyNm}">
	    		<c:out value="${boardUseInfVO.cmmntyNm}" />
	    	</c:when>
	    	<c:when test="${not empty boardUseInfVO.useAt}">
   				(시스템  활용)
   			</c:when>
   			<c:otherwise>미정</c:otherwise>
			</c:choose>
	    </td>
	</tr>
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
	<c:if test="${not empty boardUseInfVO.provdUrl2}">
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	제공 URL2
	    </th>
	    <td colspan="3">
	    	<a href="<c:out value="${boardUseInfVO.provdUrl2}" />" target="_new">
	    	   	<c:out value="${boardUseInfVO.provdUrl2}" />
			</a>
	    </td>
	</tr>
	</c:if>
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
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardMasterVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMaster.do";
    varForm.submit();
}

function fn_aram_update(){
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
	
	// 2011.11.11 : 첨부파일가능 선택 시 파일숫자를 선택하도록 함, 첨부파일가능 미선택시 파일 숫자를 없음으로 변경
	var list = document.getElementsByName('fileAtchPosblAt');
	var fileAtchPosblAt_value;
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

	if(confirm("<spring:message code='common.update.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateBoardMaster.do";
		varForm.submit();
	}
}

function fn_aram_delete(){
    var varForm = document.getElementById("boardMasterVO");
    
	if(confirm("<spring:message code='common.delete.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/deleteBoardMaster.do";
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


