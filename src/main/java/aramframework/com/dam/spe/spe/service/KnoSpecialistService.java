package aramframework.com.dam.spe.spe.service;

import java.util.List;

import aramframework.com.dam.spe.spe.domain.KnoSpecialistVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식전문가에 대한 Service Interface를 정의한다.
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

public interface KnoSpecialistService {

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	List<EgovMap> selectKnoSpecialistList(KnoSpecialistVO knoSpecialistVO);

	/**
	 * 지식전문가 목록 총 갯수를 조회한다.
	 * 
	 * @param knoSpecialistVO
	 */
	int selectKnoSpecialistListCnt(KnoSpecialistVO knoSpecialistVO);

	/**
	 * 지식전문가 상세 정보를 조회 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	KnoSpecialistVO selectKnoSpecialist(KnoSpecialistVO knoSpecialistVO);

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * 
	 * @param knoSpecialistVO
	 */
	void insertKnoSpecialist(KnoSpecialistVO knoSpecialistVO);

	/**
	 * 기 등록 된 지식전문가 정보를 수정 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	void updateKnoSpecialist(KnoSpecialistVO knoSpecialistVO);

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * 
	 * @param knoSpecialistVO
	 */
	void deleteKnoSpecialist(KnoSpecialistVO knoSpecialistVO);

}