package aramframework.com.cop.cmy.service;

import java.io.InputStream;
import java.util.List;

import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 메뉴를 관리에 관한 서비스 인터페이스 클래스를 정의한다.
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

public interface CmyMenuManageService {

	/**
	 * 메뉴 목록을 조회
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectMenuManageList(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public int selectMenuManageListCnt(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴 목록 엑셀 을 조회
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectMenuListExcel(CommunityMenuVO communityMenuVO);

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param trgetId
	 * @param checkedMenuNoForDel
	 */
	public void deleteMenuManageList(String trgetId, String checkedMenuNoForDel);

	/**
	 * 메뉴 상세정보를 조회
	 * 
	 * @param communityMenuVO
	 */
	public CommunityMenuVO selectMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴 정보를 등록
	 * 
	 * @param communityMenuVO
	 */
	public void insertMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴 정보를 수정
	 * 
	 * @param communityMenuVO
	 */
	public void updateMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴 정보를 삭제
	 * 
	 * @param communityMenuVO
	 */
	public void deleteMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public int selectMenuNoByPk(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴별명으로부터 메뉴번호 조회
	 * 
	 * @param cmmntyId
	 * @param menuAlias
	 */
	public String selectMenuNoByMenuAlias(String cmmntyId, String menuAlias);

	/**
	 * 메뉴 엑셀파일을 등록한다.
	 * 
	 * @param file
	 * @param cmmntyId
	 */
	public void insertExcelMenu(InputStream file, String cmmntyId); 
	
}