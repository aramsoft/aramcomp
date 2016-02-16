<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : ServerEqpmnEdit.jsp
 * @Description : 서버H/W 수정
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
	<h2>서버H/W 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="serverEqpmnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="서버H/W를 수정한다.">
<caption>서버H/W 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		서버H/W ID
    	</th>
    	<td width="80%">
    		<form:input path="serverEqpmnId" size="20" class="readOnlyClass" readOnly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		서버H/W 명
    	</th>
    	<td>
    		<form:input path="serverEqpmnNm" maxLength="30" size="30" />
    		<form:errors path="serverEqpmnNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		서버H/W IP
    	</th>
    	<td>
    		<form:input path="serverEqpmnIp" maxLength="23" size="23" />
    		<form:errors path="serverEqpmnIp" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		관리자
    	</th>
    	<td>
    		<form:input path="serverEqpmnMngrNm" maxLength="30" size="30" />
    		<form:errors path="serverEqpmnMngrNm" cssClass="error"/>
     	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		관리자이메일주소
    	</th>
    	<td>
    		<form:input path="mngrEmailAddr" maxLength="50" size="50" />
    		<form:errors path="mngrEmailAddr" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		OS정보
    	</th>
    	<td>
    		<form:input path="opersysmInfo" maxLength="1000" size="80" />
    		<form:errors path="opersysmInfo" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		CPU정보
    	</th>
    	<td>
    		<form:input path="cpuInfo" maxLength="1000" size="80" />
    		<form:errors path="cpuInfo" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		메모리정보
    	</th>
    	<td>
    		<form:input path="moryInfo" maxLength="1000" size="80" />
    		<form:errors path="moryInfo" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		하드디스크
    	</th>
    	<td>
    		<form:input path="hdDisk" maxLength="1000" size="80" />
    		<form:errors path="hdDisk" cssClass="error"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
			<span class="norequired_icon"></span>
    		기타정보
    	</th>
    	<td>
    		<form:input path="etcInfo" maxLength="1000" size="80" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		등록일자
    	</th>
    	<td>
      		<form:hidden path="regstYmd" />
	    	<c:if test="${!empty serverEqpmnVO.regstYmd}">
 				<c:set var="regstYmdVal" value="${fn:substring(serverEqpmnVO.regstYmd, 0,4)}-${fn:substring(serverEqpmnVO.regstYmd, 4,6)}-${fn:substring(serverEqpmnVO.regstYmd, 6,8)}"/>
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
<form:hidden path="strServerEqpmnNm" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="serverEqpmnVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("serverEqpmnVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServerEqpmn.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update() {
    var varForm = document.getElementById("serverEqpmnVO");
    
    if(!validateServerEqpmnVO(varForm)){
        return;
    }
    
    if(!ipValidate(varForm.serverEqpmnIp.value))
        return;

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/updateServerEqpmn.do";
        varForm.submit();
    }
}

function ipValidate(ipValue) {

    var IPvalue = ipValue;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + "는 예외 아이피 입니다..");
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + "는 예외 아이피 입니다.");
        result = false;
    } else {
        result = true;
    }

    if(ipArray == null) {
        alert("IP 형식이 일치 하지않습니다. ");
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment> 255) {
                alert("IP 형식이 일치 하지않습니다. ");
                result = false;
            }

            if ((i == 0) && (thisSegment> 255)) {
                alert("IP 형식이 일치 하지않습니다. ");
                result = false;
            }
        }
    }

    return result;
}

</script>
