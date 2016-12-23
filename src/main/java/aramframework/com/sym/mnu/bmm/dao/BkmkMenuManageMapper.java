package aramframework.com.sym.mnu.bmm.dao;

import java.util.List;

import aramframework.com.sym.mnu.bmm.domain.BkmkMenuManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 바로가기메뉴를 관리하는 서비스를 정의하기위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface BkmkMenuManageMapper {

	/**
	 * 조건에 맞는 바로가기메뉴관리 정보 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 조건에 맞는 바로가기메뉴관리 정보 목록의 건수를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 바로가기메뉴관리 정보를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public BkmkMenuManageVO selectBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 바로가기메뉴관리 정보를 등록한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public void insertBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 바로가기메뉴관리 정보를 삭제한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public void deleteBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 등록할 메뉴정보 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 등록할 메뉴정보 목록의 건수를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkPreview(BkmkMenuManageVO bkmkMenuManageVO);

	/**
	 * 선택된 메뉴의 URL 을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public String selectUrl(BkmkMenuManageVO bkmkMenuManageVO);
	
}
