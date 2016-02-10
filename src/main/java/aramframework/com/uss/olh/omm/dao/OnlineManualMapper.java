package aramframework.com.uss.olh.omm.dao;

import java.util.List;

import aramframework.com.uss.olh.omm.domain.OnlineManualVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인메뉴얼를 처리하는 Dao Class 구현
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
public interface OnlineManualMapper {

	/**
	 * 온라인메뉴얼를(을) 목록을 한다.
	 * 
	 * @param onlineManualVO
	 */
	public List<EgovMap> selectOnlineManualList(OnlineManualVO onlineManualVO);

	/**
	 * 온라인메뉴얼를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param onlineManualVO
	 */
	public int selectOnlineManualListCnt(OnlineManualVO onlineManualVO);

	/**
	 * 온라인메뉴얼를(을) 상세조회 한다.
	 * 
	 * @param onlineManualVO
	 */
	public OnlineManualVO selectOnlineManualDetail(OnlineManualVO onlineManualVO);

	/**
	 * 온라인메뉴얼를(을) 등록한다.
	 * 
	 * @param onlineManualVO
	 */
	public void insertOnlineManual(OnlineManualVO onlineManualVO);

	/**
	 * 온라인메뉴얼를(을) 수정한다.
	 * 
	 * @param onlineManualVO
	 */
	public void updateOnlineManual(OnlineManualVO onlineManualVO);

	/**
	 * 온라인메뉴얼를(을) 삭제한다.
	 * 
	 * @param onlineManualVO
	 */
	public void deleteOnlineManual(OnlineManualVO onlineManualVO);

}
