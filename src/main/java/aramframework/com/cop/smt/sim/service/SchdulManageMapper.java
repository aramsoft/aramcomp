package aramframework.com.cop.smt.sim.service;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cop.smt.sim.domain.SchdulManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 일정관리를 처리하는 Dao Class 구현
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
public interface SchdulManageMapper {

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
	 * 메인페이지/일정관리조회 목록을 Map(map)형식으로 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	public List<EgovMap> selectSchdulManageMainList(SchdulManageVO schdulManageVO);
	
	/**
	 * 일정 목록을 Map(map)형식으로 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	public List<EgovMap> selectSchdulManageRetrieve(SchdulManageVO schdulManageVO);
	
	/**
	 * 일정 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectSchdulManageList(SearchVO searchVO);

	/**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectSchdulManageListCnt(SearchVO searchVO);

	/**
	 * 일정를(을) 상세조회 한다.
	 * 
	 * @param schdulManageVO
	 */
	public SchdulManageVO selectSchdulManageDetail(SchdulManageVO schdulManageVO);

	/**
	 * 일정를(을) 등록한다.
	 * 
	 * @param qschdulManageVO
	 */
	public void insertSchdulManage(SchdulManageVO schdulManageVO);

	/**
	 * 일정를(을) 수정한다.
	 * 
	 * @param schdulManageVO
	 */
	public void updateSchdulManage(SchdulManageVO schdulManageVO);

	/**
	 * 일정를(을) 삭제한다.
	 * 
	 * @param schdulManageVO
	 */
	public void deleteDiaryManage(SchdulManageVO schdulManageVO);
	public void deleteSchdulManage(SchdulManageVO schdulManageVO);

}