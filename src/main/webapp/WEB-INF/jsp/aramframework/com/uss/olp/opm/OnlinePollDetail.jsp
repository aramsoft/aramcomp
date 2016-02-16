<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollDetail.jsp
  * @Description : 온라인POLL 상세조회
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
	<h2>온라인POLL 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="onlinePollManageVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pollId" />

<!--  상세보기  폼 영역  -->
<table class="table-detail">
	<tr>
    	<th width="20%">
    		POLL명
    	</th>
    	<td width="80%">
 			<c:out value="${onlinePollManageVO.pollNm}" />
    	</td>
  	</tr>
	<tr>
		<th>
			POLL시작일자
		</th>
	    <td>
			<c:out value="${fn:substring(onlinePollManageVO.pollBeginDe, 0, 4)}-${fn:substring(onlinePollManageVO.pollBeginDe, 4, 6)}-${fn:substring(onlinePollManageVO.pollBeginDe, 6, 8)}"/>
	    </td>
  	</tr>
	<tr>
		<th>
			POLL종료일자
		</th>
    	<td>
			<c:out value="${fn:substring(onlinePollManageVO.pollEndDe, 0, 4)}-${fn:substring(onlinePollManageVO.pollEndDe, 4, 6)}-${fn:substring(onlinePollManageVO.pollEndDe, 6, 8)}"/>
    	</td>
	</tr>
	<tr>
		<th>
			POLL종류
		</th>
    	<td>
 			<c:forEach items="${COM039_pollKind}" var="comCode" varStatus="pollKindStatus">
				<c:if test="${comCode.code eq onlinePollManageVO.pollKindCode}">
					<c:out value="${comCode.codeNm}" />
				</c:if>
			</c:forEach>
    	</td>
  	</tr>
	<tr>
		<th>
			POLL페기유무
		</th>
    	<td>
			<c:out value="${onlinePollManageVO.pollDsuseYn}"/>
    	</td>
	</tr>
	<tr>
		<th>
			POLL자동페기유무
		</th>
    	<td>
    		<c:out value="${onlinePollManageVO.pollAutoDsuseYn}"/>
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

<!--  줄간격조정  -->
<div style="margin-top:10px; width:100%"></div>

<!-- 온라인POLL항목 iframe  -->
<iframe id="PollIemView" onload="javascript:changeFrameSize(); return false;" src="${pageContext.request.contextPath}/uss/olp/opm/listOnlinePollItem.do?pollId=${onlinePollManageVO.pollId}" seamless="seamless" width="100%" height="0" title="컨텐츠영역"></iframe>

</DIV>

<script type="text/javascript">

function changeFrameSize(){
	var childWindow = document.getElementById('PollIemView').contentWindow; 
	var the_height = childWindow.document.body.scrollHeight;
	document.getElementById('PollIemView').height = the_height + 20;
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/listOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/editOnlinePoll.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("onlinePollManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/deleteOnlinePoll.do";
		varForm.submit();
	}
}

</script>


