package aramframework.com.cop.smt.lsm.service.impl;

import java.util.List;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import aramframework.com.cop.smt.lsm.service.LeaderSttusVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 간부일정에 대한 DAO 클래스를 정의한다.
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

@Mapper("leaderSchdulMapper")
public interface LeaderSchdulMapper {

	/**
	 * 주어진 조건에 맞는 사용자를 불러온다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectEmplyrList(SearchVO searchVO);
	
	/**
	 * 사용자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectEmplyrListCnt(SearchVO searchVO);

	/**
	 * 주어진 조건에 따른 간부일정 목록을 월별로 불러온다.
	 * 
	 * @param leaderSchdulVO
	 */
	public List<EgovMap> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 주어진 조건에 맞는 간부일정을 불러온다.
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
	 * 간부일정 일자 정보를 등록한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public void insertLeaderSchdulDe(LeaderSchdulVO leaderSchdulVO);

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
	 * 간부일정일자 정보를 삭제한다.
	 * 
	 * @param leaderSchdulVO
	 */
	public void deleteLeaderSchdulDe(LeaderSchdulVO leaderSchdulVO);

	/**
	 * 주어진 조건에 따른 간부상태 목록을 불러온다.
	 * 
	 * @param leaderSttusVO
	 */
	public List<EgovMap> selectLeaderSttusList(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param leaderSttusVO
	 */
	public int selectLeaderSttusListCnt(LeaderSttusVO leaderSttusVO);

	/**
	 * 주어진 조건에 맞는 간부상태를 불러온다.
	 * 
	 * @param leaderSttusVO
	 */
	public LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO);

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
	 * 간부상태 등록을 위한 중복 조회를 수행한다.
	 * 
	 * @param leaderSttusVO
	 */
	public int selectLeaderSttusCheck(LeaderSttusVO leaderSttusVO);

	/**
	 * 간부상태 정보를 삭제한다.
	 * 
	 * @param leaderSttusVO
	 */
	public void deleteLeaderSttus(LeaderSttusVO leaderSttusVO);

}