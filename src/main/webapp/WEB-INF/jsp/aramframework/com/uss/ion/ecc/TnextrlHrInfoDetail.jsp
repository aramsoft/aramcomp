<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : TnextrlHrInfoDetail.jsp
 * @Description : 외부인사정보 상세조회
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
	<h2>외부인사정보 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="tnextrlHrInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="eventId" />
<form:hidden path="extrlHrId" />

<!-- 등록  폼 영역  -->
<table class="table-detail">
  	<tr>
    	<th width="20%">
    		행사/이벤트/캠페인/(내용)
    	</th>
    	<td width="80%">
      		${tnextrlHrInfoVO.eventCn}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		성별
    	</th>
    	<td>
			<c:forEach items="${COM014_sexdstn}" var="comCode" varStatus="status">
			<c:if test="${comCode.code eq tnextrlHrInfoVO.sexdstnCode}">
			<c:out value="${fn:replace(comCode.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		외부인사명
    	</th>
    	<td>
       		${tnextrlHrInfoVO.extrlHrNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		생년월일
    	</th>
    	<td>
     		${fn:substring(tnextrlHrInfoVO.brth, 0, 4)}-${fn:substring(tnextrlHrInfoVO.brth, 4, 6)}-${fn:substring(tnextrlHrInfoVO.brth, 6, 8)}
    	</td>
  	</tr>
	<tr>
    	<th>
    		직업유형
     	</th>
    	<td>
			<c:forEach items="${COM034_occpType}" var="comCode" varStatus="status">
			<c:if test="${comCode.code eq tnextrlHrInfoVO.occpTyCode}">
			<c:out value="${fn:replace(comCode.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		소속기관
    	</th>
    	<td>
       		${tnextrlHrInfoVO.psitnInsttNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		전화번호
    	</th>
    	<td>
       		${tnextrlHrInfoVO.areaNo}-${tnextrlHrInfoVO.middleTelno}-${tnextrlHrInfoVO.endTelno}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		이메일주소
    	</th>
    	<td>
        	${tnextrlHrInfoVO.emailAdres}
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
	var varForm = document.getElementById("tnextrlHrInfoVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
	var varForm = document.getElementById("tnextrlHrInfoVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/editTnextrlHrInfo.do";;
	varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
	var varForm = document.getElementById("tnextrlHrInfoVO");
	
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/deleteTnextrlHrInfo.do";
		varForm.submit();
	}
}

</script>

