package aramframework.com.uss.ion.ctn.service.impl;

import java.util.List;

import aramframework.com.uss.ion.ctn.service.CtsnnManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 경조관리에 대한 DAO 클래스를 정의한다.
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

@Mapper("ctsnnManageMapper")
public interface CtsnnManageMapper {

	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public List<EgovMap> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO);

	/**
	 * 경조관리목록 총 갯수를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public int selectCtsnnManageListCnt(CtsnnManageVO ctsnnManageVO);

	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO);

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * 
	 * @param ctsnnManageVO
	 *            - 경조관리 model
	 */
	public void insertCtsnnManage(CtsnnManageVO ctsnnManageVO);

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void updateCtsnnManage(CtsnnManageVO ctsnnManageVO);

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void deleteCtsnnManage(CtsnnManageVO ctsnnManageVO);

	/*** 승인처리관련 ***/
	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public List<EgovMap> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO);

	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록 총 갯수를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public int selectCtsnnManageConfmListCnt(CtsnnManageVO ctsnnManageVO);

	/**
	 * 경조정보를 승인처리 한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void updtCtsnnManageConfm(CtsnnManageVO ctsnnManageVO);
	
}
