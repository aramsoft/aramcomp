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
  * @Class Name : DbMntrngRegist.jsp
  * @Description : DB서비스모니터링 등록
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
	<h2>DB서비스모니터링 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="dbMntrngVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!--  등록  폼 영역  -->
<table class="table-register" summary="DB서비스모니터링 등록을 제공한다.">
	<tr> 
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label id="IdDataSourcNm" for="dataSourcNm">데이타소스명</label>
	    </th>
	    <td width="80%">
	    	<form:input path="dataSourcNm" size="60" maxlength="60"/>
	        <form:errors path="dataSourcNm" cssClass="error" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label id="IdServerNm" for="serverNm">서버명</label>
	    </th>
	    <td>
	        <form:input path="serverNm" size="60" maxlength="60"/>
	        <form:errors path="serverNm" cssClass="error" />  
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label id="IdDbmsKind" for="dbmsKind">DBMS종류</label>
	    </th>
	    <td>
	         <form:select path="dbmsKind">
	             <form:option value="" label="--선택하세요--"/>
	             <form:options items="${COM048_dbmsKind}" itemValue="code" itemLabel="codeNm"/>
	         </form:select>
	         <form:errors path="dbmsKind" cssClass="error"/>
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label id="IdCeckSql" for="ceckSql">체크SQL</label>
	    </th>
	    <td>
	      	<form:input path="ceckSql" size="60" maxlength="250"/>
	      	<form:errors path="ceckSql" cssClass="error" />
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label id="IdMngrNm" for="mngrNm">관리자명</label>
	    </th>
	    <td>
	      	<form:input path="mngrNm" size="60" maxlength="60"/>
	      	<form:errors path="mngrNm" cssClass="error" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label id="IdMngrEmailAddr" for="mngrEmailAddr">관리자이메일주소</label>
	    </th>
	    <td>
	      	<form:input path="mngrEmailAddr" size="50" maxlength="50"/>  
	       	<form:errors path="mngrEmailAddr" cssClass="error" />
	    </td>
	</tr> 
	
</table>
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="dbMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("dbMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrng.do";
    varForm.submit();    
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("dbMntrngVO");
    
    if(!validateDbMntrngVO(varForm)){             
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/insertDbMntrng.do";
        varForm.submit();
    }
}

</script>
