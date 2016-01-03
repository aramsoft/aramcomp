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
 * @Class Name : LoginPolicyEdit.jsp
 * @Description : 로그인정책 수정
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
	<h2>로그인정책 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<form:form commandName="loginPolicyVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="로그인정책을 수정한다.">
<caption>로그인정책 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		사용자ID	
    	</th>
    	<td width="80%">
    		<form:input path="emplyrId" title="사용자ID" size="30" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		사용자명
    	</th>
    	<td>
    		<form:input path="emplyrNm" title="사용자명" maxLength="50" size="30" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="ipInfo">IP정보</label>
    	</th>
    	<td>
    		<form:input path="ipInfo" title="IP정보" maxLength="23" size="30" />
    		<form:errors path="ipInfo" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		IP제한여부
    	</th>
    	<td>
      		<form:select path="lmttAt" title="IP제한여부"> 
          		<form:option value="Y" label="Y" />
          		<form:option value="N" label="N" />
      		</form:select>
      		<form:errors path="lmttAt" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		등록일시
    	</th>
    	<td>
    		<form:input path="regDate" title="등록일시" maxLength="50" size="20" readonly="true" />
    	</td>
  	</tr>
</table>

<form:hidden path="dplctPermAt" value="Y" />

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="loginPolicyVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" defer="defer">

function fn_aram_list() {
    var varForm = document.getElementById("loginPolicyVO");
    varForm.action = "${pageContext.request.contextPath}/uat/uap/listLoginPolicy.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("loginPolicyVO");
    
    if(!validateLoginPolicyVO(varForm)){
        return;
    }
    
    if(!ipValidate()) { return; }

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uat/uap/updateLoginPolicy.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("loginPolicyVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uat/uap/deleteLoginPolicy.do";
    	varForm.submit();
    }
}

function ipValidate() {

    var varForm = document.getElementById("loginPolicyVO");
    var IPvalue = varForm.ipInfo.value;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = false;
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
        alert("형식이 일치 하지않습니다. ");
        result = false;
    } else {
        for (var i=1; i<5; i++) {

            thisSegment = ipArray[i];

            if (thisSegment> 255) {
                alert("형식이 일치 하지않습니다. ");
                result = false;
            }

            if ((i == 0) && (thisSegment> 255)) {
                alert("형식이 일치 하지않습니다. ");
                result = false;
            }
        }
    }

    return result;
}

</script>


