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
  * @Class Name : ProgramEdit.jsp
  * @Description : 프로그램 수정
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
	<h2>프로그램 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="progrmManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register" summary="프로그램목록 상세조회 /수정">
<caption>프로그램목록 상세조회 /수정</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="progrmFileNm">프로그램파일명</label>
    	</th>
    	<td width="80%">
      		<form:input  path="progrmFileNm" size="50"  maxlength="50" title="프로그램파일명" readonly="true" />
      		<form:errors path="progrmFileNm" cssClass="error"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="progrmStrePath">저장경로</label>
    	</th>
    	<td>
      		<form:input  path="progrmStrePath" size="50"  maxlength="50" title="저장경로"/>
      		<form:errors path="progrmStrePath" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="progrmKoreanNm">한글명</label>
    	</th>
    	<td>
      		<form:input path="progrmKoreanNm" size="60"  maxlength="60"  title="한글명"/>
      		<form:errors path="progrmKoreanNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="URL">URL</label>
    	</th>
    	<td>
      		<form:input path="URL" size="60"  maxlength="60" title="URL" />
      		<form:errors path="URL" cssClass="error"/>
    	</td>
  	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	커뮤니티사용여부
	    </th>
	    <td>
	     	<input type="radio" name="cmmntyUseAt" id="cmmntyUseAtY" class="radio2" value="Y" <c:if test="${progrmManageVO.cmmntyUseAt == 'Y'}"> checked</c:if>><label for="cmmntyAtY">예</label>&nbsp;
	     	<input type="radio" name="cmmntyUseAt" id="cmmntyUseAtN" class="radio2" value="N" <c:if test="${progrmManageVO.cmmntyUseAt== 'N'}"> checked</c:if>><label for="cmmntyAtN">아니오</label> 
	    </td>
	</tr>
  	<tr>
    	<th>
	    	<span class="norequired_icon"></span>
    		<label for="progrmDc">프로그램설명</label>
    	</th>
    	<td>
      		<form:textarea path="progrmDc" rows="14" cols="75" title="프로그램설명"/>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
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
 * 수정처리 함수
 ******************************************************** */
function fn_aram_update() {
    var varForm = document.getElementById("progrmManageVO");
    
	if(!validateProgrmManageVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action="${pageContext.request.contextPath}/sym/prm/updateProgram.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 삭제처리함수
 ******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("progrmManageVO");
    
	if(confirm("<spring:message code='common.delete.msg' />")){
		varForm.action="${pageContext.request.contextPath}/sym/prm/deleteProgram.do";
		varForm.submit();
	}
}

</script>
