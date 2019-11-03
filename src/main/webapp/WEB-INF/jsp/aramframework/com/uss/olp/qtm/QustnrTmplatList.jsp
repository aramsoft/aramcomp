<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrTmplatList.jsp
  * @Description : 설문템플릿 목록
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
	<h2>설문템플릿 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="qustnrTmplatManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="qestnrTmplatId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="QUSTNR_TMPLAT_DC" label="템플릿설명" />			   
	   		<form:option value="QUSTNR_TMPLAT_TY" label="템플릿유형" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<!-- 설문 템플릿 리스트  -->
<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="10%">템플릿유형</th>
		<th scope="col" width="10%">템플릿유형<br>이미지정보</th>
		<th scope="col"            >템플릿명</th>
		<th scope="col" width="10%">작성자명</th>
		<th scope="col" width="15%">등록일자</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
   	<c:set var="searchVO" value="${qustnrTmplatManageVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.qestnrTmplatId}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><div class="divDotText" style="width:100px;">${result.qestnrTmplatTy}</div></td>
		<td class="lt_text3">
			<img src="${pageContext.request.contextPath}/uss/olp/qtm/getQustnrTmplatImage.do' />?qestnrTmplatId=${result.qestnrTmplatId}" alt="${result.qestnrTmplatTy}템플릿이미지" title="${result.qestnrTmplatTy}템플릿이미지">
		</td>
		<!-- onload="if(this.width>65){this.width=65}" -->
		<td class="lt_text3"><c:out value="${result.qestnrTmplatCn}"/></td>
		<td class="lt_text3">${result.frstRegisterNm}</td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
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
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/listQustnrTmplat.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/listQustnrTmplat.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(qestnrTmplatId){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.qestnrTmplatId.value = qestnrTmplatId;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/detailQustnrTmplat.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.qestnrTmplatId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/registQustnrTmplat.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("설문템플릿관리");	
	window.open(url, "도움말");
}

</script>

