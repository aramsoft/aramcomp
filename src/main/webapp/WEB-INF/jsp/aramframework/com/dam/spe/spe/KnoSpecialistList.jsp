<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : KnoSpecialistList.jsp
  * @Description : 지식전문가  목록
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
	<h2>지식전문가  목록 </h2>
</div>

<form:form commandName="knoSpecialistVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="speId" type="hidden" value="">
<input name="knoTypeCd" type="hidden" value="">
<input name="appTypeCd" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" class="select" title="조회조건 선택">
	   		<form:option value="USER_NM" label="전문가성명" />			   
	   		<form:option value="KNWLDG_TY_NM" label="지식유형명" />			   
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

<table class="table-list" summary="이 표는 지식전문가 정보를 제공하며, 순번, 조직명, 지식유형명, 전문가성명, 등급, 승인일자 정보로 구성되어 있습니다 .">
<caption>지식전문가 목록</caption>
<thead>
	<tr>  
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="15%">조직명</th>
		<th scope="col" width="20%">지식유형명</th>
		<th scope="col"            >전문가성명</th>
		<th scope="col" width="20%">등급</th>									
		<th scope="col" width="20%">승인일자</th>
	</tr>
</thead>    
<tbody>
	<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if> 
	   	
 	<c:set var="startIndex" value="${(knoSpecialistVO.pageIndex-1) * knoSpecialistVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.speId}', '${result.knoTypeCd}', '${result.appTypeCd}'); return false;">					
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${knoSpecialistVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">${result.orgnztNm}</td>
		<td class="lt_text3">${result.knoTypeNm}</td>
		<td class="lt_text3">${result.userNm}</td>								
	    <td class="lt_text3">
		    <c:if test="${result.appTypeCd == '1'}">수석</c:if>
		    <c:if test="${result.appTypeCd == '2'}">책임</c:if>
		    <c:if test="${result.appTypeCd == '3'}">선임</c:if>							    
	    </td>															
		<td class="lt_text3">
			<c:out value="${fn:substring(result.speConfmDe, 0, 4)}-${fn:substring(result.speConfmDe, 4, 6)}-${fn:substring(result.speConfmDe, 6, 8)}"/>
		</td>
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
    var varForm = document.getElementById("knoSpecialistVO");
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
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/listKnoSpecialist.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/listKnoSpecialist.do";
    varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(speId, knoTypeCd, appTypeCd) {
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.speId.value = speId;
    varForm.knoTypeCd.value = knoTypeCd;
    varForm.appTypeCd.value = appTypeCd;
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/detailKnoSpecialist.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.speId.value = "";
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/registKnoSpecialist.do";
    varForm.submit();
}

</script>

