/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aramframework.cmm.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

/**
 * <tt>AuthenticationFailureHandler</tt> which performs a redirect to the value of the
 * {@link #setDefaultFailureUrl defaultFailureUrl} property when the
 * <tt>onAuthenticationFailure</tt> method is called. If the property has not been set it
 * will send a 401 response to the client, with the error message from the
 * <tt>AuthenticationException</tt> which caused the failure.
 * <p>
 * If the {@code useForward} property is set, a {@code RequestDispatcher.forward} call
 * will be made to the destination instead of a redirect.
 *
 * @author Luke Taylor
 * @since 3.0
 */
public class SimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

	protected final Log logger = LogFactory.getLog(getClass());

	private String defaultFailureUrl;

	public SimpleUrlAuthenticationFailureHandler() {
	}

	public SimpleUrlAuthenticationFailureHandler(String defaultFailureUrl) {
		setDefaultFailureUrl(defaultFailureUrl);
	}

	/**
	 * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise
	 * returns a 401 error code.
	 * <p>
	 * If redirecting or forwarding, {@code saveException} will be called to cache the
	 * exception for use in the target view.
	 */
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		if (defaultFailureUrl == null) {
			logger.debug("No failure URL set, sending 401 Unauthorized error");

			response.sendError(HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
		else {
	        String message;
        	if (exception instanceof UsernameNotFoundException) {
        		message = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
        	} else if (exception instanceof BadCredentialsException) {
	            message = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
	        } else if (exception instanceof InternalAuthenticationServiceException) {
	            message = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
	        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
	            message = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
	        } else {
	            message = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
	        }

	        request.setAttribute("message", message);
	        request.getRequestDispatcher(defaultFailureUrl).forward(request,response);
		}
	}

	/**
	 * The URL which will be used as the failure destination.
	 *
	 * @param defaultFailureUrl the failure URL, for example "/loginFailed.jsp".
	 */
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl),
				() -> "'" + defaultFailureUrl + "' is not a valid redirect URL");
		this.defaultFailureUrl = defaultFailureUrl;
	}

}
