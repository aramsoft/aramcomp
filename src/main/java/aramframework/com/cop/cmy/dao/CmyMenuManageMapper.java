package aramframework.com.cop.cmy.dao;

import java.util.List;

import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 메뉴를 관리에 관한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CmyMenuManageMapper {

	/**
	 * 메뉴목록을 조회
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectMenuManageList(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록관리 총건수를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public int selectMenuManageListCnt(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록을 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectMenuListExcel(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록관리 기본정보를 조회
	 * 
	 * @param communityMenuVO
	 */
	public CommunityMenuVO selectMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록 기본정보를 등록
	 * 
	 * @param communityMenuVO
	 */
	public void insertMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록 기본정보를 수정
	 * 
	 * @param communityMenuVO
	 */
	public void updateMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴목록 기본정보를 삭제
	 * 
	 * @param communityMenuVO
	 */
	public void deleteMenuManage(CommunityMenuVO communityMenuVO);

	/**
	 * 커뮤니티 메뉴목록전체를 삭제
	 * 
	 * @param trgetId
	 */
	public void deleteMenuManageTrget(String trgetId);

	/**
	 * 메뉴번호 존재여부를 조회
	 * 
	 * @param communityMenuVO
	 */
	public int selectMenuNmByPk(CommunityMenuVO communityMenuVO);

	/**
	 * 메뉴명으로부터 메뉴위치 조회
	 * 
	 * @param communityMenuVO
	 */
	public String selectMenuPosByMenuNm(CommunityMenuVO communityMenuVO);

}