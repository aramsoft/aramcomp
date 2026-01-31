<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ModalPopupFrame.jsp
  * @Description : 모달 팝업을 위한 외부 프레임
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<title>선택 목록</title>

<script type="text/javascript">

function fn_aram_returnValue(retVal){
	window.returnValue = retVal;
	window.close();
}

function closeWindow(){
	window.close();
}

</script>
</head>

<body>
	<iframe id="popupFrame" src="${pageContext.request.contextPath}${requestUrl}'/>" width="${width}" height="${height-10}" seamless="seamless"></iframe>
</body>
</html>
