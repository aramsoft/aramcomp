<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CmmnCodeRegist.jsp
  * @Description : 공통코드 등록
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
	<h2>공통코드 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="cmmnCodeVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="분류코드, 코드ID, 코드ID명, 코드ID설명, 사용여부를 입력하는 공통코드 등록 테이블이다.">
<CAPTION>공통코드 등록</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="clCode">분류코드</label>
    	</th>          
    	<td width="80%">
			<select name="clCode" id="clCode">
				<c:forEach var="result" items="${cmmnClCode}" varStatus="status">
					<option value='<c:out value="${result.clCode}"/>'><c:out value="${result.clCodeNm}"/></option>
				</c:forEach>			  		   
			</select>
    	</td>
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="codeId">코드ID</label>
    	</th>          
    	<td>
      		<form:input  path="codeId" size="6" maxlength="6" />
      		<form:errors path="codeId" cssClass="error"/>
    	</td>
  	</tr> 
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="codeIdNm">코드ID명</label>
    	</th>          
    	<td>
      		<form:input  path="codeIdNm" size="60" maxlength="60" />
      		<form:errors path="codeIdNm" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="codeIdDc">코드ID설명</label>
    	</th>
    	<td>
     		<form:textarea path="codeIdDc" cols="75" rows="14" style="width:450px;"  />
      		<form:errors path="codeIdDc" cssClass="error"/>
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
<input type="hidden" name="searchCondition" value="${cmmnCodeVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${cmmnCodeVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${cmmnCodeVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${cmmnCodeVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cmmnCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("cmmnCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/listCmmnCode.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("cmmnCodeVO");
	
	if(!validateCmmnCodeVO(varForm)){ 			
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/insertCmmnCode.do";
		varForm.submit();
	}
}

</script>
