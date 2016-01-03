package aramframework.com.sym.mnu.mpm.service;

import java.io.InputStream;
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

public interface MenuManageService {

	/**
	 * 메뉴 목록을 조회
	 * 
	 * @param menuManageVO
	 */
	List<EgovMap> selectMenuManageList(MenuManageVO menuManageVO);

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	int selectMenuManageListCnt(MenuManageVO menuManageVO);

	/**
	 * 메뉴 상세정보를 조회
	 * 
	 * @param menuManageVO
	 */
	MenuManageVO selectMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	int selectMenuNoByPk(MenuManageVO menuManageVO);
	int selectUpperMenuNoByPk(MenuManageVO menuManageVO);

	/**
	 * 메뉴 정보를 등록
	 * 
	 * @param menuManageVO
	 */
	void insertMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴 정보를 수정
	 * 
	 * @param menuManageVO
	 */
	void updateMenuManage(MenuManageVO menuManageVO);

	/**
	 * 메뉴 정보를 삭제
	 * 
	 * @param menuManageVO
	 */
	void deleteMenuManage(MenuManageVO menuManageVO);

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedMenuNoForDel
	 */
	void deleteMenuManageList(String checkedMenuNoForDel);

	/**
	 * 메뉴 목록을 조회
	 * 
	 */
	List<EgovMap> selectMenuList();

	/* 일괄처리 프로세스 */

	/**
	 * 메뉴일괄초기화 프로세스 메뉴목록테이블, 프로그램 목록테이블 전체 삭제
	 * 
	 */
	boolean menuBndeAllDelete();

	/**
	 * 메뉴일괄등록 프로세스
	 * 
	 * @param menuManageVO
	 * @param inputStream
	 */
	String menuBndeRegist(MenuManageVO menuManageVO, InputStream inputStream);

}