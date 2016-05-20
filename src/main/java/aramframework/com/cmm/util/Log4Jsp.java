// Decompiled by DJ v3.6.6.79 Copyright 2004 Atanas Neshkov  Date: 2009-06-26 ���� 9:38:53
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Log4Web.java

package aramframework.com.cmm.util;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4Jsp {
	
	protected static final Logger LOG = LoggerFactory.getLogger(Log4Jsp.class);

	public static void debug(String msg) {
        LOG.debug(msg);
	}
	
	public static void info(String msg) {
        LOG.info(msg);
	}
	
	public static void error(String msg) {
        LOG.error(msg);
	}
	
	public static void debugRequest (HttpServletRequest req) {
	    StringBuffer buffer = new StringBuffer();
	    buffer.append("Request: ");
	    buffer.append(req.getMethod());
	    buffer.append(' ');
	    buffer.append(req.getRequestURL());
	    String queryString = req.getQueryString();
	    if (queryString != null) {
	    	buffer.append('?');
	    	buffer.append(queryString);
	    }
	    buffer.append(", Session ID: ");
	    String sessionId = req.getRequestedSessionId();
	    if (sessionId == null) {
	    	buffer.append("none");
	    } else if (req.isRequestedSessionIdValid()) {
	    	buffer.append(sessionId);
	    	buffer.append(" (from ");
	    	if (req.isRequestedSessionIdFromCookie())
	    		buffer.append("cookie)");
	    	else if (req.isRequestedSessionIdFromURL())
	    		buffer.append("url)");
	    	else
	    		buffer.append("unknown)");
	    } else {
	    	buffer.append("invalid");
	    }
	    LOG.debug(buffer.toString());
	}

	public static void debugParameters (HttpServletRequest req) {
	    StringBuffer buffer = new StringBuffer();
	    Enumeration<?> names = req.getParameterNames();
    	buffer.append("Parameters: ");
	    if (names.hasMoreElements()) {
	    	while (names.hasMoreElements()) {
	    		String name = (String) names.nextElement();
	    		String[] values = req.getParameterValues(name);
	    		for (int i = 0; i < values.length; ++i) {
	    			buffer.append("[" + name + "]");
	    			buffer.append(" = ");
	    			buffer.append(values[i]);
	    			buffer.append(", ");
	    		}
	    	}
	    }
	    LOG.debug(buffer.toString());
	}

	public static void debugHeaders (HttpServletRequest req) {
	    StringBuffer buffer = new StringBuffer();
	    Enumeration<?> names = req.getHeaderNames();
    	buffer.append("Headers: ");
	    if (names.hasMoreElements()) {
	    	while (names.hasMoreElements()) {
	    		String name = (String) names.nextElement();
	    		String value = req.getHeader(name);
    			buffer.append("[" + name + "]");
	    		buffer.append(": ");
	    		buffer.append(value);
	    		buffer.append(", ");
	    	}
	    }
	    LOG.debug(buffer.toString());
	}

	public static void debugCookies (HttpServletRequest req) {
	    StringBuffer buffer = new StringBuffer();
    	buffer.append("Cookies: ");
	    Cookie[] cookies = req.getCookies();
	    int l = cookies.length;
	    if (l > 0) {
	    	for (int i = 0; i < l; ++i) {
	    		Cookie cookie = cookies[i];
    			buffer.append("[" + cookie.getName() + "]");
	    		buffer.append(" = ");
	    		buffer.append(cookie.getValue());
	    		buffer.append(", ");
	    	}
	    }
	    LOG.debug(buffer.toString());
	}

}