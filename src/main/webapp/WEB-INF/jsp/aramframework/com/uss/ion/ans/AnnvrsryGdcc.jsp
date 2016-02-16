<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AnnvrsryGdcc.jsp
 * @Description : 기념일 상세 조회
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>기념일 상세 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:430px;">

<div class="content_title">
	<h2>기념일 안내</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="annvrsryManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="annId" />

<table class="table-register"summary="기념일 알림화면">
<caption>기념일 알림화면</caption>
  	<tr> 
    	<th width="150">
    		사용자
    	</th>
    	<td width="250">
    		&nbsp;<c:out value="${annvrsryManageVO.usNm      }"/>
    	</td>
  	</tr> 
  	<tr>
    	<th>
    		소속
    	</th>
    	<td>
    		&nbsp;<c:out value="${annvrsryManageVO.orgnztNm      }"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		기념일자
    	</th>
    	<td>
    		&nbsp;<c:out value="${annvrsryManageVO.annvrsryDeNm      }"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		기념일 제목
    	</th>
    	<td>
    		&nbsp;<c:out value="${annvrsryManageVO.annvrsryNm      }"/>
    	</td>
  	</tr> 
  	<tr>
    	<th>
    		메모
    	</th>
    	<td>
			<TEXTAREA id="textArae" style="width:100%;height:100px;" readOnly><c:out value="${annvrsryManageVO.memo      }"/></TEXTAREA>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		D-day
    	</th>
    	<td>
    		&nbsp;D-<c:out value="${annvrsryManageVO.annvrsryBeginDe      }"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/*설명 : 기념일 목록 조회 */
function fn_aram_list() {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsry.do";
    varForm.submit();       
}

</script>

</body>
</html>
