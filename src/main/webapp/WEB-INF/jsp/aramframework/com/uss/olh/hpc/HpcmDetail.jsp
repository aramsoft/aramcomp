<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : HpcmDetail.jsp
  * @Description : 도움말 상세조회
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
	<h2>도움말 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="hpcmManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="hpcmId" />

<table class="table-detail" summary="도움말에 대한 정보를 조회합니다.">
<caption>도움말상세조회</caption>
  	<tr> 
    	<th width="20%">
    		도움말구분
    	</th>
    	<td>
    		<c:out value="${hpcmManageVO.hpcmSeCodeNm}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		도움말정의
    	</th>
    	<td>
      		<textarea name="hpcmDfn" class="textarea"  cols="300" rows="5"  style="width:450px;"  readonly title="도움말정의">
<c:out value="${hpcmManageVO.hpcmDfn}"/>
      		</textarea>                   
    	</td>
  	</tr>
    <tr> 
    	<th>
    		도움말설명
    	</th>
    	<td>    
      		<textarea name="hpcmDc" class="textarea"  cols="300" rows="30"  style="width:450px;"  readonly title="도움말설명">
<c:out value="${hpcmManageVO.hpcmDc}"/>
      		</textarea>                                    
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		등록일자
    	</th>
    	<td>
    		<fmt:formatDate value="${hpcmManageVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		마지막 수정일자
    	</th>
    	<td>
    		<fmt:formatDate value="${hpcmManageVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
  	</tr> 
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("hpcmManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/hpc/listHpcm.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("hpcmManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/hpc/editHpcm.do";
    varForm.submit();	
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("hpcmManageVO");
    
	if	(confirm("<spring:message code='common.delete.msg' />"))	{	
		varForm.action = "${pageContext.request.contextPath}/uss/olh/hpc/deleteHpcm.do";
		varForm.submit();
	}
}

</script>

