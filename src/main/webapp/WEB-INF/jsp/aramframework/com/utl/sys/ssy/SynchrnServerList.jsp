<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : SynchrnServerList.jsp
  * @Description : 동기화 서버 목록
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
	<h2>동기화 서버 목록</h2>
</div>

<form:form commandName="synchrnServerVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="serverId"/>
<input type="hidden" name="totalCount" value="${synchrnServerVO.totalRecordCount}" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_process(); return false;">동기화</a></span>
	</div>
	<div class="keyword_area">
  		<label for="strSynchrnServerNm">서버 명 : </label>
  		<form:input path="strSynchrnServerNm" size="30" title="검색" onkeypress="press();" />
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="동기화대상 서버에 대한 목록을 제공한다.">
<caption>동기화대상 서버 목록</caption>
<thead>
  	<tr>
  		<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">서버ID</th>
	    <th scope="col"            >서버명</th>
	    <th scope="col" width="20%">서버IP</th>
	    <th scope="col" width="15%">동기화 여부</th>
	    <th scope="col" width="15%">등록자</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
    <c:set var="searchVO" value="${synchrnServerVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.serverId}"/>'); return false;">
 	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3"><c:out value="${result.serverId}"/></td>
	    <td class="lt_text3"><c:out value="${result.serverNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.serverIp}"/></td>
	    <td class="lt_text3"><c:out value="${result.reflctAt}"/></td>
	    <td class="lt_text3"><c:out value="${result.lastUpdusrId}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

<br>

<div class="content_title">
	<h2>동기화대상 파일 등록</h2>
</div>

<div style="width:325px;margin-bottom:20px;">

    <!-- 동기화대상파일 등록 -->
    <form name="fileForm" action="${pageContext.request.contextPath}/utl/sys/ssy/uploadFile.do" method="post" enctype="multipart/form-data">
    <table class="table-list">
        <tr>
            <th width="20%">파일 선택 : </th>
            <td class="lt_text"><input name="file" type="file" size="30" title="동기화대상파일"></td>
            <td><span class="button"><input type="submit" value="<spring:message code="button.create" />" onclick="fncUploadFile(); return false;"></span></td>
       	</tr>
    </table>
    </form>
        
</div>

<div class="content_title">
	<h2>동기화대상 파일 목록</h2>
</div>

<div style="width:325px;margin-bottom:20px;">

 	<!-- 동기화대상파일 삭제-->   
    <form name="deleteForm" action="${pageContext.request.contextPath}/utl/sys/ssy/deleteFile.do" method="post">
	<div id="search_area">
		<div class="button_area">
			<span class="button"><a href="#" onclick="javascript:fncFileListDelete(); return false;"><spring:message code="button.delete" /></a></span>
		</div>
		<div class="keyword_area">
 			<label for="strSynchrnServerNm">서버 명 : </label>
 			<input type="text" name="strSynchrnServerNm" id="strSynchrnServerNm" value="${synchrnServerVO.strSynchrnServerNm}" size="30" title="검색" onkeypress="press();" />
		</div>
	</div>

    <table class="table-list" summary="동기화대상 파일에 대한 목록을 제공한다.">
    <caption>동기화대상 파일 목록</caption>
    <thead>
    <tr>
       	<th width="10%">
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택" />
       	</th>
       	<th width="10%">순번</th>
       	<th width="80%">파일명</th>
    </tr>
    </thead>
    <tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="3"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
    <c:forEach var="file" items="${fileList}" varStatus="status">
    <tr>
        <td class="lt_text3" width="10%">
         	<input type="checkbox" name="delYn" value="<c:out value="${file}"/>">
         	<input type="hidden" name="checkId" value="<c:out value="${file}"/>" />
        </td>
        <td class="lt_text3"  width="10%"><c:out value="${status.count}"/></td>
        <td class="lt_text3"  width="80%"><c:out value="${file}"/></td>
    </tr>
   	</c:forEach>
    </tbody>
    </table>
         
    <input type="hidden" name="pageIndex" value="<c:out value='${synchrnServerVO.pageIndex}'/>" />
    <input type="hidden" name="deleteFiles" />
    </form>

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
    var varForm = document.getElementById("synchrnServerVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/listSynchrnServer.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("synchrnServerVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/listSynchrnServer.do";
    varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(serverId) {
    var varForm = document.getElementById("synchrnServerVO");
    varForm.serverId.value = serverId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/detailSynchrnServer.do";
    varForm.submit();   
}

/* ********************************************************
 * 등록 처리화면
 ******************************************************** */
function fn_aram_regist() {
    var varForm = document.getElementById("synchrnServerVO");
    varForm.serverId.value = "";
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/registSynchrnServer.do";
    varForm.submit(); 
}

function fn_aram_process(){
    var varForm = document.getElementById("synchrnServerVO");
    if(varForm.totalCount.value == 0) {
        alert("동기화 대상목록이 없습니다");
        return; 
    }
    
    if(confirm("동기화 하시겠습니까?")) {
    	varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/processSynchrn.do";
    	varForm.submit();
    }
}

function fncUploadFile(){
    document.fileForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/uploadFile.do";
    document.fileForm.submit();
}

function fncDeleteFile(deleteFileNm){
    if(confirm("삭제 하시겠습니까?")){
        document.fileForm.deleteFileNm.value = deleteFileNm;
        document.fileForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/deleteFile.do";
        document.fileForm.submit();
    }
}

function fnCheckAll() {
    var checkField = document.deleteForm.delYn;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = document.deleteForm.checkAll.checked;
        }
    } else {
        checkField.checked = document.deleteForm.checkAll.checked;
    }
}

function fncManageChecked() {

    var checkField = document.deleteForm.delYn;
    var checkId = document.deleteForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;
    
    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else 
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount> 0) 
                returnBoolean = true;
            else {
                alert("선택된  파일이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.deleteForm.delYn.checked == false) {
                alert("선택된 파일이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.deleteForm.deleteFiles.value = returnValue;
    return returnBoolean;
}

function fncFileListDelete() {
    if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.deleteForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/deleteFile.do";
            document.deleteForm.submit();
        }
    }
}

</script>
