<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : IdPasswordResult.jsp
  * @Description : 아이디/비밀번호 찾기 결과화면
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2009.03.17   박지욱          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.17
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Active Users</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">
</head>

<body>

<DIV id="wrap">

<div class="content">

	<div class="content_title">
		<h2>Active Users</h2>
	</div>
	<div class="content_main">
		<ul>
			<c:forEach items="${activeUsers}" var="uinfo">
				<li><strong>${uinfo.key.username}</strong> / Last Active: ${uinfo.value}</li>
			</c:forEach>
		</ul>
    </div>    
      
</div>

</DIV>

</body>
</html>

