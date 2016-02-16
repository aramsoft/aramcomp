<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrQestnDetail.jsp
  * @Description : 설문문항 상세조회
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
	<h2>설문문항 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrQestnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrQesitmId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="상세조회 목록을 제공한다.">
<caption>상세조회 목록을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		설문지정보(제목)
    	</th>
    	<td width="80%">
      		${qustnrQestnManageVO.qestnrSj}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		질문순번
    	</th>
    	<td>
      		${qustnrQestnManageVO.qestnSn}
    	</td>
  	</tr>
  	<tr>
    	<th>
      		질문유형
    	</th>
    	<td>

			<c:forEach items="${COM018_qestnType}" var="comCode" varStatus="status">
			<c:if test="${comCode.code eq qustnrQestnManageVO.qestnTyCode}">
				<c:out value="${fn:replace(comCode.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>

    	</td>
  	</tr>
  	<tr>
    	<th>
    		질문 내용
    	</th>
    	<td>
      		<br>
      		<c:out value="${fn:replace(qustnrQestnManageVO.qestnCn , crlf , '<br/>')}" escapeXml="false" />
	  		<br><br>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		최대선택건수
    	</th>
    	<td width="80%">
    		${qustnrQestnManageVO.mxmmChoiseCo}
    	</td>
  	</tr>
<!--
  	<tr>
    	<th>
    		최초등록시점
    	</th>
    	<td>
    		${qustnrQestnManageVO.frstRegisterPnttm}
    	</td>
  	</tr>
	<tr>
    	<th>
    		최초등록아이디
    	</th>
    	<td>
    		${qustnrQestnManageVO.frstRegisterId}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		최종수정시점
    	</th>
    	<td>
    		${qustnrQestnManageVO.lastUpdusrPnttm}
    	</td>
  	</tr>
	<tr>
    	<th>
    		최종수정아이디
    	</th>
    	<td>
    		${qustnrQestnManageVO.lastUpdusrId}
    	</td>
  	</tr>
 -->
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
	var varForm = document.getElementById("qustnrQestnManageVO");
	varForm.action =  "${pageContext.request.contextPath}/uss/olp/qqm/listQustnrQestn.do";
	varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
	var varForm = document.getElementById("qustnrQestnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/olp/qqm/editQustnrQestn.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
	var varForm = document.getElementById("qustnrQestnManageVO");
	if(confirm("삭제시  설문문항, 설문항목, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qqm/deleteQustnrQestn.do";
		varForm.submit();
	}
}

</script>

