<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : KnoPersonalDetail.jsp
  * @Description : 개인지식 상세조회
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
	<h2>개인지식 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoPersonalVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<table class="table-detail" summary="이 표는 개인지식관리 정보를 제공하며, 조직명, 지식유형명, 지식명, 지식내용, 수집일자, 공개여부, 파일첨부 정보로 구성되어 있습니다 .">
<caption>개인지식관리 상세조회</caption>
	<tr>
 		<th width="20%">
 			조직명
 		</th>          
 		<td>
 			${knoPersonalVO.orgnztNm}
 		</td>    
	</tr>		
	<tr> 
 		<th>
 			지식유형명
 		</th>
 		<td>
 			${knoPersonalVO.knoTypeNm}
 		</td>
	</tr>
	<tr>
		<th>
			지식명
		</th>          
    	<td>
    		${knoPersonalVO.knoNm}
    	</td>    
	</tr>
	<tr> 
    	<th>
    		지식내용
    	</th>
    	<td>
      		<textarea name="knoCn" class="textarea" title="지식내용"  cols="300" rows="10"  style="width:450px;" readonly>${knoPersonalVO.knoCn}</textarea>           
    	</td>
	</tr>					  			  			
	<tr>
		<th>
			수집일자
		</th>          
    	<td>
			<c:out value="${fn:substring(knoPersonalVO.colYmd, 0, 4)}-${fn:substring(knoPersonalVO.colYmd, 4, 6)}-${fn:substring(knoPersonalVO.colYmd, 6, 8)}"/>
    	</td>    
	</tr>
	<tr>
		<th>
			공개여부
		</th>          
    	<td>
	    	<c:choose>
	    	<c:when test="${knoPersonalVO.othbcAt == 'Y'}">
	    		<spring:message code="cop.public" />
	    	</c:when>
	    	<c:otherwise>
	    		<spring:message code="cop.private" />
	    	</c:otherwise>
	    	</c:choose>				    
    	</td>    
	</tr>				
 	<c:if test="${knoPersonalVO.atchFileId != ''}">
	<tr> 
		<th>
			첨부파일 목록
		</th>
	    <td>
			<c:import url="/content/files/${knoPersonalVO.atchFileId}" />
	    </td>
	</tr>
 	</c:if>
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
function fn_aram_list(){
    var varForm = document.getElementById("knoPersonalVO");
    varForm.action = "${pageContext.request.contextPath}/dam/per/listKnoPersonal.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("knoPersonalVO");
	varForm.action = "${pageContext.request.contextPath}/dam/per/editKnoPersonal.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("knoPersonalVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/per/deleteKnoPersonal.do";
		varForm.submit();
	}
}

</script>
