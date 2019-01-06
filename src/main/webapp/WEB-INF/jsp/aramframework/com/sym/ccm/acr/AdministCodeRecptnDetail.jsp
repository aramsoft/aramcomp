<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : AdministCodeRecptnDetail.jsp
  * @Description : 법정동코드수신 상세조회
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
	<h2>법정동코드수신 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="administCodeRecptnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="administZoneSe" />
<form:hidden path="administZoneCode" />

<table class="table-detail">

	<c:if test="${administCodeRecptnVO.administZoneCode == null}">
    <tr>
        <td class="lt_text3">
            <spring:message code="common.nodata.msg" />
        </td>
    </tr>
	</c:if>

	<c:if test="${administCodeRecptnVO.administZoneCode != null}">
  	<tr>
    	<th width="20%">
    		법정동코드
    	</th>
    	<td width="80%">
    		<c:out value="${administCodeRecptnVO.administZoneCode}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		행정구역명
    	</th>
    	<td>
    		<c:out value="${administCodeRecptnVO.administZoneNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		생성일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(administCodeRecptnVO.creatDe, 0,4)}-${fn:substring(administCodeRecptnVO.creatDe, 4,6)}-${fn:substring(administCodeRecptnVO.creatDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		폐지유무
    	</th>
    	<td>
	  		<form:select path="ablEnnc" title="폐지유무" disabled="true">
	      		<form:option value="0" label="사용" />
		  		<form:option value="1" label="말소" />
	  		</form:select>
    	</td>
  	</tr>
	</c:if>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>법정동코드수신 이력</h2>
</div>

<table class="table-list">
<thead>
	<tr>
		<th width="10%">순번</th>
		<th width="10%">발생일자</th>
		<th width="20%">최하위행정구역명</th>
		<th width="15%">변경구분</th>
		<th width="15%">처리구분</th>
		<th width="10%">생성일자</th>
		<th width="10%">폐지유무</th>
		<th width="10%">폐지일자</th>
	</tr>
</thead>

<tbody>
	<c:if test="${fn:length(administCodeRecptnList) == 0}">
	<tr>
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

	<c:forEach items="${administCodeRecptnList}" var="result" varStatus="status">
	<tr>
		<td class="lt_text3"><c:out value="${status.count}"/></td>

		<td class="lt_text3"><c:out value='${fn:substring(result.occrrDe, 0,4)}-${fn:substring(result.occrrDe, 4,6)}-${fn:substring(result.occrrDe, 6,8)}'/></td>
		<td class="lt_text"><c:out value="${result.lowestAdministZoneNm}"/></td>
	    <td class="lt_text">
			<select title="변경구분" name="changeSeCode" class="select" disabled="disabled">
			   	<option value=''><c:out value="${result.changeSeCode}"/></option>
			    <c:forEach var="comCode" items="${COM043_changeSe}" varStatus="status">
				<option value='<c:out value="${comCode.code}"/>' <c:if test="${comCode.code == result.changeSeCode}">selected="selected"</c:if>><c:out value="${comCode.codeNm}"/></option>
				</c:forEach>
			</select>
	    </td>
	    <td class="lt_text">
			<select title="처리구분" name="processSe" class="select" disabled="disabled">
			   	<option value=''><c:out value="${result.processSe}"/></option>
			    <c:forEach var="comCode" items="${COM044_processSe}" varStatus="status">
				<option value='<c:out value="${comCode.code}"/>' <c:if test="${comCode.code == result.processSe}">selected="selected"</c:if>><c:out value="${comCode.codeNm}"/></option>
				</c:forEach>
			</select>
	    </td>
		<td class="lt_text3"><c:out value='${fn:substring(result.creatDe, 0,4)}-${fn:substring(result.creatDe, 4,6)}-${fn:substring(result.creatDe, 6,8)}'/></td>
	    <td class="lt_text3">
		  	<select title="폐지유무" name="ablEnnc" disabled="disabled">
		      	<option value="0" <c:if test="${result.ablEnnc == '0'}">selected="selected"</c:if>>사용</option>
			  	<option value="1" <c:if test="${result.ablEnnc == '1'}">selected="selected"</c:if>>말소</option>
		  	</select>
	    </td>
		<td class="lt_text3"><c:out value='${fn:substring(result.ablDe, 0,4)}-${fn:substring(result.ablDe, 4,6)}-${fn:substring(result.ablDe, 6,8)}'/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("administCodeRecptnVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/acr/listAdministCodeRecptn.do";
    varForm.submit();  
}

</script>
