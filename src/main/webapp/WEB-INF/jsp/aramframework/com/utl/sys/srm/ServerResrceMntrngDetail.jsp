<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ServerResrceMntrngDetail.jsp
  * @Description : 서버자원모니터링 상세조회
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
	<h2>서버자원모니터링 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="serverResrceMntrngVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-detail" summary="서버자원모니터링 결과의 상세정보를 조회한다.">
<caption>서버자원모니터링 상세조회</caption>
    <tr>
        <th width="20%">
        	로그ID
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.logId}'/>
        </td>
    </tr>
    <tr>
        <th>
         	서버H/W 명
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.serverNm}'/>
        </td>
    </tr>
    <tr>
        <th>
          	서버H/W IP
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.serverEqpmnIp}'/>
        </td>
    </tr>
    <tr>
        <th>
         	CPU사용률
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.cpuUseRt}'/>
        </td>
    </tr>
    <tr>
        <th>
        	메모리사용률
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.moryUseRt}'/>
        </td>
    </tr>
    <tr>
        <th>
         	서비스상태
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.svcSttusNm}'/>
        </td>
    </tr>
    <c:if test="${serverResrceMntrngVO.svcSttus == '02' }">
    <tr>
        <th>
        	로그정보
        </th>
        <td>
        	<textarea rows="5" cols="80" readOnly><c:out value='${serverResrceMntrngVO.logInfo}'/></textarea>
        </td>
    </tr>
    </c:if>
    <tr>
        <th>
        	생성일시
        </th>
        <td>
        	<c:out value='${serverResrceMntrngVO.creatDt}'/>
        </td>
    </tr>
</table>

<form:hidden path="strServerNm" />
<form:hidden path="strStartDt" />
<form:hidden path="strEndDt" />
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>
  
</DIV>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("serverResrceMntrng");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listServerResrceMntrng.do";
    varForm.submit();       
}

</script>
