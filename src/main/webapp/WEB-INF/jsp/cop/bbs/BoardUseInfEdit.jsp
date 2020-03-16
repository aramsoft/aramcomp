<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardUseInfEdit.jsp
  * @Description : 게시판 사용 수정
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
	<h2>게시판 사용 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
       	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="boardUseInfVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bbsId" />
<form:hidden path="trgetId" />

<table class="table-register" summary="게시판명, 커뮤니티/ 동호회명, 사용여부  입니다">
	<tr>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	게시판명
	    </th>
	    <td>
	    	<c:out value="${boardUseInfVO.bbsNm}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	커뮤니티명
	    </th>
	    <td>
	    	<c:choose>
	    	<c:when test="${not empty boardUseInfVO.cmmntyNm}">
	    		<c:out value="${boardUseInfVO.cmmntyNm}" />
	    	</c:when>
   			<c:otherwise>(시스템  활용)</c:otherwise>
			</c:choose>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	사용여부
	    </th>
	    <td>
	     	<label for="useAtY"><spring:message code="button.use" /></label> : <input type="radio" name="useAt" id="useAtY" class="radio2" value="Y"  <c:if test="${boardUseInfVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
	     	<label for="useAtN"><spring:message code="button.notUsed" /></label> : <input type="radio" name="useAt" id="useAtN" class="radio2" value="N" <c:if test="${boardUseInfVO.useAt == 'N'}"> checked="checked"</c:if>>
	     	<form:errors path="useAt" cssClass="error"/>
	    </td>
	</tr>
	<c:if test="${not empty boardUseInfVO.provdUrl}">
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	제공 URL
	    </th>
	    <td>
	    	<a href="<c:out value="${boardUseInfVO.provdUrl}" />" target="_new">
	    	   	<c:out value="${boardUseInfVO.provdUrl}" />
			</a>
	    </td>
	</tr>
	</c:if>
	<c:if test="${not empty boardUseInfVO.provdUrl2}">
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	제공 URL
	    </th>
	    <td>
	    	<a href="<c:out value="${boardUseInfVO.provdUrl2}" />" target="_new">
	    	   	<c:out value="${boardUseInfVO.provdUrl2}" />
			</a>
	    </td>
	</tr>
	</c:if>
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
<validator:javascript formName="boardUseInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardUseInfVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardUseInf.do";
    varForm.submit();
}

function fn_aram_update(){
    var varForm = document.getElementById("boardUseInfVO");
    
	if (!validateBoardUseInfVO(varForm)){
		return;
	}

	if(confirm("<spring:message code='common.update.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateBoardUseInf.do";
		varForm.submit();
	}	
}

</script>

