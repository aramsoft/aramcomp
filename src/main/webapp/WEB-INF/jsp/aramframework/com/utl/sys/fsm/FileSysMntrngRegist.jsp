<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : FileSysMntrngRegist.jsp
 * @Description : 파일시스템모니터링 등록
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
	<h2>파일시스템모니터링 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="fileSysMntrngVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register"  summary="이 표는 파일시스템모니터링 대상 정보를  등록하기 위한 표이며, 파일시스템명, 파일시스템관리명, 파일시스템 크기, 파일시스템 임계치, 관리자명, 관리자이메일주소 정보로 구성되어 있습니다 .">
<caption>파일시스템모니터대상 등록</caption>
<tbody>
	<tr> 
		<th width="20%">
			<span class="required_icon"></span>
			<label for="fileSysNm">파일시스템명</label>
		</th>
		<td width="80%">
			<form:input path="fileSysNm" size="50" maxlength="60" title="파일시스템명"/>
			<span class="button"><a href="#" onclick="fn_aram_check_fileSystem(); return false;">파일시스템확인</a></span>
		</td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="fileSysManageNm">파일시스템관리명</label>
	    </th>
	    <td>
	      	<form:input path="fileSysManageNm" size="65" maxlength="255" title="파일시스템관리명"/>
	      	<form:errors path="fileSysManageNm" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="fileSysMg">파일시스템 크기</label>
	    </th>
	    <td>
	      	<form:input path="fileSysMg" size="8" maxlength="8" title="파일시스템 크기" readonly="true"/>G
	      	<form:errors path="fileSysMg" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="fileSysThrhld">파일시스템 임계치</label>
	    </th>
	    <td>
	      	<form:input path="fileSysThrhld" size="8" maxlength="8" title="파일시스템 임계치"/>G
	      	<form:errors path="fileSysThrhld" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="mngrNm">관리자명</label>
	    </th>
	    <td>
	      	<form:input path="mngrNm" size="5" maxlength="60" title="관리자명"/>
	      	<form:errors path="mngrNm" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="mngrEmailAddr">관리자이메일주소</label>
	   	</th>
	    <td>
	      	<form:input path="mngrEmailAddr" size="25" maxlength="50" title="관리자이메일주소"/>
    	  	<form:errors path="mngrEmailAddr" cssClass="error"/>
	    </td>
	</tr>
</tbody>
</table>
	
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="fileSysMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

window.onload = function() {
    var varForm = document.getElementById("fileSysMntrngVO");
    varForm.fileSysMg.value = "<c:out value='${fileSysMgValue}'/>";
	if(varForm.fileSysMg.value == ""){
		varForm.fileSysMg.value = 0;
	}

	if("${notApplicableFileSys}" == "true"){
		alert("파일시스템명이 올바르지 않습니다.");
	}
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("fileSysMntrngVO");
	varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrng.do";
	varForm.submit();	
}	

function fn_aram_insert() {
    var varForm = document.getElementById("fileSysMntrngVO");
    
	if (!validateFileSysMntrngVO(varForm)){
		return;
	}

	if(eval(varForm.fileSysMg.value) < 1){
		alert("파일시스템확인 버튼을 클릭하여, 파일시스템의 크기를 확인해야 합니다.");
		return;
	}

	if(eval(varForm.fileSysThrhld.value) < 1){
		alert("파일시스템 임계치를 1G 이상 입력하여야 합니다.");
		return;
	}
	
	if(eval(varForm.fileSysThrhld.value)> eval(varForm.fileSysMg.value)){
		alert("파일시스템 임계치는 파일시스템 크기보다 클 수 없습니다.");
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/insertFileSysMntrng.do";
		varForm.submit();					
	}
}

function fn_aram_check_fileSystem() {
    var varForm = document.getElementById("fileSysMntrngVO");
	varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/getFileSysMg.do";
	varForm.submit();					
}

</script>
