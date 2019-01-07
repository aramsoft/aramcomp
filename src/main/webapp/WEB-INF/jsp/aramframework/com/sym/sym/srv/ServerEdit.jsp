<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : ServerEdit.jsp
 * @Description : 서버S/W 수정
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
	<h2>서버S/W 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="serverVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="서버S/W를 수정한다.">
<caption>서버S/W 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		서버S/W ID
    	</th>
    	<td>
    		<form:input path="serverId" size="30" class="readOnlyClass" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		서버S/W 명
    	</th>
    	<td>
    		<form:input path="serverNm" maxLength="30" size="30" />
    		<form:errors path="serverNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		서버S/W 종류
    	</th>
    	<td>
   			<form:select path="serverKnd">
                <form:options items="${COM064_serverKnd}" itemValue="code" itemLabel="codeNm"/>
	   		</form:select>	   
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		등록일자
    	</th>
    	<td>
      		<form:hidden path="regstYmd" />
	    	<c:if test="${!empty serverVO.regstYmd}">
 				<c:set var="regstYmdVal" value="${fn:substring(serverVO.regstYmd, 0,4)}-${fn:substring(serverVO.regstYmd, 4,6)}-${fn:substring(serverVO.regstYmd, 6,8)}"/>
      		</c:if>
      		<input name="regstYmdView" id="regstYmdView" type="text" size="10" title="등록일자" value="${regstYmdVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].regstYmd, document.forms[0].regstYmdView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
        	<form:errors path="regstYmd" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="strServerNm" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="serverVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("serverVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServer.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update() {
    var varForm = document.getElementById("serverVO");

    if(!validateServerVO(varForm)){
        return;
    }

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/updateServer.do";
        varForm.submit();
    }
}

</script>
