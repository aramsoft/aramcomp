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
  * @Class Name : QustnrRespondEdit.jsp
  * @Description : 응답자정보 수정
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
	<h2>응답자정보 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrRespondManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrRespondId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 응답자정보를 제공하며, 설문관리정보, 성별, 직업, 생년월일, 응답자명, 전화번호 정보로 구성되어 있습니다 .">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		설문관리정보
    	</th>
    	<td width="80%">
			<c:out value="${fn:replace(qustnrRespondManageVO.qestnrSj , crlf , '<br/>')}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		성별
    	</th>
    	<td>
			<form:select path="sexdstnCode" title="성별 선택">
				<form:option value="" label="선택" />
				<form:options items="${COM014_sexdstn}" itemValue="code" itemLabel="codeNm"/>
			</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
      		<span class="required_icon"></span>
    		직업
    	</th>
    	<td>
			<form:select path="occpTyCode" title="직업 선택">
				<option value="">선택</option>
				<form:options items="${COM034_occpType}" itemValue="code" itemLabel="codeNm"/>
			</form:select>
			<form:errors path="occpTyCode" cssClass="error"/> 
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		생년월일
    	</th>
    	<td>
       		<select name="brthYYYY" id="brthYYYY" title="년도 선택">
	     		<c:forEach var="h" begin="1960" end="2009" step="1">
	      		<option value="${h}" <c:if test="${fn:substring(qustnrRespondManageVO.brth, 0, 4) ==  h}">selected</c:if>>${h}</option>
	      		</c:forEach>
       		</select>년
       		<select name="brthMM" id="brthMM" title="월 선택">
	     		<c:forEach var="h" begin="1" end="12" step="1">
				<c:choose>
			    <c:when test="${h < 10}">
			 		<c:set var="brthMM" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    	<c:set var="brthMM" value="${h}"/>
			    </c:otherwise>
				</c:choose>
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(qustnrRespondManageVO.brth, 4, 6) ==  brthMM}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>월
       		<select name="brthDD" id="brthDD" title="일 선택">
	     		<c:forEach var="h" begin="1" end="31" step="1">
				<c:choose>
			    <c:when test="${h < 10}">
			 		<c:set var="brthDD" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    	<c:set var="brthDD" value="${h}"/>
			    </c:otherwise>
				</c:choose>
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(qustnrRespondManageVO.brth, 6, 8) ==  brthDD}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>일
       		<form:hidden path="brth" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		응답자명
    	</th>
    	<td>
			<form:input path="respondNm" title="응답자명 입력" size="73" maxlength="50" style="width:120px;" />
			<form:errors path="respondNm" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		전화번호
    	</th>
    	<td>
      		<form:input path="areaNo" title="지역번호 입력" size="4" maxlength="4" />-
      		<form:input path="middleTelno" title="국번 입력" size="4"  maxlength="4" />-
      		<form:input path="endTelno" size="4" title="번호 입력" maxlength="4" />
      		<form:errors path="areaNo" cssClass="error" />
      		<form:errors path="middleTelno" cssClass="error" />
      		<form:errors path="endTelno" cssClass="error" />
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
<validator:javascript formName="qustnrRespondManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrRespondManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qrm/listQustnrRespond.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("qustnrRespondManageVO");
    
	if(!validateQustnrRespondManageVO(varForm)){
		return;
	}

	varForm.brth.value = fn_aram_SelectBoxValue('brthYYYY') + "" + fn_aram_SelectBoxValue('brthMM') + "" + fn_aram_SelectBoxValue('brthDD');

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/qrm/updateQustnrRespond.do";
		varForm.submit();
	}
}

</script>


