<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : DeviceIdentDetail.java
  * @Description : 모바일 기기 식별 상세 조회
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
	<h2>모바일 기기 식별 상세 조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="deviceIdentVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="모바일 기기 식별 정보테이블.">
  	<tr> 
	    <th width="20%">브라우저&nbsp;&nbsp;</th>
	    <td width="80%">
	        <c:out value="${deviceIdentVO.browserNm}"/>  
	    </td>
  	</tr>
  	<tr> 
	    <th>운영체제&nbsp;&nbsp;</th>
	    <td>
	    	<c:out value="${deviceIdentVO.osNm}"/>
	    </td>
  	</tr>
  	<tr> 
	    <th>User-Agent&nbsp;&nbsp;</th>
	    <td>
	    	<c:out value="${deviceIdentVO.uagentInfo}"/>
	    </td>
  	</tr>
  	<tr> 
	    <th>등록상태&nbsp;&nbsp;</th>
	    <td>
	        <c:out value="${deviceIdentVO.recentNm}"/>
	    </td>
  	</tr>
  	<tr> 
	    <th>생성일시&nbsp;&nbsp;</th>
	    <td>
	        <c:out value="${deviceIdentVO.creatDt}"/>
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

</div>	

<script type="text/javaScript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("deviceIdentVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/listDeviceIdent.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("deviceIdentVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/editDeviceIdent.do";
    varForm.submit();   
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("deviceIdentVO");
    
    if  (confirm("<spring:message code='common.delete.msg' />"))    {   
        varForm.action = "${pageContext.request.contextPath}/mbl/com/mdi/deleteDeviceIdent.do";
        varForm.submit();
    }    
}
</script>
