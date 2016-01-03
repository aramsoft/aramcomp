<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : UnityLinkDetail.jsp
 * @Description : 통합링크관리 상세조회
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
	<h2>통합링크관리 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="unityLinkVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="unityLinkId" />

<!--  등록  폼 영역  -->
<table class="table-detail">
  	<tr>
    	<th width="20%">
    		통합링크명
    	</th>
	    <td width="80%">
			<c:out value="${unityLinkVO.unityLinkNm}" escapeXml="false" />
	    </td>
  	</tr>
  	<tr>
    	<th>
    		통합링크그룹
    	</th>
    	<td>
		 	<c:forEach items="${COM039_pollKind}" var="comCode" varStatus="pollKindStatus">
				<c:if test="${comCode.code eq unityLinkVO.unityLinkSeCode}">
				<c:out value="${comCode.codeNm}" escapeXml="false" />
				</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		통합링크URL
    	</th>
	    <td>
			<c:out value="${unityLinkVO.unityLinkUrl}" escapeXml="false" />
	    </td>
  	</tr>
  	<tr>
    	<th>
    		통합링크설명
    	</th>
    	<td>
		<table border="0" style="table-layout:fixed;width:500px;">
			<tr>
				<td>
					<c:out value="${unityLinkVO.unityLinkDc}" escapeXml="false" />
			    </td>
			</tr>
		</table>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("unityLinkVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ulm/listUnityLink.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("unityLinkVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ulm/editUnityLink.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("unityLinkVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/ion/ulm/deleteUnityLink.do";
		varForm.submit();
	}
}

</script>
