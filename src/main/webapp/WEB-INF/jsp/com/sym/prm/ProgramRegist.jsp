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
  * @Class Name : ProgramRegist.jsp
  * @Description : 프로그램 등록
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
	<h2>프로그램 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="progrmManageVO" method="post" action="">

<table class="table-register" summary="프로그램목록 등록">
<caption>프로그램목록 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="progrmFileNm">프로그램파일명</label>
    	</th>
    	<td width="80%">
      		<form:input path="progrmFileNm" size="50"  maxlength="50" id="F1" title="프로그램파일명"/>
      		<form:errors path="progrmFileNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="progrmStrePath">저장경로</label>
    	</th>
    	<td>
      		<form:input path="progrmStrePath"  size="60"   maxlength="60" title="저장경로"/>
      		<form:errors path="progrmStrePath" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="progrmKoreanNm">한글명</label>
    	</th>
    	<td>
      		<form:input path="progrmKoreanNm" size="60"  maxlength="60" title="한글명"/>
      		<form:errors path="progrmKoreanNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="URL">URL</label>
    	</th>
    	<td>
      		<form:input path="URL" size="60"  maxlength="60" title="URL"/>
      		<form:errors path="URL" cssClass="error"/>
    	</td>
  	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	커뮤니티사용여부
	    </th>
	    <td>
	     	<input type="radio" name="cmmntyUseAt" id="cmmntyUseAtY" class="radio2" value="Y"><label for="cmmntyAtY">예</label>&nbsp;
	     	<input type="radio" name="cmmntyUseAt" id="cmmntyUseAtN" class="radio2" value="N" checked><label for="cmmntyAtN">아니오</label> 
	    </td>
	</tr>
  	<tr>
    	<th>
	    	<span class="norequired_icon"></span>
    		<label for="progrmDc">프로그램설명</label>
    	</th>
    	<td>
       		<form:textarea path="progrmDc" rows="14" cols="75" cssClass="txArea" title="프로그램설명"/>
      		<form:errors path="progrmDc" cssClass="error"/>
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

<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("progrmManageVO");
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgram.do";
    varForm.submit();
}

/* ********************************************************
 * 입력 처리 함수
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("progrmManageVO");
    
	if(!validateProgrmManageVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/sym/prm/insertProgram.do";
		varForm.submit();
	}
}

</script>
