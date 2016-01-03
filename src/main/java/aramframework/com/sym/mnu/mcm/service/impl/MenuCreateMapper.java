package aramframework.com.sym.mnu.mcm.service.impl;

import java.util.List;

import aramframework.com.sym.mnu.mcm.service.MenuCreateVO;
import aramframework.com.sym.mnu.mcm.service.MenuSiteMapVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다. 
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

@Mapper("menuCreateMapper")
public interface MenuCreateMapper {

	/**
	 * 메뉴생성관리 내역을 조회
	 * 
	 * @param menuCreateVO
	 */
	public List<EgovMap> selectMenuCreateList(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * 
	 * @param menuCreateVO
	 */
	public int selectMenuCreateListCnt(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성 내역을 조회
	 * 
	 * @param menuCreateVO
	 */
	public List<EgovMap> selectMenuCreateDetailList(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성내역 등록
	 * 
	 * @param menuCreateVO
	 */
	public void insertMenuCreate(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성내역 수정
	 * 
	 * @param menuCreateVO
	 */
	public void updateMenuCreate(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성내역 삭제
	 * 
	 * @param menuCreateVO
	 */
	public void deleteMenuCreate(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성내역 존재여부 조회한다.
	 * 
	 * @param menuCreateVO
	 */
	public int selectMenuCreateCnt(MenuCreateVO menuCreateVO);

	/**
	 * ID 존재여부를 조회
	 * 
	 * @param menuCreateVO
	 */
	public int selectUsrByPk(MenuCreateVO menuCreateVO);

	/**
	 * ID에 대한 권한코드를 조회
	 * 
	 * @param menuCreateVO
	 */
	public MenuCreateVO selectAuthorByUsr(MenuCreateVO menuCreateVO);

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * 
	 * @param menuSiteMapVO
	 */
	public List<EgovMap> selectMenuCreateSiteMapList(MenuSiteMapVO menuSiteMapVO);

	/**
	 * 사이트맵 등록
	 * 
	 * @param menuSiteMapVO
	 */
	public void insertSiteMap(MenuSiteMapVO menuSiteMapVO);

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * 
	 * @param menuSiteMapVO
	 */
	public List<MenuSiteMapVO> selectSiteMapByUser(MenuSiteMapVO menuSiteMapVO);

	/**
	 * 사이트맵 존재여부 조회한다.
	 * 
	 * @param menuSiteMapVO
	 */
	public int selectSiteMapCnt(MenuSiteMapVO menuSiteMapVO);

}
