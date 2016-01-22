package aramframework.com.uss.mpe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.mpe.service.IndvdlPgeService;
import aramframework.com.uss.mpe.service.IndvdlPgeCntntsVO;
import aramframework.com.uss.mpe.service.IndvdlPgeConfVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 마이페이지에 대한 ServiceImpl을 정의한다.
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
public class IndvdlPgeServiceImpl extends EgovAbstractServiceImpl implements IndvdlPgeService {

	@Autowired
	private IndvdlPgeMapper indvdlPgeMapper;
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService indvdlPgeIdGnrService; 

	// 마이페이지 콘텐츠 목록
	
	/**
	 * 마이페이지 컨텐츠 목록에 컨텐츠를 추가하기 위해 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public List<EgovMap> addIndvdlpgeCntntsList(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		return indvdlPgeMapper.addIndvdlpgeCntntsList(indvdlPgeCntntsVO);
	}

	/**
	 * 마이페이지에 등록 가능한 컨텐츠 개수를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public int addIndvdlpgeCntntsListCnt(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		return indvdlPgeMapper.addIndvdlpgeCntntsListCnt(indvdlPgeCntntsVO);
	}

	/**
	 * 기 등록된 마이페이지 정보를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public List<EgovMap> selectIndvdlpgeResultDetail(IndvdlPgeConfVO indvdlPgeConfVO) {
		return indvdlPgeMapper.selectIndvdlpgeResultDetail(indvdlPgeConfVO);
	}

	/**
	 * 기 등록된 마이페이지 정보의 카운트를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public int selectIndvdlpgeResultDetailCnt(IndvdlPgeConfVO indvdlPgeConfVO) {
		return indvdlPgeMapper.selectIndvdlpgeResultDetailCnt(indvdlPgeConfVO);
	}

	/**
	 * 마이페이지에 컨텐츠를 바로추가한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void addIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		indvdlPgeMapper.addIndvdlpgeCntnts(indvdlPgeCntntsVO);
	}

	/**
	 * 마이페이지에 컨텐츠를 바로 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void delIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		indvdlPgeMapper.delIndvdlpgeCntnts(indvdlPgeCntntsVO);
	}

	// 마이페이지 설정 정보
	/**
	 * 마이페이지 설정 정보를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public IndvdlPgeConfVO selectIndvdlpgeConfDetail(IndvdlPgeConfVO indvdlPgeConfVO) {
		IndvdlPgeConfVO resultVo = indvdlPgeMapper.selectIndvdlpgeConfDetail(indvdlPgeConfVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, indvdlPgeConfVO); 
		return resultVo;
	}

	/**
	 * 마이페이지 설정여부를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public int selectIndvdlpgeConfCnt(IndvdlPgeConfVO indvdlPgeConfVO) {
		return indvdlPgeMapper.selectIndvdlpgeConfCnt(indvdlPgeConfVO);
	}

	/**
	 * 마이페이지 설정정보를 추가한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public void insertIndvdlpgeConf(IndvdlPgeConfVO indvdlPgeConfVO) {
		indvdlPgeMapper.insertIndvdlpgeConf(indvdlPgeConfVO);
	}

	/**
	 * 마이페이지 설정정보를 수정한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public void updateIndvdlpgeConf(IndvdlPgeConfVO indvdlPgeConfVO) {
		indvdlPgeMapper.updateIndvdlpgeConf(indvdlPgeConfVO);
	}

	// 마이페이지 컨텐츠 관리
	/**
	 * 마이페이지 컨텐츠를 관리하기 위해 등록된 마이페이지 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public List<EgovMap> selectIndvdlpgeCntntsList(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		return indvdlPgeMapper.selectIndvdlpgeCntntsList(indvdlPgeCntntsVO);
	}

	/**
	 * 컨텐츠 목록 개수를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public int selectIndvdlpgeCntntsListCnt(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		return indvdlPgeMapper.selectIndvdlpgeCntntsListCnt(indvdlPgeCntntsVO);
	}

	/**
	 * 컨텐츠의 상세정보를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public IndvdlPgeCntntsVO selectIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		IndvdlPgeCntntsVO resultVo = indvdlPgeMapper.selectIndvdlpgeCntnts(indvdlPgeCntntsVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, indvdlPgeCntntsVO); 
		return resultVo;
	}

	/**
	 * 컨텐츠정보를 신규로 등록한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void insertIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		try {
			indvdlPgeCntntsVO.setCntntsId(indvdlPgeIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		indvdlPgeMapper.insertIndvdlpgeCntnts(indvdlPgeCntntsVO);
	}

	/**
	 * 컨텐츠정보를 수정한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void updateIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		indvdlPgeMapper.updateIndvdlpgeCntnts(indvdlPgeCntntsVO);
	}

	/**
	 * 컨텐츠정보를 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void deleteIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		indvdlPgeMapper.deleteIndvdlpgeCntnts(indvdlPgeCntntsVO);
	}

	/**
	 *  컨텐츠정보를 DB에서 완전 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void deleteIndvdlpgeCntntsDB(IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		indvdlPgeMapper.deleteIndvdlpgeCntntsDB(indvdlPgeCntntsVO);
	}

}