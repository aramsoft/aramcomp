package aramframework.com.uss.ion.mtg.service;

import java.util.List;

import aramframework.com.uss.ion.mtg.domain.MtgPlaceFxtrsVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceManageVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceResveVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 회의실관리에 대한 Service Interface를 정의한다.
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

public interface MtgPlaceManageService {

	/**
	 * 회의실관리 정보를 관리하기 위해 등록된 회의실 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public List<EgovMap> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실관리 목록 총 갯수를 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public int selectMtgPlaceManageListCnt(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public MtgPlaceManageVO selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실관리 정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO, String checkedMtgPlacesForInsert);

	/**
	 * 기 등록된 회의실관리 정보를 수정한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void updateMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO, String checkedMtgPlacesForInsert);

	/**
	 * 기 등록된 회의실관리 정보를 삭제한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실관리가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
//	public List<MtgPlaceManageVO> selectMtgPlaceManageResult(MtgPlaceManageVO mtgPlaceManageVO);

	/******** 회의실 예약 관리 *************/
	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실 예약 목록을 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public List<EgovMap> selectMtgPlaceResveManageList(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 등록된 회의실 예약 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public MtgPlaceResveVO selectMtgPlaceResveDetail(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 회의실 예약정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 기 등록된 회의실 예약정보를 수정한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void updateMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 기 등록된 회의실 예약정보를 삭제한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void deleteMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 회의실 중복여부 체크.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public int mtgPlaceResveDplactCeck(MtgPlaceResveVO mtgPlaceResveVO);

	/******** 회의실 비품 관리 *************/

	/**
	 * 비품정보 목록을 조회한다.
	 * 
	 * @param mtgPlaceFxtrsVO
	 */
	public List<MtgPlaceFxtrsVO> selectFxtrsManageList(MtgPlaceFxtrsVO mtgPlaceFxtrsVO);
	
}