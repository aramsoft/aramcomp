package aramframework.com.cop.scp.service.impl;

import java.util.List;

import aramframework.com.cop.scp.service.ScrapVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 스크랩관리를 위한 데이터 접근 클래스
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
public interface BBSScrapMapper {

	/**
	 * 스크랩에 대한 목록을 조회 한다.
	 * 
	 * @param scrapVO
	 */
	public List<EgovMap> selectScrapList(ScrapVO scrapVO);

	/**
	 * 스크랩에 대한 목록 건수를 조회 한다.
	 * 
	 * @param scrapVO
	 */
	public int selectScrapListCnt(ScrapVO scrapVO);

	/**
	 * 스크랩에 대한 내용을 조회한다.
	 * 
	 * @param scrapVO
	 */
	public ScrapVO selectScrap(ScrapVO scrapVO);

	/**
	 * 스크랩을 등록한다.
	 * 
	 * @param scrapVO
	 */
	public void insertScrap(ScrapVO scrapVO);

	/**
	 * 스크랩에 대한 내용을 수정한다.
	 * 
	 * @param scrapVO
	 */
	public void updateScrap(ScrapVO scrapVO);
	
	/**
	 * 스크랩을 삭제한다.
	 * 
	 * @param scrapVO
	 */
	public void deleteScrap(ScrapVO scrapVO);

}
