<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : QustnrRespondInfoEdit.jsp
  * @Description : 설문조사 수정
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
	<h2>설문조사 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrRespondInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrQesrspnsId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 설문조사 대상 정보를 제공하며, 설문관리정보, 설문문항정보, 설문유형, 성문항목정보, 응답자답변내용, 기타답변내용, 응답자명 정보로 구성되어 있습니다 .">
  	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	설문관리정보
	    </th>
	    <td width="80%">
			<c:out value="${fn:replace(qustnrRespondInfoVO.qestnrSj , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	설문문항정보
	    </th>
	    <td>
	    	<c:out value="${fn:replace(qustnrRespondInfoVO.qestnCn , crlf , '<br/>')}" escapeXml="false" />
			<form:hidden path="qestnrQesitmId" />
	    </td>
  	</tr>
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	설문유형
	    </th>
	    <td>
	    	<div id="divQestnTyCode">
	    	<c:if test="${qustnrRespondInfoVO.qestnTyCode == '1'}">객관식</c:if>
	    	<c:if test="${qustnrRespondInfoVO.qestnTyCode == '2'}">주관식</c:if>
	    	</div>
	      	<!-- <input name="title" type="text" size="73" value="" maxlength="70" style="width:100%;">   -->
	    </td>
  	</tr>
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	설문항목정보
	    </th>
	    <td>
			<form:input path="qustnrIemCn" title="설문문항정보" size="73" maxlength="4000" style="width:300px;" />
	   		<a href="#" onClick="fn_aram_get_qustnrItem(); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="설문항목정보" title="새창">
			</a>
			<form:hidden path="qustnrIemId" />
	    </td>
  	</tr>
  	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	응답자답변내용<br>(주관식)
	    </th>
	    <td>
	      	<textarea name="respondAnswerCn" class="textarea" title="응답자답변내용"  cols="75" rows="4"  style="width:100%;">${qustnrRespondInfoVO.respondAnswerCn}</textarea>
	      	<form:errors path="respondAnswerCn" cssClass="error" />
	    </td>
  	</tr>
  	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	기타답변내용
	    </th>
	    <td>
	      	<textarea name="etcAnswerCn" class="textarea" title="기타답변내용"  cols="75" rows="4"  style="width:100%;">${qustnrRespondInfoVO.etcAnswerCn}</textarea>
	      	<form:errors path="etcAnswerCn" cssClass="error" />
	    </td>
  	</tr>
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	응답자명
	    </th>
	    <td>
			<input name="respondNm" type="text" title="응답자명" size="50" value="<c:out value="${fn:replace(qustnrRespondInfoVO.respondNm , crlf , '<br/>')}" escapeXml="false" />" maxlength="50" style="width:120px;">
			<form:errors path="respondNm" cssClass="error" />
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
<validator:javascript formName="qustnrRespondInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    
	if(!validateQustnrRespondInfoVO(varForm)){
		return;
	}

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/qri/updateQustnrRespondInfo.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 설문항목정보 팝업창열기 / 설문항목정보
 ******************************************************** */
function fn_aram_get_qustnrItem(){
	var varForm = document.getElementById("qustnrRespondInfoVO");
	gArguments["qustnrIemId"] = varForm.qustnrIemId;
	gArguments["qustnrIemCn"] = varForm.qustnrIemCn;
	
	var arguments = "searchCondition=QESTNR_QESITM_ID&searchKeyword="+ document.getElementById("qestnrQesitmId").value;
	var url = "/uss/olp/qim/listQustnrItemPopup.do?"+arguments;

	window.open(url, "p_qustnrItem", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

