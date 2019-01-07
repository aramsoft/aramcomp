<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : MapMaterialEdit.jsp
  * @Description : 지식맵(유형별) 수정
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
	<h2>지식맵(유형별) 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mapMaterialVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="orgnztId"/>
<form:hidden path="knoTypeCd" />

<!-- 등록  폼 영역  -->
<table class="table-register"  
summary="이 표는 지식맵(유형별) 대상 정보를 제공하며, 조직ID, 조직명, , 지식유형코드, 지식유형명, 지식URL, 분류일자 정보로 구성되어 있습니다 .">
<caption>지식맵(유형별) 수정</caption>
	<tr>
    	<th width="20%">
    		조직ID
	    	<span class="required_icon"></span>
    	</th>          
 		<td>
 			${mapMaterialVO.orgnztId}
 		</td>
  	</tr>
	<tr>
    	<th>
    		조직명
	    	<span class="required_icon"></span>
    	</th>          
 		<td>
 			${mapMaterialVO.orgnztNm}
 		</td>
  	</tr>					  			  					
	<tr>
    	<th>
    		지식유형코드
	    	<span class="required_icon"></span>
    	</th>          
 		<td>
 			${mapMaterialVO.knoTypeCd}
 		</td>
  	</tr>
  	<tr>
    	<th>
    		지식유형명
	    	<span class="required_icon"></span>
    	</th>          
    	<td>
      		<form:input  path="knoTypeNm" title="지식유형명" size="60" maxlength="20"/>
      		<form:errors path="knoTypeNm" cssClass="error"/>
    	</td>    
  	</tr>			  	
  	<tr>
    	<th>
    		지식URL
	    	<span class="required_icon"></span>
    	</th>          
    	<td>
      		<form:input  path="knoUrl" title="지식URL" size="60" maxlength="100"/>
      		<form:errors path="knoUrl" cssClass="error"/>
    	</td>    
  	</tr>
  	<tr> 
    	<th>
    		분류일자
	    	<span class="required_icon"></span>
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
	  		<form:errors path="clYmd" cssClass="error"/>
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
    varForm.knoTypeNm.focus();
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
function fn_aram_update(){
    var varForm = document.getElementById("mapMaterialVO");

    if(!validateMapMaterialVO(varForm)){ 			
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/map/mat/updateMapMaterial.do";
		varForm.submit();
	}
}

</script>
