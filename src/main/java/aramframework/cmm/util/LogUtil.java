package aramframework.cmm.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 스택트레이스의 로그 라인을 제한하기 위한 유틸리티 컴포넌트 
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class LogUtil {

	protected static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	/**
	 * 에러 메시지와 스택트레이스를  로그에 저장한다. 
	 * 
	 * @param e  		Exception 변수
	 * @param depth  	라인수
	 */
	public static void logErrorMessage(Throwable e, int depth) {

		try {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            PrintStream printstream = new PrintStream(bytearrayoutputstream);
            e.printStackTrace(printstream);
            byte abyte[] = bytearrayoutputstream.toByteArray();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
            BufferedReader bf = new BufferedReader(new InputStreamReader(bytearrayinputstream));
            printstream.close();

            String str;
            logger.error("====================================================");
            logger.error("Error message => " + e.getMessage());
            for(int count = 0; (str = bf.readLine()) != null && count < depth; count++)
            	logger.error(str);
            
        } catch(IOException ex) {
        	logger.error("logger error");
        }
	}
	
	/**
	 * 에러 메시지와 스택트레이스를  로그에 저장한다. 
	 * 
	 * @param logger	Logger
	 * @param e  		Exception 변수
	 * @param depth  	라인수
	 */
	public static void logErrorMessage(Logger logger, Exception e, int depth) {

		try {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            PrintStream printstream = new PrintStream(bytearrayoutputstream);
            e.printStackTrace(printstream);
            byte abyte[] = bytearrayoutputstream.toByteArray();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
            BufferedReader bf = new BufferedReader(new InputStreamReader(bytearrayinputstream));
            printstream.close();

            String str;
            logger.error("====================================================");
            logger.error("Error message => " + e.getMessage());
            for(int count = 0; (str = bf.readLine()) != null && count < depth; count++)
            	logger.error(str);
            
        } catch(IOException ex) {
        	logger.error("logger error");
        } 
		
	}
	
}