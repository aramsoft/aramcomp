/*
 * Copyright 2012 Aram High-Tech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aramframework.com.cmm.config.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.uat.uia.domain.LoginVO;

/**
 * Http요청을 감사하기 위한 입력 파라메터 검사 필터 
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class LogAuditFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig config;
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException {

		// get access Information
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURL = httpRequest.getRequestURI();

		LoginVO loginVO = (LoginVO) httpRequest.getSession().getAttribute("loginVO");
		String userid = (loginVO == null) ? "": loginVO.getId();
		LOG.debug("[" + userid + "@" + httpRequest.getRemoteAddr() 
				+ "] METHOD = " + httpRequest.getMethod()
				+ ", URL = " + requestURL 
				+ ", parameter : " + getParameters(httpRequest));
		// get access Information

		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {

	}
	
	private String getParameters (HttpServletRequest req) {
		
	    StringBuffer buffer = new StringBuffer();
	    Enumeration<?> names = req.getParameterNames();
	    if (names.hasMoreElements()) {
	    	while (names.hasMoreElements()) {
	    		String name = (String) names.nextElement();
	    		String[] values = req.getParameterValues(name);
	    		for (int i = 0; i < values.length; ++i) {
	    			buffer.append(" ");
	    			buffer.append(name);
	    			buffer.append(" = ");
	    			if( name.indexOf("password") != -1) {
	    				buffer.append("[protected]");
	    			}else if( values[i].length() > 100 ) {
		    			buffer.append(values[i].substring(0,100));
	    			} else {
	    				buffer.append(values[i]);
	    			}	
	    			buffer.append(",");
	    		}
	    	}
	    }
        return buffer.toString();
	}

}
