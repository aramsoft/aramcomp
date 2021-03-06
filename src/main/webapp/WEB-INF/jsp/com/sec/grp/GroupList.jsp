<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : GroupList.java
  * @Description : 그룹 목록
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
	<h2>그룹 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="groupVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="groupId"/>
<input type="hidden" name="groupIds"/>

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${groupVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	 		그룹 명 : 
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		</span>
	</div>	
</div>

<table class="table-list" summary="그룹 관리에 관한 테이블입니다.그룹 ID,그룹 명,설명,등록일자의 정보를 담고 있습니다.">
<thead>
  	<tr>
		<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="15%">그룹 ID</th>
    	<th scope="col" width="25%">그룹 명</th>
    	<th scope="col"            >설명</th>
    	<th scope="col" width="15%">등록일자</th>
  	</tr>
 </thead>
 <tbody>
  	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(groupVO.pageIndex-1) * groupVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${groupVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text"><c:out value="${result.groupId}"/></td>
    	<td class="lt_text">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.groupId}"/>'); return false;">
    			<c:out value="${result.groupNm}"/>
    		</a>
			</span>
    	</td>
    	<td class="lt_text3"><c:out value="${result.groupDc}"/></td>
    	<td class="lt_text3"><c:out value="${result.groupCreatDe}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
   <ui:pagination paginationInfo="${paginationInfo}" type="default" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" defer="defer">

function press() {

    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

function linkPage(pageNo){
    var varForm = document.getElementById("groupVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/grp/listGroup.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("groupVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sec/grp/listGroup.do";
    varForm.submit();
}

function fn_aram_detail(groupId) {
    var varForm = document.getElementById("groupVO");
    varForm.groupId.value = groupId;
    varForm.action = "${pageContext.request.contextPath}/sec/grp/editGroup.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("groupVO");
    varForm.groupId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sec/grp/registGroup.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sec:" + encodeURIComponent("그룹관리");	
	window.open(url, "도움말");
}

</script>
