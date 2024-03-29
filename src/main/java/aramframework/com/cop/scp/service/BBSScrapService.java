package aramframework.com.cop.scp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.scp.dao.BBSScrapMapper;
import aramframework.com.cop.scp.domain.ScrapVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 스크랩관리를 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BBSScrapService extends EgovAbstractServiceImpl {

	@Autowired 
	private BBSScrapMapper bbsScrapMapper;
	
	@Autowired 
	private EgovIdGnrService scrapIdGnrService; 

	/**
	 * 스크랩에 대한 목록을 조회 한다.
	 * 
	 * @param scrapVO
	 */
	public List<EgovMap> selectScrapList(ScrapVO scrapVO) {
		return bbsScrapMapper.selectScrapList(scrapVO);
	}

	/**
	 * 스크랩에 대한 총갯수를 조회 한다.
	 * 
	 * @param scrapVO
	 */
	public int selectScrapListCnt(ScrapVO scrapVO) {
		return bbsScrapMapper.selectScrapListCnt(scrapVO);
	}

	/**
	 * 스크랩 사용 가능 여부를 확인한다.
	 * 
	 */
	public boolean canUseScrap() {
		// String flag = AramProperties.getProperty("Globals.addedOptions");
		// if (flag != null && flag.trim().equalsIgnoreCase("true"))
		// {//2011.09.15
		return true;
		// }
		// return false;
	}

	/**
	 * 스크랩에 대한 내용을 조회한다.
	 * 
	 * @param scrapVO
	 */
	public ScrapVO selectScrap(ScrapVO scrapVO) {
		return bbsScrapMapper.selectScrap(scrapVO);
	}

	/**
	 * 스크랩을 등록한다.
	 * 
	 * @param scrapVO
	 */
	public void insertScrap(ScrapVO scrapVO) {
		try {
			scrapVO.setScrapId(scrapIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		bbsScrapMapper.insertScrap(scrapVO);
	}

	/**
	 * 스크랩에 대한 내용을 수정한다.
	 * 
	 * @param scrapVO
	 */
	public void updateScrap(ScrapVO scrapVO) {
		bbsScrapMapper.updateScrap(scrapVO);
	}
	
	/**
	 * 스크랩을 삭제한다.
	 * 
	 * @param scrapVO
	 */
	public void deleteScrap(ScrapVO scrapVO) {
		bbsScrapMapper.deleteScrap(scrapVO);
	}

}
