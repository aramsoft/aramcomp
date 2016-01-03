package aramframework.com.cop.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 만족도조사를 위한 서비스 인터페이스 클래스
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

public interface BBSSatisfactionService {

	/**
	 * 만족도조사 사용 가능 여부를 확인한다.
	 * 
	 * @param bbsId
	 */
	public boolean canUseSatisfaction(String bbsId);

	/**
	 * 만족도조사에 대한 목록을 조회 한다.
	 * 
	 * @param satisfactionVO
	 */
	public List<EgovMap> selectSatisfactionList(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 목록 총갯수을 조회 한다.
	 * 
	 * @param satisfactionVO
	 */
	public int selectSatisfactionListCnt(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 목록 요약점수을 조회 한다.
	 * 
	 * @param satisfactionVO
	 */
	public float getSummary(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 내용을 조회한다.
	 * 
	 * @param satisfactionVO
	 */
	public SatisfactionVO selectSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사를 등록한다.
	 * 
	 * @param satisfactionVO
	 */
	public void insertSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 내용을 수정한다.
	 * 
	 * @param satisfactionVO
	 */
	public void updateSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사를 삭제한다.
	 * 
	 * @param satisfactionVO
	 */
	public void deleteSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사 패스워드를 가져온다.
	 * 
	 * @param satisfactionVO
	 */
	public String getSatisfactionPassword(SatisfactionVO satisfactionVO);

}
