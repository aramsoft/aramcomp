<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : SyncDetail.java
  * @Description : 동기화 서비스 상세조회
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
	<h2>동기화 서비스 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="syncVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register" summary="동기화서비스 상세테이블.">
  	<tr> 
	    <th width="20%">제목&nbsp;&nbsp;</th>
	    <td width="80%">
	        <c:out value="${syncVO.syncSj}"/>  
	    </td>
  	</tr>
  	<tr> 
	    <th>작성자&nbsp;&nbsp;</th>
	    <td>
	    	<c:out value="${syncVO.mberId}"/>
	    </td>	
  	</tr>
  	<tr> 
	    <th>생성일시&nbsp;&nbsp;</th>
	    <td>
	    	<c:out value="${syncVO.creatDt}"/>
	    </td>	
  	</tr>
  	<tr> 
	    <th height="70">글내용&nbsp;&nbsp;</th>
	    <td>
	        <c:out value="${syncVO.syncCn}"/>
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

<script type="text/javaScript" >

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("syncVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/listSync.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("syncVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/editSync.do";
    varForm.submit();   
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("syncVO");
    
    if  (confirm("<spring:message code='common.delete.msg' />"))    {   
    	varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/deleteSync.do";
    	varForm.submit();
    }    
}

</script>
