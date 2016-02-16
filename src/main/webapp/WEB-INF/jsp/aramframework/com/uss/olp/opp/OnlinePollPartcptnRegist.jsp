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
  * @Class Name : OnlinePollPartcptnRegist.jsp
  * @Description : 온라인POLL 참여 등록
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
	<h2>온라인POLL 참여 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!--  등록  폼 영역  -->
<table class="table-register">
  	<tr> 
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		POLL명
    	</th>
    	<td width="80%">
			<c:out value="${onlinePollPartcptnVO.pollNm}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		POLL시작일자
    	</th>
    	<td>
			<c:out value="${fn:substring(onlinePollPartcptnVO.pollBeginDe, 0, 4)}-${fn:substring(onlinePollPartcptnVO.pollBeginDe, 4, 6)}-${fn:substring(onlinePollPartcptnVO.pollBeginDe, 6, 8)}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		POLL종료일자
    	</th>
    	<td>
			<c:out value="${fn:substring(onlinePollPartcptnVO.pollEndDe, 0, 4)}-${fn:substring(onlinePollPartcptnVO.pollEndDe, 4, 6)}-${fn:substring(onlinePollPartcptnVO.pollEndDe, 6, 8)}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		<span class="norequired_icon"></span>
    		POLL종류
    	</th>
    	<td>
			<c:forEach items="${COM039_pollKind}" var="comCode" varStatus="pollKindStatus">
			<c:if test="${comCode.code eq onlinePollPartcptnVO.pollKindCode}">
				<c:out value="${comCode.codeNm}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr> 
</table>

<!--  줄간격조정  -->
<div style="margin-top:10px; width:100%"></div>

<form:form commandName="onlinePollPartcptnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pollId" />

<!--  등록폼  -->
<table class="table-register">
	<tr> 
 		<td style="background-color:#E3E3E3;">
 		<table border="0" >
 			<c:forEach items="${pollItemList}" var="result" varStatus="status">
 			<tr>
 				<td style="background-color:#E3E3E3;" width="10px" height="28px"></td>   				
 				<td style="background-color:#E3E3E3;" width="20px">
 					<input type="radio" name="pollIemId" value="${result.pollIemId}" style="border:0px;" <c:if test="${status.count == '1'}">checked</c:if>>
 				</td>
 				<td style="background-color:#E3E3E3;"><c:out value="${result.pollIemNm}" escapeXml="false" /></td>
 			</tr>	
			</c:forEach>
  		</table>
  		</td>	    		
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/opp/insertOnlinePollPartcptn.do";
		varForm.submit();
	}
}

</script>

