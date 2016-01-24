package aramframework.com.sym.log.tlg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 송수신 로그 요약을 위한 스케쥴링 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

@Component("trsmrcvLogScheduling")
public class TrsmrcvLogScheduling {

	@Autowired
	private TrsmrcvLogService trsmrcvLogService;

	/**
	 * 송수신 로그 정보를 요약한다. 전날의 로그를 요약하여 입력하고, 일주일전의 로그를 삭제한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void trsmrcvLogSummary() throws Exception {
		trsmrcvLogService.logInsertTrsmrcvLogSummary();
	}

}
