package aramframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.mnu.mpm.domain.MenuManageVO;
import aramframework.com.sym.mnu.mpm.service.MainMenuService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
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

@Service("mainMenuService")
public class MainMenuServiceImpl extends EgovAbstractServiceImpl implements MainMenuService {

	@Autowired
	private MainMenuMapper mainMenuMapper;	

	/* ### 메뉴관련 프로세스 ### */
	/**
	 * MainMenu Head Menu 조회
	 * 
	 * @param menuManageVO
	 */
	public List<EgovMap> selectMainMenuHead(MenuManageVO menuManageVO) {
		return mainMenuMapper.selectMainMenuHead(menuManageVO);
	}

	/**
	 * MainMenu Head Left 조회
	 * 
	 * @param menuManageVO
	 */
	public List<EgovMap> selectMainMenuLeft(MenuManageVO menuManageVO) {
		return mainMenuMapper.selectMainMenuLeft(menuManageVO);
	}

	/**
	 * MainMenu Head MenuURL 조회
	 * 
	 * @param iMenuNo
	 * @param sUniqId
	 */
	public String selectLastMenuURL(int iMenuNo, String sUniqId) {
		MenuManageVO menuManageVO = new MenuManageVO();
		menuManageVO.setMenuNo(selectLastMenuNo(iMenuNo, sUniqId));
		return mainMenuMapper.selectLastMenuURL(menuManageVO);
	}

	/**
	 * MainMenu Head Menu MenuNo 조회
	 * 
	 * @param iMenuNo
	 * @param sUniqId
	 */
	private int selectLastMenuNo(int iMenuNo, String sUniqId) {
		MenuManageVO menuManageVO = new MenuManageVO();
		menuManageVO.setMenuNo(iMenuNo);
		menuManageVO.setTmpUniqId(sUniqId);
		int chkMenuNo = iMenuNo;
		int cntMenuNo = 0;
		for (; ;) {
			cntMenuNo = mainMenuMapper.selectLastMenuNoCnt(menuManageVO);
			if (cntMenuNo == 0) break;
			
			chkMenuNo = mainMenuMapper.selectLastMenuNo(menuManageVO);
			menuManageVO.setMenuNo(chkMenuNo);
		}
		return chkMenuNo;
	}

}