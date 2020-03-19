<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : ZipEdit.jsp
  * @Description : 우편번호 수정
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
	<h2>
	우편번호 수정
	</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="zipVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="zip"/>
<form:hidden path="sn"/>
<form:hidden path="ctprvnNm"/>
<form:hidden path="signguNm"/>
<form:hidden path="emdNm"/>

<table class="table-register" summary="리건물명과 번지동호를 수정하는 우편번호 수정 테이블이다.">
<CAPTION>우편번호 수정</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		우편번호
    	</th>          
    	<td width="80%">
    		<c:out value='${fn:substring(zipVO.zip, 0,3)}-${fn:substring(zipVO.zip, 3,6)}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		시도명
    	</th>
    	<td>
    		<c:out value='${zipVO.ctprvnNm}'/>
    	</td>    
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		시군구명
    	</th>          
    	<td>
    		<c:out value='${zipVO.signguNm}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		읍면동명
    	</th>          
    	<td>
			<c:out value='${zipVO.emdNm}'/>    
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="liBuldNm">리건물명</label>
    	</th>          
    	<td>
    		<form:input path="liBuldNm" size="60" maxlength="60" />
    	</td>    
  	</tr> 
  	<tr>
    	<th>
   			<span class="norequired_icon"></span>
    		<label for="lnbrDongHo">번지동호</label>
     	</th>          
    	<td>
    		<form:input path="lnbrDongHo" size="20" maxlength="20" />
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
<validator:javascript formName="zipVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
   	var varForm = document.getElementById("zipVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/listZip.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
   	var varForm = document.getElementById("zipVO");
   	
	if(!validateZipVO(varForm)){ 			
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/updateZip.do";
		varForm.submit();
	}
}

</script>
