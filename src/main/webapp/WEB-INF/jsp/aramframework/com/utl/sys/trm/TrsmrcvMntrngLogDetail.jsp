<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : TrsmrcvMntrngLogDetail.jsp
  * @Description : 송수신모니터링로그 상세조회
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
	<h2>송수신모니터링로그 상세조회</h2>
</div>
 
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="trsmrcvMntrngLogVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcId" />
<form:hidden path="logId" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="송수신모니터링로그에 대한 상세정보를 제공합니다.">
    <tr> 
        <th width="20%">로그ID</th>
        <td width="80%">
            <c:out value="${trsmrcvMntrngLogVO.logId}" escapeXml="false" /> 
        </td>
    </tr>
    <tr> 
        <th>연계ID</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.cntcId}" escapeXml="false" /> 
        </td>
    </tr>
    <tr> 
        <th>연계명</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.cntcNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>제공기관</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.provdInsttNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>제공시스템</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.provdSysNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>제공서비스</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.provdSvcNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>요청기관</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.requstInsttNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>요청시스템</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.requstSysNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>테스트클래스명</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.testClassNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>관리자명</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.mngrNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>관리자이메일주소</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.mngrEmailAddr}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>모니터링상태</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.mntrngSttusNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>로그정보</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.logInfo}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>모니터링시각</th>
        <td>
            <c:out value="${trsmrcvMntrngLogVO.creatDt}" escapeXml="false" /> 
        </td>
    </tr> 
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchKeywordFrom" />
<form:hidden path="searchKeywordTo" />
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
    var varForm = document.getElementById("trsmrcvMntrngLogVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrngLog.do";
    varForm.submit();
}

</script>
