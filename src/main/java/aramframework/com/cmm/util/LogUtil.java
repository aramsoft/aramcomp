package aramframework.com.cmm.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.slf4j.Logger;

public class LogUtil {

	/**
	 * 에러 메시지와 스택트레이스를  로그에 저장한다. 
	 * 
	 * @param e : Exception 변수
	 */
	public static void logErrorMessage(Logger LOG, Exception e, int depth) {

		try {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            PrintStream printstream = new PrintStream(bytearrayoutputstream);
            e.printStackTrace(printstream);
            byte abyte[] = bytearrayoutputstream.toByteArray();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
            BufferedReader bf = new BufferedReader(new InputStreamReader(bytearrayinputstream));
            printstream.close();

            String str;
            LOG.error("====================================================");
            LOG.error("Error message => " + e.getMessage());
            for(int count = 0; (str = bf.readLine()) != null && count < depth; count++)
            	LOG.error(str);
            
        } catch(IOException ex) {
        	LOG.error("logger error");
        } 
	}
	
}