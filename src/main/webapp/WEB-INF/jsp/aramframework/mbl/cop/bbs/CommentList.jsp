<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
<div class="com-commentT">댓글 <span><c:out value="${resultCnt}"/></span></div>

<c:forEach var="result" items="${resultList}" varStatus="status">        
	<div class="com-commentList">
		<p>	
			<c:choose>
		   	<c:when test="${not empty result.wrterNm}">
		       <b><c:out value="${result.wrterNm}" /></b>&nbsp;
		   	</c:when>
		   	<c:otherwise>
		       <b><c:out value="${result.frstRegisterNm}" /></b>&nbsp;
		   	</c:otherwise>
			</c:choose>
		</p>
        <p class="com-date">
            <fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </p>
        <p class="com-delete">
            <a href="javascript:fn_aram_delete_comment('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />'); " data-ajax="false"><span>삭제</span></a>
        </p>
        <p class="com-modify">
         	<a href="javascript:fn_aram_edit_comment('<c:out value="${result.commentNo}" />','<c:out value="${result.wrterNm}" />', '<c:out value="${result.frstRegisterNm}" />', '<c:out value="${result.commentCn}" />', '<c:out value="${status.index}" />');" data-ajax="false"><span>수정</span></a>
        </p>
		<c:if test="${anonymous == 'true'}">
	    <dl class="com-egovCommentPw">
	    	<dt>비밀번호</dt>
	    	<dd><input name="testPassword" type="password" size="20" value="" maxlength="20"></dd> 
	    </dl>
    	</c:if>
        <div class="com-commContent"><c:out value="${result.commentCn}" /></div>
	</div>          
</c:forEach>
    
