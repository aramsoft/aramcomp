<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : StplatList.jsp
  * @Description : 약관 목록
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
	<h2>약관 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="stplatManageVO" action="${pageContext.request.contextPath}/uss/sam/stp/StplatListInqire.do" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="useStplatId"/>

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
      	<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchVO.searchCondition" title="검색조건선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="USE_STPLAT_NM" label="이용약관명" />
	   		<form:option value="USE_STPLAT_CN" label="이용약관내용" />
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

<table class="table-list" summary="이 표는 약관내용 대상 정보를 제공하며, 이용약관명, 이용약관내용, 등록일자 내용 정보로 구성되어 있습니다 .">
<thead>
	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col"            >이용약관명</th>
    	<th scope="col" width="10%">등록자</th>
    	<th scope="col" width="15%">등록일자</th>
	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
  		<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
  	</tr>
 	</c:if>

 	<c:set var="startIndex" value="${(stplatManageVO.searchVO.pageIndex-1) * stplatManageVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
  	<tr class="link" onClick="fn_aram_detail('<c:out value="${result.useStplatId}"/>')">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${stplatManageVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>
	
		<td class="lt_text3"><c:out value="${result.useStplatNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>	
  	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("stplatManageVO");
    varForm.searchKeyword.focus();
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("stplatManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/listStplat.do";
    varForm.submit();
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("stplatManageVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/listStplat.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(useStplatId) {
    var varForm = document.getElementById("stplatManageVO");
    varForm.useStplatId.value = useStplatId;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/detailStplat.do";
    varForm.submit();
}

/*********************************************************
 * 등록화면 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("stplatManageVO");
    varForm.useStplatId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/registStplat.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("약관관리");	
	window.open(url, "도움말");
}

</script>
