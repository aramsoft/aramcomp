package aramframework.com.dam.per.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.dam.per.dao.KnoPersonalMapper;
import aramframework.com.dam.per.domain.KnoPersonalVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 개인지식정보에 대한 ServiceImpl 클래스를 정의한다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class KnoPersonalService extends EgovAbstractServiceImpl {

	@Autowired
	private KnoPersonalMapper knoPersonalMapper;

	/** ID Generation */
	@Autowired
	private EgovIdGnrService damManageIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * 등록된 개인지식 정보를 조회 한다.
	 * 
	 * @param knoPersonalVO
	 */
	public List<EgovMap> selectKnoPersonalList(KnoPersonalVO knoPersonalVO) {
		return knoPersonalMapper.selectKnoPersonalList(knoPersonalVO);
	}

	/**
	 * 개인지식 목록 총 갯수를 조회한다.
	 * 
	 * @param knoPersonalVO
	 */
	public int selectKnoPersonalListCnt(KnoPersonalVO knoPersonalVO) {
		return knoPersonalMapper.selectKnoPersonalListCnt(knoPersonalVO);
	}

	/**
	 * 개인지식정보 상세 정보를 조회 한다.
	 * 
	 * @param knoPersonalVO
	 */
	public KnoPersonalVO selectKnoPersonal(KnoPersonalVO knoPersonalVO) {
		KnoPersonalVO resultVo = knoPersonalMapper.selectKnoPersonal(knoPersonalVO);
		// searchVO 이전 
		resultVo.setSearchVO(knoPersonalVO.getSearchVO());
		return resultVo;
	}

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * 
	 * @param knoPersonalVO
	 */
	public void insertKnoPersonal(KnoPersonalVO knoPersonalVO) {
		try {
			knoPersonalVO.setKnoId(damManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		knoPersonalMapper.insertKnoPersonal(knoPersonalVO);
	}

	/**
	 * 기 등록 된 개인지식 정보를 수정 한다.
	 * 
	 * @param knoPersonalVO
	 */
	public void updateKnoPersonal(KnoPersonalVO knoPersonalVO) {
		knoPersonalMapper.updateKnoPersonal(knoPersonalVO);
	}

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * 
	 * @param knoPersonalVO
	 */
	public void deleteKnoPersonal(KnoPersonalVO knoPersonalVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(knoPersonalVO.getAtchFileId());

		knoPersonalMapper.deleteKnoPersonal(knoPersonalVO);
	}

}