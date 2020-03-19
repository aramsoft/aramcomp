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
  * @Class Name : ProgramChangeRequstRegist.jsp
  * @Description : 프로그램변경요청 등록
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
	<h2>프로그램변경요청 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="progrmManageDtlVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="rqestProcessCn" />
<form:hidden path="opetrId" />
<form:hidden path="processSttus" />
<form:hidden path="processDe" />

<table class="table-register" summary="프로그램변경요청 ">
<caption>프로그램변경요청</caption>
  	<tr>
    	<th width="20%" >
    		<span class="required_icon"></span>
    		<label for="progrmFileNm">프로그램파일명</label>
    	</th>
    	<td width="80%">
    		<input id="progrmFileNm" name="progrmFileNm" size="50"  maxlength="50"  title="프로그램파일명" readonly/>
	    	<form:errors path="progrmFileNm" cssClass="error"/>
        	<a href="#" target="_blank" onclick="javascript:searchFileNm(); return false;" style="selector-dummy:expression(this.hideFocus=false);"  title="새 창으로 이동">
        		<img src="${pageContext.request.contextPath}/images/cmm/icon/search.gif" alt='(프로그램파일명 검색)' width="15" height="15" />(프로그램파일명 검색)
        	</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="rqestPersonId">요청자</label>
    	</th>
    	<td>
			<form:input path="rqestPersonId" size="20"  maxlength="20"  title="요청자"/>
			<form:errors path="rqestPersonId" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="rqestDe">요청일자</label>
    	</th>
    	<td>
      		<form:hidden path="rqestDe" />
	    	<c:if test="${!empty progrmManageDtlVO.rqestDe}">
 				<c:set var="rqestDeVal" value="${fn:substring(progrmManageDtlVO.rqestDe, 0,4)}-${fn:substring(progrmManageDtlVO.rqestDe, 4,6)}-${fn:substring(progrmManageDtlVO.rqestDe, 6,8)}"/>
      		</c:if>
      		<input name="rqestDeView" id="rqestDeView" type="text" size="10" title="요청일자" value="${rqestDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].rqestDe, document.forms[0].rqestDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
   	  		<form:errors path="rqestDe" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="rqestSj">요청제목</label>
    	</th>
    	<td>
    		<form:input path="rqestSj" size="50"  maxlength="50"  title="요청제목"/>
			<form:errors path="rqestSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		<label for="changeRqestCn">변경요청내용</label>
    	</th>
    	<td>
      		<form:textarea path="changeRqestCn" rows="14" cols="75" title="변경요청내용"/>
      		<form:errors path="changeRqestCn" cssClass="error"/>
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

</div>

<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageDtlVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

var gArguments = new Array();

/* ********************************************************
 * 파일목록조회  함수
 ******************************************************** */
function searchFileNm() {
    var varForm = document.getElementById("progrmManageDtlVO");
	gArguments["progrmFileNm"] = varForm.progrmFileNm;
	
	var url = "/sym/prm/listProgramPopup.do";

	window.open(url, "p_programInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 목록조회 처리 함수
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeRequst.do";
    varForm.submit();
}

/* ********************************************************
 * 입력 처리 함수
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("progrmManageDtlVO");
    
	if(!validateProgrmManageDtlVO(varForm)){
		return;
	}

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/sym/prm/insertProgramChangeRequst.do";
		varForm.submit();
	}
}

</script>
