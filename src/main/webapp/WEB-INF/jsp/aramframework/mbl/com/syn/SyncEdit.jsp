<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : SyncEdit.java
  * @Description : 동기화 서비스 수정
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
	<h2>동기화 서비스 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update_sync(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_sync(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="syncVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<!-- 등록  폼 영역  -->
<table class="table-register"  summary="차트/그래프 데이터 수정테이블.">
  	<tr> 
	    <th width="20%">
	    	제목
			<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	        <form:input path="syncSj" size="70" maxlength="70" />
	        <form:errors path="syncSj" cssClass="error"/>                               
	    </td>
  	</tr>
    <tr> 
	    <th>
	    	<label id="mberId" for="mberId">작성자</label>
			<span class="norequired_icon"></span>
	    </th>
	    <td>
	        <c:out value="${syncVO.mberId}"/>                              
	    </td>
  	</tr>
    <tr> 
	    <th width="20%">
	    	<label id="creatDt" for="creatDt">생성일시</label>
	    	<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/no_required.gif" width="15" height="15" alt="선택항목">
	    </th>
	    <td width="80%">
	        <c:out value="${syncVO.creatDt}"/>                           
	    </td>
  	</tr>
    <tr> 
	    <th width="20%" height="70">
	    	<label id="syncCn" for="syncCn">글내용</label>
	    	<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
	    </th>
	    <td width="80%">
	        <form:textarea path="syncCn" rows="15" cols="70" size="70" maxlength="70" />
	        <form:errors path="syncCn" cssClass="error"/>                               
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="syncVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" >

/* ********************************************************
 * 초기화
 ******************************************************** */
 window.onload = function() {

    // 첫 입력란에 포커스..
    //document.syncForm.syncSj.focus()();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
 function fn_aram_list() {
    var varForm = document.getElementById("syncVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/listSync.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("syncVO");
    
	if (!validateSyncVO(varForm)) {
		return;				
	} 
	
	if (confirm("<spring:message code='common.update.msg' />")) {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/updateSync.do";
		varForm.submit();
	}
}

 /* ********************************************************
  * 입력받은문자열중에서 제거 문자열을 제외 문자열을 리턴한다.
  ******************************************************** */
 function fn_aram_remove_string(srcStr, cndStr) {
     
     var result = null;
     var rtnStr = null;
     
     for (var i = 0; i < srcStr.length; i++) {
         
         if (srcStr.charAt(i) == cndStr) {
             result = srcStr.substring(0, i);
             
             // 첫번째 제거 문자열을 제외한 전체 문자열
             srcStr = result + srcStr.substring(i+1, srcStr.length);
             
             // 최종변환 문자열
             rtnStr = srcStr;
         }
     }
     
     return rtnStr;
 }

</script>
