<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : StplatDetail.jsp
  * @Description : 약관 상세조회
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
	<h2>약관 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="stplatManageVO" action="${pageContext.request.contextPath}/uss/sam/stp/StplatDetailInqire.do" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="useStplatId" />

<table class="table-detail" summary="이 표는 약관내용 대상 정보를 제공하며, 이용약관명, 이용약관내용, 정보제공동의내용 정보로 구성되어 있습니다 .">
  	<tr> 
    	<th width="20%">
    		이용약관명
    	</th>
    	<td width="80%">
    		<c:out value="${stplatManageVO.useStplatNm}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		이용약관내용
    	</th>
    	<td>    
      		<form:textarea path="useStplatCn" class="textarea" title="이용약관내용" cols="90" rows="20"  readonly="true" />              
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		정보제공동의내용
    	</th>
    	<td>    
      		<form:textarea path="infoProvdAgreCn" class="textarea" title="정보제공동의내용" cols="90" rows="20"  readonly="true" />            
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		등록일자
    	</th>
    	<td>
    		<fmt:formatDate value="${stplatManageVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		마지막 수정일자
    	</th>
    	<td>
    		<fmt:formatDate value="${stplatManageVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
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

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("stplatManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/listStplat.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("stplatManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/editStplat.do";
	varForm.submit();	
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
	var varForm = document.getElementById("stplatManageVO");
	
	if	(confirm("<spring:message code='common.delete.msg' />"))	{	
		varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/deleteStplat.do";
		varForm.submit();
	}	
}	

</script>

