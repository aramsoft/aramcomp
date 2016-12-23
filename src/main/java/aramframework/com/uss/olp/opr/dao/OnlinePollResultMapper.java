package aramframework.com.uss.olp.opr.dao;

import java.util.List;

import aramframework.com.uss.olp.opr.domain.OnlinePollResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL결과를 처리하는 Dao Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface OnlinePollResultMapper {

	/**
	 * 온라인POLL결과를(을) 목록을 한다.
	 * 
	 * @param onlinePollResultVO
	 */
	public List<EgovMap> selectOnlinePollResultList(OnlinePollResultVO onlinePollResultVO);

	/**
	 * 온라인POLL결과를(을) 삭제 한다.
	 * 
	 * @param onlinePollResultVO
	 */
	public void deleteOnlinePollResult(OnlinePollResultVO onlinePollResultVO);

}
