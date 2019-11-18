<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : NameCardEdit.jsp
  * @Description : 명함 수정
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
	<h2>명함 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
   		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<c:if test="${writer == true}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		</c:if>
   		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code='button.list' /></a></span>
	</div>
</div>

<form:form modelAttribute="nameCardVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="ncrdId" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="ncrdNm"><spring:message code="cop.ncrdNm" /></label>
	    </th>
	    <td colspan="3">
	      	<form:input path="ncrdNm" size="30"  maxlength="60" />
	        <form:errors path="ncrdNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	<label for="cmpnyNm"><spring:message code="cop.cmpnyNm" /></label>
	    </th>
	    <td width="30%">
	      	<form:input path="cmpnyNm" size="30" maxlength="60" title="회사명수정" />
	    </td>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	<label for="deptNm"><spring:message code="cop.deptNm" /></label>
	    </th>
	    <td width="30%">
	      	<form:input path="deptNm" size="30" maxlength="60" title="부서수정" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="ofcpsNm"><spring:message code="cop.ofcpsNm" /></label>
	    </th>
	    <td>
	      	<form:input path="ofcpsNm" size="30" maxlength="60" title="직위명수정" />
	    </td>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="clsfNm"><spring:message code="cop.clsfNm" /></label>
	    </th>
	    <td>
	      	<form:input path="clsfNm" size="30" maxlength="60" title="직급명수정" />
	    </td>
	  </tr>
	  <tr>
	    <th>
			<span class="required_icon"></span>
	    	<label for="emailAdres"><spring:message code="cop.emailAdres" /></label>
		</th>
	    <td colspan="3">
	      	<form:input path="emailAdres" size="30" maxlength="60" title="" />
	      	<form:errors path="emailAdres" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<spring:message code="cop.telNo" />
	    </th>
	    <td colspan="3">
	      	<form:input path="nationNo" size="7" maxlength="7" />-
	      	<form:input path="areaNo" size="5" maxlength="4" />-
	      	<form:input path="middleTelNo" size="5" maxlength="4" />-
	      	<form:input path="endTelNo" size="5" maxlength="4" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<spring:message code="cop.mbtlNum" />
	    </th>
	    <td colspan="3">
	     	<form:select path="idntfcNo" class="select">
	  		   	<form:option value='' label="--식별번호--" />
			   	<form:option value="010" label="010" />
			   	<form:option value="011" label="011" />
			   	<form:option value="016" label="016" />
			   	<form:option value="017" label="017" />
			   	<form:option value="018" label="018" />
			   	<form:option value="019" label="019" />
	  	   	</form:select>
	  	   	-
	      	<form:input path="middleMbtlNum" size="5" maxlength="5" />-
	      	<form:input path="endMbtlNum" size="5" maxlength="5" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<spring:message code="cop.adres" />
	    </th>
	    <td colspan="3">
	      	<form:hidden path="zip" size="6" />
          	<input name="v_zip" size="7" readOnly value="<c:out value='${fn:substring(nameCardVO.zip,0,3)}-${fn:substring(nameCardVO.zip,3,6)}'/>"  />
            <a href="#" onclick="fn_aram_ZipSearch(document.forms[0].zip, document.forms[0].v_zip, document.forms[0].adres); return false;">
                <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="" />(지번 검색)
            </a>
            <a href="#" onclick="fn_aram_RdNmSearch(document.forms[0].zip, document.forms[0].v_zip, document.forms[0].adres); return false;">
                <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="" />(도로명 검색)
            </a>
	      	<br>
	      	<form:input path="adres" size="80" readonly="true" />
	      	<br>
	      	<form:input path="detailAdres" size="80" maxlength="80" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.othbcScope" />
	    </th>
	    <td colspan="3">
	     	<input type="radio" name="othbcScope" id="othbcScopeP" class="radio2" value="P"<c:if test="${nameCardVO.othbcScope == 'P'}"> checked</c:if> <c:if test="${writer != true}"> disabled</c:if>><label for="othbcScopeP">개인</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeC" class="radio2" value="C"<c:if test="${nameCardVO.othbcScope == 'C'}"> checked</c:if> <c:if test="${writer != true}"> disabled</c:if>><label for="othbcScopeC">커뮤니티</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeG" class="radio2" value="G"<c:if test="${nameCardVO.othbcScope == 'G'}"> checked</c:if> <c:if test="${writer != true}"> disabled</c:if>><label for="othbcScopeG">전체</label>
	     	<form:errors path="othbcScope" cssClass="error"/> 
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="remark"><spring:message code="cop.remark" /></label>
	    </th>
	    <td colspan="3">
	       	<form:textarea title="비고" path="remark" cols="75" rows="4" cssStyle="width:100%" />
	       	<form:errors path="remark" cssClass="error"/>
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
<validator:javascript formName="nameCardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/ccm/zip/ZipPopup.js"></script>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("nameCardVO");
    varForm.action = "${pageContext.request.contextPath}/cop/ncm/listNameCard.do";
    varForm.submit();
}

function fn_aram_update(){
    var varForm = document.getElementById("nameCardVO");

    if (!validateNameCardVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/ncm/updateNameCard.do";
		varForm.submit();
	}
}

function fn_aram_delete() {
    var varForm = document.getElementById("nameCardVO");

	if (confirm("<spring:message code='common.delete.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/cop/ncm/deleteNameCard.do";
    	varForm.submit();
    }
}

</script>

