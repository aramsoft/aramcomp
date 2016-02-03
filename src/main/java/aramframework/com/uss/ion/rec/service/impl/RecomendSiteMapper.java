package aramframework.com.uss.ion.rec.service.impl;

import java.util.List;

import aramframework.com.uss.ion.rec.domain.RecomendSiteVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 추천사이트정보를 처리하는 DAO 클래스
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
public interface RecomendSiteMapper {

	/**
	 * 추천사이트 목록을 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	public List<EgovMap> selectRecomendSiteList(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트 총 갯수를 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	public int selectRecomendSiteListCnt(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	public RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트를 등록한다.
	 * 
	 * @param recomendSiteVO
	 */
	public void insertRecomendSiteInfo(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트를 수정한다.
	 * 
	 * @param recomendSiteVO
	 */
	public void updateRecomendSiteInfo(RecomendSiteVO recomendSiteVO);

	/**
	 * 추천사이트를 삭제한다.
	 * 
	 * @param recomendSiteVO
	 */
	public void deleteRecomendSiteInfo(RecomendSiteVO recomendSiteVO);

}
