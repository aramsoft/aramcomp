<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : CmmntyRegist.jsp
  * @Description : 커뮤니티 등록
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
	<h2>커뮤니티 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="communityVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="커뮤니티명, 커뮤니티 소개, 템플릿 정보, 커뮤니티 관리자 입니다">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="cmmntyNm">커뮤니티명	</label>
	    </th>
	    <td width="80%" colspan="3">
	      	<form:input path="cmmntyNm" type="text" size="60"maxlength="60" style="width:100%" title="커뮤니티명" />
	      	<form:errors path="cmmntyNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="cmmntyIntrcn">커뮤니티 소개</label>
	    </th>
	    <td colspan="3">
	      	<form:textarea path="cmmntyIntrcn" class="textarea"  cols="75" rows="4"  style="width:100%" title="커뮤니티 소개" />
	      	<form:errors path="cmmntyIntrcn" cssClass="error"/>
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
	     	<a href="#" onClick="javascript:fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search">
	     	</a>
			<form:errors path="tmplatId" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	회원인증여부
	    </th>
	    <td colspan="3">
	     	<input type="radio" name="memberAt" id="memberAtY" class="radio2" value="Y" checked><label for="memberAtY">예</label> &nbsp;
	     	<input type="radio" name="memberAt" id="memberAtN" class="radio2" value="N"><label for="memberAtN">아니오</label> 
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	커뮤니티 관리자
	    </th>
	    <td colspan="3">
	     	<input name="emplyrId" type="hidden" value="">
	     	<input name="emplyrNm" type="text" size="20" value=""  maxlength="20" readonly title="커뮤니티 관리자">
	     	&nbsp;
	     	<a href="#" onClick="javascript:fn_aram_get_cmmntyAdmin(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="새창">
	     	</a>
			<form:errors path="emplyrId" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="cmmntyNm">Home URL</label>
	    </th>
	    <td colspan="3">
	    	<form:input path="homeUrl" size="60"  maxlength="255"  style="width:100%" title="Home URL"/>
	    	<form:errors path="homeUrl" cssClass="error"/>
	    </td>
	</tr>
  	<tr>
    	<th>
 	    	<span class="norequired_icon"></span>
    		<label for="cmmntyLogoImage">커뮤니티 로고이미지</label>
    	</th>
    	<td colspan="3">
    	<table>
    		<tr>
    			<td><input type="file" name="cmmntyImageName" id="cmmntyImageName" title="커뮤니티로고이미지 첨부"></td>
	     	</tr>
	     	<tr>
	     		<td colspan="2"><font color="red">가로:150px 세로:50px 포멧:JPGE 형식으로 업로드 해주세요</font></td>
	     	</tr>
	    </table>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${communityVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${communityVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${communityVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${communityVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="communityVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("communityVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listCommunity.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("communityVO");

    if (!validateCommunityVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/cmy/insertCommunity.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 템플릿 팝업창열기
******************************************************** */
function fn_aram_get_tmplat(){
    var varForm = document.getElementById("communityVO");
	gArguments["tmplatId"] = varForm.tmplatId;
	gArguments["tmplatNm"] = varForm.tmplatNm;
	
	var url = "/cop/tpl/listTemplatePopup.do?typeFlag=CMY";

	window.open(url, "p_tmplatInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_get_cmmntyAdmin(cmmntyId){
    var varForm = document.getElementById("communityVO");
	gArguments["uniqId"] = varForm.emplyrId;
	gArguments["userNm"] = varForm.emplyrNm;

	var url = "/cop/com/listUser.do?PopFlag=Y";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

