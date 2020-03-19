<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CpyrhtPrtcPolicyDetail.jsp
  * @Description : 저작권보호정책 상세조회
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
	<h2>저작권보호정책 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="cpyrhtPrtcPolicyVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="cpyrhtId" />

<table class="table-detail" summary="이 표는 저작권보호정책 정보를 제공하며, 저작권보호정책내용 정보로 구성되어 있습니다 .">
<caption>저작권보호정책 상세조회</caption>	 
  	<tr> 
    	<th width="25%">
    		저작권보호정책내용
    	</th>
    	<td width="75%">    
      		<form:textarea path="cpyrhtPrtcPolicyCn" cols="70" rows="25"  style="width:500px;" readonly="true" title="저작권보호정책내용" />                 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		등록일자
    	</th>
    	<td>
    		<fmt:formatDate value="${cpyrhtPrtcPolicyVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		마지막 수정일자
    	</th>
    	<td>
    		<fmt:formatDate value="${cpyrhtPrtcPolicyVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
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
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/listCpyrhtPrtcPolicy.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/editCpyrhtPrtcPolicy.do";
    varForm.submit();	
}

/**********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    
	if	(confirm("<spring:message code='common.delete.msg' />"))	{	
		varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/deleteCpyrhtPrtcPolicy.do";
		varForm.submit();
	}	
}

</script>
