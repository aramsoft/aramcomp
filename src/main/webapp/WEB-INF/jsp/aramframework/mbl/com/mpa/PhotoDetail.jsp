<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : PhotoDetail.java
  * @Description : 사진 상세조회
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
	<h2>사진 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="photoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register" summary="사진정보 정보테이블.">

  	<tr> 
	    <th width="20%">
	    	사진제목&nbsp;&nbsp;
	    </th>
	    <td width="80%">
	        <c:out value="${photoVO.photoSj}"/>  
	    </td>
  	</tr>
 
  	<c:if test="${photoVO.atchFileId != ''}">
    <tr> 
        <th>
        	사진 파일&nbsp;&nbsp;
        </th>
       	<td>
			<c:import url="/content/files/${photoVO.atchFileId}" />
           	<img title='<c:out value="${photoVO.photoSj}"/>' alt='<c:out value="${photoVO.photoSj}"/>' src='${pageContext.request.contextPath}/content/imagefiles/${photoVO.atchFileId}/file/0' class="mcomd-photo" />
       	</td>
    </tr>
  	</c:if>      

  	<tr> 
	    <th>
	    	생성일시&nbsp;&nbsp;
	    </th>
	    <td>
	        <c:out value="${photoVO.creatDt}"/>
	    </td>
  	</tr> 

</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>
	
</div>	

<script type="text/javaScript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("photoVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mpa/listPhoto.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("photoVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mpa/editPhoto.do";
    varForm.submit();   
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("photoVO");
    
    if  (confirm("<spring:message code='common.delete.msg' />"))    {   
    	varForm.action = "${pageContext.request.contextPath}/mbl/com/mpa/deletePhoto.do";
    	varForm.submit();
    }
}
</script>
