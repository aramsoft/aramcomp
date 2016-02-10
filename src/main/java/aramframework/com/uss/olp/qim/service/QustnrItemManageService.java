package aramframework.com.uss.olp.qim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olp.qim.dao.QustnrItemManageMapper;
import aramframework.com.uss.olp.qim.domain.QustnrItemManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문항목관리를 처리하는 ServiceImpl Class 구현
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
public class QustnrItemManageService extends EgovAbstractServiceImpl {

	@Autowired
	private QustnrItemManageMapper qustnrItemManageMapper;	

	@Autowired
	private EgovIdGnrService qustnrItemManageIdGnrService; 

	/**
	 * 설문항목 목록을 조회한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public List<EgovMap> selectQustnrItemManageList(QustnrItemManageVO qustnrItemManageVO) {
		return qustnrItemManageMapper.selectQustnrItemManageList(qustnrItemManageVO);
	}

	/**
	 * 설문항목를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public int selectQustnrItemManageListCnt(QustnrItemManageVO qustnrItemManageVO) {
		return (Integer) qustnrItemManageMapper.selectQustnrItemManageListCnt(qustnrItemManageVO);
	}

	/**
	 * 설문항목를(을) 상세조회 한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public QustnrItemManageVO selectQustnrItemManageDetail(QustnrItemManageVO qustnrItemManageVO) {
		QustnrItemManageVO resultVo = qustnrItemManageMapper.selectQustnrItemManageDetail(qustnrItemManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, qustnrItemManageVO); 
		return resultVo;
	}

	/**
	 * 설문항목를(을) 등록한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public void insertQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
		try {
			qustnrItemManageVO.setQustnrIemId(qustnrItemManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		qustnrItemManageMapper.insertQustnrItemManage(qustnrItemManageVO);
	}

	/**
	 * 설문항목를(을) 수정한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public void updateQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
		qustnrItemManageMapper.updateQustnrItemManage(qustnrItemManageVO);
	}

	/**
	 * 설문항목를(을) 삭제한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public void deleteQustnrItemManage(QustnrItemManageVO qustnrItemManageVO) {
		qustnrItemManageMapper.deleteQustnrRespondInfo(qustnrItemManageVO);
		qustnrItemManageMapper.deleteQustnrItemManage(qustnrItemManageVO);
	}
	
}
