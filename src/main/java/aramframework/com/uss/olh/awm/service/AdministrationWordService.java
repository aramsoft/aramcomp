package aramframework.com.uss.olh.awm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.olh.awm.dao.AdministrationWordMapper;
import aramframework.com.uss.olh.awm.domain.AdministrationWordVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행정전문용어사전관리를 처리하는 ServiceImpl Class 구현
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

@Service
public class AdministrationWordService extends EgovAbstractServiceImpl {

	@Autowired
	private AdministrationWordMapper administrationWordMapper;	

	@Autowired
	private EgovIdGnrService administrationWordIdGnrService; 

	/**
	 * 행정전문용어사전관리를(을) 목록을 조회 한다.
	 * 
	 * @param administrationWordVO
	 */
	public List<EgovMap> selectAdministrationWordList(AdministrationWordVO administrationWordVO) {
		return administrationWordMapper.selectAdministrationWordList(administrationWordVO);
	}

	/**
	 * 행정전문용어사전관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param administrationWordVO
	 */
	public int selectAdministrationWordListCnt(AdministrationWordVO administrationWordVO) {
		return (Integer) administrationWordMapper.selectAdministrationWordListCnt(administrationWordVO);
	}

	/**
	 * 행정전문용어사전관리를(을) 상세조회 한다.
	 * 
	 * @param administrationWordVO
	 */
	public AdministrationWordVO selectAdministrationWordDetail(AdministrationWordVO administrationWordVO) {
		AdministrationWordVO resultVo = administrationWordMapper.selectAdministrationWordDetail(administrationWordVO);
		// searchVO 이전 
		resultVo.setSearchVO(administrationWordVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 행정전문용어사전관리를(을) 등록한다.
	 * 
	 * @param administrationWordVO
	 */
	public void insertAdministrationWord(AdministrationWordVO administrationWordVO) {
		try {
			administrationWordVO.setAdministWordId(administrationWordIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		administrationWordMapper.insertAdministrationWord(administrationWordVO);
	}

	/**
	 * 행정전문용어사전관리를(을) 수정한다.
	 * 
	 * @param administrationWordVO
	 */
	public void updateAdministrationWord(AdministrationWordVO administrationWordVO) {
		administrationWordMapper.updateAdministrationWord(administrationWordVO);
	}

	/**
	 * 행정전문용어사전관리를(을) 삭제한다.
	 * 
	 * @param administrationWordVO
	 */
	public void deleteAdministrationWord(AdministrationWordVO administrationWordVO) {
		administrationWordMapper.deleteAdministrationWord(administrationWordVO);
	}

}
