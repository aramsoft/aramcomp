<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : DeptJobDetail.jsp
 * @Description : 부서 업무 상세조회
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>부서 업무 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="deptJobVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="deptId" /> 
<form:hidden path="deptJobId" />
<form:hidden path="deptJobBxId" />

<table class="table-detail" summary="이 표는 부서업무 정보를 제공하며, 부서, 부서업무함명, 제목, 내용, 업무담당자, 우선순위, 파일첨부 정보로 구성되어 있습니다 .">
<caption>부서업무 상세보기</caption>
<tbody>
  	<tr>
		<th width="20%">
			부서
		</th>
		<td width="80%">
			<c:out value="${deptJobVO.deptNm}" escapeXml="false" />
		</td>
  	</tr>
  	<tr>
		<th>
			부서업무함명
		</th>
		<td>
			<c:out value="${deptJobVO.deptJobBxNm}" escapeXml="false" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		제목
    	</th>
    	<td>
      		<c:out value="${deptJobVO.deptJobNm}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		내용
   	</th>
    	<td>
      		<br/>
      		<c:out value="${fn:replace(deptJobVO.deptJobCn , crlf , '<br>')}" escapeXml="false" />
      		<br/><br/>
    	</td>
  	</tr>
   	<tr>
    	<th>
    		업무담당자
    	</th>
    	<td>
			<c:out value="${deptJobVO.chargerNm}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
   			우선순위
    	</th>
    	<td>
       		<c:forEach items="${priort}" var="priortInfo" varStatus="status">
		    	<c:if test="${priortInfo.code eq deptJobVO.priort}">
		     	<c:out value="${priortInfo.codeNm}" escapeXml="false" />
		    	</c:if>
	    	</c:forEach>
    	</td>
  	</tr>
  	<!-- 첨부파일 테이블 레이아웃 -->
  	<tr>
		<th>
			파일첨부
		</th>
		<td>
			<c:import url="/content/files/${deptJobVO.atchFileId}" />
	 	</td>
  	</tr>
 	<!-- //첨부파일 테이블 레이아웃 -->
</tbody>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="deptJobVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript">

function fn_aram_list(){
	var varForm = document.getElementById("deptJobVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJob.do";
	varForm.submit();
}

function fn_aram_edit() {
	var varForm = document.getElementById("deptJobVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/editDeptJob.do";
	varForm.submit();
}

function fn_aram_delete(){
	var varForm = document.getElementById("deptJobVO");
	
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/deleteDeptJob.do";
		varForm.submit();
	}
}

</script>

