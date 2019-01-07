<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : ProxySvcRegist.jsp
  * @Description : 프록시설정 등록
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
	<h2>프록시설정 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="proxySvcVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="프록시설정을 등록한다.">
<caption>프록시설정 등록</caption>
    <!--  
    <tr>
        <th width="20%">
     		<span class="required_icon"></span>
        	프록시ID
        </th>
        <td>
        	<form:input path="proxyId" size="30" class="readOnlyClass" readOnly="true" />
        </td>
    </tr>
    -->
    <tr>
        <th width="20%">
         	<span class="required_icon"></span>
        	프록시명
        </th>
        <td>
        	<form:input path="proxyNm" maxLength="60" size="60" />
        	<form:errors path="proxyNm" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	프록시IP
        </th>
        <td>
        	<form:input path="proxyIp" maxLength="23" size="23" />
        	<form:errors path="proxyIp" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	프록시Port
         </th>
        <td>
        	<form:input path="proxyPort" maxLength="10" size="10" />
        	<form:errors path="proxyPort" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	대상서비스명
        </th>
        <td>
        	<form:input path="trgetSvcNm" maxLength="30" size="30" />
        	<form:errors path="trgetSvcNm" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="norequired_icon"></span>
        	서비스설명
        </th>
        <td>
        	<form:input path="svcDc" maxLength="255" size="60" />
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	서비스IP
        </th>
        <td>
        	<form:input path="svcIp" maxLength="23" size="23" />
        	<form:errors path="svcIp" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	서비스Port
        </th>
        <td>
        	<form:input path="svcPort" maxLength="10" size="10" />
        	<form:errors path="svcPort" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
        	<span class="required_icon"></span>
        	서비스상태
        </th>
        <td>
          	<form:select path="svcSttus">
              	<form:option value="01" label="정상" />
              	<form:option value="03" label="중지" />
          	</form:select>         
          	<!--        
          	<form:select path="svcSttus">
                <form:options items="${COM072_svcSttus}" itemValue="code" itemLabel="codeNm"/>
          	</form:select>
          	--> 
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
<validator:javascript formName="proxySvcVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("proxySvcVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxySvc.do";
    varForm.submit();       
}

function fn_aram_insert() {
    var varForm = document.getElementById("proxySvcVO");
    
    if(!validateProxySvcVO(varForm)){           
        return;
    }

    if(!ipValidate(varForm.proxyIp.value))
        return;
    else if(!ipValidate(varForm.svcIp.value))
        return;

	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/insertProxySvc.do";
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
