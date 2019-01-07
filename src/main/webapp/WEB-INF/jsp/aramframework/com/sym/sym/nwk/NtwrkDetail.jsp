<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NtwrkDetail.jsp
 * @Description : 네트워크 상세조회
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
	<h2>네트워크 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="ntwrkVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="ntwrkId" />

<table class="table-detail" summary="네트워크 상세정보를 제공한다.">
<caption>네트워크 상세조회</caption>
	<tr>
  		<th width="20%">
  			네트워크ID
  		</th>
  		<td width="80%">
  			<c:out value='${ntwrkVO.ntwrkId}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			IP
  		</th>
  		<td>
  			<c:out value='${ntwrkVO.ntwrkIp}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			게이트웨이
  		</th>
  		<td>
  			<c:out value='${ntwrkVO.gtwy}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			서브넷
  		</th>
  		<td>
  			<c:out value='${ntwrkVO.subnet}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			DNS
  		</th>
  		<td>
  			<c:out value='${ntwrkVO.domnServer}'/>
  		</td>
	</tr> 
   	<tr>
     	<th>
      		관리항목
     	</th>
     	<td>
     		<c:out value='${ntwrkVO.manageIem}'/>
     	</td>
   	</tr> 	  
	<tr>
  		<th>
   			사용자명
  		</th>
  		<td>
  			<c:out value='${ntwrkVO.userNm}'/>
  		</td>
	</tr>    
	<tr>
  		<th>
   			사용여부
   		</th>
  		<td>
  			<c:out value='${ntwrkVO.useAt}'/>
  		</td> 
	</tr>  
	<tr>
  		<th>
  			등록일자
  		</th>
  		<td>
	    	<c:out value="${fn:substring(ntwrkVO.regstYmd, 0, 4)}-${fn:substring(ntwrkVO.regstYmd, 4, 6)}-${fn:substring(ntwrkVO.regstYmd, 6, 8)}"/>
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
function fn_aram_list() {
    var varForm = document.getElementById("ntwrkVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/listNtwrk.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("ntwrkVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/editNtwrk.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("ntwrkVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/nwk/deleteNtwrk.do";
        varForm.submit();
    }
}

</script>
