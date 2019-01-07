<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ProxySvcDetail.jsp
  * @Description : 프록시설정 상세조회
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
	<h2>프록시설정 상세조회</h2>
</div>
 
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<form:form commandName="proxySvcVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="proxyId" />

<table class="table-detail"  summary="프록시설정에 대한 상세정보를 제공한다.">
<caption>프록시설정 상세조회</caption>
    <tr>
        <th width="20%">
        	프록시ID
        </th>
        <td>
        	<c:out value='${proxySvcVO.proxyId}'/>
        </td>
    </tr>
    <tr>
        <th>
         	프록시명
        </th>
        <td>
        	<c:out value='${proxySvcVO.proxyNm}'/>
        </td>
    </tr>
    <tr>
        <th>
        	프록시IP
         </th>
        <td>
        	<c:out value='${proxySvcVO.proxyIp}'/>
        </td>
    </tr>
    <tr>
        <th>
        	프록시Port
        </th>
        <td>
        	<c:out value='${proxySvcVO.proxyPort}'/>
        </td>
    </tr>
    <tr>
        <th>
        	대상서비스명
        </th>
        <td>
        	<c:out value='${proxySvcVO.trgetSvcNm}'/>
        </td>
    </tr>
    <tr>
        <th>
         	서비스설명
        </th>
        <td>
        	<c:out value='${proxySvcVO.svcDc}'/>
        </td>
    </tr>
    <tr>
        <th>
          	서비스IP
        </th>
        <td>
        	<c:out value='${proxySvcVO.svcIp}'/>
        </td>
    </tr>
    <tr>
        <th>
          	서비스Port
        </th>
        <td>
        	<c:out value='${proxySvcVO.svcPort}'/>
        </td>
    </tr>
    <tr>
        <th>
         	서비스상태
        </th>
        <td>
        	<c:out value='${proxySvcVO.svcSttusNm}'/>
        </td>
    </tr>
</table>
    
<form:hidden path="strProxyNm" />
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("proxySvcVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxySvc.do";
    varForm.submit();       
}

function fn_aram_edit() {
    var varForm = document.getElementById("proxySvcVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/editProxySvc.do";
    varForm.submit();
}

function fn_aram_delete() {
    var varForm = document.getElementById("proxySvcVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.submit();
        varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/deleteProxySvc.do";
    }
}

</script>
