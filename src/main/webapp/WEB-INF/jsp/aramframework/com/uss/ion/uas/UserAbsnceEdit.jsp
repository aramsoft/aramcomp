<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : UserAbsnceEdit.jsp
  * @Description : 사용자부재 수정
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
	<h2>사용자부재 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="userAbsnceVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="사용자부재정보를 수정한다.">
<caption>사용자부재 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		사용자ID
    	</th>
    	<td>
   			<form:input path="userId" title="사용자ID" size="30" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		사용자명
    	</th>
    	<td>
    		<form:input path="userNm" title="사용자명" size="30" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		부재여부
    	</th>
    	<td>
      		<form:select path="userAbsnceAt" title="부재여부">
          		<form:option value="Y" label="Y" />
          		<form:option value="N" label="N" />
      		</form:select>
   		</td> 
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		등록일시
    	</th>
    	<td>
    		<form:input path="lastUpdusrPnttm" title="등록일시" maxLength="50" size="20" readonly="true" />
    	</td>
  	</tr>
</table>
 
<!-- 검색조건 유지 -->
<form:hidden path="selAbsnceAt" />
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("userAbsnceVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/uas/listUserAbsnce.do";
    varForm.submit();       
}

function fn_aram_update() {
    var varForm = document.getElementById("userAbsnceVO");
 
	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/uas/updateUserAbsnce.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("userAbsnceVO");

	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/uas/deleteUserAbsnce.do";
        varForm.submit();
    }
}

</script>
