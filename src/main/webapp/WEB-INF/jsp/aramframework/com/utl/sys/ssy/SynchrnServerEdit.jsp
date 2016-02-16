<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : SynchrnServerEdit.jsp
  * @Description : 동기화 서버 수정
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
	<h2>동기화 서버 수정</h2>
</div>
 
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="synchrnServerVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="동기화대상 서버정보를 수정한다.">
<caption>동기화대상 서버정보 수정</caption>
    <tr>
        <th width="20%">
         	<span class="required_icon"></span>
        	서버ID
        </th>
        <td>
        	<form:input path="serverId" size="30" class="readOnlyClass" readonly="true" />
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	서버명
        </th>
        <td>
        	<form:input path="serverNm" maxLength="30" size="30" />
        	<form:errors path="serverNm" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	서버 IP
        </th>
        <td>
        	<form:input path="serverIp" maxLength="30" size="30" />
        	<form:errors path="serverIp" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	서버 Port
        </th>
        <td>
        	<form:input path="serverPort" maxLength="30" size="30" />
        	<form:errors path="serverPort" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	FTP ID
        </th>
        <td>
        	<form:input path="ftpId" maxLength="30" size="30" />
        	<form:errors path="ftpId" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
         	<span class="required_icon"></span>
        	FTP 비밀번호
        </th>
        <td>
        	<form:input path="ftpPassword" maxLength="30" size="30" />
        	<form:errors path="ftpPassword" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
         	<span class="required_icon"></span>
        	동기화 위치
        </th>
        <td>
        	<form:input path="synchrnLc" maxLength="30" size="30" />
        	<form:errors path="synchrnLc" cssClass="error"/>
        </td>
    </tr>
</table> 
    
<!-- 검색조건 유지 -->
<form:hidden path="strSynchrnServerNm" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>
 
</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="synchrnServerVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("synchrnServerVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/listSynchrnServer.do";
    varForm.submit();       
}

function fn_aram_update() {
    var varForm = document.getElementById("synchrnServerVO");
    
    if(!validateSynchrnServerVO(varForm)){           
        return;
    }

    if(!ipValidate(varForm.serverIp.value))
        return;

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/updateSynchrnServer.do";
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
