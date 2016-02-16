<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QnaList.jsp
  * @Description : Q&amp;A 목록
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
	<h2>Q&amp;A 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="qnaManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="qaId" type="hidden" value="">

<!--실명확인을 위한  설정   Start...-->
<input type="hidden" name="ihidnum" value="">
<input type="hidden" id="realname" name="realname" value="">

<input type="hidden" name ="nextUrlName" value="QA등록">
<input type="hidden" name ="nextUrl" value="${pageContext.request.contextPath}/uss/olh/qna/registQna.do">

<input type="hidden" name="certificationAt" value="${certificationAt}">

<input type="hidden" name="wrterNm" value=""> 
<!--실명확인을 위한  설정 End......-->

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
   		<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="WRTER_NM" label="작성자명" />			   
	   		<form:option value="QESTN_SJ" label="질문제목" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="Q&amp;A에 대한 목록을 제공합니다.">
<caption>Q&amp;A목록</caption>
<thead>
	<tr>      
   	 	<th scope="col" width="7%" >No.</th>        
    	<th scope="col"            >질문제목</th>    
    	<th scope="col" width="10%">작성자</th>        
    	<th scope="col" width="10%">진행상태</th>               
    	<th scope="col" width="10%">조회수</th>        
    	<th scope="col" width="15%">작성일자</th>                   
	</tr>
</thead>
<tbody>      
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr> 
  		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
  	</tr>   	          				 			   
 	</c:if>
 	
 	<c:set var="startIndex" value="${(qnaManageVO.pageIndex-1) * qnaManageVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.qaId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${qnaManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3L"><c:out value="${result.qestnSj}"/></td>
		<td class="lt_text3"><c:out value="${result.wrterNm}"/></td>
		<td class="lt_text3"><c:out value="${result.qnaProcessSttusCodeNm}"/></td>
		<td class="lt_text3"><c:out value="${result.inqireCo}"/></td>				
		<td class="lt_text3"><c:out value='${fn:substring(result.writngDe, 0,4)}'/>-<c:out value='${fn:substring(result.writngDe, 4,6)}'/>-<c:out value='${fn:substring(result.writngDe, 6,8)}'/></td>		
  	</tr>   
	</c:forEach>
</tbody>  
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("qnaManageVO");
    varForm.searchKeyword.focus();
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("qnaManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.do";
    varForm.submit();
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("qnaManageVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(qaId) {		
    var varForm = document.getElementById("qnaManageVO");
    varForm.qaId.value = qaId;	
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/detailQna.do";
  	varForm.submit();	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("qnaManageVO");

	// 로그인을 할 것인자? 실명확인을 할것인지? 판단 화면
	var loginRealnm_url 	= "${pageContext.request.contextPath}/uss/olh/qna/LoginRealnmChoice.do";	
	var	loginRealnm_status 	= "dialogWidth=340px;dialogHeight=150px;resizable=no;center=yes;scroll=no;";

	// 로그인 화면
	var	login_url 		= "${pageContext.request.contextPath}/uat/uia/loginUsr.do"; 		
	var login_status 	= "dialogWidth=700px;dialogHeight=420px;resizable=no;center=yes;scroll=no;";

	// 실명확인 화면
	var	realnm_url 	    = "${pageContext.request.contextPath}/sec/rnc/confirmRlnm.do?nextUrlName=button.qnaregist&nextUrl=C&ihidnum='/>"; 		
	var realnm_status 	= "dialogWidth=740px;dialogHeight=420px;resizable=no;center=yes;scroll=no;";
 
	var	isNextOK = false;
	var certificationAt = varForm.certificationAt.value;
	
	certificationAt = "Y"; // 실명인증로직 통과
	
	// 인증여부 확인
	if (certificationAt == "N") {

		// 로그인? 실명확인 여부 화면 호출
		var returnValue = window.showModalDialog(loginRealnm_url, self, loginRealnm_status);
		
 		// 로그인처리
 		if (returnValue == "L")		{

			// 로그인 화면 호출
		    /* 추후 진행 예정..
 			returnValue = window.showModalDialog(login_url, self, login_status);
 			returnValue = true;
			*/

			// 팝업이 아닌 메인 화면으로 처리.
 			varForm.action = "${pageContext.request.contextPath}/uat/uia/loginUsr.do";
 			varForm.submit();

 			isNextOK = false;
 			
 		// 실명확인처리
 		} else if (returnValue == "R")	{
		 		 			
			// 실명확인 화면 호출
 			isNextOK = window.showModalDialog(realnm_url, self, realnm_status);
 			varForm.wrterNm.value = varForm.realname.value;

 		}  // 로그인처리 혹은 실명확인 경우 end...

		if	(isNextOK)	{
		// QNA등록화면 호출..
			fn_aram_regist_next();
		}
		
	} else	{
		// QNA등록화면 호출..
		fn_aram_regist_next();
	}
}

/*********************************************************
 * Q&A 등록화면 호출
 ******************************************************** */
function fn_aram_regist_next(){
    var varForm = document.getElementById("qnaManageVO");
    varForm.qaId.value = "";	
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/registQna.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("q_a관리");	
	window.open(url, "도움말");
}

</script>

