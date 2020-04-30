<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="isUat" value="false"/>
<c:set var="isSec" value="false"/>
<c:set var="isSts" value="false"/>
<c:set var="isCop" value="false"/>
<c:set var="isTrn" value="false"/>
<c:set var="isUss" value="false"/>
<c:set var="isSym" value="false"/>
<c:set var="isSsi" value="false"/>
<c:set var="isDam" value="false"/>
<c:set var="isCom" value="false"/>
<c:set var="isMbl" value="false"/>
<c:set var="isEtc" value="false"/>
<table >
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<c:if test="${isUat == 'false' && result.gid == '10'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<h1>사용자디렉토리/통합인증</h1>
				</td>
			</tr>
			<c:set var="isUat" value="true"/>
		</c:if>
		<c:if test="${isSec == 'false' && result.gid == '20'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>보안</h1>
				</td>
			</tr>
			<c:set var="isSec" value="true"/>
		</c:if>
		<c:if test="${isSts == 'false' && result.gid == '30'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>통계/리포팅</h1>
				</td>
			</tr>
			<c:set var="isSts" value="true"/>
		</c:if>
		<c:if test="${isCop == 'false' && result.gid == '40'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>협업</h1>
				</td>
			</tr>
			<c:set var="isCop" value="true"/>
		</c:if>
		<c:if test="${isTrn == 'false' && result.gid == '45'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>메시지전송</h1>
				</td>
			</tr>
			<c:set var="isTrn" value="true"/>
		</c:if>
		<c:if test="${isUss == 'false' && result.gid == '50'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>사용자지원</h1>
				</td>
			</tr>
			<c:set var="isUss" value="true"/>
		</c:if>
		<c:if test="${isSym == 'false' && result.gid == '60'}">
			<tr height = "16">
			    <td align="left"  width="100%">
			    	<br><h1>시스템관리</h1>
				</td>
			</tr>
			<c:set var="isSym" value="true"/>
		</c:if>
		<c:if test="${isSsi == 'false' && result.gid == '70'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>시스템/서비스연계</h1>
				</td>
			</tr>
			<c:set var="isSsi" value="true"/>
		</c:if>
		<c:if test="${isDam == 'false' && result.gid == '80'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>디지털 자산 관리</h1>
				</td>
			</tr>
			<c:set var="isDam" value="true"/>
		</c:if>
		<c:if test="${isCom == 'false' && result.gid == '90'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>요소기술</h1>
				</td>
			</tr>
			<c:set var="isCom" value="true"/>
		</c:if>
		<c:if test="${isMbl == 'false' && result.gid == '100'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>모바일</h1>
				</td>
			</tr>
			<c:set var="isMbl" value="true"/>
		</c:if>
		<c:if test="${isEtc == 'false' && result.gid == '120'}">
			<tr height = "16">
			    <td align="left" width="100%">
			    	<br><h1>기타</h1>
				</td>
			</tr>
			<c:set var="isEtc" value="true"/>
		</c:if>
		<tr height = "16">
		    <td align="left" valign="middle" width="100%">
		    	<a href="<c:out value="${result.listUrl}"/>" target="contentFrame" class="link"> <c:out value="${result.order}"/>. <c:out value="${result.name}"/></a>
			</td>
		</tr>
	</c:forEach>
</table>
