<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrRespondInfoList.jsp
  * @Description : 설문조사 목록
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
	<h2>설문조사 목록</h2>
</div>

<form:form commandName="qustnrRespondInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="qestnrQesrspnsId" type="hidden" value="">

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="ETC_ANSWER_CN" label="기타답변내용" />			   
	   		<form:option value="RESPOND_ANSWER_CN" label="응답자답변내용" />			   
	   		<form:option value="RESPOND_NM" label="응답자명" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="이 표는 설문조사 대상 정보를 제공하며, 순번, 문항유형, 설문문항, 설문항목, 작성자명, 등록일자 정보로 구성되어 있습니다 .">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
		<th scope="col" width="20%">설문지</th>
	    <th scope="col"            >설문문항</th>
	    <th scope="col" width="10%">문항유형</th>
	    <th scope="col" width="10%">설문항목</th>
	    <th scope="col" width="10%">작성자명</th>
	    <th scope="col" width="15%">등록일자</th>
	</tr>
</thead> 
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	 <%-- 데이터를 화면에 출력해준다 --%>
   	<c:set var="searchVO" value="${qustnrRespondInfoVO.searchVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.qestnrQesrspnsId}'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">${result.qestnrSj}</td>
		<td class="lt_text3L"><c:out value="${result.qestnCn}"/></td>
		<td class="lt_text3">
		    <c:if test="${result.qestnTyCode == '1'}">객관식</c:if>
		    <c:if test="${result.qestnTyCode == '2'}">주관식</c:if>
		</td>    
		<td class="lt_text3">${result.qustnrIemCn}</td>
		<td class="lt_text3">${result.respondNm}</td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(qestnrQesrspnsId){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.qestnrQesrspnsId.value = qestnrQesrspnsId;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/detailQustnrRespondInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.qestnrQesrspnsId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/registQustnrRespondInfo.do";
    varForm.submit();
}

</script>

