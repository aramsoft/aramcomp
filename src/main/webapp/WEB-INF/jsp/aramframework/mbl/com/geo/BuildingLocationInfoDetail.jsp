<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : BuildingLocationInfoDetail.java
  * @Description : 건물 위치 정보 상세 조회
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
	<h2>건물 위치 정보 상세 조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="geoLocationVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register"  summary="건물 위치 정보에 대한 상세정보를 제공합니다.">
<tbody>
  	<tr> 
    	<th width="20%">건물명&nbsp;</th>
    	<td width="80%">
    		<c:out value="${geoLocationVO.buldNm}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>전화번호&nbsp;</th>
    	<td>
    		<c:out value="${geoLocationVO.telno}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>주소&nbsp;</th>
    	<td>
    		<c:out value="${geoLocationVO.adres}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>위도&nbsp;</th>
    	<td>
    		<c:out value="${geoLocationVO.la}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>경도&nbsp;</th>
    	<td>
    		<c:out value="${geoLocationVO.lo}"/>
    	</td>
  	</tr>
</tbody>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>
</div>
	
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("geoLocationVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/geo/listBuildingLocationInfo.do";
    varForm.submit();
}

function fn_aram_edit(){
    var varForm = document.getElementById("geoLocationVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/geo/editBuildingLocationInfo.do";
    varForm.submit();   
}

function fn_aram_delete(){
    var varForm = document.getElementById("geoLocationVO");
    
    if (confirm("<spring:message code='common.delete.msg' />"))    {   
        varForm.action = "${pageContext.request.contextPath}/mbl/com/geo/deleteBuildingLocationInfo.do";
        varForm.submit();
   	}
}
</script>
