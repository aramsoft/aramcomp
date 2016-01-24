package aramframework.com.dam.spe.spe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.dam.spe.spe.service.KnoSpecialistService;
import aramframework.com.dam.spe.spe.service.KnoSpecialistVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식전문가에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("knoSpecialistService")
public class KnoSpecialistServiceImpl extends EgovAbstractServiceImpl implements KnoSpecialistService {

	@Autowired
	private KnoSpecialistMapper knoSpecialistMapper;

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	public List<EgovMap> selectKnoSpecialistList(KnoSpecialistVO knoSpecialistVO) {
		return knoSpecialistMapper.selectKnoSpecialistList(knoSpecialistVO);
	}

	/**
	 * 지식전문가 목록 총 갯수를 조회한다.
	 * 
	 * @param knoSpecialistVO
	 */
	public int selectKnoSpecialistListCnt(KnoSpecialistVO knoSpecialistVO) {
		return knoSpecialistMapper.selectKnoSpecialistListCnt(knoSpecialistVO);
	}

	/**
	 * 지식전문가 상세 정보를 조회 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	public KnoSpecialistVO selectKnoSpecialist(KnoSpecialistVO knoSpecialistVO) {
		KnoSpecialistVO resultVo = knoSpecialistMapper.selectKnoSpecialist(knoSpecialistVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, knoSpecialistVO); 
		return resultVo;
	}

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * 
	 * @param knoSpecialistVO
	 */
	public void insertKnoSpecialist(KnoSpecialistVO knoSpecialistVO) {
		knoSpecialistMapper.insertKnoSpecialist(knoSpecialistVO);
	}

	/**
	 * 기 등록 된 지식전문가 정보를 수정 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	public void updateKnoSpecialist(KnoSpecialistVO knoSpecialistVO) {
		knoSpecialistMapper.updateKnoSpecialist(knoSpecialistVO);
	}

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * 
	 * @param knoSpecialistVO
	 */
	public void deleteKnoSpecialist(KnoSpecialistVO knoSpecialistVO) {
		knoSpecialistMapper.deleteKnoSpecialist(knoSpecialistVO);
	}

}