<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : ChartGraphRegist.java
  * @Description : 차트/그래프 데이터 등록
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
	<h2>차트/그래프 데이터 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- ------------------------------------ 상단타이틀(파일첨부를 위한 폼명 및 Enctype 설정 ---------------------------->
<form:form commandName="chartGraphVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register">
	<tr> 
		<th width="20%">
			범례명
			<span class="required_icon"></span>
		</th>
		<td width="80%">  
			<form:input path="lgdNm" size="70" maxlength="60" />
			<form:errors path="lgdNm" cssClass="error"/>      	  	      	      	
		</td>
	</tr>
	<tr> 
		<th>
			X축 값
			<span class="required_icon"></span>
		</th>
		<td>
			<form:input path="xaxis" size="70" maxlength="70" />
			<form:errors path="xaxis" cssClass="error"/>      	  	      	      	
		</td>
	</tr>
	<tr> 
		<th>
			Y축 값
			<span class="required_icon"></span>
		</th>
		<td>  
			<form:input path="yaxis" size="70" maxlength="70" />
			<form:errors path="yaxis" cssClass="error"/>     	  	      	      	
		</td>
	</tr>
</table>
   
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
function fn_aram_insert(){
    var varForm = document.getElementById("chartGraphVO");
    
	if (!validateChartGraphVO(varForm)) {
		return;				
	} 
	
	if (confirm("<spring:message code='common.regist.msg' />"))    {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/mcg/insertChartGraph.do";
		varForm.submit();
	} 		
}

</script>		
