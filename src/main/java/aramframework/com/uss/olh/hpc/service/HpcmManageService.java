package aramframework.com.uss.olh.hpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olh.hpc.dao.HpcmManageMapper;
import aramframework.com.uss.olh.hpc.domain.HpcmManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 도움말을 처리하는 비즈니스 구현 클래스
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
public class HpcmManageService extends EgovAbstractServiceImpl {

	@Autowired
	private HpcmManageMapper hpcmManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService hpcmManageIdGnrService; 

	/**
	 * 도움말 글 목록을 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	public List<EgovMap> selectHpcmList(HpcmManageVO hpcmManageVO) {
		return hpcmManageMapper.selectHpcmList(hpcmManageVO);
	}

	/**
	 * 도움말 글 총 갯수를 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	public int selectHpcmListCnt(HpcmManageVO hpcmManageVO) {
		return hpcmManageMapper.selectHpcmListCnt(hpcmManageVO);
	}

	/**
	 * 도움말 글을 상세 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	public HpcmManageVO selectHpcmDetail(HpcmManageVO hpcmManageVO) {
		HpcmManageVO resultVo = hpcmManageMapper.selectHpcmDetail(hpcmManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, hpcmManageVO); 
		return resultVo;
	}

	/**
	 * 도움말 글을 등록한다.
	 * 
	 * @param hpcmManageVO
	 */
	public void insertHpcmCn(HpcmManageVO hpcmManageVO) {
		try {
			hpcmManageVO.setHpcmId(hpcmManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		hpcmManageMapper.insertHpcmCn(hpcmManageVO);
	}

	/**
	 * 도움말 글을 수정한다.
	 * 
	 * @param hpcmManageVO
	 */
	public void updateHpcmCn(HpcmManageVO hpcmManageVO) {
		hpcmManageMapper.updateHpcmCn(hpcmManageVO);
	}

	/**
	 * 도움말 글을 삭제한다.
	 * 
	 * @param hpcmManageVO
	 */
	public void deleteHpcmCn(HpcmManageVO hpcmManageVO) {
		hpcmManageMapper.deleteHpcmCn(hpcmManageVO);
	}

}
