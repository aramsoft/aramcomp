<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : StplatCnfirm.jsp
  * @Description : 약관 확인
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
	<h2>약관 확인</h2>
</div>

<div style="margin-top:10px; width:100%"></div>
	
<form name="stplatForm" action="${pageContext.request.contextPath}/sec/rnc/confirmRlnm.do" method="post">
<input type=hidden name="curTrgetId" value="${curTrgetId}" />
<input type=hidden name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="sbscrbTy" value="${sbscrbTy}"/>
<!-- 실명인증의 기본옵션은 주민번호 실명확인임 : 주민번호 실명인증 으로 가기위한 초기화값 -->
<input type="hidden" name="ihidnum" value=""/>
<input type="hidden" name="realname" value=""/>
<!-- 실명인증후 다음단계에 대한 셋팅정보 -->
<input type="hidden" name ="nextUrlName" value="button.subscribe"/>
<input type="hidden" name ="nextUrl" value=
	<c:if test="${sbscrbTy == 'GNR'}">"/uss/umt/sbscrbMberView.do"</c:if>
	<c:if test="${sbscrbTy == 'ENT'}">"/uss/umt/sbscrbEntrprsMberView.do"</c:if>
	<c:if test="${empty sbscrbTy}">""</c:if>
/>

<table class="table-list">
 	<tr>
 		<th>약관내용</th>
 	</tr>
    <tr>
        <td>
        	<textarea id="useStplatCn" cols="90" rows="15">
<c:out value="${stplatVO.useStplatCn}" escapeXml="false" />
        	</textarea>
        </td>
    </tr>
    <tr>
        <td>
            <input name="checkField" type="checkbox" title="약관내용">약관내용에 동의합니다.
            <input name="checkuseStplatCn" type="hidden" value="<c:out value='${stplatVO.useStplatId}'/>">
        </td>
    </tr>
</table>

<table class="table-list">
    <tr>
    	<th>정보동의내용</th>
    </tr>
    <tr>
        <td>
        	<textarea id="infoProvdAgreCn" cols="90" rows="15">
<c:out value="${stplatVO.infoProvdAgreCn}" escapeXml="false" />
        	</textarea>
        	<!-- <c:out value="${fn:replace(result.infoProvdAgreCn , crlf , '<br/>')}" escapeXml="false" /> -->
        </td>
    </tr>
    <tr>
        <td>
            <input name="checkField" title="정보동의내용"  type="checkbox">정보이용내용에 동의합니다.
            <input name="checkinfoProvdAgeCn" type="hidden" value="<c:out value='${stplatVO.useStplatId}'/>">
        </td>
    </tr>
</table>    

<div style="margin-top:10px; width:100%"></div>
	
<table>
   	<tr>
		<td width="250">&nbsp;</td>
       	<!-- 동의 -->
       	<td><span class="button"><a href="#" onclick="javascript:fnAgree(); return false;"><spring:message code="button.agree" /></a></span>&nbsp;</td>
        <!-- 비동의 -->
        <td><span class="button"><a href="#" onclick="javascript:fnDisAgree(); return false;"><spring:message code="button.disagree" /></a></span>&nbsp;</td>
   	</tr>
</table>

</form>

</DIV>


<script type="text/javascript">

function fnAgree(){
	var checkField = document.stplatForm.checkField;
    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                }else{
                    alert("약관에 동의하지 않으면 회원으로 가입할 수 없습니다.");
                    checkField[i].focus();
                    return;
                }
            }
        } else {
            if(checkField.checked) {
            }else{
            	alert("약관에 동의하지 않으면 회원으로 가입할 수 없습니다.");
                checkField[i].focus();
                return;
            }
        }
    }
    
    //실명인증시 기본페이지는 주민번호 실명확인으로 한다.
    //document.stplatForm.action = "${pageContext.request.contextPath}/sec/rnc/confirmRlnm.do";

    //실명인증 통과
    document.stplatForm.action = document.stplatForm.nextUrl.value;
    document.stplatForm.submit();
}

function fnDisAgree(){
	alert("약관에 동의하지 않으면 회원가입을 하실수 없습니다.");
	return;
}

</script>


