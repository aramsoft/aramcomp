<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : KnoManagementEdit.jsp
  * @Description : 지식관리 수정
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
	<h2>지식관리 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoManagementVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 지식정보관리 정보를 제공하며, 조직명, 지식유형명, 등록일자, 등록자명, 지식명, 지식내용, 공개여부, 평가일자, 평가자명, 평가결과, 지식정제, 폐기일자, 첨부파일 정보로 구성되어 있습니다 .">
<caption>지식정보관리 수정</caption>
	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		조직명
    	</th>          
 		<td>
 			${knoManagementVO.orgnztNm}
 		</td>
  	</tr>
	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지식유형명
    	</th>          
 		<td>
 			${knoManagementVO.knoTypeNm}
 		</td>
  	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			등록일자
		</th>          
	    <td>
			<fmt:formatDate value="${knoManagementVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
	    </td>    
	</tr>
	<tr> 
  		<th>
  			<span class="required_icon"></span>
  			등록자명
  		</th>
  		<td>
  			${knoManagementVO.userNm}
  		</td>
	</tr>				  	
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지식명
    	</th>          
 		<td>
 			${knoManagementVO.knoNm}
 		</td>				    	   
  	</tr>
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		지식내용
    	</th>
    	<td>
      		<textarea name="knoCn" class="textarea" title="지식내용" cols="300" rows="5" style="width:450px;" readonly>${knoManagementVO.knoCn}</textarea>               
    	</td>
  	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
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
    		<span class="required_icon"></span>
    		평가일자
    	</th>
	    <td>
		    <c:if test="${knoManagementVO.appYmd == null}">진행중</c:if>
		    <c:if test="${knoManagementVO.appYmd != null}"><c:out value="${fn:substring(knoManagementVO.appYmd, 0, 4)}-${fn:substring(knoManagementVO.appYmd, 4, 6)}-${fn:substring(knoManagementVO.appYmd, 6, 8)}"/></c:if>
	    </td>
  	</tr>
	<tr> 
  		<th>
  			<span class="required_icon"></span>
  			평가자명
  		</th>
  		<td>
  			${knoManagementVO.speNm}
  		</td>
	</tr>
	<tr>
  		<th>
  			<span class="required_icon"></span>
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
    		<span class="required_icon"></span>
    		지식정제
    	</th>          
 		<td>
			<select name="knoAps" title="지식정제 선택">				  		 							    
			    <option value="3" <c:if test="${knoManagementVO.knoAps == '3'}">selected</c:if>>폐기</option>
			</select>						  	    				
 		</td>				    	
  	</tr>			  			
	<tr>
		<th>
    		<span class="norequired_icon"></span>
			폐기일자
		</th>          
    	<td width="80%">
      		<form:hidden path="junkYmd" />
	    	<c:if test="${!empty knoManagementVO.junkYmd}">
 				<c:set var="junkYmdVal" value="${fn:substring(knoManagementVO.junkYmd, 0,4)}-${fn:substring(knoManagementVO.junkYmd, 4,6)}-${fn:substring(knoManagementVO.junkYmd, 6,8)}"/>
      		</c:if>
      		<input name="junkYmdView" id="junkYmdView" type="text" size="10" title="평가일달력" value="${junkYmdVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].junkYmd, document.forms[0].junkYmdView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	  		<form:errors path="junkYmd" cssClass="error"/>
    	</td>						
	</tr>									  	
 	<!-- 첨부목록을 보여주기 위한 -->  
  	<c:if test="${knoManagementVO.atchFileId ne null && knoManagementVO.atchFileId ne ''}">
	<tr> 
		<th>
    		<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
	    <td>
			<c:import url="/content/files/${knoManagementVO.atchFileId}" />
	    </td>
	</tr>
  	</c:if>	  		  	
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="knoManagementVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {				
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("knoManagementVO");
    varForm.knoAps.focus();
};	

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("knoManagementVO");
    varForm.action = "${pageContext.request.contextPath}/dam/mgm/listKnoManagement.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("knoManagementVO");

	if(!validateKnoManagementVO(varForm)){ 			
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/mgm/updateKnoManagement.do";
		varForm.submit();
	}
}

</script>
