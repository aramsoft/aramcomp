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
  * @Class Name  : TrsmrcvMntrngRegist.jsp
  * @Description : 송수신모니터링 등록
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
	<h2>송수신모니터링 등록</h2>
</div>
 
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 상단타이틀 -->
<form:form commandName="trsmrcvMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!--  등록  폼 영역  -->
<table class="table-register"  summary="송수신모니터링 등록을 제공한다.">
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label id="idCntcId" for="cntcId">연계ID</label>
    	</th>
    	<td width="80%">
        	<form:input path="cntcId" size="10" maxlength="8" readonly="true" cssClass="readOnlyClass"/>
        	<a href="#" onClick="javascript:fn_aram_popup_cntc(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
        		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif"
                    width="15" height="15" style="vertical-align: middle" alt="연계정보조회팝업 제공">
            </a>
         	<form:errors path="cntcId" cssClass="error" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label id="idCntcNm" for="cntcNm">연계명</label>
    	</th>
    	<td>
        	<form:input path="cntcNm" size="60" maxlength="100" readonly="true" cssClass="readOnlyClass"/>
        	<form:errors path="cntcNm" cssClass="error" />  
    	</td>
  	</tr> 
   	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		<label id="idProvdInsttNm">제공기관</label>
    	</th>
        <td>
            <div id="provdInsttNm"></div> 
        </td>    
  	</tr> 
  	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		<label id="idProvdSysNm">제공시스템</label>
    	</th>
        <td>
            <div id="provdSysNm"></div> 
        </td>    
  	</tr> 
  	<tr> 
    	<th>
       		<span class="norequired_icon"></span>
    		<label id="idProvdSvcNm">제공서비스</label>
    	</th>
        <td>
            <div id="provdSvcNm"></div> 
        </td>    
  	</tr> 
  	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		<label id="idRequstInsttNm">요청기관</label>
    	</th>
        <td>
            <div id="requstInsttNm"></div> 
        </td>    
  	</tr> 
  	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		<label id="idRequstSysNm">요청시스템</label>
    	</th>
        <td>
            <div id="requstSysNm"></div> 
        </td>    
  	</tr> 
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label id="idTestClassNm" for="testClassNm">테스트클래스명</label>
    	</th>
    	<td>
      		<form:input path="testClassNm" size="100" maxlength="255"/>
      		<form:errors path="testClassNm" cssClass="error" />
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
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
function fn_aram_insert(){
    var varForm = document.getElementById("trsmrcvMntrngVO");
    
    if(!validateTrsmrcvMntrngVO(varForm)){             
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/insertTrsmrcvMntrng.do";
        varForm.submit();
    }
}

var gArguments = new Array();

/* ********************************************************
* 연계검색 팝업화면
******************************************************** */
function fn_aram_popup_cntc(){
    var varForm = document.getElementById("trsmrcvMntrngVO");
	gArguments["cntcId"] = varForm.cntcId;
	gArguments["cntcNm"] = varForm.cntcNm;
	gArguments["provdInsttNm"]  = document.getElementById("provdInsttNm");
	gArguments["provdSysNm"]    = document.getElementById("provdSysNm");
	gArguments["provdSvcNm"]    = document.getElementById("provdSvcNm");
	gArguments["requstInsttNm"] = document.getElementById("requstInsttNm");
	gArguments["requstSysNm"]   = document.getElementById("requstSysNm");

	var url = "/utl/sys/trm/listCntcPopup.do";

	window.open(url, "p_cntcInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}           

</script>
