package aramframework.com.cop.sms.service.impl;

import java.util.List;

import aramframework.com.cop.sms.domain.SmsRecptnVO;
import aramframework.com.cop.sms.domain.SmsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 문자메시지를 위한 데이터 접근 클래스
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

@Mapper
public interface SmsMapper  {

	/**
	 * 문자메시지 목록을 조회한다.
	 * 
	 * @param smsVO
	 */
	public List<EgovMap> selectSmsInfs(SmsVO smsVO) ;

	/**
	 * 문자메시지 목록 숫자를 조회한다
	 * 
	 * @param smsVO
	 */
	public int selectSmsInfsCnt(SmsVO smsVO) ;

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param smsVO
	 */
	public SmsVO selectSmsInf(SmsVO smsVO);

	/**
	 * 문자메시지 수신 및 결과 목록을 조회한다.
	 * 
	 * @param smsRecptnVO
	 */
	public List<EgovMap> selectSmsRecptnInfs(SmsRecptnVO smsRecptnVO) ;

	/**
	 * 문자메시지 정보를 등록한다.
	 * 
	 * @param smsVO
	 */
	public void insertSmsInf(SmsVO smsVO) ;

	/**
	 * 문자메시지 수신정보 및 결과 정보를 등록한다.
	 * 
	 * @param smsRecptnVO
	 */
	public void insertSmsRecptnInf(SmsRecptnVO smsRecptnVO) ;

	/**
	 * 문자메시지 전송 결과 수신을 처리한다. SmsInfoReceiver(Schedule job)에 의해 호출된다.
	 * 
	 * @param smsRecptnVO
	 */
	public void updateSmsRecptnInf(SmsRecptnVO smsRecptnVO) ;

}
