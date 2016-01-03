<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : ClubEdit.jsp
  * @Description : 동호회 수정
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
	<h2>동호회 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
     	<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<c:if test="${isAdmin=='true'}">
     		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</c:if>
	</div>
</div>

<form:form commandName="clubVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cmmntyId" />
<form:hidden path="clbId" />

<table class="table-register"  summary="소속 커뮤니티 정보, 동호회 명, 동호회 소개, 게시판 정보, 템플릿 정보, 동호회 운영자, 사용여부, 생성자, 생성일시   입니다">
	<tr>
	    <th width="20%">
			<span class="norequired_icon"></span>
	    	소속 커뮤니티 정보
	    </th>
	    <td colspan="3"><c:out value="${clubVO.cmmntyNm}" /></td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="clbNm">동호회 명</label>
	    </th>
	    <td colspan="3">
	      	<form:input path="clbNm" size="60" maxlength="60" style="width:100%" />
	      	<form:errors path="clbNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	동호회 소개
	    </th>
	    <td colspan="3">
	      	<form:textarea path="clbIntrcn" class="textarea" cols="75" rows="4" style="width:100%" />
	      	<form:errors path="clbIntrcn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
			<span class="norequired_icon"></span>
	    	게시판 정보
	    </th>
	    <td colspan="3">
	    	<c:forEach var="result" items="${bbsList}" varStatus="status">
	     	<c:out value="${result.bbsNm}" /> /
	     	</c:forEach>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	템플릿 정보
	    </th>
	    <td colspan="3">
	     	<form:input path="tmplatNm" size="20" maxlength="20" readonly="true" title="템플릿이름" />
	     	<form:hidden path="tmplatId" size="20" />
	     	&nbsp;
	     	<a href="#" onclick="javascript:fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search" />
	     	</a>
	     	<form:errors path="tmplatId" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	동호회 운영자
	    </th>
	    <td colspan="3">
	    	<input name="emplyrId" type="hidden" value='<c:out value="${operator.emplyrId}" />' />
	    	<input name="emplyrNm" type="text" size="20" id = "emplyrNm" value='<c:out value="${operator.emplyrNm}" />' maxlength="20" readonly>
	     	&nbsp;
	     	<a href="#" onclick="javascript:fn_aram_get_clbAdmin('<c:out value="${clubVO.clbId}" />'); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search">
	     	</a>
	     	<form:errors path="emplyrId" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
			<span class="required_icon"></span>
	    	사용여부
		</th>
	    <td colspan="3">
	  		<spring:message code="button.use" /> : <input type="radio" name="useAt" class="radio2" value="Y" <c:if test="${clubVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
	   		<spring:message code="button.notUsed" /> : <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${clubVO.useAt== 'N'}"> checked="checked"</c:if> onclick="alert('<spring:message code="cop.delete.confirm.msg" />');">
	    </td>
	</tr>
	<tr>
	    <th width="20%">
			<span class="norequired_icon"></span>
	    	생성자
	    </th>
	    <td width="30%">
	     	<c:out value="${clubVO.frstRegisterNm}" />
	    </td>
	    <th width="20%">
			<span class="norequired_icon"></span>
	    	생성일시
	    </th>
	    <td width="30%">
	     	<fmt:formatDate value="${clubVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
	    </td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="clubVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("clubVO");
    varForm.action = "${pageContext.request.contextPath}/cop/clb/listClub.do";
    varForm.submit();
}

function fn_aram_update(){
    var varForm = document.getElementById("clubVO");

    if (!validateClubVO(varForm)){
		return;
	}

    if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/clb/updateClub.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 템플릿 팝업창열기
******************************************************** */
function fn_aram_get_tmplat(){
    var varForm = document.getElementById("clubVO");
	gArguments["tmplatId"] = varForm.tmplatId;
	gArguments["tmplatNm"] = varForm.tmplatNm;

	var url = "/cop/tpl/listTemplatePopup.do?typeFlag=CLB";

	window.open(url, "p_tmplatInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_get_clbAdmin(clbId){
    var varForm = document.getElementById("clubVO");
	gArguments["uniqId"] = varForm.emplyrId;
	gArguments["userNm"] = varForm.emplyrNm;

	var url = "/cop/com/listClubUser.do?trgetId="+clbId+"&PopFlag=Y";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>
