<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : MberRegist.jsp
  * @Description : 일반회원 등록
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
	<h2>일반회원 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_reset(); return false;"><spring:message code="button.reset" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="mberManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register">
    <tr>
        <th width="20%">
			<span class="required_icon"></span>
			일반회원아이디
        </th>
        <td width="80%">
            <form:input path="mberId" title="일반회원아이디" size="20" readonly="true" maxlength="20" />
            <a href="#" onclick="fn_aram_idCheck(); return false;">
                <img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" alt="" />(중복아이디 검색)
            </a>
            <form:errors path="mberId" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="mberNm">일반회원이름</label>
        </th>
        <td>
        	<form:input path="mberNm" title="일반회원이름" size="20" maxlength="60" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="password">비밀번호</label>
        </th>
        <td>
            <form:password path="password" title="비밀번호" size="20" maxlength="20" />
            <form:errors path="password" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="password2">비밀번호확인</label>
        </th>
        <td>
            <input name="password2" id="password2" title="비밀번호확인" type="password" size="20" maxlength="20" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="passwordHint">비밀번호힌트</label>
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
            <form:input path="passwordCnsr" title="비밀번호정답" cssClass="txaIpUmt" size="50" maxlength="100" />
            <form:errors path="passwordCnsr" cssClass="error"/>
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="mberEmailAdres">이메일주소</label>
        </th>
        <td>
            <form:input path="mberEmailAdres" title="이메일주소" size="40" maxlength="50" />
            <form:errors path="mberEmailAdres" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
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
			전화번호
        </th>
        <td>
            <form:input path="areaNo" title="전화번호"  size="4" maxlength="3" />
            - <form:input path="middleTelno" size="5" maxlength="4" />
            - <form:input path="endTelno" size="5" maxlength="4" />
            <form:errors path="areaNo" cssClass="error" />
            <form:errors path="middleTelno" cssClass="error" />
            <form:errors path="endTelno" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
        	<label for="mberFxnum">팩스번호</label>
        </th>
        <td>
            <form:hidden path="mberFxnum" size="20" maxlength="15" title="팩스번호"/>
            <input type="text" name="mberFxnum1" id="mberFxnum1" size="4" maxlength="3" title="지역번호"/>
            - <input type="text" name="mberFxnum2" id="mberFxnum2" size="5" maxlength="4" title="중간번호"/>
            - <input type="text" name="mberFxnum3" id="mberFxnum3" size="5" maxlength="4" title="끝자리번호"/>
            <form:errors path="mberFxnum" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
			우편번호
        </th>
        <td>
			<c:if test="${fn:length(mberManageVO.zip) != 0}"><c:set var="zipValue" value="${fn:substring(mberManageVO.zip,0,3)}-${fn:substring(mberManageVO.zip,3,6)}"/></c:if>
            <input name="zip_view" type="text" size="7" value="${zipValue}" maxlength="8" readonly title="우편번호입력">
            <form:hidden path="zip" />
            <a href="#" onclick="fn_aram_ZipSearch(document.forms[0].zip, document.forms[0].zip_view, document.forms[0].adres); return false;">
                <img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" alt="" />(지번 검색)
            </a>
            <a href="#" onclick="fn_aram_RdNmSearch(document.forms[0].zip, document.forms[0].zip_view, document.forms[0].adres); return false;">
                <img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" alt="" />(도로명 검색)
            </a>
            <form:errors path="zip" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
			<label for="adres">주소</label>
        </th>
        <td>
            <form:input path="adres" title="주소" size="60" maxlength="60" />
            <form:errors path="adres" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
        	<label for="detailAdres">상세주소</label>
        </th>
        <td>
            <form:input path="detailAdres" title="상세주소" size="60" maxlength="60" />
            <form:errors path="detailAdres" cssClass="error" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			일반회원상태코드
        </th>
        <td>
            <form:select path="mberSttus" title="일반회원상태코드">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM013_mberSttus}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="mberSttus" cssClass="error"/>
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
<validator:javascript formName="mberManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/ccm/zip/ZipPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
    var varForm = document.getElementById("mberManageVO");
	if( varForm.mberFxnum.value != "" ) {
    	var mberFxnum = varForm.mberFxnum.value.split("-");
		varForm.mberFxnum1.value = mberFxnum[0];
		varForm.mberFxnum2.value = mberFxnum[1];
		varForm.mberFxnum3.value = mberFxnum[2];
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
    var varForm = document.getElementById("mberManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listMber.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("mberManageVO");
	
	// 전화번호 조정
	if( varForm.mberFxnum1.value != "" ) {
		varForm.mberFxnum.value = varForm.mberFxnum1.value + "-" 
								+ varForm.mberFxnum2.value + "-" 
								+ varForm.mberFxnum3.value;
	}
	if( varForm.moblphonNo1.value != "" ) {
		varForm.moblphonNo.value = varForm.moblphonNo1.value + "-" 
								 + varForm.moblphonNo2.value + "-" 
								 + varForm.moblphonNo3.value;
	}
	
	if(!validateMberManageVO(varForm)){
		return;	
	}

	if(varForm.password.value != varForm.password2.value){
        alert("<spring:message code="fail.user.passwordUpdate2" />");
        return;
    }

	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/umt/insertMber.do";
		varForm.submit();
    }
}

function fn_aram_reset(){
    var varForm = document.getElementById("mberManageVO");
    varForm.reset();
}

var gArguments = new Array();

/* ********************************************************
 * 아이디  팝업창열기
 ******************************************************** */
function fn_aram_idCheck(){
    var varForm = document.getElementById("mberManageVO");
	gArguments["userId"] = varForm.mberId;

	var url = "/uss/umt/checkIdDplctView.do";

	window.open(url, "p_checkid", "width=320px,height=180px,top=100px,left=100px,location=no");
}

</script>

