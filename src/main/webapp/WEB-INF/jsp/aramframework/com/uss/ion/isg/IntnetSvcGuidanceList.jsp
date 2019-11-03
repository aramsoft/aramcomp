<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : IntnetSvcGuidanceList.jsp
 * @Description : 인터넷서비스안내 목록
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
	<h2>인터넷서비스안내 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="intnetSvcGuidanceVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="intnetSvcId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_view(); return false;"><spring:message code="button.confirm" /></a></span>
	</div>
	<div class="keyword_area">
 		<label for="searchKeyword">인터넷서비스 명 : </label>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search(); return false;" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="인터넷서비스안내에 대한 목록을 제공한다.">
<caption>인터넷서비스안내 관리</caption>
<thead>
  	<tr>
     	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="5%" >
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택" />
    	</th>
	    <th scope="col" width="20%">인터넷서비스 ID</th>
	    <th scope="col"            >인터넷서비스 명</th>
	    <th scope="col" width="10%">반영여부</th>
	    <th scope="col" width="20%">등록일시</th>
  	</tr>
</thead>
<tbody>
 	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 	<c:if test="${fn:length(resultList) == 0}">
 	<tr>
 		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
 	</tr>
 	</c:if>
 	
 	<c:set var="searchVO" value="${intnetSvcGuidanceVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
 	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
    		<input type="checkbox" name="delYn" class="check2" title="선택">
    		<input type="hidden" name="checkId" value="<c:out value="${result.intnetSvcId}"/>" disabled />
    	</td>
    	<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.intnetSvcId}"/>'); return false;">
				<c:out value="${result.intnetSvcId}"/>
			</a>
			</span>
     	</td>
    	<td class="lt_text3"><c:out value="${result.intnetSvcNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.reflctAt}"/></td>
    	<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image"  jsFunction="fn_aram_linkPage"  />
</div>

</DIV>

<script type="text/javascript">

function press() {

    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/isg/listIntnetSvcGuidance.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/isg/listIntnetSvcGuidance.do";
    varForm.submit();
}

function fn_aram_detail(intnetSvcId) {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    varForm.intnetSvcId.value = intnetSvcId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/isg/editIntnetSvcGuidance.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    varForm.intnetSvcId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/isg/registIntnetSvcGuidance.do";
    varForm.submit();
}

function fn_aram_view() {

	var url = "${pageContext.request.contextPath}/uss/ion/isg/viewIntnetSvcGuidance.do";
    var openParam = "dialogWidth:800px;dialogHeight:600px;scroll:yes;status:no;center:yes;resizable:yes;";
    window.open(url,"인터넷서비스안내",'width=800,height=600,scrollbars=yes,resizable=no,status=no,center:yes');
}

function fnCheckAll() {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    var checkField = varForm.delYn;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = varForm.checkAll.checked;
        }
    } else {
        checkField.checked = varForm.checkAll.checked;
    }
}

function fncManageChecked() {
    var varForm = document.getElementById("intnetSvcGuidanceVO");
    var checkField = varForm.delYn;
    var checkId = varForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkedCount = 0;

    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	returnValue += ((checkedCount==0? "" : ";") + checkId[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                returnValue = checkId.value;
            }
        }
    } 

    if(returnValue.length> 0) {
        varForm.intnetSvcIds.value = returnValue;
        returnBoolean = true;
    } else {
        alert("선택된 인터넷서비스 ID가 없습니다.");
        returnBoolean = false;
    }

    return returnBoolean;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("인터넷서비스안내및관리");	
	window.open(url, "도움말");
}

</script>
