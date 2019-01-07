<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : MapTeamDetail.jsp
  * @Description : 지식맵(조직별) 상세조회
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
	<h2>지식맵(조직별) 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mapTeamVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="orgnztId" />

<table class="table-detail" summary="이 표는 지식맵(조직별) 정보를 제공하며, 조직ID, 조직명, 지식URL, 분류일자 정보로 구성되어 있습니다 .">
<caption>지식맵(조직별) 상세조회</caption>
	<tr> 
  		<th width="20%">
  			조직ID
  		</th>
  		<td>
  			${mapTeamVO.orgnztId}
  		</td>
	</tr>
	<tr>
  		<th>
  			조직명
  		</th>          
  		<td>
  			${mapTeamVO.orgnztNm}
  		</td>    
	</tr>
	<tr>
		<th>
			지식URL
		</th>          
	    <td>
	    	${mapTeamVO.knoUrl}
	    </td>    
	</tr>   
	<tr>
		<th>
			분류일자
		</th>          
	    <td>
	    	<c:out value="${fn:substring(mapTeamVO.clYmd, 0, 4)}-${fn:substring(mapTeamVO.clYmd, 4, 6)}-${fn:substring(mapTeamVO.clYmd, 6, 8)}"/>
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

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("mapTeamVO");
    varForm.action = "${pageContext.request.contextPath}/dam/map/tea/listMapTeam.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("mapTeamVO");
	varForm.action = "${pageContext.request.contextPath}/dam/map/tea/editMapTeam.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("mapTeamVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/map/tea/deleteMapTeam.do";
		varForm.submit();
	}
}

</script>
