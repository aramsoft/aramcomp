<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : KnoSpecialistDetail.jsp
  * @Description : 지식전문가 상세조회
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
	<h2>지식전문가 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoSpecialistVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="orgnztId"/>
<form:hidden path="knoTypeCd"/>
<form:hidden path="speId"/>
<form:hidden path="appTypeCd"/>

<table class="table-detail" summary="이 표는 지식전문가 정보를 제공하며, 조직명, 지식유형명, 전문가성명, 등급, 전문가설명, 승인일자 정보로 구성되어 있습니다 .">
<caption>지식전문가 상세조회</caption>
	<tr>
  		<th width="20%">
  			조직명
  		</th>          
  		<td>
  			${knoSpecialistVO.orgnztNm}
  		</td>    
	</tr>				
	<tr>
		<th>
			지식유형명
		</th>          
	    <td>
	    	${knoSpecialistVO.knoTypeNm}
	    </td>    
	</tr>
	<tr>
  		<th>
  			전문가명
  		</th>          
  		<td>
  			${knoSpecialistVO.userNm}
  		</td>    
	</tr>
	<tr>
  		<th>
  			등급
  		</th>          
  		<td>
		    <c:if test="${knoSpecialistVO.appTypeCd == '1'}">수석</c:if>
		    <c:if test="${knoSpecialistVO.appTypeCd == '2'}">책임</c:if>
		    <c:if test="${knoSpecialistVO.appTypeCd == '3'}">선임</c:if>							       			
  		</td>    
	</tr>
	<tr> 
	    <th>
	    	전문가설명
	    </th>
	    <td>
	      	<textarea name="speExpCn" class="textarea" title="전문가설명"  cols="300" rows="10"  style="width:450px;" readonly>${knoSpecialistVO.speExpCn}</textarea>           
	    </td>
	</tr>			  					  					   
	<tr>
		<th>
			승인일자
		</th>          
	    <td>
	    	<c:out value="${fn:substring(knoSpecialistVO.speConfmDe, 0, 4)}-${fn:substring(knoSpecialistVO.speConfmDe, 4, 6)}-${fn:substring(knoSpecialistVO.speConfmDe, 6, 8)}"/>
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
function fn_aram_list(){
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/listKnoSpecialist.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("knoSpecialistVO");
	varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/editKnoSpecialist.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("knoSpecialistVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/deleteKnoSpecialist.do";
		varForm.submit();
	}
}

</script>
