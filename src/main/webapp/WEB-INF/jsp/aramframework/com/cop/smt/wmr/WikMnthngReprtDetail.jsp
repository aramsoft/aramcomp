<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : WikMnthngReprtDetail.jsp
 * @Description : 주간/월간보고 상세조회
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
	<h2>주간/월간보고 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<c:if test="${fn:substring(wikMnthngReprtVO.reprtSttus,0,2) eq '등록' && wikMnthngReprtVO.reportrId eq uniqId}">	
     		<span class="button"><a href="#" onclick="javascript:fn_aram_confirm(); return false;">승인</a></span>
		</c:if>
		<c:if test="${fn:substring(wikMnthngReprtVO.reprtSttus,0,2) eq '등록' && wikMnthngReprtVO.wrterId eq uniqId}">	
     		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
     		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
   		</c:if>
     	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="wikMnthngReprtVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="reprtId" />
	
<table class="table-detail"   summary="이 표는 주간/월간보고 정보를 제공하며, 보고유형, 보고일자, 해당일자, 작성자, 보고대상자, 보고서제목, 금주보고내용, 차주보고내용, 특이사항, 파일첨부, 보고서상태 정보로 구성되어 있습니다 .">
<caption>주간/월간보고 상세보기</caption>
<tbody>
	<tr> 
		<th width="20%">
			보고유형
		</th>
		<td width="80%">
			<c:forEach items="${COM060_reprtSe}" var="reprtSeInfo" varStatus="status">
		    <c:if test="${reprtSeInfo.code eq wikMnthngReprtVO.reprtSe}">	
		     	<c:out value="${reprtSeInfo.codeNm}" escapeXml="false" />
		    </c:if>
		    </c:forEach>
		</td>
	</tr>
	<tr> 
		<th>
			보고일자
		</th>
		<td>
			<c:out value="${fn:substring(wikMnthngReprtVO.reprtDe, 0,4)}-${fn:substring(wikMnthngReprtVO.reprtDe, 4,6)}-${fn:substring(wikMnthngReprtVO.reprtDe, 6,8)}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
		<th>
			해당일자
		</th>
		<td>
			<c:out value="${fn:substring(wikMnthngReprtVO.reprtBgnDe, 0,4)}-${fn:substring(wikMnthngReprtVO.reprtBgnDe, 4,6)}-${fn:substring(wikMnthngReprtVO.reprtBgnDe, 6,8)}" escapeXml="false" />
			~
			<c:out value="${fn:substring(wikMnthngReprtVO.reprtEndDe, 0,4)}-${fn:substring(wikMnthngReprtVO.reprtEndDe, 4,6)}-${fn:substring(wikMnthngReprtVO.reprtEndDe, 6,8)}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
	    <th>
	    	작성자
	    </th>
	    <td>
	      	<c:out value="${wikMnthngReprtVO.wrterClsfNm}" escapeXml="false" />
	      	<c:out value="${wikMnthngReprtVO.wrterNm}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	보고대상자
	    </th>
	    <td>
	    	<c:out value="${wikMnthngReprtVO.reportrClsfNm}" escapeXml="false" />
			<c:out value="${wikMnthngReprtVO.reportrNm}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	보고서제목
	    </th>
	    <td>
	      	<c:out value="${wikMnthngReprtVO.reprtSj}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	금주보고내용
	    </th>
	    <td>
	      	<br/>
	      	<c:out value="${fn:replace(wikMnthngReprtVO.reprtThswikCn , crlf , '<br>')}" escapeXml="false" />
	      	<br/><br/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	차주보고내용
	    </th>
	    <td>
	      	<br/>
	      	<c:out value="${fn:replace(wikMnthngReprtVO.reprtLesseeCn , crlf , '<br>')}" escapeXml="false" />
	      	<br/><br/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	특이사항
	    </th>
	    <td>
	      	<br/>
	      	<c:out value="${fn:replace(wikMnthngReprtVO.partclrMatter , crlf , '<br>')}" escapeXml="false" />
	      	<br/><br/>
	    </td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 -->
	<tr>
		<th>
			파일첨부
		</th>
		<td>
			<c:import url="/content/files/${wikMnthngReprtVO.atchFileId}" />
		 </td>
	 </tr>
	 <!-- //첨부파일 테이블 레이아웃 -->
	 <tr> 
	    <th>
	    	보고서 상태
	    </th>
	    <td>
	      	<c:out value="${wikMnthngReprtVO.reprtSttus}" escapeXml="false" />
	    </td>
	</tr>
</tbody>
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchSttus" />
<form:hidden path="searchDe" />
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("wikMnthngReprtVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/wmr/listWikMnthngReprt.do";
    varForm.submit();	
}	

function fn_aram_edit() {
    var varForm = document.getElementById("wikMnthngReprtVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/wmr/editWikMnthngReprt.do";
    varForm.submit();					
}

function fn_aram_delete(){
    var varForm = document.getElementById("wikMnthngReprtVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/wmr/deleteWikMnthngReprt.do";
		varForm.submit();
	}
}

function fn_aram_confirm(){
    var varForm = document.getElementById("wikMnthngReprtVO");
	if(confirm("승인 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/cop/smt/wmr/confirmWikMnthngReprt.do";
		varForm.submit();
	}
}

</script>


