<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<rss version="2.0"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:taxo="http://purl.org/rss/1.0/modules/taxonomy/"
	xmlns:activity="http://activitystrea.ms/spec/1.0/">
<channel>
<title><![CDATA[<c:out value="${rssInfoVO.hderTitle}" />]]></title>
<link><![CDATA[<c:out value="${rssInfoVO.hderLink}" />]]></link>
<description><![CDATA[<c:out value="${fn:replace(rssInfoVO.hderDescription , crlf , '<br/>')}" escapeXml="true" />]]></description>
<tag><![CDATA[<c:out value="${rssInfoVO.hderTag}" />]]></tag>
<c:forEach items="${mapRssInfoList}" var="result" varStatus="status">
	<item>
	<title><![CDATA[<c:out value="${result.bdtTitle}"/>]]></title>
	<link><![CDATA[<c:out value="${result.bdtLink}"/>]]></link>
	<description><![CDATA[<c:out value="${result.bdtDescription}"/>]]></description>
	<tag><![CDATA[<c:out value="${result.bdtTag}"/>]]></tag>
	<pubDate><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/></pubDate>
	<c:out value="${result.bdtEtc}"/>
	</item>
</c:forEach>
</channel>
</rss>