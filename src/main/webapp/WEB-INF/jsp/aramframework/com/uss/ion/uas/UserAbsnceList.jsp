<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : UserAbsnceList.jsp
 * @Description : 사용자부재 목록
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
	<h2>사용자부재 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="userAbsnceVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="userId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
  		사용자 명 : 
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
  		<form:select path="selAbsnceAt" title="부재여부선택">
        	<form:option value="A" label="전체" />
            <form:option value="Y" label="Y" />
            <form:option value="N" label="N" />
        </form:select>
	</div>
</div>

<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="사용자부재정보에 대한 목록을 제공한다.">
<caption>사용자부재 관리</caption>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">사용자 ID</th>
	    <th scope="col"            >사용자 명</th>
	    <th scope="col" width="15%">부재여부</th>
	    <th scope="col" width="15%">등록여부</th>
	    <th scope="col" width="20%">수정일자</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

 	<c:set var="startIndex" value="${(userAbsnceVO.searchVO.pageIndex-1) * userAbsnceVO.searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.userId}"/>', '<c:out value="${result.regYn}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${userAbsnceVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.userId}"/></td>
	    <td class="lt_text3"><c:out value="${result.userNm}"/></td>
	    <td class="lt_text3">
	      	<c:if test="${result.userAbsnceAt eq 'Y'}"><c:out value="Y"/></c:if>
	      	<c:if test="${result.userAbsnceAt eq 'N'}"><c:out value="N"/></c:if>
	    </td>
	    <td class="lt_text3">
	      	<c:if test="${result.regYn eq 'Y'}"><c:out value="Y"/></c:if>
	      	<c:if test="${result.regYn eq 'N'}"><c:out value="N"/></c:if>
	    </td>
	    <td class="lt_text3"><fmt:formatDate value="${result.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("userAbsnceVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/uas/listUserAbsnce.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("userAbsnceVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/uas/listUserAbsnce.do";
    varForm.submit();
}

function fn_aram_detail(userId, regYn) {
    var varForm = document.getElementById("userAbsnceVO");
	if(regYn == 'N') {
        if(confirm("등록된 사용자부재 정보가 없습니다. 등록페이지로 이동하시겠습니까?")) {
        	location.replace("${pageContext.request.contextPath}/uss/ion/uas/registUserAbsnce.do?userId="+userId);
        	return;
        } else {
            return;
        }
	}
	varForm.userId.value = userId;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/uas/editUserAbsnce.do";
	varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("사용자부재관리");	
	window.open(url, "도움말");
}

</script>
