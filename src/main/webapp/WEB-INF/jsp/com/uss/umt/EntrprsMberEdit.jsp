<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EntrprsMberEdit.jsp
  * @Description : 기업회원 수정
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>기업회원 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<c:if test="${isAdmin=='true'}">
				<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
				<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
			</c:if>
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit_password(); return false;"><spring:message code="button.passwordUpdate" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="entrprsManageVO" action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
<input name="checkedIdForDel" type="hidden" />

<form:hidden path="sbscrbSttus" />
<form:hidden path="userTy" />

<input name="password" type="hidden" value="dummyPassword"/>

<table class="table-register">
    <tr>
        <th width="20%">
			<span class="required_icon"></span>
			<label for="entrprsmberId">기업회원아이디</label>
        </th>
        <td width="80%">
        	<form:input path="entrprsmberId" size="20" readonly="true" maxlength="20" />
            <form:errors path="entrprsmberId" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="cmpnyNm">회사명</label>
        </th>
        <td>
            <form:input path="cmpnyNm" size="45" maxlength="50" />
            <form:errors path="cmpnyNm" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="passwordHint">비밀번호힌트</label>
        </th>
        <td>
            <form:select path="passwordHint">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM022_passwordHint}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="passwordHint" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="passwordCnsr">비밀번호정답</label>
        </th>
        <td>
            <form:input path="passwordCnsr" size="50" maxlength="100" />
            <form:errors path="passwordCnsr" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="bizrno">사업자등록번호</label>
        </th>
        <td>
            <form:hidden path="bizrno" size="20" maxlength="12" title="사업자등록번호"/>
            <input type="text" name="bizrno1" id="bizrno1" size="4" maxlength="3" title="처음번호"/>
            - <input type="text" name="bizrno2" id="bizrno2" size="3" maxlength="2" title="중간번호"/>
            - <input type="text" name="bizrno3" id="bizrno3" size="6" maxlength="5" title="끝자리번호"/>
            <form:errors path="bizrno" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			회사전화번호
        </th>
        <td>
            <form:input path="areaNo" size="4" maxlength="3" />
            - <form:input path="entrprsMiddleTelno" size="5" maxlength="4" />
            - <form:input path="entrprsEndTelno" size="5" maxlength="4" />
            <form:errors path="areaNo" cssClass="error"/>
            <form:errors path="entrprsMiddleTelno" cssClass="error"/>
            <form:errors path="entrprsEndTelno" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="applcntNm">담당자이름</label>
        </th>
        <td>
            <form:input path="applcntNm" size="20" maxlength="20" />
            <form:errors path="applcntNm" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="applcntEmailAdres">담당자이메일주소</label>
        </th>
        <td>
            <form:input path="applcntEmailAdres" size="40" maxlength="50" />
            <form:errors path="applcntEmailAdres" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
 			<span class="norequired_icon"></span>
        	업종코드
        </th>
        <td>
            <form:select path="indutyCode">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM027_induty}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="indutyCode" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
 			<span class="norequired_icon"></span>
        	기업구분코드
        </th>
        <td>
            <form:select path="entrprsSeCode">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM026_entrprsSe}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="entrprsSeCode" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
 			<span class="norequired_icon"></span>
        	<label for="cxfc">대표이사이름</label>
        </th>
        <td>
            <form:input path="cxfc" size="20"  maxlength="8" />
            <form:errors path="cxfc" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
 			<span class="norequired_icon"></span>
        	<label for="jurirno">법인등록번호</label>
        </th>
        <td>
            <form:input path="jurirno" size="20" maxlength="13" />
            <form:errors path="jurirno" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
   			<span class="norequired_icon"></span>
        	<label for="fxnum">팩스번호</label>
        </th>
        <td>
            <form:hidden path="fxnum" size="20" maxlength="15" title="팩스번호"/>
            <input type="text" name="fxnum1" id="fxnum1" size="4" maxlength="3" title="지역번호"/>
            - <input type="text" name="fxnum2" id="fxnum2" size="5" maxlength="4" title="중간번호"/>
            - <input type="text" name="fxnum3" id="fxnum3" size="5" maxlength="4" title="끝자리번호"/>
            <form:errors path="fxnum" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
			우편번호
        </th>
        <td>
			<c:if test="${fn:length(entrprsManageVO.zip) != 0}">
				<c:set var="zipValue" value="${fn:substring(entrprsManageVO.zip,0,3)}-${fn:substring(entrprsManageVO.zip,3,6)}"/>
			</c:if>
            <input name="zip_view" type="text" size="7" value="${zipValue}" maxlength="8" readonly title="우편번호입력">
            <form:hidden path="zip" />
            <a href="#" onclick="fn_aram_ZipSearch(document.forms[0].zip, document.forms[0].zip_view, document.forms[0].adres); return false;">
                <img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" alt="" />(지번 검색)
            </a>
            <a href="#" onclick="fn_aram_RdNmSearch(document.forms[0].zip, document.forms[0].zip_view, document.forms[0].adres); return false;">
                <img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" alt="" />(도로명 검색)
            </a>
            <form:errors path="zip" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
			<label for="adres">주소</label>
        </th>
        <td>
            <form:input path="adres" size="60" maxlength="60" />
            <form:errors path="adres" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
			<label for="detailAdres">상세주소</label>
		</th>
        <td>
            <form:input path="detailAdres" size="60"  maxlength="60" />
            <form:errors path="detailAdres" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
			<label for="googleAccount">구글계정</label>
        </th>
        <td>
            <form:input path="googleAccount" size="40" maxlength="50" readonly="true" />
            <form:errors path="googleAccount" cssClass="error"/>
        </td>
    </tr>
	<c:choose>
	<c:when test="${isAdmin=='true'}">
    <tr>
        <th>
			<span class="required_icon"></span>
			기업회원상태코드
        </th>
        <td>
            <form:select path="entrprsMberSttus">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM013_mberSttus}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="entrprsMberSttus" cssClass="error"/>
        </td>
    </tr>
	</c:when>
	<c:otherwise>
        <form:hidden path="entrprsMberSttus" title="사용자상태코드" />
	</c:otherwise>
	</c:choose>
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
<validator:javascript formName="entrprsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/ccm/zip/ZipPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
    var varForm = document.getElementById("entrprsManageVO");
	if( varForm.fxnum.value != "" ) {
    	var fxnum = varForm.fxnum.value.split("-");
		varForm.fxnum1.value = fxnum[0];
		varForm.fxnum2.value = fxnum[1];
		varForm.fxnum3.value = fxnum[2];
	}	
	if( varForm.bizrno.value != "" ) {
    	var bizrno = varForm.bizrno.value.split("-");
		varForm.bizrno1.value = bizrno[0];
		varForm.bizrno2.value = bizrno[1];
		varForm.bizrno3.value = bizrno[2];
	}	
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("entrprsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listEntrprsMber.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("entrprsManageVO");
    // 전화번호 조정
	if( varForm.fxnum1.value != "" ) {
		varForm.fxnum.value = varForm.fxnum1.value + "-" 
							+ varForm.fxnum2.value + "-" 
							+ varForm.fxnum3.value;
	}
    // 사업자등록번호 조정
	if( varForm.bizrno1.value != "" ) {
		varForm.bizrno.value = varForm.bizrno1.value + "-" 
							 + varForm.bizrno2.value + "-" 
							 + varForm.bizrno3.value;
	}
   
    if(!validateEntrprsManageVO(varForm)){
        return;
    }
   
	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/umt/updateEntrprsMber.do.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("entrprsManageVO");
    
	if(confirm("<spring:message code='common.delete.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/uss/umt/deleteEntrprsMber.do";
		varForm.submit();
	}
}

function fn_aram_edit_password(){
    var varForm = document.getElementById("entrprsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/editEntrprsMberPassword.do";
    varForm.submit();
}

</script>

