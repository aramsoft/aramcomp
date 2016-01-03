package aramframework.com.sym.mnu.mcm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴관리에 관한 서비스 인터페이스 클래스를 정의한다.
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

public interface MenuCreateService {

	/**
	 * 메뉴생성관리 목록을 조회
	 * 
	 * @param menuCreateVO
	 */
	List<EgovMap> selectMenuCreateList(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * 
	 * @param menuCreateVO
	 */
	int selectMenuCreateListCnt(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성 내역을 조회
	 * 
	 * @param menuCreateVO
	 */
	List<EgovMap> selectMenuCreateDetailList(MenuCreateVO menuCreateVO);

	/**
	 * 화면에 조회된 메뉴정보로 메뉴생성내역 데이터베이스에서 입력
	 * 
	 * @param checkedScrtyForInsert
	 * @param checkedMenuNoForInsert
	 */
	void insertMenuCreateList(String checkedScrtyForInsert, String checkedMenuNoForInsert);

	/**
	 * ID 존재여부를 조회
	 * 
	 * @param menuCreateVO
	 */
	int selectUsrByPk(MenuCreateVO menuCreateVO);

	/**
	 * ID에 대한 권한코드를 조회
	 * 
	 * @param menuCreateVO
	 */
	MenuCreateVO selectAuthorByUsr(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * 
	 * @param menuSiteMapVO
	 */
	List<EgovMap> selectMenuCreateSiteMapList(MenuSiteMapVO menuSiteMapVO);

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * 
	 * @param menuSiteMapVO
	 */
	List<MenuSiteMapVO> selectSiteMapByUser(MenuSiteMapVO menuSiteMapVO);

	/**
	 * 사이트맵 등록
	 * 
	 * @param menuSiteMapVO
	 * @param vHtmlValue
	 */
	boolean createSiteMap(MenuSiteMapVO menuSiteMapVO, String vHtmlValue);
	
}
