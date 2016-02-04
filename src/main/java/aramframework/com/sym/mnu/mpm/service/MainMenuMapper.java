package aramframework.com.sym.mnu.mpm.service;

import java.util.List;

import aramframework.com.sym.mnu.mpm.domain.MenuManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴관리, 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다.
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
public interface MainMenuMapper {

	/* ### 메뉴관련 프로세스 ### */
	/**
	 * MainMenu Head Menu 조회
	 * 
	 * @param menuManageVO
	 */
	public List<EgovMap> selectMainMenuHead(MenuManageVO menuManageVO);

	/**
	 * MainMenu Left Menu 조회
	 * 
	 * @param menuManageVO
	 */
	public List<EgovMap> selectMainMenuLeft(MenuManageVO menuManageVO);

	/**
	 * MainMenu Head MenuURL 조회
	 * 
	 * @param menuManageVO
	 */
	public String selectLastMenuURL(MenuManageVO menuManageVO);

	/**
	 * MainMenu Left Menu 조회
	 * 
	 * @param menuManageVO
	 */
	public int selectLastMenuNo(MenuManageVO menuManageVO);

	/**
	 * MainMenu Left Menu 조회
	 * 
	 * @param menuManageVO
	 */
	public int selectLastMenuNoCnt(MenuManageVO menuManageVO);
	
}