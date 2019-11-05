<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : IndndlpgeConfRegist.jsp
 * @Description : 마이페이지 구성 등록
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
	<h2>마이페이지 구성 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
	</div>
</div>

<form:form commandName="indvdlPgeConfVO" name="indvdlPgeConfVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="userId" />

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="sortCnt">배열방식</label>
    	</th>
    	<td>
			<c:set var="checked" value="${indvdlPgeConfVO.sortMthd}" />
      		<input name="sortMthd" type="radio" value="R" tabindex="1" <c:if test="${checked eq 'R'}"><c:out value="checked"/></c:if>> 가로기준 배열
      		<input name="sortMthd" type="radio" value="C" tabindex="2" <c:if test="${checked eq 'C'}"><c:out value="checked"/></c:if>> 세로기준 배열 : 개수
      		<input name="sortCnt" id="sortCnt" type="text" size="5" tabindex="3" value="<c:out value="${indvdlPgeConfVO.sortCnt}"/>">
      		<form:errors path="sortMthd" cssClass="error"/> 
      		<form:errors path="sortCnt" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="titleBarColor">타이틀 바 색상</label>
    	</th>
    	<td>
    		색상코드
        	<input name="titleBarColor" id="titleBarColor" type="text" maxLength="7" tabindex="4" value="<c:out value="${indvdlPgeConfVO.titleBarColor}"/>">
        	<span style="cursor:pointer;" onclick="showColorTable(); return false;"><img src="${pageContext.request.contextPath}/images/aramframework/com/uss/mpe/icon/cIcon.gif" alt="색상표아이콘" width="16" height="16" border="2" style="vertical-align: middle"></span>
        	<form:errors path="titleBarColor" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${indvdlPgeConfVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${indvdlPgeConfVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${indvdlPgeConfVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${indvdlPgeConfVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="indvdlPgeConfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 마이페이지 설정 정보 저장
 ******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("indvdlPgeConfVO");
	
    if(!validateIndvdlPgeConfVO(varForm)){           
        return;
    }

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/mpe/updateIndvdlpgeConf.do";
        varForm.submit();
    }
}

/* ********************************************************
* 컬러 chooser창 띄우기
******************************************************** */
function showColorTable(){
	var url = "${pageContext.request.contextPath}/html/mpe/EgovIndvdlpgeConfColorChooser.html"
	window.open(url, "p_color", "width=230px,height=180px,top=100px,left=100px,location=no");
}

</script>

