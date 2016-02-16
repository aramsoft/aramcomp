package aramframework.com.uss.ion.ism.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.ism.dao.InfrmlSanctnMapper;
import aramframework.com.uss.ion.ism.domain.InfrmlSanctnVO;
import aramframework.com.uss.ion.ism.domain.SanctnerVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 약식결재관리에 대한 ServiceImpl 클래스를 정의한다.
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
public class InfrmlSanctnService extends EgovAbstractServiceImpl {

	@Autowired
	private InfrmlSanctnMapper infrmlSanctnMapper;	

	@Autowired
	private EgovIdGnrService infrmlSanctnIdGnrService; 

	/**
	 * 결재자 목록을 조회한다.
	 * 
	 * @param sanctnerVO
	 */
	public List<EgovMap> selectSanctnerList(SanctnerVO sanctnerVO) {
		return infrmlSanctnMapper.selectSanctnerList(sanctnerVO);
	}

	/**
	 * 결재자 총 갯수를 조회한다.
	 * 
	 * @param sanctnerVO
	 */
	public int selectSanctnerListCnt(SanctnerVO sanctnerVO) {
		return infrmlSanctnMapper.selectSanctnerListCnt(sanctnerVO);
	}

	/**
	 * 약식결재 정보를 조회한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO selectInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO) {
		InfrmlSanctnVO resultVo = infrmlSanctnMapper.selectInfrmlSanctn(infrmlSanctnVO);
		if (resultVo.getSanctnDt() != null && !resultVo.getSanctnDt().equals("")) {
			if (resultVo.getSanctnDt().length() > 18) {
				resultVo.setSanctnDt(resultVo.getSanctnDt().substring(0, 19));
			}
		}
		// searchVO 이전 
		resultVo.setSearchVO(infrmlSanctnVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 약식결재관리 정보를 수정한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO updateInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO) {
		infrmlSanctnMapper.updateInfrmlSanctn(infrmlSanctnVO);
		return selectInfrmlSanctn(infrmlSanctnVO);
	}

	/**
	 * 약식결재관리 정보를 승인한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO updateInfrmlSanctnConfm(InfrmlSanctnVO infrmlSanctnVO) {
		infrmlSanctnVO.setConfmAt("C");
		infrmlSanctnMapper.updateInfrmlSanctnConfm(infrmlSanctnVO);
		return selectInfrmlSanctn(infrmlSanctnVO);
	}

	/**
	 * 약식결재관리 정보를 반려한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO updateInfrmlSanctnReturn(InfrmlSanctnVO infrmlSanctnVO) {
		infrmlSanctnVO.setConfmAt("R");
		infrmlSanctnMapper.updateInfrmlSanctnConfm(infrmlSanctnVO);
		return selectInfrmlSanctn(infrmlSanctnVO);
	}

	/**
	 * 약식결재관리 정보를 등록한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO insertInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO) {
		try {
			infrmlSanctnVO.setInfrmlSanctnId(infrmlSanctnIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		infrmlSanctnVO.setConfmAt("A");
		infrmlSanctnMapper.insertInfrmlSanctn(infrmlSanctnVO);
		return selectInfrmlSanctn(infrmlSanctnVO);
	}

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public void deleteInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO) {
		infrmlSanctnMapper.deleteInfrmlSanctn(infrmlSanctnVO);
	}

}