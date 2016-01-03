<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : ChartGraphEdit.java
  * @Description : 차트/그래프 데이터 수정
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
	<h2>차트/그래프 데이터 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="chartGraphVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register"  summary="차트/그래프 데이터 수정테이블.">
  	<tr> 
	    <th width="20%">
	    	<label id="lgdNm" for="lgdNm">범례명</label>
			<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	        <form:input path="lgdNm" size="70" maxlength="70" />
	        <form:errors path="lgdNm" cssClass="error"/>                               
	    </td>
  	</tr>
    <tr> 
	    <th>
	    	<label id="xaxis" for="xaxis">X축 값</label>
			<span class="required_icon"></span>
	    </th>
	    <td>
	        <form:input path="xaxis" size="70" maxlength="70" />
	        <form:errors path="xaxis" cssClass="error"/>                              
	    </td>
  	</tr>
    <tr> 
	    <th>
	    	<label id="yaxis" for="yaxis">Y축 값</label>
			<span class="required_icon"></span>
	    </th>
	    <td>
	        <form:input path="yaxis" size="70" maxlength="70" />
	        <form:errors path="yaxis" cssClass="error"/>                             
	    </td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>
	
</div>	

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="chartGraphVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
/* ********************************************************
 * 초기화
	 ******************************************************** */
window.onload = function(){

    // 첫 입력란에 포커스..
    var varForm = document.getElementById("chartGraphVO");
    varForm.lgdNm.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("chartGraphVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mcg/listChartGraph.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("chartGraphVO");
    
	if (!validateChartGraphVO(varForm)) {
		return;				
	} 
	
	if (confirm("<spring:message code='common.update.msg' />"))    {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/mcg/updateChartGraph.do";
		varForm.submit();
	}
}

</script>
