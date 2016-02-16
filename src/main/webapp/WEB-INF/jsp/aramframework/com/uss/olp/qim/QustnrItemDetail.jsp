<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrItemDetail.jsp
  * @Description : 설문항목 상세조회
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
	<h2>설문항목 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrItemManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qustnrIemId" />
<form:hidden path="qestnrQesitmId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="이 표는 설문항목 목록 정보를 제공하며, 설문정보, 설문문항정보, 질문순번, 질문내용, 기타답변여부 정보로 구성되어 있습니다 .">
	<tr> 
		<th width="20%">
			설문정보
		</th>
		<td width="80%">
  			<c:out value="${fn:replace(qustnrItemManageVO.qestnrSj , crlf , '<br/>')}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
		<th>
			설문문항정보
		</th>
		<td>
  			<c:out value="${fn:replace(qustnrItemManageVO.qestnCn , crlf , '<br/>')}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
		<th>
			질문 순번
		</th>
		<td>
   			<c:out value="${qustnrItemManageVO.qustnrIemSn}" />
		</td>
	</tr> 
	<tr> 
		<th>
			질문 내용
		</th>
		<td>
   			<br>
  			<c:out value="${fn:replace(qustnrItemManageVO.qustnrIemCn , crlf , '<br/>')}" escapeXml="false" />
  			<br><br>
		</td>
	</tr> 
	<tr> 
		<th>
			기타답변여부
		</th>
		<td>
   			<c:out value=" ${qustnrItemManageVO.etcAnswerAt}" />
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
    var varForm = document.getElementById("qustnrItemManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qim/listQustnrItem.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("qustnrItemManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qim/editQustnrItem.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("qustnrItemManageVO");
	if(confirm("삭제시   설문항목, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qim/deleteQustnrItem.do";
		varForm.submit();
	}
}

</script>

