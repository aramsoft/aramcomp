<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : TroblReqstDetail.jsp
 * @Description : 장애신청 상세조회
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
	<h2>장애신청 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
     	<c:if test="${troblReqstVO.processSttus == 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_request(); return false;">요청</a></span>
        </c:if>
        <c:if test="${troblReqstVO.processSttus == 'R'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_cancel(); return false;">요청취소</a></span>
        </c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="troblReqstVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="troblId" />
<form:hidden path="processSttus" />

<table class="table-detail" summary="장애신청에 대한 상세정보를 제공한다.">
<caption>장애신청 상세조회</caption>
	<tr>
  		<th width="20%">
  			장애ID
  		</th>
  		<td width="80%">
  			<c:out value='${troblReqstVO.troblId}'/>
  		</td>
	</tr>
	<tr>
  		<th>
   			장애명
  		</th>
  		<td>
  			<c:out value='${troblReqstVO.troblNm}'/>
  		</td>
	</tr>
	<tr>
  		<th>
   			장애종류
  		</th>
  		<td>
  			<c:out value='${troblReqstVO.troblKndNm}'/>
  		</td>
	</tr>
	<tr>
  		<th>
   			장애설명
  		</th>
  		<td>
  			<textarea name="troblDc" rows="5" cols="80" title="장애설명" readOnly><c:out value='${troblReqstVO.troblDc}'/></textarea>
  		</td>
	</tr>
	<tr>
  		<th>
  			장애발생시간
  		</th>
  		<td>
  			<c:out value='${troblReqstVO.troblOccrrncTime}'/>
  		</td>
	</tr> 
   	<tr>
     	<th>
      		장애등록자
      	</th>
     	<td>
     		<c:out value='${troblReqstVO.troblRqesterNm}'/>
     	</td>
   	</tr> 	  
	<tr>
  		<th>
   			장애요청시간
  		</th>
  		<td>
  			<c:out value='${troblReqstVO.troblRequstTime}'/>
  		</td>
	</tr>    
	<tr>
  		<th>
   			처리상태
  		</th>
  		<td>
  			<c:out value='${troblReqstVO.processSttusNm}'/>
  		</td> 
  	</tr>
</table>
<br/>
<c:if test="${troblReqstVO.processSttus == 'C'}">
<table class="table-register">
  	<tr>
    	<th width="20%">
     		처리결과
    	</th>
    	<td width="80%">
    		<textarea name="troblProcessResult" rows="5" cols="80" title="처리결과" readOnly><c:out value='${troblReqstVO.troblProcessResult}'/></textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		처리시간
    	</th>
    	<td>
    		<c:out value='${troblReqstVO.troblProcessTime}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		처리자
    	</th>
    	<td>
    		<c:out value='${troblReqstVO.troblOpetrNm}'/>
    	</td>
  	</tr>
</table>
</c:if>

<!-- 검색조건 유지 -->
<form:hidden path="strProcessSttus" />
<form:hidden path="strTroblKnd" />
<form:hidden path="strTroblNm" />
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
    var varForm = document.getElementById("troblReqstVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/listTroblReqst.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("troblReqstVO");

    if(varForm.processSttus.value != 'A') {
        alert("수정할 수 없는 상태입니다");
        return;
    }

    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/editTroblReqst.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("troblReqstVO");

    if(varForm.processSttus.value != 'A') {
        alert("삭제할 수 없는 상태입니다");
        return;
    }
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/deleteTroblReqst.do";
        varForm.submit();
    }
}

function fn_aram_request() {
    var varForm = document.getElementById("troblReqstVO");

    if(varForm.processSttus.value != 'A') {
        alert("요청 대상이 아닙니다");
        return;
    }

    if(confirm("요청 하시겠습니까?")) {
        varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/requstTroblReqst.do";
        varForm.submit();
    }
}

function fn_aram_cancel() {
    var varForm = document.getElementById("troblReqstVO");

    if(varForm.processSttus.value != 'R') {
        alert("요청취소 대상이 아닙니다");
        return;
    }
    
    if(confirm("요청취소를 하시겠습니까?")) {
        varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/cancelTroblReqst.do";
        varForm.submit();
    }
}

</script>

