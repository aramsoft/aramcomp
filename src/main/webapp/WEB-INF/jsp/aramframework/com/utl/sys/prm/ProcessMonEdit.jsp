<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : ProcessMonEdit.jsp
  * @Description : 프로세스모니터링 수정
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
	<h2>프로세스모니터링 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="processMonVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="processId"/>	

<table class="table-register" summary="이 표는 프로세스모니터링 대상 정보를 제공하며, 프로세스명, 상태, 관리자명, 관리자이메일로 구성되어 있습니다 .">
<caption>프로세스모니터링 수정</caption>
	<tr>
		<th width="20%">
			<span class="required_icon"></span>
			프로세스명
		</th>
    	<td width="80%">
      		<form:input onkeyup="cleanQueryTerm()" path="processNm" size="60" maxlength="60"/>
      		<form:errors path="processNm" cssClass="error"/>
    	</td>  				    	
	</tr>
  	<!-- <tr>
    	<th scope="row" width="20%"  class="column_title">
     		<span class="required_icon"></span>
    		상태
    	</th>          
    	<td>${processMonVO.procsSttus}</td>    
  	</tr> -->   					 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		관리자명
     	</th>          
    	<td>
      		<form:input  path="mngrNm" size="60" maxlength="60"/>
      		<form:errors path="mngrNm" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
  		<th>
   			<span class="required_icon"></span>
  			관리자이메일
  		</th>          
    	<td>
      		<form:input  path="mngrEmailAddr" size="60" maxlength="60"/>
      		<form:errors path="mngrEmailAddr" cssClass="error"/>
    	</td>    
  	</tr>     
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="processMonVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("processMonVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/listProcessMon.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("processMonVO");
    
	if(!validateProcessMonVO(varForm)){ 			
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/updateProcessMon.do";
		varForm.submit();
	}
}
/* ********************************************************
 * '~`!@#$%%^&*-=+\|[{]};:\',<.>/?' 문자열은 제거한다.
 ******************************************************** */
function cleanQueryTerm() {
	var varForm = document.getElementById("processMonVO");
	var specialChars='~`!@#$%%^&*-=+\|[{]};:\',<>/?';
	var str=varForm.processNm.value;
	var i, j;
	if (str == '') {
		return false;
	}
	for (i = 0; i < str.length; i++) {
		for (j = 0; j < specialChars.length; j++) {
			if (str.charAt(i) == specialChars.charAt(j))
				str = str.replace(str.charAt(i), " ");
		}
	}
	varForm.processNm.value = str;
}

</script>
