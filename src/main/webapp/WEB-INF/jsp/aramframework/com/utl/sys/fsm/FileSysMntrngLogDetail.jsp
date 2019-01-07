<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : FileSysMntrngLogDetail.jsp
 * @Description : 파일시스템모니터 로그 상세조회
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
	<h2>파일시스템모니터링 로그 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="fileSysMntrngLogVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="fileSysId" />
<form:hidden path="logId" />

<table class="table-detail"  summary="이 표는 파일시스템모니터링 로그 정보를 제공하며, 로그ID, 파일시스템명, 파일시스템관리명, 파일시스템 크기, 파일시스템 임계치, 파일시스템사용량, 모니터링상태, 로그정보, 생성일시 정보로 구성되어 있습니다 .">
<caption>파일시스템모니터로그 상세보기</caption>
<tbody>
	<tr> 
	    <th width="20%">
	    	로그ID
	    </th>
	    <td width="80%">
	      	<c:out value="${fileSysMntrngLogVO.logId}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
		<th>
			파일시스템명
		</th>
		<td>
			<c:out value='${fileSysMntrngLogVO.fileSysNm}'/>
			
		</td>
	</tr>
	<tr> 
	    <th>
	    	파일시스템관리명
	    </th>
	    <td>
	      	<c:out value='${fileSysMntrngLogVO.fileSysManageNm}'/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	파일시스템 크기
	    </th>
	    <td>
	      	<c:out value='${fileSysMntrngLogVO.fileSysMg}'/>G
	    </td>
	</tr>
	<tr> 
	    <th>
	    	파일시스템 임계치
	    </th>
	    <td>
	      	<c:out value='${fileSysMntrngLogVO.fileSysThrhldRt}'/>% (<c:out value='${fileSysMntrngLogVO.fileSysThrhld}'/>G)
	    </td>
	</tr>
	<tr> 
	    <th>
	    	파일시스템 사용량
	    </th>
	    <td>
	      	<c:out value='${fileSysMntrngLogVO.fileSysUsgRt}'/>% (<c:out value='${fileSysMntrngLogVO.fileSysUsgQty}'/>G)
	    </td>
	</tr>
	<tr> 
	    <th>
	    	모니터링상태
	    </th>
	    <td>
	      	<c:out value="${fileSysMntrngLogVO.mntrngSttus}" escapeXml="false" />
	    </td>
	</tr>
	<c:if test="${fileSysMntrngLogVO.mntrngSttus} ne '정상'">
	<tr> 
	    <th>
	    	로그정보
	    </th>
	    <td>
	    	<textarea id="logInfo" name="logInfo" rows="10" cols="75" title="로그정보" readonly><c:out value="${fileSysMntrngLogVO.logInfo}" escapeXml="false" /></textarea>
	    </td>
	</tr>
	</c:if>
	<tr> 
	    <th>
	    	생성일시
 	    </th>
	    <td>
	      	<c:out value="${fileSysMntrngLogVO.creatDt}" escapeXml="false" />
	    </td>
	</tr>
</tbody>
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<form:hidden path="searchBgnHour" />
<form:hidden path="searchEndHour" />
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="fileSysMntrngLogVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("fileSysMntrngLogVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrngLog.do";
    varForm.submit();	
}	

</script>
