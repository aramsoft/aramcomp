<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrRespondInfoDetail.jsp
  * @Description : 설문조사 상세조회
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
	<h2>설문조사 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrRespondInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrQesrspnsId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="이 표는 설문조사 대상 정보를 제공하며, 설문관리정보, 설문문항정보, 설문유형, 성문항목정보, 응답자답변내용, 기타답변내용, 응답자명 정보로 구성되어 있습니다 .">
  	<tr>
    	<th width="20%">
    		설문관리정보
    	</th>
    	<td width="80%">
			<c:out value="${fn:replace(qustnrRespondInfoVO.qestnrSj , crlf , '<br/>')}" escapeXml="false" />
			<input name="qestnrId" type="hidden" value="${qustnrRespondInfoVO.qestnrId}">
    	</td>
  	</tr>
  	<tr>
    	<th>
    		설문문항정보
    	</th>
    	<td>
		    <c:out value="${fn:replace(qustnrRespondInfoVO.qestnCn , crlf , '<br/>')}" escapeXml="false" />
			<input name="qestnrQesitmId" type="hidden" value="${qustnrRespondInfoVO.qestnrQesitmId}">
    	</td>
  	</tr>
  	<tr>
    	<th>
    		설문유형
    	</th>
    	<td>
		    <c:if test="${qustnrRespondInfoVO.qestnTyCode == '1'}">객관식</c:if>
		    <c:if test="${qustnrRespondInfoVO.qestnTyCode == '2'}">주관식</c:if>
		    <!-- <input name="title" type="text" size="73" value="" maxlength="70" style="width:100%;">   -->
    	</td>
  	</tr>
  	<tr>
    	<th>
      		설문항목정보
    	</th>
    	<td>
   	 		<c:out value="${fn:replace(qustnrRespondInfoVO.qustnrIemCn , crlf , '<br/>')}" escapeXml="false" />
			<input name="qustnrIemId" type="hidden" value="${qustnrRespondInfoVO.qustnrIemId}">
    	</td>
  	</tr>
  	<tr>
    	<th>
   			응답자답변내용<br>(주관식)
    	</th>
    	<td>
      		<br>
      		<c:out value="${fn:replace(qustnrRespondInfoVO.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
	  		<br><br>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		기타답변내용
    	</th>
    	<td>
      		<br>
      		<c:out value="${fn:replace(qustnrRespondInfoVO.etcAnswerCn , crlf , '<br/>')}" escapeXml="false" />
	  		<br><br>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		응답자명
    	</th>
    	<td>
			<c:out value="${fn:replace(qustnrRespondInfoVO.respondNm , crlf , '<br/>')}" escapeXml="false" />
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/editQustnrRespondInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/deleteQustnrRespondInfo.do";
		varForm.submit();
	}
}

</script>


