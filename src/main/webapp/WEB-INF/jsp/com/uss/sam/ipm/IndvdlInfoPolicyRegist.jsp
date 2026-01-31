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
  * @Class Name : IndvdlInfoPolicyRegist.jsp
  * @Description : 개인정보보호정책 등록
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
	<h2>개인정보보호정책 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!--  등록  폼 영역  -->
<form:form modelAttribute="indvdlInfoPolicyVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register" summary="개인정보보호정책  등록을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="indvdlInfoNm">개인정보보호정책 명</label>
    	</th>
    	<td width="80%">
      		<form:input path="indvdlInfoNm" size="73" maxlength="255"/>
      		<form:errors path="indvdlInfoNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		동의여부
    	</th>
    	<td>
			<form:select path="indvdlInfoYn" title="동의여부">
				<form:option value="" label="선택" />
				<form:option value="Y" label="예" />
				<form:option value="N" label="아니오" />
			</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="indvdlInfoDc">개인정보보호정책 내용</label>
    	</th>
    	<td>
			<form:textarea path="indvdlInfoDc" class="textarea" cols="75" rows="30"  style="width:500px;" />               
   			<form:errors path="indvdlInfoDc" cssClass="error"/>
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
<validator:javascript formName="indvdlInfoPolicyVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "indvdlInfoDc";
_editor_lang = "kr";
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/html/htmlarea4.0/htmlarea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/utl/HtmlEditor.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

window.onload = function() {
	HTMLArea.onload = initEditor;
	HTMLArea.init(); 
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("indvdlInfoPolicyVO");
    varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/listIndvdlInfoPolicy.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("indvdlInfoPolicyVO");
	varForm.onsubmit();		// for ending of htmleditor
	
	if(!validateIndvdlInfoPolicyVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/insertIndvdlInfoPolicy.do";
		varForm.submit();
	}
}

</script>
