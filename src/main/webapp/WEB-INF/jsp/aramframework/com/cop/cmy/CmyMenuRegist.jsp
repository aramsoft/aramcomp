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
  * @Class Name : CmyMenuRegist.jsp
  * @Description : 커뮤니티 메뉴 등록
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
	<h2>커뮤니티 메뉴 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="communityMenuVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="trgetId" value="${curTrgetId}" />

<table class="table-register" summary="메뉴 등록화면">
<caption>메뉴 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="menuNo">메뉴No</label>
    	</th>
    	<td colspan="3">
      		&nbsp;
      		<form:input path="menuNo" size="10" maxlength="10" title="메뉴No"/>
      		<form:errors path="menuNo" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="menuAlias">메뉴별명</label>
    	</th>
    	<td colspan="3">
        	&nbsp;
      		<form:input path="menuAlias" size="30" maxlength="30" title="메뉴별명"/>
      		<form:errors path="menuAlias" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="menuNm">메뉴명</label>
    	</th>
    	<td width="30%">
      		&nbsp;
      		<form:input path="menuNm" size="30"  maxlength="30" title="메뉴명" />
      		<form:errors path="menuNm" cssClass="error"/>
    	</td>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	상위메뉴여부
	    </th>
	    <td width="30%">
	     	<label for="topMenuAtY">예</label> : <input type="radio" name="topMenuAt" id="topMenuAtY" class="radio2" value="Y"  checked="checked">&nbsp;
	     	<label for="topMenuAtN">아니오</label> : <input type="radio" name="topMenuAt" id="topMenuAtN" class="radio2" value="N">
	     	<form:errors path="topMenuAt" cssClass="error"/>
	    </td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="progrmFileNm">파일명</label>
    	</th>
    	<td colspan="3">
        	&nbsp;
    		<form:input path="progrmFileNm" size="60"  maxlength="60" onkeypress="press();" title="파일명" readonly="true" class="readOnlyClass"/>
        	<a href="#" target="_blank" title="새창으로" onclick="javascript:searchFileNm(); return false;" style="selector-dummy:expression(this.hideFocus=false);">
        		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt='' width="15" height="15" />(프로그램파일명 검색)
        	</a>
	    	<form:errors path="progrmFileNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="directUrl">바로가기 URL</label>
    	</th>
    	<td colspan="3">
        	&nbsp;
    		<form:input path="directUrl" size="60"  maxlength="256" title="URL" />
	    	<form:errors path="directUrl" cssClass="error"/>
	   	</td>
  	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	사용여부
	    </th>
	    <td>
	     	<label for="useAtY"><spring:message code="button.use" /></label> : <input type="radio" name="useAt" id="useAtY" class="radio2" value="Y"  checked="checked">&nbsp;
	     	<label for="useAtN"><spring:message code="button.notUsed" /></label> : <input type="radio" name="useAt" id="useAtN" class="radio2" value="N">
	     	<form:errors path="useAt" cssClass="error"/>
	    </td>
	    <th>
	    	<span class="norequired_icon"></span>
	    	관리자메뉴
	    </th>
	    <td>
	     	<label for="mgrAtY">예</label> : <input type="radio" name="mgrAt" id="mgrAtY" class="radio2" value="Y">&nbsp;
	     	<label for="mgrAtN">아니오</label> : <input type="radio" name="mgrAt" id="mgrAtN" class="radio2" value="N" checked="checked">
	     	<form:errors path="useAt" cssClass="error"/>
	    </td>
	</tr>
  	<tr>
    	<th>
	    	<span class="norequired_icon"></span>
    		<label for="menuDc">메뉴설명</label>
    	</th>
    	<td colspan="3">
      		<form:textarea path="menuDc" rows="14" cols="75" cssClass="txArea" title="메뉴설명"/>
      		<form:errors path="menuDc" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${communityMenuVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${communityMenuVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${communityMenuVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${communityMenuVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="communityMenuVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록조회  함수
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("communityMenuVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 입력처리 함수
 ******************************************************** */
function fn_aram_insert() {
	var varForm = document.getElementById("communityMenuVO");

	if(!validateCommunityMenuVO(varForm)){
		return;
	}
	
	if(!fn_validatorMenuList()){return;}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action="${pageContext.request.contextPath}/cop/cmy/insertMenu.do";
		varForm.submit();
	}
}

/* ********************************************************
* 입력값 validator 함수
******************************************************** */
function fn_validatorMenuList() {
	var varForm = document.getElementById("communityMenuVO");
	if(varForm.menuNo.value == ""){alert("메뉴번호는 Not Null 항목입니다."); return false;}
	if(!checkNumber(varForm.menuNo.value)){alert("메뉴번호는 숫자만 입력 가능합니다."); return false;}

	if(varForm.menuNm.value == ""){alert("메뉴명은 Not Null 항목입니다."); return false;}
	if(varForm.progrmFileNm.value == "" 
		&& varForm.directUrl == ""){alert("프로그램파일명 , 바로가기URL 둘중 하나는 값이 있어야 합니다."); return false;}

   return true;
}

/* ********************************************************
* 필드값 Number 체크 함수
******************************************************** */
function checkNumber(str) {
   var flag=true;
   if (str.length> 0) {
       for (i = 0; i < str.length; i++) {
           if (str.charAt(i) < '0' || str.charAt(i)> '9') {
               flag=false;
           }
       }
   }
   return flag;
}

/* ********************************************************
 * 파일명 엔터key 목록조회  함수
 ******************************************************** */
function press() {
    if (event.keyCode==13) {
    	searchFileNm();    // 원래 검색 function 호출
    }
}

var gArguments = new Array();

/* ********************************************************
 * 파일목록조회  함수
 ******************************************************** */
function searchFileNm() {
	var varForm = document.getElementById("communityMenuVO");
	gArguments["progrmFileNm"] = varForm.progrmFileNm;
	
	var url = "/sym/prm/listProgramPopup.do?searchUseAt=Y";

	window.open(url, "p_programInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>


