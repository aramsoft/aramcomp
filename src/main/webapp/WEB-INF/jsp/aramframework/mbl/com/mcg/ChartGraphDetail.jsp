<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : ChartGraphDetail.java
  * @Description : 차트/그래프 데이터 상세조회
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
	<h2>차트/그래프 데이터 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="chartGraphVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register"  summary="차트/그래프 데이터 정보테이블.">
	<tr> 
	    <th width="20%">범례명&nbsp;&nbsp;</th>
	    <td width="80%">
	        <c:out value="${chartGraphVO.lgdNm}"/>  
	    </td>
	</tr>
	<tr> 
	    <th>X축 값&nbsp;&nbsp;</th>
	    <td>
	    	<c:out value="${chartGraphVO.xaxis}"/>
	    </td>
	</tr>
	<tr> 
	    <th>Y축 값&nbsp;&nbsp;</th>
	    <td>
	    	<c:out value="${chartGraphVO.yaxis}"/>
	    </td>
	</tr>
	<tr> 
	    <th>생성일시&nbsp;&nbsp;</th>
	    <td>
	        <c:out value="${chartGraphVO.creatDt}"/>
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
	
</div>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("chartGraphVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mcg/listChartGraph.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("chartGraphVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mcg/editChartGraph.do";
    varForm.submit();   
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("chartGraphVO");
    
    if  (confirm("<spring:message code='common.delete.msg' />"))    {   
        varForm.action = "${pageContext.request.contextPath}/mbl/com/mcg/deleteChartGraph.do";
        varForm.submit();
    }    
}
</script>
