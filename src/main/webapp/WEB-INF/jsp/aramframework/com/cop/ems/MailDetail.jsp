<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MailDetail.jsp
  * @Description : 발송메일 상세조회
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
	<h2>발송메일 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="sndngMailVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mssageId" />

<table class="table-detail" summary="보내는 사람, 받는 사람, 제목, 발신 내용, 발송 결과, XML메일보기, 첨부파일에 대한 정보를 가진 발송메일을 상세조회한다.">
<CAPTION>발송메일 상세조회</CAPTION>
	<tr> 
	    <th width="20%">
	    	보내는사람
	    </th>
	    <td>
	    	${sndngMailVO.dsptchPerson}
	    </td>
	</tr>
	<tr> 
	    <th> 
	    	받는사람
	    </th>
	    <td>
	    	${sndngMailVO.recptnPerson}
	    </td>
	</tr>
	<tr> 
	    <th>
	    	제목
	    </th>
	    <td>
	    	${sndngMailVO.sj}
	    </td>
	</tr>
	<tr> 
	    <th>
	    	발신 내용
	    </th>
	    <td>
	      	<c:out value="${fn:replace(sndngMailVO.emailCn , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	발송 결과
	    </th>
	    <td>
	    	${sndngMailVO.sndngResultCode}
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	XML메일보기
	    </th>
	    <td>
	    	<a href="#" onclick="javascript:fn_aram_view_xml(); return false;">${sndngMailVO.mssageId}.xml</a>
	    </td>
	</tr>
	<c:if test="${sndngMailVO.atchFileId != ''}">
	<tr> 
	    <th>
	    	첨부파일
	    </th>
	    <td>
			<c:import url="/content/files/${sndngMailVO.atchFileId}" />
	    </td>
	</tr>
	</c:if>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${sndngMailVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${sndngMailVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${sndngMailVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${sndngMailVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>	  

<script type="text/javascript">

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("sndngMailVO");
    varForm.action = "${pageContext.request.contextPath}/cop/ems/listSndngMail.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("sndngMailVO");
	var ret = confirm("삭제하시겠습니까?");
	if (ret == true) {
		varForm.action = "${pageContext.request.contextPath}/cop/ems/deleteSndngMail.do";
		varForm.submit();
	}
}

/* ********************************************************
 * XML형태의 발송메일 보기
 ******************************************************** */
function fn_aram_view_xml(){
    var varForm = document.getElementById("sndngMailVO");
    varForm.action = "${pageContext.request.contextPath}/cop/ems/detailSndngMailXml.do";
    varForm.submit();
}

</script>

