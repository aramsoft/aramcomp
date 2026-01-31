package aramframework.cmm.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public interface Globals {

	// OS 유형
	@Value("${Globals.OsType}")
	String OS_TYPE = "";
	
	// 메인 페이지
	@Value("${Globals.MainPage}")
	String MAIN_PAGE   = "";
	
	// 메일발송요청 XML파일경로
	@Value("${Globals.MailRequestPath}")
	String MAIL_REQUEST_PATH  = "";

	// 메일발송응답 XML파일경로
	@Value("${Globals.MailResponsePath}")
	String MAIL_RESPONSE_PATH = "";

	// G4C 연결용 IP (localhost)
	@Value("${Globals.LocalIp}")
	String LOCAL_IP = "";

	@Value("${Globals.fileStorePath}")
	String FILE_UPLOAD_DIR = "";
	
	@Value("${Globals.authorResourceReload}")
	String AUTHOR_RESOURCE_RELOAD = "";
	
	// 프로퍼티 파일 위치
	@Value("${Globals.ConfPath}")
	String CONF_PATH        = "";
	// ShellFile 경로
	@Value("${Globals.ShellConfPath}")
	String SHELL_CONF_PATH  = "";
	// Server정보 프로퍼티 위치
	@Value("${Globals.ServerConfPath}")
	String SERVER_CONF_PATH = "";
	// Client정보 프로퍼티 위치
	@Value("${Globals.ClientConfPath}")
	String CLIENT_CONF_PATH = "";
	// 파일포맷 정보 프로퍼티 위치
	@Value("${Globals.FileFormatPath}")
	String FILE_FORMAT_PATH = "";
	// sms 정보 프로퍼티 위치
	@Value("${Globals.SMEConfigPath}")
	String SME_CONFIG_PATH  = "";

}
