<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ServerEqpmnRelateRegist.jsp
 * @Description : 서버H/W 연관 등록
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
	<h2>서버H/W 연관 등록</h2>
</div>

<form:form commandName="serverEqpmnRelateVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="serverId" />

<input type="hidden" name="serverEqpmnIds"/>
<input type="hidden" name="regYns"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:self.close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
	<div class="keyword_area">
		<label for="strServerNm">서버S/W 명 : </label>
		<input type="text" value="<c:out value="${server.serverId}"/>" size="20" class="readOnlyClass" readOnly>&nbsp;
		<input type="text" id="strServerNm" value="<c:out value="${server.serverNm}"/>" size="20" title="검색" onkeypress="press();" class="readOnlyClass" readOnly>
	</div>
</div>

<table class="table-list" summary="서버장비와 서버와의 관계를 등록한다.">
<caption>서버H/W 등록</caption>
<thead>
  	<tr>
	    <th width="25%" scope="col">서버H/W ID</th>
	    <th width="25%" scope="col">서버H/W 명</th>
	    <th width="20%" scope="col">서버H/W IP</th>
	    <th width="14%" scope="col">관리자</th>
	    <th width="15%" scope="col">등록여부</th>
	    <th width="1%" scope="col" ></th>
  	</tr>
</thead>
<tbody>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
    	<td class="lt_text3"><c:out value="${result.serverEqpmnId}"/></td>
    	<td class="lt_text3"><c:out value="${result.serverEqpmnNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.serverEqpmnIp}"/></td>
    	<td class="lt_text3"><c:out value="${result.serverEqpmnMngrNm}"/></td>
    	<td class="lt_text3">
    		<label for="regYn">
        	<select name="regYn" id="regYn">
            	<option value="Y" <c:if test="${result.regYn == 'Y' }">selected</c:if>>등록</option>
            	<option value="N" <c:if test="${result.regYn == 'N' }">selected</c:if>>미등록</option>
        	</select>
    		</label>
    	</td>
    	<td>
    		<input type="hidden" name="checkId" value="<c:out value="${result.serverEqpmnId}"/>" />
    		<input type="hidden" name="orgRegYn" value="<c:out value="${result.regYn}"/>"/>
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function fncManageChecked() {
	var varForm = document.getElementById("serverEqpmnRelateVO");
    var checkId = varForm.checkId;
    var checkRegYn = varForm.regYn;
    var checkOrgRegYn = varForm.orgRegYn;
    
    var returnValue = "";
    var returnRegYns = "";
    var checkedCount = 0;
    var returnBoolean = false;

    if(checkId) {
        if(checkId.length> 1) {
            for(var i=0; i<checkId.length; i++) {
                if(checkRegYn[i].value != checkOrgRegYn[i].value) {
                	returnValue += ((checkedCount==0? "" : ";") + checkId[i].value);
                	returnRegYns += ((checkedCount==0? "" : ";") + checkRegYn[i].value);
                    checkedCount++;
                }
            }
        } else {
            returnValue = checkId.value;
            returnRegYns = checkRegYn.value;
        }
    } 
    
    if(returnValue.length> 0) {
        varForm.serverEqpmnIds.value = returnValue;
        varForm.regYns.value = returnRegYns;
        returnBoolean = true;
    } else {
        alert("변경된  서버H/W가 없습니다.");
        returnBoolean = false;
    }

    return returnBoolean;
}

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("serverEqpmnRelateVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServerEqpmnRelate.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("serverEqpmnRelateVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServerEqpmnRelate.do";
    varForm.submit();
}

function fn_aram_insert() {
    if(!fncManageChecked()) return;
    
	if(confirm("<spring:message code='common.update.msg'/>")){
        var varForm = document.getElementById("serverEqpmnRelateVO");
        varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/updateServerEqpmnRelate.do";
        varForm.submit();
    }
}

</script>
