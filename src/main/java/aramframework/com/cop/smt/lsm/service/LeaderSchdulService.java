package aramframework.com.cop.smt.lsm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 간부일정에 대한 Service Interface를 정의한다.
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

public interface LeaderSchdulService {

	/**
	 * 월별 간부일정 목록을 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public List<EgovMap> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 간부일정 정보를 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public LeaderSchdulVO selectLeaderSchdul(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 간부일정 정보를 등록한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public void insertLeaderSchdul(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 간부일정 정보를 수정한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public void updateLeaderSchdul(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 간부일정 정보를 삭제한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public void deleteLeaderSchdul(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 간부상태 목록을 조회한다.
	 * 
	 * @param leaderSttusVO
	 */
	public List<EgovMap> selectLeaderSttusList(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 총횟수를 조회한다.
	 * 
	 * @param leaderSttusVO
	 */
	public int selectLeaderSttusListCnt(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 정보를 조회한다.
	 * 
	 * @param leaderSttusVO
	 */
	public LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태를 등록하기 위한 중복 조회를 수행한다.
	 * 
	 * @param leaderSttusVO
	 */
	public int selectLeaderSttusCheck(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 정보를 등록한다.
	 * 
	 * @param leaderSttusVO
	 */
	public void insertLeaderSttus(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 정보를 수정한다.
	 * 
	 * @param leaderSttusVO
	 */
	public void updateLeaderSttus(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 정보를 삭제한다.
	 * 
	 * @param leaderSttusVO
	 */
	public void deleteLeaderSttus(LeaderSttusVO leaderSttusVO);
	
}