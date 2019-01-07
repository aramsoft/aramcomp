<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : RwardDetail.jsp
 * @Description : 포상 상세조회
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
	<h2>포상 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${rwardManageVO.confmAt eq 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
	  	</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="rwardManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="rwardId" />

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>포상자</h2>
</div>

<table class="table-detail" summary="포상자정보">
<caption>포상자정보</caption>
  	<tr>
	    <th width="20%"  scope="row">
	    	포상자
	    </th>          
	    <td width="30%">
	    	<c:out value='${rwardManageVO.rwardManNm}'/>
	    </td>
	    <th width="20%"  scope="row">
	    	소속
	    </th>          
	    <td width="30%">
	    	<c:out value='${rwardManageVO.orgnztNm}'/>
	    </td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-detail" summary="포상 상세">
<caption>포상 상세</caption>
  	<tr>
    	<th width="20%">
    		포상구분
    	</th>          
    	<td colspan="3">
    		<c:out value='${rwardManageVO.rwardCdNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		포상명
    	</th>          
    	<td width="30%">
    		<c:out value='${rwardManageVO.rwardNm}'/>
    	</td>
    	<th width="20%">
    		포상일
    	</th>          
    	<td width="30%">
        	<c:out value='${fn:substring(rwardManageVO.rwardDe, 0,4)}-${fn:substring(rwardManageVO.rwardDe, 4,6)}-${fn:substring(rwardManageVO.rwardDe, 6,8)}'/>
     	</td>
  	</tr>
  	<tr>
    	<th width="20%">
     		공적사항
    	</th>          
    	<td colspan="3">
       		<textarea id="remark" name="pblenCn" class="txArea" rows="4" cols="70" title="공지사항" readOnly><c:out value='${rwardManageVO.pblenCn}' /></textarea>
    	</td>
  	</tr>

	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
  	<c:if test="${rwardManageVO.atchFileId != ''}">
	<tr> 
		<th>
			첨부파일목록
		</th>
		<td colspan="3">
			<c:import url="/content/files/${rwardManageVO.atchFileId}" />
		</td>
	</tr>
  	</c:if>	
	<!-- 첨부파일 테이블 레이아웃 End.-->

</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"> 
	<jsp:param name="infrmlSanctnId" value="${rwardManageVO.infrmlSanctnId}"/>
</jsp:include>
<!-- 결재권자 정보 Include -->

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("rwardManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/listRward.do";
	varForm.submit();
}

<c:if test="${rwardManageVO.confmAt eq 'A' }">
/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("rwardManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/editRward.do";
	varForm.submit();   
}

/* ********************************************************
* 삭제처리화면
******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("rwardManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/deleteRward.do";
        varForm.submit();
    }
}
</c:if>

</script>
