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
  * @Class Name : QustnrRespondInfoRegist.jsp
  * @Description : 설문조사 등록
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
	<h2>설문조사 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
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
			<form:input path="qestnrSj" title="설문관리정보 입력" size="73" maxlength="4000" style="width:300px;" />
     		<a href="#" onClick="fn_aram_get_qustnr(); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="설문관리정보" title="설문관리정보">
			</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		설문문항정보
    	</th>
    	<td>
			<form:input path="qestnCn" title="설문문항정보 입력" size="73" maxlength="4000" style="width:300px;" />
    		<a href="#" onClick="fn_aram_get_qustnrQestn(); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="설문문항정보" title="설문문항정보">
			</a>
 			<form:hidden path="qestnrQesitmId" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		설문유형
    	</th>
    	<td>
    		<div id="divQestnTyCode"></div>
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
      		<textarea name="respondAnswerCn" class="textarea" title="응답자답변내용 입력"  cols="75" rows="4"  style="width:100%;"></textarea>
      		<form:errors path="respondAnswerCn" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
   			<span class="norequired_icon"></span>
    		기타답변내용
     	</th>
    	<td>
      		<textarea name="etcAnswerCn" class="textarea" title="기타답변내용 입력"  cols="75" rows="4"  style="width:100%;"></textarea>
      		<form:errors path="etcAnswerCn" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		응답자명
     	</th>
    	<td>
			<input name="respondNm" type="text" title="응답자명 입력" size="50" value="" maxlength="50" style="width:120px;">
			<form:errors path="respondNm" cssClass="error" />
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qustnrRespondInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	//document.getElementById("qestnrBeginDe").value = "2008-01-01";
	//document.getElementById("qestnrEndDe").value = "2008-01-30";
};

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
function fn_aram_insert(){
	var varForm = document.getElementById("qustnrRespondInfoVO");
	
	if(!validateQustnrRespondInfoVO(varForm)){
		return;
	}
	
	if(varForm.qestnrSj.value == "" ||
			varForm.qestnrId.value == "" ){
		alert("설문지정보를  입력해주세요!");
		varForm.qestnrSj.focus();
		return;
	}

	if(varForm.qestnCn.value == "" ||
			varForm.qestnrQesitmId.value == ""	){
		alert("설문문항정보를  입력해주세요!");
		varForm.qestnCn.focus();
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/qri/insertQustnrRespondInfo.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 설문지정보 팝업창열기 / 설문관리정보
 ******************************************************** */
function fn_aram_get_qustnr(){
	var varForm = document.getElementById("qustnrRespondInfoVO");
	gArguments["qestnrId"] = varForm.qestnrId;
	gArguments["qestnrSj"] = varForm.qestnrSj;
	
	var url = "/uss/olp/qmc/listQustnrPopup.do";

	window.open(url, "p_qestnrInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
  * 설문지문항정보 팝업창열기 / 설문문항정보
  ******************************************************** */
function fn_aram_get_qustnrQestn(){
	var varForm = document.getElementById("qustnrRespondInfoVO");
	gArguments["qestnrQesitmId"] = varForm.qestnrQesitmId;
	gArguments["qestnCn"] = varForm.qestnCn;
	gArguments["divQestnTyCode"] = document.getElementById("divQestnTyCode");
	
	var arguments = "searchCondition=QESTNR_ID&searchKeyword="+ document.getElementById("qestnrId").value;
	var url = "/uss/olp/qqm/listQustnrQestnPopup.do?"+arguments;

	window.open(url, "p_qustnrQestn", "width=850px,height=480px,top=100px,left=100px,location=no");
}
 
/* ********************************************************
 * 설문항목정보 팝업창열기 / 설문항목정보
 ******************************************************** */
function fn_aram_get_qustnrItem(){
	var varForm = document.getElementById("qustnrRespondInfoVO");
	gArguments["qustnrIemId"] = varForm.qustnrIemId;
	gArguments["qustnrIemCn"] = varForm.qustnrIemCn;
	
	var url = "/uss/olp/qim/listQustnrItemPopup.do";

	window.open(url, "p_qustnrItem", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

