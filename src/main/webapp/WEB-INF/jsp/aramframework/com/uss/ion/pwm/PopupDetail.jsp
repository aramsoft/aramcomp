<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : PopupDetail.jsp
  * @Description : 팝업창관리 상세조회
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
	<h2>팝업창관리 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="popupManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="popupId" />

<!--  등록  폼 영역  -->
<table class="table-detail">
  	<tr> 
    	<th width="20%">
    		팝업창명
    	</th>
    	<td width="80%">
			<c:out value="${popupManageVO.popupTitleNm}" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		팝업창URL
    	</th>
    	<td>
			<c:out value="${popupManageVO.fileUrl}" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
      		팝업창위치
    	</th>
    	<td>
      		 가로<c:out value="${popupManageVO.popupWlc}" />&nbsp;/&nbsp;
      		 세로<c:out value="${popupManageVO.popupHlc}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		팝업창사이즈
    	</th>
    	<td>
       		WIDTH<c:out value="${popupManageVO.popupWSize}" />&nbsp;/&nbsp;  
       		HEIGHT<c:out value="${popupManageVO.popupHSize}" escapeXml="false" />
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		게시 기간
    	</th>
    	<td>
 			<c:out value="${fn:substring(popupManageVO.ntceBgnde, 0, 4)}-${fn:substring(popupManageVO.ntceBgnde, 4, 6)}-${fn:substring(popupManageVO.ntceBgnde, 6, 8)} ${fn:substring(popupManageVO.ntceBgnde, 8, 10)}H ${fn:substring(popupManageVO.ntceBgnde, 10, 12)}M"/> 
 			~
 			<c:out value="${fn:substring(popupManageVO.ntceEndde, 0, 4)}-${fn:substring(popupManageVO.ntceEndde, 4, 6)}-${fn:substring(popupManageVO.ntceEndde, 6, 8)} ${fn:substring(popupManageVO.ntceEndde, 8, 10)}H ${fn:substring(popupManageVO.ntceEndde, 10, 12)}M"/>
    	</td>
  	</tr>
  	<tr> 
	    <th>
	    	그만보기 설정 여부
	    </th>
	    <td>
	    	<c:out value="${popupManageVO.stopVewAt}"/>
	    </td>
  	</tr>
  	<tr> 
    	<th>
     		계시 상태
    	</th>
    	<td>
    		<c:out value="${popupManageVO.ntceAt}"/>
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
    var varForm = document.getElementById("popupManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/listPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("popupManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/editPopup.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("popupManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/deletePopup.do";
		varForm.submit();
	}
}

</script>
