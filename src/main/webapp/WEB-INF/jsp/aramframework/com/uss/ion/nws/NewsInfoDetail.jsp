<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NewsInfoDetail.jsp
  * @Description : 뉴스정보 상세조회
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
	<h2>뉴스정보 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="newsManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="newsId" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="뉴스정보 정보테이블.">
  	<tr> 
    	<th width="20%">
    		뉴스제목
    	</th>
    	<td width="80%">
        	<c:out value="${newsManageVO.newsSj}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		<label for="newsCn">뉴스내용</label>
    	</th>
    	<td>
      		<textarea name="newsCn" id="newsCn" class="textarea"  cols="300" rows="30"  style="width:450px;" readonly><c:out value="${newsManageVO.newsCn}"/>
      		</textarea>                   
    	</td>
  	</tr>
    <tr> 
    	<th>
     		뉴스출처
   		</th>
    	<td>
        	<c:out value="${newsManageVO.newsOrigin}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		게시일자
    	</th>
    	<td>
        	<c:out value='${fn:substring(newsManageVO.ntceDe, 0,4)}-${fn:substring(newsManageVO.ntceDe, 4,6)}-${fn:substring(newsManageVO.ntceDe, 6,8)}'/>
    	</td>
  	</tr> 
  	<c:if test="${newsManageVO.atchFileId != ''}">
    <tr> 
        <th>
         	첨부파일 목록
        </th>
        <td>
			<c:import url="/content/files/${newsManageVO.atchFileId}" />
        </td>
  	</tr>
  	</c:if>      
    <tr> 
    	<th>
    		등록일자
    	</th>
    	<td>
			<fmt:formatDate value="${newsManageVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
  	</tr> 
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
function fn_aram_list() {
    var varForm = document.getElementById("newsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/listNewsInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("newsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/editNewsInfo.do";
    varForm.submit();   
}

function fn_aram_delete(){
    var varForm = document.getElementById("newsManageVO");
    
    if  (confirm("<spring:message code='common.delete.msg' />"))    {   
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/deleteNewsInfo.do";
    	varForm.submit();
    }
}

</script>

