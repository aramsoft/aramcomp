<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TemplateList.jsp
  * @Description : 템플릿 목록화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
        <div id="content">
            <div id="cur_loc">
                <ul>
                    <li>HOME</li>
                    <li>&gt;</li>
                    <li>사이트관리</li>
                    <li>&gt;</li>
                    <li><strong>게시판템플릿관리</strong></li>
                </ul>
            </div>
            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
                <div id="search_field_loc"><h2><strong>템플릿 목록</strong></h2></div>

				<form:form modelAttribute="templateInfVO" action ="" method="post">
					<input type="hidden" name="tmplatId" value="" />
                       
                    <fieldset><legend>조건정보 영역</legend>    
                    <div class="sf_start">
                        <ul id="search_first_ul">
                            <li>
						   		<form:select path="searchCondition" class="select" title="검색조건선택">
								   <form:option value="TMPLAT_NM" label="템플릿명" />
								   <form:option value="TMPLAT_SE_CODE" label="템플릿구분" />
							   	</form:select>
                            </li>
                            <li>
   								<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
                            </li>       
                            <li>
								<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;">
							   		<form:option value="10" label="10" />
							   		<form:option value="20" label="20" />
							   		<form:option value="30" label="30" />
							   		<form:option value="50" label="50" />
								</form:select>
                            </li>
                        </ul>
                        <ul id="search_second_ul">
                            <li>
                               	<div class="buttons">
                                    <a href="#" onclick="javascript:fn_aram_search(); return false;"><img src="${pageContext.request.contextPath}/images/home/sample/img_search.gif" alt="search" />조회 </a>
                                    <a href="#" onclick="javascript:fn_aram_regist(); return false;">등록</a>
                                </div>                              
                            </li>
                        </ul>           
                    </div>          
                    </fieldset>
				<form:hidden path="pageIndex" />
				</form:form>
            </div>
            <!-- //검색 필드 박스 끝 -->

            <!-- div id="page_info"><div id="page_info_align">총 <strong>321</strong>건 (<strong>1</strong> / 12 page)</div></div-->                    
            <!-- table add start -->
            <div class="default_tablestyle">
                <table summary="번호,게시판명,사용 커뮤니티 명,사용 동호회 명,등록일시,사용여부   목록입니다" >
                    <caption>게시판 템플릿 목록</caption>
                    <colgroup>
                    <col width="10%">
                    <col width="15%">  
                    <col width="10%">
                    <col width="32%">
                    <col width="10%">
                    <col width="10%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col" class="f_field">번호</th>
                        <th scope="col">템플릿명</th>
                        <th scope="col">템플릿구분</th>
                        <th scope="col">템플릿경로</th>
                        <th scope="col">사용여부</th>
                        <th scope="col">등록일자</th>
                    </tr>
                    </thead>
                    <tbody>                 

 			        <c:if test="${fn:length(resultList) == 0}">
			        <tr>
			            <td  colspan="6"><spring:message code="common.nodata.msg" /></td>  
			        </tr>      
			        </c:if>
                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
                    <tr>
			            <td><strong><c:out value="${(templateInfVO.pageIndex-1) * templateInfVO.pageSize + status.count}"/></strong></td>      
			                 
			            <td>
							<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.tmplatId}"/>'); return false;">
								<c:out value="${result.tmplatNm}"/>
							</a>
			            </td>
			
			            <td><c:out value="${result.tmplatSeCodeNm}"/></td>
			            <td><c:out value="${result.tmplatCours}"/></td>
			            <td>
			                <c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
			                <c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
			            </td>  
			            <td><c:out value="${result.frstRegistPnttm}"/></td>       
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
        </div>
        
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
    var varForm = document.getElementById("templateInfVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();  
}

function fn_aram_search(){
    var varForm = document.getElementById("templateInfVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();  
}

function fn_aram_detail(tmplatId){
    var varForm = document.getElementById("templateInfVO");
    varForm.tmplatId.value = tmplatId;
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/editTemplate.do";
    varForm.submit();          
}

function fn_aram_regist(){    
    var varForm = document.getElementById("templateInfVO");
    varForm.tmplatId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/registTemplate.do";
    varForm.submit();
}

</script>

