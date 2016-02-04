package aramframework.com.uss.olp.opp.service;

import java.util.List;

import aramframework.com.uss.olp.opp.domain.OnlinePollPartcptnVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL참여를 처리하는 Dao Class 구현
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
public interface OnlinePollPartcptnMapper {

	/**
	 * 온라인POLL관리를(을) 목록을 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public List<EgovMap> selectOnlinePollManageList(OnlinePollPartcptnVO onlinePollPartcptnVO);

	/**
	 * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public int selectOnlinePollManageListCnt(OnlinePollPartcptnVO onlinePollPartcptnVO);

	/**
	 * 온라인POLL관리를(을) 상세조회 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public OnlinePollPartcptnVO selectOnlinePollManageDetail(OnlinePollPartcptnVO onlinePollPartcptnVO);

	/**
	 * 온라인POLL항목를(을) 상세조회 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public List<EgovMap> selectOnlinePollItemList(OnlinePollPartcptnVO onlinePollPartcptnVO);

	/**
	 * 온라인POLL참여 여부를 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public int selectOnlinePollResult(OnlinePollPartcptnVO onlinePollPartcptnVO);

	/**
	 * 온라인POLL참여를(을) 등록한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public void insertOnlinePollResult(OnlinePollPartcptnVO onlinePollPartcptnVO);

	/**
	 * 온라인POLL통계를(을) 등록한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public List<EgovMap> selectOnlinePollManageStatistics(OnlinePollPartcptnVO onlinePollPartcptnVO);

}
