<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ServerEqpmnDetail.jsp
 * @Description : 서버H/W 상세조회
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
	<h2>서버H/W 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="serverEqpmnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="serverEqpmnId" />

<table class="table-detail" summary="서버H/W에 대한  상세정보를 제공한다.">
<caption>서버H/W 상세조회</caption>
	<tr>
  		<th width="20%">
  			서버H/W ID
  		</th>
  		<td width="80%">
  			<c:out value='${serverEqpmnVO.serverEqpmnId}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			서버H/W 명
  		</th>
  		<td>
  			<c:out value='${serverEqpmnVO.serverEqpmnNm}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			서버H/W IP
  		</th>
  		<td>
  			<c:out value='${serverEqpmnVO.serverEqpmnIp}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			관리자
  		</th>
  		<td>
  			<c:out value='${serverEqpmnVO.serverEqpmnMngrNm}'/>
  		</td>
	</tr>
   	<tr>
     	<th>
      		관리자이메일주소
     	</th>
     	<td>
     		<c:out value='${serverEqpmnVO.mngrEmailAddr}'/>
     	</td>
   	</tr>
	<tr>
  		<th>
  			OS정보
  		</th>
  		<td>
  			<c:out value='${serverEqpmnVO.opersysmInfo}'/>
  		</td>
	</tr> 
   	<tr>
     	<th>
     		CPU정보
     	</th>
     	<td>
     		<c:out value='${serverEqpmnVO.cpuInfo}'/>
     	</td>
   	</tr>
	<tr>
  		<th>
  			메모리정보
  		</th>
  		<td>
  			<c:out value='${serverEqpmnVO.moryInfo}'/>
  		</td>
	</tr>    
   	<tr>
    	<th>
    		하드디스크
    	</th>
     	<td>
     		<c:out value='${serverEqpmnVO.hdDisk}'/>
     	</td>
  	</tr>
  	<tr>
    	<th>
	   		기타정보
    	</th>
    	<td>
    		<c:out value='${serverEqpmnVO.etcInfo}'/>
    	</td>
   	</tr>      
	<tr>
  		<th>
  			등록일자
  		</th>
  		<td>
	    	<c:out value="${fn:substring(serverEqpmnVO.regstYmd, 0, 4)}-${fn:substring(serverEqpmnVO.regstYmd, 4, 6)}-${fn:substring(serverEqpmnVO.regstYmd, 6, 8)}"/>
  		</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="strServerEqpmnNm" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>
 
</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("serverEqpmnVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServerEqpmn.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("serverEqpmnVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/editServerEqpmn.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("serverEqpmnVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/deleteServerEqpmn.do";
        varForm.submit();
    }
}

</script>
