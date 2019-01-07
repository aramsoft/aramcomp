<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : RecomendSiteDetail.jsp
  * @Description : 추천사이트 상세조회
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
	<h2>추천사이트 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="recomendSiteVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="recomendSiteId" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="추천사이트 정보테이블">
  	<tr>
    	<th width="20%">
     		추천사이트명
    	</th>
    	<td width="80%">
        	<c:out value="${recomendSiteVO.recomendSiteNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		추천사이트 URL
    	</th>
    	<td>
			<a href="<c:out value="${recomendSiteVO.recomendSiteUrl}"/>" target="new">
        		<c:out value="${recomendSiteVO.recomendSiteUrl}"/>
			</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="recomendSiteDc">추천사이트 설명</label>
    	</th>
    	<td width="80%">
      		<textarea name="recomendSiteDc" id="recomendSiteDc" class="textarea"  cols="300" rows="10"  style="width:450px;"  readonly><c:out value="${recomendSiteVO.recomendSiteDc}"/>
      		</textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="recomendResnCn">추천사유내용</label>
    	</th>
    	<td width="80%">
      		<textarea name="recomendResnCn" id="recomendResnCn" class="textarea"  cols="300" rows="5"  style="width:450px;"   readonly><c:out value="${recomendSiteVO.recomendResnCn}"/>
      		</textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		추천승인여부
    	</th>
    	<td>
        	<c:choose>
            <c:when test="${recomendSiteVO.recomendConfmAt == 'Y'}">
				승인
            </c:when>
            <c:otherwise>
				미승인
            </c:otherwise>
            </c:choose>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		승인일자
    	</th>
    	<td>
        	<c:out value='${fn:substring(recomendSiteVO.confmDe, 0,4)}-${fn:substring(recomendSiteVO.confmDe, 4,6)}-${fn:substring(recomendSiteVO.confmDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		등록일자
    	</th>
    	<td>
        	<fmt:formatDate value="${recomendSiteVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
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
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
	var varForm = document.getElementById("recomendSiteVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rec/editRecomendSite.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
	var varForm = document.getElementById("recomendSiteVO");
	
    if  (confirm("<spring:message code='common.delete.msg' />"))    {
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/rec/deleteRecomendSite.do";
    	varForm.submit();
    }
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
	var varForm = document.getElementById("recomendSiteVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rec/listRecomendSite.do";
    varForm.submit();
}

</script>

