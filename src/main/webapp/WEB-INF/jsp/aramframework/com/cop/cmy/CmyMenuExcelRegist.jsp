<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CmyMenuExcelRegist.jsp
  * @Description : 커뮤니티 메뉴 엑셀파일 등록
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
	커뮤니티 메뉴 엑셀파일 등록
  	</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert_excel(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="communityMenuVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="trgetId" value="${curTrgetId}" />

<table class="table-register" summary="메뉴 엑셀파일을 첨부할 수 있는 등록 테이블이다.">
<CAPTION>메뉴 엑셀파일 등록</CAPTION>
  	<tr>
    	<th width="20%" >
    		<span class="required_icon"></span>
    		<label for="fileNm">메뉴 엑셀파일</label>
    	</th>
  		<td>
  			<input name="fileNm" id="fileNm" type="file" />
  		</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${communityMenuVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${communityMenuVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${communityMenuVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${communityMenuVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("communityMenuVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert_excel(){
   	var varForm = document.getElementById("communityMenuVO");

	// 파일 확장명 확인
	var arrExt      = "xls";
	var arrExt1     = "xlsx";
	var strFilePath = varForm.fileNm.value;
	var arrTmp      = strFilePath.split(".");
	var strExt      = arrTmp[arrTmp.length-1].toLowerCase();

	if (!(arrExt == strExt || arrExt1 == strExt)) {
		alert("엑셀 파일을 첨부하지 않았습니다.\n확인후 다시 처리하십시오. ");
		abort;
	}

	varForm.action = "${pageContext.request.contextPath}/cop/cmy/insertMenuExcel.do";
	varForm.submit();
}

</script>

