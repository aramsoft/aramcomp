<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrQestnStatistics.jsp
  * @Description : 설문문항 통계
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
	<h2>설문문항 통계</h2>
</div>

<div id="search_area">
	<div class="button_area">
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
<table class="table-register">
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		설문지정보(제목)
    	</th>
    	<td width="80%">
      		${qustnrQestnManageVO.qestnrSj}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		질문순번
    	</th>
    	<td>
      		${qustnrQestnManageVO.qestnSn}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		질문유형
    	</th>
    	<td>
	    	<c:if test="${qustnrQestnManageVO.qestnTyCode == '1'}">객관식</c:if>
	    	<c:if test="${qustnrQestnManageVO.qestnTyCode == '2'}">주관식</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
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
     		<span class="required_icon"></span>
    		최대선택건수
    	</th>
    	<td>
    		${qustnrQestnManageVO.mxmmChoiseCo}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		설문항목 결과
    	</th>
    	<td>
    	<br>
    	<table>
    		<c:forEach items="${statisticsList}" var="statistics" varStatus="status">
	    	<tr>
	    		<td height="25px">
	    			${statistics.iemCn}
	    		</td>
	    		<td>
	    			<img src="/images/aramframework/com/cmm/chart/chart${status.count}.JPG" width="${statistics.qustnrPercent}px" height="8" alt="차트이미지"> ${statistics.qustnrPercent}%
	    		</td>
	    	</tr>
    		</c:forEach>
    	</table>
    	<br>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		응답자답변내용 결과
    		<span class="required_icon"></span>
    	</th>
    	<td>
    	<table>
	    	<c:forEach items="${statisticsList2}" var="statistics2" varStatus="status">
	     	<c:if test="${statistics2.respondAnswerCn ne ''}">
		    <tr>
			    <td>
			    	 <c:out value="${fn:replace(statistics2.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
			    </td>
		    </tr>
		  	</c:if>
	    	</c:forEach>
    	</table>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		기타답변내용 결과
    		<span class="required_icon"></span>
    	</th>
    	<td>
    	<table>
    		<c:forEach items="${statisticsList2}" var="statistics2" varStatus="status">
     		<c:if test="!${statistics2.etcAnswerCn ne ''}">
	    	<tr>
		    	<td>
		    	 	<c:out value="${fn:replace(statistics2.etcAnswerCn , crlf , '<br/>')}" escapeXml="false" />
		    	</td>
	    	</tr>
	  		</c:if>
    		</c:forEach>
    		<tr><td></td></tr>
    	</table>
    	</td>
  	</tr>
<!--
  	<tr>
    	<th>
    		최초등록시점
    	</th>
    	<td>
    		${resultList[0].frstRegisterPnttm}
    	</td>
  	</tr>
	<tr>
    	<th>
    		최초등록아이디
    	</th>
    	<td>
    		${resultList[0].frstRegisterId}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		최종수정시점
    	</th>
    	<td>
    		${resultList[0].lastUpdusrPnttm}
    	</td>
  	</tr>
	<tr>
    	<th>
    		최종수정아이디
    	</th>
    	<td>
    		${resultList[0].lastUpdusrId}
    	</td>
  	</tr>
 -->
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
	var varForm = document.getElementById("qustnrQestnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/olp/qqm/listQustnrQestn.do";
	varForm.submit();
}

</script>

