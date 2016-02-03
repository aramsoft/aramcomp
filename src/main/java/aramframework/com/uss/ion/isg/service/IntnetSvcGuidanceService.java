package aramframework.com.uss.ion.isg.service;

import java.util.List;

import aramframework.com.uss.ion.isg.domain.IntnetSvcGuidanceVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 인터넷서비스안내에 대한 Service Interface를 정의한다.
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

public interface IntnetSvcGuidanceService {

	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public List<EgovMap> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	/**
	 * 인터넷서비스안내목록 총 갯수를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public int selectIntnetSvcGuidanceListCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public void insertIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public void updateIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public void deleteIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public List<EgovMap> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO);

}
