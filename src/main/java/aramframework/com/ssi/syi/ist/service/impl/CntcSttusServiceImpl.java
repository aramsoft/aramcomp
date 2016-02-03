package aramframework.com.ssi.syi.ist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.ssi.syi.ist.domain.CntcSttusVO;
import aramframework.com.ssi.syi.ist.service.CntcSttusService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 연계현황에 대한 서비스 구현클래스를 정의한다.
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

@Service("cntcSttusService")
public class CntcSttusServiceImpl extends EgovAbstractServiceImpl implements CntcSttusService {

	@Autowired
	private CntcSttusMapper cntcSttusMapper;
	
	/**
	 * 연계현황 목록을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	public List<EgovMap> selectCntcSttusList(CntcSttusVO cntcSttusVO) {
		return cntcSttusMapper.selectCntcSttusList(cntcSttusVO);
	}

	/**
	 * 연계현황 총 갯수를 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	public int selectCntcSttusListCnt(CntcSttusVO cntcSttusVO) {
		return cntcSttusMapper.selectCntcSttusListCnt(cntcSttusVO);
	}

	/**
	 * 연계현황 상세항목을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	public CntcSttusVO selectCntcSttusDetail(CntcSttusVO cntcSttusVO) {
		CntcSttusVO resultVo =  cntcSttusMapper.selectCntcSttusDetail(cntcSttusVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cntcSttusVO); 
		return resultVo;
	}

}
