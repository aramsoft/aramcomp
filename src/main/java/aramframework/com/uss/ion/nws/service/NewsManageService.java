package aramframework.com.uss.ion.nws.service;

import java.util.List;

import aramframework.com.uss.ion.nws.domain.NewsManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 뉴스정보를 처리하는 서비스 클래스
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

public interface NewsManageService {

	/**
	 * 뉴스목록을 조회한다.
	 * 
	 * @param newsManageVO
	 */
	List<EgovMap> selectNewsList(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보 총 갯수를 조회한다.
	 * 
	 * @param newsManageVO
	 */
	int selectNewsListCnt(NewsManageVO newsManageVO);

	/**
	 * 뉴스 상세조회를 한다.
	 * 
	 * @param newsManageVO
	 */
	NewsManageVO selectNewsDetail(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 등록한다.
	 * 
	 * @param newsManageVO
	 */
	void insertNewsInfo(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 수정한다.
	 * 
	 * @param newsManageVO
	 */
	void updateNewsInfo(NewsManageVO newsManageVO);

	/**
	 * 뉴스정보를 삭제한다.
	 * 
	 * @param newsManageVO
	 */
	void deleteNewsInfo(NewsManageVO newsManageVO);

}
