<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CntcSystemRegist.jsp
  * @Description : 연계시스템 등록
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
	<h2>연계시스템 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cntcSystemVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="insttId"/>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="연계시스템  입력을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		기관
    	</th>
    	<td width="80%">
        	<form:select path="insttId"  disabled="true" title="연계메시지 선택">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcInsttList}" itemValue="insttId" itemLabel="insttNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		시스템ID
    	</th>
    	<td>
      		<form:input  path="sysId" size="20" maxlength="20" readonly="readonly" title="시스템ID" cssClass="readOnlyClass"/>
      		<form:errors path="sysId" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		시스템명
    	</th>
    	<td>
      		<form:input  path="sysNm" size="60" maxlength="60" title="시스템명"/>
      		<form:errors path="sysNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		시스템IP
    	</th>
    	<td>
      		<form:input  path="sysIp" size="23" maxlength="23" title="시스템IP"/>
      		<form:errors path="sysIp" cssClass="error"/>
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
<validator:javascript formName="cntcSystemVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("cntcSystemVO");
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/detailCntcInstt.do";
	varForm.submit();
}

/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("cntcSystemVO");
	
	if(!validateCntcSystemVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/insertCntcSystem.do";
		varForm.submit();
	}
}

</script>
