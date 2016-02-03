package aramframework.com.cop.scp.service;

import java.util.List;

import aramframework.com.cop.scp.domain.ScrapVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 스크랩관리를 위한 서비스 인터페이스 클래스
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

public interface BBSScrapService {

	/**
	 * 스크랩에 대한 목록을 조회 한다.
	 * 
	 * @param scrapVO
	 */
	public List<EgovMap> selectScrapList(ScrapVO scrapVO);

	/**
	 * 스크랩에 대한 총갯수를 조회 한다.
	 * 
	 * @param scrapVO
	 */
	public int selectScrapListCnt(ScrapVO scrapVO);
	
	/**
	 * 스크랩 사용 가능 여부를 확인한다.
	 * 
	 */
	public boolean canUseScrap();

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
