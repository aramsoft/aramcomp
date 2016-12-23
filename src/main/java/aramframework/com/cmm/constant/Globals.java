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
	String MOBILE_PAGE = AramProperties.getProperty("Globals.MobilePage");
	String LOCAL_PAGE  = AramProperties.getProperty("Globals.LocalPage");
	
	// 프로퍼티 파일 위치
	String CONF_PATH        = AramProperties.getPathProperty("Globals.ConfPath");
	// ShellFile 경로
	String SHELL_FILE_PATH  = AramProperties.getPathProperty("Globals.ShellFilePath");
	// Server정보 프로퍼티 위치
	String SERVER_CONF_PATH = AramProperties.getPathProperty("Globals.ServerConfPath");
	// Client정보 프로퍼티 위치
	String CLIENT_CONF_PATH = AramProperties.getPathProperty("Globals.ClientConfPath");
	// 파일포맷 정보 프로퍼티 위치
	String FILE_FORMAT_PATH = AramProperties.getPathProperty("Globals.FileFormatPath");

	// 파일 업로드 원 파일명
	String ORIGIN_FILE_NM = "originalFileName";
	// 파일 확장자
	String FILE_EXT       = "fileExtension";
	// 파일크기
	String FILE_SIZE      = "fileSize";
	// 업로드된 파일명
	String UPLOAD_FILE_NM = "uploadFileName";
	// 파일경로
	String FILE_PATH      = "filePath";

	// 메일발송요청 XML파일경로
	String MAIL_REQUEST_PATH  = AramProperties.getPathProperty("Globals.MailRequestPath");
	// 메일발송응답 XML파일경로
	String MAIL_RESPONSE_PATH = AramProperties.getPathProperty("Globals.MailResponsePath");

	// G4C 연결용 IP (localhost)
	String LOCAL_IP = AramProperties.getProperty("Globals.LocalIp");

}
