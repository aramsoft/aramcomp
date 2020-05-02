<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BatchOpertEdit.jsp
  * @Description : 배치작업 수정
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
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>배치작업 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="batchOpertVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="배치작업 수정기능을 제공한다.">
<caption>배치작업 수정</caption>
	<tr> 
  		<th width="20%">
    		<span class="required_icon"></span>
  			<label for="batchOpertId">배치작업ID</label>
  		</th>
  		<td width="80%">
      		<form:input path="batchOpertId" size="20" maxlength="20" readonly="true" cssClass="readOnlyClass"/>
     		<form:errors path="batchOpertId" cssClass="error" />
  		</td>
	</tr>
	<tr> 
  		<th>
    		<span class="required_icon"></span>
  			<label for="batchOpertNm">배치작업명</label>
  		</th>
  		<td>
      		<form:input path="batchOpertNm" size="60" maxlength="60"/>
     		<form:errors path="batchOpertNm" cssClass="error" />  
  		</td>
	</tr> 
	<tr> 
  		<th>
    		<span class="norequired_icon"></span>
  			<label for="batchProgrm">1. 배치프로그램</label>
  		</th>
  		<td>
      		<form:input path="batchProgrm" size="60" maxlength="255"/>
     		<form:errors path="batchProgrm" cssClass="error" />  
  		</td>
	</tr> 
	<tr> 
  		<th>
    		<span class="norequired_icon"></span>
  			<label for="paramtr">파라미터</label>
  		</th>
  		<td>
    		<form:input path="paramtr" size="60" maxlength="250"/>
   			<form:errors path="paramtr" cssClass="error" />
  		</td>
	</tr>
	<tr> 
  		<th>
    		<span class="norequired_icon"></span>
  			<label for="batchObject">2. 배치객체</label>
  		</th>
  		<td>
      		<form:input path="batchObject" size="60" maxlength="255"/>
     		<form:errors path="batchObject" cssClass="error" />  
  		</td>
	</tr> 
	<tr> 
  		<th>
    		<span class="norequired_icon"></span>
  			<label for="batchMethod">배치메소드</label>
  		</th>
  		<td>
      		<form:input path="batchMethod" size="60" maxlength="255"/>
     		<form:errors path="batchMethod" cssClass="error" />  
  		</td>
	</tr> 
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	사용여부
	    </th>
	    <td>
	     	<label for="useAtY"><spring:message code="button.use" /></label> : <input type="radio" name="useAt" id="useAtY" class="radio2" value="Y"  <c:if test="${batchOpertVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
	     	<label for="useAtN"><spring:message code="button.notUsed" /></label> : <input type="radio" name="useAt" id="useAtN" class="radio2" value="N" <c:if test="${batchOpertVO.useAt == 'N'}"> checked="checked"</c:if>>
	     	<form:errors path="useAt" cssClass="error"/>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="batchOpertVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("batchOpertVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchOpert.do"
    varForm.submit();    
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("batchOpertVO");
    
    if(!validateBatchOpertVO(varForm)){             
        return;
    }

    if(confirm("<spring:message code='common.update.msg' />")){
        varForm.action = "${pageContext.request.contextPath}/sym/bat/updateBatchOpert.do"
        varForm.submit();
    }
}

</script>
