<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CmmnClCodeEdit.jsp
  * @Description : 공통분류코드 수정
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
	<h2>공통분류코드 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="cmmnClCodeVO" action=""  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register" summary="분류코드명, 분류코드설명, 사용여부를 수정하는 공통분류코드 수정 테이블이다.">
<CAPTION>공통분류코드 수정</CAPTION>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		분류코드
    	</th>
    	<td width="80%">
      		<form:input path="clCode" size="60" maxlength="60" readinly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="clCodeNm">분류코드명</label>
    	</th>
    	<td>
      		<form:input  path="clCodeNm" size="60" maxlength="60" />
      		<form:errors path="clCodeNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="clCodeDc">분류코드설명</label>
    	</th>
    	<td>
      		<form:textarea path="clCodeDc" cols="75" rows="14" style="width:450px;"  />
      		<form:errors path="clCodeDc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
      		<form:select path="useAt">
	      		<form:option value="Y" label="사용"/>
	      		<form:option value="N" label="미사용"/>
      		</form:select>
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
<validator:javascript formName="cmmnClCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("cmmnClCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/ccc/listCmmnClCode.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("cmmnClCodeVO");
    
	if(!validateCmmnClCodeVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/ccc/updateCmmnClCode.do";
		varForm.submit();
	}
}

</script>
