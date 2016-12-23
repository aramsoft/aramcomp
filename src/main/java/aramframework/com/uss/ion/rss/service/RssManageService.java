package aramframework.com.uss.ion.rss.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.uss.ion.rss.dao.RssManageJDBC;
import aramframework.com.uss.ion.rss.dao.RssManageMapper;
import aramframework.com.uss.ion.rss.domain.RssManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * RSS태그관리를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class RssManageService extends EgovAbstractServiceImpl {

	/* RSS관리 DAO */
	@Autowired
	private RssManageJDBC rssManageJDBC;	
	
	@Autowired
	private RssManageMapper rssManageMapper;		

	/* RSS ID Generator Service */
	@Autowired
	private EgovIdGnrService rssManageIdGnrService;

	/**
	 * JDBC 테이블 목록을조회한다.
	 * 
	 */
	public List<ComCodeVO> selectRssManageTableList() {
		return rssManageJDBC.selectRssManageTableList();
	}

	/**
	 * JDBC 테이블 컬럼 목록을 조회한다.
	 * 
	 * @param map
	 */
	public List<ComCodeVO> selectRssManageTableColumnList(Map<String, String> map) {
		return rssManageJDBC.selectRssManageTableColumnList(map);
	}

	/**
	 * RSS태그관리를(을) 목록을 조회 한다.
	 * 
	 * @param rssManageVO
	 */
	public List<EgovMap> selectRssManageList(RssManageVO rssManageVO) {
		return rssManageMapper.selectRssManageList(rssManageVO);
	}

	/**
	 * RSS태그관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param rssManageVO
	 */
	public int selectRssManageListCnt(RssManageVO rssManageVO) {
		return rssManageMapper.selectRssManageListCnt(rssManageVO);
	}

	/**
	 * RSS태그관리를(을) 상세조회 한다.
	 * 
	 * @param rssManageVO
	 */
	public RssManageVO selectRssManageDetail(RssManageVO rssManageVO) {
		RssManageVO resultVo = rssManageMapper.selectRssManageDetail(rssManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(rssManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * RSS태그관리를(을) 등록한다.
	 * 
	 * @param rssManageVO
	 */
	public void insertRssManage(RssManageVO rssManageVO) {
		try {
			rssManageVO.setRssId((String) rssManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		rssManageMapper.insertRssManage(rssManageVO);
	}

	/**
	 * RSS태그관리를(을) 수정한다.
	 * 
	 * @param rssManageVO
	 */
	public void updateRssManage(RssManageVO rssManageVO) {
		rssManageMapper.updateRssManage(rssManageVO);
	}

	/**
	 * RSS태그관리를(을) 삭제한다.
	 * 
	 * @param rssManageVO
	 */
	public void deleteRssManage(RssManageVO rssManageVO) {
		rssManageMapper.deleteRssManage(rssManageVO);
	}

}
