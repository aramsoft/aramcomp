package aramframework.com.uss.ion.rwd.service;

import java.util.List;

import aramframework.com.uss.ion.rwd.domain.RwardManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 포상관리에 대한 DAO 클래스를 정의한다.
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
public interface RwardManageMapper {

	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	public List<EgovMap> selectRwardManageList(RwardManageVO rwardManageVO);

	/**
	 * 포상관리목록 총 갯수를 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	public int selectRwardManageListCnt(RwardManageVO rwardManageVO);

	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	public RwardManageVO selectRwardManage(RwardManageVO rwardManageVO);

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * 
	 * @param rwardManageVO
	 */
	public void insertRwardManage(RwardManageVO rwardManageVO);

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * 
	 * @param rwardManageVO
	 */
	public void updtRwardManage(RwardManageVO rwardManageVO);

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * 
	 * @param rwardManageVO
	 */
	public void deleteRwardManage(RwardManageVO rwardManageVO);

	/*** 승인처리관련 ***/
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	public List<EgovMap> selectRwardManageConfmList(RwardManageVO rwardManageVO);

	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록 총 갯수를 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	public int selectRwardManageConfmListCnt(RwardManageVO rwardManageVO);

	/**
	 * 포상정보를 승인/반려처리 한다.
	 * 
	 * @param rwardManageVO
	 */
	public void updtRwardManageConfm(RwardManageVO rwardManageVO);
	
}
