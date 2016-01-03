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
  * @Class Name : QustnrQestnRegist.jsp
  * @Description : 설문문항 등록
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
	<h2>설문문항 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<form:form commandName="qustnrQestnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrQesitmId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="등록 을 제공한다.">
<caption>등록 을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="qestnrSj">설문지정보(제목)</label>
    	</th>
    	<td width="80%">
      		<form:input path="qestnrSj" title="설문지정보(제목) 입력" size="73" maxlength="2000" style="width:300px;" />
      		<a href="#" onClick="javascript:fn_aram_get_qustnr(); return false;">
      			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="설문지정보" title="설문지정보">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnSn">질문순번</label>
    	</th>
    	<td>
      		<form:input path="qestnSn" size="50" value="1" maxlength="10" style="width:60px;" title="질문순번 입력" />
      		<form:errors path="qestnSn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnTyCode">질문유형</label>
    	</th>
    	<td>
			<form:select path="qestnTyCode" title="질문유형 선택">
				<form:option value="" label="선택" />
		    	<form:options items="${COM018_qestnType}" itemValue="code" itemLabel="codeNm"/>
			</form:select>
			<form:errors path="qestnTyCode" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnCn">질문 내용</label>
    	</th>
    	<td>
      		<form:textarea path="qestnCn" class="textarea"  cols="75" rows="10"  style="width:99%;" title="질문내용 입력" />
      		<form:errors path="qestnCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="mxmmChoiseCo">최대선택건수</label>
    	</th>
    	<td>
       		<select name="mxmmChoiseCo" title="최대선택건수 선택">
		       	<option value="1">1</option>
		       	<option value="2">2</option>
		       	<option value="3">3</option>
		       	<option value="4">4</option>
		       	<option value="5">5</option>
		       	<option value="6">6</option>
		       	<option value="7">7</option>
		       	<option value="8">8</option>
		       	<option value="9">9</option>
		       	<option value="10">10</option>
      		</select>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qustnrQestnManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

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
	var varForm = document.getElementById("qustnrQestnManageVO");
	varForm.action =  "${pageContext.request.contextPath}/uss/olp/qqm/listQustnrQestn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("qustnrQestnManageVO");
	
	if(!validateQustnrQestnManageVO(varForm)){
		return;
	}
	
	if(varForm.qestnrSj.value == "" ||
			varForm.qestnrId.value == "" ){
		alert("설문지정보를  입력해주세요!");
		varForm.qestnrSj.focus();
		return;
	}

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qqm/insertQustnrQestn.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 설문지정보 팝업창열기
 ******************************************************** */
function fn_aram_get_qustnr(){
	var varForm = document.getElementById("qustnrQestnManageVO");
	gArguments["qestnrId"] = varForm.qestnrId;
	gArguments["qestnrSj"] = varForm.qestnrSj;
	
	var url = "/uss/olp/qmc/listQustnrPopup.do";

	window.open(url, "p_qestnrInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}
 
</script>

