<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CntcServiceEdit.jsp
  * @Description : 연계서비스 수정
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
	<h2>연계서비스 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cntcServiceVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="insttId"/>
<form:hidden path="sysId"/>
<form:hidden path="svcId"/>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="연계서비스  수정을 제공한다.">
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
    		시스템
    	</th>
    	<td>
        	<form:select path="sysId"  disabled="true" title="연계메시지 선택">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcSystemList}" itemValue="sysId" itemLabel="sysNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		서비스ID
    	</th>
    	<td>
    		<c:out value='${cntcServiceVO.svcId}'/>
    	</td>
  	</tr>
  	<tr>
   		<th>
   			<span class="required_icon"></span>
   			서비스명
   		</th>
    	<td>
      		<form:input  path="svcNm" size="60" maxlength="60" title="서비스명"/>
      		<form:errors path="svcNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
   			<span class="norequired_icon"></span>
    		요청메시지ID
    	</th>
    	<td>
        	<form:select path="requestMessageId"  title="연계메시지 선택">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcMessageList}" itemValue="cntcMessageId" itemLabel="cntcMessageNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
   			<span class="norequired_icon"></span>
    		<label id="rspnsMessageId">응답메시지ID</label>
    	</th>
    	<td>
        	<form:select path="rspnsMessageId"  title="연계메시지 선택">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcMessageList}" itemValue="cntcMessageId" itemLabel="cntcMessageNm"/>
        	</form:select>
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

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cntcServiceVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("cntcServiceVO");
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/detailCntcInstt.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("cntcServiceVO");
	
	if(!validateCntcServiceVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/updateCntcService.do";
		varForm.submit();
	}
}

</script>
