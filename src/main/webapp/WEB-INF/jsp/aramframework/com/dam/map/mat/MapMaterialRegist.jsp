<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : MapMaterialRegist.jsp
  * @Description : 지식맵(유형별) 등록
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
	<h2>지식맵(유형별) 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mapMaterialVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 지식맵(유형별) 대상 정보를 제공하며, 조직명, , 지식유형코드, 지식유형명, 지식URL, 분류일자 정보로 구성되어 있습니다 .">
<caption>지식맵(유형별) 등록</caption>
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	조직명
	    </th>          
	    <td width="80%">
			<select name="orgnztId" class="select" title="조직명">
				<c:forEach var="result" items="${mapTeamList}" varStatus="status">
					<option value='<c:out value="${result.orgnztId}"/>'><c:out value="${result.orgnztNm}"/></option>
				</c:forEach>			  		   
			</select>
	    </td>
	</tr> 					
	<tr>
  		<th>
	    	<span class="required_icon"></span>
  			지식유형코드
  		</th>          
  		<td>
    		<form:input  path="knoTypeCd" size="30" title="지식유형코드" maxlength="3"/>
    		<form:errors path="knoTypeCd" cssClass="error"/>
 	 	</td>
	</tr> 
	<tr>
		<th>
	    	<span class="required_icon"></span>
			지식유형명
		</th>          
	    <td>
	    	<form:input  path="knoTypeNm" title="지식유형명" size="60" maxlength="20"/>
	      	<form:errors path="knoTypeNm" cssClass="error"/>
	    </td>    
	</tr>		
	<tr>
  		<th>
 	    	<span class="required_icon"></span>
  			지식URL
  		</th>          
  		<td>
    		<form:input  path="knoUrl" title="지식URL" size="60" maxlength="100"/>
    		<form:errors path="knoUrl" cssClass="error"/>
  		</td>    
	</tr>   
	<tr> 
  		<th>
	    	<span class="required_icon"></span>
  			분류일자
  		</th>
  		<td>
      		<form:hidden path="clYmd" />
	    	<c:if test="${!empty mapMaterialVO.clYmd}">
 				<c:set var="clYmdVal" value="${fn:substring(mapMaterialVO.clYmd, 0,4)}-${fn:substring(mapMaterialVO.clYmd, 4,6)}-${fn:substring(mapMaterialVO.clYmd, 6,8)}"/>
      		</c:if>
      		<input name="clYmdView" id="clYmdView" type="text" size="10" title="분류일자" value="${clYmdVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].clYmd, document.forms[0].clYmdView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
 		</td>
	</tr> 
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="mapMaterialVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("mapMaterialVO");
    varForm.orgnztId.focus();
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("mapMaterialVO");
    varForm.action = "${pageContext.request.contextPath}/dam/map/mat/listMapMaterial.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("mapMaterialVO");

	if(!validateMapMaterialVO(varForm)){ 			
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/map/mat/insertMapMaterial.do";
		varForm.submit();
	}
}

</script>
