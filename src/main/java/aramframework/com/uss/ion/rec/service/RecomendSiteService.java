package aramframework.com.uss.ion.rec.service;

import java.util.List;

import aramframework.com.uss.ion.rec.domain.RecomendSiteVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 추천사이트정보를 처리하는 서비스 클래스
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

public interface RecomendSiteService {

	/**
	 * 추천사이트목록을 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	List<EgovMap> selectRecomendSiteList(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트 총 갯수를 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	int selectRecomendSiteListCnt(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트 상세조회를 한다.
	 * 
	 * @param recomendSiteVO
	 */
	RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트를 등록한다.
	 * 
	 * @param recomendSiteVO
	 */
	void insertRecomendSiteInfo(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트를 수정한다.
	 * 
	 * @param recomendSiteVO
	 */
	void updateRecomendSiteInfo(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트를 삭제한다.
	 * 
	 * @param recomendSiteVO
	 */
	void deleteRecomendSiteInfo(RecomendSiteVO recomendSiteVO);

}
