package aramframework.com.cmm.constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.cmm.util.WebUtil;

/**
 * properties값들을 파일로부터 읽어와 Globals클래스의 정적변수로 로드시켜주는 클래스로
 * 문자열 정보 기준으로 사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AramProperties {

	// 프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
	public static final String ERR_CODE = " EXCEPTION OCCURRED";
	public static final String ERR_CODE_FNFE = " EXCEPTION(FNFE) OCCURRED";
	public static final String ERR_CODE_IOE = " EXCEPTION(IOE) OCCURRED";

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	protected static final Logger LOG = LoggerFactory.getLogger(AramProperties.class);

	// 프로퍼티 파일의 물리적 위치
	/*
	 * public static final String GLOBALS_PROPERTIES_FILE =
	 * 		  System.getProperty("user.home") 
	 * 		+ System.getProperty("file.separator") 
	 * 		+ "egovProps" 
	 * 		+ System.getProperty("file.separator") 
	 * 		+ "globals.properties";
	 */

	// public static final String RELATIVE_PATH_PREFIX = 
	//    	  AramProperties.class.getResource("").getPath()
	// 		+ System.getProperty("file.separator") 
	//		+ ".." + System.getProperty("file.separator")
	// 		+ ".." + System.getProperty("file.separator") 
	//		+ ".." + System.getProperty("file.separator");

	public static final String AramProperties_PATH  
			= AramProperties.class.getResource("").getPath();

	public static final String SYSCONFIG_PATH_PREFIX 		
			= AramProperties_PATH.substring(0, AramProperties_PATH.lastIndexOf("aramframework")) 
			+ "sysconfig" + System.getProperty("file.separator");

	public static final String GLOBALS_PROPERTIES_FILE 
			= SYSCONFIG_PATH_PREFIX 
			+ "egovProps" + System.getProperty("file.separator")
			+ "globals.properties";

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
	 * 
	 * @param keyName	String
	 * @return 			String
	 */
	public static String getProperty(String keyName) {
		String value = ERR_CODE;
		value = "99";
		FileInputStream fis = null;
		try {
			Properties props = new Properties();
			fis = new FileInputStream(WebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));
			props.load(new java.io.BufferedInputStream(fis));
			
			value = props.getProperty(keyName).trim();
		} catch (FileNotFoundException fne) {
			LOG.error(fne.getMessage());
		} catch (IOException ioe) {
			LOG.error(ioe.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception ex) {
				LOG.error("IGNORED: " + ex.getMessage());
			}
		}
		return value;
	}

	public static String getSysPathProperty(String keyName) {
		return SYSCONFIG_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + getProperty(keyName);
	}
	
	/**
	 * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
	 * 
	 * @param 	fileName	String
	 * @param 	key         String
	 * @return 				String
	 */
	public static String getProperty(String fileName, String key) {
		String value = ERR_CODE;
		value = "99";
		FileInputStream fis = null;
		try {
			java.util.Properties props = new java.util.Properties();
			fis = new FileInputStream(WebUtil.filePathBlackList(fileName));
			props.load(new java.io.BufferedInputStream(fis));

			value = props.getProperty(key);
		} catch (FileNotFoundException fne) {
			return ERR_CODE_FNFE;
		} catch (IOException ioe) {
			return ERR_CODE_IOE;
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception ex) {
				LOG.error("IGNORED: " + ex.getMessage());
			}
		}
		return value;
	}

	public static String getSysPathProperty(String fileName, String key) {
		return SYSCONFIG_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + getProperty(fileName, key);
	}
	
}
