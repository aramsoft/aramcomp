<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : DeviceIdentEdit.java
  * @Description : 모바일 기기 식별 수정
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
	<h2>모바일 기기 식별 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="deviceIdentVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register" summary="모바일 기기 식별 정보 수정테이블.">
	<tr> 
		<th width="20%">
			브라우저
			<span class="required_icon"></span>
		</th>
		<td width="80%">  
            <form:select path="browserCode" title="브라우저종류 구분">
                <form:options items="${browserCmmCodeDetailList}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
			<form:errors path="browserCode" cssClass="error"/>     	
		</td>
	</tr>
	<tr> 
		<th>
			운영체제
			<span class="required_icon"></span>
		</th>
		<td>  
            <form:select path="osCode" title="운영체제종류 구분">
                <form:options items="${osCmmCodeDetailList}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
			<form:errors path="osCode" cssClass="error"/>      	
		</td>
	</tr>
	<tr> 
		<th>
			User-Agent
			<span class="required_icon"></span>
		</th>
		<td>
			<form:input path="uagentInfo" size="70" maxlength="1000"/>
			<form:errors path="uagentInfo" cssClass="error"/>      	  	      	      	
		</td>
	</tr>
	<form:input path="recentCode" type="hidden" value="REG02"/>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>
	
</div>	

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="deviceIdentVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function(){

    // 첫 입력란에 포커스..
    var varForm = document.getElementById("deviceIdentVO");
    varForm.browserNm.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("deviceIdentVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/listDeviceIdent.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("deviceIdentVO");
    
	if (!validateDeviceIdentVO(varForm)) {
		return;				
	} 
	
	if (confirm("<spring:message code='common.update.msg' />"))    {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/updateDeviceIdent.do";
		varForm.submit();
	}
}

</script>
