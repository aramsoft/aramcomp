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
  * @Class Name : ResourceRegist.java
  * @Description : 접근자원 등록
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
	<h2>접근자원 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="resourceVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="롤 등록 테이블입니다.롤 코드,롤 명,롤 패턴,설명,롤 타입,롤 Sort,등록일자의 정보를 담고 있습니다.">
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		접근자원  코드
    	</th>
    	<td>
    		<form:input path="resourceCode" size="30" title="접근자원 코드" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="resourceNm">접근자원 명</label>
    	</th>
    	<td>
    		<form:input path="resourceNm"  maxLength="50" size="30" title="접근자원명"/>
    		<form:errors path="resourceNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="resourcePttrn">접근자원 패턴</label>
    	</th>
    	<td>
    		<form:input path="resourcePttrn" maxLength="200" size="50" title="접근자원패턴"/>
    		<form:errors path="resourcePttrn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="resourceDc">설명</label>
    	</th>
    	<td>
    		<form:input path="resourceDc" maxLength="50" size="50" title="설명"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		접근자원 타입
    	</th>
    	<td>
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
    	<td>
    		<form:input path="resourceSort" maxLength="10" size="10" title="접근자원sort"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		등록일자
    	</th>
    	<td>
    		<form:input path="resourceCreatDe" maxLength="50" size="20" readonly="true" title="등록일자"/>
    	</td>
  	</tr>
</table>

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

function fn_aram_insert() {
    var varForm = document.getElementById("resourceVO");
    
    if(!validateResourceVO(varForm)){
        return;
    }

	if (confirm("<spring:message code='common.regist.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/rmt/insertResource.do";
        varForm.submit();
    }
}

</script>
