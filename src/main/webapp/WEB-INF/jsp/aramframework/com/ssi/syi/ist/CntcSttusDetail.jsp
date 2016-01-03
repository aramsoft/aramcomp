<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CntcSttusDetail.jsp
  * @Description : 연계현황 상세조회
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
	<h2>연계현황 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
	</div>
</div>

<form:form commandName="cntcSttusVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcId" />

<table class="table-detail">
  	<tr>
    	<th width="20%">
    		연계ID
    	</th>
    	<td width="80%">
    		<c:out value="${cntcSttusVO.cntcId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		연계명
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.cntcNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		연계유형
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.cntcType}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		제공기관
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.provdInsttNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		제공시스템
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.provdSysNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		제공서비스
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.provdSvcNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		요청기관
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.requstInsttNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		요청시스템
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.requstSysNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		총 연계 건수
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.cntAll}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		성공 건수
    	</th>
    	<td>
    		<c:out value="${cntcSttusVO.cntSuccess}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		성공율(%)
    	</th>
    	<td>
    		<fmt:formatNumber value="${ 100 * cntcSttusVO.cntSuccess / cntcSttusVO.cntAll }" maxFractionDigits="0"/>
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
    var varForm = document.getElementById("cntcSttusVO");
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/ist/listCntcSttus.do";
    varForm.submit();
}

</script>
