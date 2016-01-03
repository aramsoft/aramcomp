<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ProgramList.jsp
  * @Description : 프로그램 목록 
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
	<h2>프로그램 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="progrmManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="progrmFileNm"/>
<input type="hidden" name="checkedProgrmFileNmForDel"  />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
  		<label for="searchKeyword">프로그램명</label>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<table class="table-list" summary="프로그램목록관리 목록으로 프로그램파일명, 프로그램명, url,프로그램설명 으로 구성">
<caption>프로그램목록관리 목록</caption>
<thead>
  	<tr>
     	<th scope="col" width="5%" >No.</th>
    	<th scope="col" width="7%" >
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택">
    	</th>
    	<th scope="col" width="20%">프로그램파일명</th>
    	<th scope="col" width="20%">프로그램명</th>
    	<th scope="col"            >URL</th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 	<c:if test="${fn:length(resultList) == 0}">
 	<tr>
 		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
 	</tr>
 	</c:if>
 	
 	<c:set var="startIndex" value="${(progrmManageVO.pageIndex-1) * progrmManageVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr >
  	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${progrmManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
       		<input type="checkbox" name="checkField" class="check2" title="선택">
       		<input type="hidden" name="checkProgrmFileNm" value="<c:out value='${result.progrmFileNm}'/>" disabled />
    	</td>
    	<td class="lt_text">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.progrmFileNm}"/>'); return false;">
		    	<c:out value="${result.progrmFileNm}"/>
            </a>
            </span>
    	</td>
    	<td class="lt_text"><c:out value="${result.progrmKoreanNm}"/></td>
    	<td class="lt_text"><c:out value="${result.url}"/></td>
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

function press() {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
	var varForm = document.getElementById("progrmManageVO");
	varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgram.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
	var varForm = document.getElementById("progrmManageVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgram.do";
    varForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function fn_aram_detail(progrmFileNm) {
    var varForm = document.getElementById("progrmManageVO");
    varForm.progrmFileNm.value = progrmFileNm;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/editProgram.do";
    varForm.submit();
}

/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function fn_aram_regist() {
    var varForm = document.getElementById("progrmManageVO");
    varForm.progrmFileNm.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/prm/registProgram.do";
    varForm.submit();
}

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fn_aram_deleteList() {
    var varForm = document.getElementById("progrmManageVO");
    var checkField = varForm.checkField;
    var ProgrmFileNm = varForm.checkProgrmFileNm;
    var checkProgrmFileNms = "";
    var checkedCount = 0; 

    if(checkField) {
    	if(checkField.length> 1) {
            for(var i=0; i < checkField.length; i++) {
            	if(checkField[i].checked) {
                    checkProgrmFileNms += ((checkedCount==0? "" : ",") + ProgrmFileNm[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
            	checkProgrmFileNms = ProgrmFileNm.value;
            }
        }
    }

    if(checkProgrmFileNms.length == 0){
		alert("선택된 프로그램이 없습니다.");
		return false;
    }

    varForm.checkedProgrmFileNmForDel.value=checkProgrmFileNms;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/deleteListProgram.do";
    varForm.submit();
}

/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fnCheckAll() {
    var varForm = document.getElementById("progrmManageVO");
    var checkField = varForm.checkField;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = varForm.checkAll.checked;
        }
    } else {
        checkField.checked = varForm.checkAll.checked;
    }
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("프로그램관리");	
	window.open(url, "도움말");
}

</script>
