<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
var imgpath = "${pageContext.request.contextPath}/images/cmm/utl/";
var getContextPath = "${pageContext.request.contextPath}";
var path = "http://" + "${pageContext.request.serverName}" + ":" + "${pageContext.request.serverPort}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sym/mnu/mpm/MainMenu.js" /></script>

<form name="menuListForm" action ="${pageContext.request.contextPath}/sym/mnu/mpm/listMenuTree.do" method="post">

<table border="0">
<tr>
    <td>
        <div style="width:0px; height:0px;">
		<c:forEach var="result" items="${list_menulist}" varStatus="status">
		<input type="hidden" name="tmp_menuNm" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.relateImagePath}|${result.relateImageNm}|${pageContext.request.contextPath}/${result.chkURL}|"/>
		</c:forEach>
		</div>
		<div class="tree" style="overflow: auto; z-index: 5; padding: 0px; ">
		<script type="text/javaScript">
			var Tree = new Array;
			for (var j = 0; j < document.menuListForm.tmp_menuNm.length; j++) {
				Tree[j] = document.menuListForm.tmp_menuNm[j].value;
			}
			createTree(Tree, true, '<c:out value="${menuManageVO.menuNo}"/>');
		</script>
		</div>
   </td>
</tr>
</table>

</form>

