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
  * @Class Name  : TrsmrcvMntrngEdit.jsp
  * @Description : 송수신모니터링 수정
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>송수신모니터링 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="trsmrcvMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcId" />
<form:hidden path="mntrngSttus" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="송수신모니터링 수정기능을 제공한다.">
	<tr> 
	    <th width="20%">연계ID</th>
	    <td width="80%">
	        <c:out value="${trsmrcvMntrngVO.cntcId}" escapeXml="false" /> 
	    </td>
	</tr>
	<tr> 
	    <th>연계명</th>
	    <td>
	        <c:out value="${trsmrcvMntrngVO.cntcNm}" escapeXml="false" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>제공기관</th>
	    <td>
	        <c:out value="${trsmrcvMntrngVO.provdInsttNm}" escapeXml="false" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>제공시스템</th>
	    <td>
	        <c:out value="${trsmrcvMntrngVO.provdSysNm}" escapeXml="false" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>제공서비스</th>
	    <td>
	        <c:out value="${trsmrcvMntrngVO.provdSvcNm}" escapeXml="false" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>요청기관</th>
	    <td>
	        <c:out value="${trsmrcvMntrngVO.requstInsttNm}" escapeXml="false" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>요청시스템</th>
	    <td>
	        <c:out value="${trsmrcvMntrngVO.requstSysNm}" escapeXml="false" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<label id="idTestClassNm" for="testClassNm">테스트클래스명</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<form:input path="testClassNm" size="100" maxlength="255" />
	      	<form:errors path="testClassNm" cssClass="error" />
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<label id="IdMngrNm" for="mngrNm">관리자명</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<form:input path="mngrNm" size="60" maxlength="60"/>
	      	<form:errors path="mngrNm" cssClass="error" /> 
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	<label id="IdMngrEmailAddr" for="mngrEmailAddr">관리자이메일주소</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<form:input path="mngrEmailAddr" size="50" maxlength="50"/>  
	       	<form:errors path="mngrEmailAddr" cssClass="error" />
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
<validator:javascript formName="trsmrcvMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("trsmrcvMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrng.do";
    varForm.submit();  
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("trsmrcvMntrngVO");
    
    if(!validateTrsmrcvMntrngVO(varForm)){             
        return;
    }
    
	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/updateTrsmrcvMntrng.do";
        varForm.submit();
    }
}

</script>
