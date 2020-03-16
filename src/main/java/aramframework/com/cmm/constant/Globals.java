package aramframework.com.cmm.constant;

/**
 * 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public interface Globals {
	// OS 유형
	String OS_TYPE = AramProperties.getProperty("Globals.OsType");
	// DB 유형
	String DB_TYPE = AramProperties.getProperty("Globals.DbType");
	
	// 메인 페이지
	String MAIN_PAGE   = AramProperties.getProperty("Globals.MainPage");
	
	// 프로퍼티 파일 위치
	String CONF_PATH        = AramProperties.getSysPathProperty("Globals.ConfPath");
	// ShellFile 경로
	String SHELL_CONF_PATH  = AramProperties.getSysPathProperty("Globals.ShellConfPath");
	// Server정보 프로퍼티 위치
	String SERVER_CONF_PATH = AramProperties.getSysPathProperty("Globals.ServerConfPath");
	// Client정보 프로퍼티 위치
	String CLIENT_CONF_PATH = AramProperties.getSysPathProperty("Globals.ClientConfPath");
	// 파일포맷 정보 프로퍼티 위치
	String FILE_FORMAT_PATH = AramProperties.getSysPathProperty("Globals.FileFormatPath");
	// sms 정보 프로퍼티 위치
	String SME_CONFIG_PATH    = AramProperties.getSysPathProperty("Globals.SMEConfigPath");

	// 메일발송요청 XML파일경로
	String MAIL_REQUEST_PATH  = AramProperties.getProperty("Globals.MailRequestPath");
	// 메일발송응답 XML파일경로
	String MAIL_RESPONSE_PATH = AramProperties.getProperty("Globals.MailResponsePath");

	// G4C 연결용 IP (localhost)
	String LOCAL_IP = AramProperties.getProperty("Globals.LocalIp");

	String FILE_UPLOAD_DIR = AramProperties.getProperty("Globals.fileStorePath");
}
