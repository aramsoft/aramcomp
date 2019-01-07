<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CntcMessageItemEdit.jsp
  * @Description : 연계메시지항목 수정
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
	<h2>연계메시지항목 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cntcMessageItemVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcMessageId"/>
<form:hidden path="itemId"/>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="연계메시지항목 수정을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		연계메시지
    	</th>
    	<td width="80%">
        	<form:select path="cntcMessageId"  disabled="true" title="연계메시지 선택">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcMessageList}" itemValue="cntcMessageId" itemLabel="cntcMessageNm"/>
        	</form:select>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		항목ID
    	</th>
    	<td>
    		<c:out value='${cntcMessageItemVO.itemId}'/>
    	</td>
 	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		항목명
    	</th>
    	<td>
      		<form:input  path="itemNm" title="항목명" size="100" maxlength="100"/>
      		<form:errors path="itemNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		항목타입
    	</th>
    	<td>
      		<form:input  path="itemType" title="항목타입" size="20" maxlength="20"/>
      		<form:errors path="itemType" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		항목길이
    	</th>
    	<td>
      		<form:input  path="itemLt" title="항목길이" size="8" maxlength="8"/>
      		<form:errors path="itemLt" cssClass="error"/>
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
<validator:javascript formName="cntcMessageItemVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("cntcMessageItemVO");
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/ims/detailCntcMessage.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("cntcMessageItemVO");
    
	if(!validateCntcMessageItemVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/ssi/syi/ims/updateCntcMessageItem.do";
		varForm.submit();
	}
}

</script>
