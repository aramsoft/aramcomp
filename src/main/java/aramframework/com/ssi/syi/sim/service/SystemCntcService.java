package aramframework.com.ssi.syi.sim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.ssi.syi.sim.dao.SystemCntcMapper;
import aramframework.com.ssi.syi.sim.domain.SystemCntcVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템연계에 대한 서비스 구현클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class SystemCntcService extends EgovAbstractServiceImpl {

	@Autowired
	private SystemCntcMapper systemCntcMapper;
	
	/** EgovIdGnrService */
	@Autowired
	private EgovIdGnrService systemCntcIdGnrService; 
	
	/**
	 * 시스템연계 목록을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	public List<EgovMap> selectSystemCntcList(SystemCntcVO systemCntcVO) {
		return systemCntcMapper.selectSystemCntcList(systemCntcVO);
	}

	/**
	 * 시스템연계 총 갯수를 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	public int selectSystemCntcListCnt(SystemCntcVO systemCntcVO) {
		return systemCntcMapper.selectSystemCntcListCnt(systemCntcVO);
	}

	/**
	 * 시스템연계 상세항목을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	public SystemCntcVO selectSystemCntcDetail(SystemCntcVO systemCntcVO) {
		return systemCntcMapper.selectSystemCntcDetail(systemCntcVO);
	}

	/**
	 * 시스템연계를 등록한다.
	 * 
	 * @param systemCntcVO
	 */
	public void insertSystemCntc(SystemCntcVO systemCntcVO) {
		try {
			systemCntcVO.setCntcId(systemCntcIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		systemCntcMapper.insertSystemCntc(systemCntcVO);
	}

	/**
	 * 시스템연계를 수정한다.
	 * 
	 * @param systemCntcVO
	 */
	public void updateSystemCntc(SystemCntcVO systemCntcVO) {
		systemCntcMapper.updateSystemCntc(systemCntcVO);
	}

	/**
	 * 시스템연계를 삭제한다.
	 * 
	 * @param systemCntcVO
	 */
	public void deleteSystemCntc(SystemCntcVO systemCntcVO) {
		systemCntcMapper.deleteSystemCntc(systemCntcVO);
	}

	/**
	 * 시스템연계 승인/승인취소한다.
	 * 
	 * @param systemCntcVO
	 */
	public void confirmSystemCntc(SystemCntcVO systemCntcVO) {
		systemCntcMapper.confirmSystemCntc(systemCntcVO);
	}

}
