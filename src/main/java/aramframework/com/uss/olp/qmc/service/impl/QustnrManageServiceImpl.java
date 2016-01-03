package aramframework.com.uss.olp.qmc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olp.qmc.service.QustnrManageService;
import aramframework.com.uss.olp.qmc.service.QustnrManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문관리를 처리하는 ServiceImpl Class 구현
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

@Service("qustnrManageService")
public class QustnrManageServiceImpl extends EgovAbstractServiceImpl implements QustnrManageService {

	@Resource(name = "qustnrManageMapper")
	private QustnrManageMapper qustnrManageMapper;	

	@Resource(name = "qustnrManageIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 설문템플릿 목록을 조회한다.
	 * 
	 * @param qustnrManageVO
	 */
	public List<EgovMap> selectQustnrTmplatManageList(QustnrManageVO qustnrManageVO) {
		return qustnrManageMapper.selectQustnrTmplatManageList(qustnrManageVO);
	}

	/**
	 * 설문관리 목록을 조회한다.
	 * 
	 * @param qustnrManageVO
	 */
	public List<EgovMap> selectQustnrManageList(QustnrManageVO qustnrManageVO) {
		return qustnrManageMapper.selectQustnrManageList(qustnrManageVO);
	}

	/**
	 * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrManageVO
	 */
	public int selectQustnrManageListCnt(QustnrManageVO qustnrManageVO) {
		return (Integer) qustnrManageMapper.selectQustnrManageListCnt(qustnrManageVO);
	}

	/**
	 * 설문관리를 상세조회(Model) 한다.
	 * 
	 * @param qustnrManageVO
	 */
	public QustnrManageVO selectQustnrManageDetail(QustnrManageVO qustnrManageVO) {
		QustnrManageVO resultVo = qustnrManageMapper.selectQustnrManageDetail(qustnrManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, qustnrManageVO); 
		return resultVo;
	}

	/**
	 * 설문관리를(을) 등록한다.
	 * 
	 * @param qustnrManageVO
	 */
	public void insertQustnrManage(QustnrManageVO qustnrManageVO) {
		try {
			qustnrManageVO.setQestnrId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		qustnrManageMapper.insertQustnrManage(qustnrManageVO);
	}

	/**
	 * 설문관리를(을) 수정한다.
	 * 
	 * @param qustnrManageVO
	 */
	public void updateQustnrManage(QustnrManageVO qustnrManageVO) {
		qustnrManageMapper.updateQustnrManage(qustnrManageVO);
	}

	/**
	 * 설문관리를(을) 삭제한다.
	 * 
	 * @param qustnrManageVO
	 */
	public void deleteQustnrManage(QustnrManageVO qustnrManageVO) {
		qustnrManageMapper.deleteQustnrRespondManage(qustnrManageVO);
		qustnrManageMapper.deleteQustnrRespondInfo(qustnrManageVO);
		qustnrManageMapper.deleteQustnrItemManage(qustnrManageVO);
		qustnrManageMapper.deleteQustnrQestnManage(qustnrManageVO);
		qustnrManageMapper.deleteQustnrManage(qustnrManageVO);
	}
	
}
