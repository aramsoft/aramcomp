package aramframework.com.uss.olp.qqm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olp.qqm.service.QustnrQestnManageService;
import aramframework.com.uss.olp.qqm.service.QustnrQestnManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문문항을 처리하는 ServiceImpl Class 구현
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

@Service("qustnrQestnManageService")
public class QustnrQestnManageServiceImpl extends EgovAbstractServiceImpl implements QustnrQestnManageService {

	@Resource(name = "qustnrQestnManageMapper")
	private QustnrQestnManageMapper qustnrQestnManageMapper;	

	@Resource(name = "qustnrQestnManageIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public List<EgovMap> selectQustnrManageStatistics2(QustnrQestnManageVO qustnrQestnManageVO) {
		return qustnrQestnManageMapper.selectQustnrManageStatistics2(qustnrQestnManageVO);
	}

	/**
	 * 설문조사 통계를 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public List<EgovMap> selectQustnrManageStatistics(QustnrQestnManageVO qustnrQestnManageVO) {
		return qustnrQestnManageMapper.selectQustnrManageStatistics(qustnrQestnManageVO);
	}

	/**
	 * 설문지정보 설문제목을 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public String selectQustnrManageQestnrSj(QustnrQestnManageVO qustnrQestnManageVO) {
		return qustnrQestnManageMapper.selectQustnrManageQestnrSj(qustnrQestnManageVO);
	}

	/**
	 * 설문문항 목록을 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public List<EgovMap> selectQustnrQestnManageList(QustnrQestnManageVO qustnrQestnManageVO) {
		return qustnrQestnManageMapper.selectQustnrQestnManageList(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public int selectQustnrQestnManageListCnt(QustnrQestnManageVO qustnrQestnManageVO) {
		return (Integer) qustnrQestnManageMapper.selectQustnrQestnManageListCnt(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 상세조회 한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public QustnrQestnManageVO selectQustnrQestnManageDetail(QustnrQestnManageVO qustnrQestnManageVO) {
		QustnrQestnManageVO resultVo = qustnrQestnManageMapper.selectQustnrQestnManageDetail(qustnrQestnManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, qustnrQestnManageVO); 
		return resultVo;
	}

	/**
	 * 설문문항를(을) 등록한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public void insertQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) {
		try {
			qustnrQestnManageVO.setQestnrQesitmId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		qustnrQestnManageMapper.insertQustnrQestnManage(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 수정한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public void updateQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) {
		qustnrQestnManageMapper.updateQustnrQestnManage(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 삭제한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public void deleteQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) {
		qustnrQestnManageMapper.deleteQustnrQestnManage(qustnrQestnManageVO);
	}
	
}
