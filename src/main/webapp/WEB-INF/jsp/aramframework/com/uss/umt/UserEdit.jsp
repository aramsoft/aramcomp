<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : UserEdit.jsp
  * @Description : 업무사용자 수정
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
	<h2>업무사용자 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<c:if test="${isAdmin=='true'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit_password(); return false;"><spring:message code="button.passwordUpdate" /></a></span>
	</div>
</div>

<form:form modelAttribute="userManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
<input type="hidden" name="checkedIdForDel"  />

<form:hidden path="sbscrbSttus" />

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${userManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${userManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${userManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${userManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->

<form:hidden path="userTy" />
<input name="password" type="hidden" value="dummyPassword"/>

<table class="table-register">
    <tr>
        <th width="20%">
			<span class="required_icon"></span>
			사용자아이디
        </th>
        <td width="80%">
            <form:input path="emplyrId" size="20" maxlength="20" readonly="true" title="사용자아이디" />
            <form:errors path="emplyrId" cssClass="error"/>
            <form:hidden path="uniqId" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="emplyrNm">사용자이름</label>
        </th>
        <td>
            <form:input path="emplyrNm" size="20"  maxlength="60" title="사용자이름" />
            <form:errors path="emplyrNm" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			비밀번호힌트
        </th>
        <td>
            <form:select path="passwordHint" title="비밀번호힌트">
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
            <form:input path="passwordCnsr" size="50" maxlength="100" title="비밀번호정답"/>
            <form:errors path="passwordCnsr" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
  			<span class="norequired_icon"></span>
        	소속기관코드
        </th>
        <td>
            <form:select path="insttCode" title="소속기관코드">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM025_instt}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="insttCode" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
 			<span class="norequired_icon"></span>
        	조직아이디
        </th>
        <td>
            <form:select path="orgnztId" title="조직아이디">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${orgnztId_result}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="orgnztId" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
  			<span class="norequired_icon"></span>
        	<label for="ofcpsNm">직위명</label>
        </th>
        <td>
            <form:input path="ofcpsNm" size="20" maxlength="50" title="직위명"/>
            <form:errors path="ofcpsNm" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
  			<span class="norequired_icon"></span>
        	<label for="emplNo">사번</label>
        </th>
        <td>
            <form:input path="emplNo" size="20" maxlength="20" title="사번"/>
            <form:errors path="emplNo" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
  			<span class="norequired_icon"></span>
        	성별구분코드
        </th>
        <td>
            <form:select path="sexdstnCode" title="성별구분코드">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM014_sexdstn}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
        </td>
    </tr>
    <tr>
        <th>
  			<span class="norequired_icon"></span>
        	<label for="brth">생일</label>
        </th>
        <td>
            <form:input path="brth" size="20" maxlength="8" title="생일"/>
            <form:errors path="brth" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			집전화번호
        </th>
        <td>
            <form:input path="areaNo" size="4" maxlength="3" title="지역번호"/>
            - <form:input path="homemiddleTelno" size="5" maxlength="4" title="중간번호"/>
            - <form:input path="homeendTelno" size="5" maxlength="4" title="끝자리번호"/>
            <form:errors path="areaNo" cssClass="error" />
            <form:errors path="homemiddleTelno" cssClass="error" />
            <form:errors path="homeendTelno" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
        	<label for="offmTelno">사무실전화번호</label>
        </th>
        <td>
            <form:hidden path="offmTelno" size="20" maxlength="15" title="사무실전화번호"/>
            <input type="text" name="offmTelno1" id="offmTelno1" size="4" maxlength="3" title="지역번호"/>
            - <input type="text" name="offmTelno2" id="offmTelno2" size="5" maxlength="4" title="중간번호"/>
            - <input type="text" name="offmTelno3" id="offmTelno3" size="5" maxlength="4" title="끝자리번호"/>
            <form:errors path="offmTelno" cssClass="error" />
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
            <form:errors path="fxnum" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
 			<span class="norequired_icon"></span>
        	<label for="moblphonNo">핸드폰번호</label>
        </th>
        <td>
            <form:hidden path="moblphonNo" size="20" maxlength="15" title="핸드폰번호"/>
            <input type="text" name="moblphonNo1" id="moblphonNo1" size="4" maxlength="3" title="지역번호"/>
            - <input type="text" name="moblphonNo2" id="moblphonNo2" size="5" maxlength="4" title="중간번호"/>
            - <input type="text" name="moblphonNo3" id="moblphonNo3" size="5" maxlength="4" title="끝자리번호"/>
            <form:errors path="moblphonNo" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
         	<span class="required_icon"></span>
        	<label for="emailAdres">이메일주소</label>
        </th>
        <td>
            <form:input path="emailAdres" size="40" maxlength="50" title="이메일주소"/>
            <form:errors path="emailAdres" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
          	<span class="required_icon"></span>
        	우편번호
        </th>
        <td>
			<c:if test="${fn:length(userManageVO.zip) != 0}"><c:set var="zipValue" value="${fn:substring(userManageVO.zip,0,3)}-${fn:substring(userManageVO.zip,3,6)}"/></c:if>
            <input name="zip_view" type="text" size="7" value="${zipValue}" maxlength="8" readonly title="우편번호입력">
            <form:hidden path="zip" />
            <a href="#" onclick="fn_aram_ZipSearch(document.forms[0].zip, document.forms[0].zip_view, document.forms[0].homeadres); return false;">
                <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="" />(지번 검색)
            </a>
            <a href="#" onclick="fn_aram_RdNmSearch(document.forms[0].zip, document.forms[0].zip_view, document.forms[0].homeadres); return false;">
                <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="" />(도로명 검색)
            </a>
            <form:errors path="zip" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
       		 <span class="required_icon"></span>
       		 주소
        </th>
        <td>
            <form:input path="homeadres" size="60" maxlength="60" readonly="true" title="주소"/>
            <form:errors path="homeadres" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
        	<label for="detailAdres">상세주소</label>
        </th>
        <td>
            <form:input path="detailAdres" size="60" maxlength="60" title="상세주소"/>
            <form:errors path="detailAdres" cssClass="error" />
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
        	사용자상태코드
        </th>
        <td>
            <form:select path="emplyrSttusCode" title="사용자상태코드">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM013_mberSttus}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="emplyrSttusCode" cssClass="error"/>
        </td>
    </tr>
	</c:when>
	<c:otherwise>
        <form:hidden path="emplyrSttusCode" title="사용자상태코드" />
	</c:otherwise>
	</c:choose>
    <tr>
        <th>
  			<span class="norequired_icon"></span>
        	사용자DN
        </th>
        <td>
            <form:input path="subDn" size="40" maxlength="100" title="사용자DN"/>
            <a href="#" onclick="fn_aram_detail_cert(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
            	<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" alt="search"/>
            </a>
            <form:errors path="subDn" cssClass="error" />
        </td>
    </tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/ccm/zip/ZipPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
    var varForm = document.getElementById("userManageVO");
	if( varForm.offmTelno.value != "" ) {
    	var offmTelno = varForm.offmTelno.value.split("-");
		varForm.offmTelno1.value = offmTelno[0];
		varForm.offmTelno2.value = offmTelno[1];
		varForm.offmTelno3.value = offmTelno[2];
	}	
	if( varForm.fxnum.value != "" ) {
    	var fxnum = varForm.fxnum.value.split("-");
		varForm.fxnum1.value = fxnum[0];
		varForm.fxnum2.value = fxnum[1];
		varForm.fxnum3.value = fxnum[2];
	}	
	if( varForm.moblphonNo.value != "" ) {
    	var moblphonNo = varForm.moblphonNo.value.split("-");
		varForm.moblphonNo1.value = moblphonNo[0];
		varForm.moblphonNo2.value = moblphonNo[1];
		varForm.moblphonNo3.value = moblphonNo[2];
	}	
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("userManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listUser.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("userManageVO");
	
	// 전화번호 조정
	if( varForm.offmTelno1.value != "" ) {
		varForm.offmTelno.value = varForm.offmTelno1.value + "-" 
								+ varForm.offmTelno2.value + "-" 
								+ varForm.offmTelno3.value;
	}
	if( varForm.fxnum1.value != "" ) {
		varForm.fxnum.value = varForm.fxnum1.value + "-" 
							+ varForm.fxnum2.value + "-" 
							+ varForm.fxnum3.value;
	}
	if( varForm.moblphonNo1.value != "" ) {
		varForm.moblphonNo.value = varForm.moblphonNo1.value + "-" 
								 + varForm.moblphonNo2.value + "-" 
								 + varForm.moblphonNo3.value;
	}
	
	if(!validateUserManageVO(varForm)){
        return;
    }

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/umt/updateUser.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("userManageVO");
    
	if(confirm("<spring:message code='common.delete.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/uss/umt/deleteUser.do";
		varForm.submit();
	}
}

function fn_aram_edit_password(){
    var varForm = document.getElementById("userManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/editUserPassword.do";
    varForm.submit();
}

function fn_aram_detail_cert() {
	var url = "${pageContext.request.contextPath}/uat/uia/GpkiRegist.do";
	var popupwidth = '500';
	var popupheight = '400';
	var title = '인증서';

	Top = (window.screen.height - popupheight) / 3;
	Left = (window.screen.width - popupwidth) / 2;
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,	scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	PopUpWindow = window.open(url, title, Future)
	PopUpWindow.focus();
}

function fn_aram_dn_info_setting(dn) {
	var vForm = document.userManageVO;
	vForm.subDn.value = dn;
}

</script>

