<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : KnoSpecialistRegist.jsp
  * @Description : 지식전문가 등록
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
	<h2>지식전문가 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area">
	</div>
</div>

<form:form commandName="knoSpecialistVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 지식전문가 정보를 제공하며, 조직명, 지식유형명, 전문가성명, 등급, 전문가설명, 승인일자 정보로 구성되어 있습니다 .">
<caption>지식전문가 등록</caption>	  									
	<tr>
		<th width="20%">
			<span class="required_icon"></span>
			조직명
		</th>          
	    <td width="80%">
			<select name="orgnztId" class="select" onChange="javascript:fn_aram_get_codeId()">
				<c:forEach var="result" items="${mapTeamList}" varStatus="status">							
				<option value='<c:out value="${result.orgnztId}"/>' <c:if test="${result.orgnztId == knoSpecialistVO.orgnztId}">selected="selected"</c:if>><c:out value="${result.orgnztNm}"/></option>
				</c:forEach>			  		   
			</select>						
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	지식유형명
	    </th>          
	    <td>
			<select name="knoTypeCd" class="select">						
				<c:forEach var="result" items="${mapMaterialList}" varStatus="status">
					<option value='<c:out value="${result.knoTypeCd}"/>'><c:out value="${result.knoTypeNm}"/></option>
				</c:forEach>			  		   
			</select>	
	    </td>
	</tr>					
	<tr> 
  		<th>
  			<span class="required_icon"></span>
  			전문가명
  		</th>
	    <td>
	      	<form:input path="userNm" size="30" maxlength="60" />
	      	<form:hidden path="speId" />
	      	&nbsp;
	      	<a href="#" onclick="javascript:fn_aram_get_user(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	      		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search"/>
	      	</a>
	     	<form:errors path="userNm" cssClass="error"/>			
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	등급
	    </th>          
	    <td>
		   	<select name="appTypeCd" title="등급 선택">
			   	<option value="1">수석</option>
			   	<option value="2">책임</option>
			   	<option value="3">선임</option>					   	
		  	</select>
	    </td>
	</tr>
	<tr>
  		<th>
  			<span class="required_icon"></span>
  			전문가설명
  		</th>          
  		<td>
    		<form:textarea  path="speExpCn" title="전문가설명" cols="100" rows="10" cssClass="txArea"/>
    		<form:errors path="speExpCn" cssClass="error"/>
  		</td>    
	</tr>			  																						
	<tr> 
  		<th>
    		<span class="required_icon"></span>
  			승인일자
  		</th>
  		<td>
      		<form:hidden path="speConfmDe" />
	    	<c:if test="${!empty knoSpecialistVO.speConfmDe}">
 				<c:set var="speConfmDeVal" value="${fn:substring(knoSpecialistVO.speConfmDe, 0,4)}-${fn:substring(knoSpecialistVO.speConfmDe, 4,6)}-${fn:substring(knoSpecialistVO.speConfmDe, 6,8)}"/>
      		</c:if>
      		<input name="speConfmDeView" id="speConfmDeView" type="text" size="10" title="승인일자" value="${speConfmDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].speConfmDe, document.forms[0].speConfmDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
 		</td>
	</tr> 
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="knoSpecialistVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.orgnztId.focus();
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/listKnoSpecialist.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
 function fn_aram_insert(){
	var varForm = document.getElementById("knoSpecialistVO");

	if(!validateKnoSpecialistVO(varForm)){ 			
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/insertKnoSpecialist.do";								
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 사용자검색 팝업
 ******************************************************** */
function fn_aram_get_user(){
	var varForm = document.getElementById("knoSpecialistVO");
	gArguments["uniqId"] = varForm.speId;
	gArguments["userNm"] = varForm.userNm;

	var url = "/cop/com/listUser.do?PopFlag=Y";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 지식유형 가져오기
 ******************************************************** */
function fn_aram_get_codeId(){
	var varForm = document.getElementById("knoSpecialistVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/registKnoSpecialist.do";
	varForm.submit();
}			

</script>
