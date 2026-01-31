<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ZipList.jsp
  * @Description : 우편번호 목록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>우편번호 목록</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="zipVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type=hidden name="zip">
<input type=hidden name="sn" value="0">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${zipVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		<form:select path="searchCondition" title="searchCondition">
				<form:option value="" label="--선택하세요--" />
		   		<form:option value='EMD_NM' label="읍면동명" />
		   		<form:option value='ZIP' label="우편번호" />
		   		<form:option value='CTPRVN_NM' label="시도명" />
		   		<form:option value='SIGNGU_NM' label="시군구명" />
		   		<form:option value='LI_BULD_NM' label="리건물명" />
	   		</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist_excel(); return false;">엑셀등록</a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="우편번호와 주소를 출력하는 우편번호 목록 테이블이다.">
<CAPTION>우편번호 목록</CAPTION>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="20%">우편번호</th>
		<th scope="col"            >주소</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan=3><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(zipVO.pageIndex-1) * zipVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
  		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${zipVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('${result.zip}','${result.sn}'); return false;">
				<c:out value='${fn:substring(result.zip, 0,3)}-${fn:substring(result.zip, 3,6)}'/>
    		</a>
			</span>
		</td>
		<td class="lt_text">${result.ctprvnNm} ${result.signguNm} ${result.emdNm} ${result.liBuldNm} ${result.lnbrDongHo}</td>
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
   	var varForm = document.getElementById("zipVO");
   	varForm.pageIndex.value = pageNo;
   	varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/listZip.do";
   	varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
   	var varForm = document.getElementById("zipVO");
	var sC1 = varForm.searchCondition.value;
	var sK = varForm.searchKeyword.value;
	if (sC1 == "1" ) {
		varForm.searchKeyword.value = sK.replace(/\-/, "");
	}
	varForm.pageIndex.value = 1;
	varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(zip,sn){
   	var varForm = document.getElementById("zipVO");
	varForm.zip.value = zip;
	varForm.sn.value = parseInt(sn);
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/detailZip.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
   	var varForm = document.getElementById("zipVO");
    varForm.zip.value = "";
	varForm.sn.value = 0;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/registZip.do";
    varForm.submit();	
}

/* ********************************************************
 * 엑셀등록 처리 함수
 ******************************************************** */
function fn_aram_regist_excel(){
   	var varForm = document.getElementById("zipVO");
    varForm.zip.value = "";
	varForm.sn.value = 0;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/registZipExcel.do";
    varForm.submit();	
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("우편번호_주소찾기_우편번호관리");	
	window.open(url, "도움말");
}

</script>
