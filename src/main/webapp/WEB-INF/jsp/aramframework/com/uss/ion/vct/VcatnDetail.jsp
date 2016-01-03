<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : VcatnDetail.jsp
 * @Description : 휴가 상세조회
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
	<h2>휴가 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${vcatnManageVO.confmAt eq 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
	  	</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="vcatnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="applcntId" />
<form:hidden path="vcatnSe" />
<form:hidden path="bgnde" />
<form:hidden path="endde" />
<form:hidden path="occrrncYear" />

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>휴가 신청자</h2>
</div>

<table class="table-detail" summary="신청자 정보">
<caption>신청자 정보</caption>
  	<tr>
    	<th width="20%">
    		신청자
    	</th>          
    	<td width="30%">
    		<c:out value='${vcatnManageVO.applcntNm}'/>
    	</td>
    	<th width="20%">
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${vcatnManageVO.orgnztNm}'/>
    	</td>
  	</tr> 
</table>

<table summary="신청자 연차 정보">
<caption>신청자 연차 정보</caption>
	<tr>
		<td colspan="4">&nbsp; </td>
	</tr>
	<tr>
		<td width="120px">[발생연차: ${indvdlYrycManageVO.occrncYrycCo}  ]</td>
		<td width="120px">[사용연차: ${indvdlYrycManageVO.useYrycCo   }  ]</td>
		<td width="120px">[잔여연차: ${indvdlYrycManageVO.remndrYrycCo}  ]</td>
		<td width="340px"> </td>
	</tr>
	<tr>
		<td colspan="4">&nbsp; </td>
	</tr>
</table>

<table class="table-detail" summary="휴가관리 상세">
<caption>휴가관리 상세</caption>
  	<tr>
    	<th width="20%">
     		휴가구분
    	</th>          
    	<td colspan="3">
    		<c:out value='${vcatnManageVO.vcatnSeNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		시작일자
    	</th>          
    	<td width="30%">
        	<c:out value='${fn:substring(vcatnManageVO.bgnde, 0,4)}-${fn:substring(vcatnManageVO.bgnde, 4,6)}-${fn:substring(vcatnManageVO.bgnde, 6,8)}'/>
    	</td>
    	<c:if test="${vcatnManageVO.vcatnSe ne '02' }">
	    	<th width="20%">
	    		종료일자
	    	</th>          
	    	<td width="30%">
	        	<c:out value='${fn:substring(vcatnManageVO.endde, 0,4)}-${fn:substring(vcatnManageVO.endde, 4,6)}-${fn:substring(vcatnManageVO.endde, 6,8)}'/>
	    	</td>
    	</c:if>
    	<c:if test="${vcatnManageVO.vcatnSe eq '02' }">
	    	<th width="20%">
	    		정오구분
	    	</th>          
	    	<td width="30%">
	       		<c:if test="${vcatnManageVO.noonSe eq '1' }">오전 </c:if>
	       		<c:if test="${vcatnManageVO.noonSe eq '2' }">오후 </c:if>
	    	</td>
    	</c:if>
  	</tr>
  	<tr>
    	<th width="20%">
    		휴가사유
    	</th>          
    	<td colspan="3">
       		<textarea id="remark" name="vcatnResn" class="txArea" rows="4" cols="70" title="휴가사유" readonly><c:out value='${vcatnManageVO.vcatnResn}' escapeXml="false" /></textarea>
    	</td>
  	</tr>
</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"> 
	<jsp:param name="infrmlSanctnId" value="${vcatnManageVO.infrmlSanctnId}"/>
</jsp:include>
<!-- //결재권자 정보 Include -->

<!-- 검색조건 유지 -->
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
    var varForm = document.getElementById("vcatnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/listVcatn.do";
	varForm.submit();
}

<c:if test="${vcatnManageVO.confmAt eq 'A' }">
/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("vcatnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/editVcatn.do";
	varForm.submit();   
}
/* ********************************************************
* 삭제처리화면
******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("vcatnManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/deleteVcatn.do";
        varForm.submit();
    }
}
</c:if>

</script>
