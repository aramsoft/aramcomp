package aramframework.com.sym.mnu.mpm.service;

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

public interface MainMenuService {

	/* ### 메뉴관련 프로세스 ### */
	/**
	 * MainMenu Head Menu 조회
	 * 
	 * @param menuManageVO
	 */
	List<EgovMap> selectMainMenuHead(MenuManageVO menuManageVO);

	/**
	 * MainMenu Head Left 조회
	 * 
	 * @param menuManageVO
	 */
	List<EgovMap> selectMainMenuLeft(MenuManageVO menuManageVO);

	/**
	 * MainMenu Head MenuURL 조회
	 * 
	 * @param iMenuNo
	 * @param sUniqId
	 */
	String selectLastMenuURL(int iMenuNo, String sUniqId);

}