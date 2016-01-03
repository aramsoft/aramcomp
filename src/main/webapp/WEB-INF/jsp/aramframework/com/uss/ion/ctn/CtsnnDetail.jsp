<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : CtsnnDetail.jsp
 * @Description : 경조사 상세조회
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
	<h2>경조사 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${ctsnnManageVO.confmAt eq 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
	  	</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="ctsnnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="ctsnnId" />

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>경조 신청자</h2>
</div>

<table class="table-detail" summary="소속정보">
<caption>소속정보</caption>
  	<tr>
    	<th width="20%">
    		경조자
    	</th>          
    	<td width="30%">
    		<c:out value='${ctsnnManageVO.usNm}'/>
    	</td>
    	<th width="20%">
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${ctsnnManageVO.orgnztNm}'/>
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-detail" summary="경조  상세">
<caption>경조 상세</caption>
  	<tr>
    	<th width="20%">
    		경조명
    	</th>          
    	<td colspan="3">
    		<c:out value='${ctsnnManageVO.ctsnnNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		경조구분
    	</th>          
    	<td width="30%">
    		<c:out value='${ctsnnManageVO.ctsnnCdNm}'/>
    	</td>
    	<th width="20%">
    		발생일
    	</th>          
    	<td width="30%">
			<c:out value="${fn:substring(ctsnnManageVO.occrrDe, 0,4)}-${fn:substring(ctsnnManageVO.occrrDe, 4,6)}-${fn:substring(ctsnnManageVO.occrrDe, 6,8)}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		대상자명
    	</th>          
    	<td colspan="3">
    		<c:out value='${ctsnnManageVO.trgterNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		관계
    	</th>          
    	<td>
    		<c:out value='${ctsnnManageVO.relateNm}'/>
    	</td>
    	<th>
    		생년월일
    	</th>          
    	<td>
			<c:out value="${fn:substring(ctsnnManageVO.brth, 0,4)}-${fn:substring(ctsnnManageVO.brth, 4,6)}-${fn:substring(ctsnnManageVO.brth, 6,8)}" escapeXml="false" />
     	</td>
  	</tr>
  	<tr>
    	<th>
    		비고
    	</th>          
    	<td colspan="3">
      		<textarea id="remark" name="remark" class="txArea" rows="4" cols="70" readonly title="비고"><c:out value='${ctsnnManageVO.remark}'/></textarea>
    	</td>
  	</tr>
</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"> 
	<jsp:param name="infrmlSanctnId" value="${ctsnnManageVO.infrmlSanctnId}"/>
</jsp:include>
<!-- //결재권자 정보 Include -->

<!-- 검색조건 유지 -->
<form:hidden path="searchFromDate" />
<form:hidden path="searchToDate" />
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
    var varForm = document.getElementById("ctsnnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/listCtsnn.do";
	varForm.submit();
}

<c:if test="${ctsnnManageVO.confmAt eq 'A' }">	
/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("ctsnnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/editCtsnn.do";
	varForm.submit();   
}

/* ********************************************************
* 삭제처리화면
******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("ctsnnManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/deleteCtsnn.do";
        varForm.submit();
    }
}
</c:if>

</script>
