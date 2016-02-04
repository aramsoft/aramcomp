package aramframework.com.ssi.syi.iis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.ssi.syi.iis.domain.CntcInsttVO;
import aramframework.com.ssi.syi.iis.domain.CntcServiceVO;
import aramframework.com.ssi.syi.iis.domain.CntcSystemVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 연계기관에 대한 서비스 구현클래스를 정의한다.
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
public class CntcInsttService extends EgovAbstractServiceImpl {

	@Autowired
	private CntcInsttMapper cntcInsttMapper;

	/** EgovIdGnrService */
	@Autowired
	private EgovIdGnrService cntcInsttIdGnrService; 

	/** EgovIdGnrService */
	@Autowired
	private EgovIdGnrService cntcSystemIdGnrService; 

	/** EgovIdGnrService */
	@Autowired
	private EgovIdGnrService cntcServiceIdGnrService; 

	/**
	 * 연계기관 목록을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	public List<CntcInsttVO> selectCntcInsttList(CntcInsttVO cntcInsttVO) {
		return cntcInsttMapper.selectCntcInsttList(cntcInsttVO);
	}

	/**
	 * 연계기관 총 갯수를 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	public int selectCntcInsttListCnt(CntcInsttVO cntcInsttVO) {
		return cntcInsttMapper.selectCntcInsttListCnt(cntcInsttVO);
	}

	/**
	 * 연계기관 상세항목을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	public CntcInsttVO selectCntcInsttDetail(CntcInsttVO cntcInsttVO) {
		CntcInsttVO resultVo =  cntcInsttMapper.selectCntcInsttDetail(cntcInsttVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cntcInsttVO); 
		return resultVo;
	}

	/**
	 * 연계기관을 등록한다.
	 * 
	 * @param cntcInsttVO
	 */
	public void insertCntcInstt(CntcInsttVO cntcInsttVO) {
		try {
			cntcInsttVO.setInsttId(cntcInsttIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cntcInsttMapper.insertCntcInstt(cntcInsttVO);
	}

	/**
	 * 연계기관을 수정한다.
	 * 
	 * @param cntcInsttVO
	 */
	public void updateCntcInstt(CntcInsttVO cntcInsttVO) {
		cntcInsttMapper.updateCntcInstt(cntcInsttVO);
	}

	/**
	 * 연계기관을 삭제한다.
	 * 
	 * @param cntcInsttVO
	 */
	public void deleteCntcInstt(CntcInsttVO cntcInsttVO) {
		cntcInsttMapper.deleteCntcInstt(cntcInsttVO);
	}

	/**
	 * 연계시스템 목록을 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	public List<CntcSystemVO> selectCntcSystemList(CntcSystemVO cntcSystemVO) {
		return cntcInsttMapper.selectCntcSystemList(cntcSystemVO);
	}

	/**
	 * 연계시스템 총 갯수를 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	public int selectCntcSystemListCnt(CntcSystemVO cntcSystemVO) {
		return cntcInsttMapper.selectCntcSystemListCnt(cntcSystemVO);
	}

	/**
	 * 연계시스템 상세항목을 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	public CntcSystemVO selectCntcSystemDetail(CntcSystemVO cntcSystemVO) {
		CntcSystemVO resultVo = cntcInsttMapper.selectCntcSystemDetail(cntcSystemVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cntcSystemVO); 
		return resultVo;
	}

	/**
	 * 연계시스템을 등록한다.
	 * 
	 * @param cntcSystemVO
	 */
	public void insertCntcSystem(CntcSystemVO cntcSystemVO) {
		try {
			cntcSystemVO.setSysId(cntcSystemIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cntcInsttMapper.insertCntcSystem(cntcSystemVO);
	}

	/**
	 * 연계시스템을 수정한다.
	 * 
	 * @param cntcSystemVO
	 */
	public void updateCntcSystem(CntcSystemVO cntcSystemVO) {
		cntcInsttMapper.updateCntcSystem(cntcSystemVO);
	}

	/**
	 * 연계시스템을 삭제한다.
	 * 
	 * @param cntcSystemVO
	 */
	public void deleteCntcSystem(CntcSystemVO cntcSystemVO) {
		cntcInsttMapper.deleteCntcSystem(cntcSystemVO);
	}

	/**
	 * 연계서비스 목록을 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	public List<CntcServiceVO> selectCntcServiceList(CntcServiceVO cntcServiceVO) {
		return cntcInsttMapper.selectCntcServiceList(cntcServiceVO);
	}

	/**
	 * 연계서비스 총 갯수를 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	public int selectCntcServiceListCnt(CntcServiceVO cntcServiceVO) {
		return cntcInsttMapper.selectCntcServiceListCnt(cntcServiceVO);
	}

	/**
	 * 연계서비스 상세항목을 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	public CntcServiceVO selectCntcServiceDetail(CntcServiceVO cntcServiceVO) {
		CntcServiceVO resultVo = cntcInsttMapper.selectCntcServiceDetail(cntcServiceVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cntcServiceVO); 
		return resultVo;
	}

	/**
	 * 연계서비스를 등록한다.
	 * 
	 * @param cntcServiceVO
	 */
	public void insertCntcService(CntcServiceVO cntcServiceVO) {
		try {
			cntcServiceVO.setSvcId(cntcServiceIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cntcInsttMapper.insertCntcService(cntcServiceVO);
	}

	/**
	 * 연계서비스를 수정한다.
	 * 
	 * @param cntcServiceVO
	 */
	public void updateCntcService(CntcServiceVO cntcServiceVO) {
		cntcInsttMapper.updateCntcService(cntcServiceVO);
	}

	/**
	 * 연계서비스를 삭제한다.
	 * 
	 * @param cntcServiceVO
	 */
	public void deleteCntcService(CntcServiceVO cntcServiceVO) {
		cntcInsttMapper.deleteCntcService(cntcServiceVO);
	}

}
