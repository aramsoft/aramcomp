<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : SystemCntcConfirmEdit.jsp
  * @Description : 시스템연계 승인 수정
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
	<h2>시스템연계 승인 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<c:if test="${systemCntcVO.confmAt eq 'N'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_confirm('Y'); return false;">승인</a></span>
		</c:if>
		<c:if test="${systemCntcVO.confmAt eq 'Y'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_confirm('N'); return false;">승인취소</a></span>
		</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="systemCntcVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcId"/>
<form:hidden path="confmAt"/>

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		연계ID
    	</th>
    	<td width="80%">
    		<c:out value="${systemCntcVO.cntcId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		연계명
    	</th>
    	<td>
    		<c:out value="${systemCntcVO.cntcNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		연계유형
    	</th>
    	<td>
    		<c:out value="${systemCntcVO.cntcType}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		제공기관
    	</th>
    	<td>
        	<form:select path="provdInsttId"  disabled="true" title="제공기관선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcInsttList}" itemValue="insttId" itemLabel="insttNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		제공시스템
    	</th>
    	<td>
        	<form:select path="provdSysId"  disabled="true" title="제공시스템선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcProvdSystemList}" itemValue="sysId" itemLabel="sysNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		제공서비스
    	</th>
     	<td>
        	<form:select path="provdSvcId"  disabled="true" title="제공서비스선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcProvdServiceList}" itemValue="svcId" itemLabel="svcNm"/>
        	</form:select>
    	</td>
   	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		요청기관
    	</th>
    	<td>
        	<form:select path="requstInsttId"  disabled="true" title="요청기관선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcInsttList}" itemValue="insttId" itemLabel="insttNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		요청시스템
    	</th>
    	<td>
        	<form:select path="requstSysId"  disabled="true" title="요청시스템선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcRequstSystemList}" itemValue="sysId" itemLabel="sysNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		유효시작일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(systemCntcVO.validBeginDe, 0,4)}-${fn:substring(systemCntcVO.validBeginDe, 4,6)}-${fn:substring(systemCntcVO.validBeginDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		유효종료일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(systemCntcVO.validEndDe, 0,4)}-${fn:substring(systemCntcVO.validEndDe, 4,6)}-${fn:substring(systemCntcVO.validEndDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		사용여부
    	</th>
    	<td>
	  		<form:select path="useAt" disabled="true" title="사용여부선택">
	      		<form:option value="Y" label="Y" />
		  		<form:option value="N" label="N" />
	  		</form:select>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
* 목록 으로 가기
******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("systemCntcVO");
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/sim/listSystemCntcConfirm.do";
    varForm.submit();
}

/* ********************************************************
 * 승인/승인취소 처리 함수
 ******************************************************** */
function fn_aram_confirm(confmAt){
    var varForm = document.getElementById("systemCntcVO");
	if (confirm((confmAt=='Y')?"<spring:message code="common.acknowledgement.msg"/>":"<spring:message code="common.acknowledgementcancel.msg"/>")) {
		varForm.confmAt.value	= confmAt;
		varForm.action          = "${pageContext.request.contextPath}/ssi/syi/sim/confirmSystemCntcConfirm.do";
		varForm.submit();
	}
}

</script>
