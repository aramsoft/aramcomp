<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="aramframework.cmm.constant.AramProperties" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NoticeList.jsp
  * @Description : 게시물 목록화면
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
                    <li>알림마당</li>
                    <li>&gt;</li>
                    <li><strong>${boardVO.boardMasterVO.bbsNm}</strong></li>
                </ul>
        	</div>
        	
			<!-- main content 시작 -->
			<div class="content_field">
				<h2><c:out value='${boardVO.boardMasterVO.bbsNm}'/></h2>
			</div>
			<!-- //main content 끝 -->			

            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
				<form:form modelAttribute="boardVO" action ="" method="post">
					<form:hidden path="pathId" />
					<input type="hidden" name="nttId" value="0"/>
					<input type="hidden" name="menuNo" value="${menuNo}"/>
					
                    <fieldset>
                    <legend>조건정보 영역</legend>
                        
                    <div class="sf_start">
                        <ul id="search_first_ul">
                            <li>
								<form:select path="searchCondition" title="검색조건선택">
							   		<form:option value="NTT_SJ" label="제목" />
							   		<form:option value="NTT_CN" label="내용" />
							   		<form:option value="USER_NM" label="작성자" />
								</form:select>
		   						<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
								<form:select path="recordPerPage" onchange="javascript:fn_aram_search(); return false;">
							   		<form:option value="10" label="10" />
							   		<form:option value="20" label="20" />
							   		<form:option value="30" label="30" />
							   		<form:option value="50" label="50" />
								</form:select>
                            </li>       
                        </ul>
                        <ul id="search_second_ul">
                           <li>
		                        <div class="buttons" >
		                           	<div class="buttons_center">
		                                <a href="#" onclick="javascript:fn_aram_search(); return false;"><img src="${pageContext.request.contextPath}/images/home/img_search.gif" alt="search" />조회 </a>
		                            	<a href="#" onclick="javascript:fn_aram_regist(); return false;">등록</a>
		                        	</div>
		                        </div>                              
                            </li>
                        </ul>           
                    </div>
					<form:hidden path="pageIndex" />
                    </fieldset>
				</form:form>
            </div>
            <!-- //검색 필드 박스 끝 -->

           	<!-- 
            <div id="page_info"><div id="page_info_align"></div></div>                    
            -->
            
            <!-- table add start -->
            <div class="default_tablestyle">
                <table summary="번호, 제목, 게시시작일, 게시종료일, 작성자, 작성일, 조회수   입니다" >
                <caption>게시물 목록</caption>
                <colgroup>
                    <col width="10%">
                    <col>  
                    <c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
	                    <col width="10%">
	                    <col width="10%">
				    </c:if>
				    <c:if test="${anonymous != 'true'}">
                        <col width="10%">
                    </c:if>
                    <col width="15%">
                    <col width="8%">
                </colgroup>
                
                <thead>
                <tr>
                    <th scope="col" class="f_field">번호</th>
                    <th scope="col">제목</th>
                    <c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
                     	<th scope="col">게시시작일</th>
                     	<th scope="col">게시종료일</th>
                 	</c:if>
                 	<c:if test="${anonymous != 'true'}">
                     	<th scope="col">작성자</th>
                 	</c:if>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                </tr>
                </thead>
                
                <tbody>                 
					<c:if test="${fn:length(resultList) == 0}">
				  	<tr>
				    	<c:set var="colNo" value="4" />
				   		<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
				    	<c:set var="colNo" value="${colNo + 2}" />
				  		</c:if>
				   		<c:if test="${anonymous != 'true'}">
				    	<c:set var="colNo" value="${colNo + 1}" />
				    	</c:if>
				 		<td class="lt_text3" colspan="${colNo}"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
	
  					<c:set var="startIndex" value="${(boardVO.pageIndex-1) * boardVO.recordPerPage}"/>
                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
                    <tr>
				 		<c:set var="index" value="${startIndex + status.count}"/>
						<c:set var="reverseIndex" value="${boardVO.totalRecordCount - index + 1}"/>
				        <td><b><c:out value="${reverseIndex}"/></b></td>            

				        <td align="left">
				            <c:if test="${result.threadDepth!=0}">
				                <c:forEach begin="0" end="${result.threadDepth}" step="1">
				                    &nbsp;
				                </c:forEach>
				                <img src="${pageContext.request.contextPath}/images/home/reply_arrow.gif" alt="reply arrow"/>
				            </c:if>
				            <c:choose>
				                <c:when test="${result.isExpired=='Y' || result.useAt == 'N'}">
				                    <c:out value="${result.nttSj}" />
				                </c:when>
				                <c:otherwise>
									<span class="link">
									<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.nttId}"/>'); return false;">
										<c:out value="${result.nttSj}"/>
									</a>
									</span>
				                </c:otherwise>
				            </c:choose>
				        </td>
				        <c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
				            <td><c:out value="${result.ntceBgnde}"/></td>
				            <td><c:out value="${result.ntceEndde}"/></td>
				        </c:if>
				        <c:if test="${anonymous != 'true'}">
				            <td><c:out value="${result.frstRegisterNm}"/></td>
				        </c:if>
				        <td><c:out value="${result.frstRegisterPnttm}"/></td>
				        <td><c:out value="${result.rdcnt}"/></td>
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

<c:if test="${preview == null || preview !='true'}">
<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
} 

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("boardVO");
	var pathId = varForm.pathId.value;
	
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/${jspPrefix}/board/"+pathId+"/list";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("boardVO");
	var pathId = varForm.pathId.value;
	
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/${jspPrefix}/board/"+pathId+"/list";
    varForm.submit();
}

function fn_aram_detail(nttId) {
    var varForm = document.getElementById("boardVO");
	var pathId = varForm.pathId.value;
   
    varForm.action = "${pageContext.request.contextPath}/${jspPrefix}/board/"+pathId+"/article/" + nttId;
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("boardVO");
    varForm.nttId.value = 0;
    varForm.action = "${pageContext.request.contextPath}/${jspPrefix}/cop/bbs/registBoardArticle.do";
    varForm.submit();
}

</script>
</c:if>

