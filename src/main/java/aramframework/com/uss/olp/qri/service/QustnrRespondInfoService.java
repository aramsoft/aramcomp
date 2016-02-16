package aramframework.com.uss.olp.qri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.olp.qri.dao.QustnrRespondInfoMapper;
import aramframework.com.uss.olp.qri.domain.QustnrRespondInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문조사 ServiceImpl Class 구현
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
public class QustnrRespondInfoService extends EgovAbstractServiceImpl {

	@Autowired
	private QustnrRespondInfoMapper qustnrRespondInfoMapper;	

	@Autowired
	private EgovIdGnrService qustnrRespondInfoIdGnrService; 

	/**
	 * 설문조사(설문등록)를(을) 목록을 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public List<EgovMap> selectQustnrRespondInfoManageList(QustnrRespondInfoVO qustnrRespondInfoVO) {
		return qustnrRespondInfoMapper.selectQustnrRespondInfoManageList(qustnrRespondInfoVO);
	}

	/**
	 * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public int selectQustnrRespondInfoManageListCnt(QustnrRespondInfoVO qustnrRespondInfoVO) {
		return qustnrRespondInfoMapper.selectQustnrRespondInfoManageListCnt(qustnrRespondInfoVO);
	}

	/**
	 * 회원정보를 조회한다.
	 * 
	 * @param uniqId
	 */
	public EgovMap selectQustnrRespondEmplyrinfo(String uniqId) {
		return qustnrRespondInfoMapper.selectQustnrRespondEmplyrinfo(uniqId);
	}

	/**
	 * 설문정보를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondQestnrInfo(String qestnrId) {
		return qustnrRespondInfoMapper.selectQustnrRespondQestnrInfo(qestnrId);
	}

	/**
	 * 문항정보를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondQustnrQesitm(String qestnrId) {
		return qustnrRespondInfoMapper.selectQustnrRespondQustnrQesitm(qestnrId);
	}

	/**
	 * 항목정보를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondQustnrIem(String qestnrId) {
		return qustnrRespondInfoMapper.selectQustnrRespondQustnrIem(qestnrId);
	}

	/**
	 * 객관식 통계를 조회 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondStatistics1(String qestnrId) {
		return qustnrRespondInfoMapper.selectQustnrRespondStatistics1(qestnrId);
	}

	/**
	 * 주관식 통계를 조회 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondStatistics2(String qestnrId) {
		return qustnrRespondInfoMapper.selectQustnrRespondStatistics2(qestnrId);
	}

	/**
	 * 응답자결과(설문조사) 목록을 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public List<EgovMap> selectQustnrRespondInfoList(QustnrRespondInfoVO qustnrRespondInfoVO) {
		return qustnrRespondInfoMapper.selectQustnrRespondInfoList(qustnrRespondInfoVO);
	}

	/**
	 * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public int selectQustnrRespondInfoListCnt(QustnrRespondInfoVO qustnrRespondInfoVO) {
		return qustnrRespondInfoMapper.selectQustnrRespondInfoListCnt(qustnrRespondInfoVO);
	}

	/**
	 * 응답자결과(설문조사)를(을) 상세조회 한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public QustnrRespondInfoVO selectQustnrRespondInfoDetail(QustnrRespondInfoVO qustnrRespondInfoVO) {
		QustnrRespondInfoVO resultVo = qustnrRespondInfoMapper.selectQustnrRespondInfoDetail(qustnrRespondInfoVO);
		// searchVO 이전 
		resultVo.setSearchVO(qustnrRespondInfoVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 응답자결과(설문조사)를(을) 등록한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public void insertQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) {
		try {
			qustnrRespondInfoVO.setQestnrQesrspnsId(qustnrRespondInfoIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		qustnrRespondInfoMapper.insertQustnrRespondInfo(qustnrRespondInfoVO);
	}

	/**
	 * 응답자결과(설문조사)를(을) 수정한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public void updateQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) {
		qustnrRespondInfoMapper.updateQustnrRespondInfo(qustnrRespondInfoVO);
	}

	/**
	 * 응답자결과(설문조사)를(을) 삭제한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public void deleteQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) {
		qustnrRespondInfoMapper.deleteQustnrRespondInfo(qustnrRespondInfoVO);
	}
	
}
