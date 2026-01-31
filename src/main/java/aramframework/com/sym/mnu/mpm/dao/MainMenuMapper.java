package aramframework.com.sym.mnu.mpm.dao;

import java.util.List;

import aramframework.com.sym.mnu.mpm.domain.MenuManageVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴관리, 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
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