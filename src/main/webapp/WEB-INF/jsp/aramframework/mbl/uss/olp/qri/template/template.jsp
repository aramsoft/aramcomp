<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script type="text/javascript">
<!--
	
$(function() {
	$('input[type="checkbox"]').click(function(){
		var id = $(this).attr('value');
		var name = $(this).attr('name');

		var checkbox = $('input[id="' + name + '"]:checked');
		var max = $('#MXMM_' + name).val();

		if($(checkbox).size()> max) {
			jAlert("최대선택건수 [" + max + "]건을 초과하였습니다.", "알림", "a");
			$(this).attr('checked', false);
		}

	});
});
-->
</script>
<dl class="uss-qustnRes">
<c:forEach items="${comtnqustnrqesitm}" var="qestmInfo" varStatus="status1">

	<dt>
		${status1.count}. <c:out value="${fn:replace(qestmInfo.qestnCn , crlf , '<br/>')}" escapeXml="false" /> 
		<c:if test="${qestmInfo.mxmmChoiseCo>  1}"><p class="uss-hitRed"><b>(최대선택건수 ${qestmInfo.mxmmChoiseCo})</b></p></c:if>
	</dt>
	
	<input type="hidden" id="ID_${qestmInfo.qestnrQesitmId}" name="ID_${qestmInfo.qestnrQesitmId}" value="${qestmInfo.qustnrIemId}" class="idHidden" />
	<c:if test="${qestmInfo.qestnTyCode ==  '1'}">
		<c:forEach items="${comtnqustnriem}" var="qestmItem" varStatus="status2">
			<c:if test="${qestmInfo.qestnrTmplatId eq qestmItem.qestnrTmplatId && qestmInfo.qestnrId eq qestmItem.qestnrId && qestmInfo.qestnrQesitmId eq qestmItem.qestnrQesitmId}">
				<dd>
				<%-- 다중체크구현 로직 start --%>
	    		<c:if test="${qestmInfo.mxmmChoiseCo ==  '1'}">	
	    			<input type="radio" name="${qestmItem.qestnrQesitmId}" value="${qestmItem.qustnrIemId}" id="${qestmItem.qestnrQesitmId}" data-role="none" /> 
	    			<c:out value="${fn:replace(qestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" />
	    		</c:if>
	    		
	    		<c:if test="${qestmInfo.mxmmChoiseCo>  1}">		    			
	    			<input type="checkbox" name="${qestmItem.qestnrQesitmId}" value="${qestmItem.qustnrIemId}" id="${qestmItem.qestnrQesitmId}" data-role="none" />
	    			<c:out value="${fn:replace(qestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" />
	    		</c:if>
	    		<%-- 다중체크구현 로직 end --%>
	    		
	    		<%-- 기타답변여부 start --%>
	    		<c:if test="${qestmItem.etcAnswerAt eq  'Y'}">
    				<input name="ETC_${qestmItem.qustnrIemId}" id="ETC_${qestmItem.qustnrIemId}" type="text" size="73" value="" maxlength="1000">
    			</c:if>
    			<c:if test="${qestmItem.etcAnswerAt eq  'N' || qestmItem.etcAnswerAt eq ''}">
	    			<input name="ETC_${QestmItem.qustnrIemId}" id="ETC_${QestmItem.qustnrIemId}" type="hidden" size="73" value="" maxlength="1000">
	    		</c:if>
    			<%-- 기타답변여부 end --%>
    			<input type="hidden" name="AT_${qestmItem.qustnrIemId}" id="AT_${qestmItem.qustnrIemId}" value="${qestmItem.etcAnswerAt}">
    			</dd>
			</c:if>
		</c:forEach>
	</c:if>
	
	<%-- 주관식 --%>
  		<c:if test="${qestmInfo.qestnTyCode ==  '2'}">
  			<textarea name="${qestmInfo.qestnrQesitmId}" id="${qestmInfo.qestnrQesitmId}" class="textarea"  cols="75" rows="4"></textarea>
  		</c:if>

	<%-- 최대선택 건수 --%>
	<input type="hidden" name="MXMM_${qestmInfo.qestnrQesitmId}" id="MXMM_${qestmInfo.qestnrQesitmId}" value="${qestmInfo.mxmmChoiseCo}">
	<%-- 객관식/주관식  타입 --%>
	<input type="hidden" name="TY_${qestmInfo.qestnrQesitmId}" id="TY_${qestmInfo.qestnrQesitmId}" value="${qestmInfo.qestnTyCode}">
	
</c:forEach>
</dl>
