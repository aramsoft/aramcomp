<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : AnnvrsryEdit.jsp
 * @Description : 기념일 수정
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
	<h2>기념일 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="annvrsryManageVO" method="post" action=""> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="annId"   />
<form:hidden path="usid"   />

<table class="table-register" summary="기념일관리 수정">
<caption>기념일관리 수정</caption>
  	<tr> 
    	<th width="20%" >
    		<span class="required_icon"></span>
    		신청자
     	</th>
    	<td width="20%" >
    		<c:out value="${annvrsryManageVO.usNm}"/>
    	</td>
    	<th width="20%" >
     		<span class="required_icon"></span>
    		소속
    	</th>
    	<td width="40%" >
    		<c:out value="${annvrsryManageVO.orgnztNm}"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		기념일구분
    	</th>
    	<td>
      		<form:select path="annvrsrySe" title="기념일구분">
	      		<form:options items="${COM069_annvrsrySe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
      	</td>
    	<th>
     		<span class="required_icon"></span>
    		기념일
    	</th>
    	<td>
      		<form:hidden path="annvrsryDe" />
	    	<c:if test="${!empty annvrsryManageVO.annvrsryDe}">
 				<c:set var="annvrsryDeVal" value="${fn:substring(annvrsryManageVO.annvrsryDe, 0,4)}-${fn:substring(annvrsryManageVO.annvrsryDe, 4,6)}-${fn:substring(annvrsryManageVO.annvrsryDe, 6,8)}"/>
      		</c:if>
      		<input name="annvrsryDeView" id="annvrsryDeView" type="text" size="10" title="기념일" value="${annvrsryDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].annvrsryDe, document.forms[0].annvrsryDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	     	양력 : <form:radiobutton path="cldrSe"  value="1" title="양력"/>&nbsp;
	     	음력 : <form:radiobutton path="cldrSe"  value="2" title="음력"/>
	     	<form:errors path="cldrSe" cssClass="error"/>
	     	&nbsp;&nbsp;매년반복 : <input type="checkbox" name="reptitSe" id="reptitSe" title="반복여부" value="1" <c:if test="${'1' eq annvrsryManageVO.reptitSe}">checked</c:if>>
	     </td>
  	</tr>
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label for="annvrsryNm">기념일명</label>
    	</th>
    	<td colspan="3">
            <form:input  path="annvrsryNm" size="80" maxlength="255" title="기념일명"/>
      		<form:errors path="annvrsryNm" cssClass="error"/>
      	</td>
  	</tr>
  	<tr> 
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="memo">메모</label>
    	</th>
    	<td colspan="3">
       		<TEXTAREA name="memo" id="memo" style="width:95%;height:100px;" title="메모"><c:out value="${annvrsryManageVO.memo      }"   escapeXml="false"/></TEXTAREA>
     	</td> 
  	</tr>
  	<tr> 
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="annvrsryBeginDe">알림시작일</label>
    	</th>
    	<td>
    		D-
        	<form:select path="annvrsryBeginDe" title="알림시작일">
	       		<form:option value="7" label="일주일"/>
	       		<form:option value="3" label="3일"/>
	       		<form:option value="2" label="2일"/>
	       		<form:option value="1" label="1일"/>
      		</form:select> 전 부터 알림
    	</td>
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="annvrsrySetup">알림설정</label>
    	</th>
    	<td>
    		<input name="annvrsrySetup" name="annvrsrySetup" type="radio" style="border:0px;" value="Y" title="알림설정" 
    			<c:if test='${annvrsryManageVO.annvrsrySetup == "Y"}'>checked</c:if>>ON 
    		<input name="annvrsrySetup" type="radio" style="border:0px;" value="N" 
    			<c:if test='${annvrsryManageVO.annvrsrySetup == "N"}'>checked</c:if>>OFF
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="annvrsryManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/*설명 : 기념일 목록조회*/
function fn_aram_list() {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsry.do";
    varForm.submit();       
}

/*설명 : 기념일 수정처리*/
function fn_aram_update() {
    var varForm = document.getElementById("annvrsryManageVO");
 
    if(!validateAnnvrsryManageVO(varForm)){           
        return;
    }

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/updateAnnvrsry.do";
        varForm.submit();
    }
}

</script>
