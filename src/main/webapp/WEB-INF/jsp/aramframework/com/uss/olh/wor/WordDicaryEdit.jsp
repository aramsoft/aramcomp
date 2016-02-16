<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : WordDicaryEdit.jsp
  * @Description : 용어사전 수정
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
	<h2>용어사전 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="wordDicaryVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="wordId" />

<table class="table-register" summary="용어사전에 대한 정보를 수정합니다.">
<caption>용어사전수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="wordNm">용어명</label>
    	</th>
    	<td width="80%">
        	<form:input path="wordNm" size="60" maxlength="60" title="용어명" />
    		<form:errors path="wordNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="engNm">영문명</label>
    	</th>
    	<td>
      		<form:input path="engNm" size="60" maxlength="60" title="영문명" />
      		<form:errors path="engNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="wordDc">용어설명</label>
    	</th>
    	<td>
      		<form:textarea path="wordDc" cols="200" rows="15" cssClass="txArea" title="용어설명" />
      		<form:errors path="wordDc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="synonm">동의어</label>
    	</th>
    	<td>
      		<form:input path="synonm" size="60" maxlength="60" title="동의어" />
      		<form:errors path="synonm" cssClass="error"/>
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
<validator:javascript formName="wordDicaryVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("wordDicaryVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/wor/listWordDicary.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("wordDicaryVO");
    
	if (!validateWordDicaryVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/wor/updateWordDicary.do";
		varForm.submit();
	}
}

</script>

