<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AnnvrsryDetail.jsp
 * @Description : 기념일 상세조회
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
	<h2>기념일 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="annvrsryManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="annId" />

<table class="table-detail" summary="기념일관리 상세">
<caption>기념일관리 상세</caption>
  	<tr> 
    	<th width="20%">
    		신청자
    	</th>
    	<td width="20%"> 
    		<c:out value="${annvrsryManageVO.usNm}"/>
    	</td>
    	<th width="20%">
    		소속
    	</th>
    	<td width="40%">
    		<c:out value="${annvrsryManageVO.orgnztNm}"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		기념일구분
    	</th>
    	<td>
    		<c:out value="${annvrsryManageVO.annvrsrySeNm}"/>
    	</td>
    	<th>
     		기념일
    	</th>
    	<td>
    		<c:out value="${annvrsryManageVO.annvrsryDeNm}"/>&nbsp;&nbsp;
    		<c:if test="${'1' eq annvrsryManageVO.reptitSe}"><b>매년반복</b></c:if>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		기념일제목
    	</th>
    	<td colspan="3">
    		<c:out value="${annvrsryManageVO.annvrsryNm}"/> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		메모
    	</th>
    	<td colspan="3">
       		<TEXTAREA id="textArae" style="width:95%;height:100px;" title="메모" readOnly><c:out value="${annvrsryManageVO.memo}"  escapeXml="false"/></TEXTAREA>
    	</td> 
  	</tr>
  	<tr> 
    	<th>
     		알림시작일
    	</th>
    	<td>
    		D-<c:out value="${annvrsryManageVO.annvrsryBeginDe}"/>일전 알림
    	</td>
    	<th>
     		알림설정
    	</th>
    	<td>
    		<c:out value="${annvrsryManageVO.annvrsrySetupNm}"/> 
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

/*설명 : 기념일 목록 조회 */
function fn_aram_list() {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsry.do";
    varForm.submit();       
}

/*설명 : 기념일 수정조회 */
function fn_aram_edit() {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/editAnnvrsry.do";
    varForm.submit();   
}

/*설명 : 기념일 삭제처리*/
function fn_aram_delete() {
    var varForm = document.getElementById("annvrsryManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/deleteAnnvrsry.do";
        varForm.submit();
    }
}

</script>
