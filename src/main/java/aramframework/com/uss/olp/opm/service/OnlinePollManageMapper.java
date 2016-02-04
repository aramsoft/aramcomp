package aramframework.com.uss.olp.opm.service;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.uss.olp.opm.domain.OnlinePollItemVO;
import aramframework.com.uss.olp.opm.domain.OnlinePollManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL관리를 처리하는 Dao Class 구현
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
public interface OnlinePollManageMapper {

	/**
	 * 온라인POLL관리를(을) 목록을 한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectOnlinePollManageList(SearchVO searchVO);

	/**
	 * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectOnlinePollManageListCnt(SearchVO searchVO);

	/**
	 * 온라인POLL관리를(을) 상세조회 한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public OnlinePollManageVO selectOnlinePollManageDetail(OnlinePollManageVO onlinePollManageVO);

	/**
	 * 온라인POLL관리를(을) 등록한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public void insertOnlinePollManage(OnlinePollManageVO onlinePollManageVO);

	/**
	 * 온라인POLL관리를(을) 수정한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public void updateOnlinePollManage(OnlinePollManageVO onlinePollManageVO);

	/**
	 * 온라인POLL관리를(을) 삭제한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public void deleteOnlinePollResultAll(OnlinePollManageVO onlinePollManageVO);
	public void deleteOnlinePollItemAll(OnlinePollManageVO onlinePollManageVO);
	public void deleteOnlinePollManage(OnlinePollManageVO onlinePollManageVO);

	/**
	 * 온라인POLL항목를(을) 조회한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public List<EgovMap> selectOnlinePollItemList(OnlinePollItemVO onlinePollItemVO);

	/**
	 * 온라인POLL항목를(을) 등록한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public void insertOnlinePollItem(OnlinePollItemVO onlinePollItemVO);

	/**
	 * 온라인POLL항목를(을) 수정한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public void updateOnlinePollItem(OnlinePollItemVO onlinePollItemVO);

	/**
	 * 온라인POLL항목를(을) 삭제한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public void deleteOnlinePollResultIemid(OnlinePollItemVO onlinePollItemVO);
	public void deleteOnlinePollItem(OnlinePollItemVO onlinePollItemVO);
	
}