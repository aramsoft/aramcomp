<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : IndvdlpgeDetail.jsp
 * @Description : 마이페이지 상세조회
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
	<h2>마이페이지 상세조회</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;" >마이페이지 설정 변경</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;" >컨텐츠 추가</a></span>
 	</div>
</div>

<table style="clear:both;" summary="마이페이지를 조회한다.">
<tbody>
  	<tr valign="top">
    	<td width="700">
		<!-- 가로배열 처리 -->
		<c:if test="${indvdlPgeConfVO.sortMthd eq 'R'}">
		<table >
    		<tr valign="top">
    			<c:set var="namuzi" value="${indvdlPgeDetailListCount%indvdlPgeConfVO.sortCnt}"/><!-- 나머지 -->
    			<c:set var="startwith" value="0"/>
    			<c:set var="endwith" value="${indvdlPgeConfVO.sortLineCnt-1}"/>
    			<c:choose>
        		<c:when test="${indvdlPgeDetailListCount < indvdlPgeConfVO.sortCnt}">
        			<c:set var="i" value="${indvdlPgeDetailListCount}"/>
        		</c:when>
        		<c:otherwise>
        			<c:set var="i" value="${indvdlPgeConfVO.sortCnt}"/>
        		</c:otherwise>
    			</c:choose>
    			<c:forEach var="td" begin="1" end="${i}" step="1">
       			<td>
            	<table>
            		<c:forEach var="indvdlPgeDetail" items="${indvdlPgeDetailList}" begin="${startwith}" end="${endwith}" step="1">
                	<tr>
                		<td width="300" align="center" valign="middle">
                    	<table  style="border:${indvdlPgeConfVO.titleBarColor} 2px solid">
                        	<tr>
                            	<td bgcolor="${indvdlPgeConfVO.titleBarColor}" height="30px">&nbsp;<c:out value="${indvdlPgeDetail.cntntsNm}"/></td> 
                            	<td bgcolor="${indvdlPgeConfVO.titleBarColor}" height="30px" align="right" valign="middle"><a href="<c:out value="${indvdlPgeDetail.cntntsLinkUrl}" />">more..</a><input type="image" src="${pageContext.request.contextPath}/images/uss/mpe/icon/bu5_close.gif" alt="삭제" onClick="javascript:fn_aram_delete('<c:out value="${indvdlPgeDetail.cntntsId}" />');"></td> 
                        	</tr>
                        	<tr> 
                            	<td colspan="2" valign="top" >
	        					<iframe width="300" name="preN<c:out value='${indvdlPgeDetail.cntntsId}'/>" id="preI<c:out value='${indvdlPgeDetail.cntntsId}'/>" title="마이페이지 컨텐츠 미리보기"  src = "${pageContext.request.contextPath}${indvdlPgeDetail.cntcUrl}'/>" seamless="seamless" ></iframe>               
                             	</td>
                        	</tr>
                    	</table>
                		</td>
                	</tr>
            		</c:forEach>
            		<c:set var="startwith" value="${endwith+1}"/>
                	<c:choose>
                    <c:when test="${td eq namuzi}">
                    	<c:set var="endwith" value="${endwith+indvdlPgeConfVO.sortLineCnt-1}"/>
                    </c:when>
                    <c:when test="${td> namuzi && namuzi != 0}">
                    	<c:set var="endwith" value="${endwith+indvdlPgeConfVO.sortLineCnt-1}"/>
                    </c:when>
                    <c:otherwise>
                    	<c:set var="endwith" value="${endwith+indvdlPgeConfVO.sortLineCnt}"/>
                    </c:otherwise>  
                	</c:choose>
            	</table>
       			</td>
    			</c:forEach> 
    		</tr>
		</table>
		</c:if>
		<!-- 세로배열 처리 -->
		<c:if test="${indvdlPgeConfVO.sortMthd eq 'C'}">
		<table >
	    	<tr>
	    		<c:forEach var="tr" begin="1" end="${indvdlPgeConfVO.sortLineCnt}" step="1"> 
	      		<td width="300" align="center" valign="top">
	      		<table >
	        		<c:forEach var="indvdlPgeDetail" items="${indvdlPgeDetailList}" begin="${(tr-1)*indvdlPgeConfVO.sortCnt}" end="${(tr)*indvdlPgeConfVO.sortCnt-1}" step="1">
	          		<tr>
	            		<td width="300" align="center" valign="middle">
	            		<table style="border:${indvdlPgeConfVO.titleBarColor} 2px solid">
	              			<tr>
	                  			<td bgcolor="${indvdlPgeConfVO.titleBarColor}" height="30px">&nbsp;<c:out value="${indvdlPgeDetail.cntntsNm}"/></td> 
	                  			<td bgcolor="${indvdlPgeConfVO.titleBarColor}" height="30px" align="right"><a href="<c:out value="${indvdlPgeDetail.cntntsLinkUrl}" />">more..</a>&nbsp;<input type="image" src="${pageContext.request.contextPath}/images/uss/mpe/icon/action_delete.gif" alt="삭제" onClick="javascript:delIndvdlCntnts('<c:out value="${indvdlPgeDetail.cntntsId}" />');"></td> 
	              			</tr>              
	              			<tr>
                           		<td colspan="2" valign="top" >
	        					<iframe width="300" name="preN<c:out value='${indvdlPgeDetail.cntntsId}'/>" id="preI<c:out value='${indvdlPgeDetail.cntntsId}'/>" title="마이페이지 컨텐츠 미리보기"  src = "${pageContext.request.contextPath}${indvdlPgeDetail.cntcUrl}'/>" seamless="seamless" ></iframe>               
                             	</td>
	              			</tr>
	            		</table>
	            		</td>
	          		</tr>
	        		</c:forEach>
	      		</table>
	      		</td>
	    		</c:forEach> 
	    	</tr>
		</table>   
		</c:if>
		</td>
	</tr>
</tbody>
</table>

<form:form modelAttribute="indvdlPgeConfVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="cntntsId" value="">
</form:form>

</DIV>


<script type="text/javascript">

/* ********************************************************
 * 마이페이지 보기에서 불필요한 컨텐츠 마이페이지에서 삭제하기
 ******************************************************** */
function fn_aram_delete(id){
	var varForm = document.getElementById("indvdlPgeConfVO");
	
	if(confirm("<spring:message code='common.delete.msg'/>")){
	    varForm.cntntsId.value = id;
	    varForm.action = "${pageContext.request.contextPath}/uss/mpe/deleteIndvdlpgeCntntsMine.do";
	    varForm.submit();
	}
}

function fn_aram_regist(){
	var varForm = document.getElementById("indvdlPgeConfVO");
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/registIndvdlpgeConf.do";
    varForm.submit();
}

function fn_aram_list(){
	var varForm = document.getElementById("indvdlPgeConfVO");
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/listIndvdlpgeCntntsMine.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80";	
	window.open(url, "도움말");
}

</script>

