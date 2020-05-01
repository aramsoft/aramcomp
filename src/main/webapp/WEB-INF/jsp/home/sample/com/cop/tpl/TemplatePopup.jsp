<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TemplatePopup.jsp
  * @Description : 템플릿 목록 조회 팝업화면
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
<title>템플릿 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home/sample/common.css" type="text/css">

</head>

<body>
<form name="frm" action ="${pageContext.request.contextPath}/cop/com/selectTemplateInfsPop.do" method="post">
    <input type="hidden" name="tmplatId" value="" />
    <input type="submit" id="invisible" class="invisible"/>

    <!-- 검색 필드 박스 시작 -->
    <div id="search_field">
        <div id="search_field_loc"><h2><strong>템플릿 목록</strong></h2></div>
        <fieldset><legend>조건정보 영역</legend>    
        <div class="sf_start">
            <ul id="search_first_ul">
                <li>
                    <label for="search_select"></label>
 					<select name="searchCnd" class="select" title="검색조건 선택">
    					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if>>템플릿명</option>
    					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if>>템플릿구분</option>   
                   	</select>
                </li>
                <li>
                    <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력"> 
                </li>       
            </ul>
            <ul id="search_second_ul">
                <li>
                    <div class="buttons">
                        <a href="${pageContext.request.contextPath}/cop/com/selectTemplateInfsPop.do" onclick="javascript:fn_aram_search(); return false;"><img src="/images/home/sample/img_search.gif" alt="search" />조회 </a>
 						<a href="#" onclick="javascript:parent.close(); return false;">닫기</a>
                    </div>                              
                </li>
            </ul>           
        </div>          
        </fieldset>
    </div>
    <!-- //검색 필드 박스 끝 -->

    <div id="page_info"><div id="page_info_align"></div></div>                    
    <!-- table add start -->
    <div class="default_tablestyle">
    <table summary="번호, 템플릿명, 템플릿구분, 템플릿경로, 사용여부, 등록일자, 선택   목록입니다">
        <caption>사용자목록관리</caption>
        <colgroup>
        <col width="5%">
        <col width="15%">  
        <col width="10%">
        <col width="37%">
        <col width="5%">
        <col width="10%">
        <col width="8%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col" class="f_field">번호</th>
            <th scope="col">템플릿명</th>
            <th scope="col">템플릿구분</th>
            <th scope="col">템플릿경로</th>
            <th scope="col">사용여부</th>
            <th scope="col">등록일자</th>
            <th scope="col">선택</th>
        </tr>
        </thead>
        <tbody>                 

        <c:forEach var="result" items="${resultList}" varStatus="status">
        <!-- loop 시작 -->                                
			<tr>
			   	<td><strong><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></strong></td>          
			   	<td><c:out value="${result.tmplatNm}"/></td>
			   	<td><c:out value="${result.tmplatSeCodeNm}"/></td>
			   	<td><c:out value="${result.tmplatCours}"/></td>
			   	<td>
			       	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
			       	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
			   	</td>  
			   	<td><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>     
			   	<td>
			       	<c:if test="${result.useAt == 'Y'}">
			           <input type="button" name="selectTmplat" value="선택" 
			               onClick="javascript:fn_aram_choose('<c:out value="${result.tmplatId}"/>','<c:out value="${result.tmplatNm}"/>')" />
			       	</c:if>         
			   	</td>  
			</tr>
        </c:forEach>     
        <c:if test="${fn:length(resultList) == 0}">
	       	<tr>
	           	<td  colspan="6"><spring:message code="common.nodata.msg" /></td>  
	       	</tr>      
	    </c:if>
        </tbody>
        </table>
    </div>

    <!-- 페이지 네비게이션 시작 -->
    <div id="paging_div">
        <ul class="paging_align">
           <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
        </ul>
    </div>                          
    <!-- //페이지 네비게이션 끝 -->  
    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplatePopup.do";
	document.frm.submit();	
}

function fn_aram_search(){
	document.frm.pageIndex.value = '1';
	document.frm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplatePopup.do";
	document.frm.submit();	
}

function fn_aram_choose(tmplatId, tmplatNm){
	var retVal = tmplatId +"|"+tmplatNm;
	parent.fn_aram_returnValue(retVal);
}

</script>

</body>
</html>