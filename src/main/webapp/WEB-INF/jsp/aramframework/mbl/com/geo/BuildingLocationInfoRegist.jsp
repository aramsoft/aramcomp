<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : BuildingLocationInfoRegist.java
  * @Description : 건물 위치 정보 등록
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
	<h2>건물 위치 정보 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="geoLocationVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register" summary="건물 위치 정보를 등록합니다.">
<tbody>
  	<tr> 
	    <th width="20%">
	    	건물명
			<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	    	<input name="buldNm" type="text" style="width:50%;height:100%;" value="">
	    </td>
  	</tr>
  	<tr> 
	    <th>
	    	전화번호
			<span class="norequired_icon"></span>
	    </th>
	    <td>
	    	<input name="telno" type="text" style="width:50%;height:100%;" value="">
	    </td>
  	</tr>
  	<tr> 
	    <th>
	    	주소
			<span class="norequired_icon"></span>
	    </th>
	    <td>
	    	<input name="adres" type="text" style="width:50%;height:100%;" value="">
	    </td>
  	</tr>
  	<tr> 
	    <th>
	    	위도
			<span class="required_icon"></span>
	    </th>
	    <td>
	    	<input name="la" type="text" style="width:50%;height:100%;" value="">
	    </td>
  	</tr>
  	<tr> 
	    <th>
	    	경도
			<span class="required_icon"></span>
	    </th>
	    <td>
	    	<input name="lo" type="text" style="width:50%;height:100%;" value="">
	    </td>
  	</tr>
</tbody>
</table>
	
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="geoLocationVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("geoLocationVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/geo/listBuildingLocationInfo.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("geoLocationVO");
    
	if(!validateGeoLocationVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/mbl/com/geo/insertBuildingLocationInfo.do";
		varForm.submit();
	}	
}
</script>
