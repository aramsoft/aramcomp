<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : SchdulDetail.jsp
 * @Description : 일정 상세조회
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
	<h2>일정 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<!-- 2011.09.16 : 일지관리 버튼 존재 방법 변경  -->
		<c:if test="${useDiaryManage == 'true'}">
       		<span class="button"><a href="#" onclick="javascript:fn_aram_diary(); return false;">일지등록</a></span>
		</c:if>
		<!-- 2011.09.16 끝  -->
      	<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
       	<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
      	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>
	
<form:form modelAttribute="schdulManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="schdulId" />
<form:hidden path="schdulNm" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	일정구분
	    </th>
	    <td width="80%">
		    <c:forEach items="${COM030_schdulSe}" var="schdulSeInfo" varStatus="status">
		    <c:if test="${schdulSeInfo.code eq schdulManageVO.schdulSe}">
		     	<c:out value="${schdulSeInfo.codeNm }"  />
		    </c:if>
		    </c:forEach>
	    </td>
	</tr>    
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	중요도
	    </th>
	    <td>
		    <c:forEach items="${COM019_schdulIpcr}" var="schdulSeInfo" varStatus="status">
		    <c:if test="${schdulSeInfo.code eq schdulManageVO.schdulIpcrCode}">
		     	<c:out value="${schdulSeInfo.codeNm}"  />
		    </c:if>
		    </c:forEach>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	일정명
	    </th>
	    <td>
	      	<c:out value="${schdulManageVO.schdulNm}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	일정 내용
	    </th>
	    <td>
	    	<c:out value="${fn:replace(schdulManageVO.schdulCn , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.othbcScope" />
	    </th>
	    <td>
	     	<input type="radio" name="othbcScope" id="othbcScopeP" class="radio2" value="P"<c:if test="${schdulManageVO.othbcScope == 'P'}"> checked</c:if> disabled><label for="othbcScopeP">개인</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeC" class="radio2" value="C"<c:if test="${schdulManageVO.othbcScope == 'C'}"> checked</c:if> disabled><label for="othbcScopeC">커뮤니티</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeG" class="radio2" value="G"<c:if test="${schdulManageVO.othbcScope == 'G'}"> checked</c:if> disabled><label for="othbcScopeG">전체</label>
	     	<form:errors path="othbcScope" cssClass="error"/> 
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	반복구분
	    </th>
	    <td>
		    <c:forEach items="${COM031_reptitSe}" var="schdulSeInfo" varStatus="status">
		    <c:if test="${schdulSeInfo.code eq schdulManageVO.reptitSeCode}">
		     	<c:out value="${schdulSeInfo.codeNm}" />
		    </c:if>
		    </c:forEach>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	날짜/시간
	    </th>
	    <td>
		    ${fn:substring(schdulManageVO.schdulBgnde, 0, 4)}년 ${fn:substring(schdulManageVO.schdulBgnde, 4, 6)}월 ${fn:substring(schdulManageVO.schdulBgnde, 6, 8)}일 ${fn:substring(schdulManageVO.schdulBgnde, 8, 10)}시  ${fn:substring(schdulManageVO.schdulBgnde, 10, 12)}분 ~
		    ${fn:substring(schdulManageVO.schdulEndde, 0, 4)}년 ${fn:substring(schdulManageVO.schdulEndde, 4, 6)}월 ${fn:substring(schdulManageVO.schdulEndde, 6, 8)}일 ${fn:substring(schdulManageVO.schdulEndde, 8, 10)}시  ${fn:substring(schdulManageVO.schdulEndde, 10, 12)}분
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	담당자
	    </th>
	    <td>
	    	<c:out value="${schdulManageVO.schdulChargerName}"  />
	    </td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
	<tr>
		<th>
			<span class="norequired_icon"></span>
			파일첨부
		</th>
		<td>
			<c:import url="/content/files/${schdulManageVO.atchFileId}" />
		</td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 End.-->
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchSchdulSe" value="" />
<form:hidden path="year" value="" />
<form:hidden path="month" value="" />
<form:hidden path="week" value="" />
<form:hidden path="day" value="" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>


<script type="text/javascript">

/* ********************************************************
 * 일지등록화면 이동
 ******************************************************** */
function fn_aram_diary(){
    var varForm = document.getElementById("schdulManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/registDiary.do";;
    varForm.submit();
}

var gOpener = parent.document.all? parent.document.communityVO : parent.document.getElementById("communityVO") ;

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("schdulManageVO");
	if( gOpener != null) {
		varForm.trgetId.value = gOpener.cmmntyId.value;
	}
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("schdulManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/editSchdul.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("schdulManageVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/deleteSchdul.do";
		varForm.submit();
	}
}

</script>


