package aramframework.cmm.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public interface Globals {

	// G4C 연결용 IP (localhost)
	@Value("${Globals.LocalIp}")
	String LOCAL_IP = "";

}
