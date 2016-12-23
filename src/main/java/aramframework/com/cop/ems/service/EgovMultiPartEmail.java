package aramframework.com.cop.ems.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 * 발송메일에 첨부파일용으로 사용되는 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class EgovMultiPartEmail extends MultiPartEmail{
	    
	/** HOST NAME */
	private String host;

	/** PORT */
	private int port;

	/** MAIL ID */
	private String mailId;

	/** MAIL PASSWORD */
	private String mailPass;

	/** MAIL NAME */
	private String mailName;

	/** Send Email Address */
	private String emailAddress;

	public EgovMultiPartEmail(String host, int port, String mailId, String mailPass, 
			           String mailName, String emailAddress) {
		this.host = host;
		this.port = port;
		this.mailId = mailId;
		this.mailPass = mailPass;
		this.mailName = mailName;
		this.emailAddress = emailAddress;
	}
	
	public String send() throws EmailException {
		super.setCharset("utf-8");
		super.setHostName(this.host);
		super.setSmtpPort(this.port);
		super.setSSL(true);
		super.setAuthenticator(new DefaultAuthenticator(this.mailId, this.mailPass));
		super.setSocketConnectionTimeout(60000);
		super.setSocketTimeout(60000);
		super.setFrom(this.emailAddress, this.mailName);
		return super.send();
	}
}
