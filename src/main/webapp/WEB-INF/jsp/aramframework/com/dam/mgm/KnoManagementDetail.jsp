<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : KnoManagementDetail.jsp
  * @Description : 지식관리 상세조회
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
	<h2>지식관리 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<c:if test="${authority == 'ROLE_ADMIN'}">									
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;">폐기</a></span>
		</c:if>		  				  				  				
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoManagementVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<table class="table-detail" summary="이 표는 지식정보관리 정보를 제공하며, 조직명, 지식유형, 등록일자, 등록자명, 지식명, 지식내용, 공개여부, 평가일자, 평가자명, 평가결과, 지식정제, 폐기일자, 첨부파일 정보로 구성되어 있습니다 .">
<caption>지식정보관리 상세조회</caption>
	<tr>
  		<th width="20%">
  			조직명
  		</th>          
  		<td>
  			${knoManagementVO.orgnztNm}
  		</td>    
	</tr>		
	<tr> 
  		<th>
  			지식유형명
  		</th>
  		<td>
  			${knoManagementVO.knoTypeNm}
  		</td>
	</tr>
	<tr>
		<th>
			등록일자
		</th>          
	    <td>
			<fmt:formatDate value="${knoManagementVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
	    </td>    
	</tr>
	<tr> 
  		<th>
  			등록자명
  		</th>
  		<td>
  			${knoManagementVO.userNm}
  		</td>
	</tr>
	<tr>
		<th>
			지식명
		</th>          
	    <td>
	    	${knoManagementVO.knoNm}
	    </td>    
	</tr>
	<tr> 
	    <th>
	    	지식내용
	    </th>
	    <td>
	      	<textarea name="knoCn" class="textarea" title="지식내용"  cols="300" rows="5"  style="width:450px;" readonly>${knoManagementVO.knoCn}</textarea>           
	    </td>
	</tr>
	<tr>
		<th>
			공개여부
		</th>          
	    <td>
		    <c:choose>
		    <c:when test="${knoManagementVO.othbcAt == 'Y'}">
		    	<spring:message code="cop.public" />
		    </c:when>
		    <c:otherwise>
		    	<spring:message code="cop.private" />
		    </c:otherwise>
		    </c:choose>				    
	    </td>    
	</tr>
	<tr>
		<th>
			평가일자
		</th>          
	    <td>
		    <c:if test="${knoManagementVO.appYmd == null}">진행중</c:if>
		    <c:if test="${knoManagementVO.appYmd != null}"><c:out value="${fn:substring(knoManagementVO.appYmd, 0, 4)}-${fn:substring(knoManagementVO.appYmd, 4, 6)}-${fn:substring(knoManagementVO.appYmd, 6, 8)}"/></c:if>
	    </td>    
	</tr>
	<tr> 
  		<th>
   			평가자명
  		</th>
  		<td>
  			${knoManagementVO.speNm}
  		</td>
	</tr>
	<tr>
  		<th>
  			평가결과
  		</th>          
  		<td>
			<c:if test="${knoManagementVO.knoAps == null}">평가중</c:if>	    			
		    <c:if test="${knoManagementVO.knoAps == '1'}">승인</c:if>
		    <c:if test="${knoManagementVO.knoAps == '2'}">반려</c:if>		
  		</td>    
	</tr>	  					  			
	<tr>
  		<th>
  			지식정제
  		</th>          
  		<td>			
		    <c:if test="${knoManagementVO.knoAps == '1'}">가용</c:if>
		    <c:if test="${knoManagementVO.knoAps == '3'}">폐기</c:if>
  		</td>    
	</tr>
	<tr>
		<th>
			폐기일자
		</th>          
		<td>
		    <c:if test="${knoManagementVO.junkYmd != null}"><c:out value="${fn:substring(knoManagementVO.junkYmd, 0, 4)}-${fn:substring(knoManagementVO.junkYmd, 4, 6)}-${fn:substring(knoManagementVO.junkYmd, 6, 8)}"/></c:if>
		</td>
	</tr>			  			
  	<c:if test="${knoManagementVO.atchFileId != ''}">
	<tr> 
		<th>
			첨부파일 목록
		</th>
		<td>
			<c:import url="/content/files/${knoManagementVO.atchFileId}" />
		</td>
	</tr>
  	</c:if>		  						  					  										  	
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
function fn_aram_list(){
    var varForm = document.getElementById("knoManagementVO");
    varForm.action = "${pageContext.request.contextPath}/dam/mgm/listKnoManagement.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("knoManagementVO");
	varForm.action = "${pageContext.request.contextPath}/dam/mgm/editKnoManagement.do";
	varForm.submit();
}

</script>
		
