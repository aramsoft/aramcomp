<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CpyrhtPrtcPolicyList.jsp
  * @Description : 저작권보호정책 목록
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
	<h2>저작권보호정책 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle; ;" alt="도움말아이콘이미지">
	</a>
</div>

<form:form modelAttribute="cpyrhtPrtcPolicyVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="cpyrhtId"/>

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${cpyrhtPrtcPolicyVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		<form:select path="searchCondition" title="조회조건 선택">
		   		<form:option value='' label="--선택하세요--" />
		   		<form:option value="CPYRHT_PRTC_POLICY_CN" label="저작권보호정책내용" />			   
	   		</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
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

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="이 표는 저작권보호정책 정보를 제공하며, 저작권보호정책내용, 등록일자 정보로 구성되어 있습니다 .">
<caption>저작권보호정책 목록</caption>
<thead>
	<tr>      
    	<th scope="col" width="7%" >No.</th>            
    	<th scope="col"            >저작권보호정책내용</th>        
    	<th scope="col" width="15%">등록일자</th>               
	</tr>
</thead>
<tbody>      
	<c:if test="${fn:length(resultList) == 0}">
  	<tr> 
  		<td class="lt_text3" colspan="3"><spring:message code="common.nodata.msg" /></td>
  	</tr>   	          				 			   
 	</c:if>
 	
  	<c:set var="startIndex" value="${(cpyrhtPrtcPolicyVO.pageIndex-1) * cpyrhtPrtcPolicyVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
  	<tr>
  		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${cpyrhtPrtcPolicyVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

   		<td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.cpyrhtId}"/>'); return false;">
   				<c:out value='${fn:substring(result.cpyrhtPrtcPolicyCn, 0, 40)}'/>
    		</a>
			</span>
   		</td>
		<td class="lt_text3"><c:out value="${result.frstRegisterPnttm}"/></td>			
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
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.searchKeyword.focus();
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/listCpyrhtPrtcPolicy.do";
    varForm.submit();
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/listCpyrhtPrtcPolicy.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(cpyrhtId) {		
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.cpyrhtId.value = cpyrhtId;	
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/detailCpyrhtPrtcPolicy.do";
    varForm.submit();	
}

/*********************************************************
 * 등록화면 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.cpyrhtId.value = "";	
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/registCpyrhtPrtcPolicy.do";
    varForm.submit();	
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("저작권보호정책");	
	window.open(url, "도움말");
}

</script>

