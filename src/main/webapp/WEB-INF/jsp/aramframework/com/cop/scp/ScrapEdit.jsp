<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : ScrapEdit.jsp
 * @Description : 스크랩 수정
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
	<h2>스크랩 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="scrapVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bbsId" />
<form:hidden path="nttId" />
<form:hidden path="scrapId" />

<table class="table-register"  summary="스크랩에 대한 정보를 수정합니다.">
<tbody>
	<tr> 
	    <th width="14%">
	    	<span class="required_icon"></span>
	    	<label for="scrapNm">스크랩명</label>
	    </th>
	    <td width="86%" colspan="5">
	    	<form:input path="scrapNm" size="50" maxlength="50" style="width:100%" />
	    	<form:errors path="scrapNm" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="norequired_icon"></span>
	    	제목
	    </th>
	    <td colspan="5"><c:out value="${boardVO.nttSj}" /></td>
	</tr>
	<tr> 
	    <th width="14%">
	    	<span class="norequired_icon"></span>
	    	작성자
	    </th>
	    <td width="20%">
			<c:out value="${boardVO.frstRegisterNm}" />
	    </td>
	    <th width="13%">
	    	<span class="norequired_icon"></span>
	   		 작성시간
	   	</th>
	    <td width="20%">
	    	<fmt:formatDate value="${boardVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
	    </td>
	    <th width="13%">
	    	<span class="norequired_icon"></span>
	    	조회수
	    </th>
	    <td width="20%"><c:out value="${boardVO.rdcnt}" /></td>
	</tr>    
	<tr> 
	    <th>
	    	<span class="norequired_icon"></span>
	    	글내용
	    </th>
	    <td colspan="5">
	     	<div class="bbs_cn">
<c:out value="${boardVO.nttCn}" escapeXml="false" />	
	     	</div>
	    </td>
	</tr>
	<c:if test="${not empty boardVO.atchFileId}">
	<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA02'}">
	<tr> 
		<th>
	    	<span class="norequired_icon"></span>
			첨부이미지
		</th>
		<td colspan="5">
			<c:import url="/content/imagefiles/${boardVO.atchFileId}" charEncoding="utf-8" />
		</td>
	</tr>
	</c:if>
	<tr> 
	    <th>
	    	<span class="norequired_icon"></span>
	    	첨부파일 목록
	    </th>
	    <td colspan="5">
			<c:import url="/content/files/${boardVO.atchFileId}" />
	    </td>
	  </tr>
	</c:if>
</tbody>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="scrapVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("scrapVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/listScrap.do";
    varForm.submit();	
}

function fn_aram_update() {
    var varForm = document.getElementById("scrapVO");

    if (!validateScrapVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/scp/updateScrap.do";
		varForm.submit();					
	}
}

</script>

