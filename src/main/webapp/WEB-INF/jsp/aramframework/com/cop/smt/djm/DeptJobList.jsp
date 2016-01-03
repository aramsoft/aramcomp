<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : DeptJobList.jsp
 * @Description : 부서 업무 목록
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
<c:set var="tmpDeptId" value=""/>
<c:set var="tmpDeptNm" value=""/>
<c:set var="wtText" value=""/>
<DIV id="main">

<div class="content_title">
	<h2>부서 업무 목록</h2>
</div>

<form:form commandName="deptJobVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="searchDeptId" />	
<form:hidden path="searchDeptJobBxId" />
<input type="hidden" name="deptJobId" value="" />
	
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
		<label for="searchCondition">조회조건 : </label>
  		<form:select path="searchCondition" class="select" title="조회조건 선택">
			<form:option value='' label="--선택하세요--" />
			<form:option value="DEPT_JOB_NM" label="제목" />
			<form:option value="DEPT_JOB_CN" label="내용" />
			<form:option value="USER_NM" label="담당자" />
   		</form:select>
   		<form:input path="searchKeyword" size="15" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" /> 
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<div style="clear:both;overflow:hidden;">

	<div id="scale" style="width:20%;float:left;">
	
		<c:forEach var="resultBx" items="${resultBxList}" varStatus="st">
			<c:if test="${tmpDeptNm != resultBx.deptNm}">
				<c:set var="tmpDeptNm" value="${resultBx.deptNm}"/>
				<table class="table-list">
				  	<tr> 
				    	<th align="left"  onClick="fn_aram_choose_dept('<c:out value="${resultBx.deptId}"/>')" style="cursor:hand">&nbsp;<c:out value='${resultBx.deptNm}'/></th>
				  	</tr>
				</table>
			</c:if>
				<div id="<c:out value="${resultBx.deptId}"/>">
				<table summary="부서업무함에 대한 목록을 제공합니다.">
			<c:if test="${tmpDeptNm == resultBx.deptNm}"> 
		 		<tr>
		 			<td class="lt_text3">
						<span class="link">
						<a href="#" onclick="javascript:fn_aram_choose_deptjobbx('<c:out value="${resultBx.deptId}"/>', '<c:out value="${resultBx.deptJobBxId}"/>'); return false;">
							<c:out value="${resultBx.deptJobBxNm}"/>
						</a>
						</span>
					</td>
				</tr>
			</c:if>
				</table>
				</div>
		</c:forEach>	  
	</div>
	
	<div style="width:78%;float:right;">

		<table class="table-list"  summary="이 표는 부서업무 정보를 제공하며, 제목, 담당자, 작성일 정보로 구성되어 있습니다 .">
	 	<caption>부서업무 목록</caption>
	 	<thead>
	  		<tr>
	    		<th scope="col" width="7%" >No.</th>
	    		<th scope="col"            >제목</th>
	    		<th scope="col" width="15%">담당자</th>
	    		<th scope="col" width="15%">작성일</th>
	  		</tr>
	 	</thead>    
	 	<tbody>
		 	<c:if test="${fn:length(resultList) == 0}">
		  	<tr>
		    	<td class="lt_text3"  colspan="4"><spring:message code="common.nodata.msg" /></td>  
		  	</tr>		 
		 	</c:if>
		 	
 			<c:set var="startIndex" value="${(deptJobVO.pageIndex-1) * deptJobVO.recordPerPage}"/>
	 		<c:forEach var="result" items="${resultList}" varStatus="status">
		  		<c:if test="${result.priort == '1'}">
		  			<c:set var="wtText" value="font-weight : bold; text-align : left; "/>
		  		</c:if>
		  		<c:if test="${result.priort == '2'}">
		  			<c:set var="wtText" value="text-align : left;"/>
		  		</c:if>
		  		<c:if test="${result.priort == '3'}">
		  			<c:set var="wtText" value="text-align : left;"/>
		  		</c:if>
	
		  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.deptJobId}"/>');">
		 		<c:set var="index" value="${startIndex + status.count}"/>
				<c:set var="reverseIndex" value="${deptJobVO.totalRecordCount - index + 1}"/>
				<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		    	<td class="lt_text3L"><c:out value="${result.deptJobNm}"/></td>
				<td class="lt_text3"><c:out value="${result.chargerNm}"/></td>
		    	<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
		 	</tr>
		 	</c:forEach>	  
	 	</tbody>
		</table>
		
	</div>		
</div>

<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

window.onload = function() {
    var varForm = document.getElementById("deptJobVO");
	fn_aram_hide_ListStyle();
	if( varForm.searchDeptId.value != "" ) {
		var idsrc = document.getElementById(varForm.searchDeptId.value);
		idsrc.style.display="";
	}	
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("deptJobVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJob.do";
    varForm.submit();	
}

function fn_aram_search() {
    var varForm = document.getElementById("deptJobVO");
    varForm.pageIndex.value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJob.do";
    varForm.submit();	
}

function fn_aram_detail(deptJobId) {
    var varForm = document.getElementById("deptJobVO");
    varForm.deptJobId.value = deptJobId;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/detailDeptJob.do";
    varForm.submit();	
}

function fn_aram_regist(){	
    var varForm = document.getElementById("deptJobVO");
    varForm.deptJobId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/registDeptJob.do";
    varForm.submit();
}

function fn_aram_choose_dept(deptId){
	fn_aram_hide_ListStyle();

	var idsrc = document.getElementById(deptId);
	idsrc.style.display="";
}
 
function fn_aram_choose_deptjobbx(deptId, deptJobBxId) {
    var varForm = document.getElementById("deptJobVO");
    varForm.searchDeptId.value = deptId; 
    varForm.searchDeptJobBxId.value = deptJobBxId; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJob.do";
    varForm.submit();	
}

function fn_aram_hide_ListStyle(){
	<c:forEach var="resultBxFn" items="${resultBxList}" varStatus="st">
	<c:if test="${tmpDeptId != resultBxFn.deptId}">
		<c:set var="tmpDeptId" value="${resultBxFn.deptId}"/>
		var idsrc = document.getElementById("${resultBxFn.deptId}");
		idsrc.style.display="none";
	</c:if>
	</c:forEach>	 
}

</script>
