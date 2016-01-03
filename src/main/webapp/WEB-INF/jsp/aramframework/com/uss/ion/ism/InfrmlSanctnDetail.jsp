<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : InfrmlSanctnDetail.java
 * @Description : 결재권자 상세조회
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

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>결재권자 상세조회</h2>
</div>
 
<table class="table-detail" summary="이 표는  약식결재 정보를 제공하며, 승인권자명, 소속 등의 정보로 구성되어 있습니다 .">
<caption>약식결재 상세보기</caption>
  	<tr>
    	<th width="20%">
    		승인권자명
    	</th>          
    	<td width="30%">
    		<c:out value='${infrmlSanctnVO.sanctnerNm}'/>
    	</td>
    	<th width="20%">
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${infrmlSanctnVO.sanctnerOrgnztNm}'/>
    	</td>
  	</tr>
  	<c:if test="${infrmlSanctnVO.confmAt ne 'A'}">
  	<tr>
    	<th>
     		승인여부
    	</th>          
    	<td> 
         	<c:if test="${infrmlSanctnVO.confmAt eq 'A'}">신청중</c:if>
         	<c:if test="${infrmlSanctnVO.confmAt eq 'C'}">승인</c:if>
         	<c:if test="${infrmlSanctnVO.confmAt eq 'R'}">반려</c:if>
    	</td>
    	<th>
     		승인일자
    	</th>          
    	<td>
    		<c:out value='${infrmlSanctnVO.sanctnDt}'/>
    	</td>
  	</tr> 
  	</c:if>
  	<c:if test="${infrmlSanctnVO.confmAt eq 'C'}">
  	<tr>
    	<th>
     		의견
    	</th>          
    	<td colspan="3"> 
			<c:out value='${infrmlSanctnVO.returnResn}'/>
    	</td>
  	</tr> 
  	</c:if>
  	<c:if test="${infrmlSanctnVO.confmAt eq 'R'}">
  	<tr>
    	<th width="20%">
     		반려사유
    	</th>          
    	<td colspan="3"> 
			<c:out value='${infrmlSanctnVO.returnResn}'/>
    	</td>
  	</tr> 
  	</c:if>
</table>
