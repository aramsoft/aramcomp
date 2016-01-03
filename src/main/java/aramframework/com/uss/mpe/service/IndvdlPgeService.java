package aramframework.com.uss.mpe.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 마이페이지에 대한 Service Interface를 정의한다.
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

public interface IndvdlPgeService {

	// 마이페이지 콘텐츠 목록
	
	/**
	 * 마이페이지 컨텐츠 목록에 컨텐츠를 추가하기 위해 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public List<EgovMap> addIndvdlpgeCntntsList(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 마이페이지에 등록 가능한 컨텐츠 개수를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public int addIndvdlpgeCntntsListCnt(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 기 등록된 마이페이지 콘텐츠 목록 정보를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public List<EgovMap> selectIndvdlpgeResultDetail(IndvdlPgeConfVO indvdlPgeConfVO);

	/**
	 * 기 등록된 마이페이지 콘텐츠 목록 정보의 카운트를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public int selectIndvdlpgeResultDetailCnt(IndvdlPgeConfVO indvdlPgeConfVO);

	/**
	 * 마이페이지 컨텐츠 목록에 컨텐츠를 바로추가한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void addIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 마이페이지 컨텐츠 목록에서 컨텐츠를 바로 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void delIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO);
	
	// 마이페이지 설정 정보

	/**
	 * 마이페이지 설정 정보를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public IndvdlPgeConfVO selectIndvdlpgeConfDetail(IndvdlPgeConfVO indvdlPgeConfVO);

	/**
	 * 마이페이지 설정여부를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public int selectIndvdlpgeConfCnt(IndvdlPgeConfVO indvdlPgeConfVO);

	/**
	 * 마이페이지 설정정보를 추가한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public void insertIndvdlpgeConf(IndvdlPgeConfVO indvdlPgeConfVO);

	/**
	 * 마이페이지 설정정보를 수정한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	public void updateIndvdlpgeConf(IndvdlPgeConfVO indvdlPgeConfVO);

	//  컨텐츠 관리

	/**
	 * 컨텐츠를 관리하기 위해 등록된 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public List<EgovMap> selectIndvdlpgeCntntsList(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 컨텐츠 목록 개수를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public int selectIndvdlpgeCntntsListCnt(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 컨텐츠의 상세정보를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public IndvdlPgeCntntsVO selectIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 컨텐츠정보를 신규로 등록한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void insertIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 컨텐츠정보를 수정한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void updateIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 컨텐츠정보를 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void deleteIndvdlpgeCntnts(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

	/**
	 * 컨텐츠정보를 DB에서 완전 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	public void deleteIndvdlpgeCntntsDB(IndvdlPgeCntntsVO indvdlPgeCntntsVO);

}