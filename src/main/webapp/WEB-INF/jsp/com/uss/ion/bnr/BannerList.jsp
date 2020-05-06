<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BannerList.jsp
 * @Description : 배너 목록
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
	<h2>배너 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="bannerVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="bannerId" type="hidden" value="">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${bannerVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	 		<label for="searchKeyword">배너명 : </label>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search(); return false;" >
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />

<table class="table-list" id="tblData" summary="메인화면에서 배너에 대한 목록으로 배너명, 링크url,배너설명,반영여부를 제공한다.">
<caption>배너 목록</caption>
<thead>
  	<tr>
     	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="5%">
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
    	</th>
	    <th scope="col" width="20%">배너 명</th>
	    <th scope="col"            >링크 URL</th>
	    <th scope="col" width="10%">반영여부</th>
  	</tr>
</thead>
<tbody>
 	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 	<c:if test="${fn:length(resultList) == 0}">
 	<tr>
 		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
 	</tr>
 	</c:if>
 	
 	<c:set var="searchVO" value="${bannerVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
   	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

 		<td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.bannerId}" />
		</td>
    	<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.bannerId}"/>'); return false;">
				<c:out value="${result.bannerNm}"/>
			</a>
			</span>
    	</td>
    	<td class="lt_text3"><c:out value="${result.linkUrl}"/></td>
    	<td class="lt_text3"><c:out value="${result.reflctAt}"/></td>
  	</tr>
	</c:forEach>
</tbody>
</table>
</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

$(function() {
	$("#checkAll").on("click", function(){
		if( $(this).is(":checked") ){
			$("#tblData input[name=uniqIds]").prop("checked", true);
		}else{
			$("#tblData input[name=uniqIds]").prop("checked", false); 
		}
	});
});

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("bannerVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/listBanner.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("bannerVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/listBanner.do";
    varForm.submit();
}

function fn_aram_detail(bannerId) {
    var varForm = document.getElementById("bannerVO");
    varForm.bannerId.value = bannerId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/editBanner.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("bannerVO");
    varForm.bannerId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/registBanner.do";
    varForm.submit();
}

function fn_aram_deleteList() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
    
    var varForm = document.getElementById("bannerVO");
    if(confirm("<spring:message code="common.delete.msg" />")){
       	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/deleteBannerList.do";
       	varForm.submit();
    }
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("배너관리");	
	window.open(url, "도움말");
}

</script>
