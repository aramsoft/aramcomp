<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : AdministCodeDetail.jsp
  * @Description : 행정코드 상세조회
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
	<h2>행정코드 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="administCodeVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="administZoneSe" />
<form:hidden path="administZoneCode" />

<table class="table-detail" summary="행정구역구분, 행정구역코드, 행정구역명, 생성일자, 페기일자, 상위행정구역코드, 사용여부가 담긴 행정코드 상세조회 테이블이다.">
<CAPTION>행정코드 상세조회</CAPTION>
  	<tr>
    	<th width="20%">
    		행정구역구분
    	</th>
    	<td width="80%">
      		<form:select path="administZoneSe" disabled="true" title="행정구역구분">
	      		<form:option value="1" label="법정동" />
	      		<form:option value="2" label="행정동" />
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		행정구역코드
    	</th>
    	<td>
    		${administCodeVO.administZoneCode}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		행정구역명
    	</th>
    	<td>
    		${administCodeVO.administZoneNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		생성일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(administCodeVO.creatDe, 0,4)}-${fn:substring(administCodeVO.creatDe, 4,6)}-${fn:substring(administCodeVO.creatDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
   			폐기일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(administCodeVO.ablDe, 0,4)}-${fn:substring(administCodeVO.ablDe, 4,6)}-${fn:substring(administCodeVO.ablDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		상위행정구역코드
    	</th>
    	<td>
    		${administCodeVO.upperAdministZoneNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
			<form:select path="useAt" disabled="true">
				<form:option value="Y" label="사용" />
				<form:option value="N" label="미사용" />
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

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("administCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/listAdministCode.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("administCodeVO");
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/editAdministCode.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("administCodeVO");
    
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/deleteAdministCode.do";
		varForm.submit();
	}
}

</script>
