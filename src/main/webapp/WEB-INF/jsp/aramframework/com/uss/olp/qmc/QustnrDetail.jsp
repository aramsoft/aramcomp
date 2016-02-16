<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrDetail.jsp
  * @Description : 설문관리 상세조회
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
	<h2>설문관리 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrId" />

<!-- 상세조회  폼 영역  -->
<table class="table-detail" summary="상세조회 목록을 제공한다.">
<caption>상세조회 목록을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		설문제목
    	</th>
    	<td width="80%">
       		${qustnrManageVO.qestnrSj}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		설문목적
    	</th>
    	<td>
      		<br>
      		<c:out value="${fn:replace(qustnrManageVO.qestnrPurps , crlf , '<br/>')}" escapeXml="false" />
	  		<br><br>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		설문작성안내 내용
    	</th>
    	<td>
      		<br>
      		<c:out value="${fn:replace(qustnrManageVO.qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
	  		<br><br>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		설문대상
    	</th>
    	<td>
			<c:forEach items="${COM034_occpType}" var="comCodeList" varStatus="status">
			<c:if test="${comCodeList.code eq qustnrManageVO.qestnrTrget}">
			<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		설문기간
    	</th>
    	<td>
	    	<c:out value="${fn:substring(qustnrManageVO.qestnrBeginDe, 0, 4)}-${fn:substring(qustnrManageVO.qestnrBeginDe, 4, 6)}-${fn:substring(qustnrManageVO.qestnrBeginDe, 6, 8)}"/>
       		~
	    	<c:out value="${fn:substring(qustnrManageVO.qestnrEndDe, 0, 4)}-${fn:substring(qustnrManageVO.qestnrEndDe, 4, 6)}-${fn:substring(qustnrManageVO.qestnrEndDe, 6, 8)}"/>
    	</td>
  	</tr>
    <tr>
    	<th>
    		템플릿 유형
    	</th>
    	<td>
      		<c:out value="${qustnrManageVO.qestnrTmplatTy}" /> <img src="${pageContext.request.contextPath}/uss/olp/qtm/getQustnrTmplatImage.do' />?qestnrTmplatId=${qustnrManageVO.qestnrTmplatId}" align="middle" alt="템플릿유형 이미지" title="템플릿유형 이미지">
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
    var varForm = document.getElementById("qustnrManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qmc/listQustnr.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("qustnrManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qmc/editQustnr.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("qustnrManageVO");

	if(confirm("삭제시 설문관리, 설문항목, 설문문항, 설문응답자관리, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qmc/deleteQustnr.do";
		varForm.submit();
	}
}

</script>


