package aramframework.com.sym.mnu.bmm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 바로가기메뉴관리 정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface BkmkMenuManageservice {

	/**
	 * 바로가기메뉴관리 정보의 전체목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 바로가기메뉴관리 정보의 총갯수를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO);
	
	/**
	 * 바로가기메뉴관리 정보를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public BkmkMenuManageVO selectBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 바로가기메뉴관리 정보를 등록한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public void insertBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 바로가기메뉴관리 정보를 삭제한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public void deleteBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 등록할 메뉴정보 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 등록할 메뉴정보 총갯수를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkPreviewList(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 선택된 메뉴의 URL 을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public String selectUrl(BkmkMenuManageVO bkmkMenuManageVO);

}
