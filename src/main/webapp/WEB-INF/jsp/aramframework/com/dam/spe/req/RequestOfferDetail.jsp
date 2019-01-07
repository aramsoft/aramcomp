<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : RequestOfferDetail.jsp
  * @Description : 지식 정보제공/정보요청  상세조회
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
	<h2>지식 정보제공/정보요청  상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<%-- 자기 글일때만 수정/삭제버튼 활성화 --%>
		<c:if test="${requestOfferVO.frstRegisterId eq uniqId}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		</c:if>
		<c:if test="${IS_SPE eq 'Y'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_reply(); return false;">답변</a></span>
		</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area">
	</div>
</div>

<form:form commandName="requestOfferVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<input name="cmd" type="hidden" value="<c:out value=''/>">

<input name="parentsKnoId" type="hidden" value="${requestOfferVO.knoId}">
<input name="threadDepth" type="hidden" value="${requestOfferVO.threadDepth + 1 }">
<input name="threadNo" type="hidden" value="${requestOfferVO.threadNo + 1}">
<input name="threadGroupNo" type="hidden" value="${requestOfferVO.threadGroupNo}">

<input name="orgnztId" type="hidden" value="${requestOfferVO.orgnztId}">
<input name="knoTypeCd" type="hidden" value="${requestOfferVO.knoTypeCd}">

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="이 표는 지식 정보제공/정보요청  정보를 제공하며, 조직명, 지식유형명, 지식명, 지식내용, 첨부파일  정보로 구성되어 있습니다 .">
<caption>지식 정보제공/정보요청 상세조회</caption>
	<tr> 
		<th width="20%">
			조직명
		</th>
		<td width="80%">
 			${requestOfferVO.orgnztNm}
		</td>
	</tr>
	<tr> 
		<th>
			지식유형명
		</th>
		<td>
 			${requestOfferVO.knoTypeNm}
		</td>
	</tr>
	<tr> 
		<th>
			지식명
		</th>
		<td>
			${requestOfferVO.knoNm}
		</td>
	</tr>
	<tr> 
		<th>
			지식내용
		</th>
		<td>
			<div style="padding:0px 0px 0px 0px;margin:0px 0px 0px 2px;">
			<c:out value="${fn:replace(requestOfferVO.knoCn , crlf , '<br/>')}" escapeXml="false" />
			<br>
			<br>
			</div>
		</td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start.. -->
  	<tr>
		<th>
			파일첨부
		</th>
		<td>
			<c:import url="/content/files/${requestOfferVO.atchFileId}" />
	 	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 End. -->
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
    var varForm = document.getElementById("requestOfferVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/req/listRequestOffer.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("requestOfferVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/req/editRequestOffer.do";
    varForm.submit();
}

/* ********************************************************
 * 답글처리화면
 ******************************************************** */
function fn_aram_reply(){
    var varForm = document.getElementById("requestOfferVO");
    varForm.cmd.value = 'reply';
    varForm.action = "${pageContext.request.contextPath}/dam/spe/req/registRequestOffer.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("requestOfferVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
	    varForm.action = "${pageContext.request.contextPath}/dam/spe/req/deleteRequestOffer.do";
		varForm.submit();
	}
}

</script>
