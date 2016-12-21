package aramframework.com.uss.ion.mtg.dao;

import java.util.List;

import aramframework.com.uss.ion.mtg.domain.MtgPlaceFxtrsVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceManageVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceResveVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 회의실관리에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface MtgPlaceManageMapper {

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public List<EgovMap> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실관리목록 총 갯수를 조회한다.
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
	 * 회의실관리정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void updateMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO);

	/******** 회의실 예약 관리 *************/

	/**
	 * 회의실 ID 정보 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceIDList(MtgPlaceManageVO mtgPlaceManageVO);

	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실예약을 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public EgovMap selectMtgPlaceResveManageList(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 등록된 회의실예약 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public MtgPlaceResveVO selectMtgPlaceResveDetail(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 회의실예약 정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 기 등록된 회의실예약 정보를 수정한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void updateMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO);

	/**
	 * 기 등록된 회의실예약 정보를 삭제한다.
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
	 * 비품정보 목록을 조회한다
	 * 
	 * @param mtgPlaceFxtrsVO
	 */
	public List<MtgPlaceFxtrsVO> selectFxtrsManageList(MtgPlaceFxtrsVO mtgPlaceFxtrsVO);

	/**
	 * 회의실관리 비품정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceFxtrsVO
	 */
	public void insertMtgPlaceFxtrs(MtgPlaceFxtrsVO mtgPlaceFxtrsVO);

	/**
	 * 회의실관리 비품정보를 삭제한다.
	 * 
	 * @param mtgPlaceFxtrsVO
	 */
	public void deleteMtgPlaceFxtrs(MtgPlaceFxtrsVO mtgPlaceFxtrsVO);

}
