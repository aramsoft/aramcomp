package aramframework.com.uss.ion.nws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.ion.nws.domain.NewsManageVO;
import aramframework.com.uss.ion.nws.service.NewsManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 뉴스정보를 처리하는 비즈니스 구현 클래스
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

@Service("newsManageService")
public class NewsManageServiceImpl extends EgovAbstractServiceImpl implements NewsManageService {

	@Autowired
	private NewsManageMapper newsManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService newsManageIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * 뉴스정보 목록을 조회한다.
	 * 
	 * @param newsManageVO
	 */
	public List<EgovMap> selectNewsList(NewsManageVO newsManageVO) {
		return newsManageMapper.selectNewsList(newsManageVO);
	}

	/**
	 * 뉴스정보 총 갯수를 조회한다.
	 * 
	 * @param newsManageVO
	 */
	public int selectNewsListCnt(NewsManageVO newsManageVO) {
		return newsManageMapper.selectNewsListCnt(newsManageVO);
	}

	/**
	 * 뉴스정보 상세조회한다.
	 * 
	 * @param newsManageVO
	 */
	public NewsManageVO selectNewsDetail(NewsManageVO newsManageVO) {
		NewsManageVO resultVo = newsManageMapper.selectNewsDetail(newsManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, newsManageVO); 
		return resultVo;
	}

	/**
	 * 뉴스정보를 등록한다.
	 * 
	 * @param newsManageVO
	 */
	public void insertNewsInfo(NewsManageVO newsManageVO) {
		try {
			newsManageVO.setNewsId(newsManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		newsManageMapper.insertNewsInfo(newsManageVO);
	}

	/**
	 * 뉴스정보를 수정한다.
	 * 
	 * @param newsManageVO
	 */
	public void updateNewsInfo(NewsManageVO newsManageVO) {
		newsManageMapper.updateNewsInfo(newsManageVO);
	}

	/**
	 * 뉴스정보를 삭제한다.
	 * 
	 * @param newsManageVO
	 */
	public void deleteNewsInfo(NewsManageVO newsManageVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(newsManageVO.getAtchFileId());

		newsManageMapper.deleteNewsInfo(newsManageVO);
	}

}
