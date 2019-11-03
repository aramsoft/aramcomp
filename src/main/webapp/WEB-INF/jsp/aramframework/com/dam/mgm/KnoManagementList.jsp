<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : KnoManagementList.jsp
  * @Description : 지식관리 목록
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
	<h2>지식관리 목록</h2> 
</div>

<form:form commandName="knoManagementVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="knoId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
     	<form:select path="searchCondition" class="select" title="조회조건 선택">
			<form:option value="" label="--선택하세요--" />
	   		<form:option value="KNWLDG_NM" label="지식명" />			   
	   		<form:option value="USER_NM" label="등록자명" />			   
   		</form:select>
  		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" /> 
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="이 표는 지식정보관리 정보를 제공하며, 순번, 조직명, 지식유형, 지식명, 등록자명, 평가일자 정보로 구성되어 있습니다 .">
<caption>지식정보관리 목록</caption>
<thead>
	<tr>  
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="15%">조직명</th>
		<th scope="col" width="20%">지식유형</th>
		<th scope="col"            >지식명</th>
		<th scope="col" width="15%">등록자명</th>								
		<th scope="col" width="20%">평가일자</th>
	</tr>
</thead>    
<tbody>
	<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>    	

  	<c:set var="searchVO" value="${knoManagementVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.knoId}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">${result.orgnztNm}</td>						
		<td class="lt_text3">${result.knoTypeNm}</td>
		<td class="lt_text3">${result.knoNm}</td>															
		<td class="lt_text3">${result.userNm}</td>
		<td class="lt_text3"><c:out value="${fn:substring(result.appYmd, 0, 4)}-${fn:substring(result.appYmd, 4, 6)}-${fn:substring(result.appYmd, 6, 8)}"/></td>								    
	</tr>   
	</c:forEach>
</tbody>  
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("knoManagementVO");
    varForm.searchCondition.focus();
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("knoManagementVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/dam/mgm/listKnoManagement.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("knoManagementVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/dam/mgm/listKnoManagement.do";
    varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(knoId) {
	var varForm = document.getElementById("knoManagementVO");
    varForm.knoId.value = knoId;
    varForm.action = "${pageContext.request.contextPath}/dam/mgm/detailKnoManagement.do";
    varForm.submit();
}

</script>


