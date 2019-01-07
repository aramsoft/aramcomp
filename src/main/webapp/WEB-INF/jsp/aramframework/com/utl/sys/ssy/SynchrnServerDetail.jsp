<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : SynchrnServerDetail.jsp
  * @Description : 동기화 서버 상세조회
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
	<h2>동기화 서버 상세조회</h2>
</div>
 
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="synchrnServerVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="serverId" />
    
<table class="table-detail" summary="동기화대상 서버에 대해 상세정보를 조회한다.">
<caption>동기화대상 서버 상세조회</caption>
    <tr>
        <th width="20%">
        	서버ID
        </th>
        <td>
        	<c:out value='${synchrnServerVO.serverId}'/>
        </td>
    </tr>
    <tr>
        <th>
        	서버명
        </th>
        <td>
        	<c:out value='${synchrnServerVO.serverNm}'/>
        </td>
    </tr>
    <tr>
        <th>
        	서버 IP
        </th>
        <td>
        	<c:out value='${synchrnServerVO.serverIp}'/>
        </td>
    </tr>
    <tr>
        <th>
         	서버 Port
        </th>
        <td>
        	<c:out value='${synchrnServerVO.serverPort}'/>
        </td>
    </tr>
    <tr>
        <th>
         	FTP ID
        </th>
        <td>
        	<c:out value='${synchrnServerVO.ftpId}'/>
        </td>
    </tr>
    <tr>
        <th>
        	FTP 비밀번호
        </th>
        <td>
        	<c:out value='${synchrnServerVO.ftpPassword}'/>
        </td>
    </tr>
    <tr>
        <th>
        	동기화 위치
        </th>
        <td>
        	<c:out value='${synchrnServerVO.synchrnLc}'/>
        </td>
    </tr>
</table>
    
<form:hidden path="strSynchrnServerNm" />
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

<div style="margin-top:10px; width:100%;"></div>

<div class="content_title" style="margin-bottom:10px; width:100%;">
	<h2>동기화 파일 목록</h2>
</div>

<form:form commandName="fileList" method="post" action="">
<input type="hidden" name="serverId" value="<c:out value='${synchrnServerVO.serverId}'/>" />
<input type="hidden" name="fileNm" />    

<table class="table-list">
<thead>
    <tr>
          <th>순번</th>
          <th>파일 목록</th>
          <th>다운로드</th>
          <th>삭제</th>
    </tr>
</thead>
<tbody>
    <c:forEach var="file" items="${fileList}" varStatus="status">
    <tr>
        <c:if test="${file == 'noList'}">
            <td class="lt_text" colspan="4">서버접속이 불가능합니다</td>
        </c:if>
        <c:if test="${file != 'noList'}">
            <td class="lt_text3"  width="20"><c:out value="${status.count}"/></td>
            <td class="lt_text3" width="200"><c:out value="${file}"/>&nbsp;</td>
            <td class="lt_text3" width="100">
            	<span class="button">
            	<a href="#" onclick="fn_aram_down_file('<c:out value="${file}"/>'); return false;">
            		다운로드
            	</a>
            	</span>
            </td>
            <!-- 동기화대상서버 파일 삭제 기능 -->
            <td class="lt_text3" width="30">
            	<span class="button">
            	<a href="#" onclick="fn_aram_remove_file('<c:out value="${file}"/>'); return false;">
            		<spring:message code="button.delete" />
            	</a>
            	</span>
            </td>
        </c:if>
    </tr>
  	</c:forEach>
</tbody>
</table>
</form:form>

</DIV>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("synchrnServerVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/listSynchrnServer.do";
    varForm.submit();       
}

function fn_aram_edit() {
    var varForm = document.getElementById("synchrnServerVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/editSynchrnServer.do";
    varForm.submit();
}

function fn_aram_delete() {
    var varForm = document.getElementById("synchrnServerVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")) {
        varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/deleteSynchrnServer.do";
        varForm.submit();
    }
}

function fn_aram_remove_file(fileNm) {
    var varForm = document.getElementById("fileList");
    varForm.fileNm.value = fileNm;
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/deleteSynchrnServerFile.do";
        varForm.submit();
    }
}

function fn_aram_down_file(fileNm) {
    var varForm = document.getElementById("fileList");
    varForm.fileNm.value = fileNm;
    
    if(confirm("다운로드 하시겠습니까?")){
        varForm.action = "${pageContext.request.contextPath}/utl/sys/ssy/getSynchrnServerFile.do";
        varForm.submit();
    }
}

</script>
