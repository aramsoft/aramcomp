<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
  * @Class Name : FileList.jsp
  * @Description : 파일 목록화면
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2009.03.26   이삼섭          최초 생성
  * @ 2011.07.20   옥찬우          <Input> Tag id속성 추가( Line : 68 )
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.26
  *  @version 1.0
  *  @see
  *
  */
%>

<script type="text/javascript">

function fn_aram_deleteFile(atchFileId, fileSn) {
	forms = document.getElementsByTagName("form");

	for (var i = 0; i < forms.length; i++) {
		if (typeof(forms[i].atchFileId) != "undefined" &&
				typeof(forms[i].fileSn) != "undefined" &&
				typeof(forms[i].fileListCnt) != "undefined") {
			form = forms[i];
		}
	}

	//form = document.forms[0];
	form.atchFileId.value = atchFileId;
	form.fileSn.value = fileSn;
	form.action = "${pageContext.request.contextPath}/cmm/fms/deleteFileInfs.mdo'/>";
	form.submit();
}

</script>

<!-- <form name="fileForm" action="" method="post">  -->
<input type="hidden" name="atchFileId" value="${atchFileId}">
<input type="hidden" name="fileSn">
<input type="hidden" name="fileListCnt" id="fileListCnt" value="${fileListCnt}">

<!-- </form>  -->

<!--<title>파일목록</title> -->

<table>
	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<tr>
		<td>
			<c:choose>
			<c:when test="${updateFlag=='Y'}">
				<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileSize}"/>&nbsp;byte]
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu5_close.gif" 
						width="19" height="18" onClick="fn_aram_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/content/mbl/files/${fileVO.pathId}/file/${fileVO.fileSn}" target="new">
					<c:out value="${fileVO.orignlFileNm}"/>
				</a>&nbsp;[<c:out value="${fileVO.fileSize}"/>&nbsp;byte]
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</c:forEach>
	<c:if test="${fn:length(fileList) == 0}">
	<tr>
		<td></td>
	</tr>
	</c:if>
</table>
