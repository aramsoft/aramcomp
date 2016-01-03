package aramframework.com.cop.ems.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메일 솔루션과 연동해서 이용해서 메일을 보내는 서비스 클래스
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

public interface SndngMailService {

	/**
	 * 발송메일 목록을 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	List<EgovMap> selectSndngMailList(SndngMailVO sndngMailVO);

	/**
	 * 발송메일 총건수를 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	int selectSndngMailListCnt(SndngMailVO sndngMailVO);

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	void deleteSndngMailList(SndngMailVO sndngMailVO);

	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	SndngMailVO selectSndngMail(SndngMailVO sndngMailVO);

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	void deleteSndngMail(SndngMailVO sndngMailVO);

	/**
	 * 발송할 메일을 등록한다
	 * 
	 * @param sndngMailVO
	 */
	boolean insertSndngMail(SndngMailVO sndngMailVO);

	/**
	 * 발송할 메일을 XML파일로 만들어 저장한다.
	 * 
	 * @param sndngMailVO
	 */
	public boolean trnsmitXmlData(SndngMailVO sndngMailVO);

	/**
	 * 발송메일 발송결과 XML파일을 읽어 발송결과코드에 수정한다.
	 * 
	 * @param xml
	 */
	public boolean recptnXmlData(String xml);

	/**
	 * 메일을 발송한다
	 * 
	 * @param sndngMailVO
	 */
	boolean sndngMail(SndngMailVO sndngMailVO);
	
}
