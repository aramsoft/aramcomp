<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CnsltAnswerDetail.jsp
  * @Description : 상담답변 상세조회
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
	<h2>상담답변 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;">답변</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cnsltManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cnsltId" />

<input name="writngPassword" 	type="hidden" value="">

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="이 표는 상담답변 정보를 제공하며, 작성자명, 전화, 휴대폰전화번호, 이메일, 작성일자, 조회횟수, 처리상태, 상담제목, 상담내용 정보 등으로 구성되어 있습니다 .">
<caption>상담답변 상세조회</caption>  
  	<tr> 
    	<th width="20%">
    		작성자명
    	</th>
    	<td width="80%">
    		<c:out value="${cnsltManageVO.wrterNm}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		전화
    	</th>
    	<td>    
    		<c:if test="${cnsltManageVO.areaNo != null}">    
      		<c:out value="${cnsltManageVO.areaNo}"/>-<c:out value="${cnsltManageVO.middleTelno}"/>-<c:out value="${cnsltManageVO.endTelno}"/>
			</c:if>                            
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		휴대폰전화번호
     	</th>
    	<td>    
      		<c:if test="${cnsltManageVO.firstMoblphonNo != null}">
      		<c:out value="${cnsltManageVO.firstMoblphonNo}"/>-<c:out value="${cnsltManageVO.middleMbtlnum}"/>-<c:out value="${cnsltManageVO.endMbtlnum}"/>
			</c:if>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		이메일
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.emailAdres}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="emailAnswerAt" type="checkbox"  disabled <c:if test="${cnsltManageVO.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부  
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		작성일자
    	</th>
    	<td>    	
    		<c:out value="${cnsltManageVO.writngDe}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		조회횟수
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.inqireCo}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		처리상태
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.qnaProcessSttusCodeNm}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		상담제목
    	</th>
    	<td>    
      		<c:out value="${cnsltManageVO.cnsltSj}"/>                 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		<label for="cnsltCn">상담내용</label>
    	</th>
    	<td>    
      		<textarea name="cnsltCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="상담내용"><c:out value="${cnsltManageVO.cnsltCn}"/>
      		</textarea>                       
    	</td>
  	</tr> 
	<!--답변내용이 있을경우 Display... -->
	<c:if test="${cnsltManageVO.qnaProcessSttusCode == '3'}">
  	<tr> 
    	<th>
    		<label for="managtCn">답변내용</label>
    	</th>
    	<td>    
      		<textarea name="managtCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="답변내용"><c:out value="${cnsltManageVO.managtCn}"/>
      		</textarea>                                        
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		담당부서
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.orgnztNm}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		답변일자
    	</th>
    	<td>
  			<c:if test="${cnsltManageVO.managtDe != null}"> 
  			<c:out value="${cnsltManageVO.managtDe}"/>      			   	          				 			   
 			</c:if>
    	</td>
  	</tr>
  	<tr> 
    	<th>
      		답변자
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.emplyrNm}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		전화번호
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.offmTelno}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		이메일
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.aemailAdres}"/>
    	</td>
  	</tr>
  	</c:if>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cnm/listCnsltAnswer.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("cnsltManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/olp/cnm/editCnsltAnswer.do";
	varForm.submit();	
}

</script>

