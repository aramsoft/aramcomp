package aramframework.com.uss.ion.ans.dao;

import java.util.List;

import aramframework.com.uss.ion.ans.domain.AnnvrsryManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 기념일관리에 대한 DAO 클래스를 정의한다.
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
public interface AnnvrsryManageMapper {

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public List<EgovMap> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기념일관리목록 총 갯수를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public int selectAnnvrsryManageListCnt(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public void insertAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public void updateAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public void deleteAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public List<EgovMap> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 기념일관리 등록시 중복여부를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public int selectAnnvrsryManageDplctAt(AnnvrsryManageVO annvrsryManageVO);

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public AnnvrsryManageVO selectAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO);

}
