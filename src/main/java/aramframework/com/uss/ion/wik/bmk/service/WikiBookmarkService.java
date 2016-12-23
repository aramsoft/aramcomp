package aramframework.com.uss.ion.wik.bmk.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.wik.bmk.dao.WikiBookmarkMapper;
import aramframework.com.uss.ion.wik.bmk.domain.WikiBookmarkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 위키북마크를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class WikiBookmarkService extends EgovAbstractServiceImpl {

	/* 위키북마크 DAO */
	@Autowired
	private WikiBookmarkMapper wikiBookmarkMapper;	

	/* WIKI_BKMK_ID Generator Service */
	@Autowired
	private EgovIdGnrService wikiBookmarkIdGnrService; 

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 위키북마크 목록을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public List<EgovMap> selectWikiBookmarkList(WikiBookmarkVO wikiBookmarkVO) {
		return wikiBookmarkMapper.selectWikiBookmarkList(wikiBookmarkVO);
	}

	/**
	 * 위키북마크를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public int selectWikiBookmarkListCnt(WikiBookmarkVO wikiBookmarkVO) {
		return (Integer) wikiBookmarkMapper.selectWikiBookmarkListCnt(wikiBookmarkVO);
	}

	/**
	 * 위키북마크를(을) 중복을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public int selectWikiBookmarkDuplicationCnt(WikiBookmarkVO wikiBookmarkVO) {
		return (Integer) wikiBookmarkMapper.selectWikiBookmarkDuplicationCnt(wikiBookmarkVO);
	}

	/**
	 * 위키북마크를(을) 등록한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public void insertWikiBookmark(WikiBookmarkVO wikiBookmarkVO) {
		// 아이디 가져오기

		String sUsid = (String) wikiBookmarkMapper.selectWikiBookmarkEmpUniqId(wikiBookmarkVO);

		log.debug("EgovWikiBookmarkServiceImpl.java sUsid>" + sUsid);

		// 아이디 비교
		if (sUsid != null) {
			// 위키북마크 키 설정
			try {
				wikiBookmarkVO.setWikiBkmkId((String) wikiBookmarkIdGnrService.getNextStringId());
			} catch (FdlException e) {
				throw new RuntimeException(e);
			}
			// 아이디 설정
			wikiBookmarkVO.setUsid((String) sUsid);
			wikiBookmarkVO.setFrstRegisterId((String) sUsid);
			wikiBookmarkVO.setLastUpdusrId((String) sUsid);
			wikiBookmarkMapper.insertWikiBookmark(wikiBookmarkVO);
			log.debug("insertWikiBookmark>" + sUsid);
		}

	}

	/**
	 * 위키북마크를(을) 삭제한다.
	 * 
	 * @param wikiBookmark
	 *            -위키북마크 정보 담김 객체
	 */
	public void deleteWikiBookmark(WikiBookmarkVO wikiBookmark) {
		wikiBookmarkMapper.deleteWikiBookmark(wikiBookmark);
	}

}
