package aramframework.com.uss.ion.evt.service.impl;

import java.util.List;

import aramframework.com.uss.ion.evt.domain.EventAtdrnVO;
import aramframework.com.uss.ion.evt.domain.EventManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 행사관리에 대한 DAO 클래스를 정의한다.
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

@Mapper
public interface EventManageMapper {

	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public List<EgovMap> selectEventManageList(EventManageVO eventManageVO);

	/**
	 * 행사관리목록 총 갯수를 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public int selectEventManageListCnt(EventManageVO eventManageVO);

	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public EventManageVO selectEventManage(EventManageVO eventManageVO);

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * 
	 * @param eventManageVO
	 */
	public void insertEventManage(EventManageVO eventManageVO);

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * 
	 * @param eventManageVO
	 */
	public void updateEventManage(EventManageVO eventManageVO);

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * 
	 * @param eventManageVO
	 */
	public void deleteEventManage(EventManageVO eventManageVO);

	/** 행사접수관리 ***/
	/**
	 * 행사접수정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public List<EgovMap> selectEventAtdrnList(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사접수관리목록 총 갯수를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public int selectEventAtdrnListCnt(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사일자, 행사구분 조건에 따른 행사명 목록을 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public List<EgovMap> selectEventNmList(EventManageVO eventManageVO);

	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public EventAtdrnVO selectEventAtdrn(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public void insertEventAtdrn(EventAtdrnVO eventAtdrnVO);

	/**
	 * 기 등록된 행사접수관리정보를 삭제한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public void deleteEventAtdrn(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public List<EgovMap> selectEventRceptConfmList(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록 총 갯수를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public int selectEventRceptConfmListCnt(EventAtdrnVO eventAtdrnVO);

	/**
	 * 기 등록된 행사접수관리정보를 승인처리한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public void updateEventAtdrn(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사접수자 정보를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public List<EgovMap> selectEventReqstAtdrnList(EventAtdrnVO eventAtdrnVO);

	/**
	 * 행사접수자 목록 총 갯수를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public int selectEventReqstAtdrnListCnt(EventAtdrnVO eventAtdrnVO);

}
