<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : ZipRegist.jsp
  * @Description : 우편번호 등록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>
   	우편번호 등록
  	</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="zipVO" method="post">

<table class="table-register" summary="우편번호, 시도명, 시군구명, 읍면동명, 리건물명, 번지동호를 입력하는 우편번호 등록 테이블입니다.">
<CAPTION>우편번호 등록</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="zip">우편번호</label>
    	</th>
    	<td width="80%">
      		<form:input  path="zip" size="6" maxlength="6" />
      		 &nbsp;* 우편번호의 '-'를 제외하고 입력하시오.
      		<form:errors path="zip" cssClass="error"/> 
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="ctprvnNm">시도명</label>
    	</th>
    	<td>
      		<form:input  path="ctprvnNm" size="20" maxlength="20" />
      		<form:errors path="ctprvnNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="signguNm">시군구명</label>
    	</th>
    	<td>
      		<form:input  path="signguNm" size="20" maxlength="20" />
      		<form:errors path="signguNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="emdNm">읍면동명</label>
    	</th>
    	<td>
      		<form:input  path="emdNm" size="30" maxlength="30" />
      		<form:errors path="emdNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		<label for="liBuldNm">리건물명</label>
    	</th>
    	<td>
      		<form:input  path="liBuldNm" size="60" maxlength="60" />
      		<form:errors path="liBuldNm" cssClass="error"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="lnbrDongHo">번지동호</label>
     	</th>
    	<td>
      		<form:input  path="lnbrDongHo" size="20" maxlength="20" />
      		<form:errors path="lnbrDongHo" cssClass="error"/>
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
 * 등록처리
 ******************************************************** */
function fn_aram_insert(form){
   	var varForm = document.getElementById("zipVO");
   	
	if(!validateZipVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/insertZip.do";
		varForm.submit();
	}
}

</script>
