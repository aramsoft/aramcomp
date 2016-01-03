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
  * @Class Name : QustnrItemRegist.jsp
  * @Description : 설문항목 등록
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
	<h2>설문항목 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrItemManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrQesitmId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 설문항목 목록 정보를 제공하며, 설문정보, 설문문항정보, 항목순번, 항목내용, 기타답변여부 정보로 구성되어 있습니다 .">
	<tr>
		<th width="20%">
			<span class="required_icon"></span>
			설문정보
		</th>
		<td width="80%">
		  	<form:input path="qestnrSj" size="73" title="설문정보 입력" maxlength="4000" style="width:300px;" />
		   	<a href="#" onClick="javascript:fn_aram_get_qustnr(); return false;">
		   		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" onClick=""align="middle" style="border:0px" alt="설문정보" title="설문정보">
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
		   	<a href="#" onClick="javascript:fn_aram_get_qustnrQestn(); return false;">
		  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" alt="설문문항정보" title="설문문항정보">
		  	</a>
		</td>
	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			항목 순번
		</th>
		<td>
		  	<form:input path="qustnrIemSn" size="5" title="항목 순번" maxlength="5" style="width:100px;" />
		  	<form:errors path="qustnrIemSn" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			항목 내용
		</th>
		<td>
		  	<form:textarea path="qustnrIemCn" class="textarea" title="항목 내용 입력" cols="75" rows="5"  style="width:100%;" />
		  	<form:errors path="qustnrIemCn" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			기타답변여부
		</th>
		<td>
		   	<select name="etcAnswerAt" title="기타답변여부 선택">
		   		<option value="N">N</option>
		   		<option value="Y">Y</option>
		  	</select>
		</td>
	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qustnrItemManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	document.getElementById("qustnrIemSn").value = "1";
	//document.getElementById("qestnrEndDe").value = "2008-01-30";
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrItemManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qim/listQustnrItem.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("qustnrItemManageVO");
	
	if(!validateQustnrItemManageVO(varForm)){
		return;
	}

	if(varForm.qestnrSj.value == "" ||
			varForm.qestnrId.value == "" ) {
		alert("설문지정보를  입력해주세요!");
		varForm.qestnrSj.focus();
		return;
	}
	
	if(varForm.qestnCn.value == "" ||
			varForm.qestnrQesitmId.value == "" ){
		alert("설문문항정보를  입력해주세요!");
		varForm.qestnrQesitmCn.focus();
		return;
	}

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/qim/insertQustnrItem.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 설문지정보 팝업창열기
 ******************************************************** */
function fn_aram_get_qustnr(){
	var varForm = document.getElementById("qustnrItemManageVO");
	gArguments["qestnrId"] = varForm.qestnrId;
	gArguments["qestnrSj"] = varForm.qestnrSj;
	
	var url = "/uss/olp/qmc/listQustnrPopup.do";

	window.open(url, "p_qestnrInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
  * 설문지문항정보 팝업창열기
  ******************************************************** */
function fn_aram_get_qustnrQestn(){
	var varForm = document.getElementById("qustnrItemManageVO");
	gArguments["qestnrQesitmId"] = varForm.qestnrQesitmId;
	gArguments["qestnCn"] = varForm.qestnCn;
	
	var arguments = "searchCondition=QESTNR_ID&searchKeyword="+ document.getElementById("qestnrId").value;
	var url = "/uss/olp/qqm/listQustnrQestnPopup.do?"+arguments;

	window.open(url, "p_qustnrQestn", "width=850px,height=480px,top=100px,left=100px,location=no");
}
 
</script>

