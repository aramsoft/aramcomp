<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : PopupList.jsp
  * @Description : 팝업창관리 목록
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
	<h2>팝업창관리 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="popupManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="popupId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_view(); return false;"><spring:message code="button.preview" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="POPUP_SJ_NM" label="팝업창명" />			   
	   		<form:option value="FILE_URL" label="팝업창URL" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" border="0">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="5%" >
	    	<input type="checkbox" name="checkAll" id="checkAll" class="check2" value="1" onClick="fn_aram_checkAll(); return false;">
	    </th>
	    <th scope="col" width="25%">제목</th>
	    <th scope="col" width="25%">게시 기간</th>
	    <th scope="col"            >파일URL</th>
	    <th scope="col" width="10%">게시상태</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(popupManageVO.searchVO.pageIndex-1) * popupManageVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${popupManageVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
			<input type="checkbox" name="checkList" id="checkList" class="check2" value="${result.popupId}">
	    </td>

	    <td class="lt_text3L">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('${result.popupId}'); return false;">
				<c:out value="${result.popupTitleNm}"/>
			</a>
			</span>
	    </td>
	    <td class="lt_text3">
		 	<c:out value="${fn:substring(result.ntceBgnde, 0, 4)}-${fn:substring(result.ntceBgnde, 4, 6)}-${fn:substring(result.ntceBgnde, 6, 8)} ${fn:substring(result.ntceBgnde, 8, 10)}H ${fn:substring(result.ntceBgnde, 10, 12)}M"/>
		 	~<br>
		 	<c:out value="${fn:substring(result.ntceEndde, 0, 4)}-${fn:substring(result.ntceEndde, 4, 6)}-${fn:substring(result.ntceEndde, 6, 8)} ${fn:substring(result.ntceEndde, 8, 10)}H ${fn:substring(result.ntceEndde, 10, 12)}M"/>
	    <td class="lt_text3L">
		    <c:out value="${result.fileUrl}"/>
	    </td>
	    <td class="lt_text3">
	       <c:out value="${result.ntceAt}"/>
	    </td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("popupManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/listPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("popupManageVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/listPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(popupId){
    var varForm = document.getElementById("popupManageVO");
    varForm.popupId.value = popupId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/detailPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("popupManageVO");
    varForm.popupId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/registPopup.do";
    varForm.submit();
}

/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_aram_checkAll() {

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1) {
		document.getElementById("checkList").checked = checkAllValue;
	}
	else {
		for(var i=0; i < FLength; i++) {
			document.getElementsByName("checkList")[i].checked = checkAllValue;
		}
	}
}

/* ********************************************************
* 팝업창 미리보기
******************************************************** */
function fn_aram_view_popupManage() {
	var FLength = document.getElementsByName("checkList").length;

	if( FLength == 1) {
		if(document.getElementById("checkList").checked == true) {
			fn_aram_ajaxPopupInfo_popupManage( document.getElementById("checkList").value );
		}
	}
	else {
		for(var i=0; i < FLength; i++) {
			if(document.getElementsByName("checkList")[i].checked == true) {
				fn_aram_ajaxPopupInfo_popupManage( document.getElementsByName("checkList")[i].value );
			}
		}
	}
	return;
}

/* ********************************************************
* 팝업창 정보 Ajax통신으로 정보 획득
******************************************************** */
function fn_aram_ajaxPopupInfo_popupManage(popupIds){
	var param = {
			popupId: popupIds
		};

	$.getJSON(
  		"${pageContext.request.contextPath}/uss/ion/pwm/ajaxPopupInfoJson.do", 
  		param, 
  		function(json) {
  			alert(json.popupManageVO.popupId);
  			
			//if(fnGetCookie(popupIds) == null ){
   			fn_aram_popupOpen_popupManage(popupIds,
   				json.popupManageVO.fileUrl,
   				json.popupManageVO.popupWSize,
   				json.popupManageVO.popupHSize,
				json.popupManageVO.popupHlc,
  				json.popupManageVO.popupWlc,
   				json.popupManageVO.stopVewAt
   			);
			//}
 	  	}
  	);
}

/* ********************************************************
* 팝업창  오픈
******************************************************** */
function fn_aram_popupOpen_popupManage(popupId, fileUrl, width, height, top, left, stopVewAt) {

	var url = "${pageContext.request.contextPath}/uss/ion/pwm/openPopup.do'/>?";
	url = url + "fileUrl=" + fileUrl;
	url = url + "&stopVewAt=" + stopVewAt;
	url = url + "&popupId=" + popupId;
	var name = popupId;
	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus()}
}

/* ********************************************************
* 팝업창  오픈 쿠키 정보 OPEN
******************************************************** */
function fnGetCookie(name) {
	  var prefix = name + "=";

	  var cookieStartIndex = document.cookie.indexOf(prefix);
	  if (cookieStartIndex == -1) return null;
	  var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	  if (cookieEndIndex == -1) cookieEndIndex = document.cookie.length;

	  return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("팝업창관리");	
	window.open(url, "도움말");
}

</script>
