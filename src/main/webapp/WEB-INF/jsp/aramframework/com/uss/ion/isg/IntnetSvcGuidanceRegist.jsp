<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : IntnetSvcGuidanceRegist.jsp
 * @Description : 인터넷서비스안내 등록
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
	<h2>인터넷서비스안내 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="intnetSvcGuidanceVO" method="post" action=""> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="intnetSvcId">

<table class="table-register" summary="인터넷서비스정보를 등록한다">
<caption>인터넷서비스안내 등록</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		인터넷서비스명
    	</th>
    	<td width="80%">
    		<form:input path="intnetSvcNm" title="인터넷서비스명" maxLength="20" size="40" />
    		<form:errors path="intnetSvcNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		인터넷서비스설명
    	</th>
    	<td>
       		<form:textarea path="intnetSvcDc" title="인터넷서비스설명" class="textarea" rows="15" cols="80" />
       		<form:errors path="intnetSvcDc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		반영여부
    	</th>
    	<td>
      		<form:select path="reflctAt" title="반영여부">
          		<form:option value="Y" label="Y" />
          		<form:option value="N" label="N" />
      		</form:select>
   		</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="intnetSvcGuidanceVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "intnetSvcDc";
_editor_lang = "kr";
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/html/htmlarea4.0/htmlarea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/HtmlEditor.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript">

window.onload = function() {
	HTMLArea.onload = initEditor;
	HTMLArea.init(); 
};

function fn_aram_list() {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/isg/listIntnetSvcGuidance.do";
    varForm.submit();
}

function fn_aram_insert() {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
	varForm.onsubmit();		// for ending of htmleditor

    if(!validateIntnetSvcGuidanceVO(varForm)){
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/isg/insertIntnetSvcGuidance.do";
        varForm.submit();
    }
}

</script>
