package aramframework.com.sym.sym.nwk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.sym.nwk.dao.NtwrkMapper;
import aramframework.com.sym.sym.nwk.domain.NtwrkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 네트워크에 대한 ServiceImpl 클래스를 정의한다.
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
public class NtwrkService extends EgovAbstractServiceImpl {

	@Autowired
	private NtwrkMapper ntwrkMapper;
	
	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService ntwrkIdGnrService; 

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	public List<EgovMap> selectNtwrkList(NtwrkVO ntwrkVO) {
		return ntwrkMapper.selectNtwrkList(ntwrkVO);
	}

	/**
	 * 네트워크목록 총 갯수를 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	public int selectNtwrkListCnt(NtwrkVO ntwrkVO) {
		return ntwrkMapper.selectNtwrkListCnt(ntwrkVO);
	}

	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	public NtwrkVO selectNtwrk(NtwrkVO ntwrkVO) {
		NtwrkVO resultVo = ntwrkMapper.selectNtwrk(ntwrkVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, ntwrkVO); 
		return resultVo;
	}

	/**
	 * 네트워크정보를 신규로 등록한다.
	 * 
	 * @param ntwrkVO
	 */
	public void insertNtwrk(NtwrkVO ntwrkVO) {
		try {
			ntwrkVO.setNtwrkId(ntwrkIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		ntwrkMapper.insertNtwrk(ntwrkVO);
	}

	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 * 
	 * @param ntwrkVO
	 */
	public void updateNtwrk(NtwrkVO ntwrkVO) {
		ntwrkMapper.updateNtwrk(ntwrkVO);
	}

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * 
	 * @param ntwrkVO
	 */
	public void deleteNtwrk(NtwrkVO ntwrkVO) {
		ntwrkMapper.deleteNtwrk(ntwrkVO);
	}

}
