package aramframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import aramframework.com.sym.mnu.mpm.service.MenuManageVO;
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

@Mapper("menuManageMapper")
public interface MenuManageMapper {

	/**
	 * 메뉴목록을 조회
	 * 
	 * @param menuManageVO
	 */
	public List<EgovMap> selectMenuManageList(MenuManageVO menuManageVO);

	/**
	 * 메뉴목록관리 총건수를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	public int selectMenuManageListCnt(MenuManageVO menuManageVO);

	/**
	 * 메뉴목록관리 기본정보를 조회
	 * 
	 * @param menuManageVO
	 */
	public MenuManageVO selectMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴목록 기본정보를 등록
	 * 
	 * @param menuManageVO
	 */
	public void insertMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴목록 기본정보를 수정
	 * 
	 * @param menuManageVO
	 */
	public void updateMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴목록 기본정보를 삭제
	 * 
	 * @param menuManageVO
	 */
	public void deleteMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴정보 전체삭제 초기화
	 * 
	 */
	public void deleteAllMenuList();

	/**
	 * 메뉴번호 존재여부를 조회
	 * 
	 * @param menuManageVO
	 */
	public int selectMenuNoByPk(MenuManageVO menuManageVO);

	/**
	 * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회
	 * 
	 * @param menuManageVO
	 */
	public int selectUpperMenuNoByPk(MenuManageVO menuManageVO);

	/**
	 * 메뉴 전체목록을 조회
	 * 
	 */
	public List<EgovMap> selectMenuList();

	/**
	 * 메뉴 전체목록 총건수를 조회한다.
	 * 
	 */
	public int selectMenuListCnt();

}