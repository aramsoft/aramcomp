<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MailList.jsp
  * @Description : 발송메일 목록
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
	<h2>발송메일 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="sndngMailVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="mssageId" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" class="select" tabindex="1" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
	   		<form:option value="SJ" label="제목" />
	   		<form:option value="EMAIL_CN" label="내용" />
	   		<form:option value="SNDR" label="보낸이" />
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" tabindex="2" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<table class="table-list" id="tblData" summary="상태, 발신자, 수신자, 제목, 날짜의 정보를 가진 발송메일내역을 조회한다.">
<CAPTION style="display: none;">발송메일 내역 조회</CAPTION>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
		<th scope="col" width="5%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
		</th>
		<th scope="col" width="10%">상태</th>
		<th scope="col" width="10%">발신자</th>
		<th scope="col" width="20%">수신자</th>
		<th scope="col"            >제목</th>
		<th scope="col" width="20%">날짜</th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(sndngMailVO.pageIndex-1) * sndngMailVO.recordPerPage}"/>
  	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('${result.mssageId}'); return false;">
 	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${sndngMailVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.mssageId}" />
        </td>
 
    	<td class="lt_text3">${result.sndngResultCode}</td>
    	<td class="lt_text3">${result.dsptchPerson}</td>
    	<td class="lt_text6">${result.recptnPerson}</td>
    	<td class="lt_text6"><c:out value="${result.sj}"/></td>
     	<td class="lt_text3">${result.dsptchDt}</td>
  	</tr>
  	</c:forEach>
</tbody>
</table>

<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage"/>
</div>

</DIV>

<script type="text/javascript">

$(function() {
	$("#checkAll").on("click", function(){
		if( $(this).is(":checked") ){
			$("#tblData input[name=uniqIds]").prop("checked", true);
		}else{
			$("#tblData input[name=uniqIds]").prop("checked", false); 
		}
	});
});

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("sndngMailVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/ems/listSndngMail.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("sndngMailVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/ems/listSndngMail.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(mssageId){
    var varForm = document.getElementById("sndngMailVO");
    varForm.mssageId.value = mssageId;
    varForm.action = "${pageContext.request.contextPath}/cop/ems/detailSndngMail.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("sndngMailVO");
    varForm.mssageId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/ems/registSndngMail.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_deleteList(){
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
	var varForm = document.getElementById("sndngMailVO");
    
   	var ret = confirm("삭제하시겠습니까?");
    if (ret == true) {
   		varForm.messageIds.value=checkedIds;
    	varForm.action = "${pageContext.request.contextPath}/cop/ems/deleteSndngMailList.do";
    	varForm.submit();
    }
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("메일솔루션_연동_인터페이스");	
	window.open(url, "도움말");
}

</script>

