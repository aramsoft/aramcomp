<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : NtwrkRegist.jsp
 * @Description : 네트워크 등록
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
	<h2>네트워크 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="ntwrkVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="네트워크 정보를 등록한다.">
<caption>네트워크 등록</caption>
  	<!--
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		네트워크ID
    	</th>
    	<td width="80%">
    		<form:input path="ntwrkId" maxLength="23" size="23" readonly="true" />
    	</td>
  	</tr>
   -->
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		IP
    	</th>
    	<td width="80%">
     		<form:input path="ntwrkIp" maxLength="23" size="23" />
     		<form:errors path="ntwrkIp" cssClass="error"/>
     	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		게이트웨이
    	</th>
    	<td>
    		<form:input path="gtwy"  maxLength="23" size="23" />
    		<form:errors path="gtwy" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		서브넷
    	</th>
    	<td>
    		<form:input path="subnet" maxLength="23" size="23" />
    		<form:errors path="subnet" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		DNS
    	</th>
    	<td>
    		<form:input path="domnServer" maxLength="23" size="23" />
    		<form:errors path="domnServer" cssClass="error"/>
     	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		관리항목
    	</th>
    	<td>
   			<form:select path="manageIem">
                <form:options items="${COM067_manageIem}" itemValue="code" itemLabel="codeNm"/>
	   		</form:select>	   
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		사용자명
    	</th>
    	<td>
    		<form:input path="userNm" maxLength="30" size="30" />
    		<form:errors path="userNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		사용여부
    	</th>
    	<td>
   			<form:select path="useAt">
		   		<form:option value='Y' label="Y" />
		   		<form:option value='N' label="N" />
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
	    	<c:if test="${!empty ntwrkVO.regstYmd}">
 				<c:set var="regstYmdVal" value="${fn:substring(ntwrkVO.regstYmd, 0,4)}-${fn:substring(ntwrkVO.regstYmd, 4,6)}-${fn:substring(ntwrkVO.regstYmd, 6,8)}"/>
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
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="ntwrkVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("ntwrkVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/listNtwrk.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("ntwrkVO");
    
    if(!validateNtwrkVO(varForm)){
        return;
    } 

	if(!ipValidate(varForm.ntwrkIp.value, ''))
        return;
    else if(!ipValidate(varForm.gtwy.value, '게이트웨이'))
        return;
    else if(!ipValidate(varForm.subnet.value, '서브넷'))
        return;
    else if(!ipValidate(varForm.domnServer.value, 'DNS'))
        return;
    	
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/insertNtwrk.do";
        varForm.submit();
    }
}

function ipValidate(ipValue, ipName) {

    var varForm = document.getElementById("ntwrk");
    // var IPvalue = varForm.gtwy.value;
    var IPvalue = ipValue;
    var IPName = ipName;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = true;
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
        alert(IPName+" IP 형식이 일치 하지않습니다. ");
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment> 255) {
                alert(IPName+" IP 형식이 일치 하지않습니다. ");
                result = false;
            }

            if ((i == 0) && (thisSegment> 255)) {
                alert(IPName+" IP 형식이 일치 하지않습니다. ");
                result = false;
            }
        }
    }

    return result;
}

</script>
