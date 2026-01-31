<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : IndvdlInfoPolicyDetail.jsp
  * @Description : 개인정보보호정책 상세조회
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *  
  */
%>
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>개인정보보호정책 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!--  등록  폼 영역  -->
<form:form modelAttribute="indvdlInfoPolicyVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="indvdlInfoId" />

<table class="table-detail">
  	<tr> 
    	<th width="20%">
    		개인정보보호정책 명
    	</th>
    	<td width="80%">
		 	<c:out value="${indvdlInfoPolicyVO.indvdlInfoNm}" />
    	</td>
  	</tr>  
  	<tr> 
    	<th>
     		동의여부
    	</th>
    	<td>
    		<c:if test="${indvdlInfoPolicyVO.indvdlInfoYn == 'Y'}">예</c:if>
    		<c:if test="${indvdlInfoPolicyVO.indvdlInfoYn == 'N'}">아니오</c:if>
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		개인정보보호정책  내용
    	</th>
    	<td>
		<table style="width:500px;table-layout:fixed;">
			<tr>
				<td>
					<c:out value="${indvdlInfoPolicyVO.indvdlInfoDc}" escapeXml="false" />	
		    	</td>
		  	</tr>
		</table>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
	varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/listIndvdlInfoPolicy.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
	varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/editIndvdlInfoPolicy.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/deleteIndvdlInfoPolicy.do";
		varForm.submit();
	}
}

</script>
