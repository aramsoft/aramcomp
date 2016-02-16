<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : SiteInfoEdit.jsp
  * @Description : 사이트 수정
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
	<h2>사이트 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="siteManageVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="siteId" />
      
<!-- 등록  폼 영역  -->
<table class="table-register" summary="사이트정보 수정테이블">
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="siteNm">사이트명</label>
    	</th>
	    <td width="80%">
	        <form:input path="siteNm" size="70" maxlength="70" />
	        <form:errors path="siteNm" cssClass="error"/>          
	    </td>
  	</tr>
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label for="siteUrl">사이트 URL</label>
    	</th>
	    <td>
	        <form:input path="siteUrl" size="70" maxlength="70" />
	        <form:errors path="siteUrl" cssClass="error"/>               
	    </td>
  	</tr> 
  	<tr> 
    	<th>
      		<span class="required_icon"></span>
    		<label for="siteDc">사이트 설명</label>
    	</th>
	    <td> 
	      	<form:textarea path="siteDc" cols="200" rows="10" cssClass="txArea" />   
	      	<form:errors path="siteDc" cssClass="error"/>                                
	    </td>
  	</tr> 
 	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="siteThemaClCode">사이트주제분류</label>
    	</th>
    	<td>
	        <form:select path="siteThemaClCode">
	            <form:options items="${COM023_siteThema}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
	        <form:errors path="siteThemaClCode" cssClass="error"/>                                                                 
	    </td>
  	</tr> 
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label for="actvtyAt">활성여부</label>
    	</th>
    	<td>
        	활성:   <input type="radio" name="actvtyAt" class="radio1" value="Y" <c:if test="${siteManageVO.actvtyAt == 'Y'}">checked</c:if>>&nbsp;
       		비활성: <input type="radio" name="actvtyAt" class="radio1" value="N" <c:if test="${siteManageVO.actvtyAt == 'N'}">checked</c:if>>&nbsp;                                      
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
        	 사용:   <input type="radio" name="useAt" class="radio2" value="Y" <c:if test="${siteManageVO.useAt == 'Y'}">checked</c:if>>&nbsp;
       		 미사용: <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${siteManageVO.useAt == 'N'}">checked</c:if>>&nbsp;     
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
<validator:javascript formName="siteManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("siteManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/sit/listSiteInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("siteManageVO");
    
    if (!validateSiteManageVO(varForm)) {
        return;
    }
    
	if(confirm("<spring:message code='common.update.msg'/>")){
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/sit/updateSiteInfo.do";
    	varForm.submit();
    } 
}

</script>

