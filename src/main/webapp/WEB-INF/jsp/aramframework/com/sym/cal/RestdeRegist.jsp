<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : RestdeRegist.jsp
  * @Description : 휴일 등록
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
	<h2>휴일 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="restdeVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="휴일 일자, 휴일명, 휴일설명, 휴일구분을 입력하여 휴일을 등록한다.">
<CAPTION>휴일 등록</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		휴일일자
    	</th>
    	<td width="80%">
    		<form:hidden path="restdeDe" />
	    	<c:if test="${!empty restdeVO.restdeDe}">
 				<c:set var="appYmdVal" value="${fn:substring(restdeVO.restdeDe, 0,4)}-${fn:substring(restdeVO.restdeDe, 4,6)}-${fn:substring(restdeVO.restdeDe, 6,8)}"/>
      		</c:if>
      		<input name="restdeDeView" id="restdeDeView" type="text" size="10" title="<spring:message code="sym.cal.restDay" />(새창)" value="${restdeDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].restdeDe, document.forms[0].restdeDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
   		</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		휴일명
    	</th>
    	<td>
      		<form:input  path="restdeNm" size="50" maxlength="50" title="휴일명"/>
      		<form:errors path="restdeNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		휴일설명
    	</th>
    	<td>
      		<form:textarea path="restdeDc" rows="3" cols="60" title="휴일설명"/>
      		<form:errors   path="restdeDc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		휴일구분
    	</th>
    	<td>
      		<form:select path="restdeSeCode" title="휴일구분">
	      		<form:options items="${COM017_restde}" itemValue="code" itemLabel="codeNm" />
      		</form:select>
		</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${restdeVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${restdeVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${restdeVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${restdeVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="restdeVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("restdeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/cal/listRestde.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("restdeVO");
	
	if(!validateRestdeVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/sym/cal/insertRestde.do";
		varForm.submit();
	}
}

</script>
