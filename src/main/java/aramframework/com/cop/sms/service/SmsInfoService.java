package aramframework.com.cop.sms.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 문자메시지를 위한 서비스 인터페이스 클래스
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

public interface SmsInfoService {

	/**
	 * 문자메시지 목록을 조회 한다.
	 * 
	 * @param smsVO
	 */
	public List<EgovMap> selectSmsInfs(SmsVO smsVO) throws Exception;

	/**
	 * 문자메시지 총갯수를 조회 한다.
	 * 
	 * @param smsVO
	 */
	public int selectSmsInfsCnt(SmsVO smsVO) throws Exception;

	/**
	 * 문자메시지를 전송(등록)한다.
	 * 
	 * @param smsVO
	 */
	public void insertSmsInf(SmsVO smsVO) throws Exception;

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param smsVO
	 */
	public SmsVO selectSmsInf(SmsVO smsVO) throws Exception;

	/**
	 * 문자메시지 실 전송을 요청한다.
	 * 
	 * @param smsMessageVO
	 */
	public SmsMessageVO sendRequsest(SmsMessageVO smsMessageVO) throws Exception;

	/**
	 * 여러 건의 문자메시지 실 전송을 요청한다.
	 * 
	 * @param smsConn
	 */
	public SmsMessageVO[] sendRequsest(SmsMessageVO[] smsConn) throws Exception;
}
