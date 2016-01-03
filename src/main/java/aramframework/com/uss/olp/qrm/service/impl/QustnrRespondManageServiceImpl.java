package aramframework.com.uss.olp.qrm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageService;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문응답자관리 ServiceImpl Class 구현
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

@Service("qustnrRespondManageService")
public class QustnrRespondManageServiceImpl extends EgovAbstractServiceImpl implements QustnrRespondManageService {

	@Resource(name = "qustnrRespondManageMapper")
	private QustnrRespondManageMapper qustnrRespondManageMapper;	

	@Resource(name = "qustnrRespondManageIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 응답자정보 목록을 조회한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public List<EgovMap> selectQustnrRespondManageList(QustnrRespondManageVO qustnrRespondManageVO) {
		return qustnrRespondManageMapper.selectQustnrRespondManageList(qustnrRespondManageVO);
	}

	/**
	 * 응답자정보를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public int selectQustnrRespondManageListCnt(QustnrRespondManageVO qustnrRespondManageVO) {
		return (Integer) qustnrRespondManageMapper.selectQustnrRespondManageListCnt(qustnrRespondManageVO);
	}

	/**
	 * 응답자정보를(을) 상세조회 한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public QustnrRespondManageVO selectQustnrRespondManageDetail(QustnrRespondManageVO qustnrRespondManageVO) {
		QustnrRespondManageVO resultVo = qustnrRespondManageMapper.selectQustnrRespondManageDetail(qustnrRespondManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, qustnrRespondManageVO); 
		return resultVo;
	}

	/**
	 * 응답자정보를(을) 등록한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public void insertQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) {
		try {
			qustnrRespondManageVO.setQestnrRespondId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		qustnrRespondManageMapper.insertQustnrRespondManage(qustnrRespondManageVO);
	}

	/**
	 * 응답자정보를(을) 수정한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public void updateQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) {
		qustnrRespondManageMapper.updateQustnrRespondManage(qustnrRespondManageVO);
	}

	/**
	 * 응답자정보를(을) 삭제한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public void deleteQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) {
		qustnrRespondManageMapper.deleteQustnrRespondManage(qustnrRespondManageVO);
	}
	
}
