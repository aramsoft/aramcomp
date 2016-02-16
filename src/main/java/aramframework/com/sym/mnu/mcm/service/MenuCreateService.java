package aramframework.com.sym.mnu.mcm.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.mnu.mcm.dao.MenuCreateMapper;
import aramframework.com.sym.mnu.mcm.domain.MenuCreateVO;
import aramframework.com.sym.mnu.mcm.domain.MenuSiteMapVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴목록, 사이트맵 생성을 처리하는 비즈니스 구현 클래스를 정의한다.
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

@Service
public class MenuCreateService extends EgovAbstractServiceImpl {

	@Autowired
	private MenuCreateMapper menuCreateMapper;	
	
	/**
	 * 메뉴생성관리 목록을 조회
	 * 
	 * @param menuCreateVO
	 */
	public List<EgovMap> selectMenuCreateList(MenuCreateVO menuCreateVO) {
		return menuCreateMapper.selectMenuCreateList(menuCreateVO);
	}

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * 
	 * @param menuCreateVO
	 */
	public int selectMenuCreateListCnt(MenuCreateVO menuCreateVO) {
		return menuCreateMapper.selectMenuCreateListCnt(menuCreateVO);
	}

	/**
	 * 화면에 조회된 메뉴정보로 메뉴생성내역 데이터베이스에서 입력
	 * 
	 * @param checkedAuthorForInsert
	 * @param checkedMenuNoForInsert
	 */
	public void insertMenuCreateList(String checkedAuthorForInsert, String checkedMenuNoForInsert) {

		String[] insertMenuNo = checkedMenuNoForInsert.split(",");
		String insertAuthor = checkedAuthorForInsert;

		MenuCreateVO menuCreateVO = new MenuCreateVO();
		menuCreateVO.setAuthorCode(insertAuthor);
		int  AuthorCnt = menuCreateMapper.selectMenuCreateCnt(menuCreateVO);

		// 이전에 존재하는 권한코드에 대한 메뉴설정내역 삭제
		if (AuthorCnt > 0) {
			menuCreateMapper.deleteMenuCreate(menuCreateVO);
		}
		for (int i = 0; i < insertMenuNo.length; i++) {
			menuCreateVO.setAuthorCode(insertAuthor);
			menuCreateVO.setMenuNo(Integer.parseInt(insertMenuNo[i]));
			menuCreateMapper.insertMenuCreate(menuCreateVO);
		}
	}

	/**
	 * 메뉴생성 내역을 조회
	 * 
	 * @param menuCreateVO
	 */
	public List<EgovMap> selectMenuCreateDetailList(MenuCreateVO menuCreateVO) {
		return menuCreateMapper.selectMenuCreateDetailList(menuCreateVO);
	}

	/**
	 * ID에 대한 권한코드를 조회
	 * 
	 * @param menuCreateVO
	 */
	public MenuCreateVO selectAuthorByUsr(MenuCreateVO menuCreateVO) {
		MenuCreateVO resultVo = menuCreateMapper.selectAuthorByUsr(menuCreateVO);
		// searchVO 이전 
		resultVo.setSearchVO(menuCreateVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * ID 존재여부를 조회
	 * 
	 * @param menuCreateVO
	 */
	public int selectUsrByPk(MenuCreateVO menuCreateVO) {
		return menuCreateMapper.selectUsrByPk(menuCreateVO);
	}

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * 
	 * @param menuSiteMapVO
	 */
	public List<EgovMap> selectMenuCreateSiteMapList(MenuSiteMapVO menuSiteMapVO) {
		return menuCreateMapper.selectMenuCreateSiteMapList(menuSiteMapVO);
	}

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * 
	 * @param menuSiteMapVO
	 */
	public List<MenuSiteMapVO> selectSiteMapByUser(MenuSiteMapVO menuSiteMapVO) {
		return menuCreateMapper.selectSiteMapByUser(menuSiteMapVO);
	}

	/**
	 * 사이트맵 등록
	 * 
	 * @param menuSiteMapvo
	 * @param vHtmlValue
	 */
	public boolean createSiteMap(MenuSiteMapVO menuSiteMapVo, String vHtmlValue) {
		boolean chkCreate = false;
		String vSiteMapName = null;
		int SiteMapCnt = 0;
		MenuCreateVO menuCreateVO = new MenuCreateVO();

		menuCreateVO.setMenuNo(menuSiteMapVo.getMenuNo());
		menuCreateVO.setAuthorCode(menuSiteMapVo.getAuthorCode());
		// vSiteMapName =
		// menuSiteMapvo.getTmp_rootPath()+"/"+menuSiteMapvo.getBndeFileNm();
		vSiteMapName = menuSiteMapVo.getTmpRootPath() + menuSiteMapVo.getBndeFilePath() + menuSiteMapVo.getBndeFileNm();
		chkCreate = siteMapCreate(vSiteMapName, vHtmlValue);
		if (chkCreate) {
			SiteMapCnt = menuCreateMapper.selectSiteMapCnt(menuSiteMapVo);
			if (SiteMapCnt > 0) {
				menuCreateVO.setMapCreatId(menuSiteMapVo.getMapCreatId() + Integer.toString(SiteMapCnt));
				menuSiteMapVo.setMapCreatId(menuSiteMapVo.getMapCreatId() + Integer.toString(SiteMapCnt));
			} else {
				menuCreateVO.setMapCreatId(menuSiteMapVo.getMapCreatId());
			}
			menuCreateMapper.insertSiteMap(menuSiteMapVo);
			menuCreateMapper.updateMenuCreate(menuCreateVO);

		}
		return chkCreate;
	}

	/**
	 * 메뉴생성 사이트맵 Html 파일 생성
	 * 
	 * @param vSiteMapName
	 * @param vHtmlValue
	 */
	private boolean siteMapCreate(String vSiteMapName, String vHtmlValue) {
		boolean success = false;
		String FileName = null;
		char FILE_SEPARATOR = File.separatorChar;
		BufferedWriter out = null;
		try {
			FileName = vSiteMapName.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
			File file = new File(FileName);
			out = new BufferedWriter(new FileWriter(file));

			// 2011.10.12 사이트맵 생성시 특수문자 치환
			vHtmlValue = vHtmlValue.replaceAll("&lt;", "<");
			vHtmlValue = vHtmlValue.replaceAll("&gt;", ">");
			vHtmlValue = vHtmlValue.replaceAll("&quot;", "\"");
			vHtmlValue = vHtmlValue.replaceAll("&apos;", "'");

			out.write(vHtmlValue);
			success = true;
		} catch (IOException e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
			// 2011.10.07 사용한 자원 반환 추가
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ignore) {
					egovLogger.error("Exception:  " + ignore.getClass().getName());
					egovLogger.error("Exception  Message:  " + ignore.getMessage());
				}
			}
		}
		return success;
	}
}