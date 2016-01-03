<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : GpkiTest.jsp
  * @Description : GPKI(Goverment Public Key Infrastructure) 테스트를 위한 화면
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
<title>GPKI</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">

<script type="text/javascript">

function process(cmd) {
	var frm = document.frm;

	frm.cmd.value = cmd;

	if (cmd == 'encrypt' || cmd == 'sign') {
		if (frm.source.value == '') {
			alert('원본을 입력해 주세요.');
			frm.source.focus();
			return;
		}
	} else if (cmd == 'decrypt' || cmd == 'verify') {
		if (frm.result.value == '') {
			alert('결과을 입력해 주세요.');
			frm.result.focus();
			return;
		}
	}

	frm.submit();
}

</script>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<form name="frm" action ="${pageContext.request.contextPath}/sec/pki/gpkiTest.do" method="post">
<input type="hidden" name="cmd" value="">

<table border="1" summary="GPKI를 통한 암복호화 및 전자서명 처리를 수행한다.">
<tbody>
	<tr>
		<td>
		    <label for="target">대상서버ID</label>
		</td>
		<td>
		   <input name="target" id="target" class="input" value='<c:out value="${target}" />'>
		   <br>
		   ex)서버인증서 CN이 "cn=SVR1310000001" 이면 "1310000001" 지정
		   <br>
		   	미지정이면 현 시스템 설정 값 사용
		</td>
	</tr>
	<tr>
		<td>
		    <label for="source">원본</label>
		</td>
		<td>
		   <textarea name="source" id="source" class="textarea" cols="75" rows="4" title="원본"><c:out value="${source}"/></textarea>
		</td>
	</tr>
<c:choose>
<c:when test="${cmd == 'encrypt'}">
	<tr>
		<td colspan="2" align="center">
		    <input type="button" value="복호화!" onclick="process('decrypt'); return false;">
		</td>
	</tr>
</c:when>
<c:when test="${cmd == 'sign'}">
	<tr>
		<td colspan="2" align="center">
		    <input type="button" value="전자서명확인!" onclick="process('verify'); return false;">
		</td>
	</tr>
</c:when>
<c:otherwise>
	<tr>
		<td colspan="2" align="center">
		    <input type="button" value="암호화!" onclick="process('encrypt'); return false;">
		    &nbsp;&nbsp;
		    <input type="button" value="전자서명!" onclick="process('sign'); return false;">
		</td>
	</tr>
</c:otherwise>
</c:choose>
	<tr>
		<td>
		    결과
		</td>
		<td>
		   <textarea name="result" class="textarea" cols="75" rows="4" title="결과"><c:out value="${result}" /></textarea>
		</td>
	</tr>
</tbody>
</table>
</form>

</DIV>
</body>
</html>
