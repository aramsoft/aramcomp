<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : ResourceEdit.java
  * @Description : 접근자원 수정
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *  
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>접근자원 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="resourceVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register" summary="접근자원을 수정하는 테이블입니다.접근자원 코드,접근자원 명,접근자원패턴,설명,접근자원타입,접근자원 Sort,등록일자 정보를 담고 있습니다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		접근자원  코드
    	</th>
    	<td class="lt_text">
    		<form:input path="resourceCode" size="30" readonly="true" title="접근자원 코드" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="resourceNm">접근자원 명</label>
    	</th>
    	<td class="lt_text">
    		<form:input path="resourceNm" maxLength="50" size="30" title="접근자원명" />
    		<form:errors path="resourceNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="resourcePttrn">접근자원 패턴</label>
    	</th>
    	<td class="lt_text">
    		<form:input path="resourcePttrn" maxLength="200" size="50" title="접근자원패턴" />
    		<form:errors path="resourcePttrn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="resourceDc">설명</label>
    	</th>
    	<td class="lt_text">
    		<form:input path="resourceDc" maxLength="50" size="50" title="설명" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		접근자원 타입
    	</th>
    	<td class="lt_text">
      		<form:select path="resourceTy" title="접근자원타입">
                <form:options items="${COM029_resourceType}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
   		</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="resourceSort">접근자원 Sort</label>
    	</th>
    	<td class="lt_text">
    		<form:input path="resourceSort" maxLength="10" size="10" title="접근자원sort" />
    	</td>
  	</tr>
  	<tr>
    	<th>
   			<span class="norequired_icon"></span>
    		등록일자
     	</th>
    	<td class="lt_text">
    		<form:input path="resourceCreatDe" maxLength="50" size="20" title="등록일자" readonly="true"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="resourceVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("resourceVO");
    varForm.action = "${pageContext.request.contextPath}/sec/rmt/listResource.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("resourceVO");
    
    if(!validateResourceVO(varForm)){
        return;
    }

	if (confirm("<spring:message code='common.update.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/rmt/updateResource.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("resourceVO");

	if (confirm("<spring:message code='common.delete.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/rmt/deleteResource.do";
    	varForm.submit();
    }
}

</script>
