<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head><title></title>
<script type="text/javascript">
<c:if test="${S_DUPL eq 'N'}">
	alert( "위키 북마크에 추가 되었습니다!" );
</c:if>

<c:if test="${S_DUPL eq 'Y'}">
	alert( "이미 추가된 북마크 입니다!" );
</c:if>
</script>
</head>
<body>
OK
</body>
</html>