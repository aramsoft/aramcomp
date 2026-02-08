<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : SchdulMainPage.jsp
 * @Description : 메인페이지/일정관리조회
 * @Modification Information
 * @
 * @ 수정일              수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2014.11.11   조헌철          최초 생성
 *
 *  @since 2014.11.11
 *  @version 1.0
 *  @see
 *  
 */
%>
<!-- 일정관리정보 시작 height="80" -->
<div style="width:430px;">
	<table style="border:0;">
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><a href="#" onClick="fn_aram_schdulManage('${resultInfo.schdulId}'); return false;">${resultInfo.toDay}  ${resultInfo.schdulNm}</a></td>
	</tr>
	</c:forEach>
	</table>
</div>
<!-- 일정관리정보 종료 -->

<script type="text/javascript">
function fn_aram_schdulManage(schdulId){
	var url = "/cop/smt/sim/detailSchdul.do?schdulId=" + schdulId;
	window.open(url, "p_schdulInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}
</script>

