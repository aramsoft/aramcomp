<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : WordDicaryDetail.jsp
  * @Description : 용어사전 상세조회
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
	<h2>용어사전 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="wordDicaryVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="wordId" />

<table class="table-detail" summary="용어사전에 대한 정보를 조회합니다.">
<caption>용어사전상세조회</caption>
  	<tr>
    	<th width="20%">
     		용어명
    	</th>
    	<td width="80%">
    		<c:out value="${wordDicaryVO.wordNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
      		영문명
    	</th>
    	<td>
    		<c:out value="${wordDicaryVO.engNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
      		용어설명
    	</th>
    	<td>
      		<textarea name="wordDc" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="용어설명">
<c:out value="${wordDicaryVO.wordDc}"/>
      		</textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		동의어
    	</th>
    	<td>
    		<c:out value="${wordDicaryVO.synonm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		등록일자
    	</th>
    	<td>
    		<fmt:formatDate value="${wordDicaryVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		등록자
    	</th>
    	<td>
    		<c:out value="${wordDicaryVO.emplyrNm}"/>
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


<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("wordDicaryVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/wor/listWordDicary.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("wordDicaryVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/wor/editWordDicary.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("wordDicaryVO");
    
	if	(confirm("<spring:message code='common.delete.msg' />"))	{
		varForm.action = "${pageContext.request.contextPath}/uss/olh/wor/deleteWordDicary.do";
		varForm.submit();
	}
}

</script>

