<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrTmplatDetail.jsp
  * @Description : 설문템플릿 상세조회
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
	<h2>설문템플릿 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrTmplatManageVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrTmplatId" />

<!--  등록  폼 영역  -->
<table class="table-detail" summary="상세조회 목록을 제공한다.">
<caption>상세조회 목록을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		템플릿유형
    	</th>
    	<td>
       		${qustnrTmplatManageVO.qestnrTmplatTy}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		템플릿유형<br>이미지정보
    	</th>
    	<td>
     		<c:if test="${qustnrTmplatManageVO.qestnrTmplatImageInfo ne null}">
      		<c:if test="${qustnrTmplatManageVO.qestnrTmplatImageInfo ne ''}">
    		<img src="${pageContext.request.contextPath}/uss/olp/qtm/getQustnrTmplatImage.do' />?qestnrTmplatId=${qustnrTmplatManageVO.qestnrTmplatId}" alt="${qustnrTmplatManageVO.qestnrTmplatTy}템플릿이미지" title="${qustnrTmplatManageVO.qestnrTmplatTy}템플릿이미지">
    		</c:if>
			</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		템플릿설명
    	</th>
    	<td>
      		<br>
      		<c:out value="${fn:replace(qustnrTmplatManageVO.qestnrTmplatCn , crlf , '<br/>')}" escapeXml="false" />
	  		<br><br>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		템플릿파일(경로)
    	</th>
    	<td>
     		${qustnrTmplatManageVO.qestnrTmplatCours}
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
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/listQustnrTmplat.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/editQustnrTmplat.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
	if(confirm("삭제시 설문템플릿, 설문항목, 설문문항, 설문응답자관리, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/deleteQustnrTmplat.do";
		varForm.submit();
	}
}

</script>

