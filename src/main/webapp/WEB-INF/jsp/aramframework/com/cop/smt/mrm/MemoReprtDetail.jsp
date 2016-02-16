<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : MemoReprtDetail.jsp
 * @Description : 메모보고 상세조회
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
	<h2>메모보고 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<c:if test="${memoReprtVO.reportrId eq uniqId}">	
    		<span class="button"><a href="#" onclick="javascript:fn_aram_opinion(); return false;">의견등록</a></span>
		</c:if>
		<c:if test="${memoReprtVO.reprtSttus eq '미확인' && memoReprtVO.wrterId eq uniqId}">	
     		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
     		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
    	</c:if>
     	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area">
	</div>
</div>

<form:form commandName="memoReprtVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="reprtId" />
	
<table class="table-detail" summary="이 표는 메모보고 정보를 제공하며, 보고일자, 작성자, 보고대상자, 보고제목, 보고내용, 파일첨부, 보고서상태 정보로 구성되어 있습니다 .">
<caption>메모보고 상세보기</caption>
<tbody>
  	<tr> 
		<th width="20%">
			보고일자
		</th>
		<td width="80%">
        	<c:out value='${fn:substring(memoReprtVO.reprtDe, 0,4)}-${fn:substring(memoReprtVO.reprtDe, 4,6)}-${fn:substring(memoReprtVO.reprtDe, 6,8)}'/>
		</td>
 	</tr>
 	<tr> 
   		<th>
   			작성자
   		</th>
   		<td>
     		<c:out value="${memoReprtVO.wrterClsfNm}" escapeXml="false" />
   			<c:out value="${memoReprtVO.wrterNm}" escapeXml="false" />
  		</td>
	</tr>
	<tr> 
  		<th>
  			보고대상자
  		</th>
  		<td>
  			<c:out value="${memoReprtVO.reportrClsfNm}" escapeXml="false" />
			<c:out value="${memoReprtVO.reportrNm}" escapeXml="false" />
  		</td>
	</tr>
	<tr> 
  		<th>
  			보고제목
  		</th>
  		<td>
    		<c:out value="${memoReprtVO.reprtSj}" escapeXml="false" />
  		</td>
	</tr>
	<tr> 
  		<th>
  			보고내용
  		</th>
  		<td>
    		<br/>
    		<c:out value="${fn:replace(memoReprtVO.reprtCn , crlf , '<br>')}" escapeXml="false" />
   			<br/><br/>
  		</td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 -->
 	<tr>
		<th>
			파일첨부
		</th>
		<td>
			<c:import url="/content/files/${memoReprtVO.atchFileId}" />
		</td>
	</tr>
	<!-- //첨부파일 테이블 레이아웃 -->
	<tr> 
  		<th>
  			보고서상태
  		</th>
  		<td>
    		<c:out value="${memoReprtVO.reprtSttus}" escapeXml="false" />
  		</td>
	</tr>
	<c:choose>
	<c:when test="${memoReprtVO.wrterId eq uniqId && memoReprtVO.reportrId ne uniqId}">
	<tr> 
  		<th>
  			의견/지시사항
  		</th>
 		<td>
   			<br/>
   			<c:out value="${fn:replace(memoReprtVO.drctMatter , crlf , '<br>')}" escapeXml="false" />
    		<br/><br/>
  		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr> 
  		<th>
  			<label for="drctMatter">의견/지시사항</label>
  		</th>
 		<td>
   			<textarea id="drctMatter" name="drctMatter" rows="5" cols="75" title="의견/지시사항"><c:out value="${memoReprtVO.drctMatter}" escapeXml="false" /></textarea>
  		</td>
	</tr>
	</c:otherwise>
	</c:choose>
</tbody>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchSttus" />
<form:hidden path="searchDrctMatter" />
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="memoReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("memoReprtVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mrm/listMemoReprt.do";
    varForm.submit();	
}	

function fn_aram_edit() {
    var varForm = document.getElementById("memoReprtVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mrm/editMemoReprt.do";
    varForm.submit();					
}

function fn_aram_delete(){
    var varForm = document.getElementById("memoReprtVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/mrm/deleteMemoReprt.do";
		varForm.submit();
	}
}

function fn_aram_opinion(){
    var varForm = document.getElementById("memoReprtVO");
	if(confirm("의견등록을 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/cop/smt/mrm/updateMemoReprtDrctMatter.do";
		varForm.submit();
	}
}

</script>

