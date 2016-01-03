<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : KnoAppraisalDetail.jsp
  * @Description : 지식평가 상세조회
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
	<h2>지식평가 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;">평가</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoAppraisalVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<table class="table-detail" summary="이 표는 지식평가관리 대상 정보를 제공하며 조직명, 지식유형명, 지식명, 지식내용, 첨부파일 목록, 평가일자, 평가결과 정보로 구성되어 있습니다 .">
<caption>지식평가관리 상세정보</caption>
	<tr>
  		<th width="20%">
  			조직명
  		</th>          
  		<td>
  			${knoAppraisalVO.orgnztNm}
  		</td>    
	</tr>		
	<tr> 
  		<th>
  			지식유형명
  		</th>
  		<td>
  			${knoAppraisalVO.knoTypeNm}
  		</td>
	</tr>
	<tr>
		<th>
			지식명
		</th>          
	    <td>
	    	${knoAppraisalVO.knoNm}
	    </td>    
	</tr>
	<tr> 
	    <th>
	    	지식내용
	    </th>
	    <td>
	      <textarea name="knoCn" class="textarea" title="지식내용"  cols="300" rows="10"  style="width:450px;" readonly>${knoAppraisalVO.knoCn}</textarea>           
	    </td>
	</tr>				
  	<c:if test="${knoAppraisalVO.atchFileId != ''}">
	<tr> 
		<th>
			첨부파일 목록
		</th>
		<td>
			<c:import url="/content/files/${knoAppraisalVO.atchFileId}" />
		</td>
	</tr>
  	</c:if>
	<tr>
		<th>
			평가일자
		</th>          
	    <td>
		    <c:if test="${knoAppraisalVO.appYmd == null}">진행중</c:if>
		    <c:if test="${knoAppraisalVO.appYmd != null}">
		    	${fn:substring(knoAppraisalVO.appYmd, 0,4)}-${fn:substring(knoAppraisalVO.appYmd, 4,6)}-${fn:substring(knoAppraisalVO.appYmd, 6,8)}
		    </c:if>
	    </td>    
	</tr>
	<tr>
  		<th>
 			평가결과
  		</th>          
  		<td>
			<c:if test="${knoAppraisalVO.knoAps == null}">평가중</c:if>	    			
		    <c:if test="${knoAppraisalVO.knoAps == '1'}">승인</c:if>
		    <c:if test="${knoAppraisalVO.knoAps == '2'}">반려</c:if>		
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
function fn_aram_list(){
    var varForm = document.getElementById("knoAppraisalVO");
    varForm.action = "${pageContext.request.contextPath}/dam/app/listKnoAppraisal.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("knoAppraisalVO");
	varForm.action = "${pageContext.request.contextPath}/dam/app/editKnoAppraisal.do";
	varForm.submit();
}

</script>
