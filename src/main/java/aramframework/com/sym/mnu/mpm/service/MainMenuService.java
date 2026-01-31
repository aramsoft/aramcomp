package aramframework.com.sym.mnu.mpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.mnu.mpm.dao.MainMenuMapper;
import aramframework.com.sym.mnu.mpm.domain.MenuManageVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class MainMenuService extends EgovAbstractServiceImpl {

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
	 * @param sUserId
	 */
	public String selectLastMenuURL(int iMenuNo, String sUserId) {
		MenuManageVO menuManageVO = new MenuManageVO();
		menuManageVO.setMenuNo(selectLastMenuNo(iMenuNo, sUserId));
		return mainMenuMapper.selectLastMenuURL(menuManageVO);
	}

	/**
	 * MainMenu Head Menu MenuNo 조회
	 * 
	 * @param iMenuNo
	 * @param sUserId
	 */
	private int selectLastMenuNo(int iMenuNo, String sUserId) {
		MenuManageVO menuManageVO = new MenuManageVO();
		menuManageVO.setMenuNo(iMenuNo);
		menuManageVO.setTmpUserId(sUserId);
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