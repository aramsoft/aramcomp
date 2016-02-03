package aramframework.com.uss.ion.nws.service.impl;

import java.util.List;

import aramframework.com.uss.ion.nws.domain.NewsManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 뉴스정보를 처리하는 DAO 클래스
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

@Mapper
public interface NewsManageMapper {

	/**
	 * 뉴스목록을 조회한다.
	 * 
	 * @param newsManageVO
	 */
	public List<EgovMap> selectNewsList(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보 총 갯수를 조회한다.
	 * 
	 * @param newsManageVO
	 */
	public int selectNewsListCnt(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 상세 조회한다.
	 * 
	 * @param newsManageVO
	 */
	public NewsManageVO selectNewsDetail(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 등록한다.
	 * 
	 * @param newsManageVO
	 */
	public void insertNewsInfo(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 수정한다.
	 * 
	 * @param newsManageVO
	 */
	public void updateNewsInfo(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 삭제한다.
	 * 
	 * @param newsManageVO
	 */
	public void deleteNewsInfo(NewsManageVO newsManageVO);

}
