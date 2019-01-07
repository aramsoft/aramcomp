<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : SiteInfoDetail.jsp
  * @Description : 사이트 상세조회
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
	<h2>사이트 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="siteManageVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="siteId" />
      
<!-- 등록  폼 영역  -->
<table class="table-detail" summary="사이트 정보테이블">
  	<tr> 
    	<th width="20%">
    		사이트명
    	</th>
	    <td width="80%">
	        <c:out value="${siteManageVO.siteNm}"/>  
	    </td>
  	</tr>
  	<tr> 
	    <th>
	    	사이트 URL
	    </th>
	    <td>
	        <c:out value="${siteManageVO.siteUrl}"/>  
	    </td>
 	</tr>
    <tr> 
	    <th>
	    	<label id="idSiteDc" for="siteDc">사이트 설명</label>
	    </th>
	    <td>    
	      	<textarea name="siteDc" id="siteDc" class="textarea"  cols="300" rows="10"  style="width:450px;" readonly><c:out value="${siteManageVO.siteDc}"/>
	      	</textarea>                 
	    </td>
  	</tr> 
  	<tr> 
    	<th>
    		사이트주제분류
    	</th>
    	<td>    
        	<c:out value="${siteManageVO.siteThemaClNm}"/> 
    	</td>
  	</tr>  
  	<tr> 
    	<th>
   			활성여부
    	</th>
	    <td>
	        <c:out value="${siteManageVO.actvtyAtNm}"/>  
	    </td>
  	</tr> 
  	<tr> 
	    <th>
	    	사용여부
	    </th>
	    <td>
	        <c:out value="${siteManageVO.useAtNm}"/>  
	    </td>
  	</tr> 
  	<tr> 
    	<th>
    		최종수정일자
    	</th>
	    <td>
	        <fmt:formatDate value="${siteManageVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
	    </td>
  	</tr> 
  	<tr> 
    	<th>
    		등록자
    	</th>
	    <td>
	        <c:out value="${siteManageVO.emplyrNm}"/>  
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
    var varForm = document.getElementById("siteManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/sit/listSiteInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("siteManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/sit/editSiteInfo.do";
    varForm.submit();   
}

/* ********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_aram_delete(){   
    var varForm = document.getElementById("siteManageVO");

    if  (confirm("<spring:message code='common.delete.msg' />"))    { 
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/sit/deleteSiteInfo.do";
    	varForm.submit();   
    }       
}

</script>

