<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : AdressBookRegist.jsp
  * @Description : 주소록 등록
  * @Modification Information
  *
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
	<h2>주소록 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
    	<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="adressBookVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="adbkId" />
<form:hidden path="userIds" />
<input type="hidden" name="checkWord" value="">
<input type="hidden" name="checkCnd"  value="">

<table class="table-register" summary="주소록 등록할 데이터 항목을 보여준다.">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="adbkNm"><spring:message code="cop.adbkNm" /></label>
	    </th>
	    <td width="80%">
	     	<form:input path="adbkNm" size="30" maxlength="50" style="width: 338px" title="주소록명입력" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.othbcScope" />
	    </th>
	    <td>
	     	<label for="othbcScopeI">개인</label> : <input type="radio" name="othbcScope" id="othbcScopeI" class="radio2" checked value="개인">&nbsp;&nbsp;&nbsp;
	     	<label for="othbcScopeD">부서</label> : <input type="radio" name="othbcScope" id="othbcScopeD" class="radio2" value="부서">&nbsp;&nbsp;&nbsp;
	     	<label for="othbcScopeC">전체</label> : <input type="radio" name="othbcScope" id="othbcScopeC" class="radio2" value="전체">
 	    </td>
	</tr>
    <tr>
        <th>
			<span class="norequired_icon"></span>
        	<label for="trgetOrgnztId">공개부서선택</label>
        </th>
        <td>
            <form:select path="trgetOrgnztId" title="부서아이디">
                <form:options items="${orgnztList}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
            <form:errors path="trgetOrgnztId" cssClass="error"/>
        </td>
    </tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="adbkUser"><spring:message code="cop.adbkUser" /></label>
	    </th>
	    <td>
	      	<input name="adbkUser" type="text" size="90" value=""  maxlength="90" style="width: 181px" readonly class="readOnlyClass" title="주소록구성원입력">
	      	<a href="#" onclick="javascript:fn_aram_get_user(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	      		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" style="vertical-align: middle" alt="주소록구성원찾기조회팝업 제공">
	      	</a>
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

<div style="margin-top:10px; width:100%"></div>

<table class="table-list" summary="주소록에  대한 주소록구성원 목록을 제공한다.">
<thead>
	<tr>
	    <th width="15%">아이디</th>
	    <th width="10%">이름 </th>
	    <th            >메일</th>
	    <th width="12%">집전화번호</th>
	    <th width="12%">휴대폰번호</th>
	    <th width="12%">회사번호</th>
	    <th width="12%"></th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(adressBookVO.adbkMan) == 0}">
	<tr>
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<c:forEach var="result" items="${adressBookVO.adbkMan}" varStatus="status">
	<tr>
	    <c:if test="${fn:length(result.emplyrId) != 0}">
		<td class="lt_text3"><c:out value="${result.emplyrId}" /></td>
		</c:if>
		
		<c:if test="${fn:length(result.ncrdId) != 0}">
		<td class="lt_text3">
			<span class="link">
   			<a href="#" onClick="javascript:fn_aram_popup_nameCard('<c:out value="${result.ncrdId}"/>'); return false;">
				<c:out value="${result.ncrdId}" />
   			</a>
			</span>
		</td>	
		</c:if>
		
	    <td class="lt_text3"><c:out value="${result.nm}"/></td>
	    <td class="lt_text3"><c:out value="${result.emailAdres}"/></td>
	    <td class="lt_text3"><c:out value="${result.homeTelno}"/></td>
	    <td class="lt_text3"><c:out value="${result.moblphonNo}"/></td>
	    <td class="lt_text3"><c:out value="${result.offmTelno}"/></td>

		<c:if test="${fn:length(result.emplyrId) != 0}">
       	<td class="lt_text3">
       		<span class="button">
       		<a href="#" onclick="javascript:fn_aram_delete_user('<c:out value="${result.emplyrId}" />'); return false;">
       			삭제
       		</a>
       		</span>
       	</td>
		</c:if>

		<c:if test="${fn:length(result.ncrdId) != 0}">
      	<td class="lt_text3">
      		<span class="button">
      		<a href="#" onclick="javascript:fn_aram_delete_user('<c:out value="${result.emplyrId}" />'); return false;">
      			삭제
      		</a>
      		</span>
      	</td>
		</c:if>
	</tr>
	</c:forEach>
</tbody>
</table>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="adressBookVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("adressBookVO");
    varForm.action = "${pageContext.request.contextPath}/cop/adb/listAdressBook.do";
    varForm.submit();
	return true;
}

function fn_aram_insert(){
    var varForm = document.getElementById("adressBookVO");
    
	if (!validateAdressBookVO(varForm)){
		return false;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/cop/adb/insertAdressBook.do";
    	varForm.submit();
	}
}

var gArguments = new Array();

function fn_aram_get_user(){
	gArguments["retFunc"] = fn_aram_insert_user;

	var url = "/cop/adb/listUserPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=420px,top=100px,left=100px,location=no");
}

function fn_aram_insert_user(uniqId){
	var varForm = document.getElementById("adressBookVO");
	var checkId = varForm.userIds.value.split(",");

	for(var i = 0; i < checkId.length; i++){
		if(uniqId == checkId[i]){
			alert("이미 등록된 사람입니다.");
			return;
		}
	}
	varForm.userIds.value += uniqId + ",";

    varForm.checkCnd.value = "regist";
	varForm.action = "${pageContext.request.contextPath}/cop/adb/insertAdressBookUser.do";
	varForm.submit();
}

function fn_aram_delete_user(userId){
    var varForm = document.getElementById("adressBookVO");
    varForm.checkWord.value = userId;
    
    varForm.checkCnd.value = "regist";
    varForm.action = "${pageContext.request.contextPath}/cop/adb/deleteAdressBookUser.do";
    varForm.submit();
	return true;
}

function fn_aram_popup_nameCard(ncrdId){
	window.open("${pageContext.request.contextPath}/cop/ncm/detailNameCardPopup.do?ncrdId="+ncrdId,"명함조회","width=800, height=400");
}

</script>

