<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : DbMntrngDetail.jsp
  * @Description : DB서비스모니터링 상세조회
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
	<h2>DB서비스모니터링 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="dbMntrngVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="dataSourcNm" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="등록된 DB서비스모니터링에 대한 상세정보를 제공합니다.">
    <tr> 
        <th width="20%">
        	데이타소스명
        </th>
        <td width="80%">
            <c:out value="${dbMntrngVO.dataSourcNm}" escapeXml="false" /> 
        </td>
    </tr>
    <tr> 
        <th>
        	서버명
        </th>
        <td>
            <c:out value="${dbMntrngVO.serverNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>
        	DBMS종류
        </th>
    	<td>
            <c:out value="${dbMntrngVO.dbmsKindNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>
        	체크SQL
        </th>
        <td>
            <c:out value="${dbMntrngVO.ceckSql}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>
        	관리자명
        </th>
        <td>
            <c:out value="${dbMntrngVO.mngrNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>
        	관리자이메일주소
        </th>
        <td>
            <c:out value="${dbMntrngVO.mngrEmailAddr}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>
        	모니터링상태
        </th>
        <td>
            <c:out value="${dbMntrngVO.mntrngSttusNm}" escapeXml="false" /> 
        </td>
    </tr> 
    <tr> 
        <th>
        	모니터링시각
        </th>
        <td>
            <fmt:formatDate value="${dbMntrngVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
    var varForm = document.getElementById("dbMntrngVO");
    varForm.action = "/utl/sys/dbm/listDbMntrng.do";
    varForm.submit()
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("dbMntrngVO");
    varForm.action = "/utl/sys/dbm/editDbMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
 function fn_aram_delete(){
	var varForm = document.getElementById("dbMntrngVO");
	
    if(confirm("<spring:message code='common.delete.msg' />")){
    	varForm.action = "/utl/sys/dbm/deleteDbMntrng.do";
    	varForm.submit();
    }
}

</script>
