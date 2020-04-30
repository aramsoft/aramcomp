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
  * @Class Name : ProgramChangProcessEdit.jsp
  * @Description : 프로그램변경요청처리 수정
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
	<h2>프로그램변경요청처리 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="progrmManageDtlVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>변경처리내역</h2>
</div>

<table class="table-register" summary="변경처리내역 ">
<caption>변경처리내역</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="processDe">변경처리일자</label>
    	</th>
    	<td width="80%">
      		<form:hidden path="processDe" />
	    	<c:if test="${!empty progrmManageDtlVO.processDe}">
 				<c:set var="processDeVal" value="${fn:substring(progrmManageDtlVO.processDe, 0,4)}-${fn:substring(progrmManageDtlVO.processDe, 4,6)}-${fn:substring(progrmManageDtlVO.processDe, 6,8)}"/>
      		</c:if>
      		<input name="processDeView" id="processDeView" type="text" size="10" title="변경처리일자" value="${processDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].processDe, document.forms[0].processDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
  	  		<form:errors path="processDe" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="opetrId">변경처리자</label>
    	</th>
    	<td>
      		<form:input path="opetrId" size="20" maxlength="50" title="변경처리자" readonly="true"/>
			<form:errors path="opetrId" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="processSttus">변경처리상태</label>
    	</th>
    	<td>
        	<form:select path="processSttus" title="변경처리상태">
            	<form:option value=""  label="N/A"/>
            	<form:option value="A" label="신청중"/>
            	<form:option value="P" label="진행중"/>
            	<form:option value="R" label="반려 "/>
            	<form:option value="C" label="처리완료"/>
        	</form:select>
        	<form:errors path="processSttus" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="rqestProcessCn">변경처리내용</label>
    	</th>
    	<td>
      		<form:textarea path="rqestProcessCn" rows="5" cols="75" cssClass="txArea" title="변경처리내용"/>
      		<form:errors path="rqestProcessCn" cssClass="error"/>
    	</td>
  	</tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>변경요청내역</h2>
</div>

<table class="table-register" summary="변경요청내역 ">
<caption>변경요청내역</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		요청번호
    	</th>
    	<td width="80%">
      		<c:out value="${progrmManageDtlVO.rqestNo}"/>
			<form:hidden path="rqestNo" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		프로그램파일명
    	</th>
    	<td>
      		<c:out value="${progrmManageDtlVO.progrmFileNm}"/>
			<form:hidden path="progrmFileNm" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		요청자ID
    	</th>
    	<td>
      		<c:out value="${progrmManageDtlVO.rqestPersonId}"/>
			<form:hidden path="rqestPersonId" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		요청일자
    	</th>
    	<td>
      		<c:out value="${progrmManageDtlVO.rqestDe}"/>
			<form:hidden path="rqestDe" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		요청제목
    	</th>
    	<td>
      		<c:out value="${progrmManageDtlVO.rqestSj}" />
			<form:hidden path="rqestSj" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		변경요청내용
    	</th>
    	<td>
      		<form:textarea path="changeRqestCn" rows="5" cols="75" readonly="true" cssClass="txArea" title="변경요청내용"/>
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

</div>

<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageDtlVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록조회 처리 함수
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeProcess.do";
    varForm.submit();
}

/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_aram_update() {
    var varForm = document.getElementById("progrmManageDtlVO");
    
	if(!validateProgrmManageDtlVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action ="${pageContext.request.contextPath}/sym/prm/updateProgramChangeProcess.do";
		varForm.submit();
	}
}

</script>
