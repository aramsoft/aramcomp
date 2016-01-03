<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BoardMasterPopup.jsp
  * @Description : 게시판 속성 조회 팝업
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
<title>게시판 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home/sample/common.css" type="text/css">

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
    var varForm = document.getElementById("searchVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMasterPopup.do";
    varForm.submit();                  
}

function fn_aram_search(){
    var varForm = document.getElementById("searchVO");
    varForm.pageIndex.value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMasterPopup.do";
    varForm.submit();                  
}

function fn_aram_choose(bbsId, bbsNm){
    var retVal = bbsId +"|"+bbsNm;
    parent.fn_aram_returnValue(retVal);
}

</script>
<title>게시판 정보</title>

</head>

<body>

<form:form commandName="searchVO" action ="" method="post">
    <input type="submit" value="실행" onclick="fn_aram_search(); return false;" id="invisible" class="invisible" />

    <!-- 검색 필드 박스 시작 -->
    <div id="search_field">
        <div id="search_field_loc"><h2><strong>게시판 정보</strong></h2></div>
            <fieldset><legend>조건정보 영역</legend>    
            <div class="sf_start">
                <ul id="search_first_ul">
                    <li>
                        <label for="searchCnd">검색유형선력</label>
				   		<form:select path="searchCondition" class="select" title="검색조건선택">
						   	<form:option value="BBS_NM" label="게시판명" />
						   	<form:option value="BBS_TY_CODE" label="게시판유형" />
					   	</form:select>
                    </li>
                    <li>
                        <label for="searchWrd" class="invisible">검색어</label>
   						<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
                    </li>
                </ul>
                <ul id="search_second_ul">
                    <li>
                        <div class="buttons">
                            <a href="#" onclick="javascript:fn_aram_search(); return false;"><img src="/images/home/sample/img_search.gif" alt="search" />조회 </a>
               				<a href="#" onclick="javascript:parent.close(); return false;">닫기</a>
                         </div>                              
                    </li>   
                </ul>
            </div>          
            </fieldset>
    </div>
    <!-- //검색 필드 박스 끝 -->
<form:hidden path="pageIndex" />
</form:form>

    <!-- div id="page_info"><div id="page_info_align">총 <strong>321</strong>건 (<strong>1</strong> / 12 page)</div></div-->                    
    <!-- table add start -->
    <div class="default_tablestyle">
        <table>
        <caption>게시판정보</caption>
        <colgroup>
	        <col width="10%">  
	        <col width="36%">
	        <col width="10%">
	        <col width="10%">
	        <col width="15%">
	        <col width="8%">
	        <col width="8%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col" class="f_field">번호</th>
            <th scope="col">게시판명</th>
            <th scope="col">게시판유형</th>
            <th scope="col">게시판속성</th>
            <th scope="col">생성일</th>
            <th scope="col">사용여부</th>
            <th scope="col">선택</th>
        </tr>
        </thead>
        
        <tbody>                 
        <c:if test="${fn:length(resultList) == 0}">
	    <tr>
	        <td  colspan="7"><spring:message code="common.nodata.msg" /></td>  
	    </tr>      
        </c:if>

 		<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
        <c:forEach var="result" items="${resultList}" varStatus="status">
        <!-- loop 시작 -->                                
             <tr>
		 		<c:set var="index" value="${startIndex + status.count}"/>
				<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
	            <td><strong><c:out value="${reverseIndex}"/></strong></td>

	            <td>
	                <c:out value="${result.bbsNm}"/>
	            </td>
	            <td><c:out value="${result.bbsTyCodeNm}"/></td>
	            <td><c:out value="${result.bbsAttrbCodeNm}"/></td>
	            <td><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	            <td>
	                <c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	                <c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	            </td>
	            <td>
                    <input type="button" value="선택"  
                        onClick="javascript:fn_aram_choose('<c:out value="${result.bbsId}"/>','<c:out value="${result.bbsNm}"/>');" />
	            </td>
	          </tr>
         </c:forEach>     
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

</body>
</html>