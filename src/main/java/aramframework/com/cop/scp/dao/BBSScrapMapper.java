package aramframework.com.cop.scp.dao;

import java.util.List;

import aramframework.com.cop.scp.domain.ScrapVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 스크랩관리를 위한 데이터 접근 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
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
