<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MeetingDetail.jsp
  * @Description : 회의록 상세조회
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
	<h2>회의록 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="meetingManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mtgId" />

<!-- 록  폼 영역  -->
<table class="table-detail" summary="상세조회 목록을 제공한다.">
<caption>상세조회 목록을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		회의제목
    	</th>
    	<td width="80%">
     	  	${meetingManageVO.mtgNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		회의 안건 내용
    	</th>
    	<td>
     		${meetingManageVO.mtgMtrCn}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		회의순서
    	</th>
    	<td>
     		${meetingManageVO.mtgSn}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		회의회차
    	</th>
    	<td>
     		${meetingManageVO.mtgCo}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		회의일자
    	</th>
    	<td>
        	<c:out value='${fn:substring(meetingManageVO.mtgDe, 0,4)}-${fn:substring(meetingManageVO.mtgDe, 4,6)}-${fn:substring(meetingManageVO.mtgDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		회의장소
    	</th>
    	<td>
     		${meetingManageVO.mtgPlace}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		회의시작시간
    	</th>
    	<td>
			<c:forTokens var="one"
		            items="${meetingManageVO.mtgBeginTime}"
		            delims=":" varStatus="sts">
  				<c:if test="${sts.count == 1}">${one}시</c:if>
		        <c:if test="${sts.count == 2}">${one}분</c:if>
			</c:forTokens>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		회의종료시간
    	</th>
    	<td>
			<c:forTokens var="one"
		            items="${meetingManageVO.mtgEndTime}"
		            delims=":" varStatus="sts">
  				<c:if test="${sts.count == 1}">${one}시</c:if>
		        <c:if test="${sts.count == 2}">${one}분</c:if>
			</c:forTokens>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		비공개 회의여부
    	</th>
    	<td>
    		<c:if test="${meetingManageVO.clsdrMtgAt eq '1'}">예</c:if>
			<c:if test="${meetingManageVO.clsdrMtgAt eq ''}">아니오</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		열람 개시일
    	</th>
    	<td>
        	<c:out value='${fn:substring(meetingManageVO.readngBeginDe, 0,4)}-${fn:substring(meetingManageVO.readngBeginDe, 4,6)}-${fn:substring(meetingManageVO.readngBeginDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		열람 여부
    	</th>
    	<td>
    		${meetingManageVO.readngAt}
    	</td>
  	</tr>
  	<tr>
    	<th>
      		회의결과 내용
    	</th>
    	<td>
    		${meetingManageVO.mtgResultCn}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		회의결과 여부
    	</th>
    	<td>
    		<c:if test="${meetingManageVO.mtgResultEnnc eq '1'}">예</c:if>
			<c:if test="${meetingManageVO.mtgResultEnnc eq ''}">아니오</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		기타 사항
    	</th>
    	<td>
    		${meetingManageVO.etcMatter}
    	</td>
  	</tr>
  	<tr>
    	<th>
       		주관부서
      	</th>
    	<td>
    		${meetingManageVO.mngtDeptNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
   			주관자ID
  		</th>
    	<td>
    		${meetingManageVO.mnaerIds}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		주관자명
    	</th>
    	<td>
    		${meetingManageVO.mnaerNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		주관자부서
    	</th>
    	<td>
     		${meetingManageVO.mnaerDeptNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		회의여부
    	</th>
   	 	<td>
    		${meetingManageVO.mtnAt}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		불참석자수
    	</th>
    	<td>
    		${meetingManageVO.nonatdrnCo}
    	</td>
  	</tr>
  	<tr>
    	<th>
       		참석자수
      	</th>
    	<td>
    		${meetingManageVO.atdrnCo}
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
    var varForm = document.getElementById("meetingManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/mgt/listMeeting.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
	var varForm = document.getElementById("meetingManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/olp/mgt/editMeeting.do";;
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_aram_delete(){
	var varForm = document.getElementById("meetingManageVO");
	
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/mgt/deleteMeeting.do";
		varForm.submit();
	}
}

</script>

