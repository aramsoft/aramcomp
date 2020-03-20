<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : IndvdlpgeList.jsp
  * @Description : 마이페이지 목록
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
	<h2>마이페이지 목록</h2> 
</div>

<form:form modelAttribute="indvdlPgeCntntsVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="cntntsId" value="">

<div id="search_area">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
		컨텐츠 명 
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
	</div>
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list"  summary="마이페이지에 추가 가능한 컨텐츠  목록을 제공한다.">
	<c:if test="${fn:length(resultList) == 0}">
   	<tr>
		<td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
   	</tr>         
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
	    <td width="300px" height="200">
	    <table border="1" style="bordercolor:#0099ff">
	    	<tr>
	    		<td width="300">
	        		<iframe width="300" name="preN<c:out value='${result.cntntsId}'/>" id="preI<c:out value='${result.cntntsId}'/>" title="마이페이지 컨텐츠 미리보기"  src = "${pageContext.request.contextPath}${result.cntntsLinkUrl}'/>" seamless="seamless" ></iframe>               
	    		</td>
	    	</tr>
	    </table>
	    </td>
	    <td >
	    <table style="width:100%; margin-left:10px;">
	        <tr>
	            <td class="lt_text2"><c:out value="${result.cntntsNm}"/></td>
	        </tr>
	        <tr>
	            <td class="lt_text"><c:out value="${result.cntntsDc}"/></td>
	        </tr>
	        <tr>
	            <td class="lt_text"><c:out value="${result.cntntsLinkUrl}"/></td>
	        </tr>
	        <tr>
	            <td>
					<span class="button">
					<a href="#" onclick="javascript:fn_aram_insert('<c:out value='${result.cntntsId}'/>'); return false;">
						<spring:message code='button.add' />
					</a>
					</span>
					&nbsp;
					<span class="button">
					<a href="#" onclick="javascript:fn_aram_preview('${pageContext.request.contextPath}${result.cntntsLinkUrl}'/>','<c:out value='${result.cntntsNm}'/>'); return false;">
						<spring:message code='button.preview' />
					</a>
					</span>
	            </td>
	        </tr>
	    </table>
	    </td>
	</tr>
	</c:forEach>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.pageIndex.value=pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/listIndvdlpgeCntntsMine.do";
    varForm.submit();
}

/* ********************************************************
* 컨텐츠 검색
******************************************************** */
function fn_aram_search(){
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/listIndvdlpgeCntntsMine.do";
    varForm.submit();
}

/* ********************************************************
 * 마이페이지에 컨텐츠 추가하기
 ******************************************************** */
function fn_aram_insert(id) {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.cntntsId.value = id;
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/insertIndvdlpgeCntntsMine.do";
    varForm.submit(); 
}

/* ********************************************************
* 컨텐츠 미리보기 팝업창 띄우기
******************************************************** */
function fn_aram_preview(cntcUrl, cntntsNm){
	window.open(cntcUrl, "p_preview", "width=850px,height=420px,top=100px,left=100px,location=no");
}

</script>
