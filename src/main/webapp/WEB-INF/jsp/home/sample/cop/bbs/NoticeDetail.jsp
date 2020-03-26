<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NoticeDetail.jsp
  * @Description : 게시물 조회 화면
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
				<h2><strong>글조회</strong></h2>
			</div>
			<!-- //main content 끝 -->			
            
			<form:form modelAttribute="boardVO"  method="post" action="">
				<input type="hidden" name="menuNo" value="${menuNo}"/>
				<form:hidden path="bbsId" />
				<form:hidden path="nttId" />

                <div class="modify_user">
                    <table>
                        <tr> 
					        <th width="15%">제목</th>
					        <td width="85%" colspan="5">
					        	<c:out value="${boardVO.nttSj}" />
					        </td>
					    </tr>
					    <tr> 
					        <th width="15%">작성자</th>
					        <td width="15%">
					            <c:out value="${boardVO.frstRegisterNm}" />
					            <!-- 
						        <c:choose>
						            <c:when test="${anonymous == 'true'}">
						                ******
						            </c:when>
						            <c:when test="${result.ntcrNm == ''}">
						                <c:out value="${result.frstRegisterNm}" />
						            </c:when>
						            <c:otherwise>
						                <c:out value="${result.ntcrNm}" />
						            </c:otherwise>
						        </c:choose>
					            -->
					        </td>
					        <th width="15%">작성시간</th>
					        <td width="15%">
					        	<fmt:formatDate value="${boardVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
					        </td>
					        <th width="15%">조회수</th>
					        <td width="15%">
					        	<c:out value="${boardVO.rdcnt}" />
					        </td>
					    </tr>    
					    <tr> 
					        <th>글내용</th>
					        <td colspan="5">
					         	<div class="bbs_cn">
					           	<textarea id="nttCn" name="nttCn"  cols="75" rows="20"  style="width:100%" readonly="readonly" title="글내용"><c:out value="${boardVO.nttCn}" escapeXml="true" /></textarea>
					         	</div>
					        </td>
					    </tr>
					    <c:if test="${not empty boardVO.atchFileId}">
					        <c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA02'}">
					        <tr> 
					            <th>첨부이미지</th>
					            <td colspan="5">
									<c:import url="/content/imagefiles/${boardVO.atchFileId}" />
					            </td>
					        </tr>
					        </c:if>
					        <tr> 
					            <th>첨부파일 목록</th>
					            <td colspan="5">
									<c:import url="/content/files/${boardVO.atchFileId}" />
					            </td>
					        </tr>
					    </c:if>
					    <c:if test="${anonymous == 'true'}">
					    <tr> 
					        <th><label for="password"><spring:message code="cop.password" /></label></th>
					        <td colspan="5">
					            <input name="password" title="암호" type="password" size="20" value="" maxlength="20">
					        </td>
					    </tr>
					    </c:if>   
                    </table>
                </div>

                <!-- 버튼 시작(상세지정 style로 div에 지정) -->
                <div class="buttons buttons_padding">
                    <!-- 목록/저장버튼  -->
                 	<div class="buttons_center">
  					  <c:if test="${editAuthFlag == 'Y'}">
    					<c:if test="${boardVO.frstRegisterId == sessionUniqId}">
                    		<a href="#" onclick="javascript:fn_aram_edit(); return false;">수정</a> 
                    		<a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a> 
             			</c:if>    
             			<c:if test="${boardVO.boardMasterVO.replyPosblAt == 'Y'}">     
                    		<a href="#" onclick="javascript:fn_aram_reply(); return false;">답글작성</a> 
              			</c:if>
  					  </c:if>
	                    <a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a> 
  					</div>
                 </div>
                <!-- 버튼 끝 -->                           
			 
			 	<!-- 검색조건 유지 -->
				<form:hidden path="searchCondition" />
				<form:hidden path="searchKeyword" />
				<form:hidden path="pageIndex" />
				<form:hidden path="recordPerPage" />
				<!-- 검색조건 유지 -->
			</form:form>

        </div>  

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("boardVO");
	var bbsId = varForm.bbsId.value;
	
    varForm.action = "${pageContext.request.contextPath}/home/sample/board/"+bbsId+"/list";
    varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("boardVO");
	if ("<c:out value='${anonymous}'/>" == "true" && varForm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		varForm.password.focus();
		return;
	}

	varForm.action = "${pageContext.request.contextPath}/home/sample/editBoardArticle.do";
	varForm.submit();
}

function fn_aram_delete() {
    var varForm = document.getElementById("boardVO");
	if ("<c:out value='${anonymous}'/>" == "true" && varForm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		varForm.password.focus();
		return;
	}

	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/home/sample/deleteBoardArticle.do";
		varForm.submit();
	}
}

function fn_aram_reply() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/home/sample/replyBoardArticle.do";
    varForm.submit();
}

</script>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useComment == 'true'}">
<c:import url="/home/sample/listComment.do" charEncoding="utf-8">
    <c:param name="type" value="head" />
</c:import>
</c:if>
<c:if test="${useSatisfaction == 'true'}">
<c:import url="/home/sample/listSatisfaction.do" charEncoding="utf-8">
    <c:param name="type" value="head" />
</c:import>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->

